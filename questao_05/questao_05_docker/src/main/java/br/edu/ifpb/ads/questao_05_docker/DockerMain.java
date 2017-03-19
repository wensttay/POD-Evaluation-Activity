package br.edu.ifpb.ads.questao_05_docker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 20:01:41
 */
public class DockerMain {

    public static void main(String[] args) {

        File sum_file = new File("/opt/app/shared/sum.txt");
        if (sum_file.exists()) {
            System.out.println("Can write: " + sum_file.canWrite());
            System.out.println("Can read: " + sum_file.canRead());
            System.out.println("Cleaning sum file ... ");
            cleanfile(sum_file.toPath());
            printFile(sum_file.toPath());
        } else {
            System.out.println("Sum file not found");
        }

        //check diff.txt
        File diff_file = new File("/opt/app/shared/diff.txt");
        if (diff_file.exists()) {
            System.out.println("Can write: " + diff_file.canWrite());
            System.out.println("Can read: " + diff_file.canRead());
            System.out.println("Cleaning diff file ... ");
            cleanfile(diff_file.toPath());
            printFile(diff_file.toPath());
        } else {
            System.out.println("Diff file not found");
        }
    }

    private static void cleanfile(Path pathToWrite){
        try {
            Files.write(pathToWrite, new ArrayList<String>());
        } catch (IOException ex) {
            System.out.println("[ERROR] Cannot clear this file");
        }
    }

    private static void printFile(Path pathToWrite){
        try {
            List<String> readAllLines = Files.readAllLines(pathToWrite);
            for (String readAllLine : readAllLines) {
                System.out.println(readAllLine);
            }
        } catch (IOException ex) {
            Logger.getLogger(DockerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Deprecated
//    public static void oldMain(String[] args) throws IOException {
//
//        System.out.println("Iniciando Node Docker ...");
//        ServerSocket serverSocket = new ServerSocket(Configs.NODE_DOCKER_PORT);
//
//        System.out.println("Aguardando Node0 (cliente) ...");
//        Socket clientNode = serverSocket.accept();
//
//        System.out.println("Processando requisição ...");
//        String reciveMessage = SocketUtils.reciveMessage(clientNode);
//        String[] decodeMessage = SocketProcotol.decodeMessage(reciveMessage);
//
//        System.out.println("Selecionando Node para solucionar o problema ...");
//        Path pathToSave = null;
//        Socket nodeDestiny = null;
//
//        if (decodeMessage.length == 3) {
//            if (decodeMessage[1].equals("sum")) {
//                nodeDestiny = new Socket(Configs.REMOTEHOST_IP, Configs.NODE_1_PORT);
//                pathToSave = Paths.get("/opt/app/shared/sum.txt");
//
//            } else if (decodeMessage[1].equals("diff")) {
//
//                nodeDestiny = new Socket(Configs.LOCALHOST_IP, Configs.NODE_2_PORT);
//                pathToSave = (Path) Paths.get("/opt/app/shared/diff.txt");
//            }
//        }
//
//        if (nodeDestiny == null) {
//            return;
//        }
//
//        System.out.println("Enviando requisição para o Node ...");
//        SocketUtils.sendMessage(nodeDestiny, reciveMessage);
//
//        System.out.println("Aguardando resposta do Node ...");
//        String msg = SocketUtils.reciveMessage(nodeDestiny);
//        System.out.println("Resposta recebida: " + msg);
//
//        // SALVAR RESPOSTA NOS ARQUIVOS
//        System.out.println("Salvando resultado em arquivo compartilhado ...");
//        List<String> lines = Arrays.asList(msg);
//
//        Files.write(pathToSave, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
//
//        System.out.println("Retornando resposta ao Cliente ...");
//        SocketUtils.sendMessage(clientNode, msg);
//
//    }
}
