# **📌 Preguntas y Respuestas para Entrevista Técnica – Comunicación y Colas en Java**

### ❓ **Pregunta:** ¿Qué es Apache Kafka y para qué se usa?
✅ **Respuesta:**  
📌 **Apache Kafka** es una **plataforma de mensajería distribuida** diseñada para manejar **grandes volúmenes de datos en tiempo real**.

📌 **Casos de uso:**  
✔ **Procesamiento de eventos en tiempo real** (Ej: monitoreo de logs).  
✔ **Integración entre microservicios**.  
✔ **Streaming de datos en Big Data**.

📌 **Arquitectura de Kafka:**
```
Productor → Topic (Particiones) → Consumidor
```
- **Productores**: Envían mensajes a Kafka.
- **Topics**: Contienen mensajes organizados en **particiones**.
- **Consumidores**: Reciben mensajes de Kafka.

---

### ❓ **Pregunta:** ¿Cómo enviar y recibir mensajes en Kafka con Spring Boot?
✅ **Respuesta:**  
📌 **Ejemplo de Productor Kafka en Java:**
```java
@Component
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarMensaje(String mensaje) {
        kafkaTemplate.send("mi-topic", mensaje);
    }
}
```

📌 **Ejemplo de Consumidor Kafka en Java:**
```java
@Component
public class KafkaConsumer {
    @KafkaListener(topics = "mi-topic", groupId = "grupo1")
    public void recibirMensaje(String mensaje) {
        System.out.println("Mensaje recibido: " + mensaje);
    }
}
```
✅ **Kafka permite manejar múltiples consumidores en paralelo, optimizando el procesamiento.**

---

### ❓ **Pregunta:** ¿Qué es RabbitMQ y en qué se diferencia de Kafka?
✅ **Respuesta:**  
📌 **RabbitMQ** es un **Message Broker basado en colas** que permite **enviar mensajes de manera confiable entre sistemas**.

📌 **Diferencias con Kafka:**  
| Característica  | RabbitMQ | Kafka |
|---------------|-----------|--------|
| **Modelo** | Basado en colas | Basado en logs |
| **Persistencia** | Opcional | Siempre persistente |
| **Procesamiento** | Work Queues | Stream de datos |

📌 **Casos de uso:**  
✔ **Procesamiento de tareas en segundo plano**.  
✔ **Sistemas de notificación y colas de trabajos**.  
✔ **Distribución de eventos en microservicios**.

📌 **Arquitectura de RabbitMQ:**
```
Productor → Exchange → Queue → Consumidor
```
- **Exchange**: Decide cómo distribuir los mensajes a las colas.
- **Queue**: Donde se almacenan los mensajes.
- **Consumidor**: Procesa los mensajes en la cola.

---

### ❓ **Pregunta:** ¿Cómo enviar y recibir mensajes con RabbitMQ en Spring Boot?
✅ **Respuesta:**  
📌 **Ejemplo de Productor RabbitMQ en Java:**
```java
@Component
public class RabbitProducer {
    private final RabbitTemplate rabbitTemplate;

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensaje(String mensaje) {
        rabbitTemplate.convertAndSend("mi-exchange", "mi-routing-key", mensaje);
    }
}
```

📌 **Ejemplo de Consumidor RabbitMQ en Java:**
```java
@Component
@RabbitListener(queues = "mi-queue")
public class RabbitConsumer {
    @RabbitHandler
    public void recibirMensaje(String mensaje) {
        System.out.println("Mensaje recibido: " + mensaje);
    }
}
```
✅ **RabbitMQ garantiza la entrega de mensajes con confirmaciones automáticas y reintentos en caso de fallo.**

---

### ❓ **Pregunta:** ¿Qué es ActiveMQ y en qué se diferencia de RabbitMQ?
✅ **Respuesta:**  
📌 **ActiveMQ** es un **Message Broker compatible con JMS (Java Message Service)** que permite enviar mensajes entre aplicaciones Java de manera confiable.

📌 **Diferencias con RabbitMQ:**  
| Característica  | ActiveMQ | RabbitMQ |
|---------------|-----------|-----------|
| **Protocolos** | Soporta JMS, AMQP, MQTT | AMQP |
| **Modelo** | Publicación/Suscripción y Punto a Punto | Colas y Enrutamiento |
| **Escalabilidad** | Menos escalable que Kafka | Menos eficiente en alta carga |

📌 **Casos de uso:**  
✔ **Mensajería entre aplicaciones Java/JMS**.  
✔ **Procesamiento de tareas en sistemas legacy**.  
✔ **Sistemas de mensajería empresarial**.

📌 **Arquitectura de ActiveMQ:**
```
Productor → Topic/Queue → Consumidor
```

---

### ❓ **Pregunta:** ¿Cómo enviar y recibir mensajes con ActiveMQ en Java?
✅ **Respuesta:**  
📌 **Ejemplo de Productor ActiveMQ en Java:**
```java
@Component
public class ActiveMQProducer {
    private final JmsTemplate jmsTemplate;

    public ActiveMQProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void enviarMensaje(String mensaje) {
        jmsTemplate.convertAndSend("mi-cola", mensaje);
    }
}
```

📌 **Ejemplo de Consumidor ActiveMQ en Java:**
```java
@Component
@JmsListener(destination = "mi-cola")
public class ActiveMQConsumer {
    public void recibirMensaje(String mensaje) {
        System.out.println("Mensaje recibido: " + mensaje);
    }
}
```
✅ **ActiveMQ es ideal para aplicaciones Java que ya usan JMS y necesitan alta compatibilidad.**

---

# 🎯 **Conclusión**

| Tecnologías  | Mejor para... |
|-------------|--------------|
| **Kafka** | Procesamiento de eventos en tiempo real, Big Data |
| **RabbitMQ** | Comunicación entre microservicios, colas de tareas |
| **ActiveMQ** | Integración con sistemas legacy, mensajería empresarial |

📌 **📢 ¿Qué debes recordar para una entrevista?**  
✔ Kafka se usa para **event-driven architectures** y streaming de datos.  
✔ RabbitMQ es un **message broker** clásico, ideal para **colas de trabajos**.  
✔ ActiveMQ es **compatible con JMS**, usado en aplicaciones Java empresariales.
