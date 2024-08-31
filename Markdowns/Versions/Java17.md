# Java 17

Java 17 es la versión LTS más reciente y trajo consigo varias mejoras que hacen que el código sea más seguro y fácil de manejar.

## Resumen

- **Clases selladas:** Controla quién puede heredar de una clase.
- **Patrones de coincidencia para switch:** Permite usar `switch` para verificar tipos de manera más elegante.
- **Registros:** Crea clases para almacenar datos de manera más simple.

## Sealed Classes (Clases Selladas):

Las clases selladas te permiten controlar quién puede heredar de una clase. Imagina que tienes un club exclusivo donde solo ciertos amigos pueden entrar; las clases selladas funcionan de la misma manera.

Ejemplo:

```java

public sealed class Vehiculo permits Coche, Bicicleta {}

public final class Coche extends Vehiculo {}
public final class Bicicleta extends Vehiculo {}
```

**Explicación:**
Aquí, `Vehiculo` es una clase sellada y solo `Coche` y `Bicicleta` pueden ser sus subclases. Es como si pusieras un letrero en la puerta del club diciendo "Solo coches y bicicletas permitidos".

---

## Pattern Matching for switch (Coincidencia de Patrones para switch):

Esta característica permite usar `switch` para verificar tipos de manera más elegante. Es como si tuvieras una caja mágica que puede cambiar de forma dependiendo de lo que pongas dentro.

Ejemplo:

```java
Object obj = "Hola";

switch (obj) {
    case String s -> System.out.println("Es una cadena: " + s);
    case Integer i -> System.out.println("Es un número entero: " + i);
    default -> System.out.println("Tipo desconocido");
}
```

**Explicación:**

Aquí, `switch` revisa el tipo de `obj` y actúa según el tipo. Es como si tuvieras una varita mágica que sabe exactamente qué hechizo lanzar dependiendo del objeto que estés sosteniendo.

---

## Records:

Los registros (`records`) son una forma de crear clases para almacenar datos de manera más simple. Es como tener una ficha rápida para guardar la información de alguien sin necesidad de escribir todo el libro.

Ejemplo:

```java

record Persona(String nombre, int edad) {}

public class Main {
    public static void main(String[] args) {
        Persona persona = new Persona("Carlos", 10);
        System.out.println(persona.nombre() + " tiene " + persona.edad() + " años.");
    }
}
```

**Explicación:**

Aquí, `Persona` es un registro que almacena datos sobre una persona, como su nombre y edad. Es como tener una tarjeta que guarda la información de un amigo de manera sencilla.