# Prototype (Prototipo)

## Explicación:

El patrón Prototype permite crear nuevos objetos copiando un prototipo en lugar de crear instancias nuevas. Es útil cuando la creación de un objeto es costosa o compleja.

## Ejemplo con código:

```java
// Interfaz de clonación
public interface Clonable {
    Clonable clonar();
}

// Clase concreta
public class Coche implements Clonable {
    private String modelo;

    public Coche(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public Clonable clonar() {
        return new Coche(modelo);
    }

    public String getModelo() {
        return modelo;
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        Coche coche1 = new Coche("Sedan");
        Coche coche2 = (Coche) coche1.clonar();

        System.out.println("Modelo del coche 1: " + coche1.getModelo());
        System.out.println("Modelo del coche 2: " + coche2.getModelo());
    }
}
```

## Explicación del ejemplo:

- Prototipo (Coche): Proporciona un método para clonar el objeto.
- Ejecución (Main): Clona un coche usando el método de prototipo.

## Dónde se usa:

Se utiliza en aplicaciones donde la creación de objetos es costosa o donde los objetos deben ser clonados repetidamente, como en juegos y editores gráficos.

**Ventajas:**

- Eficiencia: Reduce el costo de creación de objetos.
- Flexibilidad: Permite la creación de objetos complejos de manera rápida.

**Inconvenientes:**

- Clonación profunda: La clonación profunda de objetos complejos puede ser difícil.
- Complejidad: Puede aumentar la complejidad del código si no se gestiona adecuadamente.