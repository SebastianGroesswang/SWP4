package exercise1;

import exercise1.search.BoyerMooreSearch;
import exercise1.search.BruteSearch;
import exercise1.utils.DataCollector;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    @ParameterizedTest
    @CsvSource({
            "this is a simple example, this, 0",
            "this is a simple example, is, 2",
            "this is a simple example, simple, 10",
            "this is a simple example, example, 17",
    })
    public void testPatternFoundBruteSearch(String text, String pattern, int expected) {
        DataCollector dc = new DataCollector();
        int result = BruteSearch.search(text, pattern, 0, dc);
        assertEquals(expected, result);
    }

    @Test
    public void testPatternNotFoundKnuthMorrisPrattSearch() {
        String text = "this is a simple example";
        String pattern = "complex";
        int startPos = 0;
        DataCollector dc = new DataCollector();
        int result = 0;// KnuthMorrisPrattSearch.search(text, pattern, startPos, dc);
        assertEquals(-1, result);
    }
    @Test
    public void testEmptyTextBoyerMooreSearch() {
        String text = "";
        String pattern = "simple";
        int startPos = 0;
        DataCollector dc = new DataCollector();
        int result = BoyerMooreSearch.search(text, pattern, startPos, dc);
        assertEquals(-1, result);
    }
}