package exercise1;

import exercise1.search.BoyerMooreSearch;
import exercise1.search.BruteSearch;
import exercise1.search.KnuthMorrisPrattSearch;
import exercise1.utils.CharComparison;
import exercise1.utils.DataCollector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {

    public static void main(String[] args) {
        String text = "In software engineering we test search algorithms to compare their behavior and collect statistics for each run.";

        runSearchAndReport("BruteSearch", text, "search", (t, p, dc) -> BruteSearch.search(t, p, dc));
        runSearchAndReport("KnuthMorrisPrattSearch", text, "algorithms", (t, p, dc) -> KnuthMorrisPrattSearch.search(t, p, dc));
        runSearchAndReport("BoyerMooreSearch", text, "statistics", (t, p, dc) -> BoyerMooreSearch.search(t, p, dc));
    }

    private static void runSearchAndReport(String algorithmName, String text, String pattern, SearchFunction searchFunction) {
        DataCollector dc = new DataCollector();
        int position = searchFunction.search(text, pattern, dc);

        System.out.println("\n=== " + algorithmName + " ===");
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Result index: " + position);

        dc.exportStatistics();
        Path exportedFile = moveExportedFile(algorithmName);
        System.out.println("CSV exported to: " + exportedFile.toAbsolutePath());

        printStatisticsToConsole(dc);
    }

    private static Path moveExportedFile(String algorithmName) {
        Path source = Path.of("statistics.csv");
        Path target = Path.of("statistics_" + algorithmName + ".csv");

        try {
            return Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Could not move exported statistics file to " + target.toAbsolutePath(), e);
        }
    }

    private static void printStatisticsToConsole(DataCollector dc) {
        System.out.println("metric,value");
        System.out.println("chat_comparisons," + dc.getCharComparisons());
        System.out.println("numberOfIndices," + dc.getNumberOfIndices());
        System.out.println("numberOfAssignments," + dc.getNumberOfAssignments());
        System.out.println("numberOfAdditions," + dc.getNumberOfAdditions());
        System.out.println("numberOfMultiplications," + dc.getNumberOfMultiplications());
        System.out.println("numberOfComparisons," + dc.getNumberOfComparisons());
        System.out.println();
        System.out.println("character,correctCount,incorrectCount");

        for (CharComparison entry : dc.getCharComparison()) {
            System.out.println(entry.getCharacter() + "," + entry.getCorrectCount() + "," + entry.getIncorrectCount());
        }
    }

    @FunctionalInterface
    private interface SearchFunction {
        int search(String text, String pattern, DataCollector dc);
    }
}
