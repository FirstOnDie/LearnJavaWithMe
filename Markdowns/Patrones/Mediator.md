# Mediator (Mediador)

## Explicación:

El patrón Mediator define un objeto que encapsula cómo un conjunto de objetos interactúa. Promueve un acoplamiento suelto manteniendo la lógica de interacción entre los objetos en el mediador en lugar de en los objetos mismos.

## Ejemplo con código:

```java
// Interfaz de mediador
public interface Mediador {
    void enviar(String mensaje, Colega colega);
}

// Clase abstracta para colegas
public abstract class Colega {
    protected Mediador mediador;

    public Colega(Mediador mediador) {
        this.mediador = mediador;
    }

    public abstract void recibir(String mensaje);
}

// Mediador concreto
public class MediadorConcreto implements Mediador {
    private Colega colega1;
    private Colega colega2;

    public void setColega1(Colega colega) {
        this.colega1 = colega;
    }

    public void setColega2(Colega colega) {
        this.colega2 = colega;
    }

    @Override
    public void enviar(String mensaje, Colega colega) {
        if (colega == colega1) {
            colega2.recibir(mensaje);
        } else {
            colega1.recibir(mensaje);
        }
    }
}

// Colega concreto
public class ColegaConcreto1 extends Colega {
    public ColegaConcreto1(Mediador mediador) {
        super(mediador);
    }

    @Override
    public void recibir(String mensaje) {
        System.out.println("Colega 1 recibió: " + mensaje);
    }
}

// Colega concreto
public class ColegaConcreto2 extends Colega {
    public ColegaConcreto2(Mediador mediador) {
        super(mediador);
    }

    @Override
    public void recibir(String mensaje) {
        System.out.println("Colega 2 recibió: " + mensaje);
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        MediadorConcreto mediador = new MediadorConcreto();
        Colega colega1 = new ColegaConcreto1(mediador);
        Colega colega2 = new ColegaConcreto2(mediador);

        mediador.setColega1(colega1);
        mediador.setColega2(colega2);

        colega1.mediador.enviar("Hola desde Colega 1", colega1);
        colega2.mediador.enviar("Hola desde Colega 2", colega2);
    }
}
```

## Explicación del ejemplo:

- Mediador (MediadorConcreto): Gestiona la comunicación entre colegas.
- Colega (ColegaConcreto1, ColegaConcreto2): Representa los objetos que interactúan a través del mediador.
- Ejecución (Main): Configura los colegas y el mediador, y envía mensajes.

## Dónde se usa:

Se utiliza cuando es necesario reducir el acoplamiento entre componentes que se comunican entre sí, como en aplicaciones de chat y sistemas de control de tráfico aéreo.

**Ventajas:**

- Reducción del acoplamiento: Reduce la dependencia directa entre los objetos que se comunican.
- Mejora de la legibilidad: Centraliza la lógica de interacción.

**Inconvenientes:**

- Complejidad: Puede aumentar la complejidad si no se maneja correctamente.
- Punto único de fallo: El mediador puede convertirse en un cuello de botella si no se diseña adecuadamente.
