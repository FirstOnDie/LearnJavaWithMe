# **📌 Día 13: Genéricos y Enums en Java** 🚀

📌 **Objetivo del día:**  
✅ Aprender a usar **Genéricos (`List<T>`, `Map<K,V>`)** para crear código reutilizable.  
✅ Comprender **Enumeraciones (`enum`)** y sus ventajas.  
✅ **Ejercicio:** Implementar un **sistema de pedidos con estados (`PENDIENTE`, `ENTREGADO`)**.

---

# **1️⃣ Genéricos en Java (`<T>`, `<K,V>`)**

📌 **¿Qué son los Genéricos?**  
✔ Permiten escribir **clases, interfaces y métodos reutilizables** sin depender de un tipo específico.  
✔ Mejoran **seguridad de tipos**, evitando conversiones innecesarias (`casting`).  
✔ Se usan en **colecciones (`List<T>`, `Map<K,V>`), métodos y clases personalizadas**.

📌 **Ejemplo sin Genéricos (Mala Práctica)**
```java
import java.util.ArrayList;

public class SinGenericos {
    public static void main(String[] args) {
        ArrayList lista = new ArrayList(); // ❌ Sin tipo definido
        lista.add("Hola");
        lista.add(42); // ❌ Se mezcla String e Integer

        String texto = (String) lista.get(0); // ✅ Casting necesario
        int numero = (int) lista.get(1); // ✅ Casting necesario
    }
}
```

⚠ **Problema:** Se permite mezclar tipos, lo que puede causar errores.

---

📌 **Ejemplo con Genéricos (`<T>`) – Buena Práctica**
```java
import java.util.ArrayList;

public class ConGenericos {
    public static void main(String[] args) {
        ArrayList<String> lista = new ArrayList<>(); // ✅ Definiendo tipo
        lista.add("Hola");
        // lista.add(42); ❌ Error: Solo permite `String`

        String texto = lista.get(0); // ✅ No necesita casting
    }
}
```

✅ **Ventajas de Genéricos:**  
✔ **Evitan conversiones (`casting`).**  
✔ **Mejoran la legibilidad y seguridad del código.**

---

## **2️⃣ Genéricos en Clases y Métodos**

📌 **Ejemplo de Clase Genérica (`<T>`)**
```java
// Clase genérica que almacena cualquier tipo de dato
class Caja<T> {
    private T contenido;

    public void setContenido(T contenido) {
        this.contenido = contenido;
    }

    public T getContenido() {
        return contenido;
    }
}
```

📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        Caja<String> cajaString = new Caja<>();
        cajaString.setContenido("Hola, Genéricos");
        System.out.println(cajaString.getContenido());

        Caja<Integer> cajaEntero = new Caja<>();
        cajaEntero.setContenido(42);
        System.out.println(cajaEntero.getContenido());
    }
}
```
✅ **Salida esperada:**
```
Hola, Genéricos  
42
```

✔ **`Caja<T>` permite almacenar `String` o `Integer` sin modificar la clase.**

---

📌 **Ejemplo de Método Genérico (`<T>` en Métodos)**
```java
public class Utilidades {
    public static <T> void imprimir(T valor) {
        System.out.println("Valor: " + valor);
    }
}
```

📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        Utilidades.imprimir("Texto Genérico");
        Utilidades.imprimir(100);
        Utilidades.imprimir(3.14);
    }
}
```
✅ **Salida esperada:**
```
Valor: Texto Genérico  
Valor: 100  
Valor: 3.14
```

✔ **Un mismo método funciona con cualquier tipo de dato (`String`, `int`, `double`).**

---

# **3️⃣ Enumeraciones (`enum`) en Java**

📌 **¿Qué es un `enum` en Java?**  
✔ Representa un **conjunto de valores predefinidos**.  
✔ Se usa para **categorías fijas** como **días de la semana, estados de pedidos, roles de usuario, etc.**

📌 **Ejemplo básico de `enum`:**
```java
enum Dia {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO;
}

public class Main {
    public static void main(String[] args) {
        Dia hoy = Dia.MIERCOLES;
        System.out.println("Hoy es: " + hoy);
    }
}
```
✅ **Salida esperada:**
```
Hoy es: MIERCOLES
```

✔ **Evita errores de ortografía como `"miercoles"` o `miercoles = 3`.**

---

📌 **`enum` con Métodos Personalizados**
```java
enum EstadoPedido {
    PENDIENTE, EN_PROCESO, ENVIADO, ENTREGADO;

    public boolean esFinalizado() {
        return this == ENTREGADO;
    }
}
```

📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        EstadoPedido estado = EstadoPedido.EN_PROCESO;
        System.out.println("¿Pedido finalizado? " + estado.esFinalizado());
    }
}
```
✅ **Salida esperada:**
```
¿Pedido finalizado? false
```

✔ **Se pueden agregar métodos a `enum` para lógica adicional.**

---

# **📌 Ejercicio del Día 13: Sistema de Pedidos con Estados (`PENDIENTE`, `ENTREGADO`)** 🎯

📌 **Objetivo:**  
✔ Crear un **enum `EstadoPedido`** con valores:
- `PENDIENTE`
- `ENVIADO`
- `ENTREGADO`

✔ Crear una **clase `Pedido`** con:
- `int id`
- `String cliente`
- `EstadoPedido estado`

✔ Métodos:
- `avanzarEstado()` → Cambia de `PENDIENTE` → `ENVIADO` → `ENTREGADO`.
- `mostrarInfo()` → Muestra la información del pedido.

📌 **Ejemplo de salida esperada:**
```
Pedido #1001 para Juan - Estado: PENDIENTE  
Avanzando estado...  
Pedido #1001 para Juan - Estado: ENVIADO  
Avanzando estado...  
Pedido #1001 para Juan - Estado: ENTREGADO  
```

---

<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**

📌 **Definir `EstadoPedido` con métodos personalizados:**
```java
enum EstadoPedido {
    PENDIENTE, ENVIADO, ENTREGADO;

    public EstadoPedido siguiente() {
        return switch (this) {
            case PENDIENTE -> ENVIADO;
            case ENVIADO -> ENTREGADO;
            default -> this; // No avanza si ya está ENTREGADO
        };
    }
}
```

📌 **Clase `Pedido` con métodos para gestionar estados:**
```java
class Pedido {
    private int id;
    private String cliente;
    private EstadoPedido estado;

    public Pedido(int id, String cliente) {
        this.id = id;
        this.cliente = cliente;
        this.estado = EstadoPedido.PENDIENTE; // Estado inicial
    }

    public void avanzarEstado() {
        this.estado = estado.siguiente();
        System.out.println("📦 Estado del pedido actualizado: " + estado);
    }

    public void mostrarInfo() {
        System.out.println("Pedido #" + id + " para " + cliente + " - Estado: " + estado);
    }
}
```

📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        Pedido pedido = new Pedido(1001, "Juan");

        pedido.mostrarInfo();
        pedido.avanzarEstado();
        pedido.mostrarInfo();
        pedido.avanzarEstado();
        pedido.mostrarInfo();
    }
}
```

✅ **Salida esperada:**
```
Pedido #1001 para Juan - Estado: PENDIENTE  
📦 Estado del pedido actualizado: ENVIADO  
Pedido #1001 para Juan - Estado: ENVIADO  
📦 Estado del pedido actualizado: ENTREGADO  
Pedido #1001 para Juan - Estado: ENTREGADO  
```

</details>
