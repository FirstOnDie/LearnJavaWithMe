package org.fractal;

class Animal {
    public void hacerSonido() {
        System.out.println("Algun sonido de animal");
    }
}

class Perro extends Animal {
    @Override
    public void hacerSonido() {
        System.out.println("Guau");
    }
}

class Gato extends Animal {
    @Override
    public void hacerSonido() {
        System.out.println("Miau");
    }
}

public class MainAnimal {
    public static void main(String[] args) {
        Animal miAnimal1 = new Perro();
        Animal miAnimal2 = new Gato();

        miAnimal1.hacerSonido(); // "Guau"
        miAnimal2.hacerSonido(); // "Miau"
    }
}

