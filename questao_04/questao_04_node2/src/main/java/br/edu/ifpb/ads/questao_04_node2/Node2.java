package br.edu.ifpb.ads.questao_04_node2;

import br.edu.ifpb.ads.questao_04_node2.dao.PostgresDao;
import br.edu.ifpb.ads.questao_04_node2.dao.MySQLDao;
import br.edu.ifpb.ads.questao_04_shared.Configs;
import br.edu.ifpb.ads.questao_04_shared.SocketProcotol;
import br.edu.ifpb.ads.questao_04_shared.SocketUtils;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 17:33:15
 */
public class Node2 {
    public static void main(String[] args) throws IOException {
        
        System.out.println("Iniciando Node2 ...");
        ServerSocket serverSocket = new ServerSocket(Configs.NODE_2_PORT);
        
        System.out.println("Aguardando requisição ...");
        Socket socket = serverSocket.accept();
        
        System.out.println("Processando requisição ...");
        String reciveMessage = SocketUtils.reciveMessage(socket);
        String[] decodeMessage = SocketProcotol.decodeMessage(reciveMessage);
        String login = decodeMessage[0];
        String password = decodeMessage[1];
        User user = new User(login, password);
        
        System.out.println("Salvando usuario no bando Postgres ...");
        PostgresDao postgresDao = new PostgresDao();
        boolean pgdaoSaved = postgresDao.persist(user);
        
        System.out.println("Salvando usuario no bando MySql ...");
        MySQLDao mySQLDao = new MySQLDao();
        boolean mysqlSaved = mySQLDao.persist(user);
        
        System.out.println("Retornando resultado ...");
        SocketUtils.sendMessage(socket, pgdaoSaved && mysqlSaved ? "SUCCESS" :  "FAIL");
    }
    
}
