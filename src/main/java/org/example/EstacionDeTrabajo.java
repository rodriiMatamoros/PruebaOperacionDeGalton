package org.example;

import java.util.Random;

public class EstacionDeTrabajo implements Runnable {
    private Fabrica fabrica;
    private int id;

    public EstacionDeTrabajo(Fabrica fabrica, int id) {
        this.fabrica = fabrica;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Lógica de producción de componentes
                int componente = producirComponente();
                System.out.println("Estación " + id + " produjo componente " + componente);
                fabrica.producir(componente);
                Thread.sleep(100);  // Simula el tiempo de producción
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int producirComponente() {
        // Simula la producción de un componente
        Random random = new Random();
        return random.nextInt(100); // Genera un componente aleatorio
    }
}

