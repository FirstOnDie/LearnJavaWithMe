# **📌 Anotaciones en Java** 🎯📌

📌 **¿Qué son las anotaciones en Java?**  
Las **anotaciones** en Java son como **etiquetas especiales** que agregamos a nuestro código para darle **instrucciones adicionales al compilador, a las herramientas de desarrollo o a las bibliotecas**.

Imagina que las anotaciones son **pegatinas en un libro de cocina** 📖🍳:
- Algunas **explican** cómo se debe usar una receta (`@Override`).
- Otras **avisan** que algo está obsoleto (`@Deprecated`).
- Algunas ayudan a **automatizar tareas** (`@Autowired` en Spring).

💡 **Las anotaciones no afectan directamente la ejecución del código, pero ayudan a mejorar la legibilidad, organización y funcionalidad del programa.**

---

# **📌 Índice de Anotaciones** 📑

### **1️⃣ Java Básico**
- **[@Override](#override)** → Indica que un método sobrescribe uno de la clase padre.
- **[@Deprecated](#deprecated)** → Marca un método o clase como obsoleto.
- **[@SuppressWarnings](#suppresswarnings)** → Suprime advertencias del compilador.
- **[@SafeVarargs](#safevarargs)** → Indica que un método con argumentos variables es seguro.
- **[@FunctionalInterface](#functionalinterface)** → Asegura que una interfaz tiene un solo método abstracto.

### **2️⃣ Pruebas Unitarias con JUnit**
- **[@Test](#test)** → Marca un método como una prueba unitaria.

### **3️⃣ JPA (Java Persistence API)**
- **[@Entity](#entity)** → Marca una clase como entidad de base de datos.
- **[@Id](#id)** → Indica el campo clave primaria de la entidad.
- **[@GeneratedValue](#generatedvalue)** → Define cómo se generará el ID automáticamente.
- **[@Column](#column)** → Personaliza una columna de la base de datos.

### **4️⃣ Spring Framework**
📌 **Inyección de Dependencias y Componentes**
- **[@Autowired](#autowired)** → Inyecta automáticamente una dependencia.
- **[@Component](#component)** → Marca una clase como componente gestionado por Spring.
- **[@Service](#service)** → Indica que una clase es un servicio (lógica de negocio).
- **[@Repository](#repository)** → Marca una clase como repositorio de base de datos.

📌 **Controladores Web (Spring MVC y REST)**
- **[@Controller](#controller)** → Define un controlador web en Spring MVC.
- **[@RestController](#restcontroller)** → Un controlador que devuelve JSON/XML.
- **[@RequestMapping](#requestmapping)** → Define rutas en controladores.
- **[@GetMapping](#getmapping)** → Maneja solicitudes HTTP GET.
- **[@PostMapping](#postmapping)** → Maneja solicitudes HTTP POST.
- **[@RequestParam](#requestparam)** → Extrae parámetros de la URL.

---

# **📌 1️⃣ Anotaciones en Java Básico**

### **🔹 `@Override`**
📌 **Indica que un método sobrescribe otro de la clase padre.**  
✅ **Evita errores si el método padre cambia de nombre o parámetros.**

```java
class Animal {
    public void hacerSonido() {
        System.out.println("Sonido genérico");
    }
}

class Perro extends Animal {
    @Override
    public void hacerSonido() { 
        System.out.println("Guau!");
    }
}
```
📌 **Beneficio:**  
✔ Java detectará si intentamos sobrescribir un método inexistente y mostrará un error.

---

### **🔹 `@Deprecated`**
📌 **Indica que un método o clase es obsoleto y no debería usarse más.**

```java
class Calculadora {
    @Deprecated
    public int sumarViejo(int a, int b) { return a + b; }

    public int sumar(int a, int b) { return a + b; }
}
```
📌 **Beneficio:**  
✔ Al usar `sumarViejo()`, el compilador mostrará una advertencia.

---

### **🔹 `@SuppressWarnings`**
📌 **Evita que el compilador muestre advertencias específicas.**

```java
public class Main {
    @SuppressWarnings("unchecked")
    public void metodo() {
        // Código que genera una advertencia
    }
}
```
📌 **Beneficio:**  
✔ Oculta advertencias que sabemos que no afectan el código.

---

### **🔹 `@SafeVarargs`**
📌 **Evita advertencias en métodos con argumentos variables (`varargs`).**

```java
public class Main {
    @SafeVarargs
    public final <T> void imprimir(T... elementos) {
        for (T e : elementos) System.out.println(e);
    }
}
```
📌 **Beneficio:**  
✔ Java sabrá que el uso de `varargs` es seguro y no mostrará advertencias.

---

### **🔹 `@FunctionalInterface`**
📌 **Define una interfaz funcional con un solo método abstracto (para lambdas).**

```java
@FunctionalInterface
interface Operacion {
    int operar(int a, int b);
}
```
📌 **Beneficio:**  
✔ Evita que alguien agregue más métodos, asegurando que sea una interfaz funcional válida.

---

# **📌 2️⃣ Anotaciones en Pruebas Unitarias (JUnit)**

### **🔹 `@Test`**
📌 **Marca un método como una prueba unitaria.**

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {
    @Test
    public void testSumar() {
        Calculadora calc = new Calculadora();
        assertEquals(5, calc.sumar(2, 3));
    }
}
```
📌 **Beneficio:**  
✔ JUnit ejecutará el método automáticamente durante las pruebas.

---

# **📌 3️⃣ Anotaciones en JPA (Base de Datos con Hibernate)**

### **🔹 `@Entity`, `@Id`, `@GeneratedValue`, `@Column`**
📌 **Define una clase que representa una tabla en la base de datos.**

```java
import javax.persistence.*;

@Entity
class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false)
    private String nombre;
}
```
📌 **Beneficio:**  
✔ Permite mapear objetos Java en la base de datos de forma automática.

---

# **📌 4️⃣ Anotaciones en Spring Framework**

### **🔹 `@Autowired`**
📌 **Inyección automática de dependencias en Spring.**

```java
import org.springframework.beans.factory.annotation.Autowired;

public class ServicioUsuario {
    @Autowired
    private RepositorioUsuario repositorio;
}
```
📌 **Beneficio:**  
✔ Spring inyectará `RepositorioUsuario` sin necesidad de instanciarlo manualmente.

---

### **🔹 `@Service`, `@Repository`, `@Component`**
📌 **Define clases gestionadas por Spring.**

```java
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario { }
```
📌 **Beneficio:**  
✔ Spring las detecta automáticamente para inyección de dependencias.

---

### **🔹 `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`**
📌 **Define controladores REST en Spring Boot.**

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @GetMapping("/{id}")
    public String obtenerUsuario(@PathVariable Long id) {
        return "Usuario con ID " + id;
    }
    
    @PostMapping
    public String crearUsuario(@RequestBody String nombre) {
        return "Usuario " + nombre + " creado!";
    }
}
```
📌 **Beneficio:**  
✔ Spring maneja las peticiones HTTP automáticamente.

---