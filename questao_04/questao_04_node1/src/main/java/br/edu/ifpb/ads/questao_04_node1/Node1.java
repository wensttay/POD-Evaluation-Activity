package br.edu.ifpb.ads.questao_04_node1;

import br.edu.ifpb.ads.questao_04_shared.Configs;
import br.edu.ifpb.ads.questao_04_shared.SocketProcotol;
import br.edu.ifpb.ads.questao_04_shared.SocketUtils;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 17:04:30
 */
public class Node1 {

    public static void main(String[] args) throws IOException {
        System.out.println("Iniciando Node1 ...");

        System.out.println("Obtendo coneção com Node2 ...");
        Socket socket = new Socket(Configs.REMOTEHOST_IP, Configs.NODE_2_PORT);
        
        System.out.println("Enviando um usuario com login e senha para ser cadastrado ...");
        String encodeMessage = SocketProcotol.encodeMessage("Wensttay", "Password");
        SocketUtils.sendMessage(socket, encodeMessage);

        System.out.println("Esperando confirmação de Node2 ...");
        String reciveMessage = SocketUtils.reciveMessage(socket);
        System.out.println("Answer: " + reciveMessage);
        
        System.out.println("Encerrando Node1 ...");
        socket.close();
    }
}
