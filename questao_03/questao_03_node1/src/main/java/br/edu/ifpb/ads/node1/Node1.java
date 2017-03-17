package br.edu.ifpb.ads.node1;

import br.edu.ifpb.ads.questao_03_shared.Configs;
import br.edu.ifpb.ads.questao_03_shared.SocketProcotol;
import br.edu.ifpb.ads.questao_03_shared.SocketUtils;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 03:34:28
 */
public class Node1 {

    public static void main(String[] args) throws IOException {

        System.out.println("Starting Node1 ...");

        int x = 100, y = 40;
//        String op = "sum";
        String op = "diff";

        System.out.println("Selecting a Node to the operation ...");
        Socket node = null;

        if (op.equals("sum")) {
            System.out.println("Nodo3 selected");
            node = new Socket(Configs.REMOTEHOST_IP, Configs.NODE_3_PORT);
        } else if (op.equals("diff")) {
            System.out.println("Nodo2 selected");
            node = new Socket(Configs.REMOTEHOST_IP, Configs.NODE_2_PORT);
        }

        if (node != null) {
            System.out.println("Encoding Message ...");
            String encodeMessage = SocketProcotol.encodeMessage(x, op, y);

            System.out.println("Sending the request message to selected Node ...");
            SocketUtils.sendMessage(node, encodeMessage);

            System.out.println("Waiting the answer of request message ...");
            String reciveMessage = SocketUtils.reciveMessage(node);

            System.out.println("Answer: " + reciveMessage);
        } else {
            System.out.println("There are not any Node to this operation ...");
        }
    }
}
