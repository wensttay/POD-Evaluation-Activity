package br.edu.ifpb.ads.node1;

import br.edu.ifpb.ads.node.questao_01_shared.SocketProcotol;
import br.edu.ifpb.ads.node.questao_01_shared.SocketUtils;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 10/03/2017, 19:05:58
 */
public class Node1Service extends Thread{

    private final Socket socket;

    public Node1Service(Socket socket) {
        this.socket = socket;
    }

    private String createMessage() {
        int value1 = new Random().nextInt(100) + 1;
        int value2 = new Random().nextInt(100) + 1;
        String msg = SocketProcotol.encodeMessage(value1, value2);
        return msg;
    }

    @Override
    public void run(){
        try {
            System.out.println("Creating a message ...");
            String createMessage = createMessage();
            System.out.println("Created Message: " + createMessage);
            
            System.out.println("Sending the message ...");
            SocketUtils.sendMessage(socket, createMessage);
            
            System.out.println("Wait an answer ...");
            String reciveAnswer = SocketUtils.reciveMessage(socket);
            System.out.println("Answer: " + reciveAnswer);
            
            System.out.println("Closing connection ...");
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Node1Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
