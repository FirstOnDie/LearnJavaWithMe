# Patrón Singleton

**¿Qué hace?**
El patrón Singleton asegura que solo haya una instancia de una clase en todo tu programa. Es como un presidente en una escuela; solo puede haber uno en cualquier momento.

Ejemplo:

```java

public class Presidente {
private static Presidente presidente;

    private Presidente() {
        // Constructor privado
    }

    public static Presidente getInstance() {
        if (presidente == null) {
            presidente = new Presidente();
        }
        return presidente;
    }
}
```

**Explicación:**

Aquí, usamos un constructor privado para que nadie pueda crear un nuevo `Presidente` directamente. En su lugar, todos tienen que usar el método `getInstance()`, que solo creará un presidente si aún no existe.