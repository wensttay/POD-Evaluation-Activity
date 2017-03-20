package br.edu.ifpb.ads.questao_08.entities;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 15:25:54
 */
public class IncomingManager {
    
    private int peopleId = 0;
    private int entryCount = 0;
    private int entryFails = 0;
    private final Queue[] queue;

    public IncomingManager(Queue... queue) {
        this.queue = queue;
    }

    public People createNewPeople() {
        return new People(++peopleId);
    }

    public void exec(XGeneratior xGeneratior) {
        for (Queue q : queue) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int n = q.getStrategy().gerate(xGeneratior);
                    for (int i = 0; i < n; i++) {
                        People people = createNewPeople();
                        if (!q.push(people)) {
                            ++entryFails;
                        }else{
                            ++entryCount;
                        }
                    }
                }
            }).start();
        }
    }

    public int getEntryCount() {
        return entryCount;
    }

    public int getEntryFails() {
        return entryFails;
    }

}
