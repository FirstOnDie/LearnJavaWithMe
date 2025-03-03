# **📌 Preguntas y Respuestas para Entrevista Técnica - Java Avanzado**

### ❓ **Pregunta:** ¿Qué es el polimorfismo en Java y cuáles son sus tipos?
✅ **Respuesta:**  
El **polimorfismo** permite que un mismo método o interfaz se comporte de diferentes maneras según la instancia que lo implemente. Existen dos tipos:
1. **Polimorfismo de sobrecarga (Compile-time Polymorphism):** Múltiples métodos con el mismo nombre, pero con diferentes parámetros.
2. **Polimorfismo de sobrescritura (Runtime Polymorphism):** Cuando una subclase redefine un método de su superclase.

📌 **Ejemplo de polimorfismo de sobrecarga:**
```java
class Calculadora {
    public int sumar(int a, int b) { return a + b; }
    public double sumar(double a, double b) { return a + b; }
}
```
📌 **Ejemplo de polimorfismo de sobrescritura:**
```java
class Animal { void hacerSonido() { System.out.println("Sonido genérico"); } }
class Perro extends Animal { void hacerSonido() { System.out.println("Guau!"); } }
```

---

### ❓ **Pregunta:** ¿Qué son las anotaciones en Java y para qué se usan?
✅ **Respuesta:**  
Las **anotaciones** son metadatos que proporcionan información adicional sobre el código sin afectarlo directamente. Se usan para **configurar frameworks, generar código, validar reglas, entre otros usos**.

