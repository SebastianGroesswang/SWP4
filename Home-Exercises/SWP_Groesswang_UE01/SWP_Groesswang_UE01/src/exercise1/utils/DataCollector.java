package exercise1.utils;

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
    public int getTotalComparisons() {
        return totalComparisons;
    }

    /// counting fields
    private int totalComparisons;
    private final ArrayList<CharComparison> char_comparison = new ArrayList<>();
    private int numberOfIndices;
    private int numberOfAssignments;
    private int numberOfAdditions;
    private int numberOfMultiplications;
    private int numberOfComparisons;


    public DataCollector() {
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
        totalComparisons++;
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

    public <E> boolean compare(E a, E b) {
        numberOfComparisons++;
        return a.equals(b);
    }

    public boolean compareTerm(boolean term) {
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

        System.out.println("Total comparisons: " + totalComparisons);
    }
}
