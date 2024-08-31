# Flyweight (Peso Ligero)

## Explicación:

El patrón Flyweight minimiza el uso de memoria compartiendo tanto como sea posible con objetos similares. Es útil cuando un gran número de objetos casi idénticos necesitan ser creados.

## Ejemplo con código:

```java

import java.util.HashMap;
import java.util.Map;

// Flyweight
public class Carro {
private final String color;

    public Carro(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

// Flyweight Factory
public class FabricaCarro {
private static final Map<String, Carro> carMap = new HashMap<>();

    public static Carro getCarro(String color) {
        Carro carro = carMap.get(color);
        if (carro == null) {
            carro = new Carro(color);
            carMap.put(color, carro);
        }
        return carro;
    }
}

// Ejecución
public class Main {
public static void main(String[] args) {
Carro carro1 = FabricaCarro.getCarro("Rojo");
Carro carro2 = FabricaCarro.getCarro("Rojo");
Carro carro3 = FabricaCarro.getCarro("Azul");

        System.out.println("Carro 1 color: " + carro1.getColor());
        System.out.println("Carro 2 color: " + carro2.getColor());
        System.out.println("Carro 3 color: " + carro3.getColor());

        System.out.println("¿Carro 1 y Carro 2 son el mismo objeto? " + (carro1 == carro2)); // true
    }
}
```

## Explicación del ejemplo:

- Flyweight (Carro): Clase que representa los objetos que serán compartidos.
- Flyweight Factory (FabricaCarro): Crea y gestiona los objetos compartidos.
- Ejecución (Main): Obtiene varios objetos Carro, reutilizando instancias donde es posible.

## Dónde se usa:

Se utiliza cuando se necesita un gran número de objetos que son costosos de crear y que comparten estados internos comunes, como en sistemas de gráficos y juegos.

**Ventajas:**

- Reducción de memoria: Reduce significativamente el uso de memoria al compartir objetos.
- Mejora del rendimiento: Aumenta la eficiencia al reducir la sobrecarga de creación de objetos.

**Inconvenientes:**

- Complejidad de implementación: La administración de objetos compartidos puede ser compleja.
- Menor flexibilidad: Los objetos compartidos no deben tener estados modificables que puedan afectar a otros objetos.
