# Visitor (Visitante)

## Explicación:

El patrón Visitor permite definir nuevas operaciones sobre una estructura de objetos sin cambiar las clases de los elementos sobre los que opera. Esto se logra mediante la separación del algoritmo del objeto sobre el que opera.

## Ejemplo con código:

```java
// Interfaz del visitante
public interface Visitante {
    void visitar(ElementoConcreto1 elemento);
    void visitar(ElementoConcreto2 elemento);
}

// Interfaz del elemento
public interface Elemento {
    void aceptar(Visitante visitante);
}

// Elemento concreto
public class ElementoConcreto1 implements Elemento {
    @Override
    public void aceptar(Visitante visitante) {
        visitante.visitar(this);
    }

    public String operacionConcreta1() {
        return "Operación en ElementoConcreto1";
    }
}

// Elemento concreto
public class ElementoConcreto2 implements Elemento {
    @Override
    public void aceptar(Visitante visitante) {
        visitante.visitar(this);
    }

    public String operacionConcreta2() {
        return "Operación en ElementoConcreto2";
    }
}

// Visitante concreto
public class VisitanteConcreto implements Visitante {
    @Override
    public void visitar(ElementoConcreto1 elemento) {
        System.out.println(elemento.operacionConcreta1());
    }

    @Override
    public void visitar(ElementoConcreto2 elemento) {
        System.out.println(elemento.operacionConcreta2());
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        Elemento elemento1 = new ElementoConcreto1();
        Elemento elemento2 = new ElementoConcreto2();
        Visitante visitante = new VisitanteConcreto();

        elemento1.aceptar(visitante);
        elemento2.aceptar(visitante);
    }
}
```

## Explicación del ejemplo:

- Visitante (VisitanteConcreto): Define nuevas operaciones para elementos concretos.
- Elemento (ElementoConcreto1, ElementoConcreto2): Estructuras sobre las que opera el visitante.
- Ejecución (Main): Usa el visitante para realizar operaciones en los elementos.

## Dónde se usa:

Se utiliza cuando se necesita agregar nuevas operaciones a estructuras de objetos complejas sin cambiar las clases de los elementos, como en compiladores y analizadores sintácticos.

**Ventajas:**

- Flexibilidad: Permite agregar nuevas operaciones sin cambiar las clases de los elementos.
- Separación de responsabilidades: Mantiene las operaciones separadas de la estructura del objeto.

**Inconvenientes:**

- Complejidad: Puede ser difícil de mantener si hay muchas clases de elementos.
- Dependencia: Los visitantes dependen de la estructura interna de los elementos.