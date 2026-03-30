package exercise2.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Commands {

    public static StringBuilder head(String filename, int numberOfLines) throws FileNotFoundException {

        if (numberOfLines < 0)
            throw new IllegalArgumentException("Number of lines must be positive");

        StringBuilder result = new StringBuilder();

        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {

            for (int i = 0; i < numberOfLines && scanner.hasNextLine(); i++) {
                result.append(scanner.nextLine()).append("\n");
            }

        } catch (Exception e) {
            throw new FileNotFoundException("File not found: " + filename);
        }

        return result;
    }

    public static StringBuilder tail(String filename, int numberOfLines) throws IOException {

        if (numberOfLines < 0)
            throw new IllegalArgumentException("Number of lines must be positive");

        File file = new File(filename);
        StringBuilder result = new StringBuilder();

        try (FileReader fr = new FileReader(file)) {

            List<String> content = fr.readAllLines();

            //create iterator from the end of the list
            ListIterator<String> it = content.listIterator(content.size());

            int counter = 0;
            while (it.hasPrevious() && counter < numberOfLines) {
                result = new StringBuilder(it.previous() + "\n").append(result);
                counter++;
            }
        } catch (Exception e) {
            throw new FileNotFoundException("File not found: " + filename);
        }

        return result;
    }

    public static StringBuilder treeSize(String pathOfDirectory) {
        StringBuilder result = new StringBuilder();
        File file = new File(pathOfDirectory);

        if (!file.isDirectory() || !file.exists())
            throw new IllegalArgumentException("Path must be a valid directory");

        long _ = tree(file, result, 0);

        //result list is upside down, so reverse it
        // list.reverse() seems to be flaky so we had do go the algorithmic way
        String[] s = result.toString().split("\n");

        String resultString = "";
        for (int i = s.length -1; i >= 0; i--) {
            resultString += s[i] + "\n";
        }

        return resultString.isEmpty() ? new StringBuilder("Directory is empty") : new StringBuilder(resultString);
    }

    static DecimalFormat df = new DecimalFormat("#,###", DecimalFormatSymbols.getInstance(Locale.GERMANY));

    private static long tree(File path, StringBuilder result, int depth) {
        long byteCounter = 0;



        for (File child : Objects.requireNonNull(path.listFiles())) {

            if (child.isDirectory()) {
                byteCounter += tree(child, result, depth + 1);
            } else {
                byteCounter += child.length();
                result.repeat("\t", depth + 1)
                        .append(child.getName())
                        .append(" - ")
                        .append(df.format(child.length()))
                        .append(" bytes \n");
            }
        }

        result.repeat("\t", depth)
                .append(path.getName())
                .append(" - ")
                .append(df.format(byteCounter))
                .append(" bytes")
                .append("\n");

        return byteCounter;
    }

    public static class LocStatistics {
        int numberOfLines;
        int numberOfWords;
        int numberOfCharacters;

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            LocStatistics that = (LocStatistics) o;
            return numberOfLines == that.numberOfLines && numberOfWords == that.numberOfWords && numberOfCharacters == that.numberOfCharacters;
        }

        @Override
        public int hashCode() {
            return Objects.hash(numberOfLines, numberOfWords, numberOfCharacters);
        }

        public LocStatistics(int numberOfLines, int numberOfWords, int numberOfCharacters) {
            this.numberOfLines = numberOfLines;
            this.numberOfWords = numberOfWords;
            this.numberOfCharacters = numberOfCharacters;
        }

        public int getNumberOfLines() {
            return numberOfLines;
        }

        public int getNumberOfWords() {
            return numberOfWords;
        }

        public int getNumberOfCharacters() {
            return numberOfCharacters;
        }

        @Override
        public String toString() {
            return "lines=" + numberOfLines + ", words=" + numberOfWords + ", characters=" + numberOfCharacters;
        }
    }

    public static LocStatistics loc(String path) throws IOException {

        File file = new File(path);
        if (!file.exists() || !file.isFile())
            throw new IllegalArgumentException("Path must be a valid file");

        StringBuilder result = new StringBuilder();
        LocStatistics locStatistics = new LocStatistics(0, 0, 0);

        try (FileReader reader = new FileReader(file)) {

            List<String> lines = reader.readAllLines();
            locStatistics.numberOfLines = lines.size();

            for (int i = 0; i < locStatistics.numberOfLines; i++) {
                //Split line by spaces
                for (String word : lines.get(i).split(" ")) {
                    if (!word.isEmpty()) {
                        locStatistics.numberOfWords++;
                        locStatistics.numberOfCharacters += word.length();
                    }
                }
            }

        }
        return locStatistics;
    }

}
