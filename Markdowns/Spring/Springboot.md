## **📌 Spring Boot + Hibernate (JPA)**
En la última clase hicimos un CRUD con **Hibernate puro**, pero en **proyectos reales** es más común usar **Spring Boot + JPA (Jakarta Persistence API)** para facilitar la gestión de bases de datos.

✔ **Ventajas de usar Spring Boot con JPA:**  
✅ No necesitamos `hibernate.cfg.xml`, Spring se encarga de la configuración.  
✅ Usa `Spring Data JPA`, que simplifica el acceso a la base de datos.  
✅ Proporciona **repositorios automáticos** (`JpaRepository`).  
✅ Menos código repetitivo y más productividad.

---

# **1️⃣ Crear un Proyecto Spring Boot con Hibernate**
📌 Puedes crear un proyecto desde:  
👉 [Spring Initializr](https://start.spring.io/)  
**Selecciona:**
- Spring Boot 3.1+
- Dependencias: **Spring Web**, **Spring Data JPA**, **MySQL Driver**

Si usas **Maven**, el archivo `pom.xml` debería verse así:
```xml
<dependencies>
    <!-- Spring Boot Starter para Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter para JPA (incluye Hibernate) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Driver para MySQL -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```
📌 **Esto instalará Spring Boot con JPA y MySQL.**

---

# **2️⃣ Configurar `application.properties`**
📌 **En `src/main/resources/application.properties`**, agregamos:
```properties
# Configuración de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/empresa
spring.datasource.username=root
spring.datasource.password=admin

# Dialecto de Hibernate
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

# Actualizar la estructura de la base de datos automáticamente
spring.jpa.hibernate.ddl-auto=update

# Mostrar las consultas SQL en la consola
spring.jpa.show-sql=true
```
📌 **Explicación:**  
✔ `spring.datasource.url` → Conexión a MySQL.  
✔ `spring.jpa.hibernate.ddl-auto=update` → Crea/modifica tablas automáticamente.  
✔ `spring.jpa.show-sql=true` → Muestra las consultas SQL generadas.

---

# **3️⃣ Crear la Entidad `Empleado.java`**
📌 **Creamos `Empleado.java` en `src/main/java/com/ejemplo/model`**
```java
package com.ejemplo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private int edad;

    public Empleado() {}

    public Empleado(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
}
```
📌 **Explicación:**  
✔ `@Entity` → Define una tabla en la base de datos.  
✔ `@Table(name = "empleados")` → Nombre de la tabla en BD.  
✔ `@Id` + `@GeneratedValue(strategy = GenerationType.IDENTITY)` → Clave primaria auto-incremental.  
✔ `@Column(nullable = false, length = 50)` → Define restricciones.

---

# **4️⃣ Crear un Repositorio con `JpaRepository`**
📌 **Spring Boot nos permite usar `JpaRepository` para CRUD automático.**  
📌 **Creamos `EmpleadoRepository.java` en `src/main/java/com/ejemplo/repository`**
```java
package com.ejemplo.repository;

import com.ejemplo.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
```
📌 **Explicación:**  
✔ `extends JpaRepository<Empleado, Long>` → Define un repositorio que maneja `Empleado`.  
✔ **Spring Data JPA genera automáticamente los métodos CRUD (`save()`, `findAll()`, `delete()`).**

---

# **5️⃣ Crear el Servicio `EmpleadoService.java`**
📌 **Creamos `EmpleadoService.java` en `src/main/java/com/ejemplo/service`**
```java
package com.ejemplo.service;

import com.ejemplo.model.Empleado;
import com.ejemplo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> obtenerPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }
}
```
📌 **Explicación:**  
✔ `@Service` → Indica que esta clase maneja la lógica de negocio.  
✔ `@Autowired` → Inyecta `EmpleadoRepository` automáticamente.  
✔ Métodos: `obtenerTodos()`, `obtenerPorId()`, `guardar()`, `eliminar()`.

---

# **6️⃣ Crear un Controlador REST**
📌 **Creamos `EmpleadoController.java` en `src/main/java/com/ejemplo/controller`**
```java
package com.ejemplo.controller;

import com.ejemplo.model.Empleado;
import com.ejemplo.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public List<Empleado> obtenerTodos() {
        return empleadoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Empleado> obtenerPorId(@PathVariable Long id) {
        return empleadoService.obtenerPorId(id);
    }

    @PostMapping
    public Empleado guardar(@RequestBody Empleado empleado) {
        return empleadoService.guardar(empleado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
    }
}
```
📌 **Explicación:**  
✔ `@RestController` → Define una API REST.  
✔ `@GetMapping`, `@PostMapping`, `@DeleteMapping` → Define rutas HTTP.

---

# **7️⃣ Probar la API REST con Postman**
📌 **Ejemplo de `POST` para crear un empleado:**  
📍 `POST http://localhost:8080/empleados`  
📩 **Body (JSON)**
```json
{
  "nombre": "Carlos",
  "edad": 30
}
```
📌 **Ejemplo de `GET` para obtener empleados:**  
📍 `GET http://localhost:8080/empleados`

📌 **Ejemplo de `DELETE` para eliminar un empleado:**  
📍 `DELETE http://localhost:8080/empleados/1`

---

# **📌 Ejercicio: CRUD de Productos con Spring Boot + Hibernate**
## **🎯 Objetivos:**
1️⃣ Crear una API REST que maneje **productos** con atributos `nombre`, `precio`, `stock`.  
2️⃣ Implementar operaciones **CRUD** (Crear, Leer, Actualizar, Eliminar).  
3️⃣ Probar los endpoints con **Postman** o navegador.

<details>
    <summary>Solución</summary>

## **1️⃣ Configurar el proyecto**
📌 **Dependencias en `pom.xml`**
```xml
<dependencies>
    <!-- Spring Boot Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot JPA (Hibernate) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- MySQL Driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```
📌 **Esto instala Hibernate, Spring Boot y el driver de MySQL.**

---

## **2️⃣ Configurar `application.properties`**
📌 **En `src/main/resources/application.properties`**
```properties
# Configuración de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/tienda
spring.datasource.username=root
spring.datasource.password=admin

# Dialecto de Hibernate
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

# Auto-crear tablas
spring.jpa.hibernate.ddl-auto=update

# Mostrar las consultas SQL en la consola
spring.jpa.show-sql=true
```
📌 **Explicación:**  
✔ `spring.datasource.url` → Conexión a la base de datos `tienda`.  
✔ `spring.jpa.hibernate.ddl-auto=update` → Crea/modifica las tablas automáticamente.  
✔ `spring.jpa.show-sql=true` → Muestra consultas SQL generadas.

---

## **3️⃣ Crear la Entidad `Producto.java`**
📌 **Creamos `Producto.java` en `src/main/java/com/ejemplo/model`**
```java
package com.ejemplo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int stock;

    public Producto() {}

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
```
📌 **Explicación:**  
✔ `@Entity` → Define una tabla en la base de datos.  
✔ `@Table(name = "productos")` → Nombre de la tabla en la BD.  
✔ `@Id` + `@GeneratedValue(strategy = GenerationType.IDENTITY)` → Clave primaria auto-incremental.  
✔ `@Column(nullable = false, length = 100)` → Define restricciones.

---

## **4️⃣ Crear el Repositorio `ProductoRepository.java`**
📌 **Creamos `ProductoRepository.java` en `src/main/java/com/ejemplo/repository`**
```java
package com.ejemplo.repository;

import com.ejemplo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
```
📌 **Explicación:**  
✔ `extends JpaRepository<Producto, Long>` → Define un repositorio que maneja `Producto`.  
✔ **Spring Data JPA genera automáticamente los métodos CRUD (`save()`, `findAll()`, `delete()`).**

---

## **5️⃣ Crear el Servicio `ProductoService.java`**
📌 **Creamos `ProductoService.java` en `src/main/java/com/ejemplo/service`**
```java
package com.ejemplo.service;

import com.ejemplo.model.Producto;
import com.ejemplo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
```
📌 **Explicación:**  
✔ `@Service` → Define un servicio de negocio.  
✔ `@Autowired` → Inyecta `ProductoRepository`.  
✔ Métodos: `obtenerTodos()`, `obtenerPorId()`, `guardar()`, `eliminar()`.

---

## **6️⃣ Crear el Controlador `ProductoController.java`**
📌 **Creamos `ProductoController.java` en `src/main/java/com/ejemplo/controller`**
```java
package com.ejemplo.controller;

import com.ejemplo.model.Producto;
import com.ejemplo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerTodos() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Producto> obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }
}
```
📌 **Explicación:**  
✔ `@RestController` → Define una API REST.  
✔ `@GetMapping`, `@PostMapping`, `@DeleteMapping` → Define rutas HTTP.

---

## **7️⃣ Probar la API REST con Postman**
📌 **Ejemplo de `POST` para crear un producto:**  
📍 `POST http://localhost:8080/productos`  
📩 **Body (JSON)**
```json
{
  "nombre": "Laptop",
  "precio": 1200.50,
  "stock": 10
}
```

📌 **Ejemplo de `GET` para obtener productos:**  
📍 `GET http://localhost:8080/productos`

📌 **Ejemplo de `DELETE` para eliminar un producto:**  
📍 `DELETE http://localhost:8080/productos/1`


</details>