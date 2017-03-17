package br.edu.ifpb.ads.node3;

import br.edu.ifpb.ads.questao_02_shared.Configs;
import br.edu.ifpb.ads.questao_02_shared.NodeContract;
import br.edu.ifpb.ads.questao_02_shared.SocketProcotol;
import br.edu.ifpb.ads.questao_02_shared.SocketUtils;
import java.net.Socket;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 10/03/2017, 23:56:15
 */
public class Node3Impl implements NodeContract {

    public Node3Impl() {
    }

    @Override
    public String sum(int x, int y) {

        try {
            Socket socket = null;

            try {
                System.out.println("Trying to Connect to Node1 ...");
                socket = new Socket(Configs.LOCALHOST_IP, Configs.NODE_1_PORT);
            } catch (IOException ex) {
                System.out.println("[WARN] Cannot be connected to node1.");
            }

            if (socket == null) {
                try {
                    System.out.println("Trying to Connect to Node2 ...");
                    socket = new Socket(Configs.LOCALHOST_IP, Configs.NODE_2_PORT);
                } catch (IOException ex) {
                    System.out.println("[WARN] Cannot be connected to node2.");
                }
            }

            if (socket == null) {
                return "[ERROR] Connection declined";
            }

            String encodeMessage = SocketProcotol.encodeMessage(x, "sum", y);

            System.out.println("Repassing the message to seleced Node ...");
            SocketUtils.sendMessage(socket, encodeMessage);

            System.out.println("Wait an answer ...");
            String reciveMessage = SocketUtils.reciveMessage(socket);

            socket.close();

            return reciveMessage;
        } catch (IOException ex) {
            Logger.getLogger(Node3Impl.class.getName()).log(Level.SEVERE, null, ex);
            return "[ERROR] Connection defused";
        }
    }

    @Override
    public String diff(int x, int y) {
        System.out.println("Processing the diff between the values " + x + " and " + y + " ...");
        return "" + (x - y);
    }

}
