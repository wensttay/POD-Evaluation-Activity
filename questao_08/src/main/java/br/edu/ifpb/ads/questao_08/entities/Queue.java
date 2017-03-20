package br.edu.ifpb.ads.questao_08.entities;

import br.edu.ifpb.ads.questao_08.strategy.Strategy;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 15:23:36
 */
public class Queue {

    private final String id;
    private final Strategy strategy;
    private final People[] peoples;
    private int occupiedSize = 0;
    private int entryCount = 0;
    private int entryFail = 0;

    public Queue(String id, Strategy strategy, int size) {
        this.id = id;
        this.peoples = new People[size];
        this.strategy = strategy;
    }

    public int getEntryFail() {
        return entryFail;
    }

    private int organizei = 0;

    private synchronized void organize() {
        for (int i = 1; i < peoples.length; i++) {
            People p = peoples[i];
            peoples[i - 1] = p;
        }
        peoples[peoples.length - 1] = null;
    }

    public boolean push(People p) {
        synchronized (peoples) {
            if (isEmpty()) {
                peoples[occupiedSize] = p;
                occupiedSize++;
                entryCount++;
//                System.out.printf("%s Entrou na Fila %s (%d)\n", p.getName(), id, entryCount);
                return true;
            } else if (!isFull()) {
                peoples[occupiedSize - 1] = p;
                occupiedSize++;
                entryCount++;
//                System.out.printf("%s Entrou na Fila %s (%d)\n", p.getName(), id, entryCount);
                return true;
            } else {
                entryFail++;
//                System.out.printf("%s Não conseguiu entrar na Fila %s (%d)\n", p.getName(), id, entryFail);
                return false;
            }
        }
    }

    int count = 0;

    public People pop() {
        synchronized (peoples) {
            
            // A ordenação é mais lenta que a verificação ...
            // trocar as posições leva mais tempo que checar se está vazia
            // por isso é necessario verificar se existe uma pessoa
            // na posição 0
            if (!(peoples[0] == null)) {
                People p = peoples[0];
                organize();
                occupiedSize--;
                return p;
            } else {
//                System.out.printf("Não tem ninguem na fila %s\n", id);
                return null;
            }
        }
    }

    public String getId() {
        return id;
    }

    public People[] getPeoples() {
        return peoples;
    }

    public int getEntryCount() {
        return entryCount;
    }

    public int getOccupiedSize() {
        return occupiedSize;
    }

    public boolean isFull() {
        return occupiedSize == peoples.length;
    }

    public boolean isEmpty() {
        return occupiedSize == 0;
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
