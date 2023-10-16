package org.example;

//POR CONSOLA
/*
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
}*/


//VENTA GRAFICA JAVAFX

/*
import javafx.application.Application;
import javafx.application.Platform;
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

    @Override
    public void stop() {
        // Manejar la finalización de la aplicación (por ejemplo, cerrar hilos y liberar recursos)
        Platform.exit();
    }
}*/

/**
 * Cuando ejecuto el codigo comentado arriba para implementar la interfaz gafrica en JavaFX
 * a mi programa me sale el siguiente error y no se como solucionarlo si esta
 * todo bien importado en sus librerias, modulos y dependencias:

/Users/rodri/Library/Java/JavaVirtualMachines/openjdk-19.0.2/Contents/Home/bin/java -javaagent:/Users/rodri
/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/232.9559.62/IntelliJ IDEA.app/Contents/lib
/idea_rt.jar=61600:/Users/rodri/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/232.9559.62
/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8
 -classpath /Users/rodri/IdeaProjects/PruebaOperacionDeGalton/target/classes:/Users/rodri
 /.m2/repository/org/openjfx/javafx-graphics/19.0.2/javafx-graphics-19.0.2.jar:/Users/rodri
 /.m2/repository/org/openjfx/javafx-graphics/19.0.2/javafx-graphics-19.0.2-mac-aarch64.jar:/Users
 /rodri/.m2/repository/org/openjfx/javafx-base/19.0.2/javafx-base-19.0.2.jar:/Users/rodri
 /.m2/repository/org/openjfx/javafx-base/19.0.2/javafx-base-19.0.2-mac-aarch64.jar:/Users
 /rodri/.m2/repository/org/openjfx/javafx-controls/19.0.2/javafx-controls-19.0.2.jar:/Users
 /rodri/.m2/repository/org/openjfx/javafx-controls/19.0.2/javafx-controls-19.0.2-mac-aarch64.jar org.example.Main
Error: JavaFX runtime components are missing, and are required to run this application

 */

//VENTANA GRAFICA SWING
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });

        int tamanoBuffer = 10; // Tamaño del búfer compartido
        Fabrica fabrica = new Fabrica(tamanoBuffer);

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

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Distribución de Componentes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(list);

        frame.getContentPane().add(scrollPane);

        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);
    }
}




