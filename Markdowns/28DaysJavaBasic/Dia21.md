# **📌 Día 21: Introducción a Multithreading en Java** 🚀

📌 **Objetivo del día:**  
✅ Aprender cómo **crear y manejar hilos en Java** usando `Thread` y `Runnable`.  
✅ Comprender cómo ejecutar **tareas en paralelo**.  
✅ Implementar un **contador en paralelo usando múltiples hilos**.

---

# **1️⃣ ¿Qué es Multithreading en Java?**

📌 **Multithreading** permite ejecutar múltiples tareas **al mismo tiempo** dentro de una aplicación.  
✔ Un **hilo (Thread)** es una unidad de ejecución dentro de un proceso.  
✔ Java permite manejar **múltiples hilos en paralelo** para mejorar el rendimiento.  
✔ Se usa para tareas como **descarga de archivos, procesamiento en segundo plano y servidores web**.

📌 **Ejemplo sin hilos (ejecución secuencial):**
```java
public class SinHilos {
    public static void main(String[] args) {
        contar("A");
        contar("B");
    }

    public static void contar(String nombre) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(nombre + " contando: " + i);
            try {
                Thread.sleep(1000); // Simula una tarea lenta
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```
✅ **Salida (Ejecución secuencial, primero A y luego B):**
```
A contando: 1
A contando: 2
A contando: 3
A contando: 4
A contando: 5
B contando: 1
B contando: 2
B contando: 3
B contando: 4
B contando: 5
```
✔ **El código ejecuta `A` y luego `B`, pero no en paralelo.**

---

# **2️⃣ Creación de Hilos en Java**

📌 **Java ofrece dos formas de crear hilos:**  
✔ **1️⃣ Extender `Thread`**  
✔ **2️⃣ Implementar `Runnable` (Recomendado)**

📌 **Ejemplo 1: Crear un hilo extendiendo `Thread`**
```java
class HiloEjemplo extends Thread {
    private String nombre;

    public HiloEjemplo(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(nombre + " contando: " + i);
            try {
                Thread.sleep(1000); // Simula una tarea
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class CrearHiloThread {
    public static void main(String[] args) {
        HiloEjemplo hilo1 = new HiloEjemplo("A");
        HiloEjemplo hilo2 = new HiloEjemplo("B");

        hilo1.start(); // Inicia el hilo A
        hilo2.start(); // Inicia el hilo B
    }
}
```
✅ **Salida esperada (Ejecutando en paralelo):**
```
A contando: 1      B contando: 1
A contando: 2      B contando: 2
A contando: 3      B contando: 3
A contando: 4      B contando: 4
A contando: 5      B contando: 5
```
✔ **`start()` inicia los hilos y se ejecutan simultáneamente.**

📌 **Ejemplo 2: Crear un hilo implementando `Runnable` (Mejor práctica)**
```java
class HiloRunnable implements Runnable {
    private String nombre;

    public HiloRunnable(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(nombre + " contando: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class CrearHiloRunnable {
    public static void main(String[] args) {
        Thread hilo1 = new Thread(new HiloRunnable("A"));
        Thread hilo2 = new Thread(new HiloRunnable("B"));

        hilo1.start();
        hilo2.start();
    }
}
```
✅ **Salida esperada:** Igual que el anterior.  
✔ **Ventajas de `Runnable`:** Permite heredar de otras clases, es más flexible.

---

# **3️⃣ Estado de un Hilo en Java**

📌 **Ciclo de vida de un hilo:**  
1️⃣ **`NEW`** → Hilo creado pero no iniciado (`new Thread()`).  
2️⃣ **`RUNNABLE`** → Se ejecuta cuando se llama a `start()`.  
3️⃣ **`BLOCKED`** → Espera a que un recurso esté disponible.  
4️⃣ **`WAITING/TIMED_WAITING`** → Espera otro hilo o un tiempo (`sleep()`).  
5️⃣ **`TERMINATED`** → Finaliza cuando `run()` termina.

📌 **Ejemplo: Ver estado de un hilo:**
```java
public class EstadoHilo {
    public static void main(String[] args) {
        Thread hilo = new Thread(() -> System.out.println("Ejecutando hilo..."));
        System.out.println("Estado: " + hilo.getState()); // NEW

        hilo.start();
        System.out.println("Estado: " + hilo.getState()); // RUNNABLE
    }
}
```
✔ **El estado cambia según su ejecución.**

---

# **📌 Ejercicio del Día 21: Contador en Paralelo** 🎯

📌 **Objetivo:**  
✔ Crear una **clase `Contador`** que cuente de 1 a 10 en paralelo.  
✔ Ejecutar **tres hilos simultáneamente**.  
✔ Simular un **retraso (`sleep(500ms)`)** para cada número contado.

📌 **Ejemplo de salida esperada:**
```
Hilo A: 1      Hilo B: 1      Hilo C: 1
Hilo A: 2      Hilo B: 2      Hilo C: 2
Hilo A: 3      Hilo B: 3      Hilo C: 3
...
Hilo A: 10     Hilo B: 10     Hilo C: 10
```

---

<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
📌 **Clase `Contador` implementando `Runnable`:**
```java
class Contador implements Runnable {
    private String nombre;

    public Contador(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Hilo " + nombre + ": " + i);
            try {
                Thread.sleep(500); // Simula una pausa de 500ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

📌 **Clase `Main` para iniciar los hilos:**
```java
public class Main {
    public static void main(String[] args) {
        Thread hilo1 = new Thread(new Contador("A"));
        Thread hilo2 = new Thread(new Contador("B"));
        Thread hilo3 = new Thread(new Contador("C"));

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
```

✅ **Salida esperada:**
```
Hilo A: 1      Hilo B: 1      Hilo C: 1
Hilo A: 2      Hilo B: 2      Hilo C: 2
Hilo A: 3      Hilo B: 3      Hilo C: 3
...
Hilo A: 10     Hilo B: 10     Hilo C: 10
```

📌 **Explicación:**  
✔ **Cada hilo cuenta de 1 a 10 simultáneamente.**  
✔ **Cada número tiene un retraso de 500ms (`Thread.sleep(500)`).**

---

</details>
