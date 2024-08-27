package org.fractal.multithreading;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Simula una lista de imágenes
        List<String> images = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            images.add("Image" + i);
        }

        // Divide las imágenes en dos partes para procesarlas en paralelo
        List<String> part1 = images.subList(0, 50);
        List<String> part2 = images.subList(50, 100);

        // Crea hilos para procesar cada parte
        Thread thread1 = new Thread(new MultiThreadingApplication(part1));
        Thread thread2 = new Thread(new MultiThreadingApplication(part2));

        // Inicia los hilos
        thread1.start();
        thread2.start();

        // Espera a que ambos hilos terminen
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Procesamiento de imágenes completado.");
    }
}
