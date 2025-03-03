# **🔥 AWS para Programadores Java** ☁️


---

## **1️⃣ EC2 (Elastic Compute Cloud) – Servidores en la Nube** 🖥️
🔹 **¿Qué es?** Servidores virtuales en la nube para desplegar aplicaciones.  
🔹 **¿Por qué usarlo?** Puedes alojar **microservicios en Spring Boot**, APIs o aplicaciones Java con Tomcat o WildFly.

📌 **Ejemplo de uso en Java**  
Puedes conectarte a EC2 usando **AWS SDK for Java**:
```java
AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().build();
DescribeInstancesRequest request = new DescribeInstancesRequest();
DescribeInstancesResult response = ec2.describeInstances(request);

for (Reservation reservation : response.getReservations()) {
    for (Instance instance : reservation.getInstances()) {
        System.out.println("Instancia: " + instance.getInstanceId());
    }
}
```
💡 **Consejo:** Usa **Auto Scaling** y **Load Balancer** para mejorar el rendimiento.

---

## **2️⃣ S3 (Simple Storage Service) – Almacenamiento de Archivos** 🗄️
🔹 **¿Qué es?** Servicio para almacenar archivos como imágenes, logs o backups.  
🔹 **¿Por qué usarlo?** Perfecto para guardar documentos generados en Java, como reportes PDF.

📌 **Ejemplo: Subir un archivo a S3 desde Java**
```java
AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();
String bucketName = "mi-bucket";
String fileName = "reporte.pdf";
File file = new File("ruta/local/reporte.pdf");

s3Client.putObject(bucketName, fileName, file);
```
💡 **Consejo:** Configura **versionado** en S3 para evitar perder archivos por error.

---

## **3️⃣ RDS (Relational Database Service) – Bases de Datos SQL** 🛢️
🔹 **¿Qué es?** Servicio para usar bases de datos **MySQL, PostgreSQL, SQL Server y más**.  
🔹 **¿Por qué usarlo?** Si tienes una **aplicación Java con Hibernate y JPA**, RDS te permite usar bases de datos **sin preocuparte por infraestructura**.

📌 **Ejemplo: Conectar una app Spring Boot a RDS (MySQL)**
```properties
spring.datasource.url=jdbc:mysql://mi-rds.cq2xjfs.mongodb.net:3306/mibasededatos
spring.datasource.username=admin
spring.datasource.password=MiClave123
spring.jpa.hibernate.ddl-auto=update
```
💡 **Consejo:** Usa **Read Replicas** en RDS para mejorar rendimiento en consultas.

---

## **4️⃣ DynamoDB – Base de Datos NoSQL** ⚡
🔹 **¿Qué es?** Base de datos **rápida y escalable** tipo **NoSQL** (clave-valor).  
🔹 **¿Por qué usarlo?** Ideal para **almacenar sesiones de usuario, caché y logs** en aplicaciones Java.

📌 **Ejemplo: Guardar un usuario en DynamoDB desde Java**
```java
AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard().build();
DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

Usuario usuario = new Usuario("123", "Juan Pérez", "juan@example.com");
mapper.save(usuario);
```
💡 **Consejo:** Usa **DAX (DynamoDB Accelerator)** para mejorar la velocidad de las consultas.

---

## **5️⃣ Lambda – Java Serverless sin Servidores** 🌀
🔹 **¿Qué es?** Ejecuta código **Java sin necesidad de servidores**.  
🔹 **¿Por qué usarlo?** Para tareas pequeñas, como procesar eventos de S3 o responder a peticiones HTTP.

📌 **Ejemplo: Función AWS Lambda en Java**
```java
public class MiFuncionLambda implements RequestHandler<Map<String, String>, String> {
    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        return "Hola, " + input.get("nombre") + "!";
    }
}
```
💡 **Consejo:** Usa **API Gateway** para convertir tu función Lambda en una API REST.

---

## **6️⃣ API Gateway – Exponer APIs REST con AWS** 🌍
🔹 **¿Qué es?** Servicio que permite **exponer endpoints HTTP** y conectarlos con Lambda, EC2 o RDS.  
🔹 **¿Por qué usarlo?** Para crear APIs seguras y escalables sin configurar servidores.

📌 **Ejemplo: Crear una API en Java usando AWS SDK**
```java
AmazonApiGateway apiGateway = AmazonApiGatewayClientBuilder.standard().build();
CreateRestApiRequest request = new CreateRestApiRequest().withName("MiAPI");
CreateRestApiResult result = apiGateway.createRestApi(request);
System.out.println("API creada: " + result.getId());
```
💡 **Consejo:** Usa **JWT con Cognito** para autenticación segura en API Gateway.

---

## **7️⃣ IAM (Identity and Access Management) – Seguridad y Permisos** 🔑
🔹 **¿Qué es?** Servicio para gestionar **usuarios, roles y permisos** en AWS.  
🔹 **¿Por qué usarlo?** Para que tus aplicaciones Java accedan solo a los servicios necesarios.

📌 **Ejemplo: Crear un usuario IAM desde Java**
```java
AWSIot client = AWSIotClientBuilder.standard().build();
CreateUserRequest request = new CreateUserRequest().withUserName("MiUsuario");
client.createUser(request);
```
💡 **Consejo:** Aplica el **Principio de Menor Privilegio** para evitar brechas de seguridad.

---

## **8️⃣ SQS (Simple Queue Service) – Procesamiento Asíncrono** 📩
🔹 **¿Qué es?** Servicio de colas para **procesamiento de tareas en segundo plano**.  
🔹 **¿Por qué usarlo?** Para evitar **bloqueos** en tu aplicación cuando manejas **eventos masivos**.

📌 **Ejemplo: Leer mensajes de una cola SQS en Java**
```java
AmazonSQS sqs = AmazonSQSClientBuilder.standard().build();
ReceiveMessageRequest request = new ReceiveMessageRequest("URL-de-mi-cola");
List<Message> mensajes = sqs.receiveMessage(request).getMessages();

for (Message mensaje : mensajes) {
    System.out.println("Mensaje recibido: " + mensaje.getBody());
}
```
💡 **Consejo:** Usa **SNS (Simple Notification Service)** para enviar notificaciones junto con SQS.

---

## **📌 Conclusión**

1️⃣ **EC2** → Servidores en la nube para desplegar APIs.  
2️⃣ **S3** → Almacenamiento de archivos para documentos o imágenes.  
3️⃣ **RDS** → Bases de datos SQL como MySQL y PostgreSQL.  
4️⃣ **DynamoDB** → Base de datos NoSQL rápida y escalable.  
5️⃣ **Lambda** → Ejecutar código Java sin servidores (serverless).  
6️⃣ **API Gateway** → Exponer APIs REST de manera segura.  
7️⃣ **IAM** → Gestionar permisos y roles de seguridad.  
8️⃣ **SQS** → Procesamiento asíncrono con colas de mensajes.

