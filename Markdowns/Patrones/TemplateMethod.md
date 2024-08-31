# Template Method (Método Plantilla)

## Explicación:

El patrón Template Method define el esqueleto de un algoritmo en una operación, dejando algunos pasos a las subclases. Las subclases pueden redefinir ciertos pasos del algoritmo sin cambiar su estructura.

## Ejemplo con código:

```java

// Clase abstracta con método plantilla
public abstract class Juego {
    public final void jugar() {
        inicializar();
        empezarJuego();
        finalizarJuego();
    }

    protected abstract void inicializar();
    protected abstract void empezarJuego();
    protected abstract void finalizarJuego();
}

// Subclase concreta
public class Ajedrez extends Juego {
    @Override
    protected void inicializar() {
        System.out.println("Ajedrez: Inicializando el juego.");
    }

    @Override
    protected void empezarJuego() {
        System.out.println("Ajedrez: Comenzando el juego.");
    }

    @Override
    protected void finalizarJuego() {
        System.out.println("Ajedrez: Terminando el juego.");
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        Juego ajedrez = new Ajedrez();
        ajedrez.jugar();
    }
}
```

## Explicación del ejemplo:

- Método plantilla (jugar en Juego): Define la estructura del algoritmo.
- Subclase (Ajedrez): Proporciona la implementación específica para cada paso del algoritmo.
- Ejecución (Main): Utiliza el método plantilla para jugar al ajedrez.

## Dónde se usa:

Se utiliza en sistemas donde se necesita que los detalles del algoritmo puedan variar en las subclases, como en frameworks y bibliotecas.

**Ventajas:**

- Reutilización de código: Permite que las subclases compartan la estructura del algoritmo.
- Flexibilidad: Las subclases pueden modificar solo los pasos que necesitan.

**Inconvenientes:**

- Restricción: Las subclases están limitadas a la estructura del método plantilla.
- Complejidad: Puede ser excesivo para casos simples.