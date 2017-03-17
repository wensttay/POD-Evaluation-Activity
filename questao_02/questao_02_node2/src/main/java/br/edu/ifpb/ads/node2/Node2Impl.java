package br.edu.ifpb.ads.node2;


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
public class Node2Impl implements NodeContract{
    
    public Node2Impl(){
    }
    
    @Override
    public String sum(int x, int y){
        System.out.println("Processing the sum between the values " + x + " and " + y + " ...");
        return "" + (x + y);
    }

    @Override
    public String diff(int x, int y){
        try {
            System.out.println("Connecting to Node3 ...");
            Socket socket = new Socket(Configs.LOCALHOST_IP, Configs.NODE_3_PORT);
            String encodeMessage = SocketProcotol.encodeMessage(x, "diff", y);
            
            System.out.println("Repassing the message to Node3 ...");
            SocketUtils.sendMessage(socket, encodeMessage);
            
            System.out.println("Wait an answer ...");
            String reciveMessage = SocketUtils.reciveMessage(socket);
            
            socket.close();
            return reciveMessage;
        } catch (IOException ex) {
            Logger.getLogger(Node2Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "[ERROR]";
    }

}   
