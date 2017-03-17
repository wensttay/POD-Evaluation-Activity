package br.edu.ifpb.ads.client;

import br.edu.ifpb.ads.questao_02_shared.Configs;
import br.edu.ifpb.ads.questao_02_shared.SocketProcotol;
import br.edu.ifpb.ads.questao_02_shared.SocketUtils;
import java.io.IOException;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.edu.ifpb.ads.questao_02_shared.RmiNodeContract;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 00:49:26
 */
public class Main {

    public static void main(String[] args) throws IOException {
        
        System.out.println("Starting Client ...");
        // To change the Node to connect, just change this method, like this:
        // To change for Node2 change "connectToNode1();" to "connectToNode2();"
//        Socket socketNode = connectToNode1();
//        Socket socketNode = connectToNode2();
        Socket socketNode = connectToNode3();

        System.out.println("Encoding a request message ...");
        String encodeMessage = SocketProcotol.encodeMessage(10, "sum", 40);
//        String encodeMessage = SocketProcotol.encodeMessage(50, "diff", 5);
        System.out.println("EncodeMessage: " + encodeMessage);

        System.out.println("Sending the request message to selected Node ...");
        SocketUtils.sendMessage(socketNode, encodeMessage);
//        SocketUtils.sendMessage(socketNode, encodeMessage);

        System.out.println("Waiting the answer of request message ...");
        String reciveMessage = SocketUtils.reciveMessage(socketNode);
        System.out.println("Answer: " + reciveMessage);
    }

    private static Socket connectToNode3() throws IOException {
        System.out.println("Obtaining a connection with Node3 ...");
        Socket socketNode = new Socket(Configs.REMOTEHOST_IP, Configs.NODE_3_PORT);
        return socketNode;
    }

    private static Socket connectToNode2() throws IOException {
        System.out.println("Obtaining a connection with Node2 ...");
        Socket socketNode = new Socket(Configs.REMOTEHOST_IP, Configs.NODE_2_PORT);
        return socketNode;
    }

    private static Socket connectToNode1() throws IOException {
        System.out.println("Obtaining a connection with Node1 ...");
        Socket socketNode = new Socket(Configs.REMOTEHOST_IP, Configs.NODE_1_PORT);
        return socketNode;
    }

    @Deprecated
    public static void rmiMain(String[] args) {
        try {
            System.out.println("Inciando Client ...");

            System.out.println("Obtendo Conecção com Node1 ...");
            Registry r1 = LocateRegistry.getRegistry(Configs.REMOTEHOST_IP, Configs.NODE_1_PORT);
            RmiNodeContract node1 = (RmiNodeContract) r1.lookup(Configs.NODE_1_NAME);

            System.out.println("Obtendo Conecção com Node2 ...");
            Registry r2 = LocateRegistry.getRegistry(Configs.REMOTEHOST_IP, Configs.NODE_2_PORT);
            RmiNodeContract node2 = (RmiNodeContract) r2.lookup(Configs.NODE_2_NAME);

            System.out.println("Obtendo Conecção com Node3 ...");
            Registry r3 = LocateRegistry.getRegistry(Configs.REMOTEHOST_IP, Configs.NODE_3_PORT);
            RmiNodeContract node3 = (RmiNodeContract) r3.lookup(Configs.NODE_3_NAME);

            System.out.println("Efetuando Testes ...");
            System.out.println("Test de Soma(1,9) >> Node1: " + node1.sum(1, 9));
            System.out.println("Test de Soma(1,10) >> Node2: " + node2.sum(1, 10));
            System.out.println("Test de Subtracao(10,5) >> Node3 : " + node3.diff(10, 5));

            System.out.println("Test de Subtracao(20,5) >> Node1: " + node1.diff(20, 5));
            System.out.println("Test de Subtracao(30,5) >> Node2: " + node2.diff(30, 5));
            System.out.println("Test de Soma(40,5) >> Node3: " + node3.sum(40, 5));

        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
