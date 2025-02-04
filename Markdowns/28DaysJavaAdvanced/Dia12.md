
# **📌 Día 12: Patrones Estructurales en Java**
Hoy aprenderás:  
✅ **Adapter** (Permite usar clases incompatibles juntas)  
✅ **Decorator** (Agrega funcionalidades sin modificar código original)  
✅ **Facade** (Simplifica el acceso a sistemas complejos)  
✅ **Ejercicio: Gestión de pagos con diferentes proveedores**

---

📌 **¿Por qué es importante?**  
Los **Patrones Estructurales** ayudan a organizar las relaciones entre clases y objetos, facilitando la reutilización y la mantenibilidad del código.

---

# **1️⃣ Adapter: Convertir Interfaces Incompatibles**
📌 **¿Qué es?**  
El **patrón Adapter** actúa como un **puente entre dos clases con interfaces incompatibles**, permitiendo que trabajen juntas sin modificar su código.

📌 **Casos de uso:**  
✅ **Integración con APIs de terceros**  
✅ **Conectar sistemas heredados con nuevos**

---

## **🔹 Ejemplo: Integración con una API antigua**
📌 **Tenemos una API antigua que usa `ProcesadorAntiguo`, pero queremos usar `ProcesadorNuevo`.**

📌 **Interfaz esperada:**
```java
interface ProcesadorPago {
    void procesarPago(double cantidad);
}
```
📌 **Clase antigua incompatible:**
```java
class ProcesadorAntiguo {
    void ejecutarPago(double monto) {
        System.out.println("Pago procesado: $" + monto);
    }
}
```
📌 **Adaptador para convertir `ProcesadorAntiguo` en `ProcesadorPago`:**
```java
class ProcesadorAdapter implements ProcesadorPago {
    private ProcesadorAntiguo procesadorAntiguo;

    public ProcesadorAdapter(ProcesadorAntiguo procesadorAntiguo) {
        this.procesadorAntiguo = procesadorAntiguo;
    }

    @Override
    public void procesarPago(double cantidad) {
        procesadorAntiguo.ejecutarPago(cantidad);
    }
}
```
📌 **Ejemplo de uso:**
```java
public class Main {
    public static void main(String[] args) {
        ProcesadorPago pago = new ProcesadorAdapter(new ProcesadorAntiguo());
        pago.procesarPago(100.0);
    }
}
```
📌 **Salida esperada:**
```
Pago procesado: $100.0
```
✅ **Ahora `ProcesadorAntiguo` funciona con la nueva interfaz sin modificar su código.**

---

# **2️⃣ Decorator: Agregar Funcionalidad sin Modificar Código Original**
📌 **¿Qué es?**  
El **patrón Decorator** permite **agregar funcionalidades a un objeto en tiempo de ejecución** sin modificar su código original.

📌 **Casos de uso:**  
✅ **Agregar logs, seguridad, cache a servicios**  
✅ **Extender funcionalidades sin modificar clases existentes**

---

## **🔹 Ejemplo: Decorar un servicio de notificaciones**
📌 **Interfaz base:**
```java
interface Notificacion {
    void enviar(String mensaje);
}
```
📌 **Implementación base:**
```java
class NotificacionBasica implements Notificacion {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando: " + mensaje);
    }
}
```
📌 **Decorator para agregar funcionalidad extra:**
```java
class NotificacionConLog implements Notificacion {
    private Notificacion notificacion;

    public NotificacionConLog(Notificacion notificacion) {
        this.notificacion = notificacion;
    }

    @Override
    public void enviar(String mensaje) {
        System.out.println("LOG: Enviando notificación...");
        notificacion.enviar(mensaje);
    }
}
```
📌 **Ejemplo de uso:**
```java
public class Main {
    public static void main(String[] args) {
        Notificacion notificacion = new NotificacionConLog(new NotificacionBasica());
        notificacion.enviar("Hola, Decorator!");
    }
}
```
📌 **Salida esperada:**
```
LOG: Enviando notificación...
Enviando: Hola, Decorator!
```
✅ **Agregamos logs sin modificar `NotificacionBasica`.**

