package br.edu.ifpb.ads.questao_05_node2;

import br.edu.ifpb.ads.questao_05_shared.Configs;
import br.edu.ifpb.ads.questao_05_shared.SocketProcotol;
import br.edu.ifpb.ads.questao_05_shared.SocketUtils;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 20:11:29
 */
public class Node2 {

    public static void main(String[] args) throws IOException {
        System.out.println("Inciando Node2 ...");
        ServerSocket serverSocket = new ServerSocket(Configs.NODE_2_PORT);

        System.out.println("Aguandando Requsição ...");
        Socket clientNode = serverSocket.accept();

        System.out.println("Processando Requisição ...");
        String reciveMessage = SocketUtils.reciveMessage(clientNode);
        String[] decodeMessage = SocketProcotol.decodeMessage(reciveMessage);
        int result = 0;
        if (decodeMessage.length == 3) {
            int x = Integer.parseInt(decodeMessage[0]);
            int y = Integer.parseInt(decodeMessage[2]);

            result = x - y;
        }
        System.out.println("Resultado: " + result);
        System.out.println("Retornando resultado ...");
        SocketUtils.sendMessage(clientNode, "" + result);
    }
}
