# **📌 Concurrencia y Multithreading en Java**

## **🤔 ¿Qué es la concurrencia?**
La concurrencia es la capacidad de ejecutar **múltiples tareas al mismo tiempo**. En Java, esto se logra con **hilos (Threads)**, que permiten que diferentes partes del código se ejecuten en paralelo.

## **🚀 ¿Qué es un hilo (Thread)?**
Un **hilo (Thread)** es una unidad de ejecución dentro de un proceso.  
Java nos permite crear y manejar **hilos** de dos formas principales:
1. **Extendiendo `Thread`**
2. **Implementando `Runnable`**

---

# **1️⃣ Creación de Hilos en Java**
### **Opción 1: Extender `Thread`**
Podemos crear un hilo extendiendo la clase `Thread` y sobreescribiendo el método `run()`.
```java
class MiHilo extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Ejecutando hilo: " + i);
            try {
                Thread.sleep(1000); // Simula una pausa de 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class EjemploThread {
    public static void main(String[] args) {
        MiHilo hilo1 = new MiHilo();
        hilo1.start(); // Inicia el hilo
    }
}
```
📌 **Explicación:**  
✔️ `run()` contiene el código que ejecutará el hilo.  
✔️ `start()` inicia el hilo en paralelo al hilo principal.  
✔️ `Thread.sleep(1000)` pausa el hilo 1 segundo entre iteraciones.

### **Salida esperada**
```
Ejecutando hilo: 1
Ejecutando hilo: 2
Ejecutando hilo: 3
Ejecutando hilo: 4
Ejecutando hilo: 5
```

---

### **Opción 2: Implementar `Runnable` (Mejor práctica)**
Extender `Thread` no es flexible, ya que Java no soporta **herencia múltiple**. La mejor opción es implementar `Runnable`.
```java
class MiRunnable implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Hilo ejecutándose: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class EjemploRunnable {
    public static void main(String[] args) {
        Thread hilo = new Thread(new MiRunnable());
        hilo.start();
    }
}
```
📌 **Ventajas de `Runnable` sobre `Thread`**  
✔️ Permite heredar de otras clases.  
✔️ Separa la lógica del hilo de su ejecución.

---

# **2️⃣ Creando varios hilos**
```java
public class MultiHilos {
    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            Thread hilo = new Thread(new MiRunnable(), "Hilo-" + i);
            hilo.start();
        }
    }
}
```
📌 **Salida esperada**
```
Hilo-1 ejecutándose: 1
Hilo-2 ejecutándose: 1
Hilo-3 ejecutándose: 1
...
```

⚠ **Ojo:** La ejecución **no es secuencial** porque los hilos corren en paralelo.

---

# **3️⃣ Sincronización y Problema de Race Condition**
Cuando varios hilos acceden a una misma variable compartida, pueden ocurrir **inconsistencias**.  
📌 **Ejemplo sin sincronización (problema de Race Condition)**
```java
class Contador {
    private int contador = 0;

    public void incrementar() {
        contador++;
    }

    public int getValor() {
        return contador;
    }
}

public class SinSincronizacion {
    public static void main(String[] args) {
        Contador contador = new Contador();

        Runnable tarea = () -> {
            for (int i = 0; i < 1000; i++) {
                contador.incrementar();
            }
        };

        Thread hilo1 = new Thread(tarea);
        Thread hilo2 = new Thread(tarea);

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final del contador: " + contador.getValor());
    }
}
```
📌 **Salida esperada (pero incorrecta)**
```
Valor final del contador: 1897  (Debe ser 2000)
```
Esto ocurre porque los hilos acceden al mismo recurso sin sincronización.

---

# **4️⃣ Solución: Uso de `synchronized`**
Para evitar **Race Condition**, usamos `synchronized` para asegurar que solo un hilo acceda al método a la vez.
```java
class ContadorSeguro {
    private int contador = 0;

    public synchronized void incrementar() {
        contador++;
    }

    public int getValor() {
        return contador;
    }
}

public class ConSincronizacion {
    public static void main(String[] args) {
        ContadorSeguro contador = new ContadorSeguro();

        Runnable tarea = () -> {
            for (int i = 0; i < 1000; i++) {
                contador.incrementar();
            }
        };

        Thread hilo1 = new Thread(tarea);
        Thread hilo2 = new Thread(tarea);

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final del contador: " + contador.getValor());
    }
}
```
📌 **Salida esperada (correcta)**
```
Valor final del contador: 2000
```
✔️ `synchronized` evita que dos hilos ejecuten `incrementar()` al mismo tiempo.

---

# **5️⃣ Uso de `ExecutorService` (Gestión eficiente de hilos)**
En lugar de manejar hilos manualmente, **`ExecutorService`** nos ayuda a crear y gestionar pools de hilos.
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EjemploExecutor {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " está ejecutando una tarea.");
            });
        }

        executor.shutdown();
    }
}
```
📌 **Ventajas de `ExecutorService`**  
✔️ Maneja múltiples hilos de forma eficiente.  
✔️ Evita la sobrecarga de creación/destrucción de hilos.

---

# **📝 Ejercicios**
1. **Crea un programa con 3 hilos**, cada uno debe imprimir su nombre y dormir 2 segundos antes de continuar.
2. **Simula una cuenta bancaria** donde dos hilos intentan depositar y retirar dinero al mismo tiempo. Usa `synchronized` para evitar errores.
3. **Usa `ExecutorService`** para ejecutar 10 tareas en paralelo con solo 3 hilos disponibles.

<details>
    <summary>Solución</summary>

# **✅ Ejercicio 1: Crear 3 hilos que impriman su nombre y duerman 2 segundos**
📌 **Objetivo:**
- Crear 3 hilos.
- Cada hilo imprime su nombre.
- Cada hilo duerme **2 segundos** antes de continuar.

### **📝 Código:**
```java
class MiHilo implements Runnable {
    private String nombre;

