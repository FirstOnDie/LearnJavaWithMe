# **📌 Día 26: Introducción a Diseño de Software (SOLID, MVC)** 🏗️🎯

📌 **Objetivo del día:**  
✅ Aprender los **principios SOLID** para escribir código limpio y mantenible.  
✅ Comprender el **patrón MVC (Modelo-Vista-Controlador)** en el diseño de software.  
✅ **Ejemplo práctico:** Implementar **MVC y SOLID en un sistema de pedidos**.

---

## **1️⃣ ¿Qué es SOLID?** 🔍

📌 **SOLID** es un conjunto de **cinco principios de diseño** que ayudan a escribir código más **mantenible, escalable y reutilizable**.

| Principio | Significado | Explicación |
|-----------|------------|-------------|
| **S** | **Single Responsibility Principle (SRP)** | Una clase debe tener **una única razón para cambiar**. |
| **O** | **Open/Closed Principle (OCP)** | El código debe ser **abierto para extensión**, pero **cerrado para modificación**. |
| **L** | **Liskov Substitution Principle (LSP)** | Un objeto derivado **puede reemplazar** a su clase base sin problemas. |
| **I** | **Interface Segregation Principle (ISP)** | No forzar a una clase a **implementar métodos innecesarios**. |
| **D** | **Dependency Inversion Principle (DIP)** | **Depender de abstracciones, no de implementaciones concretas**. |

✅ **Ventajas de SOLID:**  
✔ **Código más modular y fácil de mantener**.  
✔ **Menos bugs al modificar clases**.  
✔ **Facilita las pruebas unitarias y el escalado del sistema**.

---

## **2️⃣ Explicación con Ejemplo de cada Principio** 📝

### **📌 1. Single Responsibility Principle (SRP)**
✔ **Incorrecto:** Una clase tiene demasiadas responsabilidades.
```java
class Pedido {
    public void calcularTotal() { /* Lógica de cálculo */ }
    public void guardarEnBD() { /* Lógica de persistencia */ }
    public void enviarCorreo() { /* Lógica de notificación */ }
}
```
❌ **Problema:** Si cambia la forma de persistir datos o enviar correos, esta clase se debe modificar.

✔ **Correcto:** Separar responsabilidades.
```java
class Pedido {
    public void calcularTotal() { /* Lógica de cálculo */ }
}

class PedidoRepository {
    public void guardarEnBD(Pedido pedido) { /* Lógica de persistencia */ }
}

class NotificadorPedido {
    public void enviarCorreo(Pedido pedido) { /* Lógica de notificación */ }
}
```
✅ **Cada clase tiene una sola responsabilidad.**

---

### **📌 2. Open/Closed Principle (OCP)**
✔ **Incorrecto:** Modificar la clase base cada vez que agregamos un nuevo método de pago.
```java
class Pago {
    public void procesarPago(String metodo) {
        if (metodo.equals("Tarjeta")) { /* Lógica de tarjeta */ }
        else if (metodo.equals("PayPal")) { /* Lógica de PayPal */ }
    }
}
```
❌ **Problema:** Si agregamos otro método, **debemos modificar la clase**.

✔ **Correcto:** Usar **herencia e interfaces** para extender sin modificar.
```java
interface MetodoPago {
    void procesar();
}

class Tarjeta implements MetodoPago {
    public void procesar() { /* Lógica de pago con tarjeta */ }
}

class PayPal implements MetodoPago {
    public void procesar() { /* Lógica de pago con PayPal */ }
}

class Pago {
    public void procesarPago(MetodoPago metodo) {
        metodo.procesar();
    }
}
```
✅ **El código ahora es extensible sin tocar `Pago`.**

---

### **📌 3. Liskov Substitution Principle (LSP)**
✔ **Incorrecto:** Una subclase **rompe el comportamiento esperado** de la clase base.
```java
class Vehiculo {
    void acelerar() { System.out.println("Vehículo en movimiento"); }
}

class Bicicleta extends Vehiculo {
    @Override
    void acelerar() { throw new UnsupportedOperationException("Las bicicletas no aceleran"); }
}
```
❌ **Problema:** `Bicicleta` **no respeta** el comportamiento de `Vehiculo`.

✔ **Correcto:** Redefinir correctamente la jerarquía.
```java
abstract class Vehiculo {
    abstract void mover();
}

class Auto extends Vehiculo {
    public void mover() { System.out.println("El auto acelera"); }
}

class Bicicleta extends Vehiculo {
    public void mover() { System.out.println("La bicicleta pedalea"); }
}
```
✅ **Las clases pueden sustituirse sin romper el código.**

---

### **📌 4. Interface Segregation Principle (ISP)**
✔ **Incorrecto:** Una interfaz obliga a clases a implementar métodos que no usan.
```java
interface Trabajador {
    void trabajar();
    void cocinar();
}

class Ingeniero implements Trabajador {
    public void trabajar() { /* Trabaja en código */ }
    public void cocinar() { throw new UnsupportedOperationException(); } // ❌ No cocina
}
```
❌ **Problema:** `Ingeniero` **tiene un método que no usa**.

✔ **Correcto:** Separar interfaces.
```java
interface Trabajador {
    void trabajar();
}

interface Cocinero {
    void cocinar();
}

class Ingeniero implements Trabajador {
    public void trabajar() { /* Trabaja en código */ }
}
```
✅ **Cada clase solo implementa lo que necesita.**

---

### **📌 5. Dependency Inversion Principle (DIP)**
✔ **Incorrecto:** Una clase depende de otra clase concreta.
```java
class Notificador {
    private Email email = new Email(); // ❌ Acoplamiento fuerte
    public void enviar() { email.enviar(); }
}
```
❌ **Problema:** Si queremos usar SMS en vez de Email, **debemos modificar la clase**.

✔ **Correcto:** Depender de una **interfaz**.
```java
interface Mensajeria {
    void enviar();
}

class Email implements Mensajeria {
    public void enviar() { /* Lógica de email */ }
}

class Notificador {
    private Mensajeria servicio;

    public Notificador(Mensajeria servicio) {
        this.servicio = servicio;
    }

    public void enviar() {
        servicio.enviar();
    }
}
```
✅ **Ahora podemos cambiar `Email` por `SMS` sin modificar `Notificador`.**

---

## **3️⃣ Ejemplo Práctico: Aplicación de Pedidos con SOLID y MVC** 📦

📌 **Carpeta del Proyecto:**
```
PedidosApp/
 ├── src/
 │   ├── modelo/ (Model)
 │   │   ├── Pedido.java
 │   │   ├── Pago.java
 │   ├── vista/ (View)
 │   │   ├── pedidos.fxml
 │   ├── controlador/ (Controller)
 │   │   ├── PedidoController.java
 │   ├── Main.java
```

📌 **Ejemplo: Código Resumido Aplicando SOLID**
```java
// Interfaz para método de pago (DIP)
interface MetodoPago {
    void procesar();
}

// Implementación de pago con tarjeta
class Tarjeta implements MetodoPago {
    public void procesar() { System.out.println("Pago con tarjeta procesado."); }
}

// Clase Pedido (SRP y OCP)
class Pedido {
    private MetodoPago metodoPago;

    public Pedido(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void pagar() {
        metodoPago.procesar();
    }
}
```
📌 **Uso en `Main.java`**
```java
public class Main {
    public static void main(String[] args) {
        Pedido pedido = new Pedido(new Tarjeta());
        pedido.pagar();
    }
}
```
✅ **Aplicamos SOLID y MVC para un sistema escalable.**

---
