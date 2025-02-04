# **📌 Día 17: Spring Security y OAuth2**
Hoy aprenderás:  
✅ **Autenticación con Spring Security y JWT**  
✅ **Configuración de OAuth2 con Keycloak**  
✅ **Protección de una API REST con OAuth2**  
✅ **Ejercicio: API protegida con OAuth2 y Keycloak**

---

📌 **¿Por qué es importante?**  
OAuth2 permite una **autenticación segura y centralizada**, ideal para aplicaciones con múltiples servicios y usuarios. **Keycloak** es un Identity Provider que facilita la gestión de usuarios y tokens **JWT** sin necesidad de implementar una autenticación personalizada.

---

# **1️⃣ ¿Qué es OAuth2 y cómo funciona con Keycloak?**

📌 **OAuth2 es un estándar de autenticación basado en tokens.**  
🔹 Permite a **terceros** acceder a recursos protegidos sin compartir credenciales.  
🔹 Utiliza **Access Tokens** (como JWT) para validar solicitudes.  
🔹 **Keycloak** actúa como **Identity Provider** (gestor de usuarios y permisos).

📌 **Flujo OAuth2 en Spring Security + Keycloak:**  
1️⃣ El usuario se **autentica** en Keycloak.  
2️⃣ Keycloak devuelve un **Access Token (JWT)**.  
3️⃣ El cliente usa este token en cada petición a la API.  
4️⃣ Spring Security **valida el token** antes de acceder al recurso.

📌 **Diferencia entre JWT y OAuth2:**  
| **JWT** (JSON Web Token) | **OAuth2** |
|-----------------|-------------|
| Es un formato de token | Es un protocolo de autenticación |
| Se puede usar sin OAuth2 | Usa JWT como **Access Token** |
| No tiene gestión de permisos | Maneja permisos con **Scopes y Roles** |

---

# **2️⃣ Instalando y Configurando Keycloak**
📌 **Descargar Keycloak:**  
🔗 https://www.keycloak.org/downloads

📌 **Ejecutar Keycloak en Docker:**
```sh
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev
```
📌 **Acceder a la consola de administración:**  
📍 **http://localhost:8080/** (usuario: `admin`, password: `admin`)

📌 **Crear un "Realm" y configurar OAuth2:**  
1️⃣ Ingresar a **Keycloak Admin Console**.  
2️⃣ Crear un nuevo **Realm** (`mi-realm`).  
3️⃣ Crear un **Cliente** (`mi-api-client`) y configurar:
- *Client Type:* `OpenID Connect`
- *Access Type:* `confidential`
- *Valid Redirect URIs:* `http://localhost:8081/*`  
  4️⃣ Crear un **Usuario** (`user1`) y asignar una contraseña.

---

# **3️⃣ Configuración de Spring Boot con Keycloak**
📌 **Agregamos dependencias en `pom.xml`**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

📌 **Configuramos `application.yml`**
```yaml
server:
  port: 8081

spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://localhost:8080/realms/mi-realm
```
✅ **Spring Security ahora usará Keycloak para validar JWTs.**

---

# **4️⃣ Protegiendo una API REST con OAuth2**
📌 **Creamos `SecurityConfig.java` para definir seguridad.**
```java
package com.ejemplo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/publico").permitAll()   // Endpoint público
                .requestMatchers("/protegido").authenticated() // Protegido por OAuth2
                .anyRequest().authenticated()
        ).oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter())));

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthConverter() {
        return new JwtAuthenticationConverter();
    }
}
```
✅ **Todos los endpoints están protegidos excepto `/publico`.**

📌 **Controlador `ApiController.java`**
```java
package com.ejemplo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/publico")
    public String endpointPublico() {
        return "Este endpoint es público.";
    }

    @GetMapping("/protegido")
    public String endpointProtegido(Authentication auth) {
        return "Acceso concedido a: " + auth.getName();
    }
}
```
✅ **`/publico` es accesible sin autenticación, `/protegido` requiere token OAuth2.**

---

# **5️⃣ Probando la API con OAuth2**
📌 **Obtener un Token OAuth2 desde Keycloak:**
```sh
curl -X POST "http://localhost:8080/realms/mi-realm/protocol/openid-connect/token" \
     -H "Content-Type: application/x-www-form-urlencoded" \
     -d "client_id=mi-api-client" \
     -d "grant_type=password" \
     -d "username=user1" \
     -d "password=12345" \
     -d "client_secret=MI_CLIENT_SECRET"
```
📌 **Ejemplo de Respuesta (Access Token)**
```json
{
  "access_token": "eyJhbGciOiJIUzI1...",
  "expires_in": 300,
  "token_type": "Bearer"
}
```
📌 **Acceder al endpoint protegido con el token:**
```sh
curl -X GET http://localhost:8081/api/protegido \
     -H "Authorization: Bearer eyJhbGciOiJIUzI1..."
```
📌 **Salida esperada:**
```
Acceso concedido a: user1
```
✅ **Keycloak autentica usuarios y devuelve un token válido para la API.**

---

# **6️⃣ Agregando Roles y Permisos con Keycloak**
📌 **Creamos roles en Keycloak:**  
1️⃣ Ir a `Roles` en Keycloak.  
2️⃣ Crear `ADMIN` y `USER`.  
3️⃣ Asignar `ADMIN` al usuario `user1`.

📌 **Modificar `SecurityConfig.java` para proteger por rol:**
```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin").hasRole("ADMIN")
            .requestMatchers("/user").hasRole("USER")
            .anyRequest().authenticated()
    ).oauth2ResourceServer(oauth2 -> oauth2.jwt());

    return http.build();
}
```
📌 **Nuevo endpoint protegido por roles (`ApiController.java`)**
```java
@GetMapping("/admin")
public String admin(Authentication auth) {
    return "Acceso ADMIN para: " + auth.getName();
}
```
✅ **Ahora solo los usuarios con rol `ADMIN` pueden acceder a `/admin`.**
