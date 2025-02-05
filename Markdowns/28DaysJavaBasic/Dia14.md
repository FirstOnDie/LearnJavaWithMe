# **📌 Día 14: Manejo de Fechas y Horas en Java** 🕒

📌 **Objetivo del día:**  
✅ Aprender a trabajar con **fechas y horas** en Java usando la API moderna (`java.time`).  
✅ Utilizar **`LocalDate`**, **`LocalTime`** y **`LocalDateTime`**.  
✅ Dar formato y manipular fechas con **`DateTimeFormatter`**.  
✅ **Ejercicio:** Implementar una **calculadora de edad basada en la fecha de nacimiento**.

---

## **1️⃣ ¿Por qué usar `java.time` en vez de `Date` y `Calendar`?**

✔ **API más moderna** y sencilla que `Date` y `Calendar`.  
✔ **Inmutable** y **hilo-seguro** (`Thread-safe`).  
✔ Soporta **zonas horarias** (`ZoneId`) y diferencias entre fechas.

📌 **Ejemplo de problemas con `Date` y `Calendar` (antes de Java 8)**
```java
import java.util.Date;

public class MalaPractica {
    public static void main(String[] args) {
        Date fecha = new Date();
        System.out.println(fecha); // ❌ Imprime con formato poco intuitivo
    }
}
```
✅ **Salida inesperada:**
```
Fri Feb 02 14:35:21 UTC 2024
```

⚠ **Problema:** `Date` es mutable y no proporciona métodos intuitivos.

📌 **Solución moderna con `LocalDateTime`:**
```java
import java.time.LocalDateTime;

public class BuenaPractica {
    public static void main(String[] args) {
        LocalDateTime fecha = LocalDateTime.now();
        System.out.println(fecha); // ✅ Fechas más legibles
    }
}
```
✅ **Salida esperada:**
```
2024-02-02T14:35:21.123
```

✔ **Las clases de `java.time` son más intuitivas y seguras.**

---

# **2️⃣ Clases Principales de `java.time`**

📌 **1. `LocalDate`** → Solo fecha (`YYYY-MM-DD`).  
📌 **2. `LocalTime`** → Solo hora (`HH:MM:SS`).  
📌 **3. `LocalDateTime`** → Fecha y hora (`YYYY-MM-DD HH:MM:SS`).

📌 **Ejemplo con `LocalDate`, `LocalTime` y `LocalDateTime`:**
```java
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class FechasHoras {
    public static void main(String[] args) {
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();
        LocalDateTime fechaHora = LocalDateTime.now();

        System.out.println("Fecha actual: " + fecha);
        System.out.println("Hora actual: " + hora);
        System.out.println("Fecha y hora actual: " + fechaHora);
    }
}
```
✅ **Salida esperada:**
```
Fecha actual: 2024-02-02  
Hora actual: 14:35:21.123  
Fecha y hora actual: 2024-02-02T14:35:21.123
```

✔ **`LocalDateTime` combina `LocalDate` y `LocalTime`.**

---

# **3️⃣ Formateo y Manipulación de Fechas**

📌 **Formatear fechas con `DateTimeFormatter`**
```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatoFecha {
    public static void main(String[] args) {
        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String fechaFormateada = fecha.format(formato);
        System.out.println("Fecha formateada: " + fechaFormateada);
    }
}
```
✅ **Salida esperada:**
```
Fecha formateada: 02/02/2024
```

✔ **`ofPattern("dd/MM/yyyy")` personaliza el formato.**

📌 **Sumar y restar días, meses, años:**
```java
import java.time.LocalDate;

public class ManipularFechas {
    public static void main(String[] args) {
        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaFutura = fechaHoy.plusDays(10);
        LocalDate fechaPasada = fechaHoy.minusMonths(2);

        System.out.println("Hoy: " + fechaHoy);
        System.out.println("Dentro de 10 días: " + fechaFutura);
        System.out.println("Hace 2 meses: " + fechaPasada);
    }
}
```
✅ **Salida esperada:**
```
Hoy: 2024-02-02  
Dentro de 10 días: 2024-02-12  
Hace 2 meses: 2023-12-02
```

✔ **Métodos `plusDays()`, `minusMonths()` permiten modificar fechas fácilmente.**

---

# **4️⃣ Cálculo de Diferencias entre Fechas**

📌 **Calcular diferencia en años entre dos fechas:**
```java
import java.time.LocalDate;
import java.time.Period;

public class DiferenciaFechas {
    public static void main(String[] args) {
        LocalDate nacimiento = LocalDate.of(1995, 5, 20);
        LocalDate hoy = LocalDate.now();

        Period diferencia = Period.between(nacimiento, hoy);
        System.out.println("Tienes " + diferencia.getYears() + " años.");
    }
}
```
✅ **Salida esperada:**
```
Tienes 28 años.
```

✔ **`Period.between(fecha1, fecha2)` calcula diferencia en años, meses y días.**

---

# **📌 Ejercicio del Día 14: Calculadora de Edad** 🎯

📌 **Objetivo:**  
✔ Solicitar la **fecha de nacimiento** del usuario.  
✔ Calcular la **edad actual** usando `Period.between()`.

📌 **Ejemplo de salida esperada:**
```
Ingrese su fecha de nacimiento (dd/MM/yyyy): 20/05/1995  
Tienes 28 años.
```

---

<details>
    <summary>Solución</summary>


### **✅ Solución en Java:**
📌 **Clase `CalculadoraEdad` con `Scanner` y `LocalDate`:**
```java
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CalculadoraEdad {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Solicitar la fecha de nacimiento
        System.out.print("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
        String inputFecha = scanner.nextLine();

        // Convertir la cadena a LocalDate
        LocalDate fechaNacimiento = LocalDate.parse(inputFecha, formato);
        LocalDate hoy = LocalDate.now();

        // Calcular la edad
        Period edad = Period.between(fechaNacimiento, hoy);
        System.out.println("Tienes " + edad.getYears() + " años.");

        scanner.close();
    }
}
```

✅ **Salida esperada:**
```
Ingrese su fecha de nacimiento (dd/MM/yyyy): 20/05/1995  
Tienes 28 años.
```

📌 **Explicación:**  
✔ **`LocalDate.parse(inputFecha, formato)`** convierte la entrada a fecha.  
✔ **`Period.between(fechaNacimiento, hoy).getYears()`** obtiene la edad.

---

</details>