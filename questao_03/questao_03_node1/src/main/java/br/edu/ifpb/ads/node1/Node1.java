package br.edu.ifpb.ads.node1;

import br.edu.ifpb.ads.questao_03_shared.Configs;
import br.edu.ifpb.ads.questao_03_shared.SocketProcotol;
import br.edu.ifpb.ads.questao_03_shared.SocketUtils;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 03:34:28
 */
public class Node1 {
    
    public static void main(String[] args) throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite o primeiro valor: ");
        int x = scanner.nextInt();
        
        System.out.println("Digite a operação desejada: ");
        String op = scanner.next();
        
        System.out.println("Digite o segundo valor: ");
        int y = scanner.nextInt();
        
        System.out.println("Formulando requisição ...");
        Socket node = null;
        if (op.equals("sum")) {
            node = new Socket(Configs.REMOTEHOST_IP, Configs.NODE_3_PORT);
        } else if (op.equals("diff")) {
            node = new Socket(Configs.REMOTEHOST_IP, Configs.NODE_2_PORT);
        }else{
            System.out.println("Não foi encontrado um servidor para essa operação");
        }
        
        if (node == null) {
            return;
        }
        String encodeMessage = SocketProcotol.encodeMessage(x, op, y);
        
        System.out.println("Enviando requisição ...");
        SocketUtils.sendMessage(node, encodeMessage);
        
        System.out.println("Aguardando resultado ...");
        String reciveMessage = SocketUtils.reciveMessage(node);
        
        System.out.println("Resultado: " + reciveMessage);
        
    }
}
