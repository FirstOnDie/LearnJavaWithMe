# 🚀 **Kubernetes en Java** 🔥

Kubernetes (**K8s**) es una **plataforma de orquestación de contenedores** que se encarga de **gestionar** y **escalar** aplicaciones automáticamente. **¿Tienes una aplicación Java y quieres que sea robusta, escalable y fácil de administrar?** Kubernetes es la clave.

📌 **Resumen rápido:**  
✅ **Automatiza despliegues y escalabilidad**  
✅ **Distribuye carga entre instancias de tu aplicación**  
✅ **Se autorepara** en caso de fallos  
✅ **Administra configuraciones y secretos**  
✅ **Balancea tráfico de red** y gestiona acceso

---

# **🔹 ¿Por qué usar Kubernetes en proyectos Java?**

| Beneficio | ¿Por qué es importante en Java? |
|-----------|--------------------------------|
| 🚀 **Despliegues automatizados** | Olvídate de desplegar manualmente en servidores. |
| 🔄 **Escalabilidad automática** | Aumenta o reduce instancias de tu app según el tráfico. |
| 🔧 **Autoreparación** | Si una instancia falla, Kubernetes la reemplaza automáticamente. |
| 🔑 **Gestión de configuraciones** | Puedes cambiar variables de entorno sin modificar tu código. |
| 🌍 **Balanceo de carga** | Distribuye peticiones entre múltiples instancias de tu aplicación. |

📌 **Si tienes una aplicación Java y quieres que esté SIEMPRE disponible, Kubernetes es tu mejor aliado.**

---

# **🔹 ¿Cómo funciona Kubernetes? (Arquitectura Simplificada)** 🏗️

1️⃣ **Subes tu aplicación Java como una imagen Docker.**  
2️⃣ **Kubernetes la despliega automáticamente en múltiples nodos (máquinas).**  
3️⃣ **Kubernetes distribuye el tráfico entre las instancias (pods).**  
4️⃣ **Si una instancia falla, Kubernetes la reinicia automáticamente.**  
5️⃣ **Si el tráfico aumenta, Kubernetes crea más instancias automáticamente.**

📌 **Kubernetes es como un "gerente de infraestructura" que mantiene tu app corriendo sin interrupciones.**

---

# **🔹 Instalación de Kubernetes (Minikube para pruebas locales)** 🛠️

Si quieres probar Kubernetes en tu máquina, usa **Minikube**.

1️⃣ **Instalar Minikube:**
```bash
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube
```
2️⃣ **Iniciar un clúster local:**
```bash
minikube start
```
3️⃣ **Ver nodos disponibles:**
```bash
kubectl get nodes
```
✅ Ahora tienes un clúster Kubernetes corriendo localmente.

---

# **🔹 Desplegar una aplicación Java en Kubernetes** 🌍

Supongamos que tenemos una aplicación **Spring Boot** con un controlador simple:

📄 **`MainController.java`**
```java
@RestController
public class MainController {
    @GetMapping("/")
    public String home() {
        return "¡Hola desde Kubernetes!";
    }
}
```

---

## **📌 Paso 1: Crear una imagen Docker de la aplicación** 🐳

📄 **`Dockerfile`**
```dockerfile
FROM openjdk:17
WORKDIR /app
COPY target/mi-app.jar app.jar
CMD ["java", "-jar", "app.jar"]
```
📌 **Construir y subir la imagen a Docker Hub:**
```bash
docker build -t miusuario/mi-app:latest .
docker push miusuario/mi-app:latest
```
✅ Ahora la imagen está lista para ser usada en Kubernetes.

---

## **📌 Paso 2: Crear un Deployment en Kubernetes**

📄 **`deployment.yaml`**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mi-app-java
spec:
  replicas: 3
  selector:
    matchLabels:
      app: mi-app-java
  template:
    metadata:
      labels:
        app: mi-app-java
    spec:
      containers:
        - name: mi-app-java
          image: miusuario/mi-app:latest
          ports:
            - containerPort: 8080
```
📌 **Desplegar en Kubernetes:**
```bash
kubectl apply -f deployment.yaml
kubectl get pods
```
✅ **Kubernetes creará 3 réplicas automáticamente** de la aplicación.

---

## **📌 Paso 3: Exponer la aplicación con un Service**

📄 **`service.yaml`**
```yaml
apiVersion: v1
kind: Service
metadata:
  name: mi-app-java-service
spec:
  selector:
    app: mi-app-java
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
  type: LoadBalancer
```
📌 **Crear el servicio en Kubernetes:**
```bash
kubectl apply -f service.yaml
minikube service mi-app-java-service
```
✅ Kubernetes asignará una dirección para acceder a la app.

---

# **🔹 Escalado Automático en Kubernetes**

Si tu app recibe muchas peticiones, **Kubernetes puede crear más instancias automáticamente**.

📌 **Configurar autoescalado:**
```bash
kubectl autoscale deployment mi-app-java --cpu-percent=50 --min=3 --max=10
```
✅ Kubernetes **creará más pods** cuando la CPU suba del 50%.

---

# **🔹 Kubernetes ConfigMaps y Secrets**

📌 **ConfigMaps:** Almacenan configuración como variables de entorno.  
📌 **Secrets:** Almacenan datos sensibles (como contraseñas).

📄 **`configmap.yaml`**
```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: mi-config
data:
  mensaje: "¡Bienvenido a Kubernetes!"
```
📌 **Crear el ConfigMap:**
```bash
kubectl apply -f configmap.yaml
```
📄 **Usarlo en un Deployment:**
```yaml
env:
  - name: MENSAJE
    valueFrom:
      configMapKeyRef:
        name: mi-config
        key: mensaje
```
✅ **Ahora la app puede leer la variable `MENSAJE` desde el ConfigMap.**

---

# **🔹 Integración con ArgoCD**

Si quieres que **Kubernetes se mantenga sincronizado con Git automáticamente**, usa **ArgoCD**.

📌 **Crea la app en ArgoCD:**
```bash
argocd app create mi-app-java \
  --repo https://github.com/miusuario/mi-repo-java.git \
  --path kubernetes \
  --dest-server https://kubernetes.default.svc \
  --dest-namespace default
```
✅ **ArgoCD desplegará tu app y aplicará cualquier cambio automáticamente desde Git.**

---

# **🔹 Kubernetes vs Docker vs ArgoCD – ¿Cuándo usar cada uno?** ⚖️

| Tecnología | Propósito |
|------------|-------------------------------------|
| 🐳 **Docker** | Crea y ejecuta contenedores. |
| ☸️ **Kubernetes** | Orquesta y gestiona contenedores. |
| 🚀 **ArgoCD** | Automatiza despliegues con GitOps. |

📌 **Ejemplo real:**
1. **Docker** empaqueta la aplicación en una imagen.
2. **Kubernetes** despliega y escala la aplicación automáticamente.
3. **ArgoCD** mantiene el clúster sincronizado con Git.

---