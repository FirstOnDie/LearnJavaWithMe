# **📌 Preguntas y Respuestas para Entrevista Técnica – Versiones LTS de Java**

### ❓ **Pregunta:** ¿Cuáles fueron las principales novedades de Java 8?
✅ **Respuesta:**  
Java 8 introdujo **programación funcional** y mejoras en la API de colecciones y fechas.

📌 **Principales cambios:**  
✔ **Lambdas y Functional Interfaces** – Permiten escribir código más conciso.  
✔ **Streams API** – Facilita el procesamiento de colecciones de datos.  
✔ **Nueva API de Fechas (`java.time`)** – Reemplaza `Date` y `Calendar`.  
✔ **Default Methods en Interfaces** – Permiten métodos con implementación en interfaces.

📌 **Ejemplo de una **expresión Lambda** con `Streams`:**
```java
List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro");
nombres.stream()
    .filter(n -> n.startsWith("J"))
    .forEach(System.out::println); // Imprime: Juan
```

---

### ❓ **Pregunta:** ¿Cómo funciona la nueva API de Fechas en Java 8?
✅ **Respuesta:**  
La API `java.time` reemplaza a `Date` y `Calendar`, haciendo más fácil manipular fechas.

📌 **Ejemplo:**
```java
LocalDate fecha = LocalDate.now();
LocalDate nacimiento = LocalDate.of(1995, Month.JUNE, 15);
Period edad = Period.between(nacimiento, fecha);
System.out.println("Tienes " + edad.getYears() + " años.");
```

✅ **Ventajas:**  
✔ **Inmutable y más segura**  
✔ **Mejor soporte para zonas horarias**

---

### ❓ **Pregunta:** ¿Qué son los **default methods** en interfaces?
✅ **Respuesta:**  
Permiten agregar métodos con implementación en interfaces sin romper clases existentes.

📌 **Ejemplo:**
```java
interface Vehiculo {
    default void arrancar() {
        System.out.println("El vehículo está en marcha.");
    }
}
```

✅ **Esto permite ampliar interfaces sin afectar clases antiguas.**

---

### ❓ **Pregunta:** ¿Cuáles fueron las principales mejoras en Java 11?
✅ **Respuesta:**  
Java 11 optimizó el lenguaje y agregó nuevas características como:

✔ **Nuevo `HttpClient`** – Manejo de peticiones HTTP de manera más sencilla.  
✔ **Ejecutar código Java sin compilar (`java archivo.java`)**.  
✔ **Soporte para `var` en parámetros de lambda**.

📌 **Ejemplo del nuevo `HttpClient`:**
```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://example.com"))
    .GET()
    .build();

client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    .thenApply(HttpResponse::body)
    .thenAccept(System.out::println);
```

✅ **Ventajas:**  
✔ Más fácil de usar que `HttpURLConnection`.  
✔ Soporta `async` y HTTP/2.

---

### ❓ **Pregunta:** ¿Cómo se ejecuta un archivo Java sin compilar en Java 11?
✅ **Respuesta:**  
Con `java archivo.java`, sin necesidad de `javac`.

📌 **Ejemplo:**
```bash
java MiArchivo.java
```
✅ **Ideal para scripts o pruebas rápidas.**

---

### ❓ **Pregunta:** ¿Cómo se usa `var` en parámetros de expresiones lambda en Java 11?
✅ **Respuesta:**  
Permite inferencia de tipos en lambdas.

📌 **Ejemplo:**
```java
BiFunction<Integer, Integer, Integer> suma = (var x, var y) -> x + y;
System.out.println(suma.apply(5, 10)); // 15
```

✅ **Hace el código más legible sin perder tipado fuerte.**

---

### ❓ **Pregunta:** ¿Cuáles son las novedades principales de Java 17?
✅ **Respuesta:**  
✔ **Clases Selladas (`sealed`)** – Controla qué clases pueden extender otra.  
✔ **Pattern Matching en `switch`** – Reduce el código en comparaciones.  
✔ **Registros (`records`)** – Facilitan la creación de clases de solo datos.

---

### ❓ **Pregunta:** ¿Cómo funcionan las **clases selladas** en Java 17?
✅ **Respuesta:**  
Permiten restringir qué clases pueden heredar de una clase padre.

📌 **Ejemplo:**
```java
sealed class Vehiculo permits Coche, Moto {}

final class Coche extends Vehiculo {}
final class Moto extends Vehiculo {}
```
✅ **Controla la jerarquía y mejora la seguridad.**

---

### ❓ **Pregunta:** ¿Cómo se usa el **Pattern Matching en `switch`** en Java 17?
✅ **Respuesta:**  
Permite evaluar **tipos de objetos** en un `switch`.

📌 **Ejemplo:**
```java
Object obj = "Hola";

switch (obj) {
    case String s -> System.out.println("Es una cadena: " + s);
    case Integer i -> System.out.println("Es un entero: " + i);
    default -> System.out.println("Tipo desconocido");
}
```
✅ **Reduce código repetitivo y mejora la claridad.**

---

### ❓ **Pregunta:** ¿Cómo funcionan los **records** en Java 17?
✅ **Respuesta:**  
Son clases inmutables diseñadas para **almacenar datos** sin código repetitivo.

📌 **Ejemplo:**
```java
record Persona(String nombre, int edad) {}

Persona p = new Persona("Ana", 25);
System.out.println(p.nombre()); // Ana
```
✅ **Menos código y más eficiencia en DTOs.**

---

### ❓ **Pregunta:** ¿Cuáles son las mejoras clave en Java 21?
✅ **Respuesta:**  
✔ **Threads Virtuales (`virtual threads`)** – Permiten concurrencia eficiente.  
✔ **Scoped Values** – Mejora la gestión de variables en entornos multi-hilo.  
✔ **Pattern Matching mejorado** – Mayor simplicidad en validaciones.

---

### ❓ **Pregunta:** ¿Cómo funcionan los **Threads Virtuales** en Java 21?
✅ **Respuesta:**  
Permiten manejar **millones de hilos concurrentes** con menos recursos.

📌 **Ejemplo:**
```java
Thread.startVirtualThread(() -> {
    System.out.println("Ejecutando en un hilo virtual");
});
```
✅ **Mejor rendimiento en aplicaciones concurrentes.**

---

### ❓ **Pregunta:** ¿Cómo se usan los **Scoped Values** en Java 21?
✅ **Respuesta:**  
Permiten definir variables de solo lectura **con alcance seguro en hilos concurrentes**.

📌 **Ejemplo:**
```java
ScopedValue<String> contexto = ScopedValue.newInstance();
try (var scope = contexto.setWhere("Valor especial")) {
    System.out.println("Contexto: " + contexto.get());
}
```
✅ **Útil en aplicaciones multi-hilo con alto tráfico.**

---

