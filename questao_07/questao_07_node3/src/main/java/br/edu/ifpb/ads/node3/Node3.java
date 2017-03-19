package br.edu.ifpb.ads.node3;

import br.edu.ifpb.ads.node.questao_01_shared.Configs;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 10/03/2017, 23:13:14
 */
public class Node3 {

    public static void main(String[] args) throws IOException {
        System.out.println("Starting Node3 ...");
        ServerSocket serverSocket = new ServerSocket(Configs.NODE_3_PORT);
        
        while (true) {
            Socket socket = serverSocket.accept();
            Node3Service node3Service = new Node3Service(socket);
            node3Service.run();
        }
        
    }
}
