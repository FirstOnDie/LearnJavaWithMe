# **📌 Mega Lista de Preguntas para Entrevistas Técnicas en Java** ☕🚀

---

## **📌 1️⃣ Preguntas Generales de Java** 🛠️

### **¿Qué diferencia hay entre JDK, JRE y JVM?**
💡 **Respuesta:**
- **JDK (Java Development Kit):** Incluye el compilador (`javac`), bibliotecas y herramientas necesarias para desarrollar aplicaciones Java.
- **JRE (Java Runtime Environment):** Contiene la JVM y bibliotecas esenciales para ejecutar aplicaciones Java.
- **JVM (Java Virtual Machine):** Máquina virtual que ejecuta el código Java compilado en **bytecode**.

📌 **Ejemplo de uso:**
```bash
java -version  # Muestra la versión del JRE
javac -version  # Muestra la versión del compilador (JDK)
```

---

### **¿Qué es el concepto de "pass by value" en Java?**
💡 **Respuesta:**  
Java **siempre** pasa los parámetros por **valor**:
- Para tipos primitivos (`int`, `double`, etc.), se pasa una copia del valor.
- Para objetos, se pasa una copia de la referencia, pero no del objeto en sí.

📌 **Ejemplo:**
```java
void cambiarValor(int x) { x = 10; }
void cambiarObjeto(Persona p) { p.setNombre("Nuevo"); }
```
- Si `x` es un **int**, su valor original no cambia.
- Si `p` es un **objeto**, la referencia es la misma y su estado cambia.

---

### **¿Cuál es la diferencia entre `==` y `equals()` en Java?**
💡 **Respuesta:**
- `==` compara **referencias de memoria** (si apuntan al mismo objeto).
- `equals()` compara **contenido** (implementado en clases como `String`).

📌 **Ejemplo:**
```java
String a = new String("Hola");
String b = new String("Hola");

System.out.println(a == b);       // ❌ false (distintas referencias)
System.out.println(a.equals(b));  // ✅ true (mismo contenido)
```

---

### **¿Cómo funciona el manejo de memoria en Java?**
💡 **Respuesta:**  
Java gestiona la memoria automáticamente usando:
1. **Heap:** Donde se almacenan los objetos.
2. **Stack:** Donde se almacenan variables locales y referencias.
3. **Garbage Collector:** Recolecta objetos no referenciados.

📌 **Ejemplo:**
```java
String nombre = "Juan"; // Se guarda en la pool de Strings en el Heap.
int edad = 25; // Se guarda en el Stack.
```

---

### **¿Qué son las `wrapper classes` en Java?**
💡 **Respuesta:**  
Son clases que envuelven tipos primitivos para tratarlos como objetos (`Integer`, `Double`, `Boolean`, etc.).

📌 **Ejemplo:**
```java
Integer num = Integer.valueOf(10);  // Boxing
int valor = num.intValue();         // Unboxing
```

---

### **¿Cuál es la diferencia entre `String`, `StringBuilder` y `StringBuffer`?**
💡 **Respuesta:**
- **`String`**: Inmutable (no cambia su valor en memoria).
- **`StringBuilder`**: Mutable y más rápido, pero **no** es seguro para múltiples hilos.
- **`StringBuffer`**: Mutable y seguro para múltiples hilos (pero más lento que `StringBuilder`).

📌 **Ejemplo:**
```java
StringBuilder sb = new StringBuilder("Hola");
sb.append(" Mundo"); // ✅ Se modifica sin crear un nuevo objeto
```

---

### **¿Qué es la reflexión (Reflection) en Java?**
💡 **Respuesta:**  
Reflection permite **inspeccionar y modificar** clases, métodos y atributos en **tiempo de ejecución**.

📌 **Ejemplo:**
```java
import java.lang.reflect.Method;

class Persona {
    public void saludar() { System.out.println("Hola!"); }
}

public class ReflectionEjemplo {
    public static void main(String[] args) throws Exception {
        Class<?> clase = Class.forName("Persona");
        Object obj = clase.getDeclaredConstructor().newInstance();
        Method metodo = clase.getMethod("saludar");
        metodo.invoke(obj);  // Imprime "Hola!"
    }
}
```

---

### **¿Cuál es la diferencia entre `fail-fast` y `fail-safe` en colecciones de Java?**
💡 **Respuesta:**
- **`Fail-fast`**: Lanza `ConcurrentModificationException` si una colección es modificada mientras se itera (`ArrayList`, `HashMap`).
- **`Fail-safe`**: Permite modificar la colección durante la iteración sin lanzar excepción (`ConcurrentHashMap`, `CopyOnWriteArrayList`).

