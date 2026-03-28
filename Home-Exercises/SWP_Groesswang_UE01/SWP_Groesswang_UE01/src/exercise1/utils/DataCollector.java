package exercise1.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class should count following operations:
 * - total number of char comparisons
 * - number of additions
 * - number of multiplications
 * - number of indices
 * - number of comparison
 * - number of definitions
 */
public class DataCollector {

    /// counting fields
    private int chatComparisons;
    private final ArrayList<CharComparison> char_comparison = new ArrayList<>();
    private int numberOfIndices;
    private int numberOfAssignments;
    private int numberOfAdditions;
    private int numberOfMultiplications;
    private int numberOfComparisons;


    public DataCollector() {
    }

    public int getChatComparisons() {
        return chatComparisons;
    }

    public int getNumberOfIndices() {
        return numberOfIndices;
    }

    public int getNumberOfAssignments() {
        return numberOfAssignments;
    }

    public int getNumberOfAdditions() {
        return numberOfAdditions;
    }

    public int getNumberOfMultiplications() {
        return numberOfMultiplications;
    }

    public int getNumberOfComparisons() {
        return numberOfComparisons;
    }

    //adds a statistic for a character comparison, if the character is already in the list, it updates the counts, otherwise it adds a new entry
    private void actionsOnNumber(final char character, final boolean correct) {
        for (CharComparison data : char_comparison) {
            if (data.character == character) {
                if (correct) {
                    data.correctCount++;
                } else {
                    data.incorrectCount++;
                }
                return;
            }
        }

        char_comparison.add(new CharComparison(character, correct ? 1 : 0, correct ? 0 : 1));
    }

    public boolean countedEqual(final char a, final char b) {
        chatComparisons++;
        actionsOnNumber(a, a == b);
        return a == b;
    }

    public int add(int a, int b) {
        numberOfAdditions++;
        return a + b;
    }

    public <E> E assignment(E a) {
        numberOfAssignments++;
        return a;
    }

    public boolean compareTerm(boolean term) {
        chatComparisons++;
        numberOfComparisons++;
        return term;
    }

    public int multiply(int a, int b) {
        numberOfMultiplications++;
        return a * b;
    }

    public List<CharComparison> getCharComparison() {
        return Collections.unmodifiableList(char_comparison);
    }

    public <E> E getIndex(List<E> collection, int a) {
        numberOfIndices++;
        return collection.get(a);
    }

    public <E> E getIndex(E[] collection, int a) {
        numberOfIndices++;
        return collection[a];
    }

    public char getIndex(String s, int i) {
        numberOfIndices++;
        return s.charAt(i);
    }

    public <E> void setIndexOfCollection(E[] collection, int index, E value) {
        numberOfIndices++;
        numberOfAssignments++;
        collection[index] = value;
    }

    public void printComparison() {
        for (CharComparison data : char_comparison) {
            System.out.println(data.character + " : " + data.correctCount + " correct, " + data.incorrectCount + " incorrect");
        }

        System.out.println("Total comparisons: " + chatComparisons);
    }

    public void exportStatistics() {
        Path output = Path.of("statistics.csv");

        try (BufferedWriter writer = Files.newBufferedWriter(output)) {
            writer.write("metric,value");
            writer.newLine();
            writer.write("chat_comparisons," + chatComparisons);
            writer.newLine();
            writer.write("numberOfIndices," + numberOfIndices);
            writer.newLine();
            writer.write("numberOfAssignments," + numberOfAssignments);
            writer.newLine();
            writer.write("numberOfAdditions," + numberOfAdditions);
            writer.newLine();
            writer.write("numberOfMultiplications," + numberOfMultiplications);
            writer.newLine();
            writer.write("numberOfComparisons," + numberOfComparisons);
            writer.newLine();
            writer.newLine();

            writer.write("character,correctCount,incorrectCount");
            writer.newLine();
            for (CharComparison data : char_comparison) {
                writer.write(escapeCsv(String.valueOf(data.character)) + "," + data.correctCount + "," + data.incorrectCount);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("CSV export failed: " + output.toAbsolutePath(), e);
        }
    }

    private String escapeCsv(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
