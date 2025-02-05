# **📌 Logging en Java** 📜✨

📌 **El logging** es una herramienta esencial en cualquier aplicación, ya que permite **registrar eventos importantes**, depurar errores y monitorear el comportamiento del sistema.

Imagina que tu aplicación es un **videojuego**, y cada acción importante (inicio de sesión, errores, datos críticos) se guarda en un **diario de aventuras**. Si ocurre un problema, el diario te ayuda a **entender qué pasó** y **cómo solucionarlo**.

✅ **Beneficios de un buen sistema de logging:**  
✔ **Diagnóstico eficiente:** Ayuda a identificar errores rápidamente.  
✔ **Monitorización y análisis:** Permite rastrear eventos en tiempo real.  
✔ **Depuración más sencilla:** Reduce la necesidad de depurar con `System.out.println()`.  
✔ **Seguridad y auditoría:** Guarda eventos críticos para futuras investigaciones.

---

# **📌 1️⃣ ¿Qué herramientas de logging usar en Java?** 🛠

📌 **Las principales herramientas de logging en Java son:**

| **Librería** | **Características** |
|-------------|--------------------|
| **SLF4J** (Simple Logging Facade for Java) | **Recomendada.** Es una interfaz unificada que permite cambiar fácilmente la implementación del sistema de logging. |
| **Logback** | Implementación potente y moderna de SLF4J. Soporta configuraciones avanzadas en XML o propiedades. |
| **Log4j 2** | Alternativa flexible, con buen rendimiento y soporte para JSON y XML. |
| **java.util.logging (JUL)** | Logging nativo de Java, menos flexible y con menos funcionalidades. |

📌 **🔹 ¿Cuál usar?**  
➡ **SLF4J + Logback** es la opción más recomendada porque combina flexibilidad, rendimiento y facilidad de configuración.

---

# **📌 2️⃣ Configuración de Logging con SLF4J + Logback** ⚙️

📌 **Agregar dependencias (Maven):**
```xml
<dependencies>
    <!-- API de SLF4J -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.9</version>
    </dependency>

    <!-- Implementación de Logback -->
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.4.11</version>
    </dependency>
</dependencies>
```

📌 **Configurar `logback.xml` para un logging organizado:**
```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    <root level="info">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```
✅ **Ventajas:**  
✔ Separa los logs en diferentes niveles (`INFO`, `DEBUG`, `ERROR`).  
✔ Configurable sin tocar el código fuente.

📌 **Clase con logging:**
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MiAplicacion {
    private static final Logger logger = LoggerFactory.getLogger(MiAplicacion.class);

    public static void main(String[] args) {
        logger.info("✅ La aplicación ha iniciado correctamente.");
        logger.debug("📊 Valor de configuración: {}", 42);
        logger.error("❌ Se produjo un error inesperado.", new RuntimeException("Error de prueba"));
    }
}
```

📌 **Salida en consola:**
```
12:30:15.123 [main] INFO  MiAplicacion - ✅ La aplicación ha iniciado correctamente.
12:30:15.124 [main] ERROR MiAplicacion - ❌ Se produjo un error inesperado.
java.lang.RuntimeException: Error de prueba
```

---

# **📌 3️⃣ Buenas prácticas en Logging** ✔️

## ✅ **Usa una capa de abstracción como SLF4J**
✔ Permite cambiar fácilmente la implementación de logging sin modificar el código.

🔹 **Ejemplo correcto con SLF4J:**
```java
private static final Logger logger = LoggerFactory.getLogger(MiClase.class);
```

❌ **Ejemplo incorrecto con implementación fija (`Log4j`):**
```java
private static final Logger logger = Logger.getLogger(MiClase.class);
```
➡ **Problema:** Si en el futuro cambias de `Log4j` a `Logback`, necesitarás modificar todo el código.

---

## ✅ **Usa niveles de logging adecuados**
Cada evento debe registrarse con un **nivel de severidad** apropiado:

| **Nivel** | **Uso recomendado** | **Ejemplo** |
|----------|--------------------|------------|
| `ERROR` | Fallos críticos que detienen la aplicación. | Error en base de datos. |
| `WARN` | Problemas que **no detienen** la ejecución, pero requieren atención. | Uso de configuración obsoleta. |
| `INFO` | Eventos importantes del sistema. | Inicio de la aplicación. |
| `DEBUG` | Detalles útiles para depuración. | Valores de variables internas. |
| `TRACE` | Información extremadamente detallada. | Paso a paso de ejecución. |

📌 **Ejemplo correcto de uso de niveles:**
```java
logger.info("📢 Aplicación iniciada.");
logger.warn("⚠️ El usuario {} intentó acceder sin permisos.", usuario);
logger.error("❌ Error al conectar con la base de datos.", exception);
```

❌ **Ejemplo incorrecto:**
```java
logger.error("📢 Aplicación iniciada."); // ❌ No es un error
```

---

## ✅ **Usa placeholders en lugar de concatenación de Strings**
📌 **Ejemplo correcto:**
```java
logger.debug("📊 Usuario {} inició sesión a las {}", usuario, LocalDateTime.now());
```
❌ **Ejemplo incorrecto:**
```java
logger.debug("📊 Usuario " + usuario + " inició sesión a las " + LocalDateTime.now());
```
➡ **Problema:** Concatenar Strings **genera objetos innecesarios** y afecta el rendimiento.

---

## ✅ **Evita registrar información sensible** 🔒
No incluyas **contraseñas, tokens o datos personales** en los logs.

❌ **Ejemplo incorrecto (riesgo de seguridad):**
```java
logger.warn("Usuario {} ingresó con contraseña {}", usuario, password);
```
📌 **Ejemplo correcto:**
```java
logger.warn("Usuario {} intentó iniciar sesión.", usuario);
```
✅ **Ventajas:**  
✔ Protege datos sensibles.  
✔ Evita filtraciones de seguridad.

---

# **📌 4️⃣ Registro de Excepciones en Logging** 🚨

📌 **Ejemplo correcto:**
```java
try {
    int resultado = 10 / 0;
} catch (ArithmeticException e) {
    logger.error("❌ Error al dividir por cero: {}", e.getMessage(), e);
}
```
✅ **Ventajas:**  
✔ Guarda la **pila de errores** para análisis posterior.  
✔ Facilita la depuración.

❌ **Ejemplo incorrecto:**
```java
logger.error("❌ Error: " + e.getMessage()); // ❌ No imprime la pila de errores
```

---