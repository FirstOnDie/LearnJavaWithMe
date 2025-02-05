# **📌 Java 17 – Innovación y Seguridad** 🚀☕

📌 **Java 17** es la versión **LTS (Long-Term Support) más reciente**, lo que significa que es una versión **estable** y **recomendada para producción**. Introdujo características clave que hacen que el código sea **más seguro, conciso y fácil de mantener**.

✅ **Principales Novedades:**  
✔ **Sealed Classes (Clases Selladas)** → Controla quién puede heredar de una clase.  
✔ **Pattern Matching en `switch`** → Manejo más elegante de tipos en `switch`.  
✔ **Records (Registros)** → Clases de solo datos sin código innecesario.  
✔ **Text Blocks** → Manejo mejorado de texto multilínea.  
✔ **Depuración de `NullPointerException`** → Mensajes más claros al depurar errores.  
✔ **Remoción de APIs obsoletas** → Mayor seguridad y limpieza del JDK.

---

# **📌 1️⃣ Sealed Classes (Clases Selladas)** 🔒

📌 **¿Qué problema soluciona?**  
Antes de Java 17, cualquier clase podía ser **extendida sin restricciones**, lo que podía generar **subclases no controladas**. Ahora, con **clases selladas**, puedes **limitar** qué clases pueden heredar de una clase base.

📌 **Ejemplo sin clases selladas** (Java 16 y anteriores)
```java
public class Vehiculo {} 
public class Coche extends Vehiculo {}
public class Bicicleta extends Vehiculo {}
public class Camion extends Vehiculo {} // ❌ No tenemos control sobre qué hereda de Vehiculo
```
📌 **Ejemplo con `sealed` en Java 17**
```java
public sealed class Vehiculo permits Coche, Bicicleta {}

public final class Coche extends Vehiculo {}     // ✅ Permitido
public final class Bicicleta extends Vehiculo {} // ✅ Permitido
public class Camion extends Vehiculo {}         // ❌ Error: No está permitido
```
✅ **Ventajas:**  
✔ **Mayor seguridad y control** sobre la jerarquía de clases.  
✔ **Mejora el diseño del código y evita herencias no deseadas.**  
✔ **Facilita el mantenimiento y optimización del código.**

📌 **Tipos de subclases permitidas en `sealed`**  
| Tipo | Descripción |
|------|------------|
| `final` | La subclase no puede ser heredada. |
| `sealed` | Puede ser heredada, pero solo por clases específicas. |
| `non-sealed` | Permite herencia libremente. |

📌 **Ejemplo con `non-sealed` (Permitir herencia abierta)**
```java
public sealed class Animal permits Perro, Gato, Pez {}

public final class Perro extends Animal {}  // No se puede heredar más
public final class Gato extends Animal {}   // No se puede heredar más
public non-sealed class Pez extends Animal {} // ✅ Cualquier clase puede heredar de Pez
```

---

# **📌 2️⃣ Pattern Matching en `switch` (Coincidencia de Patrones)** 🔄

📌 **¿Qué problema soluciona?**  
Antes, cuando usábamos `switch`, **necesitábamos hacer múltiples `instanceof`** manualmente. Ahora, **Java 17 permite evaluar tipos directamente dentro del `switch`**, reduciendo código innecesario.

📌 **Ejemplo antes de Java 17**
```java
Object obj = "Hola";

if (obj instanceof String) {
    String s = (String) obj; // ❌ Se necesita hacer casting manual
    System.out.println("Es una cadena: " + s);
} else if (obj instanceof Integer) {
    Integer i = (Integer) obj;
    System.out.println("Es un número entero: " + i);
}
```
📌 **Ejemplo con `switch` en Java 17**
```java
Object obj = "Hola";

switch (obj) {
    case String s -> System.out.println("Es una cadena: " + s);
    case Integer i -> System.out.println("Es un número entero: " + i);
    default -> System.out.println("Tipo desconocido");
}
```
✅ **Ventajas:**  
✔ **Más conciso y fácil de leer.**  
✔ **Elimina `instanceof` y `casting` manual.**  
✔ **Mejora el rendimiento y seguridad del código.**

📌 **Ejemplo con `null` en `switch`**
```java
switch (obj) {
    case null -> System.out.println("Es nulo"); // ✅ Ahora `null` se maneja correctamente
    case String s -> System.out.println("Cadena: " + s);
    default -> System.out.println("Otro tipo");
}
```

---

# **📌 3️⃣ Records (Registros) 📝**

📌 **¿Qué problema soluciona?**  
Antes, para representar una clase con solo datos (`POJO` o `DTO`), **necesitábamos escribir mucho código repetitivo** (`getters`, `toString()`, `equals()`, `hashCode()`).

📌 **Ejemplo antes de Java 17 (Código Verboso)**
```java
public class Persona {
    private final String nombre;
    private final int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    @Override
    public String toString() {
        return "Persona[nombre=" + nombre + ", edad=" + edad + "]";
    }
}
```

📌 **Ejemplo con `record` en Java 17**
```java
public record Persona(String nombre, int edad) {}
```
✅ **Ventajas:**  
✔ **Código mucho más corto y limpio.**  
✔ **Automáticamente genera `getters`, `toString()`, `equals()`, `hashCode()`.**  
✔ **Es inmutable (los valores no pueden cambiar).**

📌 **Ejemplo de uso**
```java
public class Main {
    public static void main(String[] args) {
        Persona p = new Persona("Carlos", 30);
        System.out.println(p.nombre() + " tiene " + p.edad() + " años.");
    }
}
```
📌 **Salida esperada:**
```
Carlos tiene 30 años.
```

📌 **¿Se pueden agregar métodos a un `record`?** ✅ ¡Sí!
```java
public record Persona(String nombre, int edad) {
    public String saludo() {
        return "Hola, soy " + nombre;
    }
}
```

---

# **📌 4️⃣ Bloques de Texto (`Text Blocks`) 📜**

📌 **¿Qué problema soluciona?**  
Antes, trabajar con **cadenas multilínea** era complicado y requería **concatenaciones manuales**.

📌 **Ejemplo antes de Java 17**
```java
String json = "{\n" +
              "    \"nombre\": \"Carlos\",\n" +
              "    \"edad\": 30\n" +
              "}";
```

📌 **Ejemplo con `Text Blocks` en Java 17**
```java
String json = """
    {
        "nombre": "Carlos",
        "edad": 30
    }
    """;
```
✅ **Ventajas:**  
✔ **Código más legible.**  
✔ **Evita `\n` y `+` innecesarios.**  
✔ **Mejora la compatibilidad con JSON, XML y SQL.**

---

# **📌 5️⃣ Depuración Mejorada de `NullPointerException` 🚨**

📌 **¿Qué problema soluciona?**  
Antes, los `NullPointerException` no indicaban **exactamente qué variable era `null`**, lo que dificultaba la depuración.

📌 **Ejemplo antes de Java 17**
```java
System.out.println(usuario.getDireccion().getCiudad().toUpperCase()); 
// ❌ NullPointerException, pero no sabemos cuál fue `null`
```
📌 **Ejemplo con Java 17 (Ahora indica el valor `null`)**
```
Exception in thread "main" java.lang.NullPointerException:
Cannot invoke "Direccion.getCiudad()" because "usuario.getDireccion()" is null
```
✅ **Ventajas:**  
✔ **Depuración más rápida y precisa.**  
✔ **Ahorra tiempo en encontrar errores.**

---
