# **📌 Día 1: Programación Funcional en Java**

Hoy aprenderemos:  
✅ **Expresiones Lambda y Method References**  
✅ **Uso avanzado de Streams API**  
✅ **Collectors (Grouping, Partitioning, Joining)**  
✅ **Ejercicio completo de procesamiento de datos con Streams**

Al final, tendrás **ejercicios prácticos** para reforzar lo aprendido. 💪

---

# **1️⃣ Expresiones Lambda en Java**

📌 **¿Qué es una Lambda?**  
Una **expresión Lambda** es una función anónima que se puede pasar como argumento a métodos o almacenar en una variable.

### 🔹 **Sintaxis básica de una Lambda**
```java
(parametros) -> { cuerpo de la función }
```

📌 **Ejemplo básico:**
```java
// Definir una función que sume dos números usando Lambda
interface Suma {
    int operar(int a, int b);
}

public class EjemploLambda {
    public static void main(String[] args) {
        Suma suma = (a, b) -> a + b;
        System.out.println(suma.operar(5, 3)); // 8
    }
}
```

📌 **Reglas de sintaxis en Lambdas:**  
✅ **Si hay un solo parámetro, se pueden omitir los paréntesis:**
```java
s -> s.length();
```
✅ **Si el cuerpo tiene solo una línea, `{}` y `return` son opcionales:**
```java
(a, b) -> a + b;
```
✅ **Si no hay parámetros, usa `()`**
```java
() -> System.out.println("Hola Mundo!");
```

---

# **2️⃣ Method References en Java**

📌 **¿Qué son?**  
Un **Method Reference** es una forma más corta de escribir Lambdas cuando solo llamamos un método existente.

📌 **Ejemplo de Lambda vs. Method Reference**
```java
import java.util.function.Function;

public class EjemploMethodReference {
    public static void main(String[] args) {
        // Lambda normal
        Function<String, Integer> lambda = s -> s.length();

        // Method Reference equivalente
        Function<String, Integer> methodRef = String::length;

        System.out.println(lambda.apply("Hola")); // 4
        System.out.println(methodRef.apply("Mundo")); // 5
    }
}
```

📌 **Tipos de Method References:**  
| Tipo | Ejemplo Lambda | Ejemplo Method Reference |
|------|---------------|-------------------------|
| Método estático | `(a, b) -> Math.max(a, b)` | `Math::max` |
| Método de instancia | `s -> s.toUpperCase()` | `String::toUpperCase` |
| Método de instancia de un objeto específico | `(p) -> persona.getNombre()` | `persona::getNombre` |
| Constructor | `(String s) -> new Persona(s)` | `Persona::new` |

✅ **Usar Method References mejora la legibilidad del código.**

---

# **3️⃣ Streams API: Operaciones Avanzadas**

📌 **¿Qué es un Stream en Java?**  
Un **Stream** permite procesar datos de manera funcional y declarativa.

📌 **Ejemplo: Sin Streams vs. Con Streams**
```java
import java.util.Arrays;
import java.util.List;

public class SinStreams {
    public static void main(String[] args) {
        List<String> nombres = Arrays.asList("Ana", "Miguel", "Marta", "Juan", "Mario");

        // Sin Streams
        for (String nombre : nombres) {
            if (nombre.startsWith("M")) {
                System.out.println(nombre.toUpperCase());
            }
        }
    }
}
```
📌 **Con Streams:**
```java
import java.util.Arrays;
import java.util.List;

public class ConStreams {
    public static void main(String[] args) {
        List<String> nombres = Arrays.asList("Ana", "Miguel", "Marta", "Juan", "Mario");

        nombres.stream()
                .filter(n -> n.startsWith("M"))
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
```
✅ **Código más corto y declarativo.**

---

## **4️⃣ Collectors en Java**
📌 **¿Qué es `Collectors`?**  
Es una clase que nos permite recopilar los resultados de un Stream en estructuras como **listas, mapas, cadenas de texto**, etc.

### **🔹 1. Collecting en una Lista**
```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectLista {
    public static void main(String[] args) {
        List<String> nombres = Arrays.asList("Ana", "Miguel", "Marta", "Juan", "Mario");

        List<String> nombresFiltrados = nombres.stream()
                .filter(n -> n.startsWith("M"))
                .collect(Collectors.toList());

        System.out.println(nombresFiltrados); // [Miguel, Marta, Mario]
    }
}
```

---

### **🔹 2. Grouping (Agrupación de Datos)**
📌 **Agrupa elementos en un `Map<K, List<V>>`.**
```java
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Grouping {
    public static void main(String[] args) {
        List<String> palabras = Arrays.asList("Java", "Spring", "Hibernate", "JPA", "React", "JavaScript");

        Map<Integer, List<String>> grupos = palabras.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println(grupos);
    }
}
```
📌 **Salida esperada:**
```
{
    4=[Java, JPA],
    6=[Spring],
    8=[Hibernate],
    10=[JavaScript]
}
```
✅ **Agrupamos palabras por cantidad de letras.**

---

### **🔹 3. Partitioning (Dividir en dos grupos)**
📌 **Divide los datos en dos grupos (`true` y `false`) según un `Predicate`.**
```java
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Partitioning {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(5, 12, 8, 20, 30, 7);

        Map<Boolean, List<Integer>> paresImpares = numeros.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        System.out.println(paresImpares);
    }
}
```
📌 **Salida esperada:**
```
{
    false=[5, 7],
    true=[12, 8, 20, 30]
}
```
✅ **Separa los números en pares e impares.**

---

# **📌 Ejercicio Final: Procesamiento Completo con Streams**
📌 **Dada una lista de empleados, queremos:**  
1️⃣ Filtrar empleados con salario mayor a 3000  
2️⃣ Convertir sus nombres a mayúsculas  
3️⃣ Agruparlos por cargo  
4️⃣ Obtener un `Map<String, List<String>>` donde la clave es el cargo y el valor la lista de nombres

<details>
    <summary>Solución</summary>

```java
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Empleado {
    String nombre;
    String cargo;
    double salario;

    public Empleado(String nombre, String cargo, double salario) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getNombre() { return nombre; }
    public String getCargo() { return cargo; }
    public double getSalario() { return salario; }
}

public class EjercicioStreams {
    public static void main(String[] args) {
        List<Empleado> empleados = Arrays.asList(
                new Empleado("Ana", "Desarrollador", 4000),
                new Empleado("Pedro", "Gerente", 6000),
                new Empleado("Laura", "Desarrollador", 2500),
                new Empleado("Carlos", "Analista", 3200)
        );

        Map<String, List<String>> resultado = empleados.stream()
                .filter(e -> e.getSalario() > 3000)
                .collect(Collectors.groupingBy(
                        Empleado::getCargo, 
                        Collectors.mapping(Empleado::getNombre, Collectors.toList())
                ));

        System.out.println(resultado);
    }
}
```

</details>