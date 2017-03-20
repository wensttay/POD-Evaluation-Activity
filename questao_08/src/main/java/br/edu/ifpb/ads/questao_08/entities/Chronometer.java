package br.edu.ifpb.ads.questao_08.entities;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 16:26:14
 */
public class Chronometer {

    private boolean isFinished = false;
    private final int timeLimit;
    private long spendTime = 0;

    public Chronometer(int spendTime) {
        this.timeLimit = spendTime;
    }

    public void exec(Object locker, Object finalLocker) {
        System.out.println("Starting Chronometer ...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                long startTime = System.currentTimeMillis();
                long endTime = System.currentTimeMillis();
                long time0 = System.currentTimeMillis();
                long time1 = System.currentTimeMillis();
                int secoundsPass = 0;

                while (true) {
                    time1 = System.currentTimeMillis();

                    if (time1 - time0 >= 1000) {
                        System.out.printf("[CHRONOMETER] Passed %d secs!\n", secoundsPass);
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
                        System.out.println("[CHRONOMETER] Time is over!");
                        break;
                    }
                }
                
                endTime = System.currentTimeMillis();
                spendTime = endTime - startTime;
                
                System.out.println("[CHRONOMETER] Total duration time: " + (endTime - startTime));
                
                synchronized (finalLocker) {
                    finalLocker.notifyAll();
                }
            }
        };

        Thread t0 = new Thread(runnable);
        t0.start();
    }

    public boolean isIsFinished() {
        return isFinished;
    }

    public long getSpendTime() {
        return spendTime;
    }
}
