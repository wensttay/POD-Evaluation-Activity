package br.edu.ifpb.ads.questao_05_node2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 20:08:00
 */
public class Node2 {

    public static void main(String[] args) throws IOException, InterruptedException{
        System.out.println("Startando Node2 ...");
        Path pathToWrite = Paths.get("/opt/app/shared/diff.txt");
        boolean notDone = true;

        while (notDone) {
            System.out.println("Reding the file ...");
            List<String> readAllLines = Files.readAllLines(pathToWrite);

            int lineWithAnswer = 3;
            int diff = 0;

            System.out.println("Cheking is has some operation diff on file ...");
            String outPutOperation = "Operation detected: ";
            boolean hasOperation = false;

            for (int i = 0; i < readAllLines.size(); i++) {
                if (((i + 1) % lineWithAnswer) == 0) {

                    if (readAllLines.get(i).equals("") || readAllLines.get(i).equals("\n")) {
                        readAllLines.set(i, "" + diff);

                        outPutOperation += "= " + diff;
                        System.out.println(outPutOperation);
                        hasOperation = true;
                    }

                    outPutOperation = "";
                    diff = 0;

                } else {
                    if (!(readAllLines.get(i).equals("") || readAllLines.get(i).equals("\n"))) {
                        outPutOperation += readAllLines.get(i) + " - ";
                        if (diff == 0) {
                            diff += Integer.parseInt(readAllLines.get(i));
                        } else {
                            diff -= Integer.parseInt(readAllLines.get(i));
                        }
                    }
                }

            }

            if (hasOperation) {
                Files.write(pathToWrite, readAllLines);
                System.out.println("");
                System.out.println("File with operation answer: ");
                System.out.println(">>> START <<<");
                printFile(pathToWrite);
                System.out.println(">>> END <<<");
                notDone = false;
            }
            Thread.sleep(3000);
        }
    }

    private static void printFile(Path pathToWrite) throws IOException {
        List<String> readAllLines = Files.readAllLines(pathToWrite);
        for (String readAllLine : readAllLines) {
            System.out.println(readAllLine);
        }
    }
}
