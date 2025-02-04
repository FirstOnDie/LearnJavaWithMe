# **📌 Día 15: Creación de APIs REST con Spring Boot**
Hoy aprenderás:  
✅ **Controladores y Endpoints REST**  
✅ **DTOs y Validaciones con Spring Boot**  
✅ **Swagger y OpenAPI para documentar APIs**  
✅ **Ejercicio: API REST para gestión de pedidos**

---

📌 **¿Por qué es importante?**  
Spring Boot permite crear **APIs REST robustas y escalables** con una configuración mínima. Además, con **Swagger y OpenAPI** documentamos fácilmente nuestros servicios.

---

# **1️⃣ Creando un Proyecto Spring Boot**
📌 **Generar el proyecto en [Spring Initializr](https://start.spring.io/)**  
✔ **Dependencias necesarias:**  
✅ **Spring Web** → Para construir la API REST.  
✅ **Spring Boot Validation** → Para validar datos de entrada.  
✅ **Spring Boot DevTools** → Para desarrollo rápido.  
✅ **Lombok** → Para reducir código repetitivo.  
✅ **Spring Data JPA** → Para conexión con base de datos (si la usas).  
✅ **H2 Database** → Base de datos en memoria (opcional).  
✅ **Springdoc OpenAPI** → Para documentación Swagger.

📌 **Estructura del Proyecto:**
```
📂 src/
 ├── 📂 main/java/com/ejemplo/pedidos/
 │    ├── 📂 controller/    # Controladores REST
 │    │    ├── PedidoController.java
 │    ├── 📂 dto/           # Objetos de transferencia (DTO)
 │    │    ├── PedidoDTO.java
 │    ├── 📂 service/       # Lógica de negocio
 │    │    ├── PedidoService.java
 │    ├── 📂 repository/    # Persistencia de datos
 │    │    ├── PedidoRepository.java
 │    ├── 📂 model/         # Entidades de base de datos
 │    │    ├── Pedido.java
 │    ├── PedidoApplication.java  # Clase principal
```

---

# **2️⃣ Implementación Paso a Paso**

---

## **1️⃣ Modelo de Datos (`Pedido.java`)**
📌 **Creamos la entidad `Pedido` con Lombok y JPA.**
```java
package com.ejemplo.pedidos.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String producto;
    private int cantidad;
    private double precio;
}
```
✅ **Con `@Entity`, `@Table` y `@Id`, Spring Boot maneja la base de datos automáticamente.**  
✅ **`@Data` de Lombok genera `getters/setters` automáticamente.**

---

## **2️⃣ DTO para Transferencia de Datos (`PedidoDTO.java`)**
📌 **Usamos DTOs para evitar exponer directamente nuestras entidades.**
```java
package com.ejemplo.pedidos.dto;

import jakarta.validation.constraints.*;

public record PedidoDTO(
    @NotBlank(message = "El producto no puede estar vacío")
    String producto,

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    int cantidad,

    @DecimalMin(value = "0.1", message = "El precio debe ser mayor a 0")
    double precio
) {}
```
✅ **Spring usa `record` en Java 17+ para DTOs inmutables.**  
✅ **`@NotBlank`, `@Min` y `@DecimalMin` validan los datos automáticamente.**

---

## **3️⃣ Repositorio de Datos (`PedidoRepository.java`)**
📌 **Creamos el repositorio con Spring Data JPA.**
```java
package com.ejemplo.pedidos.repository;

import com.ejemplo.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
```
✅ **`JpaRepository` proporciona CRUD sin escribir SQL.**

---

## **4️⃣ Servicio para la Lógica de Negocio (`PedidoService.java`)**
📌 **Manejamos la lógica de negocio separada del controlador.**
```java
package com.ejemplo.pedidos.service;

import com.ejemplo.pedidos.dto.PedidoDTO;
import com.ejemplo.pedidos.model.Pedido;
import com.ejemplo.pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido crearPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido(null, pedidoDTO.producto(), pedidoDTO.cantidad(), pedidoDTO.precio());
        return pedidoRepository.save(pedido);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
```
✅ **El servicio transforma `PedidoDTO` en `Pedido` antes de guardarlo.**

---

## **5️⃣ Controlador REST (`PedidoController.java`)**
📌 **Creamos un controlador REST con Spring Boot.**
```java
package com.ejemplo.pedidos.controller;

import com.ejemplo.pedidos.dto.PedidoDTO;
import com.ejemplo.pedidos.model.Pedido;
import com.ejemplo.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@Valid @RequestBody PedidoDTO pedidoDTO) {
        Pedido nuevoPedido = pedidoService.crearPedido(pedidoDTO);
        return ResponseEntity.ok(nuevoPedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
```
📌 **Endpoints de la API:**  
✔ `GET /pedidos` → Listar pedidos.  
✔ `POST /pedidos` → Crear pedido (**validado con `@Valid`**).  
✔ `DELETE /pedidos/{id}` → Eliminar pedido.

---

# **3️⃣ Documentación con Swagger y OpenAPI**
📌 **Agregamos `springdoc-openapi-starter-webmvc-ui` en `pom.xml`:**
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```
📌 **Ejecutamos la aplicación y accedemos a Swagger:**  
📍 **http://localhost:8080/swagger-ui.html**

---

# **4️⃣ Pruebas de la API con `curl` o Postman**
📌 **Ejemplo de petición para crear un pedido:**
```sh
curl -X POST http://localhost:8080/pedidos \
-H "Content-Type: application/json" \
-d '{"producto": "Laptop", "cantidad": 2, "precio": 1200.50}'
```
📌 **Respuesta esperada:**
```json
{
  "id": 1,
  "producto": "Laptop",
  "cantidad": 2,
  "precio": 1200.50
}
```
📌 **Ejemplo para listar pedidos:**
```sh
curl -X GET http://localhost:8080/pedidos
```
📌 **Ejemplo para eliminar un pedido:**
```sh
curl -X DELETE http://localhost:8080/pedidos/1
```

---
