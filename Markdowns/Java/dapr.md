# **📌 ¿Qué es Dapr?**
**Dapr (Distributed Application Runtime)** es un **runtime** para **microservicios** que simplifica la comunicación entre ellos, el almacenamiento de estados y la integración con otras herramientas (como Kafka, Redis o Kubernetes).

### 🔹 **¿Por qué usar Dapr en Java?**
✔ **Facilita la comunicación entre microservicios** sin acoplamiento directo.  
✔ **Ofrece mensajería asíncrona** sin necesidad de RabbitMQ o Kafka.  
✔ **Soporta almacenamiento de estados** con Redis, DynamoDB o PostgreSQL.  
✔ **Agrega seguridad, tracing y logs automáticos**.  
✔ **Funciona con cualquier framework Java**: **Spring Boot, Quarkus, Micronaut, Jakarta EE, etc.**

📌 **Dapr NO reemplaza a Spring Boot ni Kubernetes.** Es una **capa adicional** que facilita la gestión de microservicios.

---

# **📌 ¿Cómo funciona Dapr?**
📌 Dapr usa un **sidecar pattern**, lo que significa que cada microservicio **tiene un contenedor adicional (sidecar)** que gestiona la comunicación, estados y eventos.

### **🔹 Arquitectura de Dapr**
1️⃣ **Tu aplicación**: Microservicios escritos en **Java, Python, Node.js, etc.**  
2️⃣ **Dapr Sidecar**: Se comunica con tu microservicio a través de **HTTP o gRPC**.  
3️⃣ **Componentes de Dapr**: Maneja **mensajería, almacenamiento, eventos, autenticación, logs** y más.  
4️⃣ **Backend de Dapr**: Puede conectarse con **Kafka, Redis, PostgreSQL, Kubernetes, etc.**

📌 **Sin Dapr:** Microservicios se comunican directamente entre sí, creando dependencias fuertes.  
📌 **Con Dapr:** Se usa una capa intermedia, eliminando el acoplamiento directo entre servicios.

---

# **📌 Instalación y Configuración de Dapr**
### 🔹 **1️⃣ Instalar Dapr**
```bash
wget -q https://raw.githubusercontent.com/dapr/cli/master/install/install.sh -O - | /bin/bash
```
🔹 **Verifica que está instalado:**
```bash
dapr --version
```
**Salida esperada:**
```
CLI version: 1.x.x
Runtime version: 1.x.x
```

### 🔹 **2️⃣ Iniciar Dapr**
```bash
dapr init
```
Esto descargará y configurará Dapr en tu máquina.

---

# **📌 Dapr en Java con Spring Boot**
### 🔹 **Ejemplo: Comunicación entre Microservicios**
Supongamos que tenemos dos microservicios en **Spring Boot**:
1️⃣ **Servicio A**: Cliente que llama a otro microservicio.  
2️⃣ **Servicio B**: Servicio que recibe la solicitud.

📌 **Ejemplo: Servicio A llama a Servicio B sin conocer su dirección real.**

### **🔹 Código en Servicio A**
```java
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;

public class ClienteDapr {
    public static void main(String[] args) {
        DaprClient client = new DaprClientBuilder().build();
        String resultado = client.invokeMethod(
            "servicioB",     // Nombre del servicio destino
            "ruta",          // Ruta en el servicio destino
            "payload",       // Datos a enviar
            String.class     // Tipo de respuesta esperada
        ).block();

        System.out.println("Respuesta del servicio B: " + resultado);
    }
}
```
🔹 **¿Qué hace este código?**
✔ Usa el **SDK de Dapr para Java**.  
✔ Llama al **servicioB** sin conocer su IP o dirección real.  
✔ Usa el **sidecar de Dapr** para manejar la comunicación.  
✔ **Respuesta asíncrona** sin bloquear la aplicación.

---

