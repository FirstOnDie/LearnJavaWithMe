# 🚀 **ArgoCD en Java** 🔥

ArgoCD es una herramienta de **GitOps** para Kubernetes que automatiza la implementación y sincronización de aplicaciones. **¿Quieres llevar tus despliegues de Java a otro nivel?** Con ArgoCD, puedes hacer que tu aplicación Java se despliegue automáticamente en Kubernetes **directamente desde Git**.

📌 **Resumen rápido:**  
✅ **Git como fuente de verdad:** Se asegura de que lo que está en Kubernetes sea lo mismo que lo que tienes en tu repositorio.  
✅ **Despliegues automáticos:** No más `kubectl apply` manual, ArgoCD lo hace por ti.  
✅ **Monitoreo en tiempo real:** Si alguien cambia algo en Kubernetes manualmente, ArgoCD lo detecta y lo corrige.  
✅ **Compatibilidad con Helm y Kustomize:** Funciona con herramientas modernas de despliegue.

---

## **🔹 ¿Por qué usar ArgoCD en proyectos Java?**
| Beneficio | ¿Por qué es importante en Java? |
|-----------|--------------------------------|
| 🚀 **Automatización Total** | No más despliegues manuales con `kubectl` o scripts. |
| 🔄 **Revertir Cambios Fácilmente** | Si algo falla, vuelves a una versión estable en segundos. |
| 🔍 **Detección de Desviaciones** | Detecta si alguien cambia algo en Kubernetes manualmente y lo corrige. |
| 🔑 **Seguridad Mejorada** | Controla quién puede hacer qué en los despliegues. |
| ⚡ **Compatible con Helm y Kustomize** | Facilita la gestión de configuraciones complejas. |

---

## **🔹 ¿Cómo funciona ArgoCD? (Arquitectura Simplificada)** 🏗️

1️⃣ **Desarrollador hace un commit en Git (nueva versión de la aplicación Java)**.  
2️⃣ **ArgoCD detecta el cambio en Git** y sincroniza Kubernetes con el repositorio.  
3️⃣ **ArgoCD aplica los manifests de Kubernetes** (`Deployment`, `Service`, `Ingress`, etc.).  
4️⃣ **Tu aplicación Java se despliega automáticamente** en el clúster de Kubernetes.

📌 **Es GitOps en acción: Todo lo que está en Git se refleja en Kubernetes automáticamente**.

---

## **🔹 Instalación de ArgoCD en Kubernetes** 🛠️

Para instalar **ArgoCD en Kubernetes**, ejecuta:
```bash
kubectl create namespace argocd
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
```

📌 **Acceder a la UI de ArgoCD:**
```bash
kubectl port-forward svc/argocd-server -n argocd 8080:443
```
Luego abre en tu navegador: [https://localhost:8080](https://localhost:8080) 🚀

🔑 **Obtener la contraseña del usuario `admin`**:
```bash
kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d && echo
```

---

## **🔹 Desplegar una aplicación Java en Kubernetes con ArgoCD** 🌍

### **📌 Paso 1: Crear los manifests de Kubernetes**
📄 **`deployment.yaml` (Despliegue de la app Java)**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mi-app-java
  labels:
    app: mi-app-java
spec:
  replicas: 2
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
          image: miusuario/mi-app-java:latest
          ports:
            - containerPort: 8080
```

📄 **`service.yaml` (Servicio para acceder a la app)**
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

📌 **Sube estos archivos a un repositorio Git (por ejemplo, `https://github.com/miusuario/mi-repo-java`)**.

---

### **📌 Paso 2: Crear la aplicación en ArgoCD**
Ejecuta el siguiente comando para decirle a ArgoCD que despliegue tu app desde Git:
```bash
argocd app create mi-app-java \
  --repo https://github.com/miusuario/mi-repo-java.git \
  --path kubernetes \
  --dest-server https://kubernetes.default.svc \
  --dest-namespace default
```

✅ **¡ArgoCD ahora desplegará automáticamente tu aplicación en Kubernetes!**

---

### **📌 Paso 3: Sincronizar y actualizar la aplicación**
Para asegurarte de que Kubernetes está sincronizado con Git, ejecuta:
```bash
argocd app sync mi-app-java
```
📌 **Cada vez que hagas un cambio en Git, ArgoCD lo detectará y actualizará la app automáticamente.**

---

## **🔹 Integración con Spring Boot** 🌱

Si estás trabajando con **Spring Boot**, puedes configurar la imagen de tu aplicación en `Dockerfile`:

📄 **`Dockerfile`**
```dockerfile
FROM openjdk:17
WORKDIR /app
COPY target/mi-app-java.jar app.jar
CMD ["java", "-jar", "app.jar"]
```

### **📌 Subir la imagen a Docker Hub**
```bash
docker build -t miusuario/mi-app-java:latest .
docker push miusuario/mi-app-java:latest
```

📌 **ArgoCD detectará automáticamente los cambios en la imagen si usas `image tag` en Git**.

---

## **🔹 Integración con Helm para Despliegues Flexibles** 🎩

Si necesitas **mayor flexibilidad** en la configuración de tu aplicación, **Helm** es la mejor opción.

📄 **`values.yaml` (Ejemplo con Helm)**
```yaml
replicaCount: 3
image:
  repository: miusuario/mi-app-java
  tag: latest
  pullPolicy: IfNotPresent
service:
  type: LoadBalancer
  port: 80
```

📌 **ArgoCD soporta Helm nativamente** 🎉. Solo configura tu aplicación usando Helm Charts en lugar de YAML plano.

---

## **🔹 Monitoreo de Despliegues con ArgoCD UI** 🖥️

ArgoCD tiene una **interfaz gráfica increíble** donde puedes:  
✅ Ver el estado de las aplicaciones desplegadas.  
✅ Comparar el estado actual de Kubernetes con el repositorio Git.  
✅ Sincronizar manualmente las aplicaciones.

📌 Para acceder a la UI de ArgoCD, ve a [https://localhost:8080](https://localhost:8080) y usa las credenciales de `admin`.

---

## **🔹 Comparación: ArgoCD vs Jenkins vs FluxCD** ⚖️

| Característica | **ArgoCD** | **Jenkins** | **FluxCD** |
|--------------|-----------|------------|-----------|
| **GitOps nativo** | ✅ Sí | ❌ No | ✅ Sí |
| **Interfaz gráfica** | ✅ Sí | ⚠️ Básica | ❌ No |
| **Automatización de despliegues** | ✅ Sí | ✅ Sí (pero con scripts) | ✅ Sí |
| **Soporte para Helm/Kustomize** | ✅ Sí | ⚠️ Con plugins | ✅ Sí |
| **Rollback automático** | ✅ Sí | ❌ No | ✅ Sí |

📌 **Conclusión:** **ArgoCD** es la mejor opción si usas **Kubernetes y GitOps** para despliegues automáticos.

---
