# **📌 Introducción a Domain-Driven Design (DDD)**
Hoy aprenderás:  
✅ **Conceptos clave de DDD**  
✅ **Modelo de dominio, entidades y agregados**  
✅ **Event-Driven Architecture con DDD**  
✅ **Ejemplo práctico en Java con Spring Boot**

---

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

# **2️⃣ Conceptos Clave de DDD**
📌 **1️⃣ Bounded Contexts (Contextos Delimitados)**  
✔ Un **sistema grande** se divide en **múltiples contextos** independientes.  
✔ Cada contexto tiene **su propio modelo y base de datos** si es necesario.  
✔ **Ejemplo:** Un sistema de e-commerce tiene estos **Bounded Contexts**:
- **Pedidos (Orders)**
- **Pagos (Payments)**
- **Usuarios (Users)**

📌 **2️⃣ Entidades y Value Objects**  
✔ **Entidad:** Tiene un identificador único y cambia con el tiempo.  
✔ **Value Object:** No tiene identidad, solo representa un valor.

📌 **Ejemplo en Java:**
```java
@Entity
public class Pedido {
    @Id @GeneratedValue
    private Long id;
    private String cliente;
    private EstadoPedido estado; // Value Object

    public Pedido(String cliente) {
        this.cliente = cliente;
        this.estado = EstadoPedido.NUEVO;
    }
}
```
📌 **Value Object (`EstadoPedido` como un `enum`)**
```java
public enum EstadoPedido {
    NUEVO, PAGADO, ENVIADO, CANCELADO;
}
```
✅ **Las entidades tienen identidad, los Value Objects no.**

---

📌 **3️⃣ Agregados y Repositorios**  
✔ **Un Agregado es un grupo de entidades con una raíz (Aggregate Root).**  
✔ **El acceso a los datos debe pasar siempre por la raíz del agregado.**  
✔ **Ejemplo:** Un `Pedido` (raíz) contiene `LineasDePedido`.

📌 **Ejemplo en Java:**
```java
@Entity
public class Pedido {
    @Id @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaDePedido> lineas = new ArrayList<>();

    public void agregarProducto(String producto, int cantidad) {
        this.lineas.add(new LineaDePedido(producto, cantidad));
    }
}
```
📌 **Repositorio (`PedidoRepository.java`)**
```java
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
```
✅ **Siempre usamos `PedidoRepository` para modificar el agregado.**

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

Finalmente! En esta misma carpeta tienes un ejemplo de proyecto con DDD en Java con Spring Boot. 🚀