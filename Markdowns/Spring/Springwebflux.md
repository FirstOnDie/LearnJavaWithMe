# **📌 ¿Qué es la Programación Reactiva?**
La **Programación Reactiva** es un modelo de desarrollo basado en la manipulación de **flujos de datos asíncronos**. En lugar de esperar a que una tarea termine antes de ejecutar la siguiente, se pueden manejar múltiples operaciones de forma concurrente sin bloquear hilos.

🔹 **Ejemplo sencillo:** Imagina que tienes un restaurante y en lugar de atender a un cliente a la vez, el mesero toma órdenes, las manda a la cocina y sigue atendiendo más clientes sin esperar a que el primero termine su comida.

✅ **Ventajas de la Programación Reactiva:**
- 🔹 **Mayor rendimiento:** Maneja miles de peticiones simultáneas con menos recursos.
- 🔹 **No bloqueante:** No espera a que una tarea termine para iniciar otra.
- 🔹 **Escalable:** Ideal para aplicaciones con alta concurrencia, como APIs y microservicios.

---

# **📌 ¿Qué es Spring WebFlux?**
Spring WebFlux es el **framework reactivo de Spring** que permite construir APIs asíncronas y no bloqueantes usando el modelo de **Programación Reactiva**.

📌 **Diferencias clave entre Spring MVC y Spring WebFlux:**  
| Característica        | Spring MVC (Imperativo) | Spring WebFlux (Reactivo) |
|----------------------|----------------------|------------------------|
| **Modelo**          | Basado en hilos bloqueantes (`Servlet API`) | Basado en eventos (`Reactor`) |
| **Escalabilidad**   | Hilos limitados por `ThreadPool` | Maneja miles de peticiones concurrentes |
| **Rendimiento**     | Bloqueante (1 hilo por petición) | No bloqueante (menos consumo de memoria) |
| **Uso recomendado** | Aplicaciones estándar y síncronas | Microservicios, IoT, streaming de datos |

---

# **📌 Creando una API REST con Spring WebFlux**
¡Vamos a crear una API REST reactiva desde cero con Spring Boot y WebFlux! 🚀

### **1️⃣ Dependencias necesarias (`pom.xml`)**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

Esto incluye todo lo necesario para usar **Spring WebFlux** y manejar flujos de datos reactivos.

---

### **2️⃣ Definiendo un modelo de datos (`Producto.java`)**
```java
public class Producto {
    private String id;
    private String nombre;
    private double precio;

    public Producto(String id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y Setters
}
```

---

### **3️⃣ Creando un `Repository` Reactivo (`ProductoRepository.java`)**
```java
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoRepository extends ReactiveMongoRepository<Producto, String> {
}
```
🔹 **Nota:** WebFlux se integra muy bien con **MongoDB** ya que Mongo soporta operaciones no bloqueantes.

---

### **4️⃣ Implementando el `Service` Reactivo (`ProductoService.java`)**
```java
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoService {
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public Flux<Producto> obtenerTodos() {
        return repository.findAll(); // Devuelve un Flux con todos los productos
    }

    public Mono<Producto> obtenerPorId(String id) {
        return repository.findById(id); // Devuelve un Mono con un solo producto
    }

    public Mono<Producto> guardarProducto(Producto producto) {
        return repository.save(producto);
    }
}
```
📌 **Explicación de `Flux` y `Mono`:**
- **`Flux<T>`** → Flujo de **múltiples elementos** (ej.: una lista de productos).
- **`Mono<T>`** → Flujo de **un solo elemento** (ej.: un producto por ID).

---

### **5️⃣ Creando un `Controller` Reactivo (`ProductoController.java`)**
```java
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public Flux<Producto> obtenerTodos() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Mono<Producto> obtenerPorId(@PathVariable String id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping
    public Mono<Producto> crearProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }
}
```
📌 **¿Qué pasa aquí?**  
🔹 `@GetMapping("/{id}") → Devuelve un `Mono<Producto>` porque es un único resultado.  
🔹 `@GetMapping` → Devuelve un `Flux<Producto>` porque es una lista de productos.  
🔹 `@PostMapping` → Guarda un producto y devuelve un `Mono<Producto>`.

---

# **📌 Ventajas de usar Spring WebFlux**
✅ **Eficiencia en el manejo de recursos** → Usa menos memoria y menos hilos.  
✅ **Soporte nativo para eventos y streaming** → Ideal para **Kafka, RabbitMQ, WebSockets**.  
✅ **Mejor rendimiento en aplicaciones concurrentes** → Maneja miles de peticiones sin bloquear hilos.  
✅ **Integración con bases de datos no bloqueantes** → **MongoDB, Redis, Cassandra**.

📌 **¿Cuándo deberías usar WebFlux?**  
✔ APIs con alta concurrencia y escalabilidad.  
✔ Aplicaciones **reactivas** en tiempo real (ej.: notificaciones, chat, IoT).  
✔ Integraciones con **Kafka, RabbitMQ, SSE y WebSockets**.

📌 **¿Cuándo es mejor quedarse con Spring MVC?**  
✔ Aplicaciones tradicionales con consultas síncronas a bases de datos relacionales.  
✔ Si tu equipo ya está acostumbrado a trabajar con `ThreadPool`.

---

# **📌 Comparación de Spring MVC vs WebFlux en rendimiento**
| Escenario | Spring MVC (Bloqueante) | Spring WebFlux (Reactivo) |
|-----------|------------------------|-------------------------|
| 1000 peticiones concurrentes | Necesita más hilos (`ThreadPool`) | Maneja peticiones con menos hilos |
| Uso de memoria | Alto consumo | Bajo consumo |
| Streaming de datos | No soportado nativamente | Soportado con `Flux<T>` |
| Integración con Kafka/RabbitMQ | Limitado | Soporte nativo |
| Complejidad | Fácil para principiantes | Mayor curva de aprendizaje |

---

## **🎯 Conclusión**
📌 **Spring WebFlux** te permite construir **APIs súper rápidas, escalables y eficientes**.  
📌 **Mono y Flux** son la clave para manejar datos de manera **reactiva y no bloqueante**.  
📌 **Ideal para aplicaciones de alto rendimiento** como **microservicios, streaming y eventos**.

---