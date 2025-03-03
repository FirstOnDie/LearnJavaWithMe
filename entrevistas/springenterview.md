# **📌 Preguntas y Respuestas para Entrevista Técnica – Spring y Frameworks**

### ❓ **Pregunta:** ¿Qué es Spring Boot y cuáles son sus principales ventajas?
✅ **Respuesta:**  
Spring Boot es un **framework basado en Spring** que facilita la creación de aplicaciones empresariales con una **configuración mínima**.

📌 **Principales ventajas:**  
✔ **Configuración automática** con Spring Boot Starter.  
✔ **Servidor embebido** (Tomcat, Jetty, Undertow).  
✔ **Spring Boot Actuator** para monitoreo.  
✔ **Soporte para microservicios y cloud-native apps**.

📌 **Ejemplo de una aplicación Spring Boot mínima:**
```java
@SpringBootApplication
public class MiAplicacion {
    public static void main(String[] args) {
        SpringApplication.run(MiAplicacion.class, args);
    }
}
```

---

### ❓ **Pregunta:** ¿Qué es Spring Boot Starter y cómo ayuda en el desarrollo?
✅ **Respuesta:**  
Los **Spring Boot Starters** son paquetes preconfigurados que incluyen todas las dependencias necesarias para una funcionalidad específica.

📌 **Ejemplo de un `pom.xml` con `spring-boot-starter-web`:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
✅ **Esto permite crear APIs REST sin necesidad de configurar manualmente Servlets o Tomcat.**

---

### ❓ **Pregunta:** ¿Cómo maneja Spring Boot la configuración de la aplicación?
✅ **Respuesta:**  
Spring Boot permite configurar la aplicación mediante:
1. **`application.properties` o `application.yml`**
2. **Variables de entorno**
3. **Argumentos en línea de comandos**
4. **Perfiles de configuración (`@Profile`)**

📌 **Ejemplo de configuración en `application.yml`:**
```yaml
server:
  port: 8081
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mi_db
    username: user
    password: pass
```

---

### ❓ **Pregunta:** ¿Cómo funciona Spring Security y cuál es su flujo de autenticación?
✅ **Respuesta:**  
Spring Security es un **framework de seguridad** que maneja autenticación y autorización en aplicaciones Spring.

📌 **Flujo de autenticación en Spring Security:**
1. El usuario ingresa credenciales (usuario/contraseña).
2. Spring Security las valida con un `UserDetailsService`.
3. Si son correctas, genera un `SecurityContext`.
4. Protege rutas con filtros de seguridad y autorizaciones (`@PreAuthorize`).

📌 **Ejemplo de configuración de seguridad básica:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(withDefaults());
        return http.build();
    }
}
```

---

### ❓ **Pregunta:** ¿Qué es JWT y cómo se usa en Spring Security?
✅ **Respuesta:**  
JWT (**JSON Web Token**) es un estándar para **autenticación sin estado** en APIs REST.

📌 **Flujo de autenticación con JWT:**
1. El usuario se autentica y obtiene un **token JWT**.
2. En cada solicitud, envía el **token en el header**.
3. Spring Security valida el token y permite el acceso.

📌 **Ejemplo de generación de JWT:**
```java
String token = Jwts.builder()
    .setSubject(user.getUsername())
    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
    .signWith(SignatureAlgorithm.HS512, "secreto")
    .compact();
```

📌 **Ejemplo de interceptor que valida JWT:**
```java
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        
        String token = request.getHeader("Authorization");
        if (token != null && validarToken(token)) {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
        }
        chain.doFilter(request, response);
    }
}
```

---

### ❓ **Pregunta:** ¿Cuál es la diferencia entre autenticación y autorización en Spring Security?
✅ **Respuesta:**  
✔ **Autenticación:** Verifica **quién eres** (usuario y contraseña, JWT, OAuth).  
✔ **Autorización:** Verifica **qué puedes hacer** en la aplicación.

📌 **Ejemplo de autorización con `@PreAuthorize`:**
```java
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/admin")
public String accesoAdmin() {
    return "Solo los administradores pueden ver esto";
}
```

---

### ❓ **Pregunta:** ¿Qué es Spring WebFlux y en qué se diferencia de Spring MVC?
✅ **Respuesta:**  
Spring WebFlux es el **módulo de Spring para programación reactiva**, basado en **Project Reactor**.

📌 **Diferencias clave:**  

| Característica      | Spring MVC (Imperativo) | Spring WebFlux (Reactivo) |
|--------------------|----------------------|------------------------|
| Modelo de ejecución | Bloqueante (1 hilo por request) | No bloqueante (event-loop) |
| Performance       | Más lento en alta concurrencia | Escalable en grandes volúmenes |
| Uso de Threads    | Usa más hilos         | Usa menos hilos (más eficiente) |

---

### ❓ **Pregunta:** ¿Cómo se define un controlador reactivo con Spring WebFlux?
✅ **Respuesta:**  
Se usa `Mono<T>` para devolver **un solo elemento** y `Flux<T>` para **múltiples elementos**.

📌 **Ejemplo de controlador WebFlux:**
```java
@RestController
@RequestMapping("/productos")
public class ProductoController {
    
    @GetMapping("/{id}")
    public Mono<Producto> obtenerProducto(@PathVariable String id) {
        return productoService.buscarPorId(id);
    }

    @GetMapping
    public Flux<Producto> obtenerTodos() {
        return productoService.buscarTodos();
    }
}
```

---

### ❓ **Pregunta:** ¿Cuál es la diferencia entre `Mono` y `Flux` en WebFlux?
✅ **Respuesta:**  
✔ **`Mono<T>`** → Devuelve **un solo elemento o vacío**.  
✔ **`Flux<T>`** → Devuelve **0 a N elementos**.

📌 **Ejemplo de `Mono` y `Flux`:**
```java
Mono<String> mono = Mono.just("Hola WebFlux!");
Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
```

---

### ❓ **Pregunta:** ¿Cómo manejar backpressure en WebFlux?
✅ **Respuesta:**  
El **backpressure** controla la cantidad de datos enviados para evitar sobrecarga. Se usa `limitRate()`, `buffer()`, o `onBackpressureDrop()`.

📌 **Ejemplo de backpressure con `limitRate()`:**
```java
Flux.range(1, 100)
    .limitRate(10)
    .subscribe(System.out::println);
```

📌 **Ejemplo de `onBackpressureDrop()`:**
```java
Flux.interval(Duration.ofMillis(1))
    .onBackpressureDrop()
    .subscribe(System.out::println);
```

---
