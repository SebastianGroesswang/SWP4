package exercise2.test;

import exercise2.commands.Commands;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocTest {

        @Test
        void testLocWithExistingFile() throws Exception {
            String filename = "src/exercise2/test/TestFile1";
            Commands.LocStatistics expected = new Commands.LocStatistics(26, 26, 26);
            Commands.LocStatistics result = Commands.loc(filename);

            assertEquals(expected, result);
        }

        @Test
        void testLocWithTestFile2() throws Exception {
            String filename = "src/exercise2/test/TestFile2";
            Commands.LocStatistics expected = new Commands.LocStatistics(3, 3, 11);
            Commands.LocStatistics result = Commands.loc(filename);

            assertEquals(expected, result);
        }

        @Test
        void testLocWithTestFile3() throws Exception {
            String filename = "src/exercise2/test/TestFile3";
            Commands.LocStatistics expected = new Commands.LocStatistics(6, 14, 49);
            Commands.LocStatistics result = Commands.loc(filename);

            assertEquals(expected, result);
        }

        @Test
        void testLocWithNonExistingFile() {
            String filename = "src/exercise2/test/NonExistingFile";

            assertThrows(Exception.class, () -> Commands.loc(filename));
        }

}