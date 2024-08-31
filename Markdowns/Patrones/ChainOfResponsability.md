# Chain of Responsibility (Cadena de Responsabilidad)

## Explicación:

El patrón Chain of Responsibility permite que varios objetos manejen una solicitud sin que el remitente o receptor estén directamente acoplados. La solicitud pasa por una cadena de manejadores hasta que uno de ellos la procesa.

## Ejemplo con código:

```java
// Clase abstracta para manejador
public abstract class Manejador {
protected Manejador siguiente;

    public void setSiguiente(Manejador siguiente) {
        this.siguiente = siguiente;
    }

    public abstract void manejar(int nivel);
}

// Manejador concreto
public class ManejadorBajo extends Manejador {
    @Override
    public void manejar(int nivel) {
        if (nivel < 5) {
            System.out.println("Manejador Bajo: Procesando nivel " + nivel);
        } else if (siguiente != null) {
            siguiente.manejar(nivel);
        }
    }
}

// Manejador concreto
public class ManejadorAlto extends Manejador {
    @Override
    public void manejar(int nivel) {
        if (nivel >= 5) {
            System.out.println("Manejador Alto: Procesando nivel " + nivel);
        } else if (siguiente != null) {
            siguiente.manejar(nivel);
        }
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        Manejador manejadorBajo = new ManejadorBajo();
        Manejador manejadorAlto = new ManejadorAlto();

        manejadorBajo.setSiguiente(manejadorAlto);

        manejadorBajo.manejar(2); // Manejador Bajo procesa
        manejadorBajo.manejar(6); // Manejador Alto procesa
    }
}
```

## Explicación del ejemplo:

- Manejador (Manejador, ManejadorBajo, ManejadorAlto): Cada manejador procesa la solicitud o la pasa al siguiente.
- Ejecución (Main): Configura la cadena de manejadores y procesa las solicitudes.

## Dónde se usa:

Se utiliza en sistemas donde varias etapas de procesamiento pueden manejar una solicitud. Ejemplos incluyen validación de datos, procesamiento de eventos o flujos de trabajo.

**Ventajas:**

- Desacoplamiento: El cliente no necesita conocer la estructura de la cadena.
- Flexibilidad: Fácil de agregar o modificar manejadores.

**Inconvenientes:**

- Falta de garantía de manejo: No hay garantía de que una solicitud sea manejada.
- Dificultad en el seguimiento: Puede ser difícil de depurar si la cadena es larga.