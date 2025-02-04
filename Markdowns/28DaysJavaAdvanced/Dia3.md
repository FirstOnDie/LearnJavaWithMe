# **📌 Día 3: Entrada/Salida de Datos en Java (I/O y NIO)**

Hoy veremos:  
✅ **Lectura y escritura de archivos con `File`, `BufferedReader`, `PrintWriter`**  
✅ **Manejo de archivos con `Files` y `Path` (NIO.2)**  
✅ **Monitorización de archivos con `WatchService`**  
✅ **Serialización y deserialización (`ObjectInputStream`, `ObjectOutputStream`)**  
✅ **Ejercicio: Gestión de logs en archivos**

---  

En Java, el manejo de archivos se realiza a través de **dos APIs principales**:  
1️⃣ **Java I/O (`java.io`): API tradicional basada en `Streams`**  
2️⃣ **Java NIO (`java.nio`): API moderna con `Buffers` y `Channels`**

Hoy veremos **ambas** para que tengas **control total** sobre la lectura y escritura de archivos en Java.

---

## **1️⃣ Lectura y Escritura de Archivos con `File`, `BufferedReader`, `PrintWriter`**

📌 **¿Cómo leer un archivo en Java usando `BufferedReader`?**
```java
import java.io.*;

public class LeerArchivo {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("archivo.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
📌 **Explicación:**  
✅ `BufferedReader` mejora el rendimiento al leer **línea por línea**.  
✅ `try-with-resources` **cierra automáticamente** el archivo.

📌 **¿Cómo escribir en un archivo con `PrintWriter`?**
```java
import java.io.*;

public class EscribirArchivo {
    public static void main(String[] args) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("archivo.txt", true))) {
            pw.println("Nueva línea en el archivo");
            System.out.println("Texto guardado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
📌 **Explicación:**  
✅ `FileWriter("archivo.txt", true)` → **Modo append (agregar texto sin borrar el anterior)**.  
✅ `PrintWriter` permite escribir líneas de forma más cómoda.

---

## **2️⃣ Manejo de Archivos con `Files` y `Path` (NIO.2)**
📌 **¿Cómo leer un archivo completo en una lista de Strings?**
```java
import java.nio.file.*;
import java.io.IOException;
import java.util.List;

public class NioLeerArchivo {
    public static void main(String[] args) {
        try {
            List<String> lineas = Files.readAllLines(Path.of("archivo.txt"));
            lineas.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
✅ **Más rápido y simple que `BufferedReader` para archivos pequeños.**

📌 **¿Cómo escribir en un archivo con `Files.write()`?**
```java
import java.nio.file.*;
import java.io.IOException;
import java.util.Arrays;

public class NioEscribirArchivo {
    public static void main(String[] args) {
        try {
            Files.write(Path.of("archivo.txt"), Arrays.asList("Hola Mundo!", "Nueva línea"), StandardOpenOption.APPEND);
            System.out.println("Archivo actualizado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
✅ **Usamos `StandardOpenOption.APPEND` para no sobrescribir el archivo.**

---

## **3️⃣ Monitorización de Archivos con `WatchService`**
📌 **¿Cómo detectar cambios en un archivo en tiempo real?**
```java
import java.nio.file.*;

public class WatchServiceEjemplo {
    public static void main(String[] args) {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Path.of(".");
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            System.out.println("Monitorizando cambios en el directorio actual...");

            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println("Cambio detectado en: " + event.context());
                }
                key.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
📌 **Explicación:**  
✅ `WatchService` permite **detectar cambios** en archivos en **tiempo real**.  
✅ `ENTRY_MODIFY` → **Detecta cuando un archivo se modifica.**

---

## **4️⃣ Serialización y Deserialización (`ObjectOutputStream` y `ObjectInputStream`)**
📌 **¿Cómo guardar un objeto en un archivo (`.ser`)?**
```java
import java.io.*;

class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    String nombre;
    int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nombre + " - " + edad;
    }
}

public class SerializarObjeto {
    public static void main(String[] args) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("persona.ser"))) {
            Persona p = new Persona("Carlos", 30);
            oos.writeObject(p);
            System.out.println("Objeto serializado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
📌 **¿Cómo leer el objeto serializado (`persona.ser`)?**
```java
import java.io.*;

public class DeserializarObjeto {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("persona.ser"))) {
            Persona p = (Persona) ois.readObject();
            System.out.println("Objeto deserializado: " + p);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```
✅ **Permite guardar objetos en archivos y cargarlos después.**

---

# **5️⃣ Ejercicio Práctico: Gestión de Logs en Archivos**
📌 **Vamos a crear un sistema de logs donde:**  
1️⃣ **Se escriban logs en `logs.txt` cada vez que se ejecuta un programa.**  
2️⃣ **Se lean los últimos 5 logs guardados.**


<details>
    <summary>Solución</summary>

### 🔹 **Código para escribir logs**
```java
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;

public class Logger {
    private static final String LOG_FILE = "logs.txt";

    public static void escribirLog(String mensaje) {
        String log = LocalDateTime.now() + " - " + mensaje;
        try {
            Files.write(Path.of(LOG_FILE), List.of(log), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Log guardado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void leerUltimosLogs(int n) {
        try {
            List<String> lineas = Files.readAllLines(Path.of(LOG_FILE));
            lineas.stream().skip(Math.max(0, lineas.size() - n)).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        escribirLog("Inicio del programa");
        escribirLog("Usuario accedió al sistema");
        escribirLog("Error: No se encontró el archivo de configuración");
        System.out.println("Últimos 5 logs:");
        leerUltimosLogs(5);
    }
}
```
📌 **Explicación:**  
✅ **`Files.write()`** agrega logs al archivo.  
✅ **`skip()`** permite leer solo los últimos `n` registros.

</details>