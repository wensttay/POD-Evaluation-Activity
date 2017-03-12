package br.edu.ifpb.ads.questao_04_shared;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 10/03/2017, 22:58:38
 */
public class SocketProcotol {

    private static final String TOKEN = "---123---";

    public static String encodeMessage(String login, String password) {
        return TOKEN + "[" + login + "," + password + "]" + TOKEN;
    }

    public static String[] decodeMessage(String msg) {

        String m = msg.replaceAll(TOKEN, "");
        m = m.replace("[", "");
        m = m.replace("]", "");
        String[] split = m.split(",");

        return split;
    }
}
