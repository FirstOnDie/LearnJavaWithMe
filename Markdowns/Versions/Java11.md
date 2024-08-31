# Java 11

Java 11 continuó mejorando el lenguaje y simplificando el desarrollo, especialmente con nuevas herramientas y características.
## Resumen

- **Nuevo `HttpClient` API:** Hace que sea más fácil hacer solicitudes HTTP.
- **Ejecución de Archivos Java Directamente:** Permite ejecutar archivos `.java` sin compilarlos primero.
- **`var` para Inferencia de Tipo en Variables Locales:** Permite declarar variables sin escribir su tipo.

## Nuevo `HttpClient` API:

Esta nueva API hace que sea más fácil hacer solicitudes HTTP, que es como pedir información a través de Internet. Imagina que es como un teléfono mejorado que puedes usar para llamar a sitios web y pedirles datos.

Ejemplo:

```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://example.com"))
    .build();
client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    .thenApply(HttpResponse::body)
    .thenAccept(System.out::println);
```

**Explicación:**
Este ejemplo muestra cómo puedes usar la nueva API de `HttpClient` para hacer una solicitud a un sitio web y obtener la respuesta fácilmente.

---

## Ejecución de Archivos Java Directamente:
Java 11 permite ejecutar archivos `.java` directamente sin tener que compilarlos primero. Es como poder correr un carro sin necesidad de construirlo primero; ¡simplemente te subes y manejas!

Ejemplo:

```bash
java HolaMundo.java
```

**Explicación:**
Antes, tenías que compilar tu archivo Java en un archivo `.class` antes de ejecutarlo. Ahora, puedes ejecutar el archivo `.java` directamente, lo cual es muy útil para pruebas rápidas y scripts.

---

## `var` para Inferencia de Tipo en Variables Locales:
`var` es una nueva palabra clave que permite declarar una variable sin tener que escribir su tipo. Java adivina el tipo por ti. Es como si dijeras "guarda esto por mí" y Java sabe exactamente dónde ponerlo.

Ejemplo:

```java
var mensaje = "Hola, Mundo";
System.out.println(mensaje);
```

**Explicación:**
Aquí, Java sabe que `mensaje` es un `String` sin que tengas que decírselo explícitamente. Es como cuando tu mamá sabe que "leche" va en la nevera sin que tú se lo digas.
