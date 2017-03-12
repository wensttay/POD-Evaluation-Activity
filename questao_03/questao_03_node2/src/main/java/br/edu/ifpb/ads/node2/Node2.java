package br.edu.ifpb.ads.node2;

import br.edu.ifpb.ads.shared.Configs;
import br.edu.ifpb.ads.shared.SocketProcotol;
import br.edu.ifpb.ads.shared.SocketUtils;
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
        
        System.out.println("Iniciando Node2 ...");
        ServerSocket serverSocket = new ServerSocket(Configs.NODE_2_PORT);
        
        System.out.println("Aguardando requisição ...");
        Socket clientNode = serverSocket.accept();
        String reciveMessage = SocketUtils.reciveMessage(clientNode);
        String[] decodeMessage = SocketProcotol.decodeMessage(reciveMessage);
        
        System.out.println("Formulando requisição ...");
        Socket nodeDestiny = null;
        if (decodeMessage.length == 3) {
            if (decodeMessage[1].equals("sum")) {
                nodeDestiny = new Socket(Configs.LOCALHOST_IP, Configs.NODE_4_PORT);
            } else if (decodeMessage[1].equals("diff")) {
                nodeDestiny = new Socket(Configs.LOCALHOST_IP, Configs.NODE_3_PORT);
            }
        }
        
        System.out.println("Dados recebidos:");
        for (String string : decodeMessage) {
            System.out.println(string);
        }
        
        if(nodeDestiny == null){
            return;
        }
        
        System.out.println("Redirecionando requisição ...");
        SocketUtils.sendMessage(nodeDestiny, reciveMessage);
        
        System.out.println("Aguarando resposta ...");
        String msg = SocketUtils.reciveMessage(nodeDestiny);
        
        System.out.println("Retornando resposta redirecionada ao requisitor ...");
        SocketUtils.sendMessage(clientNode, msg);
    }
}
