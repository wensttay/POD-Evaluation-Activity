package br.edu.ifpb.ads.questao_08.entities;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 20/03/2017, 09:19:05
 */
public class QueueEngine {
    
    private final Queue queue;
    private final Attendant[] attendants;
    
    public QueueEngine(Queue queue, Attendant... attendants) {
        this.queue = queue;
        this.attendants = attendants;
    }

    public Queue getQueue() {
        return queue;
    }


    public Attendant[] getAttendants() {
        return attendants;
    }

    public void exec(XGeneratior xGeneratior, Chronometer chronometer) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[QUEUEENGINE_"+ queue.getId()+"] Starting QueueEngine ...");
                while (!chronometer.isIsFinished()) {
                    chronometer.waitASecond();

                    for (Attendant att : attendants) {
                        att.startService(queue, xGeneratior);
                    }

                    if (!chronometer.isIsFinished()) {
                        chronometer.waitASecond();
                        for (Attendant att : attendants) {
                            if (att.isAttending()) {
                                att.stopService();
                            }
                        }
                    }
                }
            }
        }).start();
    }
}
