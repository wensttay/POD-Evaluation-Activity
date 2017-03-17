package br.edu.ifpb.ads.node3;

import br.edu.ifpb.ads.questao_02_shared.Configs;
import br.edu.ifpb.ads.questao_02_shared.SocketProcotol;
import br.edu.ifpb.ads.questao_02_shared.SocketUtils;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 00:00:26
 */
public class Node3 {

    public static void main(String[] args) {

        Node3Impl node3Impl = new Node3Impl();
        try {
            System.out.println("Starting Node3 ...");
            ServerSocket serverSocket = new ServerSocket(Configs.NODE_3_PORT);
            while (true) {
                System.out.println("Waiting a client ...");
                Socket client = serverSocket.accept();

                System.out.println("Processing messege ...");
                String reciveMessage = SocketUtils.reciveMessage(client);
                String[] decodeMessage = SocketProcotol.decodeMessage(reciveMessage);

                int x = Integer.parseInt(decodeMessage[0]);
                String op = decodeMessage[1];
                int y = Integer.parseInt(decodeMessage[2]);

                String answer = "";

                if (op.equals("sum")) {
                    answer = node3Impl.sum(x, y);
                } else if (op.equals("diff")) {
                    answer = node3Impl.diff(x, y);
                } else {
                    System.out.println("This pperation has not registred");
                    answer = "[ERROR] This Operation has not registred";
                }

                System.out.println("Return the answer to Client ...");
                SocketUtils.sendMessage(client, answer);
                client.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Node3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Deprecated
    public static void rmiMain(String[] args) {
        try {
            System.out.println("Inciando Node3 ...");
            System.setProperty("java.rmi.server.hostname", Configs.LOCALHOST_IP);
            Registry registry = LocateRegistry.createRegistry(Configs.NODE_3_PORT);

            System.out.println("Disponibilizando Nod3 ...");
            registry.bind(Configs.NODE_3_NAME, new RmiNode3Impl());

        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(Node3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
