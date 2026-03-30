package exercise1.test;

import exercise1.utils.CharComparison;
import exercise1.utils.DataCollector;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataCollectorTest {

    @Test
    void shouldTrackCharStatsAndComparisonCount_whenCountedEqualIsCalled() {
        DataCollector dc = new DataCollector();

        assertTrue(dc.countedEqual('a', 'a'));
        assertFalse(dc.countedEqual('a', 'b'));
        assertFalse(dc.countedEqual('x', 'y'));

        assertEquals(3, dc.getCharComparisons());

        List<CharComparison> stats = dc.getCharComparison();
        assertEquals(2, stats.size());

        CharComparison aStats = stats.stream()
                .filter(c -> c.getCharacter() == 'a')
                .findFirst()
                .orElseThrow();
        CharComparison xStats = stats.stream()
                .filter(c -> c.getCharacter() == 'x')
                .findFirst()
                .orElseThrow();

        assertEquals(1, aStats.getCorrectCount());
        assertEquals(1, aStats.getIncorrectCount());
        assertEquals(0, xStats.getCorrectCount());
        assertEquals(1, xStats.getIncorrectCount());
    }

    @Test
    void shouldIncrementAdditionsCounter_whenAddIsCalled() {
        DataCollector dc = new DataCollector();

        assertEquals(9, dc.add(4, 5));
        assertEquals(1, dc.getNumberOfAdditions());
    }

    @Test
    void shouldIncrementAssignmentsCounter_whenAssignmentIsCalled() {
        DataCollector dc = new DataCollector();

        String value = dc.assignment("hello");
        assertEquals("hello", value);
        assertEquals(1, dc.getNumberOfAssignments());
    }

    @Test
    void shouldIncrementComparisonAndChatCounters_whenCompareTermIsCalled() {
        DataCollector dc = new DataCollector();

        assertTrue(dc.compareTerm(true));
        assertFalse(dc.compareTerm(false));

        assertEquals(2, dc.getNumberOfComparisons());
    }

    @Test
    void shouldIncrementMultiplicationsCounter_whenMultiplyIsCalled() {
        DataCollector dc = new DataCollector();

        assertEquals(42, dc.multiply(6, 7));
        assertEquals(1, dc.getNumberOfMultiplications());
    }

    @Test
    void shouldReturnUnmodifiableCharComparisonList() {
        DataCollector dc = new DataCollector();
        dc.countedEqual('k', 'k');

        List<CharComparison> comparison = dc.getCharComparison();
        assertEquals(1, comparison.size());
        assertEquals('k', comparison.getFirst().getCharacter());
        assertEquals(1, comparison.getFirst().getCorrectCount());
        assertEquals(0, comparison.getFirst().getIncorrectCount());
        assertThrows(UnsupportedOperationException.class,
                () -> comparison.add(new CharComparison('z', 1, 0)));
    }

    @Test
    void shouldIncrementIndicesCounter_whenGettingIndexFromList() {
        DataCollector dc = new DataCollector();
        List<String> items = Arrays.asList("A", "B", "C");

        assertEquals("B", dc.getIndex(items, 1));
        assertEquals(1, dc.getNumberOfIndices());
    }

    @Test
    void shouldIncrementIndicesCounter_whenGettingIndexFromArray() {
        DataCollector dc = new DataCollector();
        Integer[] values = {10, 20, 30};

        assertEquals(30, dc.getIndex(values, 2));
        assertEquals(1, dc.getNumberOfIndices());
    }

    @Test
    void shouldIncrementIndicesCounter_whenGettingIndexFromString() {
        DataCollector dc = new DataCollector();

        assertEquals('e', dc.getIndex("hello", 1));
        assertEquals(1, dc.getNumberOfIndices());
    }

    @Test
    void shouldIncrementIndicesAndAssignmentsCounters_whenSettingIndexInArray() {
        DataCollector dc = new DataCollector();
        String[] arr = {"a", "b"};

        dc.setIndexOfCollection(arr, 1, "x");

        assertEquals("x", arr[1]);
        assertEquals(1, dc.getNumberOfIndices());
        assertEquals(1, dc.getNumberOfAssignments());
    }
}