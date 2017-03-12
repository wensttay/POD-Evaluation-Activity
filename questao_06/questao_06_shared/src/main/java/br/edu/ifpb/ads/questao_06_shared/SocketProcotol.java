package br.edu.ifpb.ads.questao_06_shared;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 10/03/2017, 22:58:38
 */
public class SocketProcotol {

    private static final String TOKEN = "---123---";

    public static String encodeMessage(String User) {
        return TOKEN + "[" + User + "]" + TOKEN;
    }

    public static String decodeMessage(String msg) {

        String m = msg.replaceAll(TOKEN, "");
        m = m.replace("[", "");
        m = m.replace("]", "");
        return m;
    }
}
