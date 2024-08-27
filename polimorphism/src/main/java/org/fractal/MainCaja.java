package org.fractal;

class Caja<T> {
    private T contenido;

    public void poner(T contenido) {
        this.contenido = contenido;
    }

    public T sacar() {
        return contenido;
    }
}

public class MainCaja {
    public static void main(String[] args) {
        Caja<String> cajaDeJuguetes = new Caja<>();
        cajaDeJuguetes.poner("Coche de juguete");
        System.out.println("Contenido de la caja: " + cajaDeJuguetes.sacar()); // "Contenido de la caja: Coche de juguete"

        Caja<Integer> cajaDeNumeros = new Caja<>();
        cajaDeNumeros.poner(123);
        System.out.println("Contenido de la caja: " + cajaDeNumeros.sacar()); // "Contenido de la caja: 123"
    }
}

