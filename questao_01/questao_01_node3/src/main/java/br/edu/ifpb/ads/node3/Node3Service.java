package br.edu.ifpb.ads.node3;

import br.edu.ifpb.ads.node.questao_01_shared.SocketProcotol;
import br.edu.ifpb.ads.node.questao_01_shared.SocketUtils;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 10/03/2017, 21:32:44
 */
public class Node3Service {

    private final ServerSocket serverSocket;

    public Node3Service(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void run() throws IOException {
        System.out.println("Waiting a connection ...");
        Socket node2 = serverSocket.accept();
        System.out.println("Connection accepted ...");

        System.out.println("Reading the message ...");
        String reciveMessage = SocketUtils.reciveMessage(node2);
        System.out.println("Recived message: " + reciveMessage);

        System.out.println("Processing the message ...");
        List<Integer> integers = SocketProcotol.decodeMessage(reciveMessage);

        Integer x = integers.get(0);
        Integer y = integers.get(1);

        System.out.println("Computing the Answer ...");
        BigDecimal xToY = new BigDecimal(x).pow(y);
        BigDecimal yToX = new BigDecimal(y).pow(x);

        BigDecimal result = xToY.add(yToX);
        String answer = "" + result.toString();
        System.out.println("Answer: " + answer);

        System.out.println("Return the answer to Node2 ...");
        SocketUtils.sendMessage(node2, answer);
    }

}
