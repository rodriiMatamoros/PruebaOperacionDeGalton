package org.example;

public class LineaDeEnsamblaje implements Runnable {
    private Fabrica fabrica;

    public LineaDeEnsamblaje(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Lógica de ensamblaje
                int componente = fabrica.ensamblar();
                System.out.println("Ensamblaje: Componente " + componente + " ensamblado");
                Thread.sleep(100);  // Simula el tiempo de ensamblaje
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