### **🔹 Código en Servicio B (Spring Boot)**
📌 **Servicio B recibe la solicitud de manera transparente.**
```java
import org.springframework.web.bind.annotation.*;

@RestController
public class ServicioBController {
    
    @PostMapping("/ruta")
    public String procesarSolicitud(@RequestBody String payload) {
        return "Recibido: " + payload;
    }
}
```
🔹 **¿Qué hace este código?**
✔ **Servicio B** maneja solicitudes en `/ruta`.  
✔ **No necesita saber quién lo llama**, solo responde.  
✔ Funciona **con cualquier otro microservicio en Dapr**.

---

### **📌 Ejecutar los microservicios con Dapr**
🔹 **Ejecutar el Servicio B con Dapr**
```bash
dapr run --app-id servicioB --app-port 8081 -- java -jar servicioB.jar
```

🔹 **Ejecutar el Servicio A con Dapr**
```bash
dapr run --app-id servicioA -- java -jar servicioA.jar
```

✅ **Dapr se encarga de la comunicación entre ambos servicios sin necesidad de conocer direcciones IP.**

---

# **📌 Otras Funcionalidades Clave de Dapr**
📌 Dapr no solo facilita la comunicación entre microservicios. También proporciona:

### 🔹 **1️⃣ Mensajería Asíncrona**
✅ **Publicar un mensaje en un tópico (Pub/Sub)**
```java
client.publishEvent("kafka-pubsub", "nuevosPedidos", "Pedido #123").block();
```

✅ **Escuchar eventos**
```java
@Topic(name = "nuevosPedidos", pubsubName = "kafka-pubsub")
public void recibirEvento(String pedido) {
    System.out.println("Nuevo pedido recibido: " + pedido);
}
```
📌 **Ejemplo real:** Publicar eventos en **Kafka, Redis o RabbitMQ** sin dependencias directas.

---

### 🔹 **2️⃣ Almacenamiento de Estado**
✅ **Guardar datos en Redis/PostgreSQL sin escribir SQL**
```java
client.saveState("redis-store", "clave1", "valor guardado").block();
```

✅ **Leer datos**
```java
String valor = client.getState("redis-store", "clave1", String.class).block();
```

📌 **Ejemplo real:** Guardar sesiones de usuario o información temporal.

---

### 🔹 **3️⃣ Llamadas a APIs Externas**
✅ **Dapr puede llamar a servicios REST sin necesidad de conocer la URL exacta.**
```java
client.invokeMethod("servicioExterno", "api/data", "{}", String.class).block();
```

📌 **Ejemplo real:** Llamar a **APIs de terceros (Stripe, Twilio, etc.)** sin acoplamiento.

---

### 🔹 **4️⃣ Tracing y Logging Automático**
📌 Dapr se integra con **Jaeger, Zipkin y OpenTelemetry** para capturar logs y trazas sin modificar el código de la aplicación.

```yaml
apiVersion: dapr.io/v1alpha1
kind: Configuration
metadata:
  name: tracing-config
spec:
  tracing:
    samplingRate: "1"
    zipkin:
      endpointAddress: "http://zipkin:9411/api/v2/spans"
```

✅ **Beneficio:** Ver en tiempo real qué microservicios están tardando más en responder.

---

# **📌 Ventajas y Desventajas de Dapr**
### ✅ **Ventajas**
✔ Desacopla microservicios, reduciendo dependencias.  
✔ Simplifica **mensajería, almacenamiento y eventos**.  
✔ Compatible con **Spring Boot, Micronaut, Quarkus, Kubernetes**.  
✔ Agrega **tracing y logs automáticos**.  
✔ Soporta múltiples protocolos y backend (Kafka, Redis, AWS, etc.).

### ❌ **Desventajas**
❌ Puede agregar **complejidad** si no necesitas microservicios avanzados.  
❌ La capa extra (sidecar) puede consumir algo más de memoria.  
❌ No es necesario si tu aplicación es **monolítica**.

---

# **📌 Conclusión**
🔹 **Dapr** es una **solución moderna para microservicios** que simplifica **comunicación, mensajería, almacenamiento y observabilidad**.  
🔹 **Spring Boot + Dapr** permite crear **microservicios desacoplados** sin preocuparte por infraestructura.  
🔹 **Ideal para Kubernetes, serverless y arquitecturas distribuidas**.

---