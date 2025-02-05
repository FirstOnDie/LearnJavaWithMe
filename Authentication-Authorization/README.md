# **📌 Autenticación y Autorización con JWT en Spring Boot** 🔐🚀

📌 **¿Qué es JWT (JSON Web Token)?**  
**JWT (JSON Web Token)** es un **estándar de autenticación y autorización** utilizado para **proteger APIs** y asegurar que solo **usuarios autenticados** puedan acceder a recursos restringidos en una aplicación web.

💡 **JWT se usa en Spring Boot para:**  
✔ **Autenticación** → Identificar usuarios y generar un token seguro.  
✔ **Autorización** → Verificar los permisos antes de acceder a recursos.

---

# **📌 1️⃣ ¿Cómo Funciona JWT?** 🛡️

📌 **1. Autenticación (Login)**  
1️⃣ El usuario envía sus **credenciales (usuario y contraseña)** al servidor.  
2️⃣ El servidor valida las credenciales en la base de datos.  
3️⃣ Si las credenciales son **correctas**, el servidor genera un **JWT** con:
- **Datos del usuario** (ID, roles, permisos).
- **Tiempo de expiración**.
- **Firma digital** para evitar manipulaciones.  
  4️⃣ El JWT es enviado al **cliente**, que lo guarda en **localStorage**, **sessionStorage** o **cookies seguras**.

📌 **2. Autorización (Acceso a Rutas Protegidas)**  
1️⃣ Cada vez que el usuario accede a un recurso protegido, envía su **JWT** en los **headers HTTP** (`Authorization: Bearer <TOKEN>`).  
2️⃣ El servidor **verifica** que el token:
- **No haya expirado**.
- **No haya sido alterado** (firma válida).
- **Contenga los permisos adecuados**.  
  3️⃣ Si el token es **válido**, el usuario accede al recurso; si no, recibe un **401 Unauthorized**.

📌 **📊 Diagrama del Flujo JWT**
```
[Usuario] -> (Credenciales) -> [Servidor] <- (JWT) <-
           
[Usuario] -> (Solicitud con JWT) -> [Servidor] <- (Recurso Protegido) <-
```

---

# **📌 2️⃣ Estructura de un JWT** 🔍

📌 **Un JWT está compuesto por 3 partes (separadas por `.`):**
```
HEADER.PAYLOAD.SIGNATURE
```
📌 **Ejemplo de un Token JWT:**
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
eyJzdWIiOiJqZG9lIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTY5OTAwOTIwMH0.
K3L1F23YsN3-rHmgQVhzt6AekFfS5QebIW9rJbO8mYg
```

📌 **1️⃣ Header (Encabezado)**
```json
{
  "alg": "HS256", 
  "typ": "JWT"
}
```
- **`alg`** → Algoritmo de firma (**HS256**, RS256, etc.).
- **`typ`** → Tipo de token (**JWT**).

📌 **2️⃣ Payload (Datos del usuario)**
```json
{
  "sub": "jdoe",
  "roles": ["ROLE_USER"],
  "exp": 1699009200
}
```
- **`sub`** → Nombre del usuario.
- **`roles`** → Permisos del usuario.
- **`exp`** → Fecha de expiración en **Unix Timestamp**.

📌 **3️⃣ Signature (Firma digital)**
```bash
HMACSHA256(
    base64UrlEncode(header) + "." + base64UrlEncode(payload),  
    secret_key
)
```
✔ **Evita que el token sea modificado o falsificado.**

---

# **📌 3️⃣ Implementación de JWT en Spring Boot** 🏗️

📌 **1️⃣ Dependencias en `pom.xml`**
```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.11.5</version>
</dependency>
```

📌 **2️⃣ Clase para Generar y Validar Tokens JWT**
```java
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "clave_secreta_super_segura_1234567890123456"; // Mínimo 32 caracteres
    private static final long EXPIRATION_TIME = 86400000; // 1 día

    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public static String generarToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username) // Usuario
                .claim("role", role) // Agregar roles
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expira en 24h
                .signWith(key, SignatureAlgorithm.HS256) // Firma con clave secreta
                .compact(); // Generar el JWT
    }

    public static boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true; // Token válido
        } catch (JwtException e) {
            return false; // Token inválido
        }
    }
}
```

📌 **3️⃣ Proteger Rutas con Filtros en Spring Security**
```java
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remover "Bearer "
            if (!JwtUtil.validarToken(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response); // Continuar con la petición
    }
}
```

📌 **4️⃣ Configurar Spring Security para Usar JWT**
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No usa sesiones
                .and()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll() // Rutas públicas
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Solo Admins
                        .anyRequest().authenticated() // Todas las demás requieren autenticación
                )
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
```

---

# **📌 4️⃣ Probar JWT con Postman** 🛠️

📌 **1️⃣ Generar Token (Login)**
```
POST http://localhost:8080/auth/login
{
  "username": "admin",
  "password": "1234"
}
```
✔ **Respuesta esperada:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI..."
}
```

📌 **2️⃣ Acceder a Rutas Protegidas**
```
GET http://localhost:8080/admin/dashboard
Headers:
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI...
```
✔ **Si el token es válido → 200 OK**  
✔ **Si el token es inválido → 401 Unauthorized**

---