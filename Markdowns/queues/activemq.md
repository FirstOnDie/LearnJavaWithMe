# **📌 ActiveMQ en Java**

## **1️⃣ ¿Qué es ActiveMQ?** 🏛️

**ActiveMQ** es un **broker de mensajería basado en JMS (Java Message Service)** que permite la comunicación **asíncrona y confiable** entre aplicaciones distribuidas.

📌 **¿Cómo funciona?**
- **Productores (Producers) 📨** → Envían mensajes a ActiveMQ.
- **ActiveMQ (Broker) 📦** → Administra y almacena los mensajes.
- **Consumidores (Consumers) 📥** → Reciben y procesan los mensajes.

✅ **ActiveMQ es ideal para arquitecturas de microservicios, integración de aplicaciones y procesamiento de tareas en segundo plano.**

---

## **2️⃣ Ventajas y Desventajas de ActiveMQ** ⚖️

| ✅ Ventajas | ❌ Desventajas |
|------------|--------------|
| 🔄 **Garantiza la entrega de mensajes** con ACK y reintentos. | ⚠️ **Mayor latencia comparado con Kafka** en procesamiento masivo. |
| 🔀 **Soporta múltiples protocolos (JMS, MQTT, AMQP, STOMP, etc.).** | ⚠️ **Menor rendimiento que RabbitMQ en algunas arquitecturas.** |
| ♻️ **Mensajería transaccional y persistente**. | ⚠️ **Mayor sobrecarga en memoria si no se configuran bien las colas.** |
| 🔌 **Integración nativa con Java y Spring Boot**. | ⚠️ **Requiere configuración avanzada en entornos grandes.** |

📌 **Conclusión:** ActiveMQ es excelente cuando trabajamos con sistemas que ya usan **JMS** y queremos una solución confiable para colas de mensajes.

---

## **3️⃣ Modelos de Mensajería en ActiveMQ** 🔄

ActiveMQ soporta **dos modelos principales de mensajería**:

### **1️⃣ Point-to-Point (Colas - Queue) 📬**
- Un **mensaje solo puede ser recibido por un consumidor**.
- Útil para tareas en segundo plano o balanceo de carga.
- Ejemplo: Un sistema de pedidos donde cada pedido debe ser procesado **una sola vez**.

### **2️⃣ Publish/Subscribe (Topics - Publicación/Suscripción) 📡**
- **Múltiples consumidores reciben el mismo mensaje**.
- Útil para **eventos en tiempo real** o **notificaciones**.
- Ejemplo: Un servicio de notificaciones donde varios sistemas reciben alertas simultáneamente.

---

## **4️⃣ Configuración de ActiveMQ en Java** 🔧

