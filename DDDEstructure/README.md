# **📌 Domain-Driven Design (DDD)** 🚀

📌 **¿Por qué es importante?**  
**DDD (Domain-Driven Design)** es una estrategia de diseño de software que permite construir **sistemas escalables, flexibles y alineados con el negocio**. Con DDD, el **código refleja las reglas y procesos del negocio**, facilitando la evolución y el mantenimiento.

---

# **1️⃣ ¿Qué es Domain-Driven Design (DDD)?**
📌 **DDD es un enfoque de diseño que:**  
✔ **Organiza el código en torno al dominio del negocio.**  
✔ **Reduce la complejidad separando capas y responsabilidades.**  
✔ **Facilita la comunicación entre desarrolladores y expertos del negocio.**

📌 **Ejemplo: Gestión de Pedidos**  
Imagina que estás construyendo un sistema de e-commerce. Con DDD, en lugar de pensar en "controladores y bases de datos", modelas conceptos del negocio como **Pedidos, Productos, Pagos** y cómo interactúan.

📌 **DDD se basa en:**  
✔ **Lenguaje Ubicuo (Ubiquitous Language):** Términos del negocio en código.  
✔ **Modelo de Dominio:** Representación de la lógica del negocio.  
✔ **Bounded Contexts:** Separación de diferentes áreas del sistema.  
✔ **Entidades, Agregados, Value Objects y Repositorios.**

✅ **DDD NO es solo un patrón, es una mentalidad de diseño.**

---
## **📌 Conceptos Clave en DDD** 🎯

### **📍 1.1 El Dominio** 🏛️
💡 **El dominio es el problema que la empresa quiere resolver**.

Ejemplo:  
Si estamos creando un sistema para una tienda en línea, el **dominio** es la gestión de productos, pedidos y clientes.

---

### **📍 1.2 Modelo de Dominio** 🛠️
💡 **Es una representación del dominio en código**.

📌 **Ejemplo:** Si en la tienda los pedidos tienen estados como `"Pendiente"`, `"Enviado"` y `"Entregado"`, el modelo de dominio reflejará eso en clases y objetos.

```java
public class Pedido {
    private String id;
    private EstadoPedido estado;

    public Pedido(String id) {
        this.id = id;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public void enviar() {
        if (estado == EstadoPedido.PENDIENTE) {
            estado = EstadoPedido.ENVIADO;
        } else {
            throw new IllegalStateException("No se puede enviar un pedido que no está pendiente.");
        }
    }

    public EstadoPedido getEstado() {
        return estado;
    }
}

enum EstadoPedido {
    PENDIENTE, ENVIADO, ENTREGADO
}
```

✅ **Este código refleja la realidad del negocio:** Un pedido empieza como `"Pendiente"`, y solo puede pasar a `"Enviado"` si está en el estado correcto.

---

### **📍 1.3 Lenguaje Ubicuo (Ubiquitous Language)** 🗣️
💡 **Todos los que trabajan en el proyecto (programadores, diseñadores y expertos del negocio) deben usar el mismo lenguaje**.

Ejemplo:
- El **experto en la tienda** dice: "Un pedido puede estar Pendiente, Enviado o Entregado".
- El **programador** crea la clase `Pedido` con un `EstadoPedido` que puede ser `"PENDIENTE"`, `"ENVIADO"` o `"ENTREGADO"`.

✅ **Esto evita confusión y hace que todos entiendan el negocio de la misma forma**.

---

## **📌 2️⃣ Elementos Principales de DDD** 🏗️

DDD divide la aplicación en varias **capas y componentes**, para que el código sea más organizado y escalable.

### **📍 2.1 Entidades 📦 (Entities)**
💡 **Objetos con identidad única que cambian con el tiempo.**

Ejemplo:
- Un **Pedido** tiene un `ID único` y cambia de estado con el tiempo.

```java
public class Pedido {
    private String id;
    private EstadoPedido estado;

    public Pedido(String id) {
        this.id = id;
        this.estado = EstadoPedido.PENDIENTE;
    }
}
```

---

### **📍 2.2 Value Objects 🎭**
💡 **Objetos que representan valores, pero no tienen identidad única.**

Ejemplo:
- Un **Precio** con su valor y moneda es un `Value Object`.

```java
public class Precio {
    private final double valor;
    private final String moneda;

    public Precio(double valor, String moneda) {
        this.valor = valor;
        this.moneda = moneda;
    }
}
```
✅ Si tienes dos precios con `10 EUR`, no necesitas diferenciarlos porque representan lo mismo.

---

### **📍 2.3 Agregados 📚 (Aggregates)**
💡 **Conjunto de entidades y objetos de valor que se comportan como una unidad.**

Ejemplo:
- Un **Pedido** tiene **Productos**, pero los productos **no pueden cambiarse directamente**, sino solo a través del `Pedido`.

```java
public class Pedido {
    private String id;
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
}
```
✅ **Pedido** es el **agregado**, y **Producto** es parte del agregado.

---

### **📍 2.4 Repositorios 📂 (Repositories)**
💡 **Se encargan de guardar y recuperar entidades.**

