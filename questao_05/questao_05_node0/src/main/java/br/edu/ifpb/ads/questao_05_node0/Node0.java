package br.edu.ifpb.ads.questao_05_node0;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 19:45:14
 */
public class Node0 {

    public static void main(String[] args) throws IOException, InterruptedException {
        int x = 0, y = 0;
        String op = "";
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("Entry with the first value: ");
            x = scanner.nextInt();
            System.out.print("Entry with the operation ('sum' or 'diff'): ");
            op = scanner.next();
            scanner.nextLine();
            System.out.print("Entry with the secount value: ");
            y = scanner.nextInt();

            Path pathToWrite = null;

            System.out.println("Selecting the file to write the parameters ...");
            if (op.equals("sum")) {
                pathToWrite = Paths.get("/opt/app/shared/sum.txt");
            } else if (op.equals("diff")) {
                pathToWrite = Paths.get("/opt/app/shared/diff.txt");
            }


            if (pathToWrite == null) {
                System.out.println("Invalid Operation, try the operation 'sum' or 'diff'.");
            } else if (Files.exists(pathToWrite)) {
                System.out.println("Writing the parameters on file ...");
                boolean writed = false;

                while (!writed) {
                    writed = writeTheOperation(pathToWrite, x, y);
                    Thread.sleep(3000);
                }
                System.out.println("");
                System.out.println("File before answer: ");
                printFile(pathToWrite);
                
                System.out.println("Reading the answer on file ...");
                String result = readingTheOperationAnswer(pathToWrite);
                System.out.println("Result = " + result);
                System.out.println("");
                System.out.println("File after answer: ");
                printFile(pathToWrite);

            } else {
                System.out.println("[ERROR] Cannot find the file for this operation.");
            }
            
            System.out.println("\n\n");
        }

    }

    private static String readingTheOperationAnswer(Path pathToWrite) throws IOException {

        while (checkLastLineIsClean(pathToWrite)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Node0.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String result = getLastLineValue(pathToWrite);
        return result;
    }

    private static boolean writeTheOperation(Path pathToWrite, int x, int y) throws IOException {

        System.out.println("Checking if this file can be Written now ...");
        while (!pathToWrite.toFile().canWrite()) {
            System.out.println("Cannot to be Written now, trying again ...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Node0.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("File can be Written now ...");
        System.out.println("Check if not has some pending answer ...");
        if (checkFileIsClean(pathToWrite)
                || !checkFileIsWithoutAnswer(pathToWrite)) {
            String text = "" + x + "\n" + y + "\n\n";
            System.out.println("Writing values ...");
            Files.write(pathToWrite, text.getBytes(), StandardOpenOption.APPEND);
            return true;
        } else {
            System.out.println("Have some pending answer! Cannot write now");
            return false;
        }
    }

    public static boolean checkFileIsClean(Path pathToWrite) throws IOException {
        List<String> readAllLines = Files.readAllLines(pathToWrite);
        return readAllLines.isEmpty();
    }

    public static boolean checkFileIsWithoutAnswer(Path pathToWrite) {
        try {
            List<String> readAllLines = Files.readAllLines(pathToWrite);
            int lineWithAnswer = 3;

            for (int i = 0; i < readAllLines.size(); i++) {
                if ((i + 1) % lineWithAnswer == 0) {
                    String line = readAllLines.get(i);
                    if (line.equals("") || line.equals("\n")) {
                        return true;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Node0.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    private static boolean checkLastLineIsClean(Path pathToWrite) throws IOException {
        String line = getLastLineValue(pathToWrite);
        if (line.equals("") || line.equals("\n")) {
            System.out.println("Not Aswer eat yet ...");
            return true;
        }
        return false;
    }

    private static String getLastLineValue(Path pathToWrite) throws IOException {
        List<String> readAllLines = Files.readAllLines(pathToWrite);

        if (readAllLines.size() > 0) {
            return readAllLines.get(readAllLines.size() - 1);
        } else {
            return readAllLines.get(0);
        }
    }

    private static void printFile(Path pathToWrite) throws IOException {
        List<String> readAllLines = Files.readAllLines(pathToWrite);
        for (String readAllLine : readAllLines) {
            System.out.println(readAllLine);
        }
    }

    private static void cleanfile(Path pathToWrite) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(pathToWrite.toFile());
        writer.print("");
        writer.close();
    }
}
