# **📌 Día 16: Spring Data JPA y Hibernate Avanzado**
Hoy aprenderás:  
✅ **Consultas avanzadas con Criteria API y JPQL**  
✅ **Paginación y búsqueda dinámica con `Specification`**  
✅ **Ejercicio: Sistema de Reportes Financieros**

---

📌 **¿Por qué es importante?**  
**Spring Data JPA** y **Hibernate** simplifican el acceso a bases de datos en Java, permitiendo crear consultas avanzadas sin escribir SQL manualmente. Hoy aprenderás cómo hacer **búsquedas dinámicas, paginación y consultas personalizadas**.

---

# **1️⃣ Configurando Spring Data JPA y Hibernate**
📌 **Dependencias necesarias (`pom.xml`)**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```
📌 **Configuramos `application.yml` para usar H2 (base de datos en memoria)**
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:mi_db
    driverClassName: org.h2.Driver
    username: sa
    password:
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
```
✅ **Con `show-sql: true`, vemos las consultas en la consola.**

---

# **2️⃣ Creando una Entidad y Repositorio**
📌 **Modelo `Transaccion.java` (Entidad de reportes financieros)**
```java
package com.ejemplo.finanzas.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transacciones")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    
    @Column(nullable = false)
    private BigDecimal monto;
    
    @Column(nullable = false)
    private LocalDate fecha;
    
    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipo;
}
```
📌 **Enumeración `TipoTransaccion.java`**
```java
package com.ejemplo.finanzas.model;

public enum TipoTransaccion {
    INGRESO, GASTO
}
```
📌 **Repositorio `TransaccionRepository.java`**
```java
package com.ejemplo.finanzas.repository;

import com.ejemplo.finanzas.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    // Buscar transacciones mayores a cierto monto
    List<Transaccion> findByMontoGreaterThan(BigDecimal monto);

    // Buscar transacciones por tipo y monto mínimo
    List<Transaccion> findByTipoAndMontoGreaterThan(TipoTransaccion tipo, BigDecimal monto);

    // Consulta JPQL para buscar transacciones en un rango de fechas
    @Query("SELECT t FROM Transaccion t WHERE t.fecha BETWEEN :inicio AND :fin")
    List<Transaccion> findByRangoFechas(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
}
```
✅ **JPQL (`@Query`) permite consultas personalizadas sin SQL nativo.**

---

# **3️⃣ Criteria API: Consultas Dinámicas**
📌 **`Criteria API` permite construir consultas dinámicas en tiempo de ejecución.**

📌 **Clase `TransaccionSpecification.java`**
```java
package com.ejemplo.finanzas.specification;

import com.ejemplo.finanzas.model.Transaccion;
import com.ejemplo.finanzas.model.TipoTransaccion;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransaccionSpecification {
    public static Specification<Transaccion> buscarTransacciones(
            TipoTransaccion tipo, BigDecimal montoMin, LocalDate fechaInicio, LocalDate fechaFin) {
        return (Root<Transaccion> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (tipo != null) {
                predicates.add(cb.equal(root.get("tipo"), tipo));
            }
            if (montoMin != null) {
                predicates.add(cb.greaterThan(root.get("monto"), montoMin));
            }
            if (fechaInicio != null && fechaFin != null) {
                predicates.add(cb.between(root.get("fecha"), fechaInicio, fechaFin));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
```
✅ **Esto permite construir consultas avanzadas basadas en filtros opcionales.**

---

# **4️⃣ Paginación y Ordenación**
📌 **Modificamos `TransaccionRepository.java` para paginar resultados.**
```java
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

Page<Transaccion> findByTipo(TipoTransaccion tipo, Pageable pageable);
```
📌 **Ejemplo de uso:**
```java
Pageable pageable = PageRequest.of(0, 5, Sort.by("fecha").descending());
Page<Transaccion> pagina = transaccionRepository.findByTipo(TipoTransaccion.INGRESO, pageable);
```
✅ **Devuelve 5 transacciones más recientes.**

---

# **5️⃣ Creando el Controlador REST**
📌 **Controlador `TransaccionController.java`**
```java
package com.ejemplo.finanzas.controller;

import com.ejemplo.finanzas.model.Transaccion;
import com.ejemplo.finanzas.repository.TransaccionRepository;
import com.ejemplo.finanzas.specification.TransaccionSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {
    private final TransaccionRepository transaccionRepository;

    public TransaccionController(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    @GetMapping
    public List<Transaccion> listarTodas() {
        return transaccionRepository.findAll();
    }

    @GetMapping("/buscar")
    public List<Transaccion> buscar(
            @RequestParam(required = false) TipoTransaccion tipo,
            @RequestParam(required = false) BigDecimal montoMin,
            @RequestParam(required = false) LocalDate fechaInicio,
            @RequestParam(required = false) LocalDate fechaFin) {

        Specification<Transaccion> spec = TransaccionSpecification.buscarTransacciones(tipo, montoMin, fechaInicio, fechaFin);
        return transaccionRepository.findAll(spec);
    }

    @GetMapping("/paginadas")
    public Page<Transaccion> listarPaginadas(@RequestParam int page, @RequestParam int size) {
        return transaccionRepository.findAll(PageRequest.of(page, size));
    }
}
```
✅ **Los usuarios pueden filtrar transacciones y paginarlas dinámicamente.**

---

# **6️⃣ Pruebas con `curl` o Postman**
📌 **Listar todas las transacciones:**
```sh
curl -X GET http://localhost:8080/transacciones
```
📌 **Buscar transacciones mayores a $1000 en un rango de fechas:**
```sh
curl -X GET "http://localhost:8080/transacciones/buscar?montoMin=1000&fechaInicio=2023-01-01&fechaFin=2023-12-31"
```
📌 **Obtener transacciones paginadas (página 0, 5 resultados):**
```sh
curl -X GET "http://localhost:8080/transacciones/paginadas?page=0&size=5"
```
