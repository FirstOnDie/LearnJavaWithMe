# **📌 Día 15: Manejo de Excepciones en Java** 🚀

📌 **Objetivo del día:**  
✅ Aprender el uso de **`try`, `catch`, `finally`** para manejar errores.  
✅ Conocer **excepciones verificadas (`checked`) y no verificadas (`unchecked`)**.  
✅ Crear **excepciones personalizadas** con `extends Exception`.  
✅ **Ejercicio:** Implementar un **gestor de cuentas bancarias con manejo de excepciones**.

---

## **1️⃣ ¿Qué es una Excepción en Java?**

📌 **Una excepción es un evento que interrumpe el flujo normal del programa.**  
✔ Se usa **`try-catch`** para manejar errores en tiempo de ejecución.  
✔ Evita que la aplicación **se cierre inesperadamente**.

📌 **Ejemplo de error sin manejo de excepciones:**
```java
public class ErrorDivision {
    public static void main(String[] args) {
        int a = 10, b = 0;
        int resultado = a / b; // ❌ Error: División por 0
        System.out.println("Resultado: " + resultado);
    }
}
```
✅ **Salida esperada (con error):**
```
Exception in thread "main" java.lang.ArithmeticException: / by zero
```

✔ **Solución:** Manejar el error con `try-catch`.

---

# **2️⃣ Manejo de Excepciones con `try-catch-finally`**

📌 **Uso de `try-catch` para evitar errores:**
```java
public class ManejoExcepciones {
    public static void main(String[] args) {
        int a = 10, b = 0;
        try {
            int resultado = a / b;
            System.out.println("Resultado: " + resultado);
        } catch (ArithmeticException e) {
            System.out.println("❌ Error: No se puede dividir entre 0.");
        }
        System.out.println("El programa sigue ejecutándose.");
    }
}
```
✅ **Salida esperada:**
```
❌ Error: No se puede dividir entre 0.  
El programa sigue ejecutándose.
```

✔ **El error no detiene la ejecución del programa.**

---

📌 **Uso de `finally` para código que siempre debe ejecutarse:**
```java
public class EjemploFinally {
    public static void main(String[] args) {
        try {
            int resultado = 10 / 2;
            System.out.println("Resultado: " + resultado);
        } catch (ArithmeticException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            System.out.println("✅ Operación finalizada.");
        }
    }
}
```
✅ **Salida esperada:**
```
Resultado: 5  
✅ Operación finalizada.
```

✔ **`finally` se ejecuta siempre, haya o no error.**

---

# **3️⃣ Tipos de Excepciones en Java**

📌 **Excepciones verificadas (`Checked Exceptions`)**  
✔ **Obligan a usar `try-catch` o `throws`** en la firma del método.  
✔ Ejemplo: **`IOException`, `SQLException`, `FileNotFoundException`**.

📌 **Excepciones no verificadas (`Unchecked Exceptions`)**  
✔ **No requieren `try-catch`, pero pueden causar errores en ejecución.**  
✔ Ejemplo: **`NullPointerException`, `ArrayIndexOutOfBoundsException`**.

📌 **Ejemplo de excepción verificada (`Checked`):**
```java
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeerArchivo {
    public static void main(String[] args) {
        try {
            File archivo = new File("archivo.txt");
            FileReader reader = new FileReader(archivo);
        } catch (IOException e) {
            System.out.println("❌ Error al leer el archivo: " + e.getMessage());
        }
    }
}
```
✅ **Salida esperada si el archivo no existe:**
```
❌ Error al leer el archivo: archivo.txt (No such file or directory)
```

✔ **`IOException` es verificada, por lo que `try-catch` es obligatorio.**

---

# **4️⃣ Crear Excepciones Personalizadas**

📌 **Ejemplo: `SaldoInsuficienteException` en una cuenta bancaria**
```java
// Definir excepción personalizada
class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
```

📌 **Uso en una clase `CuentaBancaria`:**
```java
class CuentaBancaria {
    private double saldo;

    public CuentaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void retirar(double cantidad) throws SaldoInsuficienteException {
        if (cantidad > saldo) {
            throw new SaldoInsuficienteException("❌ Saldo insuficiente. Saldo actual: " + saldo);
        }
        saldo -= cantidad;
        System.out.println("✅ Retiro exitoso. Nuevo saldo: " + saldo);
    }
}
```

📌 **Uso en `main()` con `try-catch`:**
```java
public class Main {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(100.0);

        try {
            cuenta.retirar(150.0); // ❌ Provoca excepción
        } catch (SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
        }
    }
}
```
✅ **Salida esperada:**
```
❌ Saldo insuficiente. Saldo actual: 100.0
```

✔ **Excepciones personalizadas ayudan a mejorar la claridad del código.**

---

# **📌 Ejercicio del Día 15: Gestor de Cuentas Bancarias con Excepciones** 🎯

📌 **Objetivo:**  
✔ Implementar una **clase `CuentaBancaria`** con:
- `double saldo`
- Métodos: `depositar(double)`, `retirar(double)`  
  ✔ Manejar errores con **`SaldoInsuficienteException`**.

📌 **Ejemplo de salida esperada:**
```
Ingrese monto a retirar: 300  
❌ Saldo insuficiente. Saldo actual: 200.0
```

---

<details>
    <summary>Solución</summary>

### **✅ Solución en Java**

📌 **Definir `SaldoInsuficienteException`:**
```java
class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
```

📌 **Clase `CuentaBancaria` con manejo de excepciones:**
```java
class CuentaBancaria {
    private double saldo;

    public CuentaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void depositar(double cantidad) {
        saldo += cantidad;
        System.out.println("✅ Depósito realizado. Nuevo saldo: " + saldo);
    }

    public void retirar(double cantidad) throws SaldoInsuficienteException {
        if (cantidad > saldo) {
            throw new SaldoInsuficienteException("❌ Saldo insuficiente. Saldo actual: " + saldo);
        }
        saldo -= cantidad;
        System.out.println("✅ Retiro exitoso. Nuevo saldo: " + saldo);
    }

    public double getSaldo() {
        return saldo;
    }
}
```

📌 **Uso en `main()` con `Scanner`:**
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CuentaBancaria cuenta = new CuentaBancaria(200.0);

        System.out.print("Ingrese monto a retirar: ");
        double monto = scanner.nextDouble();

        try {
            cuenta.retirar(monto);
        } catch (SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
```

✅ **Salida esperada:**
```
Ingrese monto a retirar: 300  
❌ Saldo insuficiente. Saldo actual: 200.0
```

📌 **Explicación:**  
✔ **Excepción personalizada `SaldoInsuficienteException`** mejora la claridad del código.  
✔ **Manejo con `try-catch`** evita que el programa se cierre inesperadamente.

---

</details>