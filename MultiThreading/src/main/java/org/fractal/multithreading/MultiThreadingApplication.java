package org.fractal.multithreading;

import java.util.List;

public class MultiThreadingApplication  implements Runnable {
    private List<String> images;

    public MultiThreadingApplication(List<String> images) {
        this.images = images;
    }

    @Override
    public void run() {
        for (String image : images) {
            applyFilter(image);
        }
    }

    private void applyFilter(String image) {
        // Simula el procesamiento de la imagen
        System.out.println(Thread.currentThread().getName() + " processing: " + image);
        try {
            Thread.sleep(100); // Simula tiempo de procesamiento
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

