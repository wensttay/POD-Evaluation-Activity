package br.edu.ifpb.ads.questao_06_node2;

import br.edu.ifpb.ads.questao_06_shared.Configs;
import br.edu.ifpb.ads.questao_06_shared.SocketProcotol;
import br.edu.ifpb.ads.questao_06_shared.SocketUtils;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 12/03/2017, 05:50:12
 */
public class Node2 {

    public static Date lastchange = new Date();
    public static Map<String, Socket> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        System.out.println("Iniciando Node 2 ...");
        ServerSocket serverSocket = new ServerSocket(Configs.NODE_2_PORT);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("[VERIFICADOR] Verificando se existe novas Atualizações no arquivo ...");
                        boolean chackIfHaveUpdate = checkIfHaveUpdate();
                        if (chackIfHaveUpdate) {
                            System.out.println("[VERIFICADOR] Ouve Alterações no arquivo, vamos avistar todo mundo ...");
                            
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    alertAll();
                                }
                            }).start();

                        } else {
                            System.out.println("[VERIFICADOR] Não ouve alteração, vamos esperar 3 segundos e checar novamente ...");
                        }
                        Thread.sleep(5000);
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(Node2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("[CADASTRADOR] Aguarando inscrição de um novo Subscriber ...");
                        Socket socket = serverSocket.accept();

                        System.out.println("[CADASTRADOR] Recemento dados do novo subscriber ...");
                        String reciveMessage = SocketUtils.reciveMessage(socket);
                        String decodeMessage = SocketProcotol.decodeMessage(reciveMessage);

                        System.out.println("[CADASTRADOR] Incrição efetuada para o Usuario: " + decodeMessage);
                        map.put(decodeMessage, socket);
                    } catch (IOException ex) {
                        Logger.getLogger(Node2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    public static boolean checkIfHaveUpdate() throws IOException {

        File file = new File(System.getProperty("user.dir"));
        Path path = Paths.get(file.getParentFile() + "/disk.txt");

        if (Files.exists(path) && Files.isReadable(path)) {
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            FileTime time = attrs.lastModifiedTime();

            int compareTo = time.compareTo(FileTime.fromMillis(lastchange.getTime()));

            if (compareTo > 0) {
                lastchange = new Date(time.toMillis());
                return true;
            }
            return false;

        } else {
            return false;
        }
    }

    public static void alertAll() {
        Iterator it = map.entrySet().iterator();

        while (it.hasNext()) {
            try {
                Map.Entry pair = (Map.Entry) it.next();
                System.out.println("[ALERTADOR] Avisando: " + pair.getKey());

                Socket socket = (Socket) pair.getValue();
                SocketUtils.sendMessage(socket, "[ALERTADOR] Arquivo Modificado!");
//                it.remove(); // avoids a ConcurrentModificationException
            } catch (IOException ex) {
                Logger.getLogger(Node2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
