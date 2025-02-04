# **📌 Día 24: Despliegue de aplicaciones Java con Docker y Kubernetes**
Hoy aprenderás:  
✅ **Dockerizar una aplicación Spring Boot**  
✅ **Desplegar en Kubernetes con `kubectl` y `Helm`**  
✅ **Configurar `k8s` con `Deployment`, `Service` y `Ingress`**

---

📌 **¿Por qué es importante?**  
El despliegue con **Docker y Kubernetes** permite escalar aplicaciones Java de manera eficiente y hacerlas portables en cualquier entorno (local, nube, on-premise).

---

# **1️⃣ Creando un Dockerfile para una Aplicación Java**
📌 **Ejemplo de aplicación Spring Boot (`Main.java`)**
```java
package com.ejemplo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "¡Hola desde Docker y Kubernetes!";
    }
}
```
📌 **Creamos un `Dockerfile` para contenerizar la aplicación**
```dockerfile
# 1️⃣ Usamos una imagen base de Java
FROM openjdk:17-jdk-slim

# 2️⃣ Establecemos el directorio de trabajo
WORKDIR /app

# 3️⃣ Copiamos el JAR generado en la imagen
COPY target/mi-aplicacion.jar app.jar

# 4️⃣ Exponemos el puerto de la aplicación
EXPOSE 8080

# 5️⃣ Comando de inicio de la aplicación
CMD ["java", "-jar", "app.jar"]
```
📌 **Construimos la imagen de Docker:**
```sh
docker build -t mi-aplicacion:v1 .
```
📌 **Ejecutamos el contenedor:**
```sh
docker run -p 8080:8080 mi-aplicacion:v1
```
📌 **Probamos la API:**
```sh
curl http://localhost:8080/
```
✅ **¡Nuestra app Java ahora corre dentro de un contenedor!**

---

# **2️⃣ Desplegando la Aplicación en Kubernetes**
📌 **Configuramos `Deployment.yaml` para Kubernetes**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mi-aplicacion
spec:
  replicas: 2
  selector:
    matchLabels:
      app: mi-aplicacion
  template:
    metadata:
      labels:
        app: mi-aplicacion
    spec:
      containers:
      - name: mi-aplicacion
        image: mi-aplicacion:v1
        ports:
        - containerPort: 8080
```
📌 **Configuramos `Service.yaml` para exponer la aplicación**
```yaml
apiVersion: v1
kind: Service
metadata:
  name: mi-aplicacion-service
spec:
  selector:
    app: mi-aplicacion
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
  type: LoadBalancer
```
📌 **Aplicamos la configuración a Kubernetes:**
```sh
kubectl apply -f Deployment.yaml
kubectl apply -f Service.yaml
```
📌 **Verificamos el despliegue:**
```sh
kubectl get pods
kubectl get services
```
📌 **Obtenemos la IP del servicio y probamos la API:**
```sh
curl http://<EXTERNAL-IP>/
```
✅ **¡Ahora la app Java está corriendo en Kubernetes con balanceo de carga!**

---

# **3️⃣ Optimización con Helm y ConfigMaps**
📌 **Usamos Helm para simplificar el despliegue**
```sh
helm create mi-app-chart
```
📌 **Modificamos `values.yaml`**
```yaml
replicaCount: 2
image:
  repository: mi-aplicacion
  tag: "v1"
service:
  type: LoadBalancer
  port: 80
```
📌 **Instalamos la aplicación en Kubernetes con Helm**
```sh
helm install mi-aplicacion ./mi-app-chart
```
✅ **Con Helm, la gestión del despliegue es más fácil y escalable.**