# **📌 Java 21 – Innovación y Eficiencia** 🚀☕

📌 **Java 21** ha traído **mejoras clave** en el lenguaje, enfocándose en **rendimiento, seguridad y productividad**. Esta versión introduce **nuevas características** que hacen que el código sea **más expresivo, conciso y eficiente**.

✅ **Principales Novedades:**  
✔ **String Templates** → Inserción de valores en Strings de forma más sencilla.  
✔ **Sequenced Collections** → Mantenimiento del orden en colecciones de datos.  
✔ **Pattern Matching for Switch** → `switch` más potente con coincidencia de patrones.  
✔ **Record Patterns** → Extrae datos de registros de forma más intuitiva.  
✔ **Virtual Threads** → Hilos ligeros para concurrencia a gran escala.  
✔ **Scoped Values** → Variables con alcance específico para mayor seguridad y eficiencia.

---

# **📌 1️⃣ String Templates (Plantillas de Cadenas)** 📝

📌 **¿Qué problema soluciona?**  
Antes de Java 21, para construir cadenas con valores dinámicos, **teníamos que usar concatenación o `String.format()`**, lo cual podía ser tedioso.

📌 **Ejemplo antes de Java 21**
```java
String nombre = "Carlos";
int edad = 10;
String mensaje = "Hola, mi nombre es " + nombre + " y tengo " + edad + " años.";
System.out.println(mensaje);
```
📌 **Ejemplo con `String Templates` en Java 21**
```java
String mensaje = STR."Hola, mi nombre es \{nombre} y tengo \{edad} años.";
System.out.println(mensaje); // "Hola, mi nombre es Carlos y tengo 10 años."
```
✅ **Ventajas:**  
✔ **Código más limpio y legible.**  
✔ **Evita concatenaciones repetitivas.**  
✔ **Mejora el rendimiento y evita errores de formato.**

📌 **Ejemplo con cálculos dentro de la plantilla**
```java
int a = 5, b = 10;
String resultado = STR."La suma de \{a} y \{b} es \{a + b}.";
System.out.println(resultado); // "La suma de 5 y 10 es 15."
```

---

# **📌 2️⃣ Sequenced Collections (Colecciones Secuenciadas)** 📂

📌 **¿Qué problema soluciona?**  
Antes, las colecciones en Java **no garantizaban orden explícito en la mayoría de los casos**. Ahora, con **Sequenced Collections**, podemos **mantener, modificar y recuperar elementos de forma ordenada**.

📌 **Ejemplo con `SequencedCollection` en Java 21**
```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        SequencedCollection<String> animales = new ArrayList<>(List.of("León", "Tigre", "Elefante"));

        System.out.println(animales.getFirst()); // "León"
        System.out.println(animales.getLast());  // "Elefante"
        
        animales.addFirst("Mono");
        animales.addLast("Cebra");

        System.out.println(animales); // [Mono, León, Tigre, Elefante, Cebra]
    }
}
```
✅ **Ventajas:**  
✔ **Acceso rápido al primer y último elemento.**  
✔ **Facilita la manipulación de colecciones ordenadas.**  
✔ **Mejor rendimiento en estructuras de datos.**

📌 **Ejemplo con `SequencedMap`**
```java
SequencedMap<String, Integer> edades = new LinkedHashMap<>();
edades.put("Ana", 30);
edades.put("Luis", 25);
System.out.println(edades.firstEntry()); // {Ana=30}
System.out.println(edades.lastEntry());  // {Luis=25}
```

---

# **📌 3️⃣ Pattern Matching for Switch (Coincidencia de Patrones en `switch`)** 🔄

📌 **¿Qué problema soluciona?**  
Antes, teníamos que hacer **múltiples verificaciones `instanceof` y conversiones manuales**. Ahora, `switch` puede detectar **automáticamente el tipo** y ejecutar código específico.

