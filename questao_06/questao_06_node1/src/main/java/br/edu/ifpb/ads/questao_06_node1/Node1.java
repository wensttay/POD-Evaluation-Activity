package br.edu.ifpb.ads.questao_06_node1;

import br.edu.ifpb.ads.questao_06_shared.Configs;
import br.edu.ifpb.ads.questao_06_shared.SocketProcotol;
import br.edu.ifpb.ads.questao_06_shared.SocketUtils;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 12/03/2017, 05:15:35
 */
public class Node1 {

    public static void main(String[] args) throws IOException {
        System.out.println("Iniciando Node1 ...");

        System.out.println("Se inscrevendo como ouvinte ...");
        Socket socket = new Socket(Configs.REMOTEHOST_IP, Configs.NODE_2_PORT);
        String encodeMessage = SocketProcotol.encodeMessage("Pedro");
        SocketUtils.sendMessage(socket, encodeMessage);
        System.out.println("Incrição efetuada, aguartando alterações ...");
        
        while (true) {    
            String reciveMessage = SocketUtils.reciveMessage(socket);
            System.out.println(reciveMessage);
        }
    }
}
