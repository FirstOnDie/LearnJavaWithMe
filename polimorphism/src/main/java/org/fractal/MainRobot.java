package org.fractal;

class Robot {
    public void saludar() {
        System.out.println("Hola!");
    }

    public void saludar(String nombre) {
        System.out.println("Hola, " + nombre + "!");
    }

    public void saludar(String nombre, boolean formal) {
        if (formal) {
            System.out.println("Buenos días, " + nombre + ".");
        } else {
            System.out.println("¡Hola, " + nombre + "!");
        }
    }
}

public class MainRobot {
    public static void main(String[] args) {
        Robot miRobot = new Robot();
        miRobot.saludar(); // "Hola!"
        miRobot.saludar("Carlos"); // "Hola, Carlos!"
        miRobot.saludar("Carlos", true); // "Buenos días, Carlos."
    }
}