```java
public interface PedidoRepositorio {
    Pedido buscarPorId(String id);
    void guardar(Pedido pedido);
}
```
✅ **Esto permite separar la lógica de negocio de la base de datos**.

---

### **📍 2.5 Servicios de Dominio ⚙️ (Domain Services)**
💡 **Lógica del negocio que no pertenece a una sola entidad.**

Ejemplo:  
Un **descuento** se aplica a un `Pedido`, pero el descuento **no pertenece a un solo producto o cliente**, sino que es una regla de negocio.

```java
public class ServicioDescuento {
    public double calcularDescuento(Pedido pedido) {
        if (pedido.getEstado() == EstadoPedido.PENDIENTE) {
            return 10.0; // Descuento del 10%
        }
        return 0;
    }
}
```
✅ **Separamos la lógica del negocio en un servicio independiente.**

---

# **3️⃣ Implementación Completa de un Módulo DDD en Java**
📌 **Estructura del Proyecto:**
```
📂 src/main/java/com/ejemplo/pedidos/
 ├── 📂 domain/         # Lógica de Negocio (DDD)
 │    ├── Pedido.java
 │    ├── LineaDePedido.java
 │    ├── EstadoPedido.java
 │    ├── PedidoRepository.java
 ├── 📂 application/    # Casos de Uso
 │    ├── PedidoService.java
 ├── 📂 infrastructure/ # Infraestructura (DB, API)
 │    ├── PedidoController.java
```
📌 **1️⃣ Capa de Dominio (`Pedido.java`)**
```java
@Entity
public class Pedido {
    @Id @GeneratedValue
    private Long id;
    private String cliente;
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaDePedido> lineas = new ArrayList<>();

    public Pedido(String cliente) {
        this.cliente = cliente;
        this.estado = EstadoPedido.NUEVO;
    }

    public void agregarProducto(String producto, int cantidad) {
        this.lineas.add(new LineaDePedido(producto, cantidad));
    }

    public void marcarComoPagado() {
        if (this.estado != EstadoPedido.NUEVO) {
            throw new IllegalStateException("Pedido ya procesado");
        }
        this.estado = EstadoPedido.PAGADO;
    }
}
```
📌 **2️⃣ Capa de Aplicación (`PedidoService.java`)**
```java
@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido crearPedido(String cliente) {
        Pedido pedido = new Pedido(cliente);
        return pedidoRepository.save(pedido);
    }

    public void agregarProducto(Long pedidoId, String producto, int cantidad) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.agregarProducto(producto, cantidad);
        pedidoRepository.save(pedido);
    }

    public void procesarPago(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.marcarComoPagado();
        pedidoRepository.save(pedido);
    }
}
```
📌 **3️⃣ Capa de Infraestructura (`PedidoController.java`)**
```java
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public Pedido crearPedido(@RequestParam String cliente) {
        return pedidoService.crearPedido(cliente);
    }

    @PostMapping("/{id}/producto")
    public void agregarProducto(@PathVariable Long id, @RequestParam String producto, @RequestParam int cantidad) {
        pedidoService.agregarProducto(id, producto, cantidad);
    }

    @PostMapping("/{id}/pagar")
    public void procesarPago(@PathVariable Long id) {
        pedidoService.procesarPago(id);
    }
}
```




## **📌 3️⃣ Arquitectura Hexagonal en DDD** 🏛️

DDD funciona bien con **Arquitectura Hexagonal (Ports & Adapters)**, que separa la lógica del negocio de la infraestructura.

📌 **Ejemplo de capas en DDD + Hexagonal:**

```
┌───────────────────────────────────────┐
│               API (REST)               │  <-- Controlador
├───────────────────────────────────────┤
│           Aplicación (Use Cases)       │  <-- Casos de Uso
├───────────────────────────────────────┤
│           Dominio (Reglas de Negocio)  │  <-- Entidades, Servicios, Repositorios
├───────────────────────────────────────┤
│       Infraestructura (Base de Datos)  │  <-- Implementación de Repositorios
└───────────────────────────────────────┘
```

✅ **Ventajas:**
- 🎯 **El negocio no depende de la base de datos.**
- 🔄 **Puedes cambiar la API (REST, GraphQL) sin afectar la lógica.**
- 🔌 **Puedes cambiar la base de datos sin cambiar el negocio.**

---

## **📌 4️⃣ Resumen de DDD** 🎯

| Concepto          | Explicación |
|------------------|------------|
| **Dominio**       | El problema real que resuelve la aplicación. |
| **Lenguaje Ubicuo** | Todos los equipos usan el mismo lenguaje del negocio. |
| **Entidades**      | Objetos con identidad única y cambios en el tiempo. |
| **Value Objects**  | Objetos sin identidad, solo valores. |
| **Agregados**     | Conjunto de entidades tratadas como una sola unidad. |
| **Repositorios**   | Manejan la persistencia de entidades. |
| **Servicios de Dominio** | Lógica de negocio fuera de las entidades. |

✅ **DDD ayuda a crear software más claro, escalable y alineado con el negocio.**

---


Finalmente! En esta misma carpeta tienes un ejemplo de proyecto con DDD en Java con Spring Boot. 🚀