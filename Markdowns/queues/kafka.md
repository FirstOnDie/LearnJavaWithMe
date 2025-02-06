# **📌 Apache Kafka en Java**

## **1️⃣ ¿Qué es Apache Kafka?** 🏛️

**Apache Kafka** es una plataforma de mensajería **distribuida, escalable y en tiempo real** que permite la transmisión de datos entre aplicaciones de manera eficiente.

🔹 **Piensa en Kafka como una central de correos** 📬:
- **Los productores (Producers)** son los que envían los mensajes 📩.
- **Kafka (Broker)** es la oficina central que organiza los mensajes 📦.
- **Los consumidores (Consumers)** son los que reciben los mensajes y los procesan 📥.

✅ **Kafka es ideal para sistemas de microservicios, eventos en tiempo real y procesamiento de grandes volúmenes de datos.**

---

## **2️⃣ Ventajas y Desventajas de Kafka** ⚖️

| ✅ Ventajas | ❌ Desventajas |
|------------|--------------|
| 🔥 **Alto rendimiento** (millones de mensajes por segundo). | ⚠️ **Curva de aprendizaje** (requiere configuración y pruebas). |
| ♻️ **Escalabilidad** (distribuido en varios nodos). | ⚠️ **Consistencia eventual**, lo que puede generar retrasos en datos. |
| 🔄 **Persistencia de mensajes** (guarda datos por tiempo configurable). | ⚠️ **No es una base de datos** (no permite consultas complejas). |
| 🔧 **Integración con Big Data** (Spark, Flink, Elasticsearch). | ⚠️ **Latencia en redes lentas** (requiere optimización). |

📌 **Conclusión:** Kafka es excelente para flujos de datos en tiempo real, pero no es la mejor opción para almacenar datos a largo plazo.

---

## **3️⃣ Componentes de Kafka** 🔧

### **1️⃣ Tópicos (Topics) 📌**
💡 **Los tópicos son como bandejas organizadas donde Kafka almacena los mensajes.**

Ejemplo de tópicos:
- `pagos_realizados` → Mensajes sobre pagos 💰.
- `ordenes_creadas` → Mensajes sobre pedidos 📦.
- `usuarios_registrados` → Mensajes sobre nuevos usuarios 👤.

```shell
# Crear un tópico en Kafka
kafka-topics.sh --create --topic pagos_realizados --bootstrap-server localhost:9092
```

---

### **2️⃣ Productores (Producers) 🚀**
💡 **Los productores envían mensajes a los tópicos.**

Ejemplo: Un sistema de pagos envía eventos cada vez que se hace una transacción.

```java
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class ProductorKafka {
    public static void main(String[] args) {
        // Configuración del productor
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Crear el productor
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // Enviar un mensaje
        ProducerRecord<String, String> record = new ProducerRecord<>("pagos_realizados", "Pago de 100 USD");
        producer.send(record);

        producer.close();
        System.out.println("Mensaje enviado a Kafka!");
    }
}
```
✅ **Explicación:**
- `bootstrap.servers` → Dirección del servidor Kafka.
- `key.serializer`, `value.serializer` → Convierte los mensajes en texto.
- `send(record)` → Envía un mensaje al **tópico `pagos_realizados`**.

---

### **3️⃣ Consumidores (Consumers) 📥**
💡 **Los consumidores leen mensajes desde los tópicos y los procesan.**

Ejemplo: Un servicio de contabilidad recibe los pagos realizados.

```java
import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumidorKafka {
    public static void main(String[] args) {
        // Configuración del consumidor
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "grupo-contabilidad");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // Crear el consumidor
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("pagos_realizados"));

        // Leer mensajes continuamente
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Mensaje recibido: " + record.value());
            }
        }
    }
}
```
✅ **Explicación:**
- `subscribe(Collections.singletonList("pagos_realizados"))` → Se suscribe al **tópico `pagos_realizados`**.
- `poll(Duration.ofMillis(100))` → Consulta mensajes cada 100 ms.

---

## **4️⃣ Particiones y Grupos de Consumidores 🔀**

📌 **Kafka permite dividir un tópico en varias partes llamadas *particiones*.**  
✅ Esto permite que **múltiples consumidores lean en paralelo**, mejorando el rendimiento.

### **Ejemplo de procesamiento en paralelo:**
- `pagos_realizados` tiene **3 particiones** (`P0`, `P1`, `P2`).
- Hay **3 consumidores** (`C1`, `C2`, `C3`), cada uno lee de una partición.

```plaintext
Tópico: pagos_realizados
┌────────┬────────┬────────┐
│ P0     │ P1     │ P2     │  ← Particiones
└────────┴────────┴────────┘
  ↑        ↑        ↑
  C1       C2       C3      ← Consumidores en paralelo
```
📌 **Esto permite escalar fácilmente el procesamiento de datos en Kafka.**

---

## **5️⃣ Integración con Spring Boot 🏗️**

Kafka se puede integrar fácilmente con **Spring Boot** usando `spring-kafka`.

### 📌 **Productor en Spring Boot**
```java
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductorKafka {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProductorKafka(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarMensaje(String mensaje) {
        kafkaTemplate.send("pagos_realizados", mensaje);
    }
}
```

---

### 📌 **Consumidor en Spring Boot**
```java
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumidorKafka {

    @KafkaListener(topics = "pagos_realizados", groupId = "grupo-contabilidad")
    public void recibirMensaje(String mensaje) {
        System.out.println("Mensaje recibido: " + mensaje);
    }
}
```
✅ **Con solo estas clases, ya tenemos un sistema Kafka en Spring Boot.**

---

## **6️⃣ Resumen y Conclusión** 🎯

📌 **Kafka es ideal para sistemas que manejan grandes volúmenes de datos en tiempo real.**  
📌 **Se integra bien con Java y Spring Boot, facilitando su uso en microservicios.**  
📌 **Es escalable y permite el procesamiento paralelo de mensajes.**

✅ **¿Cuándo usar Kafka?**
- Cuando necesitas **procesamiento de eventos en tiempo real**.
- Para **integrar microservicios de manera eficiente**.
- Cuando trabajas con **Big Data y Machine Learning**.

---