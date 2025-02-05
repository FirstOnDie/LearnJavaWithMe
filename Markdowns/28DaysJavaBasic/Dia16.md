# **📌 Día 16: Entrada/Salida de Datos en Java (I/O y NIO)** 📝

📌 **Objetivo del día:**  
✅ Aprender a **leer y escribir archivos** en Java usando `java.io` y `java.nio`.  
✅ Conocer la diferencia entre **I/O (Input/Output) tradicional y NIO (New I/O)**.  
✅ **Ejercicio:** Implementar un **gestor de notas que guarde y lea archivos de texto**.

---

# **1️⃣ Introducción a Entrada y Salida en Java**

📌 **Java proporciona dos formas principales de manejar archivos:**

| API | Características |
|------|---------------|
| `java.io` (I/O tradicional) | ✔ Basado en **flujos de datos** (`FileReader`, `BufferedReader`). |
| `java.nio` (New I/O) | ✔ Basado en **buffers y canales**, más rápido para archivos grandes. |

---

# **2️⃣ Manejo de Archivos con `java.io` (I/O Tradicional)**

📌 **Leer un archivo con `BufferedReader` (Eficiente en lectura línea por línea)**
```java
import java.io.*;

public class LeerArchivo {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("notas.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("❌ Error al leer el archivo: " + e.getMessage());
        }
    }
}
```

✅ **Salida esperada (`notas.txt` contiene texto):**
```
Tarea 1: Estudiar Java
Tarea 2: Practicar con Streams
```

📌 **Escribir en un archivo con `BufferedWriter`**
```java
import java.io.*;

public class EscribirArchivo {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("notas.txt", true))) {
            writer.write("Nueva tarea: Aprender NIO");
            writer.newLine();
            System.out.println("✅ Nota guardada.");
        } catch (IOException e) {
            System.out.println("❌ Error al escribir en el archivo.");
        }
    }
}
```

✅ **Salida esperada en `notas.txt`:**
```
Nueva tarea: Aprender NIO
```

✔ **`FileWriter("notas.txt", true)` agrega contenido sin borrar el archivo.**

---

# **3️⃣ Manejo de Archivos con `java.nio` (New I/O)**

📌 **Usar `Files.readAllLines()` para leer un archivo rápido:**
```java
import java.nio.file.*;
import java.io.IOException;
import java.util.List;

public class LeerNIO {
    public static void main(String[] args) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get("notas.txt"));
            lineas.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("❌ Error al leer el archivo.");
        }
    }
}
```

📌 **Usar `Files.write()` para escribir en un archivo:**
```java
import java.nio.file.*;
import java.io.IOException;
import java.util.Arrays;

public class EscribirNIO {
    public static void main(String[] args) {
        try {
            Files.write(Paths.get("notas.txt"), Arrays.asList("Otra tarea: Aprender Streams"), StandardOpenOption.APPEND);
            System.out.println("✅ Nota guardada con NIO.");
        } catch (IOException e) {
            System.out.println("❌ Error al escribir en el archivo.");
        }
    }
}
```

✅ **Ventajas de `java.nio.file.Files`**  
✔ **Menos código** para operaciones de archivos.  
✔ **Más rápido** para archivos grandes.

---

# **4️⃣ Otras Operaciones con Archivos**

📌 **Verificar si un archivo existe:**
```java
import java.nio.file.*;

public class VerificarArchivo {
    public static void main(String[] args) {
        Path path = Paths.get("notas.txt");
        if (Files.exists(path)) {
            System.out.println("📂 El archivo existe.");
        } else {
            System.out.println("❌ El archivo no existe.");
        }
    }
}
```

📌 **Eliminar un archivo:**
```java
import java.nio.file.*;

public class EliminarArchivo {
    public static void main(String[] args) {
        try {
            Files.deleteIfExists(Paths.get("notas.txt"));
            System.out.println("✅ Archivo eliminado.");
        } catch (Exception e) {
            System.out.println("❌ No se pudo eliminar el archivo.");
        }
    }
}
```

---

# **📌 Ejercicio del Día 16: Gestor de Notas** 🎯

📌 **Objetivo:**  
✔ Implementar una **clase `GestorNotas`** para manejar un archivo de notas.  
✔ Métodos:
- `agregarNota(String nota)` → Añadir una nota al archivo.
- `mostrarNotas()` → Leer y mostrar todas las notas.

📌 **Ejemplo de salida esperada:**
```
📜 Notas Guardadas:
1. Estudiar Java
2. Practicar Streams
```

---
<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
📌 **Clase `GestorNotas` con `java.nio.file.Files`**
```java
import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class GestorNotas {
    private static final String ARCHIVO = "notas.txt";

    // Método para agregar una nota
    public void agregarNota(String nota) {
        try {
            Files.write(Paths.get(ARCHIVO), List.of(nota), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("✅ Nota agregada.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar la nota.");
        }
    }

    // Método para mostrar todas las notas
    public void mostrarNotas() {
        try {
            List<String> notas = Files.readAllLines(Paths.get(ARCHIVO));
            if (notas.isEmpty()) {
                System.out.println("📜 No hay notas guardadas.");
            } else {
                System.out.println("📜 Notas Guardadas:");
                for (int i = 0; i < notas.size(); i++) {
                    System.out.println((i + 1) + ". " + notas.get(i));
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error al leer las notas.");
        }
    }
}
```

📌 **Clase `Main` con menú interactivo:**
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorNotas gestor = new GestorNotas();

        while (true) {
            System.out.println("\n📌 GESTOR DE NOTAS");
            System.out.println("1. Agregar Nota");
            System.out.println("2. Mostrar Notas");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (opcion == 3) break;

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese la nota: ");
                    String nota = scanner.nextLine();
                    gestor.agregarNota(nota);
                }
                case 2 -> gestor.mostrarNotas();
                default -> System.out.println("❌ Opción no válida.");
            }
        }
        scanner.close();
    }
}
```

✅ **Ejemplo de salida esperada:**
```
📌 GESTOR DE NOTAS
1. Agregar Nota
2. Mostrar Notas
3. Salir
Opción: 1
Ingrese la nota: Aprender Java I/O
✅ Nota agregada.

Opción: 2
📜 Notas Guardadas:
1. Aprender Java I/O
```

📌 **Explicación:**  
✔ **`Files.write()`** añade notas al archivo `notas.txt`.  
✔ **`Files.readAllLines()`** lee y muestra las notas almacenadas.  
✔ **Menú interactivo** para gestionar notas fácilmente.

---

</details>