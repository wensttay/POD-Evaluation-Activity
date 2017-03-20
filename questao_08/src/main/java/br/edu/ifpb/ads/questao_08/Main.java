package br.edu.ifpb.ads.questao_08;

import br.edu.ifpb.ads.questao_08.entities.Attendant;
import br.edu.ifpb.ads.questao_08.entities.Engine;
import br.edu.ifpb.ads.questao_08.entities.XGeneratior;
import br.edu.ifpb.ads.questao_08.entities.Queue;
import br.edu.ifpb.ads.questao_08.entities.Chronometer;
import br.edu.ifpb.ads.questao_08.entities.IncomingManager;
import br.edu.ifpb.ads.questao_08.strategy.H_Strategy;
import br.edu.ifpb.ads.questao_08.strategy.F_Strategy;
import br.edu.ifpb.ads.questao_08.strategy.G_Strategy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 18:35:35
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Creating objects ...");
        Object finalLocker = new Object();
        XGeneratior xGeneratior = new XGeneratior();
        Chronometer chronometer = new Chronometer(600);

        Object locker = new Object();

        H_Strategy h_Strategy = new H_Strategy();
        F_Strategy f_Strategy = new F_Strategy();
        G_Strategy g_Strategy = new G_Strategy();

        Queue Q0 = new Queue(100, f_Strategy);
        Queue Q1 = new Queue(50, g_Strategy);

        Attendant attendant_Q0_01 = new Attendant(Q0);
        Attendant attendant_Q0_02 = new Attendant(Q0);
        Attendant attendant_Q1_01 = new Attendant(Q1);

        IncomingManager incomingManager_Q0 = new IncomingManager(Q0);
        IncomingManager incomingManager_Q1 = new IncomingManager(Q1);

        Engine engine_Q0 = new Engine(Q0, incomingManager_Q0, attendant_Q0_01, attendant_Q0_02);
        Engine engine_Q1 = new Engine(Q1, incomingManager_Q1, attendant_Q1_01);

        xGeneratior.exec(chronometer, locker);
        chronometer.exec(locker, finalLocker);
        engine_Q0.exec(xGeneratior, chronometer, h_Strategy, locker);
        engine_Q1.exec(xGeneratior, chronometer, h_Strategy, locker);

        synchronized(finalLocker){
            try {
                finalLocker.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("-------------- RESULTS --------------");
        printResults(chronometer,
                incomingManager_Q0,
                incomingManager_Q1,
                attendant_Q0_01,
                attendant_Q0_02,
                attendant_Q1_01,
                Q0,
                Q1);
    }

    private static void printResults(Chronometer chronometer, IncomingManager incomingManager_Q0, IncomingManager incomingManager_Q1, Attendant attendant_Q0_01, Attendant attendant_Q0_02, Attendant attendant_Q1_01, Queue Q0, Queue Q1) {

        int entryCount_Q0 = incomingManager_Q0.getEntryCount();
        int entryCount_Q1 = incomingManager_Q1.getEntryCount();
        System.out.printf("a) Em Q0 entraram: %d e em Q1: %d. Totalizando: %d\n",
                entryCount_Q0,
                entryCount_Q1,
                entryCount_Q0 + entryCount_Q1);

        int entryFails_Q0 = incomingManager_Q0.getEntryFails();
        int entryFails_Q1 = incomingManager_Q1.getEntryFails();
        System.out.printf("b) Em Q0 foram embora: %d e em Q1: %d. Totalizando: %d\n",
                entryFails_Q0,
                entryFails_Q1,
                entryFails_Q0 + entryFails_Q1);

        int attendedCount_Q0_01 = attendant_Q0_01.getAttendedCount();
        int attendedCount_Q0_02 = attendant_Q0_02.getAttendedCount();
        int attendedCount_Q1_01 = attendant_Q1_01.getAttendedCount();
        System.out.printf("c) Em Q0 foram atendidos: %d e em Q1: %d. Totalizando: %d\n",
                attendedCount_Q0_01 + attendedCount_Q0_02,
                attendedCount_Q1_01,
                attendedCount_Q0_01 + attendedCount_Q0_02 + attendedCount_Q1_01);

        int occupiedSize_Q0 = Q0.getOccupiedSize();
        int occupiedSize_Q1 = Q1.getOccupiedSize();
        System.out.printf("d) Em Q0 ficaram na fila: %d e em Q1: %d. Totalizando: %d\n",
                occupiedSize_Q0,
                occupiedSize_Q1,
                occupiedSize_Q0 + occupiedSize_Q1);
    }
}
