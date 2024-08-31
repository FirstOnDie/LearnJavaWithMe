# Schedulers

## Ejecutar tareas largas en hilos separados

:heavy_check_mark: **Buena práctica:**
En este ejemplo, la tarea se ejecuta de manera asíncrona, lo que significa que no bloquea el hilo principal y permite que otras operaciones continúen ejecutándose.

```java
@Async
@Scheduled(fixedRate = 10000)
public void tareaAsincrona() {
    // Tarea larga que no bloquea el hilo principal
}
```

:x: **Mala práctica**:
Aquí, la tarea se ejecuta en el hilo principal, lo que puede hacer que la aplicación sea lenta o no responda si la tarea tarda demasiado tiempo en completarse.

```java
@Scheduled(fixedRate = 10000)
public void tareaSinSeparacion() {
    // Tarea larga que bloquea el hilo principal
}
```

## Registrar adecuadamente el inicio y la finalización de tareas

:heavy_check_mark: **Buena práctica:**
Este ejemplo registra claramente cuándo la tarea comienza y termina, lo que facilita el seguimiento y la resolución de problemas.
```java
private static final Logger logger = LoggerFactory.getLogger(TuClase.class);

@Scheduled(fixedRate = 60000)
public void registrarTarea() {
    logger.info("Tarea iniciada");
    // Código de la tarea
    logger.info("Tarea finalizada");
}
```

:x: **Mala práctica**:
No registrar eventos importantes hace difícil entender el flujo de la aplicación y localizar problemas cuando ocurren.


```java
@Scheduled(fixedRate = 60000)
public void registrarTareaIncorrectamente() {
    // Código de la tarea
    // Falta el registro del inicio o finalización de la tarea
}
```

## Manejar adecuadamente las excepciones

:heavy_check_mark: **Buena práctica:**
Este ejemplo captura las excepciones y registra un mensaje de error detallado, lo que ayuda a los desarrolladores a entender qué salió mal.

```java
@Scheduled(fixedRate = 60000)
public void tareaConManejoDeErrores() {
    try {
        // Código que podría lanzar una excepción
    } catch (Exception e) {
        logger.error("Error al ejecutar la tarea", e);
    }
}
```

:x: **Mala práctica**:
No manejar excepciones puede hacer que la tarea falle silenciosamente o cause problemas en otros procesos sin dar información útil sobre lo que ocurrió.

```java
@Scheduled(fixedRate = 60000)
public void tareaSinManejoDeErrores() {
    // Código que podría lanzar una excepción
    // Sin manejo de excepciones
}
```

## Configurar la rotación de registros para evitar consumo excesivo de espacio

:heavy_check_mark: **Buena práctica:**
Este ejemplo configura la rotación de registros para que los archivos de registro no crezcan indefinidamente, lo que ayuda a gestionar el uso del espacio en disco.

```xml
<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <fileNamePattern>logs/myapp-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
        <maxHistory>30</maxHistory>
        <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
            <maxFileSize>100MB</maxFileSize>
        </timeBasedFileNamingAndTriggeringPolicy>
    </rollingPolicy>
</appender>
```

:x: **Mala práctica**:
No configurar la rotación de registros puede llevar a que los archivos crezcan demasiado y llenen el disco, lo que podría causar fallos en la aplicación y otros problemas de rendimiento.


```xml
<appender name="FILE" class="ch.qos.logback.core.FileAppender">
    <file>logs/myapp.log</file>
    <!-- Sin rotación configurada -->
</appender>
```