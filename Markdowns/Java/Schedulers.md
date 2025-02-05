# **📌 Schedulers en Spring Boot** ⏳🔄

📌 **¿Qué es un Scheduler?**  
Un **Scheduler** es un mecanismo que permite programar tareas automáticas en momentos específicos sin intervención manual.

💡 **Ejemplo en la vida real:**  
Imagina que tienes un **robot asistente** 🤖 que debe:  
✔ Regar las plantas **todos los días a las 8 AM** 🌱.  
✔ Barrer la casa **cada 5 minutos** 🧹.  
✔ Enviar un reporte por correo **cada lunes a las 9 AM** 📩.

Los **Schedulers en Spring Boot** permiten programar estas tareas de manera eficiente y automática, sin que tengas que ejecutarlas manualmente cada vez.

---

# **📌 ¿Por qué usar Schedulers en Spring Boot?**

✅ **Automatización** → Ejecuta tareas en un horario específico sin intervención manual.  
✅ **Eficiencia** → Reduce la necesidad de monitoreo constante.  
✅ **Flexibilidad** → Permite modificar, pausar o ajustar las tareas fácilmente.  
✅ **Escalabilidad** → Se pueden manejar múltiples tareas sin afectar el rendimiento del sistema.

📌 **Ejemplos de uso:**  
✔ **Copias de seguridad** automáticas de la base de datos.  
✔ **Limpieza de registros antiguos** en la base de datos.  
✔ **Envió de correos automatizados**.  
✔ **Tareas de sincronización** entre servicios.

---

# **📌 Configuración de Schedulers en Spring Boot** ⚙️

📌 **1️⃣ Habilitar Tareas Programadas con `@EnableScheduling`**

Para activar la funcionalidad de **Schedulers** en Spring Boot, agregamos `@EnableScheduling` en la clase principal.

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Aplicacion {
    public static void main(String[] args) {
        SpringApplication.run(Aplicacion.class, args);
    }
}
```
✅ **Spring ahora ejecutará automáticamente las tareas programadas**.

---

📌 **2️⃣ Programar Tareas con `@Scheduled`**

La anotación `@Scheduled` nos permite definir **cuándo y con qué frecuencia** ejecutar una tarea.

### **📌 a) Usando una Expresión `cron`** ⏳
📌 **Formato de una expresión cron:**
```
┌───────────── segundos (0-59)
│ ┌───────────── minutos (0-59)
│ │ ┌───────────── horas (0-23)
│ │ │ ┌───────────── día del mes (1-31)
│ │ │ │ ┌───────────── mes (1-12)
│ │ │ │ │ ┌───────────── día de la semana (0-6, donde 0 = domingo)
│ │ │ │ │ │
* * * * * *
```

📌 **Ejemplo:** Ejecutar una tarea **todos los días a las 8 AM**.
```java
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TareasProgramadas {
    
    @Scheduled(cron = "0 0 8 * * ?")
    public void tareaDiaria() {
        System.out.println("📅 Ejecutando tarea diaria a las 8 AM");
    }
}
```
✅ **Ejecutará la tarea todos los días a las 08:00 AM.**

📌 **Ejemplo: Expresiones Cron Comunes**

| **Expresión Cron** | **Ejecuta la tarea...** |
|--------------------|------------------|
| `"0 0 0 * * *"`   | Todos los días a la medianoche |
| `"0 0 12 * * 1"`  | Todos los lunes a las 12 PM |
| `"0 */10 * * * *"`| Cada 10 minutos |
| `"0 0 9-18 * * *"`| Cada hora entre las 9 AM y 6 PM |

---

### **📌 b) Usando Intervalos Fijos (`fixedRate` y `fixedDelay`)** 🔄

📌 **`fixedRate` → Ejecuta la tarea cada cierto tiempo SIN esperar que termine la anterior.**
```java
@Scheduled(fixedRate = 5000) // Ejecuta cada 5 segundos
public void tareaRepetitiva() {
    System.out.println("🔄 Esta tarea se ejecuta cada 5 segundos sin esperar a la anterior.");
}
```
📌 **`fixedDelay` → Espera a que termine la tarea anterior antes de ejecutarla de nuevo.**
```java
@Scheduled(fixedDelay = 5000) // Espera 5 segundos después de la ejecución previa
public void tareaConEspera() {
    System.out.println("⏳ Esperando 5 segundos después de cada ejecución.");
}
```

📌 **Diferencias clave:**

| **Método**    | **Ejecuta...** |
|--------------|--------------|
| `fixedRate`  | Cada X segundos, sin importar si la tarea anterior terminó. |
| `fixedDelay` | Espera a que termine la tarea anterior antes de volver a ejecutarla. |

---

# **📌 3️⃣ Configurar Schedulers en `application.properties`**

Podemos definir intervalos de tiempo de ejecución en el archivo `application.properties` para mayor flexibilidad.

```properties
scheduler.tarea.intervalo=5000
```
📌 **Luego, en la clase Java usamos:**
```java
import org.springframework.beans.factory.annotation.Value;

@Component
public class TareasDinamicas {
    @Value("${scheduler.tarea.intervalo}")
    private long intervalo;

    @Scheduled(fixedRateString = "${scheduler.tarea.intervalo}")
    public void tareaDinamica() {
        System.out.println("⚡ Esta tarea usa valores dinámicos configurables.");
    }
}
```
✅ **Podemos cambiar el intervalo sin modificar el código fuente.**

---

# **📌 4️⃣ Configurar Dependencias en `pom.xml`**

📌 **1️⃣ Agregar `spring-boot-starter` (si no está incluido)**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
```
📌 **2️⃣ Agregar `spring-context-support` (opcional, para características avanzadas)**
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context-support</artifactId>
</dependency>
```
✅ **Esto habilita todas las funcionalidades necesarias para `@Scheduled`.**

---

# **📌 5️⃣ Buenas Prácticas en Schedulers** 🚀

🔹 **Evitar Bloqueos:** No ejecutar tareas pesadas en el hilo principal. Usar **`@Async`** para tareas concurrentes.
```java
import org.springframework.scheduling.annotation.Async;

@Async
@Scheduled(fixedRate = 10000)
public void tareaAsincrona() {
    System.out.println("⚡ Esta tarea se ejecuta en un hilo separado.");
}
```
🔹 **Monitoreo y Logs:** Agregar logs para verificar que las tareas se ejecutan correctamente.
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TareasConLog {
    private static final Logger logger = LoggerFactory.getLogger(TareasConLog.class);

    @Scheduled(fixedRate = 60000) // Cada minuto
    public void tareaConRegistro() {
        logger.info("✅ Tarea ejecutada correctamente a las {}", System.currentTimeMillis());
    }
}
```
🔹 **Evitar Tareas Concurrentes:** Si varias tareas dependen entre sí, usa **`fixedDelay`** en lugar de `fixedRate`.

---