# **📌 Día 5: Pruebas en Java con JUnit y Mockito**

Hoy aprenderás:  
✅ **JUnit 5: Test unitarios, assertions y reglas**  
✅ **Mockito: Simulación de dependencias (mocks y stubs)**  
✅ **TestContainers: Pruebas con bases de datos reales en Docker**  
✅ **Ejercicio: Pruebas automatizadas para un servicio de clientes**

---

📌 **¿Por qué es importante?**  
Las pruebas son **esenciales** para garantizar que nuestro código funciona correctamente y prevenir errores en producción. Con **JUnit y Mockito**, podemos escribir pruebas unitarias y simulaciones de dependencias fácilmente.

---

# **1️⃣ ¿Qué es JUnit?**

📌 **JUnit es el framework de pruebas unitarias más usado en Java.**  
📌 Nos permite verificar **cada componente de forma aislada**.  
📌 Usamos **`asserts`** para validar resultados esperados.

### **🔹 Agregar JUnit 5 en `pom.xml` (Maven)**
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.9.2</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.9.2</version>
    <scope>test</scope>
</dependency>
```
📌 **JUnit 5 (`junit-jupiter`) permite escribir pruebas más flexibles y poderosas.**

---

## **2️⃣ Escribiendo nuestra primera prueba con JUnit**
📌 **Ejemplo: Probar una clase `Calculadora.java`**

```java
public class Calculadora {
    public int sumar(int a, int b) {
        return a + b;
    }
}
```

📌 **Prueba unitaria con JUnit 5 (`CalculadoraTest.java`)**
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    @Test
    void testSuma() {
        Calculadora calc = new Calculadora();
        int resultado = calc.sumar(5, 3);
        assertEquals(8, resultado, "La suma debe ser 8");
    }
}
```
📌 **Explicación:**  
✅ `@Test` indica que este método es una prueba.  
✅ `assertEquals(esperado, actual, mensaje)` valida el resultado.

📌 **Ejecutar pruebas:**  
1️⃣ En **IntelliJ/Eclipse**, botón derecho en la clase `CalculadoraTest` → **Run Test**  
2️⃣ Con Maven:
```sh
mvn test
```
✅ **Salida esperada:**
```
✔ Prueba exitosa: testSuma()
```

---

## **3️⃣ Pruebas Avanzadas con Assertions**

📌 **Otras `asserts` útiles en JUnit:**

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssertEjemplos {

    @Test
    void testAsserts() {
        assertTrue(5 > 3, "Debe ser verdadero");
        assertFalse(5 < 3, "Debe ser falso");
        assertNotNull("Hola", "No debe ser nulo");
        assertThrows(ArithmeticException.class, () -> {
            int resultado = 10 / 0;
        });
    }
}
```
✅ **Así probamos valores, excepciones y condiciones booleanas.**

---

## **4️⃣ Mockito: Simulación de Dependencias (Mocks y Stubs)**
📌 **Mockito nos permite probar código sin depender de bases de datos, APIs externas, etc.**

### **🔹 Agregar Mockito en `pom.xml`**
```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.4.0</version>
    <scope>test</scope>
</dependency>
```

---

## **5️⃣ Ejemplo: Simular una Dependencia con Mockito**

📌 **Supongamos que tenemos un `RepositorioUsuario` que accede a la base de datos:**
```java
import java.util.Optional;

public class RepositorioUsuario {
    public Optional<String> obtenerNombrePorId(int id) {
        // Simula acceso a BD (no queremos esto en una prueba unitaria)
        return Optional.of("Carlos");
    }
}
```
📌 **Queremos probar `ServicioUsuario` sin depender del repositorio:**
```java
public class ServicioUsuario {
    private RepositorioUsuario repositorio;

    public ServicioUsuario(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    public String obtenerNombre(int id) {
        return repositorio.obtenerNombrePorId(id).orElse("Desconocido");
    }
}
```

📌 **Prueba unitaria usando `Mockito` (`ServicioUsuarioTest.java`)**
```java
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class ServicioUsuarioTest {

    @Test
    void testObtenerNombre() {
        // Simular el repositorio con Mockito
        RepositorioUsuario repoMock = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repoMock.obtenerNombrePorId(1)).thenReturn(Optional.of("Carlos"));

        // Usar el mock en el servicio
        ServicioUsuario servicio = new ServicioUsuario(repoMock);
        String nombre = servicio.obtenerNombre(1);

        assertEquals("Carlos", nombre, "El nombre debe ser Carlos");
    }
}
```
📌 **Explicación:**  
✅ `Mockito.mock(RepositorioUsuario.class)` crea un objeto falso.  
✅ `when(...).thenReturn(...)` define el comportamiento del mock.  
✅ **Podemos probar `ServicioUsuario` sin necesidad de una BD real.**

✅ **Salida esperada:**
```
✔ Prueba exitosa: testObtenerNombre()
```

---

## **6️⃣ TestContainers: Pruebas con Bases de Datos Reales en Docker**
📌 **TestContainers permite ejecutar bases de datos en Docker para pruebas realistas.**

### **🔹 Agregar TestContainers en `pom.xml`**
```xml
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>postgresql</artifactId>
    <version>1.19.0</version>
    <scope>test</scope>
