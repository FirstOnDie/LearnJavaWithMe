# Schedulers

En programación, la planificación de trabajos o Job Scheduling es una técnica que permite ejecutar tareas específicas en momentos determinados sin intervención manual. Esto es especialmente útil para tareas repetitivas y programadas, como realizar copias de seguridad, enviar correos electrónicos automatizados, o limpiar registros antiguos en una base de datos.

Spring Boot es un framework en Java que facilita la configuración de estas tareas programadas. La planificación se puede realizar usando la anotación @Scheduled que permite especificar horarios precisos en los que una tarea debe ejecutarse, utilizando expresiones cron o intervalos fijos.

## Explicación:

Planificación de trabajos es como tener un calendario automático para que tu aplicación realice tareas específicas en ciertos momentos. Imagina que tienes un robot que debe regar las plantas cada mañana a las 8 am y barrer la casa cada tarde a las 5 pm. La planificación de trabajos en Spring Boot permite programar estas tareas de manera que el robot sepa exactamente cuándo hacer cada cosa sin que tengas que recordárselo manualmente cada vez.

### ¿Por qué es importante?

- Automatización: Permite que las aplicaciones ejecuten tareas automáticamente en un horario específico sin intervención manual.
- Eficiencia: Ayuda a realizar tareas repetitivas como enviar correos, limpiar bases de datos, o realizar copias de seguridad sin necesidad de monitoreo constante.
- Flexibilidad: Puedes ajustar el calendario de tareas según tus necesidades, incluso pausarlas o cambiarlas sin afectar el resto del sistema.

### Cómo se hace en Spring Boot

Spring Boot, un framework de Java, proporciona herramientas para configurar esta planificación de tareas fácilmente. A través de anotaciones y configuraciones simples, puedes definir qué tareas deben ejecutarse y cuándo.


#### @Scheduled

Esta anotación se utiliza para marcar un método que debe ejecutarse automáticamente según un horario. Permite definir exactamente cuándo una tarea debe ejecutarse utilizando expresiones cron o configuraciones de intervalos.

- Expresión Cron: Es una expresión que define el momento exacto en el que se debe ejecutar una tarea. Por ejemplo, "0 0 8 * * ?" ejecuta la tarea todos los días a las 8 am.

```java
@Scheduled(cron = "0 0 8 * * ?")
public void tareaProgramada() {
    System.out.println("Ejecutando tarea diaria a las 8 am");
}
```
- Intervalos Fijos: Puedes usar fixedRate para ejecutar una tarea repetidamente con un intervalo fijo. Por ejemplo, @Scheduled(fixedRate = 5000) ejecuta la tarea cada 5 segundos.

```java
@Scheduled(fixedRate = 5000)
public void tareaRepetitiva() {
    System.out.println("Esta tarea se ejecuta cada 5 segundos");
}
```

#### @EnableScheduling
Habilita la capacidad de Spring Boot para programar tareas. Al agregar esta anotación a una clase de configuración, Spring buscará métodos anotados con @Scheduled y los ejecutará según lo especificado.
```java
@SpringBootApplication
@EnableScheduling
public class Aplicacion {
    public static void main(String[] args) {
        SpringApplication.run(Aplicacion.class, args);
    }
}
```

## Configuración de Schedulers en Spring Boot y Detalles Extendidos

Para habilitar la programación de tareas (schedulers) en una aplicación de Spring Boot, debes asegurarte de que tienes las dependencias necesarias en tu archivo pom.xml. Aquí se explica cómo configurarlo:

- Dependencia de Spring Boot Starter: Asegúrate de incluir la dependencia spring-boot-starter en tu pom.xml. Esto proporciona las bibliotecas básicas necesarias para construir aplicaciones de Spring Boot.
```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
```
- Dependencia de Spring Context Support: Para asegurarte de que puedes utilizar todas las anotaciones y características relacionadas con la planificación de tareas, puedes incluir también spring-context-support si estás usando características avanzadas.
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context-support</artifactId>
</dependency>
```
## Conclusión
La planificación de tareas en Spring Boot es una herramienta poderosa que permite a los desarrolladores automatizar tareas recurrentes de manera eficiente. A través de la configuración adecuada en pom.xml y siguiendo las mejores prácticas, puedes crear aplicaciones robustas y fáciles de mantener. Estas prácticas aseguran que las tareas se ejecuten correctamente, con buen manejo de errores y sin afectar el rendimiento general de la aplicación.
