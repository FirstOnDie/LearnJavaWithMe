# **📌 Día 22: Sincronización de Hilos y `ExecutorService` en Java** 🔄⚡

📌 **Objetivo del día:**  
✅ Aprender sobre **problemas de concurrencia** en aplicaciones multithreading.  
✅ Comprender el uso de **`synchronized` y `ReentrantLock`** para sincronización.  
✅ Utilizar **`ExecutorService`** para una mejor gestión de hilos.  
✅ **Ejercicio:** Implementar un **sistema bancario con concurrencia segura**.

---

## **1️⃣ Problemas de Concurrencia en Java** 🛑

📌 **¿Qué ocurre cuando múltiples hilos acceden a un mismo recurso sin control?**  
✔ **Condición de carrera** → Dos hilos intentan modificar una misma variable al mismo tiempo.  
✔ **Resultados inesperados** → Datos corruptos o inconsistentes.  
✔ **Solución** → Sincronización con **`synchronized`, `ReentrantLock` y `Atomic Variables`**.

📌 **Ejemplo de Problema (Sin Sincronización):**
```java
class Contador {
    private int valor = 0;

    public void incrementar() {
        valor++; // 🚨 Problema: Puede ser modificado por varios hilos a la vez.
    }

    public int getValor() {
        return valor;
    }
}

public class ProblemaConcurrencia {
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

        System.out.println("Valor esperado: 2000");
        System.out.println("Valor real: " + contador.getValor()); // ❌ Resultado incorrecto.
    }
}
```
✅ **Salida esperada:**
```
Valor esperado: 2000  
Valor real: 1789 (o cualquier otro número incorrecto)
```
✔ **Explicación:** El valor no es 2000 porque ambos hilos modifican `valor` al mismo tiempo.

---

## **2️⃣ Solución: `synchronized` en Métodos**

📌 **Usar `synchronized` para evitar acceso simultáneo al recurso:**
```java
class ContadorSeguro {
    private int valor = 0;

    public synchronized void incrementar() { // 🔒 Bloqueo de un hilo a la vez.
        valor++;
    }

    public int getValor() {
        return valor;
    }
}

public class Sincronizacion {
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

        System.out.println("Valor esperado: 2000");
        System.out.println("Valor real: " + contador.getValor()); // ✅ Correcto
    }
}
```
✅ **Salida esperada:**
```
Valor esperado: 2000  
Valor real: 2000  
```
✔ **El uso de `synchronized` evita problemas de concurrencia.**

---

## **3️⃣ Sincronización con `ReentrantLock`** 🔐

📌 **`ReentrantLock` es una alternativa a `synchronized`, ofreciendo más control:**
```java
import java.util.concurrent.locks.ReentrantLock;

class ContadorConLock {
    private int valor = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void incrementar() {
        lock.lock(); // 🔒 Bloquear el recurso
        try {
            valor++;
        } finally {
            lock.unlock(); // 🔓 Liberar el recurso
        }
    }

    public int getValor() {
        return valor;
    }
}

public class UsoReentrantLock {
    public static void main(String[] args) {
        ContadorConLock contador = new ContadorConLock();

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

        System.out.println("Valor esperado: 2000");
        System.out.println("Valor real: " + contador.getValor()); // ✅ Correcto
    }
}
```
✅ **Ventajas de `ReentrantLock`:**  
✔ Más control sobre el bloqueo.  
✔ Permite **bloqueos más complejos** como intentos de bloqueo con timeout.

---

## **4️⃣ Uso de `ExecutorService` para Mejor Gestión de Hilos**

📌 **En lugar de crear manualmente hilos, usamos `ExecutorService` para gestionarlos:**
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsoExecutorService {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable tarea = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " - Contando: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        executor.submit(tarea);
        executor.submit(tarea);
        executor.submit(tarea);

        executor.shutdown(); // Cierra el ExecutorService cuando termine
    }
}
```
✅ **Ventajas de `ExecutorService`:**  
✔ **Manejo automático de hilos.**  
✔ **Evita la sobrecarga de crear hilos manualmente.**  
✔ **Útil para tareas concurrentes de gran escala.**

---

# **📌 Ejercicio del Día 22: Sistema Bancario con Concurrencia Segura** 🏦

📌 **Objetivo:**  
✔ Implementar una **cuenta bancaria con hilos concurrentes**.  
✔ Dos hilos intentarán **retirar dinero al mismo tiempo**.  
✔ Usar **`synchronized` o `ReentrantLock`** para evitar problemas.

📌 **Ejemplo de salida esperada:**
```
Cliente 1 intenta retirar 500€
Cliente 1: Retiro exitoso. Saldo restante: 500€
Cliente 2 intenta retirar 800€
Cliente 2: ❌ Saldo insuficiente. Saldo actual: 500€
```

---
<details>
    <summary>Solución</summary>

### **✅ Solución en Java**
📌 **Clase `CuentaBancaria` con `synchronized`:**
```java
class CuentaBancaria {
    private double saldo;

    public CuentaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void retirar(String cliente, double cantidad) {
        System.out.println(cliente + " intenta retirar " + cantidad + "€");

        if (cantidad <= saldo) {
            saldo -= cantidad;
            System.out.println(cliente + ": Retiro exitoso. Saldo restante: " + saldo + "€");
        } else {
            System.out.println(cliente + ": ❌ Saldo insuficiente. Saldo actual: " + saldo + "€");
        }
    }
}
```

📌 **Clase `Main` con Hilos Concurrentes:**
```java
public class Main {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(1000.0);

        Runnable cliente1 = () -> cuenta.retirar("Cliente 1", 500);
        Runnable cliente2 = () -> cuenta.retirar("Cliente 2", 800);

        Thread hilo1 = new Thread(cliente1);
        Thread hilo2 = new Thread(cliente2);

        hilo1.start();
        hilo2.start();
    }
}
```

✅ **Salida esperada:**
```
Cliente 1 intenta retirar 500€
Cliente 1: Retiro exitoso. Saldo restante: 500€
Cliente 2 intenta retirar 800€
Cliente 2: ❌ Saldo insuficiente. Saldo actual: 500€
```

---

</details>