# **📌 Streams en Java** 🌊🚀

📌 **¿Qué son los Streams?**  
Un **Stream** en Java es una **secuencia de datos** que permite realizar operaciones sobre colecciones de manera **declarativa**, **eficiente** y **paralela**.

📌 **¿Por qué usar Streams?**  
✔ **Código más limpio y legible** → Evita bucles innecesarios.  
✔ **Mejor rendimiento** → Usa evaluación perezosa (**lazy evaluation**).  
✔ **Optimización automática** → Puede ejecutarse en paralelo fácilmente.

💡 **Ejemplo real:**  
Imagina que tienes una **lista de pedidos** en una tienda online. Con **Streams**, puedes:  
✔ **Filtrar los pedidos mayores a $100** 🛒.  
✔ **Ordenarlos por fecha** 📅.  
✔ **Obtener el total de ventas** 💰.

📌 **Ejemplo sin Streams (forma tradicional)**
```java
List<Integer> numeros = List.of(1, 2, 3, 4, 5, 6);
List<Integer> cuadrados = new ArrayList<>();
for (Integer num : numeros) {
    if (num > 2) {
        cuadrados.add(num * num);
    }
}
System.out.println(cuadrados); // [9, 16, 25, 36]
```
📌 **Ejemplo con Streams (forma optimizada)**
```java
List<Integer> cuadrados = numeros.stream()
        .filter(n -> n > 2)   // Filtra números mayores que 2
        .map(n -> n * n)      // Eleva al cuadrado
        .collect(Collectors.toList()); // Guarda en una lista
System.out.println(cuadrados); // [9, 16, 25, 36]
```
✅ **¡Menos código y más claridad!** 🚀

---

# **📌 Características clave de Streams**

✅ **Declarativo** → Usa métodos como `filter()`, `map()`, `reduce()`.  
✅ **Inmutable** → No modifica la colección original.  
✅ **Lazy evaluation** → No ejecuta operaciones hasta una "operación terminal".  
✅ **Paralelo** → Puede ejecutarse en **múltiples hilos** (`parallelStream()`).

---

# **📌 Operaciones en Streams**

📌 **Streams tienen dos tipos de operaciones:**

🔹 **Operaciones intermedias** → Transforman el Stream, pero **no ejecutan** nada hasta que se llame una operación terminal.  
🔹 **Operaciones terminales** → Ejecutan el Stream y devuelven un resultado (lista, número, booleano, etc.).

📌 **Ejemplo gráfico:**
```java
List<Integer> resultado = numeros.stream()   // (1) Creamos el Stream
        .filter(n -> n > 2)                  // (2) Filtramos (intermedia)
        .map(n -> n * n)                     // (3) Transformamos (intermedia)
        .sorted()                             // (4) Ordenamos (intermedia)
        .collect(Collectors.toList());        // (5) Guardamos (terminal)
```
✅ **Hasta que llamamos `collect()`, no se ejecuta nada!**

---

# **📌 1️⃣ Operaciones Intermedias (Transforman el Stream)**

📌 **`filter(Predicate)`** → Filtra elementos según una condición.
```java
List<String> nombres = List.of("Ana", "Pedro", "Juan", "Marta");
List<String> nombresCortos = nombres.stream()
        .filter(n -> n.length() <= 4)
        .collect(Collectors.toList());
System.out.println(nombresCortos); // [Ana, Juan]
```

📌 **`map(Function)`** → Transforma cada elemento en otro.
```java
List<Integer> numeros = List.of(1, 2, 3, 4);
List<Integer> cuadrados = numeros.stream()
        .map(n -> n * n)
        .collect(Collectors.toList());
System.out.println(cuadrados); // [1, 4, 9, 16]
```

📌 **`sorted(Comparator)`** → Ordena los elementos.
```java
List<Integer> numeros = List.of(5, 1, 4, 2);
List<Integer> ordenados = numeros.stream()
        .sorted()
        .collect(Collectors.toList());
System.out.println(ordenados); // [1, 2, 4, 5]
```

📌 **`distinct()`** → Elimina duplicados.
```java
List<Integer> numeros = List.of(1, 2, 2, 3, 3, 3);
List<Integer> sinDuplicados = numeros.stream()
        .distinct()
        .collect(Collectors.toList());
System.out.println(sinDuplicados); // [1, 2, 3]
```

📌 **`limit(n)`** → Toma los primeros `n` elementos.
```java
List<Integer> primeros = numeros.stream()
        .limit(3)
        .collect(Collectors.toList());
System.out.println(primeros); // [1, 2, 2]
```

📌 **`skip(n)`** → Salta los primeros `n` elementos.
```java
List<Integer> saltados = numeros.stream()
        .skip(2)
        .collect(Collectors.toList());
System.out.println(saltados); // [2, 3, 3]
```

---

# **📌 2️⃣ Operaciones Terminales (Ejecutan el Stream)**

📌 **`collect(Collector)`** → Convierte el Stream en una colección.
```java
Set<String> nombresSet = nombres.stream()
        .collect(Collectors.toSet()); // Devuelve un Set en lugar de una lista
```

📌 **`forEach(Consumer)`** → Itera sobre cada elemento.
```java
nombres.stream().forEach(System.out::println);
```

📌 **`reduce(BinaryOperator)`** → Combina elementos en un solo resultado.
```java
int suma = numeros.stream().reduce(0, Integer::sum);
System.out.println(suma); // 10
```

📌 **`count()`** → Devuelve la cantidad de elementos.
```java
long cantidad = numeros.stream().count();
```

📌 **`anyMatch/noneMatch/allMatch(Predicate)`** → Devuelven `true` o `false`.
```java
boolean hayPares = numeros.stream().anyMatch(n -> n % 2 == 0);
boolean todosPositivos = numeros.stream().allMatch(n -> n > 0);
boolean ningunNegativo = numeros.stream().noneMatch(n -> n < 0);
```

---

# **📌 3️⃣ Creación de Streams**

📌 **Desde una lista:**
```java
List<Integer> lista = List.of(1, 2, 3);
Stream<Integer> stream = lista.stream();
```

📌 **Desde un array:**
```java
int[] numeros = {1, 2, 3};
IntStream stream = Arrays.stream(numeros);
```

📌 **Usando `Stream.of(...)`**
```java
Stream<String> stream = Stream.of("A", "B", "C");
```

📌 **Streams Infinitos** (`iterate()` y `generate()`)
```java
Stream<Integer> infinito = Stream.iterate(1, n -> n + 1); // 1, 2, 3...
```

---

# **📌 4️⃣ Ejemplo Completo** 🚀

📌 **Filtrar empleados con salario > 2000, ordenarlos y obtener sus nombres.**
```java
List<String> nombres = empleados.stream()
        .filter(e -> e.getSalario() > 2000)
        .sorted(Comparator.comparing(Empleado::getSalario))
        .map(Empleado::getNombre)
        .collect(Collectors.toList());
System.out.println(nombres);
```

✅ **Salida esperada:**
```
Nombres de empleados filtrados: [Ana, Pedro]
```

---
