# Adapter (Adaptador)

## Explicación:

El patrón Adapter permite que dos interfaces incompatibles trabajen juntas. Actúa como un traductor que adapta una interfaz para que se ajuste a lo que necesita otra.

## Ejemplo con código:

```java
// Interfaz de destino
public interface EnchufeEuropeo {
    void conectar();
}

// Clase existente incompatible
public class EnchufeAmericano {
    public void enchufar() {
        System.out.println("Enchufe americano conectado");
    }
}

// Adaptador
public class AdaptadorEnchufe implements EnchufeEuropeo {
    private EnchufeAmericano enchufeAmericano;

    public AdaptadorEnchufe(EnchufeAmericano enchufeAmericano) {
        this.enchufeAmericano = enchufeAmericano;
    }

    @Override
    public void conectar() {
        enchufeAmericano.enchufar();
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        EnchufeAmericano enchufeAmericano = new EnchufeAmericano();
        EnchufeEuropeo adaptador = new AdaptadorEnchufe(enchufeAmericano);

        adaptador.conectar();
    }
}
```

## Explicación del ejemplo:

- Adapter (AdaptadorEnchufe): Adapta la interfaz del enchufe americano para que funcione como un enchufe europeo.
- Ejecución (Main): Usa el adaptador para conectar un enchufe americano a un enchufe europeo.

## Dónde se usa:

Se utiliza cuando es necesario que clases con interfaces incompatibles trabajen juntas, como en la integración de sistemas legados.

**Ventajas:**

- Reutilización de código existente: Permite usar código existente sin modificarlo.
- Flexibilidad: Facilita la integración de nuevas clases con interfaces diferentes.

**Inconvenientes:**

- Complejidad adicional: Puede añadir complejidad al código si se abusa del patrón.
- Puede ocultar dependencias: Hacer uso excesivo de adaptadores puede hacer que las dependencias del sistema no sean claras.