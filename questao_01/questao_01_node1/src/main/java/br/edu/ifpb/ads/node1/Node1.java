package br.edu.ifpb.ads.node1;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 10/03/2017, 21:14:19
 */
public class Node1 {

    public static void main(String[] args) throws IOException {
        System.out.println("Starting Node1 ...");
        Socket socket = new Socket("localhost", 10992);
        Node1Service ns = new Node1Service(socket);
        ns.run();
    }
}