📌 **Ejemplo fail-fast:**
```java
List<Integer> lista = new ArrayList<>(List.of(1, 2, 3));
for (Integer num : lista) { lista.add(4); } // ❌ ConcurrentModificationException
```

---

### **¿Qué es la `Method Reference` en Java y cómo se usa?**
💡 **Respuesta:**  
Las **referencias a métodos** (`::`) permiten reutilizar métodos existentes en funciones lambda.

📌 **Ejemplo:**
```java
List<String> nombres = List.of("Ana", "Pedro", "Marta");
nombres.forEach(System.out::println); // ✅ En lugar de: nombres.forEach(n -> System.out.println(n));
```

---

### **¿Cuál es la diferencia entre `Optional.of()`, `Optional.ofNullable()` y `Optional.empty()`?**
💡 **Respuesta:**
- **`Optional.of(valor)`**: Lanza `NullPointerException` si el valor es `null`.
- **`Optional.ofNullable(valor)`**: Permite valores nulos sin lanzar error.
- **`Optional.empty()`**: Representa un `Optional` vacío.

📌 **Ejemplo:**
```java
Optional<String> opt1 = Optional.of("Hola");          // ✅ Ok
Optional<String> opt2 = Optional.ofNullable(null);    // ✅ No lanza error
Optional<String> opt3 = Optional.empty();             // ✅ Representa vacío
```

---

## **📌 2️⃣ Programación Orientada a Objetos (POO) en Java** 🏗️

### **¿Cuáles son los principios SOLID?**
💡 **Respuesta:**  
1️⃣ **S**ingle Responsibility (Responsabilidad Única)  
2️⃣ **O**pen/Closed (Abierto/Cerrado)  
3️⃣ **L**iskov Substitution (Sustitución de Liskov)  
4️⃣ **I**nterface Segregation (Segregación de Interfaces)  
5️⃣ **D**ependency Inversion (Inversión de Dependencias)

---

### **¿Qué diferencia hay entre una clase abstracta y una interfaz?**
💡 **Respuesta:**
- **Clase abstracta:** Puede tener métodos concretos y abstractos.
- **Interfaz:** Solo métodos abstractos (hasta Java 7) y `default/static` desde Java 8.

📌 **Ejemplo:**
```java
abstract class Animal { abstract void hacerSonido(); }
interface Volador { void volar(); }
```

---

### **¿Qué es el polimorfismo y cómo se implementa en Java?**
💡 **Respuesta:**  
El polimorfismo permite que un método tenga **diferentes comportamientos** según el contexto.

📌 **Ejemplo de sobrecarga (compile-time polymorphism):**
```java
class Calculadora {
    int sumar(int a, int b) { return a + b; }
    double sumar(double a, double b) { return a + b; }
}
```

📌 **Ejemplo de sobrescritura (runtime polymorphism):**
```java
class Animal { void hacerSonido() { System.out.println("Sonido genérico"); } }
class Perro extends Animal { @Override void hacerSonido() { System.out.println("Guau!"); } }
```

---

### **¿Qué es el Principio de Sustitución de Liskov (L en SOLID)?**
💡 **Respuesta:**  
Una subclase debe poder **sustituir a su clase base** sin alterar el comportamiento esperado del programa.

📌 **Ejemplo incorrecto (viola Liskov):**
```java
class Pato {
    void nadar() { System.out.println("Pato nadando"); }
}

class PatoDeGoma extends Pato {
    void nadar() { throw new UnsupportedOperationException("No puedo nadar!"); } // ❌
}
```

Aquí `PatoDeGoma` no cumple el contrato de `Pato`, rompiendo Liskov.

---

### **¿Qué diferencia hay entre la sobrecarga (`overloading`) y la sobrescritura (`overriding`) de métodos?**
💡 **Respuesta:**
- **Sobrecarga (`overloading`)**: Mismo nombre de método, pero distintos parámetros.
- **Sobrescritura (`overriding`)**: Una subclase redefine el método de su superclase.

📌 **Ejemplo:**
```java
class Animal {
    void hacerSonido() { System.out.println("Sonido genérico"); }
}

class Perro extends Animal {
    @Override
    void hacerSonido() { System.out.println("Guau!"); } // ✅ Sobrescritura
}
```

---

## **📌 3️⃣ Concurrencia y Multithreading** 🧵⚡

### **¿Cuál es la diferencia entre `synchronized` y `volatile`?**
💡 **Respuesta:**
- **`synchronized`**: Bloquea un recurso para que solo un hilo pueda acceder.
- **`volatile`**: Indica que una variable puede ser modificada por múltiples hilos, asegurando que los cambios sean visibles para todos.

📌 **Ejemplo:**
```java
private volatile boolean running = true;
```

