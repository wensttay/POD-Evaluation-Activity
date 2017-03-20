package br.edu.ifpb.ads.questao_08.entities;

import br.edu.ifpb.ads.questao_08.strategy.Strategy;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 16:05:45
 */
public class Attendant {

    private int attendedCount = 0;
    private int attending = 0;
    private final Queue queue;

    public Attendant(Queue queue) {
        this.queue = queue;
    }

    public boolean startService(XGeneratior xGeneratior, Strategy strategy) {
        int peoplesPerSeccound = strategy.gerate(xGeneratior);
        boolean entryOne = false;
        for (int i = 0; i < peoplesPerSeccound; i++) {
            People pop = this.queue.pop();
            if (pop != null) {
                entryOne = true;
                attending++;
            } else {
                break;
            }
        }
        return entryOne;
    }

    public void stopService() {
        if (attending != 0) {
            attendedCount += attending;
            attending = 0;
        }
    }

    public int getAttendedCount() {
        return attendedCount;
    }
}
