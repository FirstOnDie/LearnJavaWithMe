# **📌 CQRS y Event Sourcing con Axon Framework** 🚀

Si trabajas con **arquitecturas orientadas a eventos**, es clave conocer **CQRS (Command Query Responsibility Segregation)** y **Event Sourcing** con **Axon Framework**. Estas técnicas son utilizadas en **sistemas distribuidos**, donde la escalabilidad, la auditabilidad y la flexibilidad son fundamentales.

---

## **🔹 ¿Qué son CQRS y Event Sourcing?**

| Concepto          | Descripción |
|-------------------|-------------|
| **CQRS** (*Command Query Responsibility Segregation*) | Separa la lógica de lectura (*queries*) de la lógica de escritura (*commands*). |
| **Event Sourcing** | En lugar de almacenar solo el estado actual, se almacenan **todos los eventos pasados**, permitiendo reconstruir el estado en cualquier punto del tiempo. |

### 📌 **¿Por qué usar CQRS y Event Sourcing?**
✔ **Mayor escalabilidad:** Separar comandos y consultas optimiza el rendimiento.  
✔ **Auditabilidad completa:** Se puede reconstruir el estado de un sistema en cualquier momento.  
✔ **Menor carga en bases de datos transaccionales:** Se reduce la dependencia de consultas SQL complejas.  
✔ **Compatible con Kafka, RabbitMQ y gRPC:** Funciona bien en sistemas distribuidos.

---

## **📌 1. Arquitectura de CQRS con Axon Framework**

🔹 **Modelo tradicional vs. CQRS**  
📌 En un sistema tradicional, la base de datos almacena el **estado actual** de un objeto:
```sql
ID   | Cliente   | Estado  
--------------------------
001  | Juan      | ENVIADO  
002  | Ana       | PENDIENTE  
```
📌 Con **Event Sourcing**, en lugar de almacenar solo el estado actual, guardamos **todos los eventos que llevaron a ese estado**:
```
PedidoCreado(ID=001, Cliente=Juan)  
PedidoPagado(ID=001)  
PedidoEnviado(ID=001)  
```
✅ **Ventaja**: Se puede auditar y reconstruir cualquier estado del sistema.

---

## **📌 2. Instalación de Axon Framework**
Para usar **Axon Framework**, agrega las siguientes dependencias en tu `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.axonframework</groupId>
        <artifactId>axon-spring-boot-starter</artifactId>
        <version>4.7.0</version>
    </dependency>
</dependencies>
```
💡 **Axon se integra fácilmente con Spring Boot**, lo que facilita su configuración.

---

## **📌 3. Definir un evento en Axon Framework**
Cada cambio en el sistema se representa como un **evento**.  
📌 **Ejemplo: Evento cuando se crea un pedido**

```java
import lombok.Value;
import org.axonframework.serialization.Revision;

@Value
@Revision("1.0") // Mantiene compatibilidad en cambios futuros
public class PedidoCreadoEvent {
    private final String pedidoId;
    private final String clienteId;
    private final double total;
}
```
✅ **Cada evento es inmutable y se almacena en la base de datos de eventos**.

---

## **📌 4. Crear un Agregado (Aggregate)**
Un **aggregate** en Axon representa una entidad que genera eventos.  
📌 **Ejemplo: Crear un agregado `Pedido`**

```java
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Pedido {

    @AggregateIdentifier
    private String pedidoId;
    private String clienteId;
    private double total;

    // Constructor vacío necesario para Axon
    public Pedido() {}

    @CommandHandler
    public Pedido(CrearPedidoCommand cmd) {
        // Aplicar un evento cuando se recibe el comando
        apply(new PedidoCreadoEvent(cmd.getPedidoId(), cmd.getClienteId(), cmd.getTotal()));
    }

    @EventSourcingHandler
    public void on(PedidoCreadoEvent event) {
        // Aplicar cambios al agregado
        this.pedidoId = event.getPedidoId();
        this.clienteId = event.getClienteId();
        this.total = event.getTotal();
    }
}
```
📌 **Explicación:**
✔ `@Aggregate`: Marca la clase como un **aggregate** en Axon.  
✔ `@CommandHandler`: Define cómo responder a un **comando**.  
✔ `@EventSourcingHandler`: Aplica cambios basados en eventos almacenados.

