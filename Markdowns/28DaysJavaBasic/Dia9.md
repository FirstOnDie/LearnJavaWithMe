# **📌 Día 9: Herencia y Polimorfismo en Java** 🚀

📌 **Objetivo del día:**  
✅ Aprender a extender clases usando **`extends`**.  
✅ Comprender la **sobreescritura de métodos** con **`@Override`**.  
✅ Usar **`super`** para invocar métodos de la clase base.  
✅ **Ejercicio:** Implementar un **sistema de empleados** con herencia (`Empleado`, `Gerente`).

---

## **1️⃣ ¿Qué es la Herencia en Java?**

📌 **La herencia permite que una clase (subclase) herede atributos y métodos de otra clase (superclase).**  
✔ Permite **reutilizar código** y evitar duplicación.  
✔ Usa la palabra clave **`extends`**.

📌 **Ejemplo de herencia:**
```java
// Clase padre (superclase)
public class Animal {
    String nombre;

    void hacerSonido() {
        System.out.println("El animal hace un sonido.");
    }
}

// Clase hija (subclase)
public class Perro extends Animal {
    void ladrar() {
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
        perro.hacerSonido(); // Método heredado
        perro.ladrar();      // Método de la subclase
    }
}
```
✅ **Salida esperada:**
```
El animal hace un sonido.  
Max dice: ¡Guau!
```

✔ **`Perro` hereda de `Animal` y obtiene su método `hacerSonido()`.**  
✔ **También define su propio método `ladrar()`.**

---

## **2️⃣ Sobreescritura de Métodos con `@Override`**

📌 **La sobreescritura permite modificar el comportamiento de un método heredado.**  
✔ Se usa la anotación **`@Override`**.

📌 **Ejemplo sin sobreescritura:**
```java
public class Gato extends Animal {
    void hacerSonido() {
        System.out.println(nombre + " dice: ¡Miau!");
    }
}
```

📌 **Ejemplo con `@Override`:**
```java
public class Gato extends Animal {
    @Override
    void hacerSonido() {
        System.out.println(nombre + " dice: ¡Miau!");
    }
}
```

📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        Gato gato = new Gato();
        gato.nombre = "Luna";
        gato.hacerSonido(); // Se ejecuta el método sobreescrito
    }
}
```
✅ **Salida esperada:**
```
Luna dice: ¡Miau!
```

✔ **`@Override` indica que `hacerSonido()` ha sido modificado en `Gato`.**

---

## **3️⃣ Uso de `super` y Clases Base**

📌 **`super` permite acceder a métodos y constructores de la clase padre.**

📌 **Ejemplo con `super`:**
```java
public class Animal {
    String nombre;

    void hacerSonido() {
        System.out.println("El animal hace un sonido.");
    }
}

public class Perro extends Animal {
    @Override
    void hacerSonido() {
        super.hacerSonido(); // Llama al método de la clase padre
        System.out.println("El perro ladra.");
    }
}
```

📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        Perro perro = new Perro();
        perro.hacerSonido();
    }
}
```
✅ **Salida esperada:**
```
El animal hace un sonido.  
El perro ladra.
```

✔ **`super.hacerSonido();` ejecuta primero el método de `Animal`.**  
✔ Luego, **`Perro` agrega su propio comportamiento.**

---

# **📌 Ejercicio del Día 9: Sistema de Empleados con Herencia** 🎯

📌 **Objetivo:**  
✔ Crear una **clase `Empleado`** con los atributos:
- `nombre` (`String`)
- `salario` (`double`)

✔ Crear una **clase `Gerente`** que herede de `Empleado` y agregue:
- `bono` (`double`)

✔ Métodos:
- `calcularSalarioTotal()` → Devuelve el salario más el bono (si aplica).

📌 **Ejemplo de salida esperada:**
```
Empleado: Ana - Salario: 2500.0€
Gerente: Carlos - Salario Total: 5000.0€
```

---

<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
```java
// Clase base (Empleado)
public class Empleado {
    protected String nombre;
    protected double salario;

    // Constructor
    public Empleado(String nombre, double salario) {
        this.nombre = nombre;
        this.salario = salario;
    }

    // Método para mostrar información del empleado
    public void mostrarInfo() {
        System.out.println("Empleado: " + nombre + " - Salario: " + salario + "€");
    }

    // Método para obtener el salario
    public double getSalario() {
        return salario;
    }
}
```

📌 **Clase `Gerente` que extiende `Empleado` y añade `bono`:**
```java
// Clase derivada (Gerente)
public class Gerente extends Empleado {
    private double bono;

    // Constructor con `super`
    public Gerente(String nombre, double salario, double bono) {
        super(nombre, salario); // Llama al constructor de Empleado
        this.bono = bono;
    }

    // Método sobreescrito para calcular salario total
    @Override
    public void mostrarInfo() {
        System.out.println("Gerente: " + nombre + " - Salario Total: " + getSalarioTotal() + "€");
    }

    // Método para calcular el salario total
    public double getSalarioTotal() {
        return salario + bono;
    }
}
```

📌 **Uso de las clases en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        // Crear empleado normal
        Empleado empleado = new Empleado("Ana", 2500.0);
        empleado.mostrarInfo();

        // Crear gerente con bono
        Gerente gerente = new Gerente("Carlos", 4000.0, 1000.0);
        gerente.mostrarInfo();
    }
}
```

✅ **Salida esperada:**
```
Empleado: Ana - Salario: 2500.0€  
Gerente: Carlos - Salario Total: 5000.0€
```

📌 **Explicación del código:**  
✔ **`Gerente` hereda de `Empleado` y reutiliza atributos y métodos.**  
✔ **`super(nombre, salario)` llama al constructor de `Empleado`.**  
✔ **`getSalarioTotal()` calcula el salario base más el bono.**  
✔ **Polimorfismo:** `Gerente` redefine `mostrarInfo()`.

---

</details>