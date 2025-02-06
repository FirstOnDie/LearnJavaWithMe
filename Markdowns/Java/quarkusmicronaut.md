# 🚀 **Quarkus y Micronaut**

Si buscas **alternativas más rápidas y ligeras** a **Spring Boot**, es el momento de conocer **Quarkus** y **Micronaut**. Ambos frameworks están diseñados para **microservicios de alto rendimiento**, tiempos de inicio ultra bajos y **soporte nativo con GraalVM**.

---

# **📌 ¿Qué son Quarkus y Micronaut?**
Son **frameworks modernos para Java** que permiten crear **microservicios optimizados** para la nube y entornos **serverless**.

| Característica      | Quarkus  | Micronaut |
|--------------------|----------|-----------|
| **Optimizado para** | Kubernetes, Serverless | Cloud-native, Serverless |
| **Tiempo de inicio** | ~0.03s con GraalVM | ~0.3s |
| **Consumo de memoria** | Bajo (~25MB en nativo) | Medio (~30-40MB) |
| **Compatibilidad** | **Jakarta EE, RESTEasy, Hibernate, Mutiny** | **Spring, JPA, gRPC, Serverless** |
| **¿Compila a nativo?** | ✅ Sí, con GraalVM | ✅ Sí, con GraalVM |
| **¿Soporta programación reactiva?** | ✅ Sí (Mutiny, Vert.x, RxJava) | ✅ Sí (RxJava, Reactor) |

✅ Ambos frameworks están diseñados para **arrancar más rápido** y **usar menos memoria** que Spring Boot.

---

# **📌 ¿Cuándo usar Quarkus o Micronaut?**
✔ **Cuando necesitas microservicios ultrarrápidos**.  
✔ **Para aplicaciones serverless en AWS Lambda o Google Cloud Run**.  
✔ **Cuando buscas compatibilidad con Spring Boot sin overhead**.  
✔ **Para aplicaciones reactivas y eventos asíncronos**.  
✔ **Cuando usas GraalVM para compilar a código nativo**.

---

# **📌 Instalación y Configuración**
### 🔹 **1️⃣ Instalar Quarkus**
```bash
mvn io.quarkus.platform:quarkus-maven-plugin:3.0.0.Final:create \
    -DprojectGroupId=com.miapp \
    -DprojectArtifactId=mi-app \
    -DclassName="org.miapp.HolaResource" \
    -Dpath="/hola"
```
🔹 **Iniciar el servidor**:
```bash
cd mi-app
./mvnw quarkus:dev
```
💡 **Quarkus soporta "live coding"**, lo que significa que puedes modificar el código sin reiniciar la aplicación.

---

### 🔹 **2️⃣ Instalar Micronaut**
```bash
mn create-app com.miapp --features=graalvm
cd com.miapp
./gradlew run
```
📌 **Micronaut genera menos archivos `.class`** en comparación con Spring Boot, lo que reduce el uso de memoria.

---

# **📌 Ejemplo 1: Crear un API REST**
### **🔹 Quarkus:**
```java
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hello")
public class HelloResource {
    
    @GET
    public String hello() {
        return "¡Hola desde Quarkus!";
    }
}
```
✅ **Arranca en menos de 1 segundo con GraalVM**.

---

### **🔹 Micronaut:**
```java
import io.micronaut.http.annotation.*;

@Controller("/hello")
public class HelloController {
    
    @Get
    public String hello() {
        return "¡Hola desde Micronaut!";
    }
}
```
✅ **Micronaut usa inyección de dependencias sin reflexión, lo que mejora el rendimiento**.

---

# **📌 Programación Reactiva con Quarkus y Micronaut**
Ambos frameworks soportan **programación reactiva** con **RxJava, Reactor y Mutiny**.

