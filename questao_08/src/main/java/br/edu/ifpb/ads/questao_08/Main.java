package br.edu.ifpb.ads.questao_08;

import br.edu.ifpb.ads.questao_08.entities.Attendant;
import br.edu.ifpb.ads.questao_08.entities.Engine;
import br.edu.ifpb.ads.questao_08.entities.XGeneratior;
import br.edu.ifpb.ads.questao_08.entities.Queue;
import br.edu.ifpb.ads.questao_08.entities.Chronometer;
import br.edu.ifpb.ads.questao_08.entities.IncomingManager;
import br.edu.ifpb.ads.questao_08.entities.QueueEngine;
import br.edu.ifpb.ads.questao_08.strategy.F_Strategy;
import br.edu.ifpb.ads.questao_08.strategy.G_Strategy;
import br.edu.ifpb.ads.questao_08.strategy.H_Strategy;
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
        
        XGeneratior xGeneratior = new XGeneratior();
        IncomingManager incomingManager = new IncomingManager();
        Chronometer chronometer = new Chronometer(600);
        
        Queue queue0 = new Queue("Q0", new F_Strategy(), 100);
        Queue queue1 = new Queue("Q1", new G_Strategy(), 50);
        
        Attendant attendant0 = new Attendant("A0", new H_Strategy());
        Attendant attendant1 = new Attendant("A1", new H_Strategy());
        Attendant attendant2 = new Attendant("A2", new H_Strategy());
        
        QueueEngine queueEngine0 = new QueueEngine(queue0, attendant0, attendant1);
        QueueEngine queueEngine1 = new QueueEngine(queue1, attendant2);
                
        Engine engine = new Engine(chronometer, xGeneratior, incomingManager,
                queueEngine0, queueEngine1);
        
        engine.exec();
        try {
            engine.printResult();
        } catch (InterruptedException ex) {
            System.out.println("[ERROR] Error trying print results.}");
        }
    }
}
