package exercise2.test;

import exercise2.commands.Commands;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TreeTests {

    @TempDir
    Path tempDir;

    @Test
    void testTreeSizeWithInvalidDirectoryPath() {
        assertThrows(IllegalArgumentException.class, () -> Commands.treeSize("src/exercise2/test/does-not-exist"));
    }

    @Test
    void testTreeSizeWithFilePathInsteadOfDirectory() throws IOException {
        Path file = Files.createFile(tempDir.resolve("single-file.txt"));

        assertThrows(IllegalArgumentException.class, () -> Commands.treeSize(file.toString()));
    }

    @Test
    void testTreeSizeWithEmptyDirectory() {
        String expected = tempDir.getFileName() + " - 0 bytes\n";

        StringBuilder result = Commands.treeSize(tempDir.toString());

        assertEquals(expected, result.toString());
    }

    @Test
    void testTreeSizeWithNestedDirectoryAndFile() throws IOException {
        Path nestedDirectory = Files.createDirectory(tempDir.resolve("nested"));
        Path nestedFile = Files.createFile(nestedDirectory.resolve("payload.bin"));
        Files.write(nestedFile, new byte[]{1, 2, 3, 4, 5});

        String expected = tempDir.getFileName() + " - 5 bytes\n"
                + "\tnested - 5 bytes\n"
                + "\t\tpayload.bin - 5 bytes \n";

        StringBuilder result = Commands.treeSize(tempDir.toString());

        assertEquals(expected, result.toString());
    }
}