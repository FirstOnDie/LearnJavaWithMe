# 📌 **Expresiones Lambda en Java**

Las **expresiones lambda** permiten escribir código más conciso y declarativo en Java, especialmente cuando se trabaja con funciones que requieren **interfaces funcionales** (interfaces con un solo método abstracto).

---

## ✨ **¿Por qué usar Lambdas?**
Antes de Java 8, cuando queríamos usar una función dentro de otra, teníamos que escribir **clases anónimas** con mucho código repetitivo. Con las lambdas, podemos escribir lo mismo en una sola línea.

📌 **Ejemplo: Código antes de Java 8 (clase anónima)**
```java
import java.util.Comparator;

public class EjemploAntesJava8 {
    public static void main(String[] args) {
        Comparator<Integer> comparador = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        };
        System.out.println(comparador.compare(10, 5)); // Resultado: 1
    }
}
```

📌 **Código equivalente con Lambda (Java 8+)**
```java
import java.util.Comparator;

public class EjemploLambda {
    public static void main(String[] args) {
        Comparator<Integer> comparador = (a, b) -> a.compareTo(b);
        System.out.println(comparador.compare(10, 5)); // Resultado: 1
    }
}
```
✅ ¡Mucho más limpio y fácil de leer! 😃

---

## **📌 Sintaxis de una expresión Lambda**
Una lambda tiene tres partes:
```java
(parametros) -> { cuerpo de la función }
```
Ejemplo de estructura:
```java
(int a, int b) -> { return a + b; }
```
Pero se puede simplificar:
```java
(a, b) -> a + b
```

📌 **Reglas:**
1. Si hay **un solo parámetro**, puedes omitir los paréntesis:
   ```java
   s -> s.length();
   ```
2. Si el cuerpo de la función tiene **una sola línea**, puedes omitir `{}` y `return`:
   ```java
   (a, b) -> a + b;
   ```
3. Si no hay parámetros, usa `() -> expresión`:
   ```java
   () -> System.out.println("Hola mundo!");
   ```

---

# **📌 Interfaces funcionales y Lambdas**
Las **interfaces funcionales** son clave para usar lambdas. La más conocida es `Runnable`:
```java
Runnable tarea = () -> System.out.println("Ejecutando tarea...");
```

📌 **Ejemplo con `Predicate` (función que devuelve `true` o `false`)**
```java
import java.util.function.Predicate;

public class EjemploPredicate {
    public static void main(String[] args) {
        Predicate<String> esLargo = s -> s.length() > 5;
        System.out.println(esLargo.test("Hola")); // false
        System.out.println(esLargo.test("Programación")); // true
    }
}
```

📌 **Ejemplo con `Function` (Transforma datos)**
```java
import java.util.function.Function;

public class EjemploFunction {
    public static void main(String[] args) {
        Function<Integer, Integer> cuadrado = x -> x * x;
        System.out.println(cuadrado.apply(4)); // 16
    }
}
```

📌 **Ejemplo con `Consumer` (Ejecuta una acción sin devolver valor)**
```java
import java.util.function.Consumer;

public class EjemploConsumer {
    public static void main(String[] args) {
        Consumer<String> imprimir = s -> System.out.println("Hola " + s);
        imprimir.accept("Mundo"); // Hola Mundo
    }
}
```

---

# **📌 Lambdas con Streams**
Podemos usar lambdas para manipular colecciones de forma declarativa.

📌 **Ejemplo: Filtrar y procesar una lista de nombres**
```java
import java.util.Arrays;
import java.util.List;

public class EjemploStreamsLambda {
    public static void main(String[] args) {
        List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "Marta", "Luis");

        nombres.stream()
               .filter(n -> n.startsWith("M")) // Filtra nombres que comienzan con "M"
               .forEach(System.out::println); // Imprime los resultados
    }
}
```
**Salida esperada:**
```
Marta
```

📌 **Ejemplo: Convertir lista de números a su cuadrado y ordenarlos**
```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EjemploMap {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(3, 1, 4, 1, 5, 9);

        List<Integer> cuadradosOrdenados = numeros.stream()
                .map(n -> n * n)         // Elevar al cuadrado
                .sorted()                // Ordenar ascendente
                .collect(Collectors.toList()); // Recoger en una lista

        System.out.println(cuadradosOrdenados);
    }
}
```
**Salida esperada:**
```
[1, 1, 9, 16, 25, 81]
```

---

## **📝 Ejercicio para ti**
1. Crea una lista de números enteros y usa **streams + lambdas** para:
    - Filtrar los números impares.
    - Multiplicarlos por 3.
    - Ordenarlos de mayor a menor.
    - Imprimir el primer número de la lista.

2. Implementa una función `Function<String, Integer>` que reciba un nombre y devuelva la cantidad de letras que tiene.

<details>
    <summary>Solución</summary>

# **Ejercicio 1: Procesar una lista de números**
📌 **Objetivo:**
1. Filtrar los números impares.
2. Multiplicarlos por 3.
3. Ordenarlos de mayor a menor.
4. Imprimir el primer número de la lista.

### **Código resuelto**
```java
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EjercicioLambdasStreams {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(10, 3, 7, 5, 8, 1, 6, 9);

        // Procesar la lista con Streams
        Optional<Integer> primerNumero = numeros.stream()
                .filter(n -> n % 2 != 0)  // Filtrar impares
                .map(n -> n * 3)         // Multiplicar por 3
                .sorted((a, b) -> b - a) // Ordenar de mayor a menor
                .findFirst();            // Obtener el primer elemento

        // Imprimir el resultado si existe
        primerNumero.ifPresent(n -> System.out.println("El primer número es: " + n));
    }
}
```
---

# **Ejercicio 2: Contar letras en un nombre**
📌 **Objetivo:**
- Crear una función que reciba un **String** y devuelva la cantidad de letras.

### **Código resuelto**
```java
import java.util.function.Function;

public class EjercicioFunction {
    public static void main(String[] args) {
        // Definir la función lambda
        Function<String, Integer> contarLetras = nombre -> nombre.length();

        // Probar la función
        System.out.println("Número de letras en 'Ana': " + contarLetras.apply("Ana"));
        System.out.println("Número de letras en 'Programación': " + contarLetras.apply("Programación"));
    }
}
```

</details>