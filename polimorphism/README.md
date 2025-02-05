# **📌 Polimorfismo en Java** 🎭🚀

📌 **¿Qué es el Polimorfismo?**  
El **polimorfismo** proviene del griego **"poli" (muchos) y "morfos" (formas)**, lo que significa que un mismo objeto puede comportarse de múltiples maneras.

En Java, el polimorfismo permite que un **método se ejecute de diferentes formas dependiendo del contexto**. Esto hace que nuestro código sea más **flexible, reutilizable y fácil de mantener**.

📌 **Ejemplo simple:**  
Todos los animales hacen un sonido, pero **cada uno tiene su propia manera de hacerlo**:
```java
class Animal {
    public void hacerSonido() {
        System.out.println("Algún sonido de animal");
    }
}

class Perro extends Animal {
    @Override
    public void hacerSonido() {
        System.out.println("Guau");
    }
}

class Gato extends Animal {
    @Override
    public void hacerSonido() {
        System.out.println("Miau");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal miPerro = new Perro();
        Animal miGato = new Gato();

        miPerro.hacerSonido();  // Imprime "Guau"
        miGato.hacerSonido();   // Imprime "Miau"
    }
}
```
✅ **Explicación:**  
✔ **`Animal`** → Clase base con un método `hacerSonido()`.  
✔ **`Perro` y `Gato`** → Clases que sobrescriben el método para hacer un sonido diferente.  
✔ **El polimorfismo permite tratar a `miPerro` y `miGato` como `Animal`, pero ejecutan sus propias versiones del método.**

---

# **📌 Tipos de Polimorfismo en Java**

### **1️⃣ Polimorfismo de Sobrecarga (Overloading Polymorphism)**
**📌 Concepto:**  
Un **mismo método puede tener múltiples versiones** con diferentes parámetros.

📌 **Ejemplo:**
```java
class Robot {
    public void saludar() {
        System.out.println("Hola!");
    }

    public void saludar(String nombre) {
        System.out.println("Hola, " + nombre + "!");
    }

    public void saludar(String nombre, boolean formal) {
        if (formal) {
            System.out.println("Buenos días, Sr./Sra. " + nombre);
        } else {
            System.out.println("¡Hey, " + nombre + "!");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Robot robot = new Robot();
        robot.saludar();                // "Hola!"
        robot.saludar("Carlos");        // "Hola, Carlos!"
        robot.saludar("María", true);   // "Buenos días, Sr./Sra. María"
    }
}
```
✅ **Explicación:**  
✔ **Mismo método `saludar()`** → Pero con diferentes firmas (diferentes parámetros).  
✔ **Java elige automáticamente cuál usar según los parámetros que pasemos.**

---

### **2️⃣ Polimorfismo de Sobrescritura (Overriding Polymorphism)**
**📌 Concepto:**  
Una **clase hija puede redefinir** un método de la clase padre para cambiar su comportamiento.

📌 **Ejemplo:**
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

class Gato extends Animal {
    @Override
    public void hacerSonido() {
        System.out.println("Miau!");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal miAnimal = new Perro();
        miAnimal.hacerSonido(); // "Guau!"

        miAnimal = new Gato();
        miAnimal.hacerSonido(); // "Miau!"
    }
}
```
✅ **Explicación:**  
✔ **`@Override`** indica que un método de la clase padre se está sobrescribiendo.  
✔ **El método `hacerSonido()` ejecuta la versión específica de la clase hija, aunque se use una variable de tipo `Animal`**.

---

### **3️⃣ Polimorfismo de Coerción (Coercion Polymorphism)**
**📌 Concepto:**  
Java **convierte automáticamente** un tipo de dato en otro cuando es necesario.

📌 **Ejemplo:**
```java
public class Main {
    public static void main(String[] args) {
        int numeroEntero = 10;
        double numeroDecimal = numeroEntero; // Conversión automática de int a double

        System.out.println("Número decimal: " + numeroDecimal);
    }
}
```
✅ **Explicación:**  
✔ **Java convierte `int` en `double` automáticamente sin errores.**  
✔ **Se llama "coerción implícita" porque el compilador lo hace sin intervención del programador.**

---

### **4️⃣ Polimorfismo Paramétrico (Parametric Polymorphism)**
**📌 Concepto:**  
Usamos **genéricos (`<T>`)** para que una clase o método funcione con cualquier tipo de dato.

📌 **Ejemplo:**
```java
class Caja<T> {
    private T contenido;

    public void guardar(T objeto) {
        this.contenido = objeto;
    }

    public T obtener() {
        return contenido;
    }
}

public class Main {
    public static void main(String[] args) {
        Caja<String> cajaDeTexto = new Caja<>();
        cajaDeTexto.guardar("Hola, mundo!");
        System.out.println(cajaDeTexto.obtener()); // "Hola, mundo!"

        Caja<Integer> cajaDeNumeros = new Caja<>();
        cajaDeNumeros.guardar(100);
        System.out.println(cajaDeNumeros.obtener()); // 100
    }
}
```
✅ **Explicación:**  
✔ **Clase `Caja<T>`** → Puede almacenar cualquier tipo de dato (`String`, `Integer`, etc.).  
✔ **Permite reutilizar el código sin necesidad de definir múltiples clases.**

---

# **📌 Beneficios del Polimorfismo en Java**

✅ **Código más limpio y modular** → Separamos la lógica en clases reutilizables.  
✅ **Flexibilidad** → Podemos tratar diferentes objetos como un tipo común (`Animal`, `Transporte`, etc.).  
✅ **Extensibilidad** → Podemos agregar nuevas clases sin modificar el código existente.

---

# **📌 Ejemplo Final: Sistema de Vehículos con Polimorfismo** 🚗✈️🚴

📌 **Objetivo:**  
✔ Crear un sistema de transporte donde **diferentes vehículos se muevan de distintas maneras.**

```java
abstract class Vehiculo {
    public abstract void moverse();
}

class Bicicleta extends Vehiculo {
    @Override
    public void moverse() {
        System.out.println("🚲 La bicicleta se mueve pedaleando.");
    }
}

class Coche extends Vehiculo {
    @Override
    public void moverse() {
        System.out.println("🚗 El coche se mueve acelerando.");
    }
}

class Avion extends Vehiculo {
    @Override
    public void moverse() {
        System.out.println("✈️ El avión se mueve volando.");
    }
}

public class Main {
    public static void main(String[] args) {
        Vehiculo[] vehiculos = { new Bicicleta(), new Coche(), new Avion() };

        for (Vehiculo v : vehiculos) {
            v.moverse(); 
        }
    }
}
```
✅ **Salida esperada:**
```
🚲 La bicicleta se mueve pedaleando.
🚗 El coche se mueve acelerando.
✈️ El avión se mueve volando.
```
📌 **¿Por qué es útil?**  
✔ **Podemos agregar más tipos de vehículos sin modificar el código principal.**  
✔ **Usamos `Vehiculo` como tipo genérico, pero cada uno se comporta de manera diferente.**

---
