# Java 11

Java 8 fue una versión muy importante porque introdujo muchas características nuevas que hicieron a Java más moderno y divertido para programar. Aquí te explico algunas de las principales:

## Resumen

- **Lambdas (Funciones Lambda):** Permite escribir funciones más cortas y fáciles de leer.
- **Stream API:** Te permite trabajar con colecciones de datos de una manera más fácil y poderosa.
- **Date and Time API (Nueva API de Fecha y Hora):** Hace que trabajar con fechas y horas sea más fácil y menos confuso.

## Lambdas (Funciones Lambda):
Las funciones lambda permiten escribir funciones más cortas y fáciles de leer. Es como si pudieras escribir una receta de cocina en una sola línea en lugar de escribir todos los pasos en párrafos separados.

Ejemplo:

```java
// Antes de Java 8
Runnable tarea = new Runnable() {
    @Override
    public void run() {
        System.out.println("Haciendo algo...");
    }
};

// Con Lambda en Java 8
Runnable tareaLambda = () -> System.out.println("Haciendo algo...");
```
**Explicación:**
Antes, tenías que escribir mucho código para hacer que algo funcionara. Con lambdas, puedes hacer lo mismo con menos líneas, lo que hace que tu código sea más claro y conciso.

---

## Stream API:
Los Streams te permiten trabajar con colecciones de datos de una manera más fácil y poderosa. Es como tener un tubo mágico por el que pasan los datos y puedes filtrarlos, ordenarlos, o modificarlos mientras pasan por el tubo.

Ejemplo:

```java
List<String> nombres = Arrays.asList("Ana", "Luis", "Juan", "Carlos");
nombres.stream()
    .filter(nombre -> nombre.startsWith("J"))
    .forEach(System.out::println);  // Imprime "Juan"
```
**Explicación:**
Aquí estamos usando un Stream para tomar una lista de nombres y filtrar solo los que empiezan con "J". Luego, los imprimimos. Es como usar un colador para separar las cosas que quieres.

---

## Date and Time API (Nueva API de Fecha y Hora):
Antes de Java 8, trabajar con fechas y horas era un poco complicado y confuso. Java 8 introdujo una nueva API que es más fácil de usar y entender.

Ejemplo:

```java
LocalDate fecha = LocalDate.now();
System.out.println("Fecha actual: " + fecha);
```
**Explicación:**
Ahora, puedes trabajar con fechas de manera más intuitiva. Es como si te dieran un calendario nuevo que es más fácil de leer y usar.