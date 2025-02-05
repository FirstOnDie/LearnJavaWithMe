# **📌 Día 11: Métodos Estáticos y `final` en Java** 🚀

📌 **Objetivo del día:**  
✅ Aprender el uso de **`static` en métodos y variables**.  
✅ Comprender el propósito de **clases y métodos `final`**.  
✅ **Ejercicio:** Implementar una **clase `MathUtils` con métodos estáticos**.

---

## **1️⃣ ¿Qué es `static` en Java?**

📌 **`static` indica que un atributo o método pertenece a la clase en lugar de a una instancia.**  
✔ Se puede acceder a **métodos estáticos sin crear objetos**.  
✔ Se usa en **utilidades, constantes y gestión de recursos**.

📌 **Ejemplo de variable `static`:**
```java
public class EjemploStatic {
    static int contador = 0;

    public EjemploStatic() {
        contador++; // Incrementa en cada instancia creada
    }
}
```

📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        EjemploStatic obj1 = new EjemploStatic();
        EjemploStatic obj2 = new EjemploStatic();
        System.out.println("Contador: " + EjemploStatic.contador);
    }
}
```
✅ **Salida esperada:**
```
Contador: 2
```

✔ **La variable `contador` es compartida entre todas las instancias.**

---

## **2️⃣ Métodos `static` en Java**

📌 **Los métodos `static` pueden llamarse sin crear un objeto.**  
✔ **No pueden acceder a atributos de instancia** (`this` no está disponible).  
✔ Se usan para **métodos de utilidad** como `Math.pow()`.

📌 **Ejemplo de método `static`:**
```java
public class Utilidades {
    static void imprimirMensaje() {
        System.out.println("Hola desde un método estático.");
    }
}
```

📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        Utilidades.imprimirMensaje(); // Llamada sin instanciar
    }
}
```
✅ **Salida esperada:**
```
Hola desde un método estático.
```

✔ **No es necesario crear un objeto `Utilidades`.**

📌 **Ejemplo de utilidad con `static`:**
```java
public class MathUtils {
    public static double cuadrado(double numero) {
        return numero * numero;
    }
}
```
📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        double resultado = MathUtils.cuadrado(4);
        System.out.println("4^2 = " + resultado);
    }
}
```
✅ **Salida esperada:**
```
4^2 = 16.0
```

✔ **Métodos `static` se usan en cálculos matemáticos, conversión de datos, etc.**

---

## **3️⃣ Clases y Métodos `final` en Java**

📌 **`final` restringe modificaciones:**  
✔ **Una variable `final` no puede cambiar su valor.**  
✔ **Un método `final` no puede ser sobrescrito (`@Override`).**  
✔ **Una clase `final` no puede ser heredada.**

📌 **Ejemplo de variable `final`:**
```java
public class EjemploFinal {
    final double PI = 3.1416; // Constante

    void mostrarPi() {
        System.out.println("El valor de PI es: " + PI);
    }
}
```
✅ **No se puede modificar `PI`.**

📌 **Ejemplo de método `final`:**
```java
public class Figura {
    final void mostrarMensaje() {
        System.out.println("Este es un mensaje de la figura.");
    }
}
```

📌 **Intentar sobrescribir el método `final` generará error:**
```java
public class Circulo extends Figura {
    // ❌ Esto causará un error de compilación
    // @Override
    // void mostrarMensaje() { System.out.println("Mensaje nuevo"); }
}
```

📌 **Ejemplo de clase `final` (no se puede extender):**
```java
public final class Utilidad {
    public static void mostrarMensaje() {
        System.out.println("Clase final no heredable.");
    }
}
```
📌 **Intentar heredar generará error:**
```java
// ❌ ERROR: No se puede heredar de una clase final
// public class NuevaClase extends Utilidad {}
```

✔ **Se usa `final` en clases diseñadas para no ser modificadas.**

---

# **📌 Ejercicio del Día 11: Clase `MathUtils` con Métodos Estáticos** 🎯

📌 **Objetivo:**  
✔ Crear una **clase `MathUtils`** con métodos estáticos:
- `double sumar(double a, double b)`
- `double restar(double a, double b)`
- `double multiplicar(double a, double b)`
- `double dividir(double a, double b)` (verificar división por 0)
- `double potencia(double base, double exponente)`

📌 **Ejemplo de salida esperada:**
```
Suma: 5.0 + 3.0 = 8.0  
Multiplicación: 4.0 * 2.0 = 8.0  
División: 10.0 / 2.0 = 5.0  
Potencia: 3.0^2.0 = 9.0  
```

---

<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
📌 **Clase `MathUtils` con métodos estáticos:**
```java
public final class MathUtils {

    // Método para sumar
    public static double sumar(double a, double b) {
        return a + b;
    }

    // Método para restar
    public static double restar(double a, double b) {
        return a - b;
    }

    // Método para multiplicar
    public static double multiplicar(double a, double b) {
        return a * b;
    }

    // Método para dividir (con verificación)
    public static double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("❌ No se puede dividir entre 0.");
        }
        return a / b;
    }

    // Método para calcular potencia
    public static double potencia(double base, double exponente) {
        return Math.pow(base, exponente);
    }
}
```

📌 **Uso en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Suma: " + MathUtils.sumar(5, 3));
        System.out.println("Multiplicación: " + MathUtils.multiplicar(4, 2));
        System.out.println("División: " + MathUtils.dividir(10, 2));
        System.out.println("Potencia: " + MathUtils.potencia(3, 2));
    }
}
```

✅ **Salida esperada:**
```
Suma: 8.0  
Multiplicación: 8.0  
División: 5.0  
Potencia: 9.0  
```

📌 **Explicación del código:**  
✔ **`MathUtils` es `final`**, por lo que no puede ser heredada.  
✔ **Todos los métodos son `static`**, accesibles sin instanciar la clase.  
✔ **`dividir()` maneja la división por 0 con `throw new ArithmeticException()`.**

---

</details>