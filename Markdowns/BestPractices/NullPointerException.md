# **📌 NullPointerException en Java** 🚨

📌 **Un `NullPointerException` (NPE)** ocurre cuando intentamos acceder a un objeto que **no ha sido inicializado** (es `null`).

Imagina que tienes una caja de juguetes y algunos están **rotos o faltan piezas**. Si intentas jugar con un juguete roto, **no funcionará** y podrías lastimarte.

➡ **En Java, `NullPointerException` es como intentar jugar con un juguete roto:** el programa **se detiene** porque algo no está en su lugar.

✅ **Buenas prácticas para evitar `NullPointerException`:**  
✔ **Verificar si un objeto es `null` antes de usarlo.**  
✔ **Usar `Optional` para manejar valores que pueden ser `null`.**  
✔ **Utilizar `Objects.requireNonNull()` cuando sea necesario.**  
✔ **Evitar comparar `null` con `equals()` en variables inseguras.**

---

## **📌 1️⃣ Comprobar antes de usar (`null check`)** ✅

📌 **Ejemplo correcto:** Verificar si un objeto es `null` antes de utilizarlo.
```java
String nombre = null;

if (nombre != null) {
    System.out.println("Longitud del nombre: " + nombre.length());
} else {
    System.out.println("⚠️ El nombre es nulo, no se puede calcular la longitud.");
}
```
✅ **Ventajas:**  
✔ Evita el `NullPointerException`.  
✔ Permite manejar los valores nulos correctamente.

❌ **Ejemplo incorrecto:** No comprobar si el objeto es `null` antes de usarlo.
```java
String nombre = null;
System.out.println(nombre.length()); // ❌ ¡Error! NullPointerException
```
➡ **Problema:** Se intenta llamar a `length()` en `null`, causando un error.

---

## **📌 2️⃣ Usar `Objects.requireNonNull()` para validaciones** 🔍

📌 **Ejemplo correcto:**
```java
import java.util.Objects;

public class Usuario {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = Objects.requireNonNull(nombre, "⚠️ El nombre no puede ser nulo");
    }
}
```
✅ **Ventajas:**  
✔ Evita la creación de objetos inválidos.  
✔ Lanza una excepción clara si el valor es `null`.

---

## **📌 3️⃣ Comparar valores conocidos (`"test".equals(str)`)** ✔

📌 **Ejemplo correcto:**
```java
String str = null;

if ("test".equals(str)) {
    System.out.println("✅ La cadena es igual a 'test'.");
} else {
    System.out.println("⚠️ La cadena es nula o diferente.");
}
```
✅ **Ventajas:**  
✔ Evita el `NullPointerException`.  
✔ Se asegura de que `equals()` nunca se llame en un valor `null`.

❌ **Ejemplo incorrecto:** Llamar `equals()` en una variable que puede ser `null`.
```java
String str = null;

if (str.equals("test")) { // ❌ ¡Error! NullPointerException
    System.out.println("✅ La cadena es igual a 'test'.");
}
```
➡ **Problema:** `str` es `null`, por lo que `equals()` no puede ejecutarse.

---

## **📌 4️⃣ Usar `Optional` para manejar valores nulos de forma segura** ☂️

📌 **Ejemplo correcto con `Optional`:**
```java
import java.util.Optional;

public class EjemploOptional {
    public static void main(String[] args) {
        String str = null;
        Optional<String> optionalStr = Optional.ofNullable(str);

        optionalStr.ifPresent(s -> System.out.println("✅ Longitud: " + s.length()));
    }
}
```
✅ **Ventajas:**  
✔ **Evita el uso de `null` directamente.**  
✔ **Hace el código más seguro y legible.**

❌ **Ejemplo incorrecto:** Usar `null` sin `Optional`.
```java
String str = null;
System.out.println(str.length()); // ❌ ¡Error! NullPointerException
```
➡ **Problema:** `str` es `null`, por lo que `length()` no puede ejecutarse.

---

## **📌 5️⃣ Evitar `null` en Streams y Colecciones** 🚀

📌 **Ejemplo correcto:** Usar `Optional` o `filter()` para evitar `null`.
```java
import java.util.List;

public class StreamsEjemplo {
    public static void main(String[] args) {
        List<String> nombres = List.of("Ana", "Pedro", null, "Luis");

        nombres.stream()
                .filter(nombre -> nombre != null) // Evita NullPointerException
                .forEach(System.out::println);
    }
}
```
✅ **Ventajas:**  
✔ Evita que los `null` causen errores en Streams.  
✔ Hace el código más seguro y fácil de entender.

❌ **Ejemplo incorrecto:** No filtrar `null` en Streams.
```java
nombres.stream().map(String::length).forEach(System.out::println); // ❌ ¡Error!
```
➡ **Problema:** Si la lista contiene `null`, `String::length` generará un `NullPointerException`.

---

## **📌 6️⃣ Usar `String.valueOf()` para evitar `null` en conversiones** 🔄

📌 **Ejemplo correcto:**
```java
Integer numero = null;
System.out.println(String.valueOf(numero)); // ✅ Imprime "null" sin error.
```
✅ **Ventajas:**  
✔ Convierte valores nulos en `"null"` en lugar de lanzar una excepción.

❌ **Ejemplo incorrecto:**
```java
Integer numero = null;
System.out.println(numero.toString()); // ❌ ¡Error! NullPointerException
```
➡ **Problema:** `numero` es `null`, por lo que `toString()` no puede ejecutarse.

---

## **📌 7️⃣ Evitar devolver `null`, usar valores por defecto** 🚀

📌 **Ejemplo correcto:**
```java
public String obtenerNombreSeguro(String nombre) {
    return nombre != null ? nombre : "Desconocido";
}
```
✅ **Ventajas:**  
✔ Retorna un valor seguro en lugar de `null`.

❌ **Ejemplo incorrecto:**
```java
public String obtenerNombre(String nombre) {
    return nombre; // ❌ Puede retornar null
}
```
➡ **Problema:** Si `nombre` es `null`, cualquier uso posterior podría causar un `NullPointerException`.

---