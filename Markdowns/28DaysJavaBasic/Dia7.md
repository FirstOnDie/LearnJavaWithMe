# **📌 Día 7: Introducción a la Programación Orientada a Objetos (POO) en Java** 🚀

📌 **Objetivo del día:**  
✅ Entender qué es la **Programación Orientada a Objetos (POO)**.  
✅ Aprender a crear **clases y objetos** en Java.  
✅ Comprender el uso de **`new` y `this`**.  
✅ Diferenciar **atributos y métodos**.  
✅ **Ejercicio:** Crear una clase `Coche` con atributos y métodos básicos.

---

## **1️⃣ ¿Qué es la Programación Orientada a Objetos (POO)?**
📌 **POO es un paradigma de programación basado en la organización del código en objetos.**

🔹 **Un objeto** es una entidad con **propiedades (atributos) y comportamientos (métodos)**.  
🔹 **Una clase** es un molde o plantilla para crear objetos.

📌 **Ejemplo en la vida real:**  
Imagina que queremos representar un **coche** en Java:  
✔ **Atributos:** Color, marca, modelo, velocidad.  
✔ **Métodos:** Acelerar, frenar, girar.

💡 **En Java, un objeto se crea a partir de una clase.**

---

## **2️⃣ Clases y Objetos en Java**

📌 **Ejemplo de una clase en Java:**
```java
// Definimos la clase Coche
public class Coche {
    // Atributos
    String marca;
    String modelo;
    int velocidad;

    // Método para acelerar
    void acelerar() {
        velocidad += 10;
        System.out.println("El coche aceleró. Velocidad actual: " + velocidad + " km/h");
    }
}
```

📌 **Crear y usar un objeto en Java:**
```java
public class Main {
    public static void main(String[] args) {
        Coche miCoche = new Coche(); // Crear un objeto de la clase Coche
        miCoche.marca = "Toyota";
        miCoche.modelo = "Corolla";
        miCoche.velocidad = 0;

        System.out.println("Marca: " + miCoche.marca);
        System.out.println("Modelo: " + miCoche.modelo);
        miCoche.acelerar();
    }
}
```

✅ **Salida esperada:**
```
Marca: Toyota  
Modelo: Corolla  
El coche aceleró. Velocidad actual: 10 km/h  
```

💡 **Notas:**  
✔ `Coche miCoche = new Coche();` → **Creamos un objeto** de la clase `Coche`.  
✔ `miCoche.acelerar();` → **Llamamos a un método** para modificar su estado.

---

## **3️⃣ Uso de `new` y `this` en Java**

📌 **El operador `new` crea un nuevo objeto en memoria.**
```java
Coche otroCoche = new Coche(); // Crea un nuevo objeto de la clase Coche
```

📌 **El uso de `this` para referenciar atributos de la misma clase.**  
💡 **Se usa cuando un atributo tiene el mismo nombre que un parámetro.**
```java
public class Coche {
    String marca;
    String modelo;

    // Constructor con parámetros
    public Coche(String marca, String modelo) {
        this.marca = marca;   // "this.marca" se refiere al atributo de la clase
        this.modelo = modelo; // "modelo" es el parámetro recibido
    }
}
```

📌 **Crear un objeto usando el constructor con `this`:**
```java
public class Main {
    public static void main(String[] args) {
        Coche miCoche = new Coche("Honda", "Civic");
        System.out.println("Marca: " + miCoche.marca);
        System.out.println("Modelo: " + miCoche.modelo);
    }
}
```
✅ **Salida esperada:**
```
Marca: Honda  
Modelo: Civic  
```

---

## **4️⃣ Atributos vs Métodos**

📌 **Diferencias entre atributos y métodos:**

| **Atributos** 🏷️ | **Métodos** 🔧 |
|-------------------|-------------------|
| Describen las características del objeto. | Definen el comportamiento del objeto. |
| Ejemplo: `String color;` | Ejemplo: `void acelerar() {}` |
| Se almacenan en memoria junto con el objeto. | Se ejecutan cuando se invocan. |

📌 **Ejemplo de atributos y métodos en una clase `Persona`:**
```java
public class Persona {
    // Atributos
    String nombre;
    int edad;

    // Método
    void saludar() {
        System.out.println("Hola, soy " + nombre + " y tengo " + edad + " años.");
    }
}
```

📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        Persona persona1 = new Persona();
        persona1.nombre = "Carlos";
        persona1.edad = 25;
        persona1.saludar();
    }
}
```
✅ **Salida esperada:**
```
Hola, soy Carlos y tengo 25 años.
```

---

# **📌 Ejercicio del Día 7: Clase `Coche` con Atributos y Métodos** 🎯

📌 **Objetivo:**  
✔ Crear una clase `Coche` con los atributos:
- `marca` (String)
- `modelo` (String)
- `velocidad` (int)

✔ Agregar métodos:
- `acelerar()` → Aumenta la velocidad en 10 km/h.
- `frenar()` → Reduce la velocidad en 10 km/h (mínimo 0).
- `mostrarInfo()` → Muestra la información del coche.

✔ **Ejemplo de salida esperada:**
```
Marca: Ford  
Modelo: Mustang  
Velocidad: 0 km/h  
El coche aceleró. Velocidad actual: 10 km/h  
El coche frenó. Velocidad actual: 0 km/h  
```

---

<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
```java
public class Coche {
    // Atributos
    String marca;
    String modelo;
    int velocidad;

    // Constructor
    public Coche(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidad = 0; // Velocidad inicial
    }

    // Método para acelerar
    void acelerar() {
        velocidad += 10;
        System.out.println("El coche aceleró. Velocidad actual: " + velocidad + " km/h");
    }

    // Método para frenar
    void frenar() {
        if (velocidad >= 10) {
            velocidad -= 10;
        } else {
            velocidad = 0;
        }
        System.out.println("El coche frenó. Velocidad actual: " + velocidad + " km/h");
    }

    // Método para mostrar información
    void mostrarInfo() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Velocidad: " + velocidad + " km/h");
    }
}
```

📌 **Uso de la clase `Coche` en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        Coche miCoche = new Coche("Ford", "Mustang");

        miCoche.mostrarInfo();
        miCoche.acelerar();
        miCoche.frenar();
    }
}
```

✅ **Salida esperada:**
```
Marca: Ford  
Modelo: Mustang  
Velocidad: 0 km/h  
El coche aceleró. Velocidad actual: 10 km/h  
El coche frenó. Velocidad actual: 0 km/h  
```

---

</details>