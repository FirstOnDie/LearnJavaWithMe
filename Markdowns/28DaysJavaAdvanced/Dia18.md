# **📌 Día 18: Spring Boot y Microservicios**
Hoy aprenderás:  
✅ **Eureka (Service Discovery)**  
✅ **Feign Clients (Comunicación entre microservicios)**  
✅ **Resilience4J (Circuit Breaker para tolerancia a fallos)**  
✅ **Ejercicio: Microservicio de pagos con comunicación entre servicios**

---

📌 **¿Por qué es importante?**  
Los **microservicios** permiten desarrollar aplicaciones **escalables, independientes y resilientes**. Con **Spring Boot**, **Spring Cloud Eureka**, **Feign Clients** y **Circuit Breaker**, podemos manejar servicios distribuidos de manera eficiente.

---

# **1️⃣ Arquitectura de Microservicios con Spring Boot**
📌 **Componentes Clave en Microservicios**  
✅ **Service Discovery** → *Eureka Server* (Registro de servicios).  
✅ **Comunicación entre servicios** → *Feign Client*.  
✅ **Balanceo de carga** → *Ribbon (integrado en Feign)*.  
✅ **Tolerancia a fallos** → *Circuit Breaker con Resilience4J*.

📌 **Estructura del Proyecto:**
```
📂 microservicios/
 ├── 📂 eureka-server/      # Service Discovery
 ├── 📂 pedidos-service/    # Microservicio de Pedidos
 ├── 📂 pagos-service/      # Microservicio de Pagos
 ├── 📂 gateway-service/    # API Gateway (Opcional)
```

---

# **2️⃣ Creando el Servidor Eureka (`eureka-server`)**
📌 **Este microservicio se encarga de registrar y descubrir servicios.**

📌 **Generamos un proyecto en Spring Initializr con:**  
✔ **Spring Cloud Eureka Server**  
✔ **Spring Boot Web**

📌 **Configuramos `application.yml`**
```yaml
server:
  port: 8761

eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
```

📌 **Habilitamos Eureka Server (`EurekaServerApplication.java`)**
```java
package com.ejemplo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```
📌 **Ejecutamos Eureka Server y accedemos a:**  
📍 **http://localhost:8761/**

✅ **Aquí veremos todos los microservicios registrados.**

---

# **3️⃣ Creando el Microservicio de Pedidos (`pedidos-service`)**
📌 **Este microservicio maneja pedidos y se comunica con `pagos-service`.**

📌 **Generamos un proyecto con:**  
✔ **Spring Web**  
✔ **Spring Boot JPA + H2 Database**  
✔ **Spring Cloud Eureka Client**  
✔ **OpenFeign (para comunicación entre microservicios)**

📌 **Configuramos `application.yml`**
```yaml
server:
  port: 8081

spring:
  application:
    name: pedidos-service

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

📌 **Modelo `Pedido.java`**
```java
package com.ejemplo.pedidos.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

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
    private BigDecimal total;
}
```

📌 **Repositorio `PedidoRepository.java`**
```java
package com.ejemplo.pedidos.repository;

import com.ejemplo.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
```

📌 **Feign Client para comunicación con `pagos-service`**
```java
package com.ejemplo.pedidos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "pagos-service")
public interface PagosClient {
    @PostMapping("/pagos/procesar")
    String procesarPago(@RequestParam Long pedidoId, @RequestParam BigDecimal monto);
}
```
✅ **Feign simplifica las llamadas entre microservicios.**

📌 **Controlador `PedidoController.java`**
```java
package com.ejemplo.pedidos.controller;

import com.ejemplo.pedidos.client.PagosClient;
import com.ejemplo.pedidos.model.Pedido;
import com.ejemplo.pedidos.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoRepository pedidoRepository;
    private final PagosClient pagosClient;

    public PedidoController(PedidoRepository pedidoRepository, PagosClient pagosClient) {
        this.pedidoRepository = pedidoRepository;
        this.pagosClient = pagosClient;
    }

    @PostMapping
    public String crearPedido(@RequestBody Pedido pedido) {
        pedidoRepository.save(pedido);
        return pagosClient.procesarPago(pedido.getId(), pedido.getTotal());
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}
```
✅ **Cuando se crea un pedido, se llama a `pagos-service` para procesar el pago.**

---

# **4️⃣ Creando el Microservicio de Pagos (`pagos-service`)**
📌 **Este microservicio procesa los pagos de los pedidos.**

📌 **Configuramos `application.yml`**
```yaml
server:
  port: 8082

spring:
  application:
    name: pagos-service

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

📌 **Controlador `PagoController.java`**
```java
package com.ejemplo.pagos.controller;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @PostMapping("/procesar")
    public String procesarPago(@RequestParam Long pedidoId, @RequestParam BigDecimal monto) {
        return "Pago de $" + monto + " procesado para el pedido " + pedidoId;
    }
}
```
✅ **El servicio de pagos simula un procesamiento exitoso.**

---

# **5️⃣ Implementando Circuit Breaker con Resilience4J**
📌 **Si `pagos-service` falla, `pedidos-service` manejará la excepción.**

📌 **Agregamos dependencia en `pom.xml`**
```xml
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot2</artifactId>
</dependency>
```

📌 **Modificamos `PagosClient.java` para agregar `@CircuitBreaker`**
```java
@FeignClient(name = "pagos-service")
public interface PagosClient {

    @CircuitBreaker(name = "pagos-service", fallbackMethod = "fallbackPago")
    @PostMapping("/pagos/procesar")
    String procesarPago(@RequestParam Long pedidoId, @RequestParam BigDecimal monto);

    default String fallbackPago(Long pedidoId, BigDecimal monto, Throwable ex) {
        return "No se pudo procesar el pago del pedido " + pedidoId + ". Inténtelo más tarde.";
    }
}
```
✅ **Si `pagos-service` está caído, se ejecuta `fallbackPago()`.**
