# 🛳️ **Helm en Kubernetes** 🚀

## **🔹 ¿Qué es Helm?**
Helm es un **gestor de paquetes** para Kubernetes, similar a **APT** en Ubuntu o **YUM** en CentOS.  
📦 **Facilita la instalación, actualización y gestión de aplicaciones en Kubernetes** sin necesidad de escribir complejas configuraciones YAML manualmente.

📌 **En pocas palabras, Helm te permite desplegar aplicaciones en Kubernetes con solo un comando!** 😎

---

# **🔹 ¿Por qué usar Helm?** 🤔

| 🚀 Beneficio | 💡 ¿Por qué es importante? |
|-------------|---------------------------|
| 📦 **Plantillas reutilizables** | No necesitas escribir YAML desde cero cada vez. |
| ⚡ **Despliegues rápidos** | Instala aplicaciones completas con un solo comando. |
| 🔄 **Facilita actualizaciones** | Modifica configuraciones sin volver a escribir todo. |
| 🛠️ **Gestión de versiones** | Permite hacer rollback si algo sale mal. |
| ☸ **Soporte para microservicios** | Ideal para desplegar múltiples componentes juntos. |

📌 **Si usas Kubernetes y quieres automatizar tus despliegues, Helm es imprescindible.**

---

# **🔹 Instalación de Helm (en 2 minutos)** ⏳

📌 **Paso 1: Descargar Helm**  
🔗 [Descargar Helm](https://helm.sh/docs/intro/install/) según tu sistema operativo.

📌 **Paso 2: Verificar la instalación**
```bash
helm version
```
✅ **Si ves la versión de Helm, ya está listo!** 🎉

---

# **🔹 ¿Cómo funciona Helm? (Explicación Visual)** 🧐

1️⃣ **Helm usa "Charts"**, que son paquetes que contienen todo lo necesario para desplegar una aplicación en Kubernetes.  
2️⃣ **Instalas un Chart**, y Helm despliega automáticamente los Pods, Services, Deployments, etc.  
3️⃣ **Puedes actualizar configuraciones fácilmente sin tocar los YAML manualmente.**

📌 **Helm convierte un despliegue complejo en Kubernetes en algo tan fácil como instalar una app en tu teléfono.** 📱

---

# **🔹 Instalando una Aplicación con Helm (Ejemplo con Nginx)**

📌 **Paso 1: Agregar un repositorio de Charts oficiales**
```bash
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update
```
📌 **Paso 2: Instalar Nginx en Kubernetes con Helm**
```bash
helm install mi-nginx bitnami/nginx
```
✅ **Esto desplegará Nginx con configuración predeterminada en Kubernetes!** 🚀

📌 **Paso 3: Verificar el despliegue**
```bash
helm list
kubectl get pods
```
📌 **Paso 4: Desinstalar si ya no lo necesitas**
```bash
helm uninstall mi-nginx
```
✅ **Con un solo comando, puedes eliminar la aplicación sin dejar rastros.**

---

# **🔹 Creando un Chart Helm para una Aplicación Java (Spring Boot + PostgreSQL)**

📌 **Paso 1: Crear un nuevo Chart Helm**
```bash
helm create mi-app
cd mi-app
```
📌 **Estructura generada:**
```
mi-app/
  ├── charts/        # Dependencias (otros charts)
  ├── templates/     # Archivos YAML con variables
  ├── values.yaml    # Configuraciones editables
  ├── Chart.yaml     # Información del Chart
  └── templates/     # Manifiestos Kubernetes con Helm templates
```

📌 **Paso 2: Editar `values.yaml` (Variables de Configuración)**
```yaml
image:
  repository: miusuario/mi-app
  tag: latest
  pullPolicy: IfNotPresent

service:
  type: ClusterIP
  port: 8080
```
📌 **Paso 3: Editar `templates/deployment.yaml`**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{ .Release.Name }}-app
spec:
  replicas: 2
  selector:
    matchLabels:
      app: {{ .Release.Name }}
  template:
    metadata:
      labels:
        app: {{ .Release.Name }}
    spec:
      containers:
        - name: mi-app
          image: "{{ .Values.image.repository }}:{{ .Values.image.tag }}"
          ports:
            - containerPort: 8080
```
📌 **Paso 4: Desplegar la aplicación en Kubernetes con Helm**
```bash
helm install mi-app ./mi-app
```
✅ **Helm generará todos los archivos YAML automáticamente y los desplegará en Kubernetes.** 🎉

📌 **Para actualizar la aplicación:**
```bash
helm upgrade mi-app ./mi-app
```
📌 **Para eliminar la aplicación:**
```bash
helm uninstall mi-app
```

---

# **🔹 Integración de Helm con CI/CD (GitHub Actions + ArgoCD)** 🤖

📌 **Automatizar despliegues con Helm en GitHub Actions**
```yaml
name: Deploy Helm Chart

on:
  push:
    branches:
      - main

jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout código
        uses: actions/checkout@v2

      - name: Configurar Helm
        run: |
          curl https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3 | bash

      - name: Desplegar en Kubernetes
        run: helm upgrade --install mi-app ./mi-app --namespace produccion
```
✅ **Cada vez que hagas `git push`, Helm actualizará el despliegue en Kubernetes automáticamente!** 🚀

📌 **Para una integración avanzada, usa [ArgoCD](https://argo-cd.readthedocs.io/en/stable/) con Helm para despliegues GitOps.**

---

# **🔹 Helm vs Kubernetes YAML – ¿Cuál usar?** 🤔

| 🔥 Característica | ⚙️ Kubernetes YAML | 🚀 Helm |
|------------------|-----------------|--------|
| **Facilidad de uso** | Configuración manual y repetitiva | Plantillas reutilizables y automatización |
| **Manejo de versiones** | No hay control de versiones | Fácil rollback con `helm rollback` |
| **Configuración** | Manual en YAML | Variables en `values.yaml` |
| **Escalabilidad** | Complejo para microservicios | Fácil para arquitecturas modulares |

📌 **Usa Helm si trabajas con múltiples servicios o quieres CI/CD eficiente en Kubernetes.**

---