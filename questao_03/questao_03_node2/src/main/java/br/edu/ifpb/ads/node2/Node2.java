package br.edu.ifpb.ads.node2;

import br.edu.ifpb.ads.questao_03_shared.Configs;
import br.edu.ifpb.ads.questao_03_shared.SocketProcotol;
import br.edu.ifpb.ads.questao_03_shared.SocketUtils;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 03:45:51
 */
public class Node2 {

    public static void main(String[] args) throws IOException {

        System.out.println("Starting Node2 ...");
        ServerSocket serverSocket = new ServerSocket(Configs.NODE_2_PORT);

        while (true) {
            System.out.println("Waiting a client ...");
            Socket clientNode = serverSocket.accept();
            String reciveMessage = SocketUtils.reciveMessage(clientNode);
            String[] decodeMessage = SocketProcotol.decodeMessage(reciveMessage);

            System.out.println("Selecting a Node to the operation ...");
            Socket nodeDestiny = null;
            if (decodeMessage.length == 3) {
                if (decodeMessage[1].equals("sum")) {
                    System.out.println("Nodo4 selected");
                    nodeDestiny = new Socket(Configs.LOCALHOST_IP, Configs.NODE_4_PORT);
                } else if (decodeMessage[1].equals("diff")) {
                    System.out.println("Nodo3 selected");
                    nodeDestiny = new Socket(Configs.LOCALHOST_IP, Configs.NODE_3_PORT);
                }
            }

            if (nodeDestiny != null) {
                System.out.println("Sending the request message to selected Node ...");
                SocketUtils.sendMessage(nodeDestiny, reciveMessage);

                System.out.println("Waiting the answer of request message ...");
                String msg = SocketUtils.reciveMessage(nodeDestiny);

                System.out.println("Returning the redirected answer to the client ...");
                SocketUtils.sendMessage(clientNode, msg);

            } else {
                System.out.println("This pperation has not registred");
            }
            
            clientNode.close();
        }
    }
}
