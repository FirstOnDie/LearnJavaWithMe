#  Decorator (Decorador)

## Explicación:

El patrón Decorator permite añadir comportamiento a un objeto de manera dinámica sin modificar su estructura. Es como agregar capas adicionales de funcionalidad.

## Ejemplo con código:

```java
// Componente base
public interface Helado {
    String hacer();
}

// Componente concreto
public class HeladoBasico implements Helado {
    @Override
    public String hacer() {
        return "Helado básico";
    }
}

// Decorador base
public abstract class HeladoDecorador implements Helado {
    protected Helado helado;

    public HeladoDecorador(Helado helado) {
        this.helado = helado;
    }

    public abstract String hacer();
}

// Decorador concreto
public class ConChispas extends HeladoDecorador {
    public ConChispas(Helado helado) {
        super(helado);
    }

    @Override
    public String hacer() {
        return helado.hacer() + " con chispas";
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        Helado helado = new HeladoBasico();
        Helado heladoConChispas = new ConChispas(helado);

        System.out.println(heladoConChispas.hacer());
    }
}
```

## Explicación del ejemplo:

- Decorador (ConChispas): Añade comportamiento adicional al Helado sin modificar su estructura.
- Ejecución (Main): Crea un helado básico y luego añade chispas usando el decorador.

## Dónde se usa:

Se utiliza cuando se desea añadir o cambiar el comportamiento de objetos de manera dinámica sin herencia, como en sistemas de interfaz de usuario.

**Ventajas:**

- Flexibilidad: Permite añadir comportamientos adicionales sin modificar la clase original.
- Reutilización de código: Los decoradores pueden ser reutilizados en diferentes contextos.

**Inconvenientes:**

- Complejidad: Puede añadir complejidad adicional si se usan muchos decoradores.
- Difícil de entender: El flujo de ejecución puede ser difícil de seguir si hay muchos decoradores encadenados.