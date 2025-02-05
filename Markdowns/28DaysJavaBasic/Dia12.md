# **📌 Día 12: Colecciones en Java (`List`, `Set`, `Map`)** 🚀

📌 **Objetivo del día:**  
✅ Conocer y usar las colecciones más importantes de Java:
- **`List` (`ArrayList`, `LinkedList`)** → Listas ordenadas con elementos duplicados.
- **`Set` (`HashSet`)** → Conjunto de elementos únicos sin orden específico.
- **`Map` (`HashMap`)** → Estructura clave-valor para búsquedas rápidas.

✅ Comparar **rendimiento y usos** de cada estructura.  
✅ **Ejercicio:** Implementar una **lista de contactos con `HashMap`**.

---

## **1️⃣ Introducción a las Colecciones en Java**

📌 **¿Qué es una colección?**  
✔ Es una estructura que almacena múltiples elementos.  
✔ Java proporciona la API `java.util.Collection` para gestionar datos.  
✔ Las colecciones más usadas son **List, Set y Map**.

📌 **Resumen de estructuras de datos:**

| Colección | Características | Permite duplicados? | Ordenado? | Clave-Valor? |
|-----------|----------------|-----------------|---------|-------------|
| **`ArrayList`** | Lista dinámica basada en arrays. Rápido acceso por índice. | ✅ Sí | ✅ Sí | ❌ No |
| **`LinkedList`** | Lista doblemente enlazada. Rápido en inserciones/borrados. | ✅ Sí | ✅ Sí | ❌ No |
| **`HashSet`** | Conjunto de elementos únicos. Sin orden específico. | ❌ No | ❌ No | ❌ No |
| **`HashMap`** | Almacena pares clave-valor. Búsqueda rápida. | 🔹 N/A | ❌ No | ✅ Sí |

---

# **2️⃣ `List` en Java: `ArrayList` vs `LinkedList`**

📌 **`ArrayList` (Lista Dinámica)**  
✔ Basado en **arrays dinámicos**, acceso rápido (`O(1)`).  
✔ **Lento en inserciones/borrados intermedios** (`O(n)`).

📌 **Ejemplo de `ArrayList`:**
```java
import java.util.ArrayList;

public class EjemploArrayList {
    public static void main(String[] args) {
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add("Ana");
        nombres.add("Carlos");
        nombres.add("Beatriz");

        System.out.println(nombres.get(1)); // Accede al índice 1: "Carlos"
    }
}
```
✅ **Salida esperada:**
```
Carlos
```

---

📌 **`LinkedList` (Lista Enlazada)**  
✔ Basado en **nodos enlazados**, rápido en **inserciones/borrados** (`O(1)`).  
✔ **Lento en acceso por índice** (`O(n)`).

📌 **Ejemplo de `LinkedList`:**
```java
import java.util.LinkedList;

public class EjemploLinkedList {
    public static void main(String[] args) {
        LinkedList<String> tareas = new LinkedList<>();
        tareas.add("Estudiar Java");
        tareas.addFirst("Hacer café"); // Agrega al inicio

        System.out.println(tareas.getFirst()); // "Hacer café"
    }
}
```
✅ **Salida esperada:**
```
Hacer café
```

📌 **¿Cuándo usar `ArrayList` vs `LinkedList`?**

| Operación | Mejor en `ArrayList` | Mejor en `LinkedList` |
|-----------|--------------------|--------------------|
| **Acceso por índice (`get(i)`)** | ✅ | ❌ |
| **Inserción/Borrado en medio** | ❌ | ✅ |
| **Agregar al final (`add()`)** | ✅ | ✅ |

---

# **3️⃣ `Set` en Java: `HashSet` (Conjunto de Elementos Únicos)**

📌 **`HashSet` (Conjunto sin duplicados)**  
✔ **Solo permite valores únicos**.  
✔ No garantiza un orden específico.  
✔ Basado en **tablas hash** (`O(1)` para inserción/búsqueda).

