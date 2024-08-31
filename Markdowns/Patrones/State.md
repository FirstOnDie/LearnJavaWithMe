# State (Estado)

## Explicación:

El patrón State permite que un objeto altere su comportamiento cuando su estado interno cambia. El objeto parecerá cambiar su clase.

## Ejemplo con código:

```java

// Interfaz de estado
public interface Estado {
    void manejar();
}

// Estado concreto
public class EstadoEncendido implements Estado {
    @Override
    public void manejar() {
        System.out.println("La luz está encendida.");
    }
}

// Estado concreto
public class EstadoApagado implements Estado {
    @Override
    public void manejar() {
        System.out.println("La luz está apagada.");
    }
}

// Contexto
public class Luz {
    private Estado estado;

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void cambiarEstado() {
        estado.manejar();
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        Luz luz = new Luz();
        luz.setEstado(new EstadoEncendido());
        luz.cambiarEstado(); // La luz está encendida

        luz.setEstado(new EstadoApagado());
        luz.cambiarEstado(); // La luz está apagada
    }
}
```

## Explicación del ejemplo:

- Estado (EstadoEncendido, EstadoApagado): Define diferentes comportamientos basados en el estado de la luz.
- Contexto (Luz): Mantiene una referencia al estado actual y delega las solicitudes al estado.
- Ejecución (Main): Cambia el estado de la luz y observa el comportamiento resultante.

## Dónde se usa:

Se utiliza en sistemas que tienen comportamientos dependientes del estado, como máquinas de estados, controladores de juegos y flujos de trabajo.

**Ventajas:**

- Flexibilidad: Permite cambiar comportamientos en tiempo de ejecución.
- Desacoplamiento: Separa la lógica de estado del contexto.

**Inconvenientes:**

- Complejidad adicional: Puede ser excesivo para problemas simples.
- Aumento de clases: Cada estado requiere una clase, lo que puede aumentar el número de clases.