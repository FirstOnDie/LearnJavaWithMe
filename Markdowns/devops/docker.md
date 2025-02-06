# 🐳 **Docker en Java** 🚀

**¿Qué es Docker?**  
Docker es una **plataforma de contenedores** que permite empaquetar, distribuir y ejecutar aplicaciones **de forma rápida y consistente** en cualquier entorno.

📌 **Resumen rápido:**  
✅ **Ejecuta tu app en cualquier máquina sin problemas de compatibilidad.**  
✅ **Aísla dependencias** para que no haya conflictos entre proyectos.  
✅ **Optimiza recursos**, permitiendo ejecutar varias apps en el mismo servidor.  
✅ **Fácil integración con Kubernetes y CI/CD.**

---

# **🔹 ¿Por qué usar Docker en proyectos Java?**

| Beneficio | ¿Por qué es importante en Java? |
|-----------|--------------------------------|
| 🚀 **Independencia del sistema operativo** | Tu app corre igual en Windows, Mac o Linux. |
| 🔄 **Entorno consistente** | Evita el clásico "En mi máquina funciona...". |
| 📦 **Empaquetado fácil** | Incluye Java, librerías y código en un solo contenedor. |
| ⚡ **Rápido despliegue** | Ejecuta y escala aplicaciones con solo unos comandos. |
| ☸ **Compatible con Kubernetes** | Ideal para microservicios en la nube. |

📌 **Si tienes una aplicación Java y quieres desplegarla fácilmente en cualquier servidor o nube, Docker es tu mejor amigo.**

---

# **🔹 ¿Cómo funciona Docker? (Explicación Visual)** 🔥

1️⃣ **Creas una imagen Docker con tu aplicación Java.**  
2️⃣ **Esa imagen se ejecuta en un contenedor Docker.**  
3️⃣ **Docker permite correr múltiples contenedores aislados en un solo servidor.**  
4️⃣ **Puedes compartir imágenes en Docker Hub y ejecutar tu app en cualquier lugar.**

📌 **Docker es como una maleta con todo lo que necesitas para que tu aplicación funcione en cualquier parte.**

---

# **🔹 Instalación de Docker (en 2 pasos)** 🛠️

📌 **Paso 1: Instalar Docker**  
Descarga e instala Docker según tu sistema operativo:  
🔗 [Descargar Docker Desktop](https://www.docker.com/products/docker-desktop/)

📌 **Paso 2: Verificar instalación**
```bash
docker --version
```
✅ **Si ves una versión de Docker, ya está listo!** 🎉

---

# **🔹 Dockerizando una Aplicación Java (Spring Boot)** 🚀

📄 **📌 Código básico de la app (`MainController.java`)**
```java
@RestController
public class MainController {
    @GetMapping("/")
    public String home() {
        return "¡Hola desde Docker!";
    }
}
```
📌 **Construimos el JAR:**
```bash
mvn clean package
```
📌 **Generará `target/mi-app.jar`. Ahora lo convertimos en un contenedor con Docker.**

---

## **📌 Paso 1: Crear un Dockerfile** 📄

📄 **`Dockerfile`** (Define cómo construir la imagen Docker de nuestra app)
```dockerfile
# Usamos una imagen base con Java 17
FROM openjdk:17

# Definimos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos nuestro archivo JAR dentro del contenedor
COPY target/mi-app.jar app.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
```

📌 **Dockerfile explicado paso a paso:**  
1️⃣ `FROM openjdk:17` → Usa Java 17 como base.  
2️⃣ `WORKDIR /app` → Crea una carpeta `/app` en el contenedor.  
3️⃣ `COPY target/mi-app.jar app.jar` → Copia la app dentro del contenedor.  
4️⃣ `CMD ["java", "-jar", "app.jar"]` → Comando que ejecutará la app.

---

## **📌 Paso 2: Construir la imagen Docker**

📌 **Ejecuta este comando en la carpeta del proyecto:**
```bash
docker build -t miusuario/mi-app:latest .
```
✅ **Esto crea una imagen Docker llamada `miusuario/mi-app` basada en nuestro `Dockerfile`.**

---

## **📌 Paso 3: Ejecutar la imagen Docker (Crear un contenedor)**

📌 **Ejecutar la aplicación en un contenedor:**
```bash
docker run -p 8080:8080 miusuario/mi-app
```
✅ **Ahora puedes acceder a tu app en `http://localhost:8080/`.**

---

## **📌 Paso 4: Subir la imagen a Docker Hub**

📌 **1️⃣ Iniciar sesión en Docker Hub:**
```bash
docker login
```
📌 **2️⃣ Subir la imagen a Docker Hub:**
```bash
docker push miusuario/mi-app:latest
```
✅ **Ahora puedes descargar y ejecutar esta imagen en cualquier servidor!** 🎉

---

# **🔹 Docker Compose: Ejecutar múltiples servicios** 🛠️

Si tu app necesita una base de datos, puedes usar **Docker Compose** para levantar ambos servicios.

📄 **`docker-compose.yaml`**
```yaml
version: '3.8'
services:
  app:
    image: miusuario/mi-app
    ports:
      - "8080:8080"
    depends_on:
      - db
  db:
    image: postgres
    environment:
      POSTGRES_USER: usuario
      POSTGRES_PASSWORD: contraseña
      POSTGRES_DB: midb
```
📌 **Levantar todo con un solo comando:**
```bash
docker-compose up -d
```
✅ **Ahora tienes tu app y la base de datos corriendo juntas!** 🎉

---

# **🔹 ¿Cómo integrar Docker con Kubernetes?** ☸

📌 **Desplegar la app en Kubernetes:**
```bash
kubectl create deployment mi-app --image=miusuario/mi-app
kubectl expose deployment mi-app --type=LoadBalancer --port=8080
```
✅ **Docker y Kubernetes trabajan juntos para hacer el despliegue fácil y escalable.**

---

# **🔹 Docker vs Kubernetes vs ArgoCD – ¿Cuándo usar cada uno?** ⚖️

| Tecnología | Propósito |
|------------|-------------------------------------|
| 🐳 **Docker** | Crea y ejecuta contenedores. |
| ☸ **Kubernetes** | Orquesta y gestiona contenedores. |
| 🚀 **ArgoCD** | Automatiza despliegues con GitOps. |

📌 **Ejemplo real:**  
1️⃣ **Docker** empaqueta la app en una imagen.  
2️⃣ **Kubernetes** la ejecuta en múltiples servidores.  
3️⃣ **ArgoCD** la actualiza automáticamente cuando hay cambios en Git.

---