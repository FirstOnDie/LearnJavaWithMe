# Strategy (Estrategia)
Explicación:
El patrón Strategy permite definir una familia de algoritmos y hacer que estos sean intercambiables. El algoritmo puede variar independientemente de los clientes que lo usan.

Ejemplo con código:

```java
// Interfaz de estrategia
public interface Estrategia {
    void ejecutar();
}

// Estrategia concreta
public class AtaqueRapido implements Estrategia {
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando ataque rápido");
    }
}

// Estrategia concreta
public class AtaqueLento implements Estrategia {
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando ataque lento");
    }
}

// Contexto
public class Juego {
    private Estrategia estrategia;

    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
    }

    public void jugar() {
        estrategia.ejecutar();
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego();
        
        juego.setEstrategia(new AtaqueRapido());
        juego.jugar(); // Ejecuta ataque rápido

        juego.setEstrategia(new AtaqueLento());
        juego.jugar(); // Ejecuta ataque lento
    }
}
```

## Explicación del ejemplo:

- Estrategia (Estrategia, AtaqueRapido, AtaqueLento): Define diferentes maneras de ejecutar un ataque.
- Contexto (Juego): Permite cambiar la estrategia de ataque durante el juego.

## Dónde se usa:

Se utiliza en aplicaciones donde se necesitan múltiples variantes de un algoritmo. Por ejemplo, diferentes métodos de ordenamiento, rutas de navegación o cálculos financieros.

**Ventajas:**

- Flexibilidad: Permite cambiar algoritmos fácilmente sin modificar el contexto.
- Mejora de mantenimiento: Los algoritmos están encapsulados en sus propias clases, lo que facilita el mantenimiento.

**Inconvenientes:**

- Aumento del número de clases: Puede resultar en muchas clases para cada estrategia.
- Complejidad adicional: Puede ser excesivo para casos simples.