📌 **Instalar ActiveMQ:**  
1️⃣ Descarga ActiveMQ desde: [activemq.apache.org](https://activemq.apache.org/)  
2️⃣ Extrae el archivo y ejecuta el servidor:
```bash
./bin/activemq start
```
3️⃣ Accede a la consola de administración en:  
📌 `http://localhost:8161/admin` (usuario: `admin`, contraseña: `admin`)

---

## **5️⃣ Productor y Consumidor en Java con JMS**

### **📌 1️⃣ Productor en Java (Envía Mensajes) 🚀**
```java
import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ProductorActiveMQ {
    public static void main(String[] args) throws JMSException {
        // 1️⃣ Conectar a ActiveMQ
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection();
        connection.start();

        // 2️⃣ Crear una sesión
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 3️⃣ Crear una cola
        Destination queue = session.createQueue("COLA_PEDIDOS");

        // 4️⃣ Crear el productor
        MessageProducer producer = session.createProducer(queue);

        // 5️⃣ Enviar un mensaje
        TextMessage mensaje = session.createTextMessage("Nuevo pedido recibido.");
        producer.send(mensaje);
        System.out.println("✅ Mensaje enviado: " + mensaje.getText());

        // 6️⃣ Cerrar conexión
        session.close();
        connection.close();
    }
}
```

✅ **Explicación:**
- Se conecta a **ActiveMQ** (`tcp://localhost:61616`).
- Crea una **cola (`COLA_PEDIDOS`)**.
- Envía un mensaje **de texto**.
- Cierra la conexión.

---

### **📌 2️⃣ Consumidor en Java (Recibe Mensajes) 📥**
```java
import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumidorActiveMQ {
    public static void main(String[] args) throws JMSException {
        // 1️⃣ Conectar a ActiveMQ
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection();
        connection.start();

        // 2️⃣ Crear una sesión
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 3️⃣ Crear la cola
        Destination queue = session.createQueue("COLA_PEDIDOS");

        // 4️⃣ Crear el consumidor
        MessageConsumer consumer = session.createConsumer(queue);

        // 5️⃣ Recibir mensaje
        System.out.println("📩 Esperando mensajes...");
        TextMessage mensaje = (TextMessage) consumer.receive();
        System.out.println("✅ Mensaje recibido: " + mensaje.getText());

        // 6️⃣ Cerrar conexión
        session.close();
        connection.close();
    }
}
```

✅ **Explicación:**
- Se suscribe a la **cola (`COLA_PEDIDOS`)**.
- **Espera hasta recibir un mensaje** y lo imprime en la consola.

---

## **6️⃣ Integración con Spring Boot 🏗️**

📌 **Añadir dependencias en `pom.xml`:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
```

---

### **📌 Productor en Spring Boot**
```java
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductorActiveMQ {
    private final JmsTemplate jmsTemplate;

    public ProductorActiveMQ(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void enviarMensaje(String mensaje) {
        jmsTemplate.convertAndSend("COLA_PEDIDOS", mensaje);
        System.out.println("✅ Mensaje enviado: " + mensaje);
    }
}
```

---

### **📌 Consumidor en Spring Boot**
```java
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumidorActiveMQ {

    @JmsListener(destination = "COLA_PEDIDOS")
    public void recibirMensaje(String mensaje) {
        System.out.println("✅ Mensaje recibido: " + mensaje);
    }
}
```

✅ **Con estas clases, ya tenemos un sistema de mensajería con ActiveMQ en Spring Boot.**

---

## **7️⃣ Configuración en `application.properties`**
```properties
spring.activemq.broker-url=tcp://localhost:61616
spring.activemq.user=admin
spring.activemq.password=admin
```

---

## **8️⃣ ¿Cuándo usar ActiveMQ?** 📊

📌 **Casos donde ActiveMQ es una buena opción:**  
✅ Necesitas **integración con sistemas basados en JMS**.  
✅ Requieres **entrega garantizada de mensajes**.  
✅ Necesitas **mensajería transaccional** con rollback.  
✅ Requieres soporte para **diferentes protocolos (AMQP, MQTT, STOMP, WebSockets, etc.)**.

---

## **9️⃣ Comparación: ActiveMQ vs RabbitMQ vs Kafka**

| Característica | **ActiveMQ** | **RabbitMQ** | **Kafka** |
|--------------|-------------|------------|---------|
| **Modelo** | Basado en JMS | Basado en AMQP | Basado en Log |
| **Entrega garantizada** | ✅ Sí | ✅ Sí | ❌ No |
| **Streaming de datos** | ❌ No | ❌ No | ✅ Sí |
| **Latencia baja** | ⚠️ Media | ✅ Alta | ✅ Alta |
| **Escalabilidad** | ✅ Sí | ✅ Sí | ✅ Sí (Mejor para Big Data) |

📌 **Conclusión:**  
🔹 **ActiveMQ** es ideal para sistemas basados en **JMS** y mensajería transaccional.  
🔹 **RabbitMQ** es mejor para **eventos y microservicios**.  
🔹 **Kafka** es el mejor para **procesamiento masivo de datos y streaming**.