    public MiHilo(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            System.out.println("Iniciando: " + nombre);
            Thread.sleep(2000); // Duerme 2 segundos
            System.out.println("Finalizando: " + nombre);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class HilosSimples {
    public static void main(String[] args) {
        Thread hilo1 = new Thread(new MiHilo("Hilo-1"));
        Thread hilo2 = new Thread(new MiHilo("Hilo-2"));
        Thread hilo3 = new Thread(new MiHilo("Hilo-3"));

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
```

### **📌 Explicación:**
1. **Creamos la clase `MiHilo`**, que implementa `Runnable` y tiene un `nombre`.
2. **Cada hilo imprime su nombre y duerme 2 segundos** con `Thread.sleep(2000)`.
3. **En `main()`, creamos e iniciamos 3 hilos** con `start()`.

### **Salida esperada (en orden aleatorio, ya que los hilos son concurrentes):**
```
Iniciando: Hilo-1
Iniciando: Hilo-2
Iniciando: Hilo-3
Finalizando: Hilo-2
Finalizando: Hilo-1
Finalizando: Hilo-3
```

---

# **✅ Ejercicio 2: Simular una cuenta bancaria con sincronización**
📌 **Objetivo:**
- Crear una cuenta bancaria con saldo inicial.
- Un hilo deposita dinero y otro retira dinero.
- **Sincronizar el acceso** para evitar inconsistencias.

### **📝 Código:**
```java
class CuentaBancaria {
    private double saldo;

    public CuentaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void depositar(double cantidad) {
        saldo += cantidad;
        System.out.println(Thread.currentThread().getName() + " depositó: " + cantidad);
        System.out.println("Saldo actual: " + saldo);
    }

    public synchronized void retirar(double cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            System.out.println(Thread.currentThread().getName() + " retiró: " + cantidad);
        } else {
            System.out.println(Thread.currentThread().getName() + " intentó retirar " + cantidad + " pero saldo insuficiente.");
        }
        System.out.println("Saldo actual: " + saldo);
    }
}

public class BancoSeguro {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(1000);

        Runnable tareaDeposito = () -> {
            for (int i = 0; i < 3; i++) {
                cuenta.depositar(500);
                try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        };

        Runnable tareaRetiro = () -> {
            for (int i = 0; i < 3; i++) {
                cuenta.retirar(700);
                try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        };

        Thread hilo1 = new Thread(tareaDeposito, "Cliente-1");
        Thread hilo2 = new Thread(tareaRetiro, "Cliente-2");

        hilo1.start();
        hilo2.start();
    }
}
```

### **📌 Explicación:**
1. **Clase `CuentaBancaria` con `saldo` y métodos `depositar()` y `retirar()`.**
2. **Ambos métodos son `synchronized` para evitar Race Condition.**
3. **Dos hilos ejecutan depósitos y retiros en paralelo.**

### **Salida esperada (puede variar por concurrencia)**
```
Cliente-1 depositó: 500
Saldo actual: 1500
Cliente-2 retiró: 700
Saldo actual: 800
Cliente-1 depositó: 500
Saldo actual: 1300
Cliente-2 retiró: 700
Saldo actual: 600
Cliente-1 depositó: 500
Saldo actual: 1100
Cliente-2 intentó retirar 700 pero saldo insuficiente.
Saldo actual: 1100
```

---

# **✅ Ejercicio 3: Usar `ExecutorService` para manejar 10 tareas con 3 hilos**
📌 **Objetivo:**
- Crear un **pool de 3 hilos** para ejecutar **10 tareas en paralelo**.
- Usar **`ExecutorService`** en lugar de `Thread`.

### **📝 Código:**
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Tarea implements Runnable {
    private int id;

    public Tarea(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " ejecutando tarea " + id);
        try {
            Thread.sleep(2000); // Simula trabajo pesado
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ExecutorServiceEjemplo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3); // Pool de 3 hilos

        for (int i = 1; i <= 10; i++) {
            executor.execute(new Tarea(i));
        }

        executor.shutdown(); // Finalizar tareas después de completar las actuales
    }
}
```

### **📌 Explicación:**
1. **`Executors.newFixedThreadPool(3)`** crea un pool de **3 hilos**.
2. **Ejecutamos 10 tareas en paralelo**, pero **solo 3 se ejecutan a la vez**.
3. **Cada tarea imprime su ID y duerme 2 segundos** para simular trabajo real.
4. **`executor.shutdown()`** detiene la ejecución cuando todas las tareas terminan.

### **Salida esperada (puede variar):**
```
pool-1-thread-1 ejecutando tarea 1
pool-1-thread-2 ejecutando tarea 2
pool-1-thread-3 ejecutando tarea 3
(pool-1-thread-1, 2 y 3 esperan 2 segundos...)
pool-1-thread-1 ejecutando tarea 4
pool-1-thread-2 ejecutando tarea 5
pool-1-thread-3 ejecutando tarea 6
...
```
📌 **Los hilos se reutilizan** en lugar de crear nuevos cada vez.

</details>
