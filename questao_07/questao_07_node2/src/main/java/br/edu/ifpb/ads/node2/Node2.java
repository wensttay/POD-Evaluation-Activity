package br.edu.ifpb.ads.node2;

import br.edu.ifpb.ads.node.questao_01_shared.Configs;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 10/03/2017, 23:14:50
 */
public class Node2 {

    public static void main(String[] args) throws IOException {
        System.out.println("Starting Node2 ...");
        ServerSocket serverSocket = new ServerSocket(Configs.NODE_2_PORT);

        while (true) {
            Socket socket = serverSocket.accept();
            Node2Service node2Service = new Node2Service(socket, Configs.LOCALHOST_IP, Configs.NODE_3_PORT);
            node2Service.run();
        }
    }
}
