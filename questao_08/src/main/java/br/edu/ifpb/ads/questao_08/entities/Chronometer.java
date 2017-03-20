package br.edu.ifpb.ads.questao_08.entities;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 16:26:14
 */
public class Chronometer{

    private boolean isFinished = false;
    private long spendTime = 0;
    private final Object locker = new Object();
    private final Object finlizedLocker = new Object();
    private final int timeLimit;

    public Chronometer(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void exec() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[CHRONOMETER] Starting chronometer ...");
                long startTime = System.currentTimeMillis();
                long endTime = System.currentTimeMillis();
                long time0 = System.currentTimeMillis();
                long time1 = System.currentTimeMillis();
                int secoundsPass = 0;

                while (true) {
                    time1 = System.currentTimeMillis();

                    if (time1 - time0 >= 1000) {
                        System.out.printf("[CHRONOMETER] Passed %d seccounds\n", secoundsPass);
                        secoundsPass++;
                        time0 = time1;
                        synchronized (locker) {
                            locker.notifyAll();
                        }
                    }

                    if (secoundsPass == (timeLimit - 1) && isFinished == false) {
                        isFinished = true;
                    }

                    if (secoundsPass == timeLimit) {
                        isFinished = true;
                        System.out.printf("[CHRONOMETER] Time Over!\n");
                        break;
                    }
                }

                endTime = System.currentTimeMillis();
                spendTime = endTime - startTime;
                
                System.out.printf("[CHRONOMETER] Full Passed time: %d\n", secoundsPass);
                synchronized (finlizedLocker) {
                    finlizedLocker.notifyAll();
                }
            }
        }).start();
    }

    public boolean isIsFinished() {
        return isFinished;
    }

    public long getSpendTime() {
        return spendTime;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public Object getLocker() {
        return locker;
    }

    public Object getFinlizedLocker() {
        return finlizedLocker;
    }

    public void waitASecond() {
        synchronized (locker) {
            try {
                locker.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
