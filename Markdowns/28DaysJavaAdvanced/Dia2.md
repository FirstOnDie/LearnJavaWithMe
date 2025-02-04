# **📌 Día 2: Manejo Avanzado de Colecciones en Java**

Hoy aprenderemos:  
✅ Diferencias entre `List`, `Set`, `Queue` y `Map`  
✅ Implementaciones avanzadas (`ConcurrentHashMap`, `TreeSet`, `PriorityQueue`)  
✅ **Comparators y ordenación personalizada**  
✅ **Ejercicio práctico: Simulación de un sistema de reservas**

**¡Vamos allá!** 🚀

---

Java tiene una poderosa API de **colecciones**, que nos permite almacenar y manipular datos de forma eficiente.

---

# **1️⃣ Tipos de Colecciones en Java**

📌 **Colecciones más comunes en Java:**

| Tipo | Implementaciones Comunes | Características |
|------|--------------------------|----------------|
| **List** | `ArrayList`, `LinkedList` | Permite elementos duplicados, orden por inserción |
| **Set** | `HashSet`, `TreeSet`, `LinkedHashSet` | No permite duplicados, ordenación depende de la implementación |
| **Queue** | `PriorityQueue`, `LinkedList`, `ArrayDeque` | Estructura FIFO o basada en prioridad |
| **Map** | `HashMap`, `TreeMap`, `ConcurrentHashMap` | Almacena pares clave-valor, permite búsqueda rápida |

📌 **Ejemplo de cada una:**
```java
import java.util.*;

public class EjemploColecciones {
    public static void main(String[] args) {
        // List (Permite duplicados, orden por inserción)
        List<String> lista = new ArrayList<>(Arrays.asList("Juan", "Ana", "Pedro", "Ana"));
        System.out.println("Lista: " + lista); // [Juan, Ana, Pedro, Ana]

        // Set (No permite duplicados)
        Set<String> set = new HashSet<>(lista);
        System.out.println("Set: " + set); // [Juan, Ana, Pedro]

        // Queue (FIFO)
        Queue<String> queue = new LinkedList<>(lista);
        System.out.println("Queue: " + queue);
        System.out.println("Primer elemento: " + queue.poll()); // Saca el primer elemento

        // Map (Clave-valor)
        Map<String, Integer> map = new HashMap<>();
        map.put("Juan", 30);
        map.put("Ana", 25);
        map.put("Pedro", 40);
        System.out.println("Mapa: " + map);
    }
}
```

---

# **2️⃣ Implementaciones Avanzadas**
### 🔹 **1. `TreeSet` (Set ordenado)**
📌 **Mantiene los elementos ordenados automáticamente**
```java
import java.util.TreeSet;

public class EjemploTreeSet {
    public static void main(String[] args) {
        TreeSet<Integer> numeros = new TreeSet<>();
        numeros.add(5);
        numeros.add(1);
        numeros.add(8);
        numeros.add(3);

        System.out.println("TreeSet ordenado: " + numeros); // [1, 3, 5, 8]
    }
}
```
✅ **`TreeSet` usa un árbol rojo-negro para mantener el orden.**

---

### 🔹 **2. `PriorityQueue` (Cola de prioridad)**
📌 **Ordena automáticamente los elementos según prioridad (menor a mayor por defecto)**
```java
import java.util.PriorityQueue;

public class EjemploPriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<Integer> cola = new PriorityQueue<>();
        cola.add(10);
        cola.add(5);
        cola.add(20);

        while (!cola.isEmpty()) {
            System.out.println(cola.poll()); // 5, 10, 20
        }
    }
}
```
✅ **`PriorityQueue` usa un heap binario para ordenar elementos.**

---

### 🔹 **3. `ConcurrentHashMap` (Mapa seguro para concurrencia)**
📌 **Ideal para operaciones concurrentes sin bloqueos completos**
```java
import java.util.concurrent.ConcurrentHashMap;

public class EjemploConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> mapa = new ConcurrentHashMap<>();
        mapa.put("Juan", 30);
        mapa.put("Ana", 25);
        System.out.println("ConcurrentHashMap: " + mapa);
    }
}
```
✅ **Ideal para aplicaciones multithreading.**

---

# **3️⃣ Comparators y Ordenación Personalizada**
📌 **¿Cómo ordenar listas de objetos?**

### 🔹 **Ejemplo 1: `Comparable` (Orden Natural)**
```java
import java.util.*;

class Persona implements Comparable<Persona> {
    String nombre;
    int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public int compareTo(Persona otra) {
        return Integer.compare(this.edad, otra.edad);
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + ")";
    }
}

public class EjemploComparable {
    public static void main(String[] args) {
        List<Persona> personas = Arrays.asList(
                new Persona("Ana", 25),
                new Persona("Juan", 30),
                new Persona("Pedro", 20)
        );

        Collections.sort(personas);
        System.out.println("Ordenado por edad: " + personas);
    }
}
```
📌 **Salida esperada:**
```
Ordenado por edad: [Pedro (20), Ana (25), Juan (30)]
```
✅ **Usamos `Comparable` para definir el orden natural (por edad).**

---

### 🔹 **Ejemplo 2: `Comparator` (Orden Personalizado)**
📌 **Ordenar por nombre en orden alfabético inverso**
```java
import java.util.*;

public class EjemploComparator {
    public static void main(String[] args) {
        List<Persona> personas = Arrays.asList(
                new Persona("Ana", 25),
                new Persona("Juan", 30),
                new Persona("Pedro", 20)
        );

        // Comparator para ordenar por nombre en orden inverso
        personas.sort(Comparator.comparing(Persona::getNombre).reversed());

        System.out.println("Ordenado por nombre inverso: " + personas);
    }
}
```
✅ **Más flexible que `Comparable`, se usa para múltiples criterios de ordenación.**

---

# **4️⃣ Ejercicio Práctico: Sistema de Reservas**
📌 **Queremos simular un sistema de reservas donde:**  
1️⃣ Un `TreeSet` almacena reservas ordenadas por fecha.  
2️⃣ Un `PriorityQueue` maneja la lista de espera.

<details>
    <summary>Solución</summary>

```java
import java.util.*;

class Reserva implements Comparable<Reserva> {
    String cliente;
    Date fecha;

    public Reserva(String cliente, Date fecha) {
        this.cliente = cliente;
        this.fecha = fecha;
    }

    @Override
    public int compareTo(Reserva otra) {
        return this.fecha.compareTo(otra.fecha);
    }

    @Override
    public String toString() {
        return cliente + " - " + fecha;
    }
}

public class SistemaReservas {
    public static void main(String[] args) {
        TreeSet<Reserva> reservas = new TreeSet<>();
        PriorityQueue<Reserva> listaEspera = new PriorityQueue<>();

        // Crear reservas
        reservas.add(new Reserva("Juan", new Date(2024, 6, 10)));
        reservas.add(new Reserva("Ana", new Date(2024, 6, 5)));
        reservas.add(new Reserva("Pedro", new Date(2024, 6, 20)));

        // Agregar a lista de espera
        listaEspera.add(new Reserva("Carlos", new Date(2024, 6, 15)));

        System.out.println("Reservas confirmadas:");
        reservas.forEach(System.out::println);

        System.out.println("\nLista de espera:");
        while (!listaEspera.isEmpty()) {
            System.out.println(listaEspera.poll());
        }
    }
}
```
✅ **Aplicamos `TreeSet` para ordenar reservas y `PriorityQueue` para manejar la lista de espera.**

---

</details>