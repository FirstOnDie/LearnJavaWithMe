# Patrón Strategy (Estrategia)
**¿Qué hace?**
El patrón Strategy es como tener diferentes estrategias para jugar un juego, como el ajedrez. Puedes cambiar la estrategia dependiendo de lo que esté haciendo tu oponente.

Ejemplo:

```java
public interface Estrategia {
    void ejecutar();
}

public class AtaqueRapido implements Estrategia {
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando ataque rápido");
    }
}

public class Juego {
    private Estrategia estrategia;

    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
    }

    public void jugar() {
        estrategia.ejecutar();
    }
}
```
**Explicación:**
Aquí, `Juego` puede usar diferentes estrategias, como `AtaqueRapido`. Puedes cambiar la estrategia en cualquier momento y el juego usará esa nueva estrategia.