# **📌 Día 28: Despliegue de Aplicaciones Java (JAR, Instaladores, Docker)** 🚀⚙️

📌 **Objetivo del día:**  
✅ Aprender a **generar archivos ejecutables (JAR, instaladores)** en Java.  
✅ Preparar un proyecto para su despliegue con **Docker**.  
✅ **Ejercicio:** Crear un **JAR ejecutable** y un **contenedor Docker** para nuestra aplicación.

---

## **1️⃣ Generar un Archivo Ejecutable (JAR)** 📦

📌 **Un archivo `JAR` (Java ARchive) permite distribuir y ejecutar aplicaciones Java.**  
✔ Contiene **código compilado (`.class`) y dependencias**.  
✔ Permite ejecutar el programa con **`java -jar archivo.jar`**.

📌 **Pasos para generar un `JAR` en Maven:**  
1️⃣ **Añadir el plugin de compilación en `pom.xml`**:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>17</source> <!-- Cambiar a tu versión de Java -->
                <target>17</target>
            </configuration>
        </plugin>

        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <version>3.2.0</version>
            <configuration>
                <archive>
                    <manifest>
                        <mainClass>Main</mainClass> <!-- Nombre de tu clase principal -->
                    </manifest>
                </archive>
            </configuration>
        </plugin>
    </plugins>
</build>
```
2️⃣ **Compilar y generar el JAR:**
```sh
mvn clean package
```
3️⃣ **Ejecutar el JAR generado:**
```sh
java -jar target/mi-aplicacion.jar
```
✅ **Nuestra aplicación ahora es ejecutable en cualquier computadora con Java instalado.**

---

## **2️⃣ Crear un Instalador para la Aplicación** 🏗️

📌 **Para distribuir nuestra aplicación como un instalador, usamos `jpackage` (Java 14+).**  
✔ Genera un **instalador `.exe` (Windows), `.dmg` (Mac), `.deb/.rpm` (Linux)**.

📌 **Ejemplo de uso:**
```sh
jpackage --input target/ --name MiAplicacion --main-jar mi-aplicacion.jar --type exe
```
✅ **Salida esperada:** Un instalador ejecutable para Windows.

---

## **3️⃣ Crear un Contenedor Docker para Java** 🐳

📌 **Docker permite ejecutar aplicaciones en cualquier entorno sin problemas de compatibilidad.**  
✔ **Empaqueta la aplicación y sus dependencias** en un solo contenedor.  
✔ **Facilita el despliegue en servidores, nube o entornos productivos.**

📌 **1️⃣ Crear un `Dockerfile` en la raíz del proyecto:**
```dockerfile
# Imagen base de Java
FROM openjdk:17-jdk-slim

# Copiar el archivo JAR a la imagen
COPY target/mi-aplicacion.jar /app.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app.jar"]
```
📌 **2️⃣ Construir la imagen Docker:**
```sh
docker build -t mi-aplicacion .
```
📌 **3️⃣ Ejecutar la aplicación dentro del contenedor:**
```sh
docker run -p 8080:8080 mi-aplicacion
```
✅ **Nuestra aplicación ahora corre en un contenedor Docker.**

---

# **📌 Ejercicio Final: Generar un JAR y un Contenedor Docker** 🎯

📌 **Objetivo:**  
✔ Generar un `JAR` ejecutable de nuestra aplicación de inventario.  
✔ Crear una imagen **Docker** para ejecutarla en cualquier entorno.  
✔ Publicar la imagen en **Docker Hub** (opcional).

---

<details>
    <summary>Solución</summary>

## **1️⃣ Generar un JAR Ejecutable** 📦

📌 **1️⃣ Asegurar que el `pom.xml` tiene el `maven-jar-plugin`**  
Si estás usando **Maven**, agrega esto dentro de `<build>` en `pom.xml`:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <version>3.2.0</version>
            <configuration>
                <archive>
                    <manifest>
                        <mainClass>Main</mainClass> <!-- Reemplazar por tu clase principal -->
                    </manifest>
                </archive>
            </configuration>
        </plugin>
    </plugins>
</build>
```

📌 **2️⃣ Compilar el proyecto y generar el `.jar`**  
Ejecutar en la terminal dentro del directorio del proyecto:
```sh
mvn clean package
```
✅ **Salida esperada:**
```
[INFO] Building jar: /target/inventario-app.jar
```

📌 **3️⃣ Ejecutar el JAR manualmente para probarlo:**
```sh
java -jar target/inventario-app.jar
```
✅ **Si todo está bien, la aplicación se ejecutará correctamente.**

---

## **2️⃣ Crear un Contenedor Docker para la Aplicación** 🐳

📌 **1️⃣ Crear un archivo `Dockerfile` en la raíz del proyecto:**
```dockerfile
# 1️⃣ Usar la imagen oficial de OpenJDK 17
FROM openjdk:17-jdk-slim

# 2️⃣ Copiar el JAR generado a la imagen
COPY target/inventario-app.jar /app.jar

# 3️⃣ Comando para ejecutar la aplicación dentro del contenedor
CMD ["java", "-jar", "/app.jar"]
```

📌 **2️⃣ Construir la imagen de Docker:**  
Ejecuta en la terminal dentro del directorio del proyecto:
```sh
docker build -t inventario-app .
```
✅ **Salida esperada:**
```
Successfully built <image_id>
Successfully tagged inventario-app:latest
```

📌 **3️⃣ Ejecutar la imagen en un contenedor:**
```sh
docker run -p 8080:8080 inventario-app
```
✅ **Ahora la aplicación está corriendo dentro del contenedor en el puerto 8080.**

---

## **3️⃣ (Opcional) Subir la Imagen a Docker Hub** 🌍

📌 **1️⃣ Iniciar sesión en Docker Hub:**
```sh
docker login
```
📌 **2️⃣ Etiquetar la imagen con tu nombre de usuario:**
```sh
docker tag inventario-app <tu_usuario>/inventario-app:latest
```
📌 **3️⃣ Subir la imagen a Docker Hub:**
```sh
docker push <tu_usuario>/inventario-app:latest
```
✅ **Ahora cualquier persona puede descargar tu aplicación con:**
```sh
docker pull <tu_usuario>/inventario-app:latest
docker run -p 8080:8080 <tu_usuario>/inventario-app
```

---

</details>