# **📌 Día 17: Serialización y Deserialización de Objetos en Java** 🗂️

📌 **Objetivo del día:**  
✅ Aprender a **serializar** objetos en Java para guardarlos en archivos.  
✅ Comprender cómo **deserializar** objetos y recuperarlos.  
✅ Usar **`ObjectOutputStream`** y **`ObjectInputStream`** para manejar objetos.  
✅ **Ejercicio:** Implementar un **gestor de empleados que guarde y recupere objetos serializados**.

---

# **1️⃣ ¿Qué es la Serialización en Java?**

📌 **La serialización permite convertir un objeto en una secuencia de bytes** para almacenarlo o enviarlo.  
✔ Se usa para **guardar datos en archivos, bases de datos o enviarlos por red**.  
✔ Se utiliza la **interfaz `Serializable`** para indicar que un objeto puede ser serializado.

📌 **Ejemplo de uso:**
- Un **sistema de empleados** guarda cada empleado en un archivo.
- Al reiniciar el programa, se pueden **recuperar los empleados** desde el archivo.

✅ **Para serializar un objeto:**  
✔ La clase debe **implementar `Serializable`**.  
✔ Se usa **`ObjectOutputStream`** para guardar el objeto en un archivo.  
✔ Se usa **`ObjectInputStream`** para leer el objeto de un archivo.

---

# **2️⃣ Serialización en Java**

📌 **Ejemplo: Serializar un objeto (`ObjectOutputStream`)**
```java
import java.io.*;

// Clase que implementa Serializable
class Persona implements Serializable {
    private static final long serialVersionUID = 1L; // Recomendado para la versión del objeto
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre + ", Edad: " + edad);
    }
}

public class SerializarObjeto {
    public static void main(String[] args) {
        Persona persona = new Persona("Juan", 30);

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("persona.dat"))) {
            salida.writeObject(persona);
            System.out.println("✅ Persona serializada correctamente.");
        } catch (IOException e) {
            System.out.println("❌ Error al serializar: " + e.getMessage());
        }
    }
}
```
✅ **Salida esperada:**
```
✅ Persona serializada correctamente.
```

📌 **Explicación:**  
✔ `serialVersionUID` ayuda a mantener la compatibilidad de versiones.  
✔ **`ObjectOutputStream.writeObject()`** guarda el objeto en `persona.dat`.

---

# **3️⃣ Deserialización en Java**

📌 **Ejemplo: Deserializar un objeto (`ObjectInputStream`)**
```java
import java.io.*;

public class DeserializarObjeto {
    public static void main(String[] args) {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("persona.dat"))) {
            Persona persona = (Persona) entrada.readObject();
            System.out.println("✅ Persona deserializada:");
            persona.mostrarInfo();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Error al deserializar: " + e.getMessage());
        }
    }
}
```
✅ **Salida esperada:**
```
✅ Persona deserializada:
Nombre: Juan, Edad: 30
```

📌 **Explicación:**  
✔ **`ObjectInputStream.readObject()`** recupera el objeto de `persona.dat`.  
✔ Se usa **`(Persona)`** para hacer un **casting** del objeto recuperado.

---

# **4️⃣ Serializar Listas de Objetos**

📌 **Ejemplo: Guardar y recuperar una lista de empleados**

📌 **Clase `Empleado` implementando `Serializable`:**
```java
import java.io.Serializable;

class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private double salario;

    public Empleado(String nombre, double salario) {
        this.nombre = nombre;
        this.salario = salario;
    }

    public void mostrarInfo() {
        System.out.println("Empleado: " + nombre + ", Salario: " + salario + "€");
    }
}
```

📌 **Clase `GestorEmpleados` para gestionar una lista de empleados:**
```java
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorEmpleados {
    private List<Empleado> empleados;
    private static final String ARCHIVO = "empleados.dat";

    public GestorEmpleados() {
        empleados = cargarDesdeArchivo(); // Carga empleados al iniciar
    }

    // Agregar empleado a la lista
    public void agregarEmpleado(String nombre, double salario) {
        empleados.add(new Empleado(nombre, salario));
        System.out.println("✅ Empleado agregado.");
    }

    // Mostrar todos los empleados
    public void mostrarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("📂 No hay empleados guardados.");
        } else {
            System.out.println("📋 Lista de empleados:");
            empleados.forEach(Empleado::mostrarInfo);
        }
    }

    // Guardar empleados en archivo
    public void guardarEnArchivo() {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            salida.writeObject(empleados);
            System.out.println("💾 Empleados guardados.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar empleados.");
        }
    }

    // Cargar empleados desde archivo
    @SuppressWarnings("unchecked")
    private List<Empleado> cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (List<Empleado>) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠ No se pudo cargar empleados.");
            return new ArrayList<>();
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
        GestorEmpleados gestor = new GestorEmpleados();

        while (true) {
            System.out.println("\n👥 GESTOR DE EMPLEADOS");
            System.out.println("1. Agregar Empleado");
            System.out.println("2. Mostrar Empleados");
            System.out.println("3. Guardar y Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (opcion == 3) {
                gestor.guardarEnArchivo();
                System.out.println("👋 Saliendo...");
                break;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese salario: ");
                    double salario = scanner.nextDouble();
                    gestor.agregarEmpleado(nombre, salario);
                }
                case 2 -> gestor.mostrarEmpleados();
                default -> System.out.println("❌ Opción no válida.");
            }
        }
        scanner.close();
    }
}
```

✅ **Ejemplo de salida esperada:**
```
👥 GESTOR DE EMPLEADOS
1. Agregar Empleado
2. Mostrar Empleados
3. Guardar y Salir
Opción: 1
Ingrese nombre: Ana
Ingrese salario: 2500
✅ Empleado agregado.

Opción: 2
📋 Lista de empleados:
Empleado: Ana, Salario: 2500.0€

Opción: 3
💾 Empleados guardados.
👋 Saliendo...
```

📌 **Explicación:**  
✔ **Serializa una lista de empleados (`List<Empleado>`) en `empleados.dat`.**  
✔ **Carga automáticamente los empleados al iniciar el programa.**  
✔ **Menú interactivo para gestionar empleados.**

---
