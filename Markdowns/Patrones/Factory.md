# Patrón Factory (Fábrica)
**¿Qué hace?**

El patrón Factory es como una fábrica que puede crear diferentes tipos de juguetes, dependiendo de lo que se pida. La fábrica decide qué juguete fabricar sin que tú tengas que preocuparte por los detalles.

Ejemplo:

```java
public class JugueteFactory {
    public Juguete crearJuguete(String tipo) {
        if (tipo.equals("Coche")) {
            return new Coche();
        } else if (tipo.equals("Muñeca")) {
            return new Muñeca();
        }
        return null;
    }
}

public interface Juguete {}
public class Coche implements Juguete {}
public class Muñeca implements Juguete {}
```

**Explicación:**
La `JugueteFactory` es como una fábrica que crea diferentes tipos de juguetes. Si pides un "Coche", te da un objeto `Coche`; si pides una "Muñeca", te da un objeto `Muñeca`.