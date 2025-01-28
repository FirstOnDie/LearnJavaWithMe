
# **Streams** 💻✨

## **¿Qué son los Streams?**
Un `Stream` en Java es una secuencia de datos que se puede procesar de manera declarativa (al estilo de SQL) para realizar transformaciones y filtrados en colecciones de datos o cualquier otra fuente.

### **Características clave**
- **Declarativo**: En lugar de bucles tradicionales (`for`, `while`), usas métodos como `filter` o `map` para describir el proceso.
- **Inmutable**: Los streams no modifican los datos originales; trabajan sobre una copia.
- **Lazy evaluation**: Los datos no se procesan hasta que se realiza una operación terminal.
- **Paralelismo**: Los streams pueden aprovechar múltiples hilos con `parallelStream`.

---

## **Operaciones de un Stream**
Los streams trabajan con dos tipos principales de operaciones:

### 1. **Operaciones intermedias** (transforman el stream):
- **`filter(Predicate)`**: Filtra elementos que cumplan una condición.
- **`map(Function)`**: Transforma cada elemento.
- **`sorted()`**: Ordena los elementos.
- **`distinct()`**: Elimina duplicados.
- **`limit(n)`**: Limita el número de elementos.
- **`skip(n)`**: Salta los primeros `n` elementos.

### 2. **Operaciones terminales** (cierran el stream y devuelven un resultado):
- **`collect(Collector)`**: Convierte el stream en una colección.
- **`forEach(Consumer)`**: Itera sobre cada elemento.
- **`reduce(BinaryOperator)`**: Combina elementos para producir un único valor.
- **`count()`**: Devuelve la cantidad de elementos.
- **`anyMatch/noneMatch/allMatch(Predicate)`**: Devuelve booleanos en base a condiciones.

---

## **Creación de Streams**

Los streams se pueden crear a partir de:
1. **Colecciones**:
   ```java
   List<Integer> lista = List.of(1, 2, 3);
   Stream<Integer> stream = lista.stream();
   ```
2. **Arrays**:
   ```java
   int[] numeros = {1, 2, 3};
   IntStream stream = Arrays.stream(numeros);
   ```
3. **Stream directo**:
   ```java
   Stream<String> stream = Stream.of("A", "B", "C");
   ```
4. **Streams infinitos**:
   ```java
   Stream<Integer> infinito = Stream.iterate(1, n -> n + 1); // 1, 2, 3...
   ```

---

## **Ejemplo práctico completo**

### **Caso práctico: Procesar una lista de empleados**
Queremos filtrar empleados con un salario mayor a 2000, ordenar los resultados y obtener una lista con sus nombres.

### **Código**
```java
import java.util.*;
import java.util.stream.Collectors;

class Empleado {
    private String nombre;
    private double salario;

    public Empleado(String nombre, double salario) {
        this.nombre = nombre;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombre='" + nombre + '\'' + ", salario=" + salario + '}';
    }
}

public class StreamsEjemplo {
    public static void main(String[] args) {
        List<Empleado> empleados = Arrays.asList(
                new Empleado("Juan", 1800),
                new Empleado("Ana", 2500),
                new Empleado("Pedro", 3000),
                new Empleado("Marta", 2000)
        );

        // Procesar la lista de empleados
        List<String> nombres = empleados.stream()
                .filter(e -> e.getSalario() > 2000)       // Filtrar por salario
                .sorted(Comparator.comparing(Empleado::getSalario)) // Ordenar por salario
                .map(Empleado::getNombre)                // Obtener solo los nombres
                .collect(Collectors.toList());           // Recoger en una lista

        System.out.println("Nombres de empleados filtrados: " + nombres);
    }
}
```

### **Salida esperada**
```
Nombres de empleados filtrados: [Ana, Pedro]
```

---

## **Explicación detallada**
1. **Creación del stream**:  
   `empleados.stream()` crea un stream a partir de la lista de empleados.

2. **Filtrar elementos**:  
   `.filter(e -> e.getSalario() > 2000)` incluye solo empleados con salario mayor a 2000.

3. **Ordenar por salario**:  
   `.sorted(Comparator.comparing(Empleado::getSalario))` ordena los empleados de menor a mayor salario.

4. **Transformar datos**:  
   `.map(Empleado::getNombre)` convierte cada empleado en su nombre (String).

5. **Recolectar en una lista**:  
   `.collect(Collectors.toList())` convierte el resultado en una lista.

---

## **Tarea práctica para ti** 📝
1. Dada una lista de números enteros, realiza las siguientes operaciones usando streams:
    - Filtra los números que sean mayores que 5.
    - Eleva al cuadrado los números filtrados.
    - Ordena los números de mayor a menor.
    - Devuelve el primer número de la lista (usando `findFirst()`).
