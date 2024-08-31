# Patrón Decorator (Decorador)
**¿Qué hace?**
El patrón Decorator es como agregar ingredientes a un helado. Puedes empezar con un helado básico y luego añadirle más cosas (como chispas o jarabe) para hacerlo más sabroso.

Ejemplo:

```java
public interface Helado {
    String hacer();
}

public class HeladoBasico implements Helado {
    @Override
    public String hacer() {
        return "Helado básico";
    }
}

public class ConChispas implements Helado {
private Helado helado;

    public ConChispas(Helado helado) {
        this.helado = helado;
    }

    @Override
    public String hacer() {
        return helado.hacer() + " con chispas";
    }
}
```
**Explicación:**
En este ejemplo, puedes tener un `HeladoBasico`, y luego puedes decorarlo con `ConChispas` para obtener un helado con chispas. Puedes seguir añadiendo más decoradores como "ConJarabe" para tener más y más ingredientes.