📌 **Ejemplo de `HashSet`:**
```java
import java.util.HashSet;

public class EjemploHashSet {
    public static void main(String[] args) {
        HashSet<String> colores = new HashSet<>();
        colores.add("Rojo");
        colores.add("Azul");
        colores.add("Rojo"); // ❌ Duplicado (se ignora)

        System.out.println(colores); // No garantiza orden
    }
}
```
✅ **Salida esperada (orden no garantizado):**
```
[Rojo, Azul]
```

✔ **Usa `HashSet` cuando necesitas valores únicos y no importa el orden.**

---

# **4️⃣ `Map` en Java: `HashMap` (Clave-Valor)**

📌 **`HashMap` (Diccionario Clave-Valor)**  
✔ Almacena **pares clave-valor** (`nombre -> teléfono`).  
✔ **Búsqueda rápida** (`O(1)`) gracias a **tablas hash**.  
✔ No garantiza orden de los elementos.

📌 **Ejemplo de `HashMap`:**
```java
import java.util.HashMap;

public class EjemploHashMap {
    public static void main(String[] args) {
        HashMap<String, Integer> edades = new HashMap<>();
        edades.put("Ana", 25);
        edades.put("Carlos", 30);
        
        System.out.println("Edad de Ana: " + edades.get("Ana"));
    }
}
```
✅ **Salida esperada:**
```
Edad de Ana: 25
```

📌 **Recorrer un `HashMap`:**
```java
for (String nombre : edades.keySet()) {
    System.out.println(nombre + " tiene " + edades.get(nombre) + " años.");
}
```

✅ **Salida esperada:**
```
Ana tiene 25 años.  
Carlos tiene 30 años.
```

✔ **Usa `HashMap` para búsquedas rápidas en grandes volúmenes de datos.**

---

# **📌 Ejercicio del Día 12: Lista de Contactos con `HashMap`** 🎯

📌 **Objetivo:**  
✔ Crear un **`HashMap<String, String>`** para almacenar contactos (`nombre -> teléfono`).  
✔ Implementar métodos para **agregar, buscar y mostrar contactos**.

📌 **Ejemplo de salida esperada:**
```
📞 Agenda de Contactos
1. Agregar Contacto
2. Buscar Contacto
3. Mostrar Todos
4. Salir
Opción: 1
Nombre: Juan
Teléfono: 555-1234
Contacto agregado.

Opción: 2
Nombre: Juan
Teléfono: 555-1234

Opción: 3
Lista de contactos:
- Juan: 555-1234
```

---

<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
📌 **Clase `AgendaContactos` con un `HashMap`:**
```java
import java.util.HashMap;
import java.util.Scanner;

public class AgendaContactos {
    private HashMap<String, String> contactos = new HashMap<>();

    // Método para agregar contacto
    public void agregarContacto(String nombre, String telefono) {
        contactos.put(nombre, telefono);
        System.out.println("📌 Contacto agregado.");
    }

    // Método para buscar un contacto
    public void buscarContacto(String nombre) {
        if (contactos.containsKey(nombre)) {
            System.out.println(nombre + " -> " + contactos.get(nombre));
        } else {
            System.out.println("❌ Contacto no encontrado.");
        }
    }

    // Método para mostrar todos los contactos
    public void mostrarContactos() {
        System.out.println("📞 Lista de contactos:");
        for (String nombre : contactos.keySet()) {
            System.out.println("- " + nombre + ": " + contactos.get(nombre));
        }
    }
}
```

📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AgendaContactos agenda = new AgendaContactos();

        while (true) {
            System.out.println("\n📞 Agenda de Contactos");
            System.out.println("1. Agregar Contacto");
            System.out.println("2. Buscar Contacto");
            System.out.println("3. Mostrar Todos");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 4) break;

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            if (opcion == 1) {
                System.out.print("Teléfono: ");
                String telefono = scanner.nextLine();
                agenda.agregarContacto(nombre, telefono);
            } else if (opcion == 2) {
                agenda.buscarContacto(nombre);
            } else if (opcion == 3) {
                agenda.mostrarContactos();
            }
        }
        scanner.close();
    }
}
```

</details>