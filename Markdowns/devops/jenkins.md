# 🔧 **Jenkins en Java** 🚀

## **🔹 ¿Qué es Jenkins?**
Jenkins es una herramienta de **automatización de CI/CD (Integración Continua y Despliegue Continuo)**.  
📌 **Permite construir, probar y desplegar aplicaciones automáticamente cada vez que haces un cambio en tu código.** 😎

💡 **Piensa en Jenkins como un robot que se encarga de hacer todo el trabajo sucio por ti:**  
✔️ Descarga el código  
✔️ Compila el proyecto  
✔️ Ejecuta pruebas  
✔️ Genera artefactos (JAR, WAR, Docker)  
✔️ Despliega la aplicación

🔹 **Ejemplo de Jenkins en acción:**  
🎯 Haces un `git push` → Jenkins detecta el cambio → Ejecuta los tests → Construye un JAR/WAR → Lo sube a un servidor o contenedor. 🚀

✅ **Jenkins te ahorra tiempo y errores automatizando procesos repetitivos.**

---

# **🔹 ¿Por qué usar Jenkins en proyectos Java?** 🤔

| 🚀 Beneficio | 💡 ¿Por qué es importante? |
|-------------|---------------------------|
| ⚡ **Automatización** | No más despliegues manuales, todo se ejecuta automáticamente. |
| 🔄 **Integración con GitHub/GitLab** | Cada vez que hagas `git push`, Jenkins construye tu aplicación. |
| 🧪 **Ejecución automática de pruebas** | Detecta errores antes de que lleguen a producción. |
| ☸ **Despliegue en Kubernetes o Docker** | Puedes desplegar fácilmente en contenedores o servidores. |
| 🎯 **Monitoreo de builds** | Puedes ver qué cambios rompieron el código. |

📌 **Si trabajas con Java y quieres automatizar tus builds, Jenkins es imprescindible!** 🔥

---

# **🔹 Instalación de Jenkins (en 5 minutos)** ⏳

📌 **Paso 1: Descargar e instalar Jenkins**
```bash
wget -q -O - https://pkg.jenkins.io/debian/jenkins.io.key | sudo apt-key add -
sudo apt-add-repository "deb https://pkg.jenkins.io/debian-stable binary/"
sudo apt update
sudo apt install jenkins -y
```
📌 **Paso 2: Iniciar Jenkins**
```bash
sudo systemctl start jenkins
sudo systemctl enable jenkins
```
📌 **Paso 3: Acceder a Jenkins en el navegador**  
Abre en tu navegador:
```
http://localhost:8080
```
📌 **Paso 4: Obtener la contraseña de administrador**
```bash
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
```
📌 **Paso 5: Instalar plugins recomendados y configurar tu usuario**

✅ **¡Listo! Jenkins está instalado y funcionando!** 🎉

---

# **🔹 Creando un Pipeline en Jenkins para un Proyecto Java**

📌 **Paso 1: Crear un nuevo "Pipeline" en Jenkins**
- Ir a **Jenkins Dashboard** → **New Item** → **Pipeline** → **OK**

📌 **Paso 2: Escribir el `Jenkinsfile` en tu repositorio**

```groovy
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/tu-repo/mi-proyecto-java.git'
            }
        }
        
        stage('Compilar') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Ejecutar Pruebas') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Construir Docker') {
            steps {
                sh 'docker build -t mi-app-java .'
            }
        }

        stage('Desplegar en Kubernetes') {
            steps {
                sh 'kubectl apply -f k8s/deployment.yaml'
            }
        }
    }
}
```
📌 **Paso 3: Guardar y ejecutar el Pipeline en Jenkins**  
Cada vez que hagas un `git push`, Jenkins ejecutará automáticamente este flujo de trabajo. 🚀

✅ **¡Felicidades! Ahora Jenkins está compilando y desplegando tu aplicación automáticamente!**

---

# **🔹 Integración de Jenkins con GitHub para CI/CD**

📌 **Paso 1: Configurar Webhooks en GitHub**
- Ir a tu repositorio en GitHub
- Ir a **Settings** → **Webhooks** → **Add webhook**
- URL: `http://<tu-servidor>:8080/github-webhook/`
- Elegir `Just the push event`

📌 **Paso 2: Configurar Jenkins para GitHub**
- Instalar el plugin **GitHub Integration Plugin** en Jenkins
- En la configuración del pipeline, marcar **Build when a change is pushed to GitHub**

📌 **Paso 3: Confirmar que funciona**
- Hacer un `git push` en tu repo
- Jenkins debería detectar el cambio y ejecutar el pipeline automáticamente.

✅ **Ahora cada cambio en GitHub se construye y despliega automáticamente!** 🚀

---

# **🔹 Jenkins vs Otros CI/CD (GitHub Actions, GitLab CI, ArgoCD)** 🤔

| 🚀 Característica | 🏗️ Jenkins | 🔥 GitHub Actions | ☸ ArgoCD |
|------------------|------------|----------------|--------|
| **Infraestructura** | Servidor propio | 100% en GitHub | Kubernetes |
| **Facilidad de uso** | Requiere instalación | Fácil en GitHub | Más complejo |
| **Escalabilidad** | Puede ser pesado | Ligero | Despliegues GitOps |
| **Integración con Kubernetes** | Con plugins adicionales | Limitado | Nativo |

📌 **Si quieres flexibilidad total, usa Jenkins.**  
📌 **Si solo necesitas CI/CD básico para GitHub, usa GitHub Actions.**  
📌 **Si usas Kubernetes con GitOps, usa ArgoCD.**

---