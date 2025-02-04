# **📌 Día 5: Métodos y Funciones en Java** 🚀

📌 **Objetivo del día:**  
✅ **Definir y llamar métodos** en Java.  
✅ **Entender parámetros y valores de retorno.**  
✅ **Diferenciar entre métodos `static` y de instancia.**  
✅ **Ejercicio:** Crear un **programa modularizado** para operaciones matemáticas.

---

## **1️⃣ ¿Qué es un Método en Java?**
📌 **Un método** es un bloque de código reutilizable que realiza una tarea específica.  
✔ Se usa para organizar mejor el código y evitar repeticiones.  
✔ Puede recibir **parámetros** y devolver **un valor**.

---

## **2️⃣ Definir y Llamar Métodos**

📌 **Estructura de un método en Java:**
```java
public class EjemploMetodo {
    // Definimos un método
    static void saludar() {
        System.out.println("¡Hola desde un método!");
    }

    public static void main(String[] args) {
        saludar(); // Llamamos al método
    }
}
```
✅ **Salida esperada:**
```
¡Hola desde un método!
```

📌 **Explicación:**  
✔ `static void saludar()` → Declara un método **sin retorno** (`void`).  
✔ `System.out.println()` → Código dentro del método.  
✔ `saludar();` → Llama al método dentro de `main()`.

---

## **3️⃣ Métodos con Parámetros y Valores de Retorno**

📌 **Ejemplo de método con parámetros:**
```java
public class MetodosConParametros {
    static void saludar(String nombre) {
        System.out.println("Hola, " + nombre + "!");
    }

    public static void main(String[] args) {
        saludar("Juan"); // Llamada con argumento "Juan"
        saludar("Ana");
    }
}
```
✅ **Salida esperada:**
```
Hola, Juan!  
Hola, Ana!
```

---

📌 **Ejemplo de método que devuelve un valor (`return`)**
```java
public class MetodosConRetorno {
    static int sumar(int a, int b) {
        return a + b; // Retorna la suma
    }

    public static void main(String[] args) {
        int resultado = sumar(5, 7);
        System.out.println("Suma: " + resultado);
    }
}
```
✅ **Salida esperada:**
```
Suma: 12
```

📌 **Explicación:**  
✔ `int sumar(int a, int b) {}` → Recibe dos parámetros y retorna un `int`.  
✔ `return a + b;` → Retorna el resultado de la suma.  
✔ `int resultado = sumar(5, 7);` → Almacena el resultado en una variable.

---

## **4️⃣ Métodos `static` vs Métodos de Instancia**

📌 **Métodos `static`**  
✔ Pertenecen a la clase y no requieren un objeto.  
✔ Se llaman con `NombreClase.metodo()`.  
✔ No pueden acceder a variables de instancia directamente.

📌 **Ejemplo de método `static`:**
```java
public class MathUtils {
    static int multiplicar(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
        System.out.println("Multiplicación: " + MathUtils.multiplicar(4, 3));
    }
}
```
✅ **Salida esperada:**
```
Multiplicación: 12
```

---

📌 **Métodos de Instancia**  
✔ Requieren crear un **objeto** de la clase para ser llamados.  
✔ Pueden acceder a variables de instancia.

📌 **Ejemplo de método de instancia:**
```java
public class Calculadora {
    int sumar(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        Calculadora calc = new Calculadora(); // Crear un objeto
        int resultado = calc.sumar(8, 4);
        System.out.println("Suma: " + resultado);
    }
}
```
✅ **Salida esperada:**
```
Suma: 12
```

📌 **Diferencias clave:**  
✔ `static` → Se puede llamar sin crear un objeto (`MathUtils.multiplicar(4, 3)`).  
✔ **De instancia** → Necesita un objeto (`calc.sumar(8, 4)`).

---

# **📌 Ejercicio del Día 5: Calculadora Modularizada** 🎯

📌 **Objetivo:** Crear un programa con métodos separados para realizar operaciones matemáticas (`+`, `-`, `*`, `/`).

✔ **Entrada:** Dos números y un operador (`+`, `-`, `*`, `/`).  
✔ **Salida esperada:**
```
Ingrese el primer número: 10  
Ingrese el segundo número: 5  
Seleccione la operación (+, -, *, /): *  
Resultado: 50
```

---

<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
```java
import java.util.Scanner;

public class CalculadoraModularizada {
    // Métodos para cada operación
    static double sumar(double a, double b) {
        return a + b;
    }

    static double restar(double a, double b) {
        return a - b;
    }

    static double multiplicar(double a, double b) {
        return a * b;
    }

    static double dividir(double a, double b) {
        if (b == 0) {
            System.out.println("Error: No se puede dividir entre 0.");
            return 0;
        }
        return a / b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir los números
        System.out.print("Ingrese el primer número: ");
        double num1 = scanner.nextDouble();

        System.out.print("Ingrese el segundo número: ");
        double num2 = scanner.nextDouble();

        // Pedir la operación
        System.out.print("Seleccione la operación (+, -, *, /): ");
        char operacion = scanner.next().charAt(0);

        double resultado = 0;
        boolean operacionValida = true;

        // Llamar al método correspondiente
        switch (operacion) {
            case '+': resultado = sumar(num1, num2); break;
            case '-': resultado = restar(num1, num2); break;
            case '*': resultado = multiplicar(num1, num2); break;
            case '/': resultado = dividir(num1, num2); break;
            default:
                System.out.println("Operación inválida.");
                operacionValida = false;
        }

        // Mostrar resultado si la operación fue válida
        if (operacionValida) {
            System.out.println("Resultado: " + resultado);
        }

        scanner.close();
    }
}
```

✅ **Explicación del código:**  
✔ **Cada operación es un método (`sumar()`, `restar()`, `multiplicar()`, `dividir()`).**  
✔ **Se usa un `switch` para elegir la operación.**  
✔ **`dividir()` verifica que `b != 0` antes de dividir.**  
✔ **El programa es modularizado y reutilizable.**

---

</details>