# Java 21

Java 21 ha introducido muchas características interesantes que hacen que el lenguaje sea más poderoso y fácil de usar. Vamos a ver cada una de estas nuevas características y explicarlas con ejemplos sencillos.

## Resumen
- String Templates: Ayuda a insertar valores en cadenas de texto de manera fácil y rápida.
- Sequenced Collections: Mantiene elementos en un orden específico, como una fila de animales en el zoológico.
- Pattern Matching for Switch: Identifica rápidamente tipos de objetos y realiza acciones específicas, como un robot que clasifica juguetes.
- Record Patterns: Extrae datos fácilmente de registros, como fichas con etiquetas.
- Virtual Threads: Hilos ligeros y rápidos para ejecutar muchas tareas pequeñas, como tener muchos amigos ayudantes.
- Scoped Values: Valores especiales que solo están disponibles durante una tarea específica, como una caja mágica de secretos.

## String Templates (Plantillas de Cadenas)
**¿Qué es?**
Imagina que tienes un conjunto de bloques de construcción con los que puedes crear palabras o frases rápidamente, usando plantillas que puedes personalizar. En Java 21, las plantillas de cadenas permiten insertar fácilmente valores en cadenas de texto.

Ejemplo:

```java
public class Main {
    public static void main(String[] args) {
        String nombre = "Carlos";
        int edad = 10;
        // Usamos una plantilla para construir la cadena de texto
        String mensaje = STR."Hola, mi nombre es \{nombre} y tengo \{edad} años.";
        System.out.println(mensaje); // Imprime: "Hola, mi nombre es Carlos y tengo 10 años."
    }
}
```
Explicación:

En lugar de construir manualmente el texto, utilizamos plantillas para insertar directamente las variables nombre y edad en la cadena. ¡Es como tener un molde donde puedes cambiar lo que quieras sin rehacer todo!

## Sequenced Collections (Colecciones Secuenciadas)
**¿Qué es?**
Imagina que tienes una fila de animales en un zoológico y quieres asegurarte de que siempre estén en el mismo orden, de principio a fin. En Java 21, las colecciones secuenciadas aseguran que los elementos se mantengan en un orden específico.

Ejemplo:

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> animales = new ArrayList<>();
        animales.add("León");
        animales.add("Tigre");
        animales.add("Elefante");

        // Imprimimos los animales en el orden en que se añadieron
        animales.forEach(animal -> System.out.println(animal));
        // Imprime: "León", "Tigre", "Elefante"
    }
}
```
Explicación:

Usando ArrayList, que es un tipo de colección secuenciada, siempre podemos mantener y recuperar los elementos en el orden en que fueron añadidos. ¡Es como tener una fila de animales en orden que nunca cambia!
## Pattern Matching for Switch (Coincidencia de Patrones para switch)
**¿Qué es?**
Imagina que tienes una caja de juguetes que puede contener diferentes tipos de juguetes: coches, aviones, barcos, etc. Usando el switch con coincidencia de patrones, puedes identificar rápidamente qué tipo de juguete es y hacer algo específico con cada uno.

Ejemplo:

```java

public class Main {
    public static void main(String[] args) {
        Object juguete = "Coche";

        // Usamos pattern matching con switch para identificar el tipo de juguete
        switch (juguete) {
            case String s -> System.out.println("Es un juguete llamado: " + s);
            case Integer i -> System.out.println("Es un juguete con número: " + i);
            default -> System.out.println("Es un tipo de juguete desconocido");
        }
    }
}
```
Explicación:

Aquí, el switch detecta automáticamente el tipo del objeto juguete y realiza una acción específica para cada tipo. ¡Es como si tuvieras un robot que automáticamente sabe qué hacer con cada tipo de juguete que encuentra!

## Record Patterns (Patrones de Registro)
**¿Qué es?**
Imagina que tienes una ficha que describe a una persona con su nombre y edad. Los patrones de registro te permiten trabajar fácilmente con estos datos, como si tuvieras etiquetas en la ficha para acceder rápidamente a la información.

Ejemplo:

```java
record Persona(String nombre, int edad) {}

public class Main {
    public static void main(String[] args) {
        Persona persona = new Persona("Carlos", 10);

        // Usamos patrones de registro para extraer fácilmente los datos
        if (persona instanceof Persona(String nombre, int edad)) {
            System.out.println("Nombre: " + nombre + ", Edad: " + edad);
        }
    }
}
```
Explicación:

Un record es como una ficha con datos. Puedes usar patrones para extraer esos datos fácilmente y trabajar con ellos. ¡Es como tener etiquetas mágicas que te dicen todo lo que necesitas saber sobre una persona!

## Virtual Threads (Hilos Virtuales)
**¿Qué es?**
Imagina que tienes muchas tareas que hacer, como armar rompecabezas, y deseas que muchos amigos te ayuden al mismo tiempo. Los hilos virtuales son como esos amigos que pueden hacer muchas tareas pequeñas y rápidas simultáneamente, sin atascarse.

Ejemplo:

```java

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Crear un hilo virtual para ejecutar una tarea
        Thread hilo = Thread.ofVirtual().start(() -> {
            System.out.println("Este es un hilo virtual ejecutando una tarea.");
        });

        hilo.join(); // Esperar a que el hilo termine
    }
}
```
Explicación:

Los hilos virtuales son ligeros y rápidos. Puedes crear muchos de ellos para hacer tareas pequeñas y rápidas sin usar muchos recursos. ¡Es como tener un ejército de ayudantes diminutos que hacen tu trabajo muy rápidamente!

## Scoped Values (Valores con Alcance)
**¿Qué es?**
Imagina que tienes una caja mágica donde puedes guardar algo especial mientras haces una tarea, y solo puedes acceder a lo que hay dentro de la caja mientras estás haciendo esa tarea específica. Los valores con alcance funcionan de manera similar, proporcionando datos que solo son accesibles durante una operación particular.

Ejemplo:

```java
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;

public class Main {
// Un valor con alcance
private static final ScopedValue<Integer> CONTEXTO_ESPECIAL = ScopedValue.newInstance();

    public static void main(String[] args) {
        try (var scope = CONTEXTO_ESPECIAL.setWhere(123)) {
            ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
            executor.submit((Callable<Void>) () -> {
                System.out.println("El valor especial es: " + CONTEXTO_ESPECIAL.get());
                return null;
            }).join();
        }
    }
}
```
Explicación:

Valores con Alcance: Son como una caja especial que puedes usar para guardar algo solo mientras haces una tarea. Aquí, CONTEXTO_ESPECIAL es un valor que está disponible solo en una sección particular del código. ¡Es como si estuvieras usando una caja de secretos que desaparece después de usarla!

