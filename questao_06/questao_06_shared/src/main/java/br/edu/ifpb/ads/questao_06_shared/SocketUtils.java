package br.edu.ifpb.ads.questao_06_shared;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 10/03/2017, 22:04:51
 */
public class SocketUtils {

    public static void sendMessage(Socket socket, String msg) throws IOException {
        socket.getOutputStream().write(msg.getBytes());
    }

    public static String reciveMessage(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        byte[] s = new byte[1024];
        inputStream.read(s);
        return new String(s, "UTF-8").trim();
    }
}
