# 🌍 **Google Cloud Platform (GCP) para Java 🚀**

Si eres un **Java Developer** y quieres **dominar Google Cloud Platform (GCP)**, esta guía es para ti. Vamos a explorar los **servicios clave de GCP para desarrollar, desplegar y escalar aplicaciones Java**, con **ejemplos prácticos, código y casos de uso reales**.

---

# **1️⃣ ¿Por qué usar GCP en proyectos Java?**

✅ **Infraestructura global** – Centros de datos en todo el mundo con baja latencia.  
✅ **Integración con herramientas Java** – Compatible con **Spring Boot, Quarkus, Micronaut, JBoss, Tomcat, y más**.  
✅ **Escalabilidad automática** – Ajusta recursos dinámicamente según la demanda.  
✅ **Servicios administrados** – Bases de datos, almacenamiento y seguridad sin preocuparte por la infraestructura.  
✅ **Compatible con Kubernetes y Contenedores** – Integración con **Google Kubernetes Engine (GKE)**.

---

# **2️⃣ Servicios Clave de Google Cloud para Java Developers**

## ☁️ **1. Google App Engine – Despliegue Serverless de Aplicaciones Java**
Google **App Engine** es un **PaaS (Plataforma como Servicio)** que permite desplegar aplicaciones **Java sin preocuparse por la infraestructura**.

📌 **Ejemplo de uso:**
- Desplegar aplicaciones **Spring Boot** sin configurar servidores.
- Ejecutar aplicaciones con **Tomcat, WildFly o Jetty**.
- Escalado automático según la carga de trabajo.

🔹 **Ejemplo: Desplegar una aplicación Java en Google App Engine**

1️⃣ **Crea un archivo `app.yaml`**
```yaml
runtime: java17
instance_class: F2
```
2️⃣ **Desplegar la aplicación**
```bash
gcloud app deploy
```
✅ **Tu aplicación Java ya está en producción en Google Cloud! 🎉**

---

## 🖥️ **2. Compute Engine – Máquinas Virtuales para Java**
Google **Compute Engine** te permite ejecutar aplicaciones Java en **máquinas virtuales personalizables**.

📌 **Ejemplo de uso:**
- Desplegar **microservicios Java en máquinas virtuales**.
- Ejecutar aplicaciones Java con alta disponibilidad.
- Configurar instancias con **autoscaling** y balanceo de carga.

🔹 **Ejemplo: Crear una VM en Compute Engine y ejecutar una app Java**
```bash
gcloud compute instances create mi-servidor-java \
    --image-family=debian-11 \
    --image-project=debian-cloud \
    --machine-type=e2-medium
```
✅ **Esto lanza una máquina virtual lista para desplegar una aplicación Java.**

---

## 🚢 **3. Google Kubernetes Engine (GKE) – Contenedores y Microservicios**
GKE es un servicio **gestionado de Kubernetes**, ideal para desplegar **microservicios Java en contenedores**.

📌 **Ejemplo de uso:**
- Ejecutar aplicaciones **Spring Boot en contenedores**.
- Gestionar despliegues con **Helm y Kubernetes**.
- Orquestar **microservicios Java** escalables.

🔹 **Ejemplo: Desplegar una aplicación Java en GKE**
```bash
kubectl create deployment mi-app-java --image=gcr.io/mi-proyecto/mi-imagen-java:latest
kubectl expose deployment mi-app-java --type=LoadBalancer --port=8080
```
✅ **Tu microservicio Java ya está corriendo en Kubernetes en la nube.**

---

## 💾 **4. Cloud Storage – Almacenamiento de Archivos en GCP**
Cloud Storage es un servicio para **almacenar archivos en la nube** de forma segura y escalable.

📌 **Ejemplo de uso:**
- Guardar imágenes, videos y documentos desde una aplicación Java.
- Compartir archivos entre diferentes microservicios.
- Procesar archivos de forma asíncrona con **Cloud Functions**.

