# Patrón Observer (Observador)
**¿Qué hace?**
El patrón Observer es como un sistema de alarma en una casa. Cuando algo cambia (como si alguien abre la puerta), todas las alarmas conectadas se activan.

Ejemplo:

```java
import java.util.ArrayList;
import java.util.List;

public class Alarma {
private List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void notificar() {
        for (Observador o : observadores) {
            o.actualizar();
        }
    }
}

public interface Observador {
    void actualizar();
}

public class AlarmaSonora implements Observador {
    public void actualizar() {
        System.out.println("¡Alarma Sonora activada!");
    }
}
```
**Explicación:**
En este ejemplo, `Alarma` es un objeto que notifica a todos sus `Observadores` (como `AlarmaSonora`) cuando algo sucede. Es como cuando alguien grita "¡Fuego!" y todos corren.