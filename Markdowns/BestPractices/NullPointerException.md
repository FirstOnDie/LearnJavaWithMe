# NullPointerException

## Explicación:

Imagina que tienes una caja de juguetes y algunos de los juguetes están rotos o faltan piezas. Si intentas jugar con un juguete roto, no funcionará bien y podrías lastimarte. En Java, un NullPointerException es como intentar jugar con un juguete roto: el programa se detiene porque algo no está en su lugar.

Para evitar estos errores, hay algunas cosas que los programadores pueden hacer, como revisar si el juguete (o el objeto en el programa) está en buen estado antes de usarlo.

## Ejemplos:

### Comprobar antes de usar

:heavy_check_mark: **Buena práctica:**
Aquí se comprueba si nombre es nulo antes de usarlo. Esto es como revisar si el juguete está roto antes de intentar jugar con él.
```java
String nombre = null;

if (nombre != null) {
    System.out.println(nombre.length());
} else {
    System.out.println("El nombre es nulo, no se puede calcular la longitud.");
}
```

:x: **Mala práctica**:
Este código intenta usar nombre sin comprobar si es nulo, lo que provoca un error, como jugar con un juguete roto.

```java
String nombre = null;
System.out.println(nombre.length()); // ¡Error!
```

### Comparar con un valor conocido

:heavy_check_mark: **Buena práctica:**
En este ejemplo, equals se llama en el valor conocido "test" en lugar de en str, que podría ser nulo. Esto es como comprobar si tienes un juguete que sabes que está en buen estado.

```java
String str = null;

if ("test".equals(str)) {
        System.out.println("La cadena es igual a 'test'.");
} else {
        System.out.println("La cadena es nula o no es igual a 'test'.");
}
```

:x: **Mala práctica**:
Aquí, equals se llama en str, que es nulo, lo que provoca un error, como intentar usar un juguete que no tienes.

```java
String str = null;

if (str.equals("test")) { // ¡Error!
        System.out.println("La cadena es igual a 'test'.");
}
```

### Usar Optional para manejar valores que podrían ser nulos

:heavy_check_mark: **Buena práctica:**
Este código utiliza Optional para envolver un valor que podría ser nulo y verificarlo de manera segura antes de usarlo. Esto es como guardar tus juguetes en una caja y comprobar que están allí antes de sacarlos.

```java
String str = null;
Optional<String> optionalStr = Optional.ofNullable(str);

optionalStr.ifPresent(s -> System.out.println("Longitud: " + s.length()));
```

:x: **Mala práctica**:
Aquí se intenta usar str sin comprobar si es nulo, lo que provoca un error.

```java
String str = null;
System.out.println(str.length()); // ¡Error!
```

### Usar String.valueOf() para evitar nulls

:heavy_check_mark: **Buena práctica:**
Este código convierte numero en una cadena de manera segura, incluso si es nulo, devolviendo "null" en lugar de causar un error. Esto es como usar un juguete de repuesto si el que querías está roto.

```java
Integer numero = null;
System.out.println(String.valueOf(numero));
```

:x: **Mala práctica**:
Aquí se intenta llamar a toString() en numero que es nulo, lo que provoca un error.

```java
Integer numero = null;
System.out.println(numero.toString()); // ¡Error!
```