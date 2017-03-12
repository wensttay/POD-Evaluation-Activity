package br.edu.ifpb.ads.node2;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 10/03/2017, 23:14:50
 */
public class Node2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting Node2 ...");
        ServerSocket serverSocket = new ServerSocket(10992);
        String remoteIP = "localhost";
        int remotePort = 10993;
        Node2Service node2Service = new Node2Service(serverSocket, remoteIP, remotePort);
        node2Service.run();
    }
}
