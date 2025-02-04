
# **📌 Día 9: Sincronización y Paralelismo Avanzado**
Hoy aprenderás:  
✅ **Locks, `ReentrantLock`, `ReadWriteLock`** (para evitar problemas de concurrencia)  
✅ **Atomic Variables y `ThreadLocal`** (para mejorar seguridad y rendimiento)  
✅ **Ejercicio: Sistema bancario concurrente**

---
📌 **¿Por qué es importante?**  
Cuando múltiples hilos acceden a los mismos datos, pueden producirse **problemas de concurrencia**. Hoy aprenderás **cómo evitar inconsistencias y mejorar el rendimiento** en aplicaciones concurrentes.

---

# **1️⃣ Problema de Concurrencia: Condición de Carrera**
📌 **Ejemplo de problema de concurrencia:**  
Cuando varios hilos intentan modificar un mismo recurso **sin sincronización**, pueden aparecer valores incorrectos.

```java
class CuentaBancaria {
    private int saldo = 100;

    public void retirar(int cantidad) {
        if (saldo >= cantidad) {
            System.out.println(Thread.currentThread().getName() + " retirando " + cantidad);
            saldo -= cantidad;
            System.out.println("Saldo restante: " + saldo);
        } else {
            System.out.println(Thread.currentThread().getName() + " no pudo retirar, saldo insuficiente.");
        }
    }
}

public class ProblemaConcurrencia {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria();
        Runnable tarea = () -> cuenta.retirar(50);

        Thread t1 = new Thread(tarea, "Hilo-1");
        Thread t2 = new Thread(tarea, "Hilo-2");
        Thread t3 = new Thread(tarea, "Hilo-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
```
📌 **Salida esperada (puede variar):**
```
Hilo-1 retirando 50
Saldo restante: 50
Hilo-2 retirando 50
Saldo restante: 0
Hilo-3 retirando 50
Hilo-3 no pudo retirar, saldo insuficiente.
```
✅ **A veces funciona, pero si dos hilos leen el saldo al mismo tiempo, puede producirse un error.**  
🚨 **Para evitar esto, usamos `synchronized` o `ReentrantLock`.**

---

# **2️⃣ Solución: Usar `synchronized`**
📌 **`synchronized` bloquea el acceso a un método para que solo un hilo lo ejecute a la vez.**

```java
class CuentaBancaria {
    private int saldo = 100;

    public synchronized void retirar(int cantidad) {
        if (saldo >= cantidad) {
            System.out.println(Thread.currentThread().getName() + " retirando " + cantidad);
            saldo -= cantidad;
            System.out.println("Saldo restante: " + saldo);
        } else {
            System.out.println(Thread.currentThread().getName() + " no pudo retirar, saldo insuficiente.");
        }
    }
}
```
✅ **Ahora solo un hilo puede ejecutar `retirar()`, evitando condiciones de carrera.**

---

# **3️⃣ Usar `ReentrantLock` para más control**
📌 **`ReentrantLock` ofrece más flexibilidad que `synchronized`.**

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CuentaBancaria {
    private int saldo = 100;
    private Lock lock = new ReentrantLock();

    public void retirar(int cantidad) {
        lock.lock();
        try {
            if (saldo >= cantidad) {
                System.out.println(Thread.currentThread().getName() + " retirando " + cantidad);
                saldo -= cantidad;
                System.out.println("Saldo restante: " + saldo);
            } else {
                System.out.println(Thread.currentThread().getName() + " no pudo retirar, saldo insuficiente.");
            }
        } finally {
            lock.unlock();
        }
    }
}
```
📌 **Ventajas de `ReentrantLock`:**  
✅ **Más flexibilidad** que `synchronized`.  
✅ **Permite `tryLock()`** para intentar bloquear sin esperar.

---

# **4️⃣ `ReadWriteLock`: Mejor rendimiento en lectura/escritura**
📌 **`ReadWriteLock` permite múltiples lecturas simultáneas, pero solo una escritura a la vez.**

```java
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Banco {
    private int saldo = 100;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void leerSaldo() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " leyendo saldo: " + saldo);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void modificarSaldo(int cantidad) {
        lock.writeLock().lock();
        try {
            saldo += cantidad;
            System.out.println(Thread.currentThread().getName() + " modificó el saldo. Nuevo saldo: " + saldo);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
```
📌 **Ventajas:**  
✅ **Múltiples hilos pueden leer al mismo tiempo**.  
✅ **Escritura bloquea a todos los demás hilos.**

---

# **5️⃣ Variables Atómicas (`AtomicInteger`)**
📌 **En vez de `synchronized`, podemos usar `AtomicInteger` para evitar condiciones de carrera.**

```java
import java.util.concurrent.atomic.AtomicInteger;

class CuentaAtomic {
    private AtomicInteger saldo = new AtomicInteger(100);

    public void retirar(int cantidad) {
        if (saldo.get() >= cantidad) {
            saldo.addAndGet(-cantidad);
            System.out.println(Thread.currentThread().getName() + " retiró " + cantidad + ". Saldo: " + saldo.get());
        } else {
            System.out.println(Thread.currentThread().getName() + " no pudo retirar, saldo insuficiente.");
        }
    }
}
```
📌 **Ventajas:**  
✅ **Operaciones atómicas sin necesidad de `synchronized`.**  
✅ **Mayor rendimiento en operaciones concurrentes.**

---

# **6️⃣ Ejercicio Práctico: Sistema Bancario Concurrente**
📌 **Queremos:**  
1️⃣ Un sistema bancario donde múltiples clientes realicen retiros concurrentemente.  
2️⃣ Usar `ReentrantLock` para evitar problemas de concurrencia.

---

<details>
    <summary>Solución</summary>

## **🔹 Código del ejercicio resuelto**
```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CuentaBancaria {
    private int saldo;
    private Lock lock = new ReentrantLock();

    public CuentaBancaria(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void retirar(int cantidad, String cliente) {
        lock.lock();
        try {
            if (saldo >= cantidad) {
                System.out.println(cliente + " retirando " + cantidad);
                saldo -= cantidad;
                System.out.println("Saldo restante: " + saldo);
            } else {
                System.out.println(cliente + " no pudo retirar, saldo insuficiente.");
            }
        } finally {
            lock.unlock();
        }
    }
}

public class BancoConcurrente {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(200);

        Runnable tareaCliente = () -> {
            String cliente = Thread.currentThread().getName();
            cuenta.retirar(100, cliente);
        };

        Thread cliente1 = new Thread(tareaCliente, "Cliente-1");
        Thread cliente2 = new Thread(tareaCliente, "Cliente-2");
        Thread cliente3 = new Thread(tareaCliente, "Cliente-3");

        cliente1.start();
        cliente2.start();
        cliente3.start();
    }
}
```
📌 **Salida esperada (puede variar):**
```
Cliente-1 retirando 100
Saldo restante: 100
Cliente-2 retirando 100
Saldo restante: 0
Cliente-3 no pudo retirar, saldo insuficiente.
```
✅ **Múltiples clientes acceden a la cuenta sin errores de concurrencia.**

</details>