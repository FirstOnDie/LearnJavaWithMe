# Composite (Compuesto)

## Explicación:

El patrón Composite permite tratar objetos individuales y composiciones de objetos de manera uniforme. Es útil para construir estructuras jerárquicas como árboles.

## Ejemplo con código:

```java
// Componente
public interface Componente {
    void mostrar();
}

// Hoja
public class Hoja implements Componente {
    private String nombre;

    public Hoja(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void mostrar() {
        System.out.println("Hoja: " + nombre);
    }
}

// Compuesto
public class Compuesto implements Componente {
    private List<Componente> componentes = new ArrayList<>();

    public void agregar(Componente componente) {
        componentes.add(componente);
    }

    @Override
    public void mostrar() {
        for (Componente componente : componentes) {
            componente.mostrar();
        }
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        Hoja hoja1 = new Hoja("Hoja 1");
        Hoja hoja2 = new Hoja("Hoja 2");

        Compuesto compuesto = new Compuesto();
        compuesto.agregar(hoja1);
        compuesto.agregar(hoja2);

        compuesto.mostrar();
    }
}
```

## Explicación del ejemplo:

- Componente (Componente): Interfaz común para hojas y compuestos.
- Hoja (Hoja): Representa objetos individuales.
- Compuesto (Compuesto): Contiene hojas y otros compuestos, permitiendo tratar todos de manera uniforme.

## Dónde se usa:

Se utiliza en sistemas gráficos (como interfaces de usuario), estructuras jerárquicas o para representar documentos en un formato estructurado.

**Ventajas:**

- Uniformidad: Facilita el tratamiento uniforme de objetos individuales y compuestos.
- Flexibilidad: Simplifica la adición de nuevos componentes.

**Inconvenientes:**

- Complejidad: Puede ser innecesario para estructuras simples.
- Difícil de gestionar: La gestión de composiciones complejas puede ser complicada.