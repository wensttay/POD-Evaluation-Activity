package br.edu.ifpb.ads.questao_08.entities;

import br.edu.ifpb.ads.questao_08.strategy.Strategy;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 16:11:58
 */
public class Engine {

    private final Chronometer chronometer;
    private final XGeneratior xGeneratior;
    private final QueueEngine[] queueEngines;
    private final IncomingManager incomingManager;

    public Engine(Chronometer chronometer, XGeneratior xGeneratior, IncomingManager incomingManager, QueueEngine... queueEngines) {
        this.chronometer = chronometer;
        this.xGeneratior = xGeneratior;
        this.incomingManager = incomingManager;
        this.queueEngines = queueEngines;
    }

    public void exec() {
        System.out.println("[ENGINE] Starting Engine ...");
        chronometer.exec();
        xGeneratior.exec(chronometer);

        entryControll();

        for (QueueEngine queueEngine : queueEngines) {
            queueEngine.exec(xGeneratior, chronometer);
        }
    }

    private void entryControll() {
        System.out.println("[ENTRYCONTROLL} Starting entryControll ...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (!chronometer.isIsFinished()) {
                    chronometer.waitASecond();
                    incomingManager.exec(xGeneratior, queueEngines);
                }
            }
        };

        Thread t1 = new Thread(runnable);
        t1.start();
    }

    public XGeneratior getxGeneratior() {
        return xGeneratior;
    }

    public Chronometer getChronometer() {
        return chronometer;
    }

    public QueueEngine[] getQueueEngines() {
        return queueEngines;
    }

    public void printResult() throws InterruptedException {
        
        synchronized (chronometer.getFinlizedLocker()) {
            chronometer.getFinlizedLocker().wait();
        }
        System.out.println("");
        System.out.println("------------------------------RESULTS------------------------------");
        System.out.println("");
        System.out.println("A) Quantas pessoas entraram na fila?");
        int totalEntryCount = 0;
        for (QueueEngine queueEngine : queueEngines) {
            Queue queue = queueEngine.getQueue();
            totalEntryCount += queue.getEntryCount();
            System.out.printf("> %d pessoas entraram na fila %s.\n",
                    queue.getEntryCount(),
                    queue.getId());
        }
        System.out.printf("Totalizando: %d pessoas.\n", totalEntryCount);
        System.out.println("");
        
        System.out.println("B) Quantas pessoas foram embora antes de entrar na fila (ocorre quando a fila está cheia)?");
        int totalNotEntryCount = 0;
        for (QueueEngine queueEngine : queueEngines) {
            Queue queue = queueEngine.getQueue();
            totalNotEntryCount += queue.getEntryFail();
            System.out.printf("> %d pessoas não entram na fila %s.\n",
                    queue.getEntryFail(),
                    queue.getId());
        }
        System.out.printf("Totalizando: %d pessoas.\n", totalNotEntryCount);
        System.out.println("");

        System.out.println("C) Quantas pessoas foram atendidas?");
        int totalAttended = 0;
        for (QueueEngine queueEngine : queueEngines) {
            Queue queue = queueEngine.getQueue();
            Attendant[] attendants = queueEngine.getAttendants();

            for (Attendant attendant : attendants) {
                System.out.printf("> %d pessoas foram atendidas por %s na fila %s.\n",
                        attendant.getAttendedCount(),
                        attendant.getName(),
                        queue.getId());
                totalAttended += attendant.getAttendedCount();
            }
        }
        System.out.printf("Totalizando: %d pessoas.\n", totalAttended);
        System.out.println("");
        
        System.out.println("D) Quantas pessoas ficaram na fila?");
        int totalInerQueue = 0;
        for (QueueEngine queueEngine : queueEngines) {
            Queue queue = queueEngine.getQueue();
            totalInerQueue += queue.getOccupiedSize();
            System.out.printf("> %d pessoas continuaram na fila %s.\n",
                    queue.getOccupiedSize(),
                    queue.getId());
        }
        System.out.printf("Totalizando: %d pessoas.\n", totalInerQueue);
        System.out.println("");
        
        System.out.println("------------------------------EXTRA-INFO------------------------------");
        int totalEmAtendimento = 0;
        for (QueueEngine queueEngine : queueEngines) {
            Queue queue = queueEngine.getQueue();
            Attendant[] attendants = queueEngine.getAttendants();

            for (Attendant attendant : attendants) {
                System.out.printf("> %d pessoas estavam sendo atendidas por %s na fila %s.\n",
                        attendant.getAttending(),
                        attendant.getName(),
                        queue.getId());
                totalEmAtendimento += attendant.getAttending();
            }
        }
        System.out.printf("Totalizando: %d pessoas.\n", totalEmAtendimento);
        System.out.println("");
    }
}
