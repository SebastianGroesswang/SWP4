package exercise2;

import exercise2.commands.Commands;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static void main(String[] args) {

        if (args.length > 0) {
            switch (args[0]) {
                case "head":
                    if (args.length != 3)
                        System.out.println("Usage: head <file> <number_of_lines>");
                    else {
                        try {
                            System.out.println(Commands.head(args[1], Integer.parseInt(args[2])));
                        } catch (FileNotFoundException e) {
                            System.out.println("File not found: " + args[1]);
                        }
                    }
                    return;
                case "tail":
                    if (args.length != 3)
                        System.out.println("Usage: tail <file> <number_of_lines>");
                    else {
                        try {
                            System.out.println(Commands.tail(args[1], Integer.parseInt(args[2])));
                        } catch (FileNotFoundException e) {
                            System.out.println("File not found: " + args[1]);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    return;
                case "treeSize":
                    if (args.length != 2)
                        System.out.println("Usage: treeSize <directory>");
                    else {
                        try {
                            System.out.println(Commands.treeSize(args[1]));
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    return;
                case "loc":
                    if (args.length != 2)
                        System.out.println("Usage: loc <file>");
                    else {
                        try {
                            System.out.println(Commands.loc(args[1]));
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    return;
                default:
                    System.out.println("Ungueltige Auswahl. Bitte 'head', 'tail', 'treeSize' oder 'loc' eingeben.");
            }
        } else {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                printMenu();
                String choice = scanner.nextLine().trim();

                try {
                    switch (choice) {
                        case "1":
                            runHead(scanner);
                            break;
                        case "2":
                            runTail(scanner);
                            break;
                        case "3":
                            runTreeSize(scanner);
                            break;
                        case "4":
                            runLoc(scanner);
                            break;
                        case "0":
                            System.out.println("Programm beendet.");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Ungueltige Auswahl. Bitte 0 bis 4 eingeben.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Bitte eine gueltige ganze Zahl eingeben.");
                } catch (Exception e) {
                    System.out.println("Fehler: " + e.getMessage());
                }

                System.out.println();
            }
        }


    }

    private static void printMenu() {
        System.out.println("=== Datei-Utilities ===");
        System.out.println("1) head");
        System.out.println("2) tail");
        System.out.println("3) treeSize");
        System.out.println("4) loc");
        System.out.println("0) beenden");
        System.out.print("Auswahl: ");
    }

    private static void runHead(Scanner scanner) throws Exception {
        System.out.print("Dateipfad: ");
        String path = scanner.nextLine().trim();

        System.out.print("Anzahl Zeilen (leer = ganze Datei): ");
        String linesInput = scanner.nextLine().trim();
        int lines = linesInput.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(linesInput);

        System.out.println("\n--- Ausgabe (head) ---");
        System.out.print(Commands.head(path, lines));
    }

    private static void runTail(Scanner scanner) throws Exception {
        System.out.print("Dateipfad: ");
        String path = scanner.nextLine().trim();

        System.out.print("Anzahl Zeilen: ");
        int lines = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("\n--- Ausgabe (tail) ---");
        System.out.print(Commands.tail(path, lines));
    }

    private static void runTreeSize(Scanner scanner) {
        System.out.print("Verzeichnispfad: ");
        String path = scanner.nextLine().trim();

        System.out.println("\n--- Ausgabe (treeSize) ---");
        System.out.print(Commands.treeSize(path));
    }

    private static void runLoc(Scanner scanner) throws Exception {
        System.out.print("Dateipfad: ");
        String path = scanner.nextLine().trim();

        Commands.LocStatistics stats = Commands.loc(path);
        System.out.println("\n--- Ausgabe (loc) ---");
        System.out.println(stats);
    }
}
