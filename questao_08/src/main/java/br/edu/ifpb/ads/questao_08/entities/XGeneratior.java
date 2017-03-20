package br.edu.ifpb.ads.questao_08.entities;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 17:51:57
 */
public class XGeneratior {

    private double XValue = 0.0;

    public XGeneratior() {
    }

    public void exec(Chronometer chronometer, Object locker) {
        System.out.println("Starting XGeneratior ...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (!chronometer.isIsFinished()) {
                    synchronized (locker) {
                        try {
                            locker.wait();
                        } catch (InterruptedException ex) {
                            System.out.println("[ERROR] Error on XGeneratior");
                        }
                        XValue = Math.random();
                    }
                }
            }
        };
        Thread xThread = new Thread(runnable);
        xThread.start();
    }

    public double getXValue() {
        return XValue;
    }
}
