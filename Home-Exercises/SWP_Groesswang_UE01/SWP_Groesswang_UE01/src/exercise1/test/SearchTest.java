package exercise1.test;

import exercise1.search.BoyerMooreSearch;
import exercise1.search.BruteSearch;
import exercise1.search.KnuthMorrisPrattSearch;
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
        int result = BruteSearch.search(text, pattern, dc);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "this is a simple example, simple, 10",
            "this is a simple example, example, 17",
    })
    public void testPatternFoundWithStartingPositionBruteSearch(String text, String pattern, int expected) {
        DataCollector dc = new DataCollector();
        int result = BruteSearch.search(text, pattern, 5, dc);
        assertEquals(expected, result);
    }


    @ParameterizedTest
    @CsvSource({
            "this is a simple example, this, 0",
            "this is a simple example, is, 2",
            "this is a simple example, simple, 10",
            "this is a simple example, example, 17",
    })
    public void testPatternFoundKnuthMorrisPrattSearch(String text, String pattern, int expected) {
        DataCollector dc = new DataCollector();
        int result = KnuthMorrisPrattSearch.search(text, pattern, dc);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "this is a simple example, simple, 10",
            "this is a simple example, example, 17",
    })
    public void testPatternFoundWithStartingPositionKnuthMorrisPrattSearch(String text, String pattern, int expected) {
        DataCollector dc = new DataCollector();
        int result = KnuthMorrisPrattSearch.search(text, pattern, 5, dc);
        assertEquals(expected, result);
    }


    @ParameterizedTest
    @CsvSource({
            "this is a simple example, this, 0",
            "this is a simple example, is, 2",
            "this is a simple example, simple, 10",
            "this is a simple example, example, 17",
    })
    public void testPatternFoundBoyerMooreSearch(String text, String pattern, int expected) {
        DataCollector dc = new DataCollector();
        int result = BoyerMooreSearch.search(text, pattern, dc);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "this is a simple example, simple, 10",
            "this is a simple example, example, 17",
    })
    public void testPatternFoundWithStartingPositionBoyerMooreSearch(String text, String pattern, int expected) {
        DataCollector dc = new DataCollector();
        int result = BoyerMooreSearch.search(text, pattern, 5, dc);
        assertEquals(expected, result);
    }


    @Test
    public void testPatternNotFoundBrutSearch() {
        String text = "this is a simple example";
        String pattern = "complex";
        int startPos = 0;
        DataCollector dc = new DataCollector();
        int result = BruteSearch.search(text, pattern, startPos, dc);
        assertEquals(-1, result);
    }

    @Test
    public void testPatternNotFoundKnuthMorrisPrattSearch() {
        String text = "this is a simple example";
        String pattern = "complex";
        int startPos = 0;
        DataCollector dc = new DataCollector();
        int result = KnuthMorrisPrattSearch.search(text, pattern, startPos, dc);
        assertEquals(-1, result);
    }

    @Test
    public void testPatternNotFoundBoyerMooreSearch() {
        String text = "this is a simple example";
        String pattern = "complex";
        int startPos = 0;
        DataCollector dc = new DataCollector();
        int result = BoyerMooreSearch.search(text, pattern, startPos, dc);
        assertEquals(-1, result);
    }

    @Test
    public void testEmptyTextBruteSearch() {
        String text = "";
        String pattern = "simple";
        int startPos = 0;
        DataCollector dc = new DataCollector();
        int result = BruteSearch.search(text, pattern, startPos, dc);
        assertEquals(-1, result);
    }

    @Test
    public void testEmptyTextKnuthMorrisPrattSearch() {
        String text = "";
        String pattern = "simple";
        int startPos = 0;
        DataCollector dc = new DataCollector();
        int result = KnuthMorrisPrattSearch.search(text, pattern, startPos, dc);
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

    @Test
    public void testPatternLongerThanTextBruteSearch() {
        String text = "abc";
        String pattern = "abcdef";
        DataCollector dc = new DataCollector();

        int result = BruteSearch.search(text, pattern, 0, dc);
        assertEquals(-1, result);
    }

    @Test
    public void testPatternLongerThanTextKnuthMorrisPrattSearch() {
        String text = "abc";
        String pattern = "abcdef";
        DataCollector dc = new DataCollector();

        int result = KnuthMorrisPrattSearch.search(text, pattern, 0, dc);
        assertEquals(-1, result);
    }

    @Test
    public void testPatternLongerThanTextBoyerMooreSearch() {
        String text = "abc";
        String pattern = "abcdef";
        DataCollector dc = new DataCollector();

        int result = BoyerMooreSearch.search(text, pattern, 0, dc);
        assertEquals(-1, result);
    }

    @Test
    public void testNegativeStartingPositionBruteSearch() {
        String text = "abcdef";
        String pattern = "bc";
        DataCollector dc = new DataCollector();
        BruteSearch.search(text, pattern, -1, dc);
    }

    @Test
    public void testNegativeStartingPositionKnuthMorrisPrattSearch() {
        String text = "abcdef";
        String pattern = "bc";
        DataCollector dc = new DataCollector();

        int result = KnuthMorrisPrattSearch.search(text, pattern, -1, dc);
        assertEquals(-1, result);
    }

    @Test
    public void testNegativeStartingPositionBoyerMooreSearch() {
        String text = "abcdef";
        String pattern = "bc";
        DataCollector dc = new DataCollector();

        int result = BoyerMooreSearch.search(text, pattern, -1, dc);
        assertEquals(-1, result);
    }
}