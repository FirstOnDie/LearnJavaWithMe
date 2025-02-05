# **📌 Mini-Proyecto Semana 1: Calculadora Científica en Java** 🚀

📌 **Objetivo:**  
✅ Implementar una **calculadora científica** en Java.  
✅ Aplicar **POO, métodos y control de flujo**.  
✅ Usar **`Math` y estructuras de control (`switch`)** para operaciones avanzadas.  
✅ Mejorar la entrada de datos con **validaciones y manejo de excepciones**.

---

# **📌 1️⃣ Requisitos del Proyecto**

📌 **Funcionalidades:**  
✔ Realizar operaciones básicas: **suma, resta, multiplicación, división**.  
✔ Operaciones científicas avanzadas: **potencia, raíz cuadrada, logaritmo, seno, coseno, tangente**.  
✔ Manejo de errores: **evitar divisiones por 0 y validar entradas**.

📌 **Ejemplo de salida esperada:**
```
🔢 CALCULADORA CIENTÍFICA 🔢
1. Suma
2. Resta
3. Multiplicación
4. División
5. Potencia
6. Raíz Cuadrada
7. Logaritmo
8. Seno
9. Coseno
10. Tangente
0. Salir
Seleccione una opción: 5
Ingrese el primer número: 2
Ingrese el segundo número: 3
Resultado: 2^3 = 8.0
```

---
<details>
    <summary>Solución</summary>

# **📌 2️⃣ Diseño del Proyecto**

📌 **Clases necesarias:**  
✅ **`Calculadora`** → Contiene métodos para cada operación matemática.  
✅ **`Main`** → Gestiona la interacción con el usuario.

📌 **Uso de `Math` para funciones científicas:**  
✔ `Math.pow(a, b)` → Calcula la potencia.  
✔ `Math.sqrt(a)` → Calcula la raíz cuadrada.  
✔ `Math.log(a)` → Calcula el logaritmo natural.  
✔ `Math.sin(a)`, `Math.cos(a)`, `Math.tan(a)` → Calcula seno, coseno y tangente.

📌 **Manejo de Excepciones (`try-catch`)** para evitar errores.

---

# **📌 3️⃣ Implementación del Código**

📌 **Clase `Calculadora`: Implementación de métodos científicos**
```java
public class Calculadora {

    // Operaciones básicas
    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("❌ Error: No se puede dividir entre 0.");
        }
        return a / b;
    }

    // Operaciones científicas
    public double potencia(double base, double exponente) {
        return Math.pow(base, exponente);
    }

    public double raizCuadrada(double numero) {
        if (numero < 0) {
            throw new ArithmeticException("❌ Error: No se puede calcular la raíz cuadrada de un número negativo.");
        }
        return Math.sqrt(numero);
    }

    public double logaritmo(double numero) {
        if (numero <= 0) {
            throw new ArithmeticException("❌ Error: No se puede calcular el logaritmo de un número <= 0.");
        }
        return Math.log(numero);
    }

    public double seno(double angulo) {
        return Math.sin(Math.toRadians(angulo));
    }

    public double coseno(double angulo) {
        return Math.cos(Math.toRadians(angulo));
    }

    public double tangente(double angulo) {
        return Math.tan(Math.toRadians(angulo));
    }
}
```

---

📌 **Clase `Main`: Interacción con el Usuario**
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculadora calculadora = new Calculadora();

        while (true) {
            // Mostrar menú de opciones
            System.out.println("\n🔢 CALCULADORA CIENTÍFICA 🔢");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicación");
            System.out.println("4. División");
            System.out.println("5. Potencia");
            System.out.println("6. Raíz Cuadrada");
            System.out.println("7. Logaritmo");
            System.out.println("8. Seno");
            System.out.println("9. Coseno");
            System.out.println("10. Tangente");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion;
            try {
                opcion = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("❌ Error: Debe ingresar un número válido.");
                scanner.next(); // Limpiar buffer
                continue;
            }

            if (opcion == 0) {
                System.out.println("👋 ¡Gracias por usar la calculadora!");
                break;
            }

            double num1 = 0, num2 = 0, resultado = 0;

            // Pedir valores si la operación lo requiere
            if (opcion >= 1 && opcion <= 5) {
                System.out.print("Ingrese el primer número: ");
                num1 = scanner.nextDouble();
                System.out.print("Ingrese el segundo número: ");
                num2 = scanner.nextDouble();
            } else if (opcion >= 6 && opcion <= 10) {
                System.out.print("Ingrese el número o ángulo: ");
                num1 = scanner.nextDouble();
            } else {
                System.out.println("❌ Opción no válida.");
                continue;
            }

            try {
                // Ejecutar la operación seleccionada
                switch (opcion) {
                    case 1 -> resultado = calculadora.sumar(num1, num2);
                    case 2 -> resultado = calculadora.restar(num1, num2);
                    case 3 -> resultado = calculadora.multiplicar(num1, num2);
                    case 4 -> resultado = calculadora.dividir(num1, num2);
                    case 5 -> resultado = calculadora.potencia(num1, num2);
                    case 6 -> resultado = calculadora.raizCuadrada(num1);
                    case 7 -> resultado = calculadora.logaritmo(num1);
                    case 8 -> resultado = calculadora.seno(num1);
                    case 9 -> resultado = calculadora.coseno(num1);
                    case 10 -> resultado = calculadora.tangente(num1);
                }
                System.out.println("📌 Resultado: " + resultado);
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
```

---

# **📌 4️⃣ Explicación del Código**

📌 **📂 `Calculadora.java`** → Contiene métodos para cada operación matemática.  
✔ **Evita errores con `throw new ArithmeticException()`**.  
✔ **Usa `Math` para operaciones científicas** (`pow`, `sqrt`, `log`, `sin`, etc.).

📌 **📂 `Main.java`** → Controla la interacción con el usuario.  
✔ **Menú interactivo con `switch-case`.**  
✔ **Manejo de excepciones con `try-catch`** para evitar errores al ingresar datos.  
✔ **Valida operaciones inválidas (`división por 0`, `logaritmo de números negativos`, etc.).**

---

# **📌 5️⃣ Pruebas y Ejemplo de Uso**

✔ **Ejemplo 1: Operación básica (Suma)**
```
Seleccione una opción: 1
Ingrese el primer número: 10
Ingrese el segundo número: 5
📌 Resultado: 15.0
```

✔ **Ejemplo 2: Potencia**
```
Seleccione una opción: 5
Ingrese el primer número: 2
Ingrese el segundo número: 3
📌 Resultado: 8.0
```

✔ **Ejemplo 3: División por 0 (manejo de errores)**
```
Seleccione una opción: 4
Ingrese el primer número: 10
Ingrese el segundo número: 0
❌ Error: No se puede dividir entre 0.
```

---

</details>