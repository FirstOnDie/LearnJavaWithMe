# **📌 Expresiones Lambda en Java** 🚀🔀

📌 **¿Qué es una expresión Lambda?**  
Una **expresión lambda** es una **función anónima** que nos permite escribir código más **conciso** y **legible**, especialmente al trabajar con **interfaces funcionales** (interfaces con un único método abstracto).

💡 **Ejemplo real:**
- Antes de Java 8, para pasar una función como argumento, necesitábamos **clases anónimas** (código repetitivo y difícil de leer).
- Con **Lambdas**, podemos hacer lo mismo con **una sola línea de código**.

---

# **📌 1️⃣ Código Antes y Después de Lambdas**

📌 **Antes de Java 8 (Clase Anónima)**
```java
import java.util.Comparator;

public class SinLambda {
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

📌 **Java 8+ (Con Lambda)**
```java
import java.util.Comparator;

public class ConLambda {
    public static void main(String[] args) {
        Comparator<Integer> comparador = (a, b) -> a.compareTo(b);
        System.out.println(comparador.compare(10, 5)); // Resultado: 1
    }
}
```
✅ **¡Mucho más limpio y fácil de leer!** 🚀

---

# **📌 2️⃣ Sintaxis de una Expresión Lambda**

📌 **Estructura básica:**
```java
(parametros) -> { cuerpo de la función }
```

📌 **Ejemplo de sintaxis y simplificación:**
```java
(int a, int b) -> { return a + b; } // Versión detallada
(a, b) -> a + b                      // Versión simplificada
```

📌 **Reglas de simplificación:**  
✅ **Un solo parámetro** → No necesita paréntesis.
```java
s -> s.length();
```  
✅ **Una sola línea de código** → Sin `{}` ni `return`.
```java
(a, b) -> a + b;
```  
✅ **Sin parámetros** → Usa `()`.
```java
() -> System.out.println("Hola Mundo!");
```  

---

# **📌 3️⃣ Interfaces Funcionales y Lambdas**

📌 **¿Qué es una interfaz funcional?**  
Una **interfaz funcional** es aquella que tiene **un único método abstracto** y es el requisito para que una lambda funcione.

📌 **Ejemplo con `Runnable` (sin recibir ni devolver valores)**
```java
Runnable tarea = () -> System.out.println("Ejecutando tarea...");
new Thread(tarea).start();
```

📌 **Ejemplo con `Predicate<T>` (devuelve `true` o `false`)**
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

📌 **Ejemplo con `Function<T, R>` (Transforma un dato en otro)**
```java
import java.util.function.Function;

public class EjemploFunction {
    public static void main(String[] args) {
        Function<Integer, Integer> cuadrado = x -> x * x;
        System.out.println(cuadrado.apply(4)); // 16
    }
}
```

📌 **Ejemplo con `Consumer<T>` (Recibe un valor y ejecuta una acción, sin retorno)**
```java
import java.util.function.Consumer;

public class EjemploConsumer {
    public static void main(String[] args) {
        Consumer<String> imprimir = s -> System.out.println("Hola " + s);
        imprimir.accept("Mundo"); // Hola Mundo
    }
}
```

📌 **Ejemplo con `Supplier<T>` (Provee un valor sin recibir parámetros)**
```java
import java.util.function.Supplier;

public class EjemploSupplier {
    public static void main(String[] args) {
        Supplier<Double> aleatorio = () -> Math.random();
        System.out.println(aleatorio.get());
    }
}
```

---

# **📌 4️⃣ Lambdas con Streams**

📌 **Ejemplo: Filtrar y procesar una lista de nombres**
```java
import java.util.Arrays;
import java.util.List;

public class EjemploStreamsLambda {
    public static void main(String[] args) {
        List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "Marta", "Luis");

        nombres.stream()
               .filter(n -> n.startsWith("M")) // Filtrar nombres con "M"
               .forEach(System.out::println); // Imprimir resultados
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
                .map(n -> n * n)  // Elevar al cuadrado
                .sorted()         // Ordenar ascendente
                .collect(Collectors.toList());

        System.out.println(cuadradosOrdenados);
    }
}
```
**Salida esperada:**
```
[1, 1, 9, 16, 25, 81]
```

---

# **📌 5️⃣ Referencias a Métodos (`::`)**

📌 **Ejemplo: `System.out::println` (Método estático de instancia)**
```java
List<String> nombres = List.of("Ana", "Juan", "Pedro");
nombres.forEach(System.out::println);
```

📌 **Ejemplo: `String::toUpperCase` (Método de instancia de un objeto arbitrario)**
```java
List<String> nombres = List.of("ana", "juan", "pedro");
nombres.stream().map(String::toUpperCase).forEach(System.out::println);
```
**Salida esperada:**
```
ANA
JUAN
PEDRO
```

📌 **Ejemplo: `Math::max` (Método estático)**
```java
BiFunction<Integer, Integer, Integer> maximo = Math::max;
System.out.println(maximo.apply(10, 5)); // 10
```

📌 **Ejemplo: `Empleado::getNombre` (Método de una instancia específica)**
```java
List<Empleado> empleados = List.of(new Empleado("Ana", 3000), new Empleado("Pedro", 2500));
empleados.stream().map(Empleado::getNombre).forEach(System.out::println);
```

---

# **📌 6️⃣ Ejercicios Prácticos**

📌 **Ejercicio 1:**
1. Crea una lista de números enteros y usa **Streams + Lambdas** para:
   - Filtrar los números impares.
   - Multiplicarlos por 3.
   - Ordenarlos de mayor a menor.
   - Imprimir el primer número de la lista.

📌 **Ejercicio 2:**
1. Implementa una función `Function<String, Integer>` que reciba un nombre y devuelva la cantidad de letras que tiene.

<details>
    <summary>💡 Ver solución</summary>

📌 **Solución Ejercicio 1:**
```java
Optional<Integer> resultado = numeros.stream()
        .filter(n -> n % 2 != 0)
        .map(n -> n * 3)
        .sorted((a, b) -> b - a)
        .findFirst();
resultado.ifPresent(System.out::println);
```

📌 **Solución Ejercicio 2:**
```java
Function<String, Integer> contarLetras = nombre -> nombre.length();
System.out.println(contarLetras.apply("Java")); // 4
```

</details>

---