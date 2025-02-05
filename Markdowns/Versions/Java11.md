# **📌 Java 11 – Evolución y Simplicidad** 🚀☕

📌 **Java 11** trajo consigo mejoras clave en rendimiento, productividad y mantenimiento del código. Esta versión se enfocó en **simplificar el desarrollo**, optimizar la **concurrencia** y mejorar la **ejecutabilidad de Java**.

✅ **Principales Novedades:**  
✔ **Nuevo `HttpClient` API** → Manejo de solicitudes HTTP más moderno y fácil.  
✔ **Ejecución Directa de Archivos `.java`** → Sin necesidad de compilación previa.  
✔ **Inferencia de Tipo con `var`** → Código más limpio y conciso.  
✔ **Mejoras en Strings (`isBlank()`, `strip()`, `lines()`)** → Operaciones más eficientes.  
✔ **Colecciones Inmutables (`List.of()`, `Map.of()`)** → Creación rápida de estructuras de datos.  
✔ **E/S Mejorada con `Files.writeString()` y `Files.readString()`** → Más simple trabajar con archivos.  
✔ **Deprecación de Java EE y CORBA** → Limpieza y modernización del JDK.

---

# **📌 1️⃣ Nuevo `HttpClient` API 🌐**

📌 **¿Qué problema soluciona?**  
Antes de Java 11, las solicitudes HTTP se realizaban con **`HttpURLConnection`**, lo cual era **verboso, poco intuitivo y difícil de manejar** en aplicaciones modernas.

📌 **Ejemplo antes de Java 11 (`HttpURLConnection`)**
```java
URL url = new URL("https://example.com");
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("GET");

BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
String response = reader.lines().collect(Collectors.joining());
reader.close();

System.out.println(response);
```
❌ **Código complejo y difícil de leer.**

📌 **Ejemplo con `HttpClient` en Java 11**
```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpExample {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
```
✅ **Ventajas:**  
✔ **Menos código y más claridad.**  
✔ **Soporta `GET`, `POST`, `PUT`, `DELETE` fácilmente.**  
✔ **Manejo de `Async` con `.sendAsync()`.**  
✔ **Soporta HTTP/2 para mejor rendimiento.**

📌 **Ejemplo con `sendAsync()` para peticiones asíncronas**
```java
client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    .thenApply(HttpResponse::body)
    .thenAccept(System.out::println);
```

---

# **📌 2️⃣ Ejecución Directa de Archivos `.java` 🚀**

📌 **¿Qué problema soluciona?**  
Antes, para ejecutar un archivo Java era necesario **compilarlo** primero (`javac HolaMundo.java`) y luego ejecutarlo (`java HolaMundo`).

📌 **Ahora en Java 11, puedes ejecutar archivos `.java` directamente:**
```bash
java HolaMundo.java
```

📌 **Ejemplo de código en `HolaMundo.java`**
```java
public class HolaMundo {
    public static void main(String[] args) {
        System.out.println("¡Hola, Java 11!");
    }
}
```
✅ **Ventajas:**  
✔ Ideal para **scripts rápidos** sin necesidad de crear proyectos grandes.  
✔ Facilita **pruebas y aprendizaje** en Java.

---

# **📌 3️⃣ Inferencia de Tipo con `var`** 📌

📌 **¿Qué problema soluciona?**  
Antes de Java 11, era obligatorio **especificar los tipos de datos** al declarar variables, lo que podía hacer el código más verboso.

📌 **Ejemplo antes de Java 11**
```java
String mensaje = "Hola, Mundo";
int numero = 42;
List<String> nombres = new ArrayList<>();
```

📌 **Ejemplo con `var` en Java 11**
```java
var mensaje = "Hola, Mundo";  // Java infiere que es un String
var numero = 42;              // Java infiere que es un int
var nombres = List.of("Ana", "Pedro", "Juan"); // Java infiere List<String>
```
✅ **Ventajas:**  
✔ **Menos código y más claridad**.  
✔ **Código más limpio y conciso**.  
✔ **Java sigue siendo fuertemente tipado** (no es como JavaScript).

❌ **Limitaciones:**  
🚫 `var` **no se puede usar en atributos de clase ni en parámetros de métodos.**

---

# **📌 4️⃣ Nuevos Métodos en `String`** 🔤

📌 **¿Qué problema soluciona?**  
Antes, trabajar con Strings requería múltiples validaciones manuales. Java 11 agrega métodos más eficientes.

📌 **`isBlank()` – Verifica si una cadena está vacía o solo tiene espacios**
```java
System.out.println("  ".isBlank()); // true
```

📌 **`strip()` – Elimina espacios en blanco mejor que `trim()`**
```java
System.out.println("  Hola  ".strip()); // "Hola"
System.out.println("  Hola  ".trim());  // "Hola"
```

📌 **`lines()` – Divide un String en líneas**
```java
String texto = "Hola\nMundo\nJava";
texto.lines().forEach(System.out::println);
```

📌 **`repeat(n)` – Repite un String `n` veces**
```java
System.out.println("Java ".repeat(3)); // "Java Java Java"
```

---

# **📌 5️⃣ Creación de Colecciones Inmutables (`List.of()`, `Set.of()`, `Map.of()`)**

📌 **Ejemplo antes de Java 11**
```java
List<String> lista = new ArrayList<>();
lista.add("Java");
lista.add("Python");
lista.add("C++");
```

📌 **Ejemplo en Java 11 (`List.of()`)**
```java
List<String> lista = List.of("Java", "Python", "C++");
```

📌 **Ejemplo en Java 11 (`Map.of()`)**
```java
Map<String, Integer> edades = Map.of("Ana", 25, "Pedro", 30);
```
✅ **Ventajas:**  
✔ **Menos código y más eficiencia.**  
✔ **Estructuras inmutables (no modificables).**

---

# **📌 6️⃣ Mejoras en Manejo de Archivos (`Files.writeString()` y `Files.readString()`)** 📂

📌 **Antes de Java 11, escribir y leer archivos era complejo:**
```java
Files.write(Paths.get("archivo.txt"), "Contenido".getBytes());
String contenido = new String(Files.readAllBytes(Paths.get("archivo.txt")));
```

📌 **Ahora en Java 11, es más fácil:**
```java
import java.nio.file.Files;
import java.nio.file.Path;

Files.writeString(Path.of("archivo.txt"), "Hola, Java 11!");
String contenido = Files.readString(Path.of("archivo.txt"));

System.out.println(contenido); // Hola, Java 11!
```

✅ **Ventajas:**  
✔ **Código más limpio y moderno.**  
✔ **Menos excepciones y mejor manejo de errores.**

---