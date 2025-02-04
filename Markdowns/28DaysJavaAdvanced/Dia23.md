# **📌 Día 23: Buenas Prácticas y SOLID en Java**
Hoy aprenderás:  
✅ **Principios SOLID para diseño de software limpio**  
✅ **Inyección de Dependencias con Spring**  
✅ **Testing con JUnit y Mockito**  
✅ **Logging con SLF4J y Logback**

---

📌 **¿Por qué es importante?**  
Escribir código limpio y mantenible es clave para proyectos escalables. Hoy aprenderás sobre **principios SOLID, inyección de dependencias, testing y logging** para mejorar la calidad del código.

---



# **1️⃣ Principios SOLID en Java**
📌 **SOLID es un conjunto de principios para escribir código escalable y fácil de mantener.**

| **Principio** | **Descripción** | **Ejemplo** |
|--------------|----------------|-------------|
| **S**ingle Responsibility | Cada clase debe hacer una sola cosa | Una clase `UserService` no debe manejar logs |
| **O**pen/Closed | El código debe poder extenderse sin modificarse | Usar interfaces en vez de cambiar clases |
| **L**iskov Substitution | Las subclases deben ser reemplazables sin romper el código | Una `List` debe poder ser sustituida por `ArrayList` |
| **I**nterface Segregation | Interfaces específicas en vez de una gigante | Separar `IMostrar` y `IImprimir` en vez de `IAcciones` |
| **D**ependency Inversion | Depender de abstracciones y no implementaciones | Usar `Repository` en lugar de `new MySQLRepository()` |

✅ **Vamos a implementar SOLID con ejemplos en código.**

---

## **1️⃣ Single Responsibility (SRP) - Una clase, una responsabilidad**
📌 **❌ Código incorrecto: Un `PedidoService` que maneja pagos y logs**
```java
public class PedidoService {
    public void procesarPedido() { System.out.println("Procesando pedido"); }
    public void registrarPago() { System.out.println("Procesando pago"); }
    public void log(String mensaje) { System.out.println(mensaje); }
}
```
📌 **✅ Código correcto: Separar responsabilidades**
```java
class PedidoService {
    private final LoggerService logger;
    public PedidoService(LoggerService logger) { this.logger = logger; }
    public void procesarPedido() { logger.log("Procesando pedido"); }
}
class LoggerService {
    public void log(String mensaje) { System.out.println(mensaje); }
}
```
✅ **Cada clase se enfoca en una sola responsabilidad.**

---

## **2️⃣ Open/Closed Principle (OCP) - Código abierto a extensión, cerrado a modificación**
📌 **❌ Código incorrecto: Una clase `Descuento` con `if` para cada tipo de descuento**
```java
public class Descuento {
    public double calcular(String tipo, double precio) {
        if (tipo.equals("NAVIDAD")) return precio * 0.9;
        else if (tipo.equals("BLACKFRIDAY")) return precio * 0.8;
        return precio;
    }
}
```
📌 **✅ Código correcto: Extender sin modificar (Polimorfismo)**
```java
interface Descuento { double aplicar(double precio); }
class DescuentoNavidad implements Descuento {
    public double aplicar(double precio) { return precio * 0.9; }
}
class DescuentoBlackFriday implements Descuento {
    public double aplicar(double precio) { return precio * 0.8; }
}
```
✅ **Ahora podemos agregar nuevos descuentos sin modificar `Descuento`.**

---

## **3️⃣ Liskov Substitution (LSP) - Subclases deben ser intercambiables con su padre**
📌 **❌ Código incorrecto: Una subclase `Cuadrado` que rompe `Rectangulo`**
```java
class Rectangulo {
    protected int ancho, alto;
    public void setAncho(int ancho) { this.ancho = ancho; }
    public void setAlto(int alto) { this.alto = alto; }
}
class Cuadrado extends Rectangulo {
    public void setAncho(int ancho) { this.ancho = this.alto = ancho; }
}
```
📌 **✅ Código correcto: No forzar relaciones incorrectas**
```java
interface Figura { int getArea(); }
class Rectangulo implements Figura {
    protected int ancho, alto;
    public Rectangulo(int ancho, int alto) { this.ancho = ancho; this.alto = alto; }
    public int getArea() { return ancho * alto; }
}
class Cuadrado implements Figura {
    private int lado;
    public Cuadrado(int lado) { this.lado = lado; }
    public int getArea() { return lado * lado; }
}
```
✅ **Ahora `Rectangulo` y `Cuadrado` son independientes y cumplen LSP.**

---

## **4️⃣ Interface Segregation (ISP) - Interfaces pequeñas y específicas**
📌 **❌ Código incorrecto: Una interfaz `Ave` con métodos que no aplican a todas**
```java
interface Ave {
    void volar();
    void nadar();
}
class Aguila implements Ave {
    public void volar() { System.out.println("Vuelo alto"); }
    public void nadar() { throw new UnsupportedOperationException(); }
}
```
📌 **✅ Código correcto: Separar interfaces según necesidad**
```java
interface AveVoladora { void volar(); }
interface AveNadadora { void nadar(); }
class Aguila implements AveVoladora {
    public void volar() { System.out.println("Vuelo alto"); }
}
class Pato implements AveVoladora, AveNadadora {
    public void volar() { System.out.println("Vuelo bajo"); }
    public void nadar() { System.out.println("Nado en agua"); }
}
```
✅ **Cada clase implementa solo lo que necesita.**

---

## **5️⃣ Dependency Inversion (DIP) - Depender de abstracciones, no de implementaciones**
📌 **❌ Código incorrecto: Un `PedidoService` que usa `new MySQLRepository()`**
```java
public class PedidoService {
    private final MySQLRepository repo = new MySQLRepository();
    public void procesarPedido() { repo.guardarPedido(); }
}
```
📌 **✅ Código correcto: Inyección de dependencias**
```java
interface PedidoRepository { void guardarPedido(); }
class MySQLRepository implements PedidoRepository {
    public void guardarPedido() { System.out.println("Pedido guardado en MySQL"); }
}
class PedidoService {
    private final PedidoRepository repo;
    public PedidoService(PedidoRepository repo) { this.repo = repo; }
    public void procesarPedido() { repo.guardarPedido(); }
}
```
✅ **Ahora `PedidoService` no depende de MySQL, sino de una abstracción.**

---

# **6️⃣ Testing con JUnit y Mockito**
📌 **Ejemplo de prueba unitaria con JUnit**
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {
    @Test
    void testSuma() {
        Calculadora calc = new Calculadora();
        assertEquals(5, calc.suma(2, 3));
    }
}
```
📌 **Ejemplo de prueba con Mockito**
```java
PedidoRepository repoMock = mock(PedidoRepository.class);
PedidoService service = new PedidoService(repoMock);
service.procesarPedido();
verify(repoMock).guardarPedido();
```
✅ **Usamos mocks para probar sin dependencias reales.**

---

# **7️⃣ Logging con SLF4J y Logback**
📌 **Ejemplo de uso de SLF4J para logs estructurados**
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogEjemplo {
    private static final Logger logger = LoggerFactory.getLogger(LogEjemplo.class);
    public static void main(String[] args) {
        logger.info("Iniciando aplicación...");
        logger.error("Error inesperado");
    }
}
```
✅ **Los logs ayudan a depurar sin imprimir en consola.**
