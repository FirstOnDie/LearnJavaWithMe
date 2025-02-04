
# **📌 Día 10: Programación Reactiva con Project Reactor**
Hoy aprenderás:  
✅ **Mono y Flux** (Manejo de flujos de datos reactivos)  
✅ **Backpressure y operadores avanzados** (Control de carga)  
✅ **Ejercicio: Procesamiento de eventos en tiempo real**

---

📌 **¿Por qué es importante?**  
La **Programación Reactiva** permite manejar **flujos de datos asíncronos y concurrentes** de forma eficiente, lo que es **clave para aplicaciones escalables y de alto rendimiento**.

**Project Reactor** es el estándar en **Spring WebFlux**, **Microservicios** y **Sistemas de Streaming**.

---


# **1️⃣ ¿Qué es la Programación Reactiva?**
📌 **Diferencias entre Programación Imperativa y Reactiva:**

| **Imperativa** 🚫 | **Reactiva** 🚀 |
|------------------|----------------|
| Bloqueante (espera respuesta) | No bloqueante (flujo continuo) |
| Pocos hilos, pero lentos | Escalable con pocos recursos |
| `List<T>` y `Stream<T>` | `Mono<T>` y `Flux<T>` |
| Ideal para tareas simples | Ideal para streaming y microservicios |

📌 **Ejemplo: Diferencia clave**  
✅ **Imperativo (Bloqueante)**
```java
List<String> datos = List.of("A", "B", "C");
datos.forEach(System.out::println);
```
✅ **Reactivo (No bloqueante)**
```java
import reactor.core.publisher.Flux;

Flux.just("A", "B", "C")
    .subscribe(System.out::println);
```
📌 **Salida esperada:**
```
A
B
C
```
✅ **`Flux` emite valores uno a uno, permitiendo procesamiento en tiempo real.**

---

# **2️⃣ `Mono<T>` y `Flux<T>`: Fundamentos de Reactor**
📌 **Mono:** Representa **0 o 1 valor** (Ejemplo: Respuesta de una API)  
📌 **Flux:** Representa **0 a N valores** (Ejemplo: Streaming de datos)

---

## **🔹 Ejemplo 1: `Mono<T>` (Un solo valor)**
```java
import reactor.core.publisher.Mono;

public class MonoEjemplo {
    public static void main(String[] args) {
        Mono.just("Hola Mundo")
            .subscribe(System.out::println);
    }
}
```
📌 **Salida esperada:**
```
Hola Mundo
```
✅ **Usamos `Mono.just(valor)` para emitir un solo dato.**

---

## **🔹 Ejemplo 2: `Flux<T>` (Múltiples valores)**
```java
import reactor.core.publisher.Flux;

public class FluxEjemplo {
    public static void main(String[] args) {
        Flux.just("A", "B", "C")
            .subscribe(System.out::println);
    }
}
```
📌 **Salida esperada:**
```
A
B
C
```
✅ **Usamos `Flux.just(valor1, valor2, ...)` para emitir múltiples valores.**

---

# **3️⃣ Operadores Avanzados en Flux y Mono**
📌 **Los operadores nos permiten transformar, filtrar y combinar datos en `Flux` y `Mono`.**

| **Operador** | **Descripción** | **Ejemplo** |
|-------------|----------------|-------------|
| `map()` | Transforma valores | `flux.map(String::toUpperCase)` |
| `filter()` | Filtra elementos | `flux.filter(x -> x.startsWith("A"))` |
| `flatMap()` | Convierte cada valor en un nuevo `Flux` o `Mono` | `flux.flatMap(x -> Mono.just(x + "!"))` |
| `concat()` | Une múltiples `Flux` | `Flux.concat(flux1, flux2)` |
| `delayElements()` | Introduce retardo entre emisiones | `flux.delayElements(Duration.ofSeconds(1))` |

---

## **🔹 Ejemplo 3: Uso de `map()` y `filter()`**
```java
import reactor.core.publisher.Flux;

public class OperadoresEjemplo {
    public static void main(String[] args) {
        Flux.just("Ana", "Miguel", "Marta", "Juan", "Mario")
            .filter(nombre -> nombre.startsWith("M"))
            .map(String::toUpperCase)
            .subscribe(System.out::println);
    }
}
```
📌 **Salida esperada:**
```
MIGUEL
MARTA
MARIO
```
✅ **Filtramos nombres y los convertimos a mayúsculas.**

---

# **4️⃣ Backpressure: Control de Flujo en Flux**
📌 **Backpressure evita que un consumidor reciba más datos de los que puede manejar.**

📌 **Ejemplo: Generar datos más rápido de lo que pueden procesarse**
```java
import reactor.core.publisher.Flux;
import java.time.Duration;

public class BackpressureEjemplo {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(200)) // Emite cada 200ms
            .doOnNext(i -> System.out.println("Emitiendo: " + i))
            .blockLast(); // Evita que el programa termine antes de recibir datos
    }
}
```
📌 **Salida esperada:**
```
Emitiendo: 0
Emitiendo: 1
Emitiendo: 2
...
```
✅ **Emitimos datos continuamente en intervalos de tiempo.**

📌 **Ejemplo: Controlar backpressure con `onBackpressureBuffer()`**
```java
import reactor.core.publisher.Flux;
import java.time.Duration;

public class BackpressureControl {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(100))
            .onBackpressureBuffer(5)
            .doOnNext(System.out::println)
            .blockLast();
    }
}
```
✅ **Si hay demasiados datos, los almacena en un buffer en lugar de desecharlos.**

---

# **5️⃣ Ejercicio Práctico: Procesamiento de Eventos en Tiempo Real**
📌 **Queremos:**  
1️⃣ **Simular un sistema que recibe eventos cada 500ms.**  
2️⃣ **Filtrar eventos con valores mayores a 50.**  
3️⃣ **Transformar valores duplicándolos.**

---

<details>
    <summary>Solución</summary>

## **🔹 Código del ejercicio resuelto**
```java
import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.Random;

public class ProcesamientoEventos {
    public static void main(String[] args) {
        Random random = new Random();

        Flux.interval(Duration.ofMillis(500)) // Generar eventos cada 500ms
            .map(i -> random.nextInt(100)) // Generar número aleatorio
            .filter(valor -> valor > 50) // Filtrar valores mayores a 50
            .map(valor -> valor * 2) // Transformar valores
            .doOnNext(valor -> System.out.println("Procesando evento: " + valor))
            .blockLast(); // Mantener el programa corriendo
    }
}
```
📌 **Salida esperada (valores aleatorios):**
```
Procesando evento: 120
Procesando evento: 160
Procesando evento: 110
...
```
✅ **Manejamos eventos en tiempo real con filtros y transformación de datos.**

---

</details>