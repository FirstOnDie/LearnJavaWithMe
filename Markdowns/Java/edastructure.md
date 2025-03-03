# **📌 Event-Driven Architecture (EDA)** ⚡

## **🔹 ¿Qué es EDA?**
La **Arquitectura Orientada a Eventos (EDA)** es un modelo en el que los sistemas **se comunican y reaccionan a eventos en tiempo real**. En lugar de depender de llamadas directas entre servicios, **cada componente actúa cuando ocurre un evento** sin necesidad de estar estrechamente acoplado con otros.

📌 **Ejemplo del mundo real:**  
Piensa en una **estación de trenes** donde los pasajeros esperan anuncios en una pantalla.
1. **Un tren llega a la estación (evento).**
2. **La pantalla se actualiza automáticamente (reacción al evento).**
3. **Los pasajeros suben al tren sin preguntarle a cada empleado si ya pueden abordar.**

**En una arquitectura EDA:**  
✅ Cada servicio se **entera solo de los eventos que le importan**.  
✅ **No hay dependencias directas entre servicios**, lo que reduce el acoplamiento.  
✅ La comunicación es **asíncrona y escalable**.

---  
## **🔹 ¿Cómo funciona EDA?**

### **📌 1. Eventos**
Son notificaciones de que algo ha ocurrido en el sistema. Ejemplo:
- **"Pedido creado"** en una tienda en línea.
- **"Usuario registrado"** en un sistema de autenticación.
- **"Pago aprobado"** en un sistema de facturación.

📌 **Ejemplo de evento en Java:**
```java
public class PedidoCreadoEvent {
    private final String pedidoId;
    private final String clienteId;
    private final double total;

    public PedidoCreadoEvent(String pedidoId, String clienteId, double total) {
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.total = total;
    }

    // Getters
}
```

---

### **📌 2. Productores de eventos (Event Producers)**
Son los servicios o sistemas que generan eventos cuando algo sucede.

📌 **Ejemplo en Java:**  
Un microservicio que crea un pedido y lo publica en un broker de eventos como **Kafka o RabbitMQ**.
```java
@Component
public class PedidoService {
    private final KafkaTemplate<String, PedidoCreadoEvent> kafkaTemplate;

    public PedidoService(KafkaTemplate<String, PedidoCreadoEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void crearPedido(String pedidoId, String clienteId, double total) {
        PedidoCreadoEvent event = new PedidoCreadoEvent(pedidoId, clienteId, total);
        kafkaTemplate.send("pedidos", event); // Publicamos el evento
    }
}
```

---

### **📌 3. Broker de Eventos**
Es la herramienta que **recibe, almacena y distribuye eventos a los consumidores**.

📌 **Ejemplos de Brokers de Eventos:**  
✅ **Kafka** – Escalabilidad masiva y alto rendimiento.  
✅ **RabbitMQ** – Enrutamiento flexible y entrega confiable.  
✅ **Amazon SNS** – Publicación/Suscripción en la nube.  
✅ **Azure Event Grid** – Integración con servicios en Azure.

---

### **📌 4. Consumidores de eventos (Event Consumers)**
Son los servicios que **reaccionan** a eventos generados por otros sistemas.

📌 **Ejemplo en Java:**  
Un servicio de facturación escucha cuando se crea un pedido para generar una factura.
```java
@Component
@KafkaListener(topics = "pedidos", groupId = "facturacion")
public class FacturacionService {
    public void procesarPedido(PedidoCreadoEvent event) {
        System.out.println("Generando factura para pedido: " + event.getPedidoId());
    }
}
```

---

## **🔹 ¿Por qué usar Event-Driven Architecture?**

### **✅ Ventajas de EDA**
✅ **Escalabilidad** → Los servicios son independientes y pueden crecer sin afectar a otros.  
✅ **Desacoplamiento** → Los microservicios no dependen directamente entre sí.  
✅ **Tiempo real** → Responde a eventos en milisegundos. Ideal para **IoT, procesamiento financiero, notificaciones, etc.**  
✅ **Resiliencia** → Si un servicio falla, los eventos quedan almacenados en el broker y pueden ser procesados después.

### **❌ Desafíos de EDA**
❌ **Debugging más difícil** → Como los eventos son asíncronos, seguir el flujo completo puede ser más complejo.  
❌ **Mayor complejidad inicial** → Requiere una infraestructura de mensajería (Kafka, RabbitMQ, etc.).  
❌ **Garantizar la consistencia** → Como los eventos no ocurren al mismo tiempo, puede haber problemas de **datos en diferentes estados**.

---

## **🔹 Cuándo usar EDA y cuándo no**

📌 **✅ Usar EDA cuando:**  
✔️ Se necesita **procesamiento en tiempo real** (notificaciones, streaming de datos).  
✔️ Se trabaja con **microservicios** y se quiere evitar dependencias directas entre ellos.  
✔️ Se manejan grandes volúmenes de datos y se necesita **escalabilidad horizontal**.

📌 **❌ No usar EDA cuando:**  
❌ Un sistema es **pequeño** y la comunicación síncrona es suficiente.  
❌ Se necesita **consistencia estricta** (Ejemplo: transferencias bancarias).  
❌ No se quiere gestionar la complejidad de un **broker de eventos**.

---

## **🔹 Comparación con Arquitecturas Tradicionales**

| **Característica**       | **Arquitectura Monolítica** | **Arquitectura Basada en Microservicios** | **Arquitectura EDA** |
|--------------------------|--------------------------|---------------------------------|-------------|
| **Acoplamiento**         | Alto                      | Medio                           | Bajo        |
| **Escalabilidad**        | Baja                      | Alta                            | Altísima    |
| **Resiliencia**          | Baja                      | Media                           | Alta        |
| **Dificultad de Debugging** | Baja                      | Media                           | Alta        |
| **Ejemplo de Uso**       | Aplicaciones pequeñas     | Aplicaciones empresariales      | Sistemas de streaming, IoT, FinTech |

---

## **🔹 EDA + CQRS + Event Sourcing: Un enfoque más avanzado**

Muchos sistemas combinan **EDA con CQRS y Event Sourcing** para mejorar el manejo de eventos.

📌 **CQRS (Command Query Responsibility Segregation):**
- Separa comandos (escribir datos) de consultas (leer datos).
- Aumenta el rendimiento y escalabilidad en sistemas de alta concurrencia.

📌 **Event Sourcing:**
- En lugar de guardar solo el estado actual de los datos, se almacenan **todos los eventos pasados**.
- Ideal para sistemas financieros, blockchain, auditoría y sistemas distribuidos.

📌 **Ejemplo en Axon Framework:**
```java
@Aggregate
public class Pedido {
    @AggregateIdentifier
    private String id;
    private String clienteId;
    private double total;

    @CommandHandler
    public Pedido(CrearPedidoCommand command) {
        apply(new PedidoCreadoEvent(command.getId(), command.getClienteId(), command.getTotal()));
    }
}
```
✅ **Cada cambio de estado se almacena como un evento y puede reconstruirse en cualquier momento**.

---

# **📌 Conclusión** 🎯
La **Arquitectura Orientada a Eventos (EDA)** es clave para **microservicios escalables y sistemas en tiempo real**.

✔️ **Usar EDA cuando se necesiten eventos asíncronos, alta escalabilidad y baja dependencia entre servicios.**  
✔️ **Elegir bien el broker de eventos** según las necesidades del proyecto (Kafka, RabbitMQ, SNS, etc.).  
✔️ **Combinar con CQRS y Event Sourcing** cuando se necesite historización de datos o escalabilidad extrema.
