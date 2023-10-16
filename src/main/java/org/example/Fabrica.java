package org.example;

import java.util.concurrent.locks.*;

public class Fabrica {
    private final Lock lock = new ReentrantLock();
    private final Condition espacioDisponible = lock.newCondition();
    private final Condition espacioOcupado = lock.newCondition();
    private int[] buffer;
    private int in = 0;
    private int out = 0;
    private int count = 0;

    public Fabrica(int tamanoBuffer) {
        buffer = new int[tamanoBuffer];
    }

    public void producir(int componente) throws InterruptedException {
        lock.lock();
        try {
            while (count == buffer.length) {
                espacioDisponible.await();
            }
            buffer[in] = componente;
            in = (in + 1) % buffer.length;
            count++;
            espacioOcupado.signal();
        } finally {
            lock.unlock();
        }
    }

    public int ensamblar() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                espacioOcupado.await();
            }
            int componente = buffer[out];
            out = (out + 1) % buffer.length;
            count--;
            espacioDisponible.signal();
            return componente;
        } finally {
            lock.unlock();
        }
    }
}
