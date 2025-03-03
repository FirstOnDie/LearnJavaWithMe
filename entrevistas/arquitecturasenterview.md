# **📌 Preguntas y Respuestas para Entrevista Técnica – Arquitecturas de Software en Java**

### ❓ **Pregunta:** ¿Qué es la Arquitectura Hexagonal y cuál es su objetivo?
✅ **Respuesta:**  
📌 **La Arquitectura Hexagonal (o Puertos y Adaptadores)** busca desacoplar el **núcleo de negocio** de los detalles de implementación como bases de datos, interfaces web y APIs externas.

📌 **Beneficios:**  
✔ Facilita la **prueba unitaria del dominio** sin depender de infraestructura.  
✔ Permite cambiar tecnologías sin afectar la lógica de negocio.  
✔ Fomenta el **desarrollo orientado a interfaces**, facilitando la extensibilidad.

📌 **Ejemplo de capas en Arquitectura Hexagonal:**
```
┌──────────────────┐
│  UI (REST, CLI)  │  ⬅ Adaptador de Entrada  
└──────────────────┘
       ↓  
┌────────────────────────────┐  
│  Aplicación (Casos de Uso) │  ⬅ Core  
└────────────────────────────┘  
       ↓  
┌───────────────────────────────────────┐  
│  Infraestructura (DB, APIs externas)  │  ⬅ Adaptador de Salida  
└───────────────────────────────────────┘  
```

---

### ❓ **Pregunta:** ¿Cómo se implementa la separación de capas en Arquitectura Hexagonal en Java?
✅ **Respuesta:**  
📌 **Ejemplo de implementación:**

📌 **1️⃣ Adaptador de Entrada (REST Controller en Spring Boot):**
```java
@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService servicio;

    public ProductoController(ProductoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.obtenerProducto(id));
    }
}
```

📌 **2️⃣ Aplicación (Caso de Uso / Servicio de Negocio):**
```java
@Service
public class ProductoService {
    private final ProductoRepositorio repositorio;

    public ProductoService(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Producto obtenerProducto(Long id) {
        return repositorio.buscarPorId(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }
}
```

📌 **3️⃣ Adaptador de Salida (Repositorio con JPA):**
```java
@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
}
```
✅ **Esto separa la lógica de negocio de las dependencias externas, facilitando el mantenimiento.**

---

### ❓ **Pregunta:** ¿Qué es Domain-Driven Design (DDD) y cuál es su propósito?
✅ **Respuesta:**  
📌 **DDD** es un enfoque para modelar software basándose en el **dominio del negocio**, asegurando que la lógica refleje fielmente el problema real.

📌 **Beneficios:**  
✔ Separa el **dominio** de la infraestructura.  
✔ Facilita la colaboración entre desarrolladores y expertos del negocio.  
✔ Mejora la **escalabilidad y mantenibilidad** del código.

📌 **Ejemplo de capas en DDD:**
```
┌────────────┐
│  Interfaz  │  ⬅ API REST, UI  
└────────────┘
       ↓  
┌──────────────┐
│  Aplicación  │  ⬅ Casos de uso  
└──────────────┘
       ↓  
┌───────────┐
│  Dominio  │  ⬅ Entidades y reglas de negocio  
└───────────┘
       ↓  
┌───────────────────┐
│  Infraestructura  │  ⬅ Base de datos, APIs externas  
└───────────────────┘
```

---

### ❓ **Pregunta:** ¿Qué son las entidades y los valores en DDD?
✅ **Respuesta:**  
📌 **Entidad** – Tiene identidad propia y puede cambiar con el tiempo.  
📌 **Objeto de Valor (Value Object)** – No tiene identidad y es inmutable.

📌 **Ejemplo en Java:**
```java
@Entity
public class Cliente {
    @Id
    private Long id;
    private String nombre;
    private Direccion direccion; // Objeto de Valor

    public Cliente(Long id, String nombre, Direccion direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }
}
```

```java
@Embeddable
public class Direccion {
    private String calle;
    private String ciudad;

    public Direccion(String calle, String ciudad) {
        this.calle = calle;
        this.ciudad = ciudad;
    }
}
```
✅ **Separar entidades de objetos de valor mejora la coherencia del dominio.**

---

### ❓ **Pregunta:** ¿Qué es la Arquitectura Orientada a Eventos (EDA)?
✅ **Respuesta:**  
📌 **EDA** permite que los sistemas se comuniquen mediante **eventos asíncronos**, mejorando la escalabilidad.

📌 **Beneficios:**  
✔ Desacopla los servicios.  
✔ Escalable y distribuido.  
✔ Permite responder en tiempo real.

📌 **Ejemplo con Kafka en Java:**
```java
@Component
public class EventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public EventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarEvento(String mensaje) {
        kafkaTemplate.send("mi-topic", mensaje);
    }
}
```
✅ **Esto publica eventos a Kafka, permitiendo comunicación asíncrona entre servicios.**

---

### ❓ **Pregunta:** ¿Qué es TDD y cómo mejora el desarrollo?
✅ **Respuesta:**  
📌 **TDD** es un enfoque donde se escriben **pruebas antes del código**, asegurando que cada funcionalidad esté bien definida desde el principio.

📌 **Beneficios:**  
✔ Código más limpio y mantenible.  
✔ Reduce errores en producción.  
✔ Mejora el diseño del software.

📌 **Ciclo TDD:**  
1️⃣ Escribir una **prueba fallida** (`RED`).  
2️⃣ Escribir el **mínimo código necesario** para que pase (`GREEN`).  
3️⃣ **Refactorizar** (`REFACTOR`).

📌 **Ejemplo en JUnit:**
```java
@Test
void sumaDebeRetornarResultadoCorrecto() {
    Calculadora calc = new Calculadora();
    assertEquals(5, calc.sumar(2, 3));
}
```

📌 **Código mínimo para pasar la prueba:**
```java
public class Calculadora {
    public int sumar(int a, int b) {
        return a + b;
    }
}
```
✅ **Esto garantiza que cada funcionalidad tenga una prueba clara desde el inicio.**

---

# 🎯 **Conclusión**
📌 **Arquitectura Hexagonal** – Desacopla lógica de infraestructura.  
📌 **DDD** – Modela software basado en el dominio del negocio.  
📌 **EDA** – Usa eventos para comunicación asíncrona.  
📌 **TDD** – Escribe pruebas antes del código para mayor calidad.