---

## **📌 5. Enviar Comandos con Axon**
Un **comando** es una solicitud para cambiar el estado del sistema.  
📌 **Ejemplo: Comando para crear un pedido**

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class CrearPedidoCommand {
    @TargetAggregateIdentifier
    private String pedidoId;
    private String clienteId;
    private double total;
}
```
📌 **Ejemplo: Enviar un comando desde un controlador REST**
```java
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CommandGateway commandGateway;

    public PedidoController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String crearPedido(@RequestParam String clienteId, @RequestParam double total) {
        String pedidoId = UUID.randomUUID().toString();
        CrearPedidoCommand command = new CrearPedidoCommand(pedidoId, clienteId, total);
        commandGateway.send(command);
        return "Pedido creado con ID: " + pedidoId;
    }
}
```
📌 **Explicación:**
✔ `CommandGateway.send(command)`: Envía el comando a Axon.  
✔ Axon procesa el comando, lo convierte en un evento y lo almacena.

---

## **📌 6. Consultar Eventos con Axon**
Los eventos se pueden consultar desde una **proyección**.  
📌 **Ejemplo: Proyección en una base de datos relacional**
```java
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PedidoProyeccion {

    private final Map<String, String> pedidos = new HashMap<>();

    @EventHandler
    public void on(PedidoCreadoEvent event) {
        pedidos.put(event.getPedidoId(), event.getClienteId());
        System.out.println("Pedido proyectado: " + event.getPedidoId());
    }
}
```
📌 **Explicación:**
✔ `@EventHandler`: Permite que Axon capture eventos y actualice vistas.  
✔ **Separa la lógica de escritura (commands) de la lógica de lectura (queries)**.

---

## **📌 7. Integración con Kafka y RabbitMQ**
Axon se integra con **Kafka, RabbitMQ o WebSockets** para distribuir eventos.  
📌 **Ejemplo: Configuración de Axon con Kafka en `application.yml`**
```yaml
axon:
  eventhandling:
    processors:
      kafkaEventProcessor:
        mode: tracking
  kafka:
    producer:
      bootstrap-servers: localhost:9092
```
📌 **Ejemplo: Enviar eventos a Kafka**
```java
@Bean
public KafkaEventPublisher kafkaPublisher(KafkaTemplate<String, String> kafkaTemplate) {
    return new KafkaEventPublisher(kafkaTemplate);
}
```
✅ **Ventaja**: **CQRS + Event Sourcing + Kafka** es ideal para **sistemas distribuidos**.

---

## **📌 Ventajas y Desventajas de CQRS y Event Sourcing**
### ✅ **Ventajas**
✔ **Escalabilidad**: Permite manejar altos volúmenes de tráfico.  
✔ **Auditabilidad**: Se pueden reconstruir estados pasados.  
✔ **Optimización de consultas**: Se pueden estructurar mejor las bases de datos.

### ❌ **Desventajas**
❌ **Mayor complejidad** en comparación con un CRUD tradicional.  
❌ **Mayor consumo de almacenamiento** debido a la persistencia de eventos.  
❌ **Curva de aprendizaje** para entender Axon Framework.

---

# **📌 Conclusión**
✅ **CQRS y Event Sourcing** con **Axon Framework** son ideales para **sistemas distribuidos y escalables**.  
✅ Separar **comandos y consultas** mejora el rendimiento y la optimización de datos.  
✅ **Integraciones con Kafka y RabbitMQ** permiten manejar eventos de forma eficiente.