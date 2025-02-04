# **📌 Día 19: Comunicación Asíncrona con Kafka y RabbitMQ**
Hoy aprenderás:  
✅ **Conceptos de mensajería asíncrona**  
✅ **Kafka y RabbitMQ: Comparación y uso**  
✅ **Publicar y consumir mensajes en Spring Boot**  
✅ **Ejercicio: Sistema de procesamiento de eventos en tiempo real**

---
📌 **¿Por qué es importante?**  
La **mensajería asíncrona** permite que los microservicios **se comuniquen sin estar directamente conectados**, mejorando la **escalabilidad, resiliencia y eficiencia**. **Kafka** y **RabbitMQ** son los brokers de mensajería más usados en arquitecturas distribuidas.

---

# **1️⃣ Kafka vs. RabbitMQ: ¿Cuál elegir?**

| **Característica** | **Kafka** | **RabbitMQ** |
|-------------------|----------|--------------|
| Patrón principal | **Event Streaming** | **Message Queueing** |
| Persistencia | Sí (mensajes almacenados en disco) | Opcional (en memoria o disco) |
| Orden de mensajes | Garantizado por **particiones** | Depende de la configuración |
| Uso principal | **Eventos en tiempo real, Big Data** | **Mensajería transaccional, RPC** |
| Escalabilidad | Alta (particiones y múltiples consumidores) | Media (colas y enrutamiento) |

📌 **Kafka** → Ideal para **procesamiento de eventos en tiempo real**.  
📌 **RabbitMQ** → Ideal para **comunicación entre microservicios**.

✅ **Hoy usaremos ambos para entender sus diferencias.**

---

# **2️⃣ Instalando Kafka y RabbitMQ con Docker**
📌 **Ejecutar Kafka y Zookeeper**
```sh
docker-compose -f kafka.yml up -d
```
📌 **Ejecutar RabbitMQ**
```sh
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```
📌 **Acceder a RabbitMQ Dashboard:**  
📍 **http://localhost:15672/** (user: `guest`, pass: `guest`)

✅ **Ahora podemos enviar y recibir mensajes en Kafka y RabbitMQ.**

---

# **3️⃣ Implementación en Spring Boot**
📌 **Estructura del Proyecto:**
```
📂 microservicios/
 ├── 📂 productor-service/    # Envía mensajes a Kafka/RabbitMQ
 ├── 📂 consumidor-service/   # Recibe y procesa mensajes
```

---

## **1️⃣ Productor de Mensajes con Kafka (`productor-service`)**
📌 **Dependencias en `pom.xml`**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

📌 **Configuramos `application.yml`**
```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
```

📌 **Productor `KafkaProducer.java`**
```java
package com.ejemplo.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarMensaje(String mensaje) {
        kafkaTemplate.send("eventos", mensaje);
        System.out.println("Mensaje enviado a Kafka: " + mensaje);
    }
}
```

📌 **Controlador `KafkaController.java`**
```java
package com.ejemplo.kafka.controller;

import com.ejemplo.kafka.producer.KafkaProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/enviar")
    public String enviarMensaje(@RequestParam String mensaje) {
        kafkaProducer.enviarMensaje(mensaje);
        return "Mensaje enviado: " + mensaje;
    }
}
```
✅ **Podemos enviar mensajes a Kafka con un simple `POST`.**

---

## **2️⃣ Consumidor de Mensajes con Kafka (`consumidor-service`)**
📌 **Dependencias en `pom.xml`**
```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

📌 **Configuramos `application.yml`**
```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: grupo-consumidores
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
```

📌 **Consumidor `KafkaConsumer.java`**
```java
package com.ejemplo.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "eventos", groupId = "grupo-consumidores")
    public void recibirMensaje(String mensaje) {
        System.out.println("Mensaje recibido desde Kafka: " + mensaje);
    }
}
```
✅ **Cada mensaje publicado en Kafka será procesado por `KafkaConsumer`.**

---

## **3️⃣ Productor de Mensajes con RabbitMQ (`productor-service`)**
📌 **Dependencias en `pom.xml`**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

📌 **Configuramos `application.yml`**
```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
```

📌 **Productor `RabbitProducer.java`**
```java
package com.ejemplo.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {
    private final RabbitTemplate rabbitTemplate;

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensaje(String mensaje) {
        rabbitTemplate.convertAndSend("cola_eventos", mensaje);
        System.out.println("Mensaje enviado a RabbitMQ: " + mensaje);
    }
}
```
✅ **Cada mensaje se envía a la cola `cola_eventos`.**

---

## **4️⃣ Consumidor de Mensajes con RabbitMQ (`consumidor-service`)**
📌 **Configuramos `application.yml`**
```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
```

📌 **Consumidor `RabbitConsumer.java`**
```java
package com.ejemplo.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitConsumer {

    @RabbitListener(queues = "cola_eventos")
    public void recibirMensaje(String mensaje) {
        System.out.println("Mensaje recibido desde RabbitMQ: " + mensaje);
    }
}
```
✅ **Cada mensaje publicado en RabbitMQ será procesado por `RabbitConsumer`.**

---

# **5️⃣ Probando la API con `curl` o Postman**
📌 **Enviar mensaje a Kafka:**
```sh
curl -X POST "http://localhost:8080/kafka/enviar?mensaje=HolaKafka"
```
📌 **Enviar mensaje a RabbitMQ:**
```sh
curl -X POST "http://localhost:8080/rabbitmq/enviar?mensaje=HolaRabbit"
```
📌 **Ver mensajes en consola:**
```
Mensaje recibido desde Kafka: HolaKafka
Mensaje recibido desde RabbitMQ: HolaRabbit
```
✅ **Los mensajes son procesados en tiempo real sin conexión directa entre servicios.**
