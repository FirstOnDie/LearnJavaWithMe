# Logging

Imagina que tienes un cuaderno donde escribes todo lo que haces mientras juegas un videojuego. Esto te ayuda a recordar lo que pasó si algo sale mal, como cuando pierdes una partida o te atascas en un nivel. En la programación, esto se llama "registro" o "logging". Es como llevar un diario de lo que hace un programa para que los programadores puedan entender qué está pasando y arreglar problemas más fácilmente.

## Buenas y malas prácticas de registro en Java

### Usa una capa de registro como SLF4J

:heavy_check_mark: **Buena práctica:** Utiliza una herramienta como SLF4J para gestionar los registros. Es como usar un cuaderno especial que te permite cambiar de lápiz a pluma fácilmente si necesitas cambiar de herramienta de escritura.


```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClass {
    private static final Logger logger = LoggerFactory.getLogger(MyClass.class);
}
```

:x: **Mala práctica**: Usar una herramienta específica que hace difícil cambiarla después, como si solo tuvieras una pluma que no puedes reemplazar.

```java
import org.apache.log4j.Logger;

public class MyClass {
    private static final Logger logger = Logger.getLogger(MyClass.class);
}
```

### Configura Logback de manera eficiente

:heavy_check_mark: **Buena práctica:** Mantén la configuración de registro fuera del código y usa patrones para organizar mejor los registros, como tener secciones en tu cuaderno para diferentes tipos de notas.

```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    <root level="debug">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

:x: **Mala práctica**: Poner la configuración en el código, lo que hace difícil cambiarla más tarde, como si escribieras cosas importantes directamente en el videojuego en lugar de en el cuaderno.
    
```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">
            <!-- Configuración no recomendada -->
        </layout>
    </appender>
</configuration>
```

### Usa niveles de registro adecuados

:heavy_check_mark: **Buena práctica:** Usa diferentes niveles de registro para diferentes tipos de mensajes. Es como usar diferentes colores en tu cuaderno: uno para cosas importantes, otro para detalles, y otro para errores.
    
```java
logger.info("La aplicación ha comenzado.");
logger.debug("El valor de X es {}", x);
logger.error("No se pudo procesar la solicitud.", e);
```

:x: **Mala práctica**: Registrar todo al mismo nivel es como usar el mismo color para todo en tu cuaderno. Hace difícil encontrar lo importante.
        
```java
logger.error("La aplicación ha comenzado."); // Uso incorrecto del nivel de registro
```

### Registra mensajes significativos

:heavy_check_mark: **Buena práctica:** Incluye información relevante y específica en tus registros, como anotar exactamente qué pasó y dónde en tu cuaderno.

```java
logger.info("Pedido {} ha sido procesado exitosamente.", orderId);
```

:x: **Mala práctica**: Mensajes vagos que no ayudan a entender lo que pasó, como escribir "Algo pasó" en tu cuaderno.

```java
logger.info("Procesado exitosamente."); // No proporciona contexto
```

### Usa marcadores de posición para contenido dinámico

:heavy_check_mark: **Buena práctica:** Usa marcadores de posición para evitar crear cadenas largas de texto, ahorrando tiempo y espacio, como usar abreviaturas en tus notas.
    
```java
logger.debug("El usuario {} inició sesión a las {}", username, LocalDateTime.now());
```

:x: **Mala práctica**: Concatenar cadenas en los registros es ineficiente y como escribir todo completo en lugar de usar abreviaturas.
    
```java
logger.debug("El usuario " + username + " inició sesión a las " + LocalDateTime.now());
```

### Resumen
Un buen registro en Java es como llevar un cuaderno organizado y detallado de todo lo que pasa en un videojuego. Te ayuda a entender qué salió mal y cómo arreglarlo, mientras evitas llenar el cuaderno con información inútil o difícil de entender.