package br.edu.ifpb.ads.node3;

import br.edu.ifpb.ads.questao_02_shared.Configs;
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
        try {
            System.out.println("Inciando Node3 ...");
            System.setProperty("java.rmi.server.hostname", Configs.LOCALHOST_IP);
            Registry registry = LocateRegistry.createRegistry(Configs.NODE_3_PORT);
            
            System.out.println("Disponibilizando Nod3 ...");
            registry.bind(Configs.NODE_3_NAME, new Node3Impl());
            
        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(Node3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
