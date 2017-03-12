package br.edu.ifpb.ads.questao_05_node0;

import br.edu.ifpb.ads.questao_05_shared.Configs;
import br.edu.ifpb.ads.questao_05_shared.SocketProcotol;
import br.edu.ifpb.ads.questao_05_shared.SocketUtils;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 19:45:14
 */
public class Node0 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o primeiro valor: ");
        int x = scanner.nextInt();

        System.out.println("Digite a operação desejada: ");
        String op = scanner.next();

        System.out.println("Digite o segundo valor: ");
        int y = scanner.nextInt();

        Socket node = new Socket(Configs.REMOTEHOST_IP, Configs.NODE_DOCKER_PORT);
        
        if (node == null) {
            return;
        }
        
        System.out.println("Enviando Requisicao ao NodeDocker ...");
        String encodeMessage = SocketProcotol.encodeMessage(x, op, y);
        SocketUtils.sendMessage(node, encodeMessage);
        String reciveMessage = SocketUtils.reciveMessage(node);
        System.out.println("Resposta Recebida: " + reciveMessage);

    }
}
