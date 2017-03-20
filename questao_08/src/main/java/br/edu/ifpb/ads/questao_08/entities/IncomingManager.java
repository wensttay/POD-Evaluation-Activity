package br.edu.ifpb.ads.questao_08.entities;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 15:25:54
 */
public class IncomingManager {

    private int entryCode = 0;

    public People createNewPeople() {
        return new People("People" + (++entryCode));
    }

    public void exec(XGeneratior xGeneratior, QueueEngine...queueEngines) {
        
        for (QueueEngine queueEngine : queueEngines) {
            new Thread(new Runnable() {
                @Override
                public void run() {            
                    int n = queueEngine.getQueue()
                            .getStrategy()
                            .gerate(xGeneratior);
                    for (int i = 0; i < n; i++) {
                        People people = createNewPeople();
                        queueEngine.getQueue().push(people);
                    }
                }
            }).start();
        }
    }

    public int getEntryCode() {
        return entryCode;
    }

}
