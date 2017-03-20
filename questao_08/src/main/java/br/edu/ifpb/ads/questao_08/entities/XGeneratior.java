package br.edu.ifpb.ads.questao_08.entities;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 17:51:57
 */
public class XGeneratior {

    private double XValue = 0.0;

    public XGeneratior() {
    }

    public void exec(Chronometer chronometer) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[XGENERATIOR] Starting xGeneratior ...");
                while (!chronometer.isIsFinished()) {
                    synchronized (chronometer.getLocker()) {
                        chronometer.waitASecond();
                        XValue = Math.random();
                    }
                }
            }
        }).start();
    }

    public double getXValue() {
        return XValue;
    }
}