---

# **3️⃣ Facade: Simplificar el Acceso a un Sistema Complejo**
📌 **¿Qué es?**  
El **patrón Facade** proporciona **una interfaz simplificada** para un conjunto de clases complejas.

📌 **Casos de uso:**  
✅ **Reducir la complejidad de subsistemas**  
✅ **Crear una API más fácil de usar**

---

## **🔹 Ejemplo: Un sistema de conversión de archivos**
📌 **Clases complejas individuales:**
```java
class LectorPDF {
    void leerPDF(String archivo) {
        System.out.println("Leyendo PDF: " + archivo);
    }
}

class ConvertidorWord {
    void convertirAPalabra(String archivo) {
        System.out.println("Convirtiendo " + archivo + " a Word...");
    }
}

class ConvertidorTexto {
    void convertirATexto(String archivo) {
        System.out.println("Convirtiendo " + archivo + " a texto...");
    }
}
```
📌 **Fachada para simplificar su uso:**
```java
class ConversorFacade {
    private LectorPDF lector = new LectorPDF();
    private ConvertidorWord convertidorWord = new ConvertidorWord();
    private ConvertidorTexto convertidorTexto = new ConvertidorTexto();

    public void convertirPDF(String archivo) {
        lector.leerPDF(archivo);
        convertidorWord.convertirAPalabra(archivo);
        convertidorTexto.convertirATexto(archivo);
    }
}
```
📌 **Ejemplo de uso:**
```java
public class Main {
    public static void main(String[] args) {
        ConversorFacade conversor = new ConversorFacade();
        conversor.convertirPDF("documento.pdf");
    }
}
```
📌 **Salida esperada:**
```
Leyendo PDF: documento.pdf
Convirtiendo documento.pdf a Word...
Convirtiendo documento.pdf a texto...
```
✅ **Escondemos la complejidad tras una única interfaz fácil de usar.**

---

# **4️⃣ Ejercicio Práctico: Gestión de Pagos con Diferentes Proveedores**
📌 **Queremos:**  
1️⃣ Soportar múltiples **proveedores de pago** (`PayPal`, `Stripe`).  
2️⃣ Usar **Adapter** para unificar sus interfaces.  
3️⃣ Usar **Facade** para simplificar su uso.

---

<details>
    <summary>Solución</summary>

## **🔹 Código del ejercicio resuelto**
📌 **Interfaz común de pagos:**
```java
interface ProveedorPago {
    void procesarPago(double monto);
}
```
📌 **Clases originales (APIs de terceros):**
```java
class PayPal {
    void pagarConPayPal(double cantidad) {
        System.out.println("Pago realizado con PayPal: $" + cantidad);
    }
}

class Stripe {
    void realizarPago(double monto) {
        System.out.println("Pago realizado con Stripe: $" + monto);
    }
}
```
📌 **Adapter para unificar las interfaces:**
```java
class PayPalAdapter implements ProveedorPago {
    private PayPal paypal = new PayPal();

    @Override
    public void procesarPago(double monto) {
        paypal.pagarConPayPal(monto);
    }
}

class StripeAdapter implements ProveedorPago {
    private Stripe stripe = new Stripe();

    @Override
    public void procesarPago(double monto) {
        stripe.realizarPago(monto);
    }
}
```
📌 **Facade para simplificar su uso:**
```java
class SistemaPago {
    public void realizarPago(ProveedorPago proveedor, double monto) {
        proveedor.procesarPago(monto);
    }
}
```
📌 **Ejemplo de uso:**
```java
public class Main {
    public static void main(String[] args) {
        SistemaPago sistema = new SistemaPago();

        ProveedorPago pagoPayPal = new PayPalAdapter();
        ProveedorPago pagoStripe = new StripeAdapter();

        sistema.realizarPago(pagoPayPal, 50);
        sistema.realizarPago(pagoStripe, 75);
    }
}
```
📌 **Salida esperada:**
```
Pago realizado con PayPal: $50.0
Pago realizado con Stripe: $75.0
```
✅ **Podemos agregar nuevos proveedores sin modificar `SistemaPago`.**

---

</details>