package br.edu.ifpb.ads.questao_05_node1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 20:08:00
 */
public class Node1 {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Startando Node1 ...");

        Path pathToWrite = Paths.get("/opt/app/shared/sum.txt");
        boolean notDone = true;
        
        while (notDone) {
            System.out.println("Reding the file ...");
            List<String> readAllLines = Files.readAllLines(pathToWrite);

            int lineWithAnswer = 3;
            int sum = 0;

            System.out.println("Cheking is has some operation sum on file ...");
            String outPutOperation = "Operation detected: ";
            boolean hasOperation = false;

            for (int i = 0; i < readAllLines.size(); i++) {
                if (((i + 1) % lineWithAnswer) == 0) {

                    if (readAllLines.get(i).equals("") || readAllLines.get(i).equals("\n")) {
                        readAllLines.set(i, "" + sum);
                        
                        outPutOperation += "= " + sum;
                        System.out.println(outPutOperation);
                        hasOperation = true;
                    }
                    
                    outPutOperation = "";
                    sum = 0;
                
                } else {
                    if (!(readAllLines.get(i).equals("") || readAllLines.get(i).equals("\n"))) {
                        outPutOperation += readAllLines.get(i) + " + ";
                        sum += Integer.parseInt(readAllLines.get(i));
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
