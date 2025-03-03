## **🔴 Problemas de Concurrencia Comunes en Java**
Cuando varios hilos trabajan en paralelo, pueden ocurrir problemas como:

### **1️⃣ Race Condition (Condición de Carrera)**
Cuando **dos o más hilos intentan modificar un mismo recurso al mismo tiempo** y el resultado depende del orden de ejecución.

🔹 **Ejemplo:**  
Un cajero automático 👨‍💻💰 permite a dos personas retirar dinero al mismo tiempo, pero no hay control sobre quién retira primero.

```java
class CuentaBancaria {
    private int saldo = 100;

    void retirar(int cantidad) {
        if (saldo >= cantidad) {
            System.out.println(Thread.currentThread().getName() + " retiró: " + cantidad);
            saldo -= cantidad;
        } else {
            System.out.println(Thread.currentThread().getName() + " no pudo retirar: Saldo insuficiente");
        }
    }
}
```
🔴 **Problema:** Si dos hilos intentan retirar 80€ al mismo tiempo, ambos pueden ver que hay suficiente saldo (100€), pero después de retirar, el saldo queda en -60€.

✅ **Solución:** Usar **`synchronized`** para que solo un hilo acceda a la cuenta a la vez.

```java
class CuentaBancaria {
    private int saldo = 100;

    synchronized void retirar(int cantidad) {
        if (saldo >= cantidad) {
            System.out.println(Thread.currentThread().getName() + " retiró: " + cantidad);
            saldo -= cantidad;
        } else {
            System.out.println(Thread.currentThread().getName() + " no pudo retirar: Saldo insuficiente");
        }
    }
}
```
💡 **¿Cómo funciona?**  
🔹 `synchronized` bloquea el acceso a otros hilos hasta que el actual termine.

---

### **2️⃣ Data Inconsistency (Datos Inconsistentes)**
Ocurre cuando múltiples hilos modifican datos compartidos sin sincronización, dejando información incorrecta.

🔹 **Ejemplo:**  
Un sistema de reservas 🎟️ permite comprar boletos para un concierto, pero si dos personas compran el último boleto al mismo tiempo, el sistema puede permitir que ambas lo obtengan.

✅ **Solución:** Usar **`AtomicInteger`** o **`ReentrantLock`**.

```java
import java.util.concurrent.atomic.AtomicInteger;

class Reservas {
    private AtomicInteger boletos = new AtomicInteger(1);

    void comprarBoleto() {
        if (boletos.get() > 0) {
            System.out.println(Thread.currentThread().getName() + " compró un boleto.");
            boletos.decrementAndGet();
        } else {
            System.out.println(Thread.currentThread().getName() + " no pudo comprar: Agotado.");
        }
    }
}
```
🔹 **¿Por qué funciona?**  
`AtomicInteger` **garantiza operaciones atómicas**, evitando condiciones de carrera.

---

### **3️⃣ Deadlocks (Bloqueo Mutuo)**
Ocurre cuando **dos hilos esperan indefinidamente a que el otro libere un recurso**.

🔹 **Ejemplo:**  
Dos amigos intentan intercambiar un libro 📚 y una revista 📰, pero cada uno está esperando que el otro entregue primero.

```java
class Amigo {
    synchronized void darLibro(Amigo otro) {
        System.out.println(Thread.currentThread().getName() + " esperando el libro...");
        otro.recibirRevista();
    }

    synchronized void recibirRevista() {
        System.out.println(Thread.currentThread().getName() + " recibió la revista.");
    }
}
```
🔴 **Problema:** Ambos hilos quedan atrapados esperando que el otro libere el recurso.

✅ **Solución:** Usar **`tryLock()`** en lugar de `synchronized`.

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Amigo {
    private final Lock lock = new ReentrantLock();

    void intercambiar(Amigo otro) {
        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName() + " intercambió con " + otro);
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " no pudo intercambiar, evitando deadlock.");
        }
    }
}
```
🔹 **¿Por qué funciona?**  
`tryLock()` evita el bloqueo infinito porque no espera indefinidamente.

---

### **4️⃣ Starvation (Hambre de Hilos)**
Sucede cuando **un hilo nunca obtiene acceso a un recurso** porque otros hilos lo bloquean continuamente.

🔹 **Ejemplo:**  
Si en un restaurante hay una **mesa reservada siempre por clientes VIP**, los clientes normales nunca podrán sentarse.

✅ **Solución:** Usar **hilos de prioridad justa** con `ReentrantLock` y `fair = true`.

```java
Lock lock = new ReentrantLock(true);  // Prioridad justa
```
🔹 **¿Por qué funciona?**  
Con `fair = true`, el sistema da acceso de manera equitativa.

---

## **✅ Buenas Prácticas para Evitar Problemas de Concurrencia**
| 🔴 Problema | ✅ Solución |
|------------|-----------|
| **Race Condition** | `synchronized`, `AtomicInteger` |
| **Datos Inconsistentes** | `synchronized`, `Lock`, `AtomicInteger` |
| **Deadlocks** | `tryLock()`, `Timeouts`, evitar bloqueos en cadena |
| **Starvation** | Usar `ReentrantLock(true)` para prioridad justa |

---

## **📌 Conclusión**
Los problemas de concurrencia pueden causar **errores impredecibles y difíciles de depurar**, pero Java ofrece herramientas como **synchronized, Locks, Atomic Variables y Concurrent Collections** para manejarlos correctamente. 🚀

💡 **Mi consejo:** Si puedes, usa `ExecutorService` en lugar de manejar hilos manualmente, porque simplifica la concurrencia.
