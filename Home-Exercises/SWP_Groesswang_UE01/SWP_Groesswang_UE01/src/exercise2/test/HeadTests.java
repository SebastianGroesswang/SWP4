package exercise2.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class HeadTests {


    @Test
    void testHeadWithExistingFile() throws FileNotFoundException {

        String expected = "a\n" +
                "b\n" +
                "c\n";

        String filename = "src/exercise2/test/TestFile1";
        int numberOfLines = 3;
        StringBuilder result = exercise2.commands.Commands.head(filename, numberOfLines);
        assertEquals(expected, result.toString());
    }

    @Test
    void testHeadWithNonExistingFile() {
        String filename = "src/exercise2/test/NonExistingFile";
        int numberOfLines = 3;

        assertThrows(FileNotFoundException.class, () -> {
            exercise2.commands.Commands.head(filename, numberOfLines);
        });
    }

    @Test
    void testHeadWithNegativeNumberOfLines() throws FileNotFoundException {
        String filename = "src/exercise2/test/TestFile1";
        int numberOfLines = -3;
        assertThrows(IllegalArgumentException.class, () -> {
            exercise2.commands.Commands.head(filename, numberOfLines);
        });
    }

    @Test
    void testHeadWithMoreLinesThanFile() throws FileNotFoundException {
        String expected = "one\n" +
                "two\n" +
                "three\n";

        String filename = "src/exercise2/test/TestFile2";
        int numberOfLines = 10;
        StringBuilder result = exercise2.commands.Commands.head(filename, numberOfLines);
        assertEquals(expected, result.toString());
    }
}