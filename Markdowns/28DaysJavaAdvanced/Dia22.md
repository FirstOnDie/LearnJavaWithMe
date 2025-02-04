# **📌 Día 22: Optimización de Código en Java**
Hoy aprenderás:  
✅ **Uso de herramientas de profiling: JVisualVM y JMC**  
✅ **Estrategias para evitar memory leaks**  
✅ **Optimización del Garbage Collector (GC Tuning)**

---

📌 **¿Por qué es importante?**  
La optimización en Java mejora el **rendimiento, consumo de memoria y eficiencia del código**. Hoy veremos cómo **detectar cuellos de botella**, **evitar memory leaks** y **afinar el Garbage Collector (GC)** con herramientas como **JVisualVM** y **Java Mission Control (JMC)**.

---

# **1️⃣ Introducción al Profiling en Java**
📌 **¿Qué es el profiling?**  
El **profiling** es el análisis del comportamiento de una aplicación en ejecución para **detectar problemas de rendimiento** como:  
✔ **Uso excesivo de CPU**  
✔ **Memory leaks**  
✔ **Objetos no recolectados por el GC**  
✔ **Hilos bloqueados**

📌 **Herramientas de Profiling en Java**  

| Herramienta  | Uso Principal |
|-------------|--------------|
| **JVisualVM** | Análisis en tiempo real de memoria y CPU |
| **Java Mission Control (JMC)** | Profiling avanzado y análisis de GC |
| **YourKit Java Profiler** | Profiling profesional con UI avanzada |
| **Async Profiler** | Bajo overhead, ideal para producción |

✅ **Hoy veremos JVisualVM y JMC porque son gratuitas y fáciles de usar.**

---

# **2️⃣ Profiling con JVisualVM**
📌 **¿Cómo iniciar JVisualVM?**  
1️⃣ **Ejecuta una aplicación Java:**
```sh
java -jar mi-aplicacion.jar
```
2️⃣ **Abre JVisualVM:**
```sh
jvisualvm
```
3️⃣ **Selecciona tu aplicación en la lista de procesos.**

📌 **Características clave de JVisualVM:**  
✔ **Monitoreo de memoria y CPU en tiempo real**  
✔ **Detección de memory leaks**  
✔ **Análisis de hilos (Thread Dump)**

📌 **Ejemplo de uso:**  
✔ Abre la pestaña **"Monitor"** para ver el consumo de CPU y memoria.  
✔ Usa **"Heap Dump"** para ver qué objetos ocupan más memoria.  
✔ Captura un **Thread Dump** si la aplicación está bloqueada.

✅ **JVisualVM es ideal para detectar problemas en memoria y CPU.**

---

# **3️⃣ Profiling con Java Mission Control (JMC)**
📌 **¿Cómo iniciar JMC?**  
1️⃣ **Ejecuta una aplicación con Java Flight Recorder (JFR):**
```sh
java -XX:+FlightRecorder -jar mi-aplicacion.jar
```
2️⃣ **Abre Java Mission Control:**
```sh
jmc
```
3️⃣ **Selecciona tu aplicación y empieza un nuevo recording.**

📌 **Características clave de JMC:**  
✔ **Menos overhead que JVisualVM (ideal para producción)**  
✔ **Detección de memory leaks y pausas del GC**  
✔ **Análisis de bloqueos de hilos**

📌 **Ejemplo de uso:**  
✔ Usa **"Memory Leak Detection"** para encontrar objetos no recolectados.  
✔ En **"GC Logs"**, revisa pausas largas en el Garbage Collector.

✅ **JMC es ideal para producción porque tiene menos impacto en el rendimiento.**

---

# **4️⃣ Evitando Memory Leaks en Java**
📌 **¿Qué es un memory leak en Java?**  
Un **memory leak** ocurre cuando objetos en memoria **no se liberan** porque siguen referenciados aunque ya no sean necesarios.

📌 **Ejemplo clásico de memory leak:**
```java
import java.util.*;

public class MemoryLeakExample {
    private static final List<byte[]> lista = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            lista.add(new byte[10_000_000]); // Agregamos 10MB de datos sin liberar memoria
        }
    }
}
```
📌 **¿Cómo evitarlo?**  
✔ **Usar estructuras de datos adecuadas (`WeakReference`, `SoftReference`)**  
✔ **Evitar variables estáticas que acumulan datos**  
✔ **Cerrar conexiones (JDBC, archivos, sockets)**  
✔ **Eliminar listeners o callbacks no usados**

📌 **Ejemplo de uso de `WeakReference`:**
```java
import java.lang.ref.WeakReference;

public class WeakReferenceExample {
    public static void main(String[] args) {
        WeakReference<String> referencia = new WeakReference<>(new String("Hola"));
        System.out.println("Antes del GC: " + referencia.get());
        System.gc();
        System.out.println("Después del GC: " + referencia.get());
    }
}
```
✅ **El objeto puede ser liberado por el GC cuando es necesario.**

---

# **5️⃣ Optimizando el Garbage Collector (GC Tuning)**
📌 **¿Cómo funciona el GC en Java?**  
✔ El **Garbage Collector** elimina objetos no referenciados para liberar memoria.  
✔ Java tiene varios algoritmos de GC, cada uno con ventajas distintas.

📌 **Tipos de Garbage Collector en Java**  

| **GC** | **Uso recomendado** |
|--------|-------------------|
| **Serial GC** | Aplicaciones pequeñas con poca memoria |
| **Parallel GC** | Servidores con múltiples hilos |
| **G1 GC** (por defecto en Java 11) | Equilibrio entre rendimiento y latencia |
| **ZGC** (Java 15+) | Latencias ultra bajas en sistemas grandes |

📌 **Cómo seleccionar un GC**  
✔ **Para apps pequeñas:** `-XX:+UseSerialGC`  
✔ **Para servidores web:** `-XX:+UseG1GC`  
✔ **Para baja latencia:** `-XX:+UseZGC`

📌 **Ejemplo de configuración avanzada:**
```sh
java -Xms512m -Xmx2g -XX:+UseG1GC -jar mi-aplicacion.jar
```
✔ **`-Xms512m`** → Memoria inicial: 512MB  
✔ **`-Xmx2g`** → Memoria máxima: 2GB  
✔ **`-XX:+UseG1GC`** → Usa el Garbage Collector G1

📌 **Cómo monitorear el GC en tiempo real:**
```sh
jstat -gc PID 1s
```
📌 **Ejemplo de salida:**
```
 S0C    S1C    E    O     M    CCS  YGC   YGCT   FGC   FGCT    GCT
 5120   5120   8000  20480  10240  5120   15     0.05   3     0.12   0.17
```
✔ **Si el `FGC` (Full GC) es alto, el rendimiento puede verse afectado.**

