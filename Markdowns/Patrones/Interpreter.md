# Interpreter (Intérprete)

## Explicación:

El patrón Interpreter proporciona una manera de evaluar el lenguaje o gramática de un programa. Es útil para aplicaciones que necesitan interpretar o ejecutar instrucciones, como lenguajes de scripting o reglas de negocio.

# Ejemplo con código:

```java
// Interfaz de expresión
public interface Expresion {
    boolean interpretar(String contexto);
}

// Expresión concreta
public class ExpresionTerminal implements Expresion {
    private String datos;

    public ExpresionTerminal(String datos) {
        this.datos = datos;
    }

    @Override
    public boolean interpretar(String contexto) {
        return contexto.contains(datos);
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        Expresion esJava = new ExpresionTerminal("Java");
        Expresion esPatron = new ExpresionTerminal("Patrón");

        String contexto = "Patrón de diseño en Java";

        System.out.println("Contiene 'Java'? " + esJava.interpretar(contexto)); // true
        System.out.println("Contiene 'Patrón'? " + esPatron.interpretar(contexto)); // true
    }
}
```

## Explicación del ejemplo:

- Expresión (ExpresionTerminal): Define una operación sobre el contexto.
- Ejecución (Main): Interpreta si el contexto contiene ciertas palabras clave.

## Dónde se usa:

Se utiliza en aplicaciones que requieren el análisis o interpretación de un lenguaje formal, como compiladores o motores de reglas.

**Ventajas:**

- Flexibilidad: Permite definir y ejecutar reglas de manera dinámica.
- Fácil de extender: Se pueden agregar nuevas reglas fácilmente.

**Inconvenientes:**

- Complejidad: Puede ser complejo para gramáticas grandes.
- Rendimiento: Puede ser lento si no se optimiza adecuadamente.