package br.edu.ifpb.ads.node.questao_01_shared;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 10/03/2017, 22:58:38
 */
public class SocketProcotol {

    private static final String TOKEN = "---123---";

    public static String encodeMessage(int n1, int n2) {
        return TOKEN + "[" + n1 + "," + n2 + "]" + TOKEN;
    }

    public static List<Integer> decodeMessage(String msg) {

        String m = msg.replaceAll(TOKEN, "");
        m = m.replace("[", "");
        m = m.replace("]", "");
        String[] split = m.split(",");

        List<Integer> values = new ArrayList<>();
        for (String number : split) {
            values.add(Integer.valueOf(number));
        }

        return values;
    }
}