📌 **Ejemplo: Endpoint reactivo con Quarkus**
```java
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;

@Path("/reactivo")
public class ReactivoResource {
    
    @GET
    public Uni<String> obtenerMensaje() {
        return Uni.createFrom().item("¡Hola de forma reactiva!");
    }
}
```
📌 **Ejemplo: Endpoint reactivo con Micronaut**
```java
import io.micronaut.http.annotation.*;
import reactor.core.publisher.Mono;

@Controller("/reactivo")
public class ReactivoController {
    
    @Get
    public Mono<String> obtenerMensaje() {
        return Mono.just("¡Hola de forma reactiva!");
    }
}
```
✅ **¿Beneficio?** Ambos frameworks **usan menos memoria y escalan mejor** en arquitecturas asíncronas.

---

# **📌 Integración con Bases de Datos (JPA/Hibernate)**
Ambos soportan **Hibernate y Panache en Quarkus**, y **Micronaut Data** en Micronaut.

📌 **Ejemplo: Entidad JPA en Quarkus**
```java
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Persona extends PanacheEntity {
    public String nombre;
}
```
📌 **Ejemplo: Repositorio en Micronaut**
```java
import io.micronaut.data.annotation.*;
import io.micronaut.data.jpa.repository.*;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
```
✅ **¿Beneficio?** Quarkus y Micronaut eliminan la necesidad de `@Transactional` en muchos casos, optimizando las consultas SQL.

---

# **📌 Microservicios con Kubernetes**
Ambos frameworks están optimizados para **Kubernetes y OpenShift**.

📌 **Ejemplo: Desplegar en Kubernetes con Quarkus**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mi-app
spec:
  template:
    spec:
      containers:
        - name: mi-app
          image: mi-app:latest
          ports:
            - containerPort: 8080
```
📌 **Ejemplo: Desplegar en Kubernetes con Micronaut**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mi-app-micronaut
spec:
  template:
    spec:
      containers:
        - name: mi-app
          image: mi-app:latest
          ports:
            - containerPort: 8080
```
✅ **¿Beneficio?** Ambas opciones se integran fácilmente con **Docker, Kubernetes y OpenShift**.

---

# **📌 Comparación: ¿Quarkus o Micronaut?**
| Característica       | Quarkus  | Micronaut |
|----------------------|---------|-----------|
| **Uso de memoria**   | Menos memoria con GraalVM (~25MB) | Ligeramente más (~30MB) |
| **Tiempo de inicio** | ~0.03s (con GraalVM) | ~0.3s |
| **Compatibilidad**   | Jakarta EE, RESTEasy, Hibernate | Spring Boot, Reactor, JPA |
| **Soporte Reactivo** | ✅ Mutiny, RxJava | ✅ Reactor, RxJava |
| **Serverless**       | ✅ AWS Lambda, Azure Functions | ✅ AWS Lambda, Google Cloud |
| **Integración con Kubernetes** | ✅ Nativa | ✅ Nativa |

✅ **Conclusión:**
- **Usa Quarkus** si vienes de **Jakarta EE o JBoss** y quieres lo más **rápido y liviano**.
- **Usa Micronaut** si vienes de **Spring Boot** y quieres algo más **optimizado**.

---

# **📌 Ventajas y Desventajas**
### ✅ **Ventajas**
✔ **Tiempo de inicio ultrarrápido**.  
✔ **Menor consumo de memoria**.  
✔ **Compilación nativa con GraalVM**.  
✔ **Mejor rendimiento en Serverless**.  
✔ **Compatibilidad con Spring Boot, JPA, Hibernate**.

### ❌ **Desventajas**
❌ **Curva de aprendizaje** si vienes de Spring Boot.  
❌ **Menos librerías y soporte en comparación con Spring Boot**.  
❌ **Compilación nativa con GraalVM puede ser compleja**.

---

# **📌 Conclusión**
🔹 **Quarkus y Micronaut** son **las mejores alternativas a Spring Boot** si buscas **rendimiento y optimización en la nube**.  
🔹 Ambos frameworks son ideales para **Kubernetes, microservicios y aplicaciones serverless**.  
🔹 **Si buscas compatibilidad con Spring Boot**, usa **Micronaut**.  
🔹 **Si buscas lo más rápido y ligero**, usa **Quarkus con GraalVM**.

---
