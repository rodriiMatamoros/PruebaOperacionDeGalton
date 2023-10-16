package org.example;

public class Main {
    public static void main(String[] args) {
        int tamanoBuffer = 10; // Tamaño del búfer compartido
        Fabrica fabrica = new Fabrica(tamanoBuffer);

        int numEstaciones = 5; // Número de estaciones de trabajo
        Thread[] estaciones = new Thread[numEstaciones];

        // Crear y arrancar hilos de estaciones de trabajo
        for (int i = 0; i < numEstaciones; i++) {
            estaciones[i] = new Thread(new EstacionDeTrabajo(fabrica, i));
            estaciones[i].start();
        }

        // Crear y arrancar hilo de línea de ensamblaje
        Thread lineaDeEnsamblajeThread = new Thread(new LineaDeEnsamblaje(fabrica));
        lineaDeEnsamblajeThread.start();

        // Esperar a que todos los hilos terminen
        try {
            for (Thread estacion : estaciones) {
                estacion.join();
            }

            lineaDeEnsamblajeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Realizar visualización en tiempo real del fenómeno (debe ser implementada)
    }
}
/*
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int tamanoBuffer = 10; // Tamaño del búfer compartido
    private static Fabrica fabrica;
    private static LineChart<Number, Number> chart;
    private static XYChart.Series<Number, Number> series;

    public static void main(String[] args) {
        fabrica = new Fabrica(tamanoBuffer);
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Distribución de Componentes");

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle("Distribución de Componentes");

        series = new XYChart.Series<>();
        series.setName("Componentes");

        chart.getData().add(series);

        Scene scene = new Scene(chart, 800, 600);

        stage.setScene(scene);
        stage.show();

        int numEstaciones = 5; // Número de estaciones de trabajo
        Thread[] estaciones = new Thread[numEstaciones];

        for (int i = 0; i < numEstaciones; i++) {
            estaciones[i] = new Thread(new EstacionDeTrabajo(fabrica, i));
            estaciones[i].start();
        }

        Thread lineaDeEnsamblajeThread = new Thread(new LineaDeEnsamblaje(fabrica));
        lineaDeEnsamblajeThread.start();

        // Esperar a que todos los hilos terminen
        try {
            for (Thread estacion : estaciones) {
                estacion.join();
            }

            lineaDeEnsamblajeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}*/
