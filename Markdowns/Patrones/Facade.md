# Facade (Fachada)

## Explicación:

El patrón Facade proporciona una interfaz simplificada a un conjunto complejo de clases, bibliotecas o frameworks. Actúa como una fachada que oculta la complejidad del sistema subyacente.

## Ejemplo con código:

```java
// Sistema complejo
public class SubsistemaAudio {
    public void encender() { System.out.println("Audio encendido"); }
    public void ajustarVolumen(int nivel) { System.out.println("Volumen ajustado a " + nivel); }
}

public class SubsistemaVideo {
    public void encender() { System.out.println("Video encendido"); }
    public void establecerResolucion(int resolucion) { System.out.println("Resolución ajustada a " + resolucion + "p"); }
}

// Fachada
public class SistemaEntretenimientoFacade {
    private SubsistemaAudio audio = new SubsistemaAudio();
    private SubsistemaVideo video = new SubsistemaVideo();

    public void iniciar() {
        audio.encender();
        audio.ajustarVolumen(10);
        video.encender();
        video.establecerResolucion(1080);
        System.out.println("Sistema de entretenimiento iniciado");
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        SistemaEntretenimientoFacade sistema = new SistemaEntretenimientoFacade();
        sistema.iniciar();
    }
}
```

## Explicación del ejemplo:

- Fachada (SistemaEntretenimientoFacade): Proporciona una interfaz simplificada para inicializar un sistema de entretenimiento complejo.
- Ejecución (Main): Utiliza la fachada para iniciar el sistema de entretenimiento sin tener que interactuar con los subsistemas de audio y video por separado.

## Dónde se usa:

Se utiliza para simplificar la interacción con sistemas complejos, como al usar librerías grandes o múltiples subsistemas.

**Ventajas:**

- Simplificación: Oculta la complejidad del sistema subyacente.
- Mejor mantenimiento: Facilita el cambio de los componentes internos sin afectar a los usuarios externos.

**Inconvenientes:**

- Funcionalidad limitada: Puede limitar la funcionalidad disponible al simplificar demasiado la interfaz.
- Dependencia en la fachada: Puede llevar a una dependencia excesiva en la fachada, dificultando la personalización.