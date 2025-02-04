# **📌 Día 28: Entrevistas Técnicas en Java**
Hoy aprenderás:  
✅ **Preguntas avanzadas de entrevistas**  
✅ **Resolución de coding challenges en Java**  
✅ **Estrategias para entrevistas en vivo (live coding y system design)**

---

📌 **¿Por qué es importante?**  
Dominar entrevistas técnicas en Java te ayudará a conseguir oportunidades en empresas top. Hoy practicaremos **preguntas avanzadas de teoría**, **desafíos de código** y **estrategias para entrevistas en vivo**.

---

# **1️⃣ Preguntas Avanzadas de Java en Entrevistas**
📌 **Teoría de Java: Preguntas frecuentes y respuestas**

### **🔹 Preguntas sobre Java y POO**
❓ **¿Cuál es la diferencia entre `==` y `.equals()`?**  
✔ `==` compara referencias de objetos, mientras que `.equals()` compara el contenido.  
📌 **Ejemplo:**
```java
String s1 = new String("Hola");
String s2 = new String("Hola");
System.out.println(s1 == s2);        // false (referencias distintas)
System.out.println(s1.equals(s2));   // true (contenido igual)
```

❓ **¿Cómo funciona el `HashMap` en Java?**  
✔ `HashMap` usa una tabla de hash con buckets y maneja colisiones con **listas enlazadas o árboles (desde Java 8)**.

📌 **Ejemplo:**
```java
Map<String, Integer> mapa = new HashMap<>();
mapa.put("clave", 1);
System.out.println(mapa.get("clave"));  // 1
```

---

### **🔹 Preguntas sobre concurrencia y multithreading**
❓ **¿Cuál es la diferencia entre `synchronized` y `Lock`?**  
✔ `synchronized` bloquea todo el objeto, mientras que `Lock` ofrece más flexibilidad (como intentos de bloqueo con tiempo de espera).  
📌 **Ejemplo con `ReentrantLock`:**
```java
Lock lock = new ReentrantLock();
lock.lock();
try {
    // Código crítico
} finally {
    lock.unlock();
}
```

❓ **¿Cómo funciona el `ThreadPoolExecutor` en Java?**  
✔ Administra un grupo de hilos reutilizables en vez de crear nuevos hilos constantemente.

📌 **Ejemplo:**
```java
ExecutorService executor = Executors.newFixedThreadPool(3);
executor.submit(() -> System.out.println("Ejecutando tarea"));
executor.shutdown();
```

---

### **🔹 Preguntas sobre Spring y Microservicios**
❓ **¿Cuál es la diferencia entre `@Component`, `@Service` y `@Repository`?**  
✔ Son estereotipos de Spring:
- `@Component`: Componente genérico.
- `@Service`: Lógica de negocio.
- `@Repository`: Acceso a base de datos con integración a `Spring Data JPA`.

❓ **¿Cómo maneja Spring Boot la inyección de dependencias?**  
✔ A través de **IoC (Inversión de Control)** y **DI (Dependency Injection)** con `@Autowired`, `@Bean` o constructor injection.

📌 **Ejemplo:**
```java
@Service
public class MiServicio {
    private final MiRepositorio repo;
    public MiServicio(MiRepositorio repo) {
        this.repo = repo;
    }
}
```

---

### **🔹 Preguntas sobre Hibernate y JPA**
❓ **¿Cuál es la diferencia entre `FetchType.LAZY` y `FetchType.EAGER`?**  
✔ **`EAGER` carga datos de inmediato**, **`LAZY` los carga solo cuando se necesitan**.

📌 **Ejemplo:**
```java
@OneToMany(fetch = FetchType.LAZY) // Carga diferida
private List<Pedido> pedidos;
```

❓ **¿Cómo manejar transacciones en Spring Boot?**  
✔ Con `@Transactional`, que asegura **rollback automático en caso de error**.
```java
@Transactional
public void procesarPago() { /* Lógica de pago */ }
```

---

# **2️⃣ Coding Challenges en Java (Ejercicios de Entrevista)**
📌 **Ejercicio 1: Encontrar el primer carácter no repetido en un String**  
**Ejemplo:** `"aabccdbe"` → **Salida:** `'d'`  
📌 **Solución:**
```java
public static char primerNoRepetido(String s) {
    Map<Character, Integer> frecuencia = new LinkedHashMap<>();
    for (char c : s.toCharArray()) {
        frecuencia.put(c, frecuencia.getOrDefault(c, 0) + 1);
    }
    for (Map.Entry<Character, Integer> entry : frecuencia.entrySet()) {
        if (entry.getValue() == 1) return entry.getKey();
    }
    return '_';
}
```
✅ **Complejidad:** `O(n)`

---

📌 **Ejercicio 2: Detectar ciclos en una lista enlazada**  
**Ejemplo:** `1 -> 2 -> 3 -> 4 -> 2 (ciclo)` → **Salida:** `true`  
📌 **Solución con el algoritmo de Floyd (puntero rápido y lento):**
```java
public static boolean tieneCiclo(ListNode head) {
    ListNode lento = head, rapido = head;
    while (rapido != null && rapido.next != null) {
        lento = lento.next;
        rapido = rapido.next.next;
        if (lento == rapido) return true;
    }
    return false;
}
```
✅ **Complejidad:** `O(n)`

---

📌 **Ejercicio 3: Implementar un LRU Cache**  
**Ejemplo:**
```java
LRUCache cache = new LRUCache(2);
cache.put(1, 10);
cache.put(2, 20);
cache.get(1); // Devuelve 10
cache.put(3, 30); // Elimina 2 porque 1 y 3 son más recientes
```
📌 **Solución con `LinkedHashMap`:**
```java
class LRUCache extends LinkedHashMap<Integer, Integer> {
    private final int capacidad;

    public LRUCache(int capacidad) {
        super(capacidad, 0.75f, true);
        this.capacidad = capacidad;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacidad;
    }
}
```
✅ **Complejidad:** `O(1)`

---

# **3️⃣ Estrategias para Entrevistas en Vivo (Live Coding & System Design)**
📌 **Consejos para entrevistas en vivo**  
✔ **Habla en voz alta:** Explica tu razonamiento mientras codificas.  
✔ **Empieza con un ejemplo:** Usa inputs simples antes de escribir código.  
✔ **Optimiza después:** Primero haz que funcione (`brute force`), luego optimiza (`O(n)`).  
✔ **Pregunta requisitos:** Si falta información, **pregunta antes de suponer**.

📌 **Preguntas típicas de System Design**  
❓ **¿Cómo diseñarías un sistema como YouTube?**  
✔ **Componentes clave:** CDN, microservicios, caché, sharding de bases de datos.

❓ **¿Cómo diseñar un chat en tiempo real?**  
✔ **Usar WebSockets, Kafka para eventos, y una base de datos distribuida.**

✅ **Practicar System Design es clave para entrevistas senior.**
