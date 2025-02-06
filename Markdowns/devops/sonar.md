# 🚀 **SonarQube y SonarCloud en Java** 🔍🛠️

## **¿Qué es SonarQube y SonarCloud?**
SonarQube y SonarCloud son herramientas que analizan el **código fuente** de tu aplicación Java para detectar **errores, vulnerabilidades, malas prácticas y problemas de calidad**.

✅ **SonarQube** → Se instala en servidores propios y se usa en entornos locales o empresariales.  
✅ **SonarCloud** → Es la versión en la nube, sin necesidad de instalación.

📌 **En pocas palabras, SonarQube/SonarCloud es como un "detector de problemas" que te dice si tu código está limpio, seguro y optimizado.**

---

# **🔹 ¿Por qué usar SonarQube en proyectos Java?** 🧐

| 🔥 Beneficio | 💡 ¿Por qué es importante? |
|-------------|---------------------------|
| 📊 **Mejora la calidad del código** | Encuentra errores, código duplicado y problemas de seguridad. |
| 🔐 **Detecta vulnerabilidades** | Evita problemas de seguridad antes de que lleguen a producción. |
| 🎯 **Sigue buenas prácticas** | Te ayuda a escribir código limpio y mantenible siguiendo estándares. |
| 🚀 **Integración con CI/CD** | Se integra con GitHub, GitLab, Jenkins, Azure DevOps, etc. |
| 🔍 **Mejora el rendimiento del equipo** | Reduce el tiempo de depuración y mejora la colaboración. |

📌 **Si quieres que tu código sea más limpio, seguro y eficiente, SonarQube es una herramienta imprescindible.**

---

# **🔹 Instalación de SonarQube en Local (Modo Manual)** 🛠️

Si quieres probar SonarQube en tu máquina local, sigue estos pasos:

📌 **Paso 1: Descargar e instalar SonarQube**
```bash
wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-9.9.2.zip
unzip sonarqube-9.9.2.zip
cd sonarqube-9.9.2/bin/linux-x86-64
```
📌 **Paso 2: Iniciar SonarQube**
```bash
./sonar.sh start
```
📌 **Paso 3: Acceder a la interfaz web**  
Abre en el navegador:
```
http://localhost:9000
```
Usuario y contraseña predeterminados: **admin / admin**

✅ ¡Listo! Ahora tienes SonarQube corriendo en tu máquina. 🎉

---

# **🔹 Integrar SonarQube en un Proyecto Java (Maven o Gradle)**

📌 **Si usas Maven, agrega esto a tu `pom.xml`:**
```xml
<properties>
    <sonar.projectKey>mi-proyecto-java</sonar.projectKey>
    <sonar.host.url>http://localhost:9000</sonar.host.url>
    <sonar.login>admin</sonar.login>
</properties>
```
📌 **Ejecutar el análisis:**
```bash
mvn clean verify sonar:sonar
```
📌 **Si usas Gradle, agrega esto a `build.gradle`:**
```groovy
plugins {
    id "org.sonarqube" version "4.0.0.2929"
}

sonarqube {
    properties {
        property "sonar.projectKey", "mi-proyecto-java"
        property "sonar.host.url", "http://localhost:9000"
        property "sonar.login", "admin"
    }
}
```
📌 **Ejecutar el análisis:**
```bash
gradle sonarqube
```
✅ **Ahora SonarQube analizará tu código y te mostrará los problemas encontrados en la interfaz web.** 🎯

---

# **🔹 Integración de SonarCloud con GitHub Actions (CI/CD en la nube)** 🌩️

📌 **Si no quieres instalar SonarQube, usa SonarCloud (la versión en la nube).**

📌 **Paso 1: Crear una cuenta en [SonarCloud](https://sonarcloud.io/)**  
📌 **Paso 2: Obtener tu token de autenticación en SonarCloud.**  
📌 **Paso 3: Agregar el token a tu repositorio en GitHub como `SONAR_TOKEN`.**

📌 **Paso 4: Agregar un workflow en `.github/workflows/sonar.yml`**
```yaml
name: SonarCloud Analysis
on:
  push:
    branches:
      - main
jobs:
  sonar:
    runs-on: ubuntu-latest
    steps:
      - name: Clonar repositorio
        uses: actions/checkout@v2
      - name: Configurar JDK 17
        uses: actions/setup-java@v1
        with:
          java-version: 17
      - name: Construir y analizar con Maven
        run: mvn clean verify sonar:sonar -Dsonar.projectKey=mi-proyecto -Dsonar.organization=mi-org -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=$SONAR_TOKEN
        env:
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
```
✅ **Cada vez que hagas un push, SonarCloud analizará el código automáticamente.** 🎯

---

# **🔹 ¿Qué problemas detecta SonarQube?** 🤯

| 🛑 Problema | 🔎 Explicación |
|------------|--------------|
| 🐛 **Bugs** | Código que puede causar errores en ejecución. |
| 🔥 **Vulnerabilidades** | Riesgos de seguridad como inyecciones SQL o fugas de datos. |
| 🚨 **Code Smells** | Código mal escrito o difícil de entender. |
| 🔄 **Duplicaciones** | Fragmentos de código repetidos. |
| 🎯 **Cobertura de Pruebas** | Evalúa qué porcentaje del código está cubierto por tests. |

📌 **Ejemplo de un problema detectado:**
```java
public void dividir(int a, int b) {
    System.out.println(a / b); // ERROR: No maneja división por cero
}
```
🔎 **SonarQube te dirá:** _"Posible división por cero detectada"._

---

# **🔹 SonarQube vs SonarCloud – ¿Cuál usar?** 🤔

| Característica | SonarQube | SonarCloud |
|--------------|-----------|-----------|
| 🏠 **Dónde corre** | En un servidor propio | En la nube |
| 🚀 **Instalación** | Manual | Sin instalación |
| 🔍 **Análisis** | Código local y CI/CD | Solo en CI/CD |
| 🔐 **Seguridad** | Control total | Basado en la nube |
| 💰 **Precio** | Gratis (Community) o pago | Gratis para proyectos open-source |

📌 **Si trabajas en una empresa con código privado, usa SonarQube.**  
📌 **Si trabajas en GitHub con código público, SonarCloud es la mejor opción.**

---