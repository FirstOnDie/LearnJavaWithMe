# Command (Comando)

## Explicación:

El patrón Command encapsula una solicitud como un objeto, permitiendo parametrizar clientes con diferentes solicitudes, encolar solicitudes o realizar operaciones de deshacer.

## Ejemplo con código:

```java
// Interfaz de comando
public interface Comando {
    void ejecutar();
}

// Comando concreto
public class EncenderLuz implements Comando {
private Luz luz;

    public EncenderLuz(Luz luz) {
        this.luz = luz;
    }

    @Override
    public void ejecutar() {
        luz.encender();
    }
}

// Receptor
public class Luz {
    public void encender() {
        System.out.println("Luz encendida");
    }
}

// Invocador
public class ControlRemoto {
private Comando comando;

    public void setComando(Comando comando) {
        this.comando = comando;
    }

    public void presionarBoton() {
        comando.ejecutar();
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        Luz luz = new Luz();
        Comando encenderLuz = new EncenderLuz(luz);

        ControlRemoto control = new ControlRemoto();
        control.setComando(encenderLuz);
        control.presionarBoton(); // Luz encendida
    }
}
```

## Explicación del ejemplo:

- Comando (EncenderLuz): Encapsula la solicitud de encender la luz.
- Receptor (Luz): La luz que se enciende.
- Invocador (ControlRemoto): Ejecuta el comando.

## Dónde se usa:

Se utiliza en sistemas que requieren operaciones de deshacer/rehacer, procesamiento de transacciones o sistemas de menús.

**Ventajas:**

- Desacoplamiento: Separa el objeto que invoca la operación del objeto que conoce cómo realizarla.
- Flexibilidad: Facilita agregar nuevas operaciones sin cambiar el código existente.

**Inconvenientes:**

- Complejidad: Puede añadir complejidad al código si se abusa del patrón.
- Mayor número de clases: Requiere una clase para cada comando.