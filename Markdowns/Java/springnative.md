# **📌 Spring Native** 🚀☁️

Hoy en día, los servicios en la nube como **AWS Lambda, Google Cloud Functions y Azure Functions** permiten ejecutar código sin necesidad de gestionar servidores. Esto se conoce como **Serverless Computing**, y **Spring Boot** tiene soporte nativo para ello con **Spring Native y Spring Cloud Functions**.

---

## **🔹 ¿Qué es Spring Native?**
Spring Native es una tecnología que permite compilar aplicaciones Spring Boot en **binarios nativos** usando **GraalVM**, reduciendo el **tiempo de arranque y el consumo de memoria**.

📌 **Beneficios de Spring Native:**  
✔ **Menor consumo de memoria** → Ideal para entornos serverless y contenedores.  
✔ **Arranque ultrarrápido** → Hasta 100 veces más rápido que una JVM tradicional.  
✔ **Ideal para funciones serverless** en **AWS Lambda, Google Cloud Functions y Azure Functions**.

---

## **📌 ¿Qué es Spring Cloud Functions?**
**Spring Cloud Functions** permite escribir funciones reutilizables que pueden ejecutarse en **serverless**, **Kafka**, **RabbitMQ** o **REST APIs** sin modificar el código.

📌 **Ventajas de Spring Cloud Functions:**  
✔ **Código reutilizable** → Puedes ejecutar la misma función en diferentes entornos (local, nube, eventos).  
✔ **Menos dependencia del proveedor** → Compatible con AWS, Azure y Google Cloud.  
✔ **Despliegue más sencillo** → Menos boilerplate y configuración.

---

# **📌 Creando una función Serverless con Spring Cloud Functions**
Spring Cloud Functions permite definir funciones de manera sencilla en Spring Boot.

## **📌 1️⃣ Definir una función en Spring Boot**
📌 **Ejemplo: Crear una función serverless con Spring Cloud Functions**
```java
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MiFuncionServerless {

    @Bean
    public Function<String, String> procesarMensaje() {
        return mensaje -> "Mensaje procesado: " + mensaje;
    }
}
```
📌 **Explicación:**  
✔ `@Bean` → Define una función reutilizable.  
✔ `Function<String, String>` → Recibe un `String` y devuelve otro `String`.  
✔ Compatible con **AWS Lambda, Google Cloud Functions y Azure Functions**.

---

## **📌 2️⃣ Ejecutar la función en un REST API**
Spring Boot detecta automáticamente las funciones y las expone como un **endpoint HTTP**.  
📌 **Ejemplo: Llamar a la función con un API REST**
```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcion")
public class FuncionController {

    private final Function<String, String> procesarMensaje;

    public FuncionController(Function<String, String> procesarMensaje) {
        this.procesarMensaje = procesarMensaje;
    }

    @PostMapping
    public String procesar(@RequestBody String mensaje) {
        return procesarMensaje.apply(mensaje);
    }
}
```
✅ **Ahora podemos llamar a la función enviando un `POST` a `/funcion` con un mensaje.**

---

## **📌 3️⃣ Ejecutar la función como AWS Lambda**
📌 **Pasos para desplegar en AWS Lambda:**

### **1. Agregar dependencias en `pom.xml`**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-function-adapter-aws</artifactId>
    <version>3.2.0</version>
</dependency>
```

### **2. Crear la clase `LambdaHandler`**
AWS Lambda necesita un "handler" para recibir eventos y ejecutarlos.

```java
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class LambdaHandler extends SpringBootRequestHandler<String, String> {
}
```
✅ **Ahora, AWS Lambda podrá ejecutar la función `procesarMensaje()`.**

### **3. Empaquetar la aplicación en un JAR**
```bash
mvn clean package
```

### **4. Subir el JAR a AWS Lambda**
1. Ir a **AWS Lambda** > Crear función > **Subir JAR**.
2. Configurar el `Handler`:
   ```
   com.ejemplo.LambdaHandler::handleRequest
   ```
3. Guardar y probar la función.

📌 **Ejemplo de evento JSON en AWS Lambda:**
```json
{
  "mensaje": "Hola desde AWS Lambda"
}
```
✅ **AWS ejecutará la función y devolverá:**
```json
"Mensaje procesado: Hola desde AWS Lambda"
```

---

## **📌 4️⃣ Ejecutar la función en Google Cloud Functions**
📌 **Pasos para desplegar en Google Cloud Functions:**

### **1. Agregar dependencias en `pom.xml`**
```xml
<dependency>
    <groupId>com.google.cloud.functions</groupId>
    <artifactId>functions-framework-api</artifactId>
    <version>1.0.4</version>
</dependency>
```

### **2. Crear la clase `MiFuncionGCP`**
Google Cloud Functions usa una interfaz especial para recibir eventos.

```java
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

import java.io.BufferedWriter;

public class MiFuncionGCP implements HttpFunction {

    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        String mensaje = request.getFirstQueryParameter("mensaje").orElse("Mensaje vacío");
        BufferedWriter writer = response.getWriter();
        writer.write("Mensaje procesado: " + mensaje);
    }
}
```
✅ **Ahora, Google Cloud ejecutará la función cuando reciba un evento HTTP.**

### **3. Desplegar en Google Cloud**
```bash
gcloud functions deploy MiFuncionGCP --runtime java11 --trigger-http
```
📌 **Ejemplo de petición HTTP a Google Cloud Functions:**
```bash
curl "https://REGION-PROJECT.cloudfunctions.net/MiFuncionGCP?mensaje=Hola"
```
✅ **Salida esperada:**
```
Mensaje procesado: Hola
```

---

## **📌 5️⃣ Ejecutar la función en Azure Functions**
📌 **Pasos para desplegar en Azure Functions:**

### **1. Agregar dependencias en `pom.xml`**
```xml
<dependency>
    <groupId>com.microsoft.azure.functions</groupId>
    <artifactId>azure-functions-java-library</artifactId>
    <version>1.4.0</version>
</dependency>
```

### **2. Crear la clase `MiFuncionAzure`**
Azure Functions usa `HttpTrigger` para manejar peticiones HTTP.

```java
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

public class MiFuncionAzure {

    @FunctionName("procesarMensaje")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            ExecutionContext context) {

        String mensaje = request.getBody().orElse("Mensaje vacío");
        return request.createResponseBuilder(HttpStatus.OK).body("Mensaje procesado: " + mensaje).build();
    }
}
```
✅ **Ahora, Azure ejecutará la función cuando reciba una solicitud HTTP.**

### **3. Desplegar en Azure Functions**
```bash
mvn clean package
az functionapp deploy --resource-group my-resource-group --name my-function-app --src-path target/app.jar
```
📌 **Ejemplo de petición HTTP a Azure Functions:**
```bash
curl "https://my-function-app.azurewebsites.net/api/procesarMensaje?mensaje=Hola"
```
✅ **Salida esperada:**
```
Mensaje procesado: Hola
```

---

# **📌 Conclusión**
✅ **Spring Native y Spring Cloud Functions** permiten crear aplicaciones ultrarrápidas para **AWS Lambda, Google Cloud Functions y Azure Functions**.  
✅ **Menos consumo de memoria y arranque instantáneo**, ideal para **microservicios y serverless**.  
✅ **Despliegue simple**, sin necesidad de modificar el código para cada plataforma.
