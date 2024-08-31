# Memento

## Explicación: 

El patrón Memento permite capturar el estado interno de un objeto en un momento específico para poder restaurarlo más tarde, sin romper la encapsulación del objeto. Es útil para implementar funciones de deshacer (undo) y rehacer (redo).

## Ejemplo con código:

```java
import java.util.ArrayList;
import java.util.List;

// Memento
public class Memento {
    private final String estado;

    public Memento(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}

// Originador
public class Originador {
    private String estado;

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Memento guardarEstadoEnMemento() {
        return new Memento(estado);
    }

    public void obtenerEstadoDeMemento(Memento memento) {
        estado = memento.getEstado();
    }

    public String getEstado() {
        return estado;
    }
}

// Caretaker

public class Caretaker {
    private List<Memento> listaMementos = new ArrayList<>();

    public void add(Memento estado) {
        listaMementos.add(estado);
    }

    public Memento get(int index) {
        return listaMementos.get(index);
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        Originador originador = new Originador();
        Caretaker caretaker = new Caretaker();

        originador.setEstado("Estado #1");
        originador.setEstado("Estado #2");
        caretaker.add(originador.guardarEstadoEnMemento());

        originador.setEstado("Estado #3");
        caretaker.add(originador.guardarEstadoEnMemento());

        originador.setEstado("Estado #4");

        System.out.println("Estado actual: " + originador.getEstado());
        originador.obtenerEstadoDeMemento(caretaker.get(0));
        System.out.println("Primer estado guardado: " + originador.getEstado());
        originador.obtenerEstadoDeMemento(caretaker.get(1));
        System.out.println("Segundo estado guardado: " + originador.getEstado());
    }
}
```

## Explicación del ejemplo:

- Memento (Memento): Guarda el estado del objeto.
- Originador (Originador): Crea un memento y puede restaurar su estado desde un memento.
- Caretaker (Caretaker): Administra los mementos, guardándolos y recuperándolos.
- Ejecución (Main): Demuestra cómo se guardan y restauran los estados del objeto.

## Dónde se usa:

Se utiliza en aplicaciones que requieren la capacidad de deshacer y rehacer operaciones, como en editores de texto o sistemas de control de versiones.

**Ventajas:**

- No rompe la encapsulación: El estado del objeto se guarda y restaura sin exponer detalles internos.
- Facilita la implementación de operaciones de deshacer/rehacer.

**Inconvenientes:**

- Consumo de memoria: Almacenar múltiples mementos puede requerir mucha memoria.
- Complejidad: Puede ser complicado gestionar el ciclo de vida de los mementos y su almacenamiento.
