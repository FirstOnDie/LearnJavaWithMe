# **📌 Preguntas y Respuestas para Entrevista Técnica – Buenas Prácticas en Java**

### ❓ **Pregunta:** ¿Por qué es importante usar un sistema de logging en una aplicación?
✅ **Respuesta:**  
El logging es esencial para:  
✔ **Depuración** – Diagnosticar errores en producción.  
✔ **Monitoreo** – Analizar el comportamiento del sistema.  
✔ **Auditoría** – Registrar eventos clave para cumplimiento normativo.

---

### ❓ **Pregunta:** ¿Cuál es la diferencia entre `System.out.println()` y una librería de logging como SLF4J?
✅ **Respuesta:**  
📌 **`System.out.println()`** es una salida simple, no configurable ni escalable.  
📌 **SLF4J** permite controlar el nivel de logs (`INFO`, `ERROR`, `DEBUG`), escribir en archivos y configurar formatos.

📌 **Ejemplo con SLF4J:**
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Aplicación iniciada correctamente.");
        logger.error("Error al conectar con la base de datos.");
    }
}
```
✅ **Permite configurar distintos niveles de logs y almacenar registros en archivos.**

---

### ❓ **Pregunta:** ¿Qué niveles de logging existen y cuándo se deben usar?
✅ **Respuesta:**  
✔ **TRACE** – Información detallada sobre el flujo interno de la aplicación.  
✔ **DEBUG** – Información útil para depuración en desarrollo.  
✔ **INFO** – Eventos importantes pero no críticos.  
✔ **WARN** – Posibles problemas que podrían escalar a errores.  
✔ **ERROR** – Errores graves que afectan la ejecución del programa.

📌 **Ejemplo:**
```java
logger.warn("El servicio no respondió en el tiempo esperado.");
```

---

### ❓ **Pregunta:** ¿Qué es un `Scheduler` en Java y cuándo se usa?
✅ **Respuesta:**  
Un `Scheduler` es un programador de tareas que ejecuta procesos en intervalos definidos, útil para:  
✔ **Tareas automáticas** – Backups, reportes periódicos, limpieza de datos.  
✔ **Ejecución programada** – Enviar emails o notificaciones en horarios específicos.  
✔ **Procesamiento en segundo plano** – Sin bloquear el hilo principal.

📌 **Ejemplo en Spring Boot:**
```java
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TareasProgramadas {

    @Scheduled(fixedRate = 5000) // Ejecuta cada 5 segundos
    public void tareaPeriodica() {
        System.out.println("Tarea ejecutada en: " + System.currentTimeMillis());
    }
}
```
✅ **Esto ejecuta la tarea cada 5 segundos sin intervención manual.**

---

### ❓ **Pregunta:** ¿Qué diferencia hay entre `fixedRate` y `fixedDelay` en `@Scheduled`?
✅ **Respuesta:**  
📌 **`fixedRate`** – La tarea se ejecuta en intervalos fijos, sin importar si la anterior finalizó.  
📌 **`fixedDelay`** – Espera a que la tarea termine antes de iniciar la siguiente.

📌 **Ejemplo:**
```java
@Scheduled(fixedRate = 5000)
public void tareaFixedRate() {
    System.out.println("Esta tarea se ejecuta cada 5 segundos, sin esperar que termine.");
}

@Scheduled(fixedDelay = 5000)
public void tareaFixedDelay() {
    System.out.println("Esta tarea espera 5 segundos después de terminar.");
}
```

---

### ❓ **Pregunta:** ¿Qué es un `NullPointerException` y cómo se puede evitar?
✅ **Respuesta:**  
📌 Ocurre cuando intentamos acceder a un objeto que es `null`.

📌 **Formas de evitarlo:**  
✔ **Comprobación previa (`if != null`)**  
✔ **Uso de `Optional`**  
✔ **Valores por defecto (`Objects.requireNonNullElse`)**

📌 **Ejemplo con `Optional`:**
```java
Optional<String> nombre = Optional.ofNullable(null);
nombre.ifPresent(System.out::println); // No imprime nada y evita error
```

✅ **Esto permite manejar valores nulos sin errores inesperados.**

---

### ❓ **Pregunta:** ¿Cómo se usa `Objects.requireNonNullElse()` en Java 9+?
✅ **Respuesta:**  
📌 Permite asignar un valor por defecto si el objeto es `null`.

📌 **Ejemplo:**
```java
import java.util.Objects;

String nombre = null;
String resultado = Objects.requireNonNullElse(nombre, "Valor por defecto");
System.out.println(resultado); // Imprime: Valor por defecto
```
✅ **Evita `NullPointerException` y asegura un valor válido.**

---

### ❓ **Pregunta:** ¿Qué son los principios SOLID y por qué son importantes?
✅ **Respuesta:**  
SOLID es un conjunto de **5 principios** para diseñar software **mantenible, flexible y escalable**.

📌 **Principios SOLID:**  
✔ **S** – **Single Responsibility Principle (SRP)**  
✔ **O** – **Open/Closed Principle (OCP)**  
✔ **L** – **Liskov Substitution Principle (LSP)**  
✔ **I** – **Interface Segregation Principle (ISP)**  
✔ **D** – **Dependency Inversion Principle (DIP)**

---

### ❓ **Pregunta:** ¿Cómo se aplica el **Principio de Responsabilidad Única (SRP)**?
✅ **Respuesta:**  
📌 **Una clase debe tener solo una razón para cambiar.**

📌 **Ejemplo incorrecto:**
```java
class Reporte {
    public void generarPDF() { /* Generar reporte */ }
    public void enviarEmail() { /* Enviar email */ }
}
```
📌 **Ejemplo correcto (Separación de responsabilidades):**
```java
class GeneradorReporte {
    public void generarPDF() { /* Generar reporte */ }
}

class EnvioEmail {
    public void enviarEmail() { /* Enviar email */ }
}
```
✅ **Mejor mantenimiento y menor acoplamiento.**

---

### ❓ **Pregunta:** ¿Cómo funciona el **Principio de Abierto/Cerrado (OCP)**?
✅ **Respuesta:**  
📌 **El código debe estar abierto para extensión, pero cerrado para modificación.**

📌 **Ejemplo incorrecto:**
```java
class Calculadora {
    public int calcular(String operacion, int a, int b) {
        if (operacion.equals("suma")) return a + b;
        else if (operacion.equals("resta")) return a - b;
        return 0;
    }
}
```
📌 **Ejemplo correcto (Extensible con herencia):**
```java
abstract class Operacion {
    abstract int calcular(int a, int b);
}

class Suma extends Operacion {
    int calcular(int a, int b) { return a + b; }
}

class Resta extends Operacion {
    int calcular(int a, int b) { return a - b; }
}
```
✅ **Permite agregar nuevas operaciones sin modificar código existente.**

---

# 🎯 **Conclusión**
📌 **Logging** – Usa SLF4J para controlar niveles y almacenar logs.  
📌 **Schedulers** – Usa `@Scheduled` en tareas programadas.  
📌 **Evitar `NullPointerException`** – Usa `Optional` y `Objects.requireNonNullElse()`.  
📌 **Principios SOLID** – Diseña software modular, flexible y mantenible.
