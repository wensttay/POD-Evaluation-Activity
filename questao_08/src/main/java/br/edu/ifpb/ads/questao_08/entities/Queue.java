package br.edu.ifpb.ads.questao_08.entities;

import br.edu.ifpb.ads.questao_08.strategy.Strategy;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 15:23:36
 */
public class Queue {

    private final People[] peoples;
    private int occupiedSize = 0;
    private final Strategy strategy;

    public Queue(int capacity, Strategy strategy) {
        this.peoples = new People[capacity];
        this.strategy = strategy;
    }

    private void organize() {
//        if (peoples.length >= 1) {
            for (int i = 1; i < peoples.length; i++) {
                People p = peoples[i];
                peoples[i - 1] = p;
            }
            peoples[peoples.length - 1] = null;
//        }
    }

    public boolean push(People p) {
        synchronized (peoples) {
            if (isEmpty()) {
                peoples[occupiedSize] = p;
                occupiedSize++;
                return true;
            } else if (!isFull()) {
                peoples[occupiedSize - 1] = p;
                occupiedSize++;
                return true;
            } else {
                return false;
            }
        }
    }

    public People pop() {
        synchronized (peoples) {
            if (!isEmpty()) {
                People p = peoples[0];
                peoples[0] = null;
                organize();
                occupiedSize--;
                return p;
            } else {
                return null;
            }
        }
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
