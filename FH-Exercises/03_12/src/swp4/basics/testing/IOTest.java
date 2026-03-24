package swp4.basics.testing;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class IOTest {
    // Create temporary file path for input/output operations
    static final String FOLDER_PATH = "./tmp/";
    static String inputFile = FOLDER_PATH + "sample.txt";
    static String fileOutputStreamOutput = FOLDER_PATH + "FileOutputStream.txt";
    static String fileWriterOutput = FOLDER_PATH + "FileWriter.txt";


    // === 1 JAVA.IO.FILEINPUTSTREAM ===
    private static void readFileContentFileInputStreamV1(String fileName) throws IOException {
        /*
        Used for binary files and streams (for example images or audio files)
         */

        FileInputStream fin = new FileInputStream(fileName);
        int i = fin.read();
        while (i != -1) {
            System.out.println((char) i);
            i = fin.read();
        }
        fin.close();
    }

    // === 2 JAVA.IO.BUFFEREDREADER ===
    private static void readFileContentBufferedReaderV2(String fileName) throws IOException {
        /*
        Character reading with buffering for character-wise or line-wise reading. Useful for large Files. Performance!
        InputStreamReader: bytes to character (simple use); BufferedReader: adds buffering (performance & line-wise)
         */

        String str;
        BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        while ((str = bReader.readLine()) != null) {
            System.out.println(str);
        }
        bReader.close();
    }

    // === 3 JAVA.IO.FILE.FILEREADER ===
    private static void readFileContentFileReaderV3(String fileName) throws IOException {
        /*
        Convenience function for file reading. Provides decoding and buffering.
        Often combined with BufferedReader for line-wise reading.
         */
        String str;
        BufferedReader bReader = new BufferedReader(new FileReader(fileName));
        while ((str = bReader.readLine()) != null) {
            System.out.println(str);
        }
        bReader.close();
    }

    // === 4 WRITE JAVA.UTIL.SCANNER ===
    private static void readFileContentScannerV4(String fileName) throws IOException {
        /*
        High level for advanced parsing options like reading streams and strings. Easy & flexible but less efficient.
         */
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }

    // === 1 WRITE JAVA.IO.FILEOUTPUTSTREAM ===
    private static void writeFileOutputStreamV1(String fileName) throws IOException {
        /*
        Used for binary files and streams (for example images or audio files)
         */
        FileOutputStream f_out = new FileOutputStream(fileName);
        String toWrite = "Hello from SWP4 using FileOutputStream.\nTest";
        f_out.write(toWrite.getBytes());
        f_out.close();
    }

    // === 2 WRITE JAVA.IO.FILEWRITER ===
    private static void writeFileWriterV2(String fileName) throws IOException {
        /*
        Used for writing text to files, allows handling of encodings and functions like newline, no conversion needed
         */
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        String toWrite = "Hello from SWP4 using FileWriter.\nTest";
        writer.write(toWrite);
        writer.close();
    }

    // === 1 READ CONSOLE INPUT JAVA.IO.INPUTSTREAMREADER ===
    private static String readConsoleInputStreamReader() throws IOException {
        String res = null;

        var x = new BufferedReader(new InputStreamReader(System.in));
        res = x.readLine();

        System.out.println("Result from the first stream" + res);



        Scanner sc = new Scanner(System.in);
        res = sc.nextLine();
        sc.close();
        Integer.parseInt(res);

        return res;
    }

    // === TESTS ===
    public static void main(String[] args) throws IOException {

        // Setup Test-Folder and File (with nio)
        Files.createDirectories(Paths.get(FOLDER_PATH));
        Files.write(Paths.get(inputFile), "1\n2\n3\nTestFile".getBytes());

        System.out.println("READ Version 1: using FileInputStream");
        System.out.println("-----------------------------------");
        readFileContentFileInputStreamV1(inputFile);
        System.out.println();
        System.out.println("-----------------------------------");

        System.out.println("READ Version 2: using BufferedReader");
        System.out.println("-----------------------------------");
        readFileContentBufferedReaderV2(inputFile);
        System.out.println();
        System.out.println("-----------------------------------");

        System.out.println("READ Version 3: using FileReader");
        System.out.println("-----------------------------------");
        readFileContentFileReaderV3(inputFile);
        System.out.println();
        System.out.println("-----------------------------------");

        System.out.println("READ Version 4: using Scanner");
        System.out.println("-----------------------------------");
        readFileContentScannerV4(inputFile);
        System.out.println();
        System.out.println("-----------------------------------");

        System.out.println("WRITE Version 1: using FileOutputStream");
        System.out.println("-----------------------------------");
        writeFileOutputStreamV1(fileOutputStreamOutput);
        System.out.println();
        System.out.println("-----------------------------------");

        System.out.println("WRITE Version 2: using FileWriter");
        System.out.println("-----------------------------------");
        writeFileWriterV2(fileWriterOutput);
        System.out.println();
        System.out.println("-----------------------------------");

        System.out.println("READ Input: using Console Input");
        System.out.println("-----------------------------------");
        String input = readConsoleInputStreamReader();
        System.out.println(input);
    }

}