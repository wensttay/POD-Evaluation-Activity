package br.edu.ifpb.ads.node2;

import br.edu.ifpb.ads.shared.Configs;
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
public class Node2 {

    public static void main(String[] args) {
        try {
            System.out.println("Inciando Node2 ...");
            System.setProperty("java.rmi.server.hostname", Configs.LOCALHOST_IP);
            Registry registry = LocateRegistry.createRegistry(Configs.NODE_2_PORT);
            
            System.out.println("Disponibilizando Nod2 ...");
            registry.bind(Configs.NODE_2_NAME, new Node2Impl());
            
        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(Node2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
