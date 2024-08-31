# Observer (Observador)**

## Explicación:

El patrón Observer establece una relación uno-a-muchos entre objetos, donde un objeto (sujeto) notifica a varios objetos dependientes (observadores) de cualquier cambio de estado.

## Ejemplo con código:

```java
import java.util.ArrayList;
import java.util.List;

// Interfaz del sujeto
public interface Sujeto {
    void agregarObservador(Observador observador);
    void notificarObservadores();
}

// Interfaz del observador
public interface Observador {
    void actualizar(String mensaje);
}

// Sujeto concreto
public class CanalNoticias implements Sujeto {
    private List<Observador> observadores = new ArrayList<>();
    private String noticia;

    @Override
    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.actualizar(noticia);
        }
    }

    public void nuevaNoticia(String noticia) {
        this.noticia = noticia;
        notificarObservadores();
    }
}

// Observador concreto
public class Suscriptor implements Observador {
    private String nombre;

    public Suscriptor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println(nombre + " recibió la noticia: " + mensaje);
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        CanalNoticias canal = new CanalNoticias();
        Suscriptor suscriptor1 = new Suscriptor("Carlos");
        Suscriptor suscriptor2 = new Suscriptor("Ana");

        canal.agregarObservador(suscriptor1);
        canal.agregarObservador(suscriptor2);

        canal.nuevaNoticia("¡Nuevo patrón de diseño lanzado!");
    }
}
```

## Explicación del ejemplo:

- Sujeto (CanalNoticias): Notifica a sus observadores cada vez que hay una nueva noticia.
- Observador (Suscriptor): Recibe y procesa las notificaciones del sujeto.
- Ejecución (Main): Agrega observadores al sujeto y genera un cambio de estado.

## Dónde se usa:

Se utiliza en sistemas donde varios objetos necesitan ser notificados sobre cambios en el estado de otro objeto, como en sistemas de eventos, interfaces gráficas y aplicaciones de mensajería.

**Ventajas:**

- Desacoplamiento: Los observadores y los sujetos están desacoplados.
- Flexibilidad: Facilita la adición y eliminación de observadores en tiempo de ejecución.

**Inconvenientes:**

- Complejidad: Puede ser difícil de manejar con muchos observadores.
- Potencial para ciclos de referencia: Los observadores y los sujetos pueden crear referencias circulares.
