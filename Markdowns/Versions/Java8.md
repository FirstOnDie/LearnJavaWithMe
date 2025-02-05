# **📌 Java 8 – La Revolución en el Mundo de Java** 🚀☕

📌 **Java 8** fue una de las versiones **más importantes y revolucionarias** en la historia de Java. Introdujo características modernas que hicieron que el lenguaje fuera más **conciso, funcional y eficiente**.

✅ **Principales Novedades:**  
✔ **Lambdas (Funciones Lambda)** → Código más limpio y funcional.  
✔ **Stream API** → Procesamiento de datos de forma declarativa y eficiente.  
✔ **Date and Time API** → Manejo de fechas y horas más sencillo y robusto.  
✔ **Interfaces funcionales y `java.util.function`** → Programación funcional en Java.  
✔ **Default Methods en Interfaces** → Métodos con implementación en interfaces.  
✔ **`Optional<T>`** → Manejo seguro de valores nulos.  
✔ **Mejoras en Concurrencia (`CompletableFuture`)** → Programación asíncrona más eficiente.  
✔ **Nashorn (JavaScript Engine)** → Integración con JavaScript (Eliminado en Java 15).

---

# **📌 1️⃣ Expresiones Lambda (`->`)** 🎯

📌 **¿Qué son?**  
Las **expresiones lambda** permiten escribir funciones anónimas de manera concisa. Antes de Java 8, para definir un comportamiento, era necesario crear **clases anónimas** o interfaces, lo que generaba código repetitivo.

📌 **Ejemplo: Código antes de Java 8**
```java
Runnable tarea = new Runnable() {
    @Override
    public void run() {
        System.out.println("Ejecutando tarea...");
    }
};
```
📌 **Ejemplo con Lambdas en Java 8**
```java
Runnable tarea = () -> System.out.println("Ejecutando tarea...");
```
✅ **Ventajas:**  
✔ **Menos código y más claridad**.  
✔ **Ideal para interfaces funcionales** (`Runnable`, `Comparator`, `Predicate`, etc.).  
✔ **Se combina con Streams y programación funcional**.

📌 **Ejemplo con `Comparator`** (Ordenando una lista de números)
```java
List<Integer> numeros = Arrays.asList(5, 2, 8, 1);
numeros.sort((a, b) -> a - b);
System.out.println(numeros); // [1, 2, 5, 8]
```

📌 **Ejemplo con `Predicate` (Filtrar palabras largas)**
```java
import java.util.function.Predicate;

Predicate<String> esLargo = palabra -> palabra.length() > 5;

System.out.println(esLargo.test("Hola"));      // false
System.out.println(esLargo.test("Programación")); // true
```

---

# **📌 2️⃣ Stream API (Procesamiento Declarativo de Datos)** 🌊

📌 **¿Qué son?**  
Los **Streams** permiten **procesar datos** de manera **declarativa y funcional** en lugar de usar bucles tradicionales.

📌 **Ejemplo sin Streams (Java 7)**
```java
List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "Carlos");
List<String> nombresFiltrados = new ArrayList<>();

for (String nombre : nombres) {
    if (nombre.startsWith("J")) {
        nombresFiltrados.add(nombre);
    }
}

for (String nombre : nombresFiltrados) {
    System.out.println(nombre);
}
```
📌 **Ejemplo con Streams (Java 8)**
```java
List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "Carlos");

nombres.stream()
    .filter(n -> n.startsWith("J"))
    .forEach(System.out::println); // Imprime "Juan"
```
✅ **Ventajas:**  
✔ **Más conciso y legible**.  
✔ **Operaciones encadenadas (`filter`, `map`, `sorted`, etc.)**.  
✔ **Optimización automática (Lazy Evaluation)**.  
✔ **Paralelismo con `parallelStream()`**.

📌 **Ejemplo: Mapear y ordenar una lista de números**
```java
List<Integer> numeros = Arrays.asList(3, 1, 4, 1, 5, 9);

List<Integer> cuadradosOrdenados = numeros.stream()
    .map(n -> n * n)  // Elevar al cuadrado
    .sorted()         // Ordenar
    .collect(Collectors.toList());

System.out.println(cuadradosOrdenados); // [1, 1, 9, 16, 25, 81]
```

📌 **Ejemplo: Sumar elementos con `reduce()`**
```java
int suma = numeros.stream().reduce(0, Integer::sum);
System.out.println(suma); // 23
```

---

# **📌 3️⃣ Nueva API de Fechas y Horas (`java.time` ⏳)**

📌 **¿Qué problema soluciona?**  
Antes de Java 8, `Date` y `Calendar` eran **complejos y propensos a errores**. Java 8 introduce **`LocalDate`, `LocalTime`, `LocalDateTime` y `ZonedDateTime`**, que son **inmutables, fáciles de usar y thread-safe**.

📌 **Ejemplo: Obtener la fecha y hora actual**
```java
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

LocalDate fecha = LocalDate.now();
LocalTime hora = LocalTime.now();
LocalDateTime fechaHora = LocalDateTime.now();

System.out.println("Fecha: " + fecha);
System.out.println("Hora: " + hora);
System.out.println("Fecha y Hora: " + fechaHora);
```

📌 **Ejemplo: Sumar días y restar horas**
```java
LocalDate fecha = LocalDate.of(2023, 5, 20);
LocalDate nuevaFecha = fecha.plusDays(5); // Agrega 5 días
System.out.println(nuevaFecha); // 2023-05-25
```

📌 **Ejemplo: Formatear fechas**
```java
import java.time.format.DateTimeFormatter;

DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
System.out.println(LocalDate.now().format(formato)); // 06/02/2024
```

---

# **📌 4️⃣ `Optional<T>` – Evitar `NullPointerException`** 🚨

📌 **¿Qué problema soluciona?**  
Antes de Java 8, si una función podía devolver `null`, teníamos que usar múltiples verificaciones para evitar errores:
```java
if (objeto != null) {
    return objeto.getValor();
}
```
📌 **Java 8 introduce `Optional<T>` para manejar valores nulos de manera segura.**
```java
Optional<String> nombre = Optional.ofNullable(null);

System.out.println(nombre.orElse("Valor por defecto")); // Valor por defecto
```

📌 **Ejemplo con `Optional` en una consulta de base de datos:**
```java
Optional<Usuario> usuario = buscarUsuarioPorId(1);
usuario.ifPresent(u -> System.out.println(u.getNombre()));
```

---

# **📌 5️⃣ Otras Mejoras en Java 8**

📌 **`default` methods en interfaces**
```java
interface Vehiculo {
    default void arrancar() {
        System.out.println("El vehículo está arrancando...");
    }
}

class Coche implements Vehiculo {}

public class Main {
    public static void main(String[] args) {
        Coche miCoche = new Coche();
        miCoche.arrancar(); // El vehículo está arrancando...
    }
}
```
📌 **`CompletableFuture` (Concurrencia mejorada)**
```java
CompletableFuture.supplyAsync(() -> "Hola mundo")
    .thenAccept(System.out::println);
```

---
