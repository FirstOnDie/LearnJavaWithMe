# ☁️ **AWS para Java🚀**

Si eres programador Java y quieres **dominar AWS (Amazon Web Services)**, esta guía es para ti. Vamos a explorar cómo AWS puede mejorar tus aplicaciones, qué servicios puedes usar y cómo integrarlos con Java. 💪🔥

---

## **🔹 ¿Por qué usar AWS en proyectos Java?**

✅ **Escalabilidad automática** – Tu aplicación crecerá sin problemas.  
✅ **Alta disponibilidad** – AWS garantiza que tu aplicación siempre esté disponible.  
✅ **Despliegue rápido** – No necesitas gestionar servidores manualmente.  
✅ **Menos costos de infraestructura** – Paga solo por lo que usas.  
✅ **Integración con microservicios** – Ideal para arquitecturas modernas con Spring Boot.

---

# 🔥 **1️⃣ Servicios Clave de AWS para Java Developers**

## 🔷 **1. AWS EC2 – Servidores Virtuales en la Nube**
AWS **EC2** (Elastic Compute Cloud) te permite crear servidores virtuales (instancias) donde puedes desplegar aplicaciones Java.

📌 **Ejemplo de uso:**
- Ejecutar una aplicación Java con Spring Boot.
- Desplegar un servidor Tomcat para una API REST.
- Configurar un servidor de pruebas automatizadas con Selenium.

🔹 **Ejemplo: Conectarse a una instancia EC2 con Java**
```java
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;

public class AWSEC2Example {
    public static void main(String[] args) {
        Ec2Client ec2 = Ec2Client.create();
        DescribeInstancesResponse response = ec2.describeInstances(DescribeInstancesRequest.builder().build());
        response.reservations().forEach(reservation -> reservation.instances().forEach(instance -> {
            System.out.println("Instancia EC2 ID: " + instance.instanceId());
        }));
    }
}
```
✅ **Esto devuelve las instancias EC2 activas en tu cuenta AWS.**

---

## 🔷 **2. AWS Lambda – Funciones Serverless**
AWS **Lambda** te permite ejecutar código Java sin necesidad de gestionar servidores.

📌 **Ejemplo de uso:**
- Procesar eventos en tiempo real.
- Ejecutar tareas automáticas en segundo plano.
- Conectar APIs sin necesidad de servidores.

🔹 **Ejemplo: Lambda con Java**  
1️⃣ **Crear una función Lambda en Java:**
```java
public class MiLambdaHandler implements RequestHandler<Map<String, String>, String> {
    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        return "¡Hola desde AWS Lambda, " + input.get("nombre") + "!";
    }
}
```
2️⃣ **Compilar en un JAR y subirlo a AWS Lambda:**
```bash
mvn clean package
aws lambda create-function --function-name MiFuncionLambda --runtime java17 --handler MiLambdaHandler --zip-file fileb://target/mi-lambda.jar
```
✅ **Esto ejecutará una función Lambda en la nube con código Java.**

---

## 🔷 **3. AWS S3 – Almacenamiento de Archivos en la Nube**
AWS **S3 (Simple Storage Service)** es un servicio de almacenamiento de objetos en la nube, ideal para guardar imágenes, logs, backups y más.

📌 **Ejemplo de uso:**
- Subir y descargar archivos desde Java.
- Guardar logs y reportes de la aplicación.
- Almacenar imágenes y videos de usuarios.

🔹 **Ejemplo: Subir un archivo a S3 con Java**
```java
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.nio.file.Paths;

public class S3Example {
    public static void main(String[] args) {
        S3Client s3 = S3Client.create();
        s3.putObject(PutObjectRequest.builder().bucket("mi-bucket").key("mi-archivo.txt").build(),
                     Paths.get("mi-archivo.txt"));
        System.out.println("Archivo subido a S3.");
    }
}
```
✅ **Esto subirá un archivo a un bucket en AWS S3.**

---

## 🔷 **4. AWS RDS – Bases de Datos en la Nube**
AWS **RDS (Relational Database Service)** permite crear bases de datos como **MySQL, PostgreSQL y SQL Server** en la nube.

📌 **Ejemplo de uso:**
- Conectar una aplicación Spring Boot a una base de datos en la nube.
- Automatizar backups y escalado de la base de datos.
- Migrar una base de datos local a AWS sin esfuerzo.

🔹 **Ejemplo: Conectar Spring Boot a una base de datos RDS**
```properties
spring.datasource.url=jdbc:mysql://mi-base-de-datos.cbnufwkgzbe1.us-east-1.rds.amazonaws.com:3306/mibasedatos
spring.datasource.username=admin
spring.datasource.password=miPassword123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

✅ **Esto conecta una aplicación Spring Boot a una base de datos RDS en AWS.**

---

# **2️⃣ Otras Herramientas Claves en AWS para Java Developers**

## **🔷 AWS IAM (Identity & Access Management) – Seguridad y Control de Accesos**
AWS IAM permite gestionar permisos y accesos a los servicios de AWS.

📌 **Ejemplo de uso:**
- Crear usuarios y roles con permisos específicos.
- Proteger buckets de S3 o bases de datos con autenticación segura.

---

## **🔷 AWS CloudWatch – Monitoreo y Logs**
AWS CloudWatch permite recopilar logs y métricas de las aplicaciones Java en AWS.

📌 **Ejemplo de uso:**
- Capturar logs de aplicaciones en EC2 o Lambda.
- Crear alarmas para detectar errores automáticamente.

---

## **🔷 AWS ECS y EKS – Despliegue con Contenedores**
AWS ECS (Elastic Container Service) y AWS EKS (Elastic Kubernetes Service) permiten desplegar aplicaciones en contenedores Docker o Kubernetes.

📌 **Ejemplo de uso:**
- Ejecutar microservicios Java en contenedores escalables.
- Gestionar despliegues con Kubernetes y AWS EKS.

---

## **🔷 AWS SNS y SQS – Comunicación Asíncrona entre Servicios**
AWS SNS (Simple Notification Service) y SQS (Simple Queue Service) permiten comunicar microservicios sin bloqueo.

📌 **Ejemplo de uso:**
- Enviar notificaciones a usuarios con SNS.
- Implementar colas de mensajes con SQS para desacoplar servicios Java.

---

## **🔷 AWS Step Functions – Orquestación de Procesos**
AWS Step Functions permite crear flujos de trabajo y procesos automatizados.

📌 **Ejemplo de uso:**
- Coordinar múltiples funciones Lambda en una secuencia lógica.
- Automatizar procesos de negocio con estados definidos.

---
