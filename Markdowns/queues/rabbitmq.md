# **📌 RabbitMQ en Java**

## **1️⃣ ¿Qué es RabbitMQ?** 🏛️

**RabbitMQ** es un **sistema de mensajería basado en colas** que permite la comunicación entre aplicaciones de forma **segura, escalable y eficiente**.

🔹 **Piensa en RabbitMQ como un buzón de mensajes** 📬:
- **Los productores (Producers)** 📨 → Envían mensajes al buzón.
- **RabbitMQ (Broker)** 📦 → Almacena y organiza los mensajes.
- **Los consumidores (Consumers)** 📥 → Reciben los mensajes y los procesan.

✅ **RabbitMQ es ideal para sistemas de microservicios, integración de aplicaciones y procesamiento de tareas en segundo plano.**

---

## **2️⃣ Ventajas y Desventajas de RabbitMQ** ⚖️

| ✅ Ventajas | ❌ Desventajas |
|------------|--------------|
| 🔄 **Garantiza la entrega de mensajes** (ACK y reintentos). | ⚠️ **Requiere configuración avanzada** para alto rendimiento. |
| 🔀 **Flexibilidad con diferentes patrones de enrutamiento**. | ⚠️ **Mayor latencia comparado con Kafka** en algunos casos. |
| ♻️ **Mensajería transaccional y persistente**. | ⚠️ **No es óptimo para streaming masivo de datos**. |
| 🔌 **Fácil integración con Java y Spring Boot**. | ⚠️ **Mayor sobrecarga si hay demasiados mensajes pendientes**. |

📌 **Conclusión:** RabbitMQ es excelente para garantizar que los mensajes se entreguen correctamente y para procesar tareas en segundo plano.

---

## **3️⃣ Componentes de RabbitMQ** 🔧

### **1️⃣ Exchanges (Intercambiadores) 🚦**
💡 **Son como enrutadores que dirigen los mensajes a las colas correctas.**

📌 **Tipos de Exchanges en RabbitMQ:**  
1️⃣ **Direct Exchange** → Mensajes van a una cola específica según una clave.  
2️⃣ **Fanout Exchange** → Un mensaje se envía a **todas** las colas vinculadas.  
3️⃣ **Topic Exchange** → Los mensajes se enrutan según patrones (`logs.#`, `orden.*`).  
4️⃣ **Headers Exchange** → Usa encabezados en lugar de claves de enrutamiento.

Ejemplo de creación de un **Direct Exchange** en Java:
```java
channel.exchangeDeclare("mi_direct_exchange", "direct");
```

---

### **2️⃣ Queues (Colas) 📬**
💡 **Son como bandejas donde se almacenan los mensajes hasta que un consumidor los recibe.**

📌 **Ejemplo de Creación de una Cola en Java:**
```java
channel.queueDeclare("cola_pedidos", true, false, false, null);
```
✅ Parámetros:
- `true` → Cola persistente (se mantiene después de reiniciar el servidor).
- `false` → No es exclusiva (puede ser usada por varios consumidores).

---

### **3️⃣ Productores (Producers) 🚀**
💡 **Envían mensajes a un Exchange, que los dirige a una Cola.**

📌 **Ejemplo de un Productor en Java:**
```java
import com.rabbitmq.client.*;

public class ProductorRabbitMQ {
    private final static String EXCHANGE_NAME = "mi_direct_exchange";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // Dirección del servidor RabbitMQ
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            String mensaje = "Pedido realizado con éxito";
            channel.basicPublish(EXCHANGE_NAME, "pedidos", null, mensaje.getBytes());
            System.out.println(" [x] Mensaje enviado: '" + mensaje + "'");
        }
    }
}
```
✅ **Explicación:**
- Crea una conexión con RabbitMQ (`ConnectionFactory`).
- Declara un **Exchange `direct`**.
- Publica un mensaje con la clave de enrutamiento `"pedidos"`.

---

### **4️⃣ Consumidores (Consumers) 📥**
💡 **Reciben mensajes desde una Cola y los procesan.**

📌 **Ejemplo de un Consumidor en Java:**
```java
import com.rabbitmq.client.*;

public class ConsumidorRabbitMQ {
    private final static String QUEUE_NAME = "cola_pedidos";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        System.out.println(" [*] Esperando mensajes...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String mensaje = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Mensaje recibido: '" + mensaje + "'");
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
    }
}
```
✅ **Explicación:**
- Se conecta al servidor RabbitMQ.
- Se suscribe a la **cola `cola_pedidos`**.
- **Cuando recibe un mensaje**, lo imprime en la consola.

---

## **4️⃣ Patrones de Enrutamiento en RabbitMQ** 🔀

📌 **RabbitMQ permite múltiples formas de distribuir mensajes**.

### **📌 1️⃣ Direct Exchange (Enrutamiento Directo) 📬**
- Los mensajes van a **una cola específica** según una clave.
```java
channel.basicPublish("mi_direct_exchange", "clave_pagos", null, mensaje.getBytes());
```

### **📌 2️⃣ Fanout Exchange (Broadcast) 📡**
- **El mensaje se envía a TODAS las colas vinculadas.**
```java
channel.exchangeDeclare("mi_fanout_exchange", "fanout");
```

### **📌 3️⃣ Topic Exchange (Enrutamiento por Patrones) 🏷️**
- Se pueden usar patrones como `"logs.#"` o `"orden.*"`.
```java
channel.exchangeDeclare("mi_topic_exchange", "topic");
channel.queueBind("cola_logs", "mi_topic_exchange", "logs.#");
```

---

## **5️⃣ Integración con Spring Boot 🏗️**

📌 **RabbitMQ se puede integrar fácilmente con Spring Boot usando `spring-boot-starter-amqp`.**

### **📌 Productor en Spring Boot**
```java
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductorRabbitMQ {
    private final RabbitTemplate rabbitTemplate;

    public ProductorRabbitMQ(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensaje(String mensaje) {
        rabbitTemplate.convertAndSend("mi_direct_exchange", "pedidos", mensaje);
    }
}
```

---

### **📌 Consumidor en Spring Boot**
```java
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumidorRabbitMQ {

    @RabbitListener(queues = "cola_pedidos")
    public void recibirMensaje(String mensaje) {
        System.out.println("Mensaje recibido: " + mensaje);
    }
}
```
✅ **Con estas clases, ya tenemos un sistema RabbitMQ en Spring Boot.**

---

## **6️⃣ Resumen y Conclusión** 🎯

📌 **RabbitMQ es ideal para sistemas donde es crítico que los mensajes se entreguen y procesen correctamente.**  
📌 **Se integra bien con Java y Spring Boot, facilitando su uso en microservicios.**  
📌 **Soporta diferentes patrones de enrutamiento, ofreciendo flexibilidad.**

✅ **¿Cuándo usar RabbitMQ?**
- Cuando necesitas **procesamiento confiable de mensajes**.
- Para **gestionar colas de tareas en segundo plano**.
- Cuando necesitas **garantizar la entrega de mensajes (ACK, reintentos)**.

---