📌 **Ejemplo de anotación personalizada:**
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface InfoMetodo {
    String descripcion();
}
```

📌 **Ejemplo de uso en Spring Boot:**
```java
@Service
public class MiServicio { }
```

---

### ❓ **Pregunta:** ¿Cuál es la diferencia entre Iterator y ListIterator en Java?
✅ **Respuesta:**
- **Iterator:** Solo permite recorrer la colección en **una dirección** (hacia adelante).
- **ListIterator:** Permite recorrer en **ambas direcciones** (hacia adelante y hacia atrás).

📌 **Ejemplo de `Iterator`:**
```java
Iterator<String> it = lista.iterator();
while(it.hasNext()) { System.out.println(it.next()); }
```
📌 **Ejemplo de `ListIterator`:**
```java
ListIterator<String> it = lista.listIterator(lista.size());
while(it.hasPrevious()) { System.out.println(it.previous()); }
```

---

### ❓ **Pregunta:** Explica el patrón Singleton y cuándo es útil.
✅ **Respuesta:**  
El **patrón Singleton** garantiza que una clase tenga **una única instancia** en toda la aplicación. Es útil para manejar **conexiones a bases de datos, caches y configuraciones globales**.

📌 **Ejemplo de Singleton en Java:**
```java
public class Singleton {
    private static Singleton instancia;
    private Singleton() { }
    public static Singleton getInstance() {
        if (instancia == null) { instancia = new Singleton(); }
        return instancia;
    }
}
```

---

### ❓ **Pregunta:** ¿Cómo funciona `@Scheduled` en Spring Boot?
✅ **Respuesta:**  
Spring Boot permite ejecutar tareas programadas usando `@Scheduled`.

📌 **Ejemplo de una tarea que corre cada 10 segundos:**
```java
@Scheduled(fixedRate = 10000)
public void tareaProgramada() {
    System.out.println("Ejecutando tarea...");
}
```

---

### ❓ **Pregunta:** ¿Cuál es la diferencia entre `map()` y `flatMap()` en Streams?
✅ **Respuesta:**
- **`map()`** aplica una función y devuelve un Stream de valores individuales.
- **`flatMap()`** aplana estructuras anidadas y devuelve un Stream de elementos individuales.

📌 **Ejemplo con `map()`:**
```java
List<String> nombres = List.of("Juan", "Ana");
List<Integer> longitudes = nombres.stream().map(String::length).collect(Collectors.toList());
```
📌 **Ejemplo con `flatMap()`:**
```java
List<List<String>> listaDeListas = List.of(List.of("A", "B"), List.of("C", "D"));
List<String> resultado = listaDeListas.stream().flatMap(List::stream).collect(Collectors.toList());
```

---

### ❓ **Pregunta:** ¿Cuál es la diferencia entre `synchronized` y `ReentrantLock`?
✅ **Respuesta:**
- **`synchronized`** es una palabra clave de Java que **bloquea el acceso** a un método o bloque de código.
- **`ReentrantLock`** es más flexible y permite **intentos de bloqueo con timeout y chequeos más avanzados**.

📌 **Ejemplo de `synchronized`:**
```java
public synchronized void metodoSeguro() { /* código crítico */ }
```

📌 **Ejemplo de `ReentrantLock`:**
```java
private final ReentrantLock lock = new ReentrantLock();
public void metodoSeguro() {
    lock.lock();
    try { /* código crítico */ } finally { lock.unlock(); }
}
```

---

### ❓ **Pregunta:** ¿Qué es Lazy Loading y cómo se soluciona el problema de `LazyInitializationException`?
✅ **Respuesta:**  
El **Lazy Loading** retrasa la carga de datos hasta que se accede explícitamente. `LazyInitializationException` ocurre cuando se intenta acceder a datos cargados de manera diferida fuera del contexto de una sesión de Hibernate.

📌 **Solución:** Usar `JOIN FETCH` o **@Transactional**.
```java
@Query("SELECT p FROM Pedido p JOIN FETCH p.detalles WHERE p.id = :id")
Pedido findPedidoConDetalles(@Param("id") Long id);
```

---

### ❓ **Pregunta:** ¿Cuáles son las ventajas de usar GraalVM en Java?
✅ **Respuesta:**  
✔ **Compila código Java a binarios nativos**, reduciendo tiempo de arranque y consumo de memoria.  
✔ **Soporta lenguajes adicionales** (Python, JavaScript, etc.).  
✔ **Mejora el rendimiento de las aplicaciones.**

📌 **Ejemplo:**
```bash
mvn -Pnative native:compile
./mi-aplicacion
```

---

### ❓ **Pregunta:** ¿Cómo permite Dapr la comunicación entre microservicios?
✅ **Respuesta:**  
Dapr ofrece un **bus de eventos** para microservicios desacoplados, facilitando la comunicación sin depender de direcciones directas.

📌 **Ejemplo:**
```java
DaprClient client = new DaprClientBuilder().build();
client.invokeMethod("servicioB", "ruta", "payload", String.class).block();
```

---

### ❓ **Pregunta:** ¿Cómo se usa Axon Framework para manejar eventos?
✅ **Respuesta:**  
Axon permite **separar comandos de consultas** y almacenar eventos para reconstruir estados.

📌 **Ejemplo:**
```java
@Aggregate
public class Pedido {
    @AggregateIdentifier private String id;
    @CommandHandler public Pedido(CrearPedidoCommand cmd) {
        apply(new PedidoCreadoEvent(cmd.getId()));
    }
}
```

---

### ❓ **Pregunta:** Completa el codigo para que los asserts sean correctos
✅ **Respuesta:**
```java
// package whatever; // don't place package name!
// Installed Libraries: JSON-Simple, JUNit 4, Apache Commons Lang3
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class MyCode {
public static void main (String[] args) {
final var adminRole = new Role("admin");
final var userRole = new Role("user");

    final var user1 = new User("user1", 10, List.of(adminRole));
    final var user2 = new User("user2", 10, List.of(userRole));
    final var user3 = new User("user3", 20, List.of(adminRole));
    
		
    final var ageToUsers = new HashMap<Integer, List<User>>();

    //-------------ANSWER-------------
    Map<Integer, List<User>> ageToUsers = Stream.of(user1, user2, user3)
            .collect(Collectors.groupingBy(User::age, LinkedHashMap::new, Collectors.toList()));
    //--------------------------------
    
    final var roleToUsers = new HashMap<Role, List<User>>();

    //-------------ANSWER-------------
    Map<Role, List<User>> roleToUsers = Stream.of(user1, user2, user3)
            .flatMap(user -> user.roles().stream().map(role -> new AbstractMap.SimpleEntry<>(role, user)))
            .collect(Collectors.groupingBy(
                    Map.Entry::getKey,
                    LinkedHashMap::new,
                    Collectors.mapping(Map.Entry::getValue, Collectors.toList())
            ));
    //--------------------------------
    
    // Group by age
    assertEquals(
      Map.of(
        10, List.of(user1, user2),
        20, List.of(user3)), ageToUsers);
    
    // Group by role
    assertEquals(
      Map.of(
        userRole, List.of(user2),
        adminRole, List.of(user1, user3)), ageToUsers);
    
	}


static class Role {
    private final String name;
    Role(String name) {
        this.name = name;
    }
}

record User(String name, int age, List<Role> roles) {}
}
```

---