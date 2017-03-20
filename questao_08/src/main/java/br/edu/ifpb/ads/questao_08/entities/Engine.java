package br.edu.ifpb.ads.questao_08.entities;

import br.edu.ifpb.ads.questao_08.strategy.Strategy;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 16:11:58
 */
public class Engine {

    private final Queue queue;
    private final IncomingManager incomingManager;
    private final Attendant[] attendants;

    public Engine(Queue queue, IncomingManager incomingManager, Attendant... attendant) {
        this.queue = queue;
        this.incomingManager = incomingManager;
        this.attendants = attendant;
    }

    private void waitASecond(Object locker) {
        synchronized (locker) {
            try {
                locker.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void entryControll(XGeneratior xGeneratior, Chronometer chronometer, Object locker) {
        System.out.println("[ENTRYCONTROLL] Starting a EntryControll ...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (!chronometer.isIsFinished()) {
                    waitASecond(locker);
                    System.out.println("[ENTRYCONTROLL] Trying to entry with more peoples in Queue ...");
                    incomingManager.exec(xGeneratior);
                }
            }
        };
        //
        Thread t1 = new Thread(runnable);
        t1.start();
    }

    private void attendanceControll(XGeneratior xGeneratior, Chronometer chronometer, Strategy strategy, Object locker) {
        System.out.println("[ATTENDANCECONTROLL] Starting a AttendenceControll ...");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {

                while (!chronometer.isIsFinished()) {
                    System.out.println("[ATTENDANCECONTROLL] Cheking if the Attendants are disponible now ...");
                    waitASecond(locker);
                    int atteToTry = attendants.length;
                    
                    for (Attendant att : attendants) {
                        // Atende de forma sequencia :/
                        if (!att.startService(xGeneratior, strategy)) {
                            --atteToTry;
                        }
                    }

                    if (atteToTry == 0) {
                        System.out.println("[ATTENDANCECONTROLL] The Attendants are occupade now ...");
                        continue;
                    }

                    if (!chronometer.isIsFinished()) {
                        waitASecond(locker);
                        for (Attendant att : attendants) {
                            att.stopService();
                        }
                    }
                }
            }
        };
        //
        Thread t2 = new Thread(r2);
        t2.start();
    }

    public void exec(XGeneratior xGeneratior, Chronometer chronometer, Strategy H_Strategy, Object locker) {
        System.out.println("[ENGENE] Estarting a Queue Engine ...");
        entryControll(xGeneratior, chronometer, locker);
        attendanceControll(xGeneratior, chronometer, H_Strategy, locker);
    }

    public Queue getQueue() {
        return queue;
    }

    public IncomingManager getIncomingManager() {
        return incomingManager;
    }

    public Attendant[] getAttendants() {
        return attendants;
    }
}