🔹 **Ejemplo: Subir un archivo a Google Cloud Storage con Java**
```java
Storage storage = StorageOptions.getDefaultInstance().getService();
BlobId blobId = BlobId.of("mi-bucket", "archivo.txt");
BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
storage.create(blobInfo, Files.readAllBytes(Paths.get("archivo.txt")));
System.out.println("Archivo subido a Google Cloud Storage.");
```
✅ **Esto subirá un archivo a un bucket en Google Cloud Storage.**

---

## 🗄️ **5. Cloud SQL – Bases de Datos Relacionales en la Nube**
Cloud SQL es una base de datos **gestionada** compatible con **MySQL, PostgreSQL y SQL Server**.

📌 **Ejemplo de uso:**
- Conectar **Spring Boot a Cloud SQL**.
- Escalar automáticamente la base de datos.
- Migrar bases de datos locales a Google Cloud.

🔹 **Ejemplo: Conectar Spring Boot a Cloud SQL (MySQL)**
```properties
spring.datasource.url=jdbc:mysql://mi-cloudsql-instance:3306/miBaseDatos
spring.datasource.username=admin
spring.datasource.password=MiPassword123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
✅ **Esto conecta una aplicación Java a una base de datos MySQL en Google Cloud.**

---

## 📡 **6. Cloud Pub/Sub – Comunicación Asíncrona para Microservicios**
Cloud **Pub/Sub** permite **comunicación en tiempo real** entre microservicios con **mensajería basada en eventos**.

📌 **Ejemplo de uso:**
- Enviar mensajes entre microservicios **Spring Boot**.
- Procesar eventos en tiempo real con **Google Cloud Functions**.

🔹 **Ejemplo: Publicar un mensaje en un tópico de Pub/Sub**
```java
TopicName topicName = TopicName.of("mi-proyecto", "mi-topico");
Publisher publisher = Publisher.newBuilder(topicName).build();
ByteString data = ByteString.copyFromUtf8("Hola desde Pub/Sub!");
PubsubMessage message = PubsubMessage.newBuilder().setData(data).build();
publisher.publish(message);
System.out.println("Mensaje enviado a Pub/Sub.");
```
✅ **Esto envía un mensaje a un tópico de Pub/Sub en Google Cloud.**

---

# **3️⃣ Otras Herramientas Claves en GCP para Java Developers**

## **🔷 Cloud Build – CI/CD para Java**
Cloud Build permite **automatizar el despliegue de aplicaciones Java** en Google Cloud.

📌 **Ejemplo de uso:**
- Integrar pruebas automatizadas en **Maven/Gradle**.
- Desplegar automáticamente en **App Engine, Kubernetes o Compute Engine**.

---

## **🔷 Cloud Monitoring – Logs y Métricas**
Cloud Monitoring captura **logs y métricas de las aplicaciones Java** en GCP.

📌 **Ejemplo de uso:**
- Monitorizar logs de **Spring Boot y Tomcat**.
- Crear alertas cuando una aplicación falla.
- Analizar rendimiento con **Cloud Trace y Cloud Profiler**.

---

## **🔷 Secret Manager – Gestión Segura de Credenciales**
Google **Secret Manager** permite gestionar **contraseñas y claves API** de manera segura.

📌 **Ejemplo de uso:**
- Guardar credenciales de bases de datos de forma segura.
- Acceder a secretos desde una aplicación Spring Boot.

🔹 **Ejemplo: Obtener un secreto desde Secret Manager en Java**
```java
SecretManagerServiceClient client = SecretManagerServiceClient.create();
SecretVersionName secretName = SecretVersionName.of("mi-proyecto", "mi-secreto", "latest");
AccessSecretVersionResponse response = client.accessSecretVersion(secretName);
String secret = response.getPayload().getData().toStringUtf8();
System.out.println("Secreto obtenido: " + secret);
```
✅ **Esto recupera un secreto desde Google Secret Manager en Java.**

---