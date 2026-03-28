package exercise2.test;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TailTest {

    @Test
    void testTailWithExistingFile() throws IOException {

        String expected = "x\n" +
                "y\n" +
                "z\n";

        String filename = "src/exercise2/test/TestFile1";
        int numberOfLines = 3;
        StringBuilder result = exercise2.commands.Commands.tail(filename, numberOfLines);
        assertEquals(expected, result.toString());
    }

    @Test
    void testTailWithNonExistingFile() {
        String filename = "src/exercise2/test/NonExistingFile";
        int numberOfLines = 3;

        assertThrows(FileNotFoundException.class, () -> {
            exercise2.commands.Commands.tail(filename, numberOfLines);
        });
    }

    @Test
    void testTailWithNegativeNumberOfLines() {
        String filename = "src/exercise2/test/TestFile1";
        int numberOfLines = -3;
        assertThrows(IllegalArgumentException.class, () -> {
            exercise2.commands.Commands.tail(filename, numberOfLines);
        });
    }

    @Test
    void testTailWithMoreLinesThanFile() throws IOException {
        String expected = "one\n" +
                "two\n" +
                "three\n";

        String filename = "src/exercise2/test/TestFile2";
        int numberOfLines = 10;
        StringBuilder result = exercise2.commands.Commands.tail(filename, numberOfLines);
        assertEquals(expected, result.toString());
    }
}