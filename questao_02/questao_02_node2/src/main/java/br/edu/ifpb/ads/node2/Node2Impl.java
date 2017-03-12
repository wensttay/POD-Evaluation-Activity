package br.edu.ifpb.ads.node2;

import br.edu.ifpb.ads.shared.Configs;
import br.edu.ifpb.ads.shared.NodeContract;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 10/03/2017, 23:56:15
 */
public class Node2Impl extends UnicastRemoteObject implements NodeContract {

    public Node2Impl() throws RemoteException {
    }

    @Override
    public int sum(int x, int y) throws RemoteException {
        System.out.println("Obtendo a soma dos números " + x + " e " + y + " ...");
        return x + y;
    }

    @Override
    public int diff(int x, int y) throws RemoteException {
        try {
            System.out.println("Conectando ao Node3 ...");
            Registry registry = LocateRegistry.getRegistry(Configs.REMOTEHOST_IP, Configs.NODE_3_PORT);
            NodeContract contract = (NodeContract) registry.lookup(Configs.NODE_3_NAME);

            System.out.println("Redirecionando o processamento de diferença para o Node3 ...");
            return contract.diff(x, y);
        } catch (NotBoundException | AccessException ex) {
            throw new RemoteException("Não foi possivel encontrar Node3");
        }
    }
}
