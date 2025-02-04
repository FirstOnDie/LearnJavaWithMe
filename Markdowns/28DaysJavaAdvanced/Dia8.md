# **📌 Día 8: Hilos Avanzados en Java**
Hoy aprenderás:  
✅ **ThreadPool y Executors** (manejo eficiente de hilos)  
✅ **Fork/Join Framework** (paralelismo eficiente en grandes tareas)  
✅ **CompletableFuture** (programación asíncrona moderna)  
✅ **Ejercicio: Simulación de descarga de archivos concurrente**

---

**¿Por qué es importante?**  
El manejo eficiente de **hilos y concurrencia** es clave para aplicaciones que requieren **alta performance**, como **servidores web, procesamiento de datos masivo y aplicaciones en la nube**.

---


# **1️⃣ ThreadPool y Executors en Java**

📌 **¿Por qué no crear hilos manualmente?**  
Si usamos `new Thread()`, cada hilo se crea y destruye individualmente, lo que es **ineficiente** y genera sobrecarga.

📌 **¿Solución?**  
Usamos **ThreadPool** con `Executors`, que gestiona un conjunto fijo de hilos **reutilizables**.

---

## **🔹 Ejemplo 1: Usar `ExecutorService` con un `FixedThreadPool`**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolEjemplo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3); // Pool de 3 hilos

        for (int i = 1; i <= 5; i++) {
            final int tareaId = i;
            executor.execute(() -> {
                System.out.println("Ejecutando tarea " + tareaId + " en " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000); // Simula trabajo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown(); // No acepta más tareas, pero termina las actuales
    }
}
```
📌 **Salida esperada (orden puede variar)**
```
Ejecutando tarea 1 en pool-1-thread-1
Ejecutando tarea 2 en pool-1-thread-2
Ejecutando tarea 3 en pool-1-thread-3
Ejecutando tarea 4 en pool-1-thread-1
Ejecutando tarea 5 en pool-1-thread-2
```
✅ **Los hilos se reutilizan en lugar de crear nuevos cada vez.**

---

# **2️⃣ Fork/Join Framework (Procesamiento Paralelo)**
📌 **¿Qué es?**  
Es un **framework de paralelismo** en Java que permite dividir una gran tarea en subtareas más pequeñas (Divide & Conquer).

📌 **Usamos `RecursiveTask<T>` para retornar un valor.**

---

## **🔹 Ejemplo 2: Calcular la suma de un array en paralelo**
```java
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class SumaParalela extends RecursiveTask<Integer> {
    private static final int UMBRAL = 3; // Si el array es pequeño, calcular directamente
    private int[] array;
    private int inicio, fin;

    public SumaParalela(int[] array, int inicio, int fin) {
        this.array = array;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    protected Integer compute() {
        if ((fin - inicio) <= UMBRAL) {
            int suma = 0;
            for (int i = inicio; i < fin; i++) {
                suma += array[i];
            }
            return suma;
        } else {
            int medio = (inicio + fin) / 2;
            SumaParalela tarea1 = new SumaParalela(array, inicio, medio);
            SumaParalela tarea2 = new SumaParalela(array, medio, fin);

            tarea1.fork(); // Ejecutar en paralelo
            int resultado2 = tarea2.compute(); // Ejecutar en este hilo
            int resultado1 = tarea1.join(); // Esperar resultado de la otra tarea

            return resultado1 + resultado2;
        }
    }
}

public class ForkJoinEjemplo {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        SumaParalela tarea = new SumaParalela(numeros, 0, numeros.length);
        int resultado = pool.invoke(tarea);

        System.out.println("Suma total: " + resultado);
    }
}
```
📌 **Salida esperada:**
```
Suma total: 45
```
✅ **Divide el trabajo en partes más pequeñas y ejecuta en paralelo.**

---

# **3️⃣ CompletableFuture (Programación Asíncrona Moderna)**
📌 **¿Qué es?**  
Nos permite ejecutar **tareas asíncronas** y encadenarlas sin bloquear el hilo principal.

📌 **Ejemplo: Descargar datos de una API simulada en paralelo**
```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureEjemplo {
    public static void main(String[] args) {
        CompletableFuture<Void> tarea1 = CompletableFuture.runAsync(() -> {
            System.out.println("Descargando datos en " + Thread.currentThread().getName());
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("Datos descargados!");
        });

        tarea1.join(); // Esperar que termine
    }
}
```
📌 **Salida esperada:**
```
Descargando datos en ForkJoinPool.commonPool-worker-1
Datos descargados!
```
✅ **Tareas ejecutadas en segundo plano sin bloquear el hilo principal.**

---

# **4️⃣ Ejercicio Práctico: Simulación de Descarga de Archivos Concurrente**
📌 **Queremos:**  
1️⃣ Descargar **5 archivos en paralelo** usando `CompletableFuture`.  
2️⃣ Mostrar **el tiempo total de descarga**.

---
<details>
    <summary>Solución</summary>

## **🔹 Código del ejercicio resuelto**
```java
import java.util.concurrent.CompletableFuture;
import java.util.List;

public class DescargaArchivos {
    public static void descargarArchivo(String nombre, int tiempo) {
        System.out.println("Iniciando descarga: " + nombre);
        try {
            Thread.sleep(tiempo * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Descarga completada: " + nombre);
    }

    public static void main(String[] args) {
        long inicio = System.currentTimeMillis();

        List<CompletableFuture<Void>> descargas = List.of(
            CompletableFuture.runAsync(() -> descargarArchivo("Archivo1", 2)),
            CompletableFuture.runAsync(() -> descargarArchivo("Archivo2", 3)),
            CompletableFuture.runAsync(() -> descargarArchivo("Archivo3", 1)),
            CompletableFuture.runAsync(() -> descargarArchivo("Archivo4", 4)),
            CompletableFuture.runAsync(() -> descargarArchivo("Archivo5", 2))
        );

        CompletableFuture.allOf(descargas.toArray(new CompletableFuture[0])).join(); // Esperar todas

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total: " + (fin - inicio) / 1000.0 + "s");
    }
}
```
📌 **Salida esperada (orden puede variar):**
```
Iniciando descarga: Archivo1
Iniciando descarga: Archivo2
Iniciando descarga: Archivo3
Iniciando descarga: Archivo4
Iniciando descarga: Archivo5
Descarga completada: Archivo3
Descarga completada: Archivo1
Descarga completada: Archivo5
Descarga completada: Archivo2
Descarga completada: Archivo4
Tiempo total: 4.0s
```
✅ **Descargas concurrentes sin bloquear el flujo principal.**

---

</details>