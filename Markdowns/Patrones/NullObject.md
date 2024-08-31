# Null Object

## Explicación:

El patrón Null Object proporciona un objeto "nulo" que representa la ausencia de un objeto y evita el uso de comprobaciones nulas. Es útil para simplificar el manejo de referencias nulas.

## Ejemplo con código:

```java
// Interfaz de cliente
public interface Cliente {
    void hacerAlgo();
}

// Cliente real
public class ClienteReal implements Cliente {
    @Override
    public void hacerAlgo() {
        System.out.println("Cliente real haciendo algo.");
    }
}

// Cliente nulo
public class ClienteNulo implements Cliente {
    @Override
    public void hacerAlgo() {
        // No hacer nada
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new ClienteReal();
        Cliente cliente2 = new ClienteNulo();

        cliente1.hacerAlgo(); // Cliente real haciendo algo.
        cliente2.hacerAlgo(); // No hace nada
    }
}
```

## Explicación del ejemplo:

- Cliente nulo (ClienteNulo): Representa la ausencia de un cliente real y no hace nada.
- Ejecución (Main): Muestra cómo usar un objeto nulo en lugar de manejar referencias nulas.

## Dónde se usa:

Se utiliza en aplicaciones donde el uso de referencias nulas es común y se desea evitar comprobaciones nulas repetitivas, como en frameworks y bibliotecas.

**Ventajas:**

- Simplicidad: Reduce la necesidad de comprobaciones nulas.
- Seguridad: Evita excepciones NullPointerException.

**Inconvenientes:**

- Comportamiento inesperado: Puede ser difícil detectar errores si se usa incorrectamente.
- Sobrecarga: Puede añadir una capa adicional de abstracción.