# **📌 Día 10: Clases Abstractas e Interfaces en Java** 🚀

📌 **Objetivo del día:**  
✅ Comprender las diferencias entre **clases abstractas** e **interfaces**.  
✅ Aprender a implementar **interfaces en Java**.  
✅ **Ejercicio:** Crear un **sistema de figuras geométricas con interfaces**.

---

## **1️⃣ ¿Qué es una Clase Abstracta en Java?**

📌 **Una clase abstracta es una plantilla para otras clases, pero NO se puede instanciar.**

✔ Se usa para definir métodos comunes y evitar duplicación de código.  
✔ Puede contener **métodos abstractos** (sin implementación) y **métodos concretos** (con implementación).  
✔ Se usa la palabra clave **`abstract`**.

📌 **Ejemplo de clase abstracta en Java:**
```java
// Clase abstracta
abstract class Animal {
    String nombre;

    // Método concreto (con implementación)
    void comer() {
        System.out.println(nombre + " está comiendo.");
    }

    // Método abstracto (debe ser implementado por las subclases)
    abstract void hacerSonido();
}

// Subclase que implementa el método abstracto
class Perro extends Animal {
    @Override
    void hacerSonido() {
        System.out.println(nombre + " dice: ¡Guau!");
    }
}
```

📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        Perro perro = new Perro();
        perro.nombre = "Max";
        perro.comer();       // Método heredado de la clase abstracta
        perro.hacerSonido(); // Método implementado en la subclase
    }
}
```
✅ **Salida esperada:**
```
Max está comiendo.  
Max dice: ¡Guau!
```

✔ **`Perro` hereda de `Animal` y debe implementar `hacerSonido()`.**  
✔ **No se puede instanciar `Animal` directamente.**

---

## **2️⃣ ¿Qué es una Interfaz en Java?**

📌 **Una interfaz es un contrato que define métodos, pero NO tiene implementación.**

✔ Se usa para definir **comportamientos comunes** entre clases no relacionadas.  
✔ Todos los métodos en una interfaz son **abstractos por defecto** (Java 8+ permite métodos `default`).  
✔ Una clase puede **implementar múltiples interfaces** (pero solo extender una clase).

📌 **Ejemplo de una interfaz en Java:**
```java
// Definición de la interfaz
interface Volador {
    void volar(); // Método abstracto
}

// Implementación en una clase
class Pajaro implements Volador {
    @Override
    public void volar() {
        System.out.println("El pájaro está volando.");
    }
}

// Otra clase que implementa la misma interfaz
class Avion implements Volador {
    @Override
    public void volar() {
        System.out.println("El avión está despegando.");
    }
}
```

📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        Volador pajaro = new Pajaro();
        Volador avion = new Avion();

        pajaro.volar();
        avion.volar();
    }
}
```
✅ **Salida esperada:**
```
El pájaro está volando.  
El avión está despegando.
```

✔ **Diferentes clases (`Pajaro` y `Avion`) implementan la misma interfaz `Volador`.**  
✔ **Esto permite polimorfismo: un `Volador` puede ser un pájaro o un avión.**

---

## **3️⃣ Diferencias entre Clases Abstractas e Interfaces**

| Característica | Clase Abstracta | Interfaz |
|--------------|-----------------|----------|
| **Herencia** | Solo permite herencia simple (`extends`). | Permite múltiples implementaciones (`implements`). |
| **Métodos** | Puede tener métodos abstractos y concretos. | Solo define métodos abstractos (excepto `default` y `static`). |
| **Atributos** | Puede tener atributos con cualquier modificador (`private`, `protected`, `public`). | Solo permite **constantes** (`public static final`). |
| **Uso** | Se usa cuando varias clases comparten comportamiento. | Se usa cuando varias clases comparten acciones pero no herencia. |

📌 **Ejemplo combinado:**
```java
// Clase abstracta
abstract class Animal {
    abstract void hacerSonido();
}

// Interfaz
interface Nadador {
    void nadar();
}

// Subclase que implementa la interfaz y hereda la clase abstracta
class Delfin extends Animal implements Nadador {
    @Override
    void hacerSonido() {
        System.out.println("El delfín hace un sonido agudo.");
    }

    @Override
    public void nadar() {
        System.out.println("El delfín está nadando.");
    }
}
```

---

# **📌 Ejercicio del Día 10: Sistema de Figuras Geométricas con Interfaces** 🎯

📌 **Objetivo:**  
✔ Crear una **interfaz `Figura`** con los métodos:
- `double calcularArea()`
- `double calcularPerimetro()`

✔ Implementar dos clases que representen figuras geométricas:
- `Circulo`
- `Rectangulo`

📌 **Ejemplo de salida esperada:**
```
Círculo - Área: 78.54, Perímetro: 31.42  
Rectángulo - Área: 50.0, Perímetro: 30.0
```

---

<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
📌 **Interfaz `Figura` con métodos abstractos:**
```java
// Definir la interfaz Figura
interface Figura {
    double calcularArea();
    double calcularPerimetro();
}
```

📌 **Clase `Circulo` que implementa la interfaz `Figura`:**
```java
class Circulo implements Figura {
    private double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }
}
```

📌 **Clase `Rectangulo` que implementa la interfaz `Figura`:**
```java
class Rectangulo implements Figura {
    private double ancho, alto;

    public Rectangulo(double ancho, double alto) {
        this.ancho = ancho;
        this.alto = alto;
    }

    @Override
    public double calcularArea() {
        return ancho * alto;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * (ancho + alto);
    }
}
```

📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        Figura circulo = new Circulo(5); // Radio = 5
        Figura rectangulo = new Rectangulo(10, 5); // Ancho = 10, Alto = 5

        System.out.println("Círculo - Área: " + circulo.calcularArea() + ", Perímetro: " + circulo.calcularPerimetro());
        System.out.println("Rectángulo - Área: " + rectangulo.calcularArea() + ", Perímetro: " + rectangulo.calcularPerimetro());
    }
}
```

✅ **Salida esperada:**
```
Círculo - Área: 78.54, Perímetro: 31.42  
Rectángulo - Área: 50.0, Perímetro: 30.0
```

📌 **Explicación:**  
✔ **`Figura` es una interfaz con métodos abstractos.**  
✔ **`Circulo` y `Rectangulo` implementan `Figura` y definen `calcularArea()` y `calcularPerimetro()`.**  
✔ **Uso de `Math.PI` para cálculos de círculo.**

---

</details>