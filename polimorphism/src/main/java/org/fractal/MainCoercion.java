package org.fractal;

public class MainCoercion {
    public static void main(String[] args) {
        int numeroEntero = 5;
        double numeroDecimal = numeroEntero; // El entero se convierte en decimal automáticamente

        System.out.println("Número entero: " + numeroEntero); // "Número entero: 5"
        System.out.println("Número decimal: " + numeroDecimal); // "Número decimal: 5.0"
    }
}

