# **📌 Principios SOLID** 🚀

Los **principios SOLID** son **cinco reglas** que ayudan a escribir código **más limpio, mantenible y flexible** en programación orientada a objetos (POO).

🔹 **S**ingle Responsibility (Responsabilidad Única)  
🔹 **O**pen/Closed (Abierto/Cerrado)  
🔹 **L**iskov Substitution (Sustitución de Liskov)  
🔹 **I**nterface Segregation (Segregación de Interfaces)  
🔹 **D**ependency Inversion (Inversión de Dependencias)

---

## **1️⃣ Principio de Responsabilidad Única (Single Responsibility - SRP)**
💡 **Cada clase debe tener una única razón para cambiar.**

📌 **Ejemplo Incorrecto (Violando SRP)**  
Aquí, la clase `Usuario` tiene **dos responsabilidades**:
- 📌 **Guardar datos de usuario.**
- 📌 **Enviar emails.** ❌

```java
class Usuario {
    private String nombre;
    
    public Usuario(String nombre) { this.nombre = nombre; }
    
    public void guardarEnBD() { 
        System.out.println("Guardando usuario en la BD...");
    }
    
    public void enviarEmail() { 
        System.out.println("Enviando email de bienvenida...");
    }
}
```

📌 **Ejemplo Correcto (Cumpliendo SRP)**  
Separamos las responsabilidades en **dos clases diferentes**:

```java
class Usuario {
    private String nombre;
    
    public Usuario(String nombre) { this.nombre = nombre; }
}

class UsuarioRepositorio {
    public void guardar(Usuario usuario) {
        System.out.println("Guardando usuario en la BD...");
    }
}

class ServicioEmail {
    public void enviarEmail(Usuario usuario) {
        System.out.println("Enviando email de bienvenida...");
    }
}
```

✅ Ahora, si cambia la forma de enviar emails, solo modificamos `ServicioEmail`, sin tocar `Usuario`.

---

## **2️⃣ Principio de Abierto/Cerrado (Open/Closed - OCP)**
💡 **Las clases deben estar abiertas para extensión, pero cerradas para modificación.**

📌 **Ejemplo Incorrecto (Violando OCP)**  
Aquí, si queremos agregar más descuentos, **tenemos que modificar la clase** ❌.

```java
class CalculadoraDescuentos {
    public double calcularDescuento(String tipoCliente, double precio) {
        if (tipoCliente.equals("VIP")) {
            return precio * 0.2;  // 20% descuento
        } else if (tipoCliente.equals("Normal")) {
            return precio * 0.1;  // 10% descuento
        }
        return 0;
    }
}
```

📌 **Ejemplo Correcto (Cumpliendo OCP)**  
✅ En lugar de modificar la clase, **usamos polimorfismo** para extenderla fácilmente.

```java
interface Descuento {
    double aplicar(double precio);
}

class DescuentoVIP implements Descuento {
    public double aplicar(double precio) { return precio * 0.2; }
}

class DescuentoNormal implements Descuento {
    public double aplicar(double precio) { return precio * 0.1; }
}

class Calculadora {
    public double calcular(Descuento descuento, double precio) {
        return descuento.aplicar(precio);
    }
}
```

✅ Ahora, si queremos agregar **otro tipo de descuento**, simplemente creamos otra clase sin modificar el código existente.

---

## **3️⃣ Principio de Sustitución de Liskov (Liskov Substitution - LSP)**
💡 **Las clases derivadas deben poder reemplazar a sus clases base sin problemas.**

📌 **Ejemplo Incorrecto (Violando LSP)**  
Aquí, `PatoDeGoma` **hereda** de `Pato`, pero **no puede volar** ❌.

```java
class Pato {
    void volar() { System.out.println("El pato está volando!"); }
}

class PatoDeGoma extends Pato {
    @Override
    void volar() { throw new UnsupportedOperationException("No puedo volar!"); } // ❌ Rompe Liskov
}
```

📌 **Ejemplo Correcto (Cumpliendo LSP)**  
✅ Creamos una **interfaz Volador** y la implementamos **solo en los patos que vuelan**.

```java
interface Volador {
    void volar();
}

class Pato implements Volador {
    public void volar() { System.out.println("El pato está volando!"); }
}

class PatoDeGoma {
    public void hacerSonido() { System.out.println("Squeak!"); } // ✅ No intenta volar
}
```

✅ Ahora `PatoDeGoma` **ya no hereda de `Pato`** y el código es más lógico.

---

## **4️⃣ Principio de Segregación de Interfaces (Interface Segregation - ISP)**
💡 **Las interfaces deben ser específicas y no obligar a las clases a implementar métodos que no necesitan.**

📌 **Ejemplo Incorrecto (Violando ISP)**  
Aquí, la interfaz `Trabajador` **obliga a todos los empleados a comer**, pero los robots no comen ❌.

```java
interface Trabajador {
    void trabajar();
    void comer();
}

class Empleado implements Trabajador {
    public void trabajar() { System.out.println("Trabajando..."); }
    public void comer() { System.out.println("Hora de comer!"); }
}

class Robot implements Trabajador {
    public void trabajar() { System.out.println("Robot trabajando..."); }
    public void comer() { throw new UnsupportedOperationException("Los robots no comen!"); } // ❌
}
```

📌 **Ejemplo Correcto (Cumpliendo ISP)**  
✅ Separamos en **dos interfaces**:

```java
interface Trabajador {
    void trabajar();
}

interface Comedor {
    void comer();
}

class Empleado implements Trabajador, Comedor {
    public void trabajar() { System.out.println("Trabajando..."); }
    public void comer() { System.out.println("Hora de comer!"); }
}

class Robot implements Trabajador {
    public void trabajar() { System.out.println("Robot trabajando..."); } // ✅ No necesita "comer"
}
```

✅ Ahora **cada clase implementa solo lo que necesita**.

---

## **5️⃣ Principio de Inversión de Dependencias (Dependency Inversion - DIP)**
💡 **Los módulos de alto nivel no deben depender de módulos de bajo nivel, sino de abstracciones.**

📌 **Ejemplo Incorrecto (Violando DIP)**  
Aquí, `Tienda` depende directamente de `PagoPaypal`, lo que **hace difícil cambiar el método de pago** ❌.

```java
class PagoPaypal {
    void pagar(double monto) { System.out.println("Pagando $" + monto + " con PayPal."); }
}

class Tienda {
    private PagoPaypal pago = new PagoPaypal();
    void procesarCompra(double monto) { pago.pagar(monto); }
}
```

📌 **Ejemplo Correcto (Cumpliendo DIP)**  
✅ Usamos una **interfaz `Pago`** para que `Tienda` no dependa de un método de pago específico.

```java
interface Pago {
    void pagar(double monto);
}

class PagoPaypal implements Pago {
    public void pagar(double monto) { System.out.println("Pagando $" + monto + " con PayPal."); }
}

class PagoTarjeta implements Pago {
    public void pagar(double monto) { System.out.println("Pagando $" + monto + " con Tarjeta."); }
}

class Tienda {
    private Pago metodoPago;

    public Tienda(Pago metodoPago) { this.metodoPago = metodoPago; }

    void procesarCompra(double monto) { metodoPago.pagar(monto); }
}
```

✅ Ahora `Tienda` puede usar cualquier método de pago sin cambiar su código.

---