</dependency>
```
📌 **Ejemplo: Levantar PostgreSQL en un contenedor y probar una consulta real**
```java
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import static org.junit.jupiter.api.Assertions.*;

public class BaseDatosTest {

    static PostgreSQLContainer<?> postgreSQL = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("test")
            .withUsername("user")
            .withPassword("pass");

    @BeforeAll
    static void iniciarBD() {
        postgreSQL.start();
    }

    @AfterAll
    static void detenerBD() {
        postgreSQL.stop();
    }

    @Test
    void testConexion() {
        assertTrue(postgreSQL.isRunning(), "La BD debería estar en ejecución");
    }
}
```
📌 **Explicación:**  
✅ Se levanta un contenedor Docker con PostgreSQL solo para la prueba.  
✅ **Evita depender de bases de datos en el entorno de desarrollo.**

✅ **Salida esperada:**
```
✔ Prueba exitosa: testConexion()
```

---

# **📌 Ejercicio: Pruebas Automatizadas para un Servicio de Clientes**
📌 **Queremos:**  
1️⃣ **Probar el servicio `ClienteService` sin acceder a la base de datos.**  
2️⃣ **Simular `ClienteRepository` con Mockito.**

### **1️⃣ Código Base: `ClienteService.java`**
```java
public class ClienteService {
    private ClienteRepository repositorio;

    public ClienteService(ClienteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public boolean existeCliente(int id) {
        return repositorio.buscarPorId(id).isPresent();
    }
}
```
📌 **Crea una prueba con Mockito que verifique si un cliente existe.**

<details>
    <summary>Solución</summary>

📌 **Objetivo:**  
1️⃣ Probar la clase `ClienteService` sin acceder a la base de datos.  
2️⃣ Simular `ClienteRepository` con **Mockito**.

---

## **1️⃣ Código Base: `ClienteRepository.java` (Interfaz que simularemos con Mockito)**
```java
import java.util.Optional;

public interface ClienteRepository {
    Optional<Cliente> buscarPorId(int id);
}
```
📌 **Es una interfaz que normalmente accedería a la BD, pero la vamos a simular con Mockito.**

---

## **2️⃣ Código Base: `Cliente.java` (Entidad básica)**
```java
public class Cliente {
    private int id;
    private String nombre;

    public Cliente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
}
```
📌 **Representa un cliente en nuestra aplicación.**

---

## **3️⃣ Código Base: `ClienteService.java` (Clase a probar)**
```java
public class ClienteService {
    private ClienteRepository repositorio;

    public ClienteService(ClienteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public boolean existeCliente(int id) {
        return repositorio.buscarPorId(id).isPresent();
    }
}
```
📌 **Este servicio depende de `ClienteRepository`, pero en las pruebas usaremos un **mock** en lugar de una BD real.**

---

## **4️⃣ Prueba Unitaria: `ClienteServiceTest.java`**
📌 **Aquí usaremos `Mockito` para simular el `ClienteRepository`.**

```java
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

public class ClienteServiceTest {

    @Test
    void testExisteCliente() {
        // 1️⃣ Simular el repositorio con Mockito
        ClienteRepository repoMock = Mockito.mock(ClienteRepository.class);

        // 2️⃣ Simular un cliente con ID 1
        Cliente clienteMock = new Cliente(1, "Carlos");

        // 3️⃣ Configurar el mock para que devuelva un cliente cuando se busque por ID 1
        Mockito.when(repoMock.buscarPorId(1)).thenReturn(Optional.of(clienteMock));

        // 4️⃣ Probar el servicio usando el mock
        ClienteService servicio = new ClienteService(repoMock);

        // 5️⃣ Verificar el comportamiento
        assertTrue(servicio.existeCliente(1), "El cliente con ID 1 debería existir");
        assertFalse(servicio.existeCliente(2), "El cliente con ID 2 NO debería existir");

        // 6️⃣ Verificar que `buscarPorId(1)` fue llamado exactamente una vez
        Mockito.verify(repoMock, Mockito.times(1)).buscarPorId(1);
    }
}
```

---

## **📌 Explicación Paso a Paso**

1️⃣ **Creamos un `mock` de `ClienteRepository`** usando `Mockito.mock(ClienteRepository.class)`.  
2️⃣ **Creamos un cliente de prueba** (`Cliente(1, "Carlos")`).  
3️⃣ **Configuramos el `mock` para que devuelva este cliente cuando se llame a `buscarPorId(1)`.**  
4️⃣ **Creamos una instancia de `ClienteService` usando el `mock`.**  
5️⃣ **Probamos el método `existeCliente(int id)`, verificando que:**
- **`existeCliente(1)` devuelve `true`.**
- **`existeCliente(2)` devuelve `false`.**  
  6️⃣ **Verificamos que el mock se usó correctamente con `Mockito.verify()`**.

---

## **📌 Resultado Esperado**
```
✔ Prueba exitosa: testExisteCliente()
```

📌 **Si el código tuviera un error, veríamos algo como:**
```
AssertionError: El cliente con ID 1 debería existir
```

</details>