---

### **¿Qué es un `ThreadPool` y por qué es útil?**
💡 **Respuesta:**  
Un **ThreadPool** gestiona un conjunto de hilos reutilizables, mejorando el rendimiento en tareas concurrentes.

📌 **Ejemplo con `Executors`:**
```java
ExecutorService pool = Executors.newFixedThreadPool(5);
pool.execute(() -> System.out.println("Tarea ejecutada"));
pool.shutdown();
```

---

### **¿Qué es el patrón "Producer-Consumer" y cómo se implementa en Java?**
💡 **Respuesta:**  
El **patrón Productor-Consumidor** es un problema clásico de concurrencia donde un **productor** genera datos y un **consumidor** los procesa.

📌 **Ejemplo con `BlockingQueue`:**
```java
BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

// Productor
new Thread(() -> {
    try { queue.put(1); } catch (InterruptedException e) {}
}).start();

// Consumidor
new Thread(() -> {
    try { System.out.println(queue.take()); } catch (InterruptedException e) {}
}).start();
```

---

### **¿Qué es un `CompletableFuture` y cómo se usa?**
💡 **Respuesta:**  
`CompletableFuture` permite ejecutar tareas **asíncronas y no bloqueantes** en Java.

📌 **Ejemplo:**
```java
CompletableFuture.supplyAsync(() -> "Resultado")
    .thenApply(resultado -> resultado + " procesado")
    .thenAccept(System.out::println);
```

---

## **📌 4️⃣ Frameworks: Spring Boot y Hibernate** 🌱📦

### **¿Qué diferencia hay entre `@Component`, `@Service` y `@Repository`?**
💡 **Respuesta:**
- **`@Component`**: Marca cualquier clase gestionada por Spring.
- **`@Service`**: Indica lógica de negocio.
- **`@Repository`**: Indica una capa de acceso a datos (DAO).

📌 **Ejemplo:**
```java
@Service
public class MiServicio { /* Código */ }
```

---

### **¿Qué es la Inversión de Control (IoC) en Spring?**
💡 **Respuesta:**  
Es un **principio de diseño** donde el control de la creación de objetos lo maneja **Spring** en lugar del programador.

📌 **Ejemplo con `@Autowired`:**
```java
@Component
public class MiRepositorio { /* Código */ }

@Service
public class MiServicio {
    @Autowired private MiRepositorio repo;
}
```

---

### **¿Cuál es la diferencia entre `@Component`, `@Bean` y `@Configuration` en Spring?**
💡 **Respuesta:**
- **`@Component`**: Anotación de clase que marca un **componente gestionado por Spring**.
- **`@Bean`**: Método dentro de una `@Configuration` que devuelve una instancia gestionada.
- **`@Configuration`**: Clase que define uno o más `@Bean`.

📌 **Ejemplo:**
```java
@Configuration
public class MiConfiguracion {
    @Bean
    public Servicio servicio() { return new Servicio(); }
}
```

---

### **¿Cómo se maneja la paginación en Spring Data JPA?**
💡 **Respuesta:**  
Spring Data JPA ofrece `Pageable` para manejar **paginación y ordenación** de resultados.

📌 **Ejemplo:**
```java
Page<Usuario> usuarios = repo.findAll(PageRequest.of(0, 10, Sort.by("nombre")));
```

---

## **📌 5️⃣ Ejercicios Prácticos en Entrevistas** 💻

### **Escribe un programa que detecte si una palabra es un palíndromo.**
💡 **Respuesta:**
```java
public boolean esPalindromo(String palabra) {
    String invertida = new StringBuilder(palabra).reverse().toString();
    return palabra.equalsIgnoreCase(invertida);
}
```

---

### **¿Cómo encontrar el número que más se repite en una lista?**
💡 **Respuesta:**
```java
public int encontrarMasRepetido(List<Integer> numeros) {
    return numeros.stream()
        .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
        .entrySet().stream()
        .max(Map.Entry.comparingByValue())
        .get().getKey();
}
```

### **Escribe un método que encuentre el número duplicado en una lista de enteros.**
💡 **Solución:**
```java
public int encontrarDuplicado(List<Integer> numeros) {
    Set<Integer> vistos = new HashSet<>();
    for (int num : numeros) {
        if (!vistos.add(num)) return num;
    }
    return -1; // No hay duplicados
}
```

---

### **Escribe un método que cuente las palabras en un texto.**
💡 **Solución:**
```java
public Map<String, Integer> contarPalabras(String texto) {
    return Arrays.stream(texto.split("\\s+"))
        .collect(Collectors.groupingBy(w -> w, Collectors.summingInt(w -> 1)));
}
```

---