📌 **Ejemplo antes de Java 21**
```java
Object objeto = "Hola";

if (objeto instanceof String) {
    String s = (String) objeto; // ❌ Casting manual
    System.out.println("Es un texto: " + s);
} else if (objeto instanceof Integer) {
    Integer i = (Integer) objeto;
    System.out.println("Es un número: " + i);
}
```
📌 **Ejemplo con `switch` en Java 21**
```java
Object objeto = "Hola";

switch (objeto) {
    case String s -> System.out.println("Es un texto: " + s);
    case Integer i -> System.out.println("Es un número: " + i);
    default -> System.out.println("Tipo desconocido");
}
```
✅ **Ventajas:**  
✔ **Código más limpio y sin casting manual.**  
✔ **Mejora la seguridad y la legibilidad.**  
✔ **Mayor flexibilidad en `switch`.**

📌 **Ejemplo con `null` en `switch`**
```java
switch (objeto) {
    case null -> System.out.println("El valor es nulo.");
    case String s -> System.out.println("Cadena: " + s);
    default -> System.out.println("Otro tipo.");
}
```

---

# **📌 4️⃣ Record Patterns (Patrones de Registro)** 🎯

📌 **¿Qué problema soluciona?**  
Antes, acceder a los valores de un `record` requería **múltiples llamadas a métodos**. Ahora, **Java 21 permite extraer los valores de forma más intuitiva**.

📌 **Ejemplo sin Record Patterns**
```java
record Persona(String nombre, int edad) {}

Persona persona = new Persona("Carlos", 10);
System.out.println(persona.nombre() + " tiene " + persona.edad() + " años.");
```
📌 **Ejemplo con Record Patterns en Java 21**
```java
Persona persona = new Persona("Carlos", 10);

if (persona instanceof Persona(String nombre, int edad)) {
    System.out.println(nombre + " tiene " + edad + " años.");
}
```
✅ **Ventajas:**  
✔ **Código más limpio y sin llamadas repetitivas.**  
✔ **Facilita el acceso a los valores de un `record`.**  
✔ **Mejora la legibilidad y seguridad.**

---

# **📌 5️⃣ Virtual Threads (Hilos Virtuales)** 🧵

📌 **¿Qué problema soluciona?**  
Antes, crear **miles de hilos** en Java era **costoso en memoria y rendimiento**. Ahora, los **hilos virtuales permiten crear millones de tareas concurrentes sin problemas**.

📌 **Ejemplo con `Thread.ofVirtual()` en Java 21**
```java
Thread hilo = Thread.ofVirtual().start(() -> {
    System.out.println("Ejecutando tarea en un hilo virtual.");
});

hilo.join(); // Esperar a que termine
```
✅ **Ventajas:**  
✔ **Creación masiva de hilos sin sobrecargar la CPU.**  
✔ **Mejor rendimiento en aplicaciones concurrentes.**  
✔ **Ideal para servidores de alto rendimiento.**

📌 **Ejemplo con `ExecutorService` y hilos virtuales**
```java
ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

executor.submit(() -> System.out.println("Tarea concurrente en hilo virtual."));
executor.shutdown();
```

---

# **📌 6️⃣ Scoped Values (Valores con Alcance)** 🎭

📌 **¿Qué problema soluciona?**  
Antes, compartir valores entre hilos requería **variables estáticas o `ThreadLocal`**, lo cual podía ser **poco eficiente y propenso a errores**.

📌 **Ejemplo con `ScopedValue` en Java 21**
```java
import java.util.concurrent.Executors;

private static final ScopedValue<String> CONTEXTO = ScopedValue.newInstance();

public class Main {
    public static void main(String[] args) {
        try (var scope = CONTEXTO.setWhere("Valor Especial")) {
            Executors.newVirtualThreadPerTaskExecutor().submit(() ->
                System.out.println("El valor especial es: " + CONTEXTO.get())
            ).join();
        }
    }
}
```
✅ **Ventajas:**  
✔ **Mayor seguridad en el manejo de datos compartidos.**  
✔ **Evita problemas de concurrencia.**  
✔ **Mejor gestión de variables con contexto limitado.**

---
