package br.edu.ifpb.ads.questao_08.entities;

import br.edu.ifpb.ads.questao_08.strategy.Strategy;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 16:05:45
 */
public class Attendant {

    private final Strategy attendantStrategy;
    private final String name;
    private int attendedCount = 0;
    private int attending = 0;

    public Attendant(String name, Strategy attendantStrategy) {
        this.name = name;
        this.attendantStrategy = attendantStrategy;
    }

    public void startService(Queue queue, XGeneratior xGeneratior) {
        int peoplesPerSeccound = attendantStrategy.gerate(xGeneratior);

        for (int i = 0; i < peoplesPerSeccound; i++) {
            People pop = queue.pop();
            if (pop != null) {
                attending++;
            }
        }
    }

    public void stopService() {
        if (isAttending()) {
            attendedCount += attending;
            attending = 0;
        }
    }

    public boolean isAttending() {
        return attending > 0 ? true : false;
    }

    public String getName() {
        return name;
    }

    public int getAttendedCount() {
        return attendedCount;
    }

    public int getAttending() {
        return attending;
    }

    public Strategy getAttendantStrategy() {
        return attendantStrategy;
    }

}
