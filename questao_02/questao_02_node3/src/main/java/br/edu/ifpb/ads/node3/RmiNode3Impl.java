package br.edu.ifpb.ads.node3;

import br.edu.ifpb.ads.questao_02_shared.Configs;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import br.edu.ifpb.ads.questao_02_shared.RmiNodeContract;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 10/03/2017, 23:56:15
 */
public class RmiNode3Impl extends UnicastRemoteObject implements RmiNodeContract {

    public RmiNode3Impl() throws RemoteException {
    }

    @Override
    public int sum(int x, int y) throws RemoteException {
        try {
            System.out.println("Conectando ao Node1 ...");
            Registry registry = LocateRegistry.getRegistry(Configs.REMOTEHOST_IP, Configs.NODE_1_PORT);
            RmiNodeContract contract = (RmiNodeContract) registry.lookup(Configs.NODE_1_NAME);
            
            System.out.println("Redirecionando o processamento de diferença para o Node1 ...");
            return contract.sum(x, y);
        } catch (NotBoundException | AccessException ex) {
            System.out.println("Não foi possivel encontrar Node1");
        }

        try {
            System.out.println("Conectando ao Node2 ...");
            Registry registry = LocateRegistry.getRegistry(Configs.REMOTEHOST_IP, Configs.NODE_2_PORT);
            RmiNodeContract contract = (RmiNodeContract) registry.lookup(Configs.NODE_2_NAME);
            
            System.out.println("Redirecionando o processamento de diferença para o Node2 ...");
            return contract.sum(x, y);
        } catch (NotBoundException | AccessException ex) {
            System.out.println("Não foi possivel encontrar Node2");
        }

        throw new RemoteException("Não foi possivel encontrar Node1 e Node2");
    }

    @Override
    public int diff(int x, int y) throws RemoteException {
        System.out.println("Obtendo a diferença dos números " + x + " e " + y + " ...");
        return x - y;
    }

}
