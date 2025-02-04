# **📌 Día 3: Operadores y Expresiones en Java** 🚀

📌 **Objetivo del día:**  
✅ Aprender los **operadores aritméticos, lógicos y relacionales** en Java.  
✅ Comprender la **precedencia de operadores** para evaluar expresiones correctamente.  
✅ **Ejercicio:** Crear una **calculadora simple** en Java.

---

## **1️⃣ Operadores en Java**

📌 **Java tiene varios tipos de operadores:**

| Tipo de Operador | Ejemplo | Descripción |
|-----------------|---------|-------------|
| **Aritméticos** | `+`, `-`, `*`, `/`, `%` | Operaciones matemáticas |
| **Relacionales** | `==`, `!=`, `>`, `<`, `>=`, `<=` | Comparaciones entre valores |
| **Lógicos** | `&&`, `||`, `!` | Evaluación de condiciones |
| **Asignación** | `=`, `+=`, `-=`, `*=`, `/=`, `%=` | Modificación de variables |
| **Incremento/Decremento** | `++`, `--` | Sumar o restar 1 a una variable |

---

## **2️⃣ Operadores Aritméticos**
📌 **Se utilizan para realizar operaciones matemáticas básicas.**

| Operador | Descripción | Ejemplo (`a = 10, b = 3`) | Resultado |
|----------|------------|-------------------|-----------|
| `+` | Suma | `a + b` | `13` |
| `-` | Resta | `a - b` | `7` |
| `*` | Multiplicación | `a * b` | `30` |
| `/` | División | `a / b` | `3` (si `a` y `b` son enteros) |
| `%` | Módulo (resto) | `a % b` | `1` |

📌 **Ejemplo en Java:**
```java
public class OperadoresAritmeticos {
    public static void main(String[] args) {
        int a = 10, b = 3;
        System.out.println("Suma: " + (a + b));
        System.out.println("Resta: " + (a - b));
        System.out.println("Multiplicación: " + (a * b));
        System.out.println("División: " + (a / b));
        System.out.println("Módulo: " + (a % b));
    }
}
```
✅ **Salida esperada:**
```
Suma: 13  
Resta: 7  
Multiplicación: 30  
División: 3  
Módulo: 1  
```

⚠ **Nota:** Si queremos una división decimal, al menos un número debe ser `double`:
```java
double resultado = 10.0 / 3;
System.out.println(resultado); // 3.3333
```

---

## **3️⃣ Operadores Relacionales**
📌 **Comparan valores y devuelven `true` o `false`.**

| Operador | Descripción | Ejemplo (`a = 10, b = 3`) | Resultado |
|----------|------------|-------------------|-----------|
| `==` | Igual a | `a == b` | `false` |
| `!=` | Diferente de | `a != b` | `true` |
| `>` | Mayor que | `a > b` | `true` |
| `<` | Menor que | `a < b` | `false` |
| `>=` | Mayor o igual | `a >= b` | `true` |
| `<=` | Menor o igual | `a <= b` | `false` |

📌 **Ejemplo en Java:**
```java
public class OperadoresRelacionales {
    public static void main(String[] args) {
        int a = 10, b = 3;
        System.out.println("¿a es igual a b? " + (a == b));
        System.out.println("¿a es mayor que b? " + (a > b));
        System.out.println("¿a es menor o igual a b? " + (a <= b));
    }
}
```
✅ **Salida esperada:**
```
¿a es igual a b? false  
¿a es mayor que b? true  
¿a es menor o igual a b? false  
```

---

## **4️⃣ Operadores Lógicos**
📌 **Se usan para combinar condiciones (`boolean`).**

| Operador | Descripción | Ejemplo (`a = 10, b = 3`) | Resultado |
|----------|------------|-------------------|-----------|
| `&&` | AND (y) | `(a > 5 && b < 5)` | `true` |
| `||` | OR (o) | `(a > 5 || b > 5)` | `true` |
| `!` | NOT (negación) | `!(a > 5)` | `false` |

📌 **Ejemplo en Java:**
```java
public class OperadoresLogicos {
    public static void main(String[] args) {
        boolean condicion1 = (10 > 5);  // true
        boolean condicion2 = (3 < 2);   // false
        
        System.out.println("AND: " + (condicion1 && condicion2));
        System.out.println("OR: " + (condicion1 || condicion2));
        System.out.println("NOT: " + (!condicion1));
    }
}
```
✅ **Salida esperada:**
```
AND: false  
OR: true  
NOT: false  
```

---

## **5️⃣ Precedencia de Operadores en Java**
📌 **Al igual que en matemáticas, algunos operadores tienen mayor prioridad que otros.**

Orden de ejecución de operadores (de mayor a menor prioridad):  
1️⃣ **Paréntesis** → `( )`  
2️⃣ **Incremento y Decremento** → `++`, `--`  
3️⃣ **Multiplicación, División, Módulo** → `*`, `/`, `%`  
4️⃣ **Suma y Resta** → `+`, `-`  
5️⃣ **Relacionales** → `<`, `>`, `<=`, `>=`  
6️⃣ **Igualdad** → `==`, `!=`  
7️⃣ **Lógicos** → `&&`, `||`

📌 **Ejemplo de Precedencia:**
```java
public class PrecedenciaOperadores {
    public static void main(String[] args) {
        int resultado = 10 + 2 * 5; // Multiplicación se ejecuta primero
        System.out.println("Sin paréntesis: " + resultado); // 20

        int resultado2 = (10 + 2) * 5; // Paréntesis se ejecuta primero
        System.out.println("Con paréntesis: " + resultado2); // 60
    }
}
```
✅ **Salida esperada:**
```
Sin paréntesis: 20  
Con paréntesis: 60  
```

---

# **📌 Ejercicio del Día 3** ✍️

📌 **Ejercicio:** Crear una calculadora simple que realice operaciones básicas (`+`, `-`, `*`, `/`).

✔ **Entrada:**
1. Solicitar al usuario **dos números**.
2. Solicitar la **operación (+, -, *, /)**.

✔ **Salida esperada:**
```
Ingrese el primer número: 10  
Ingrese el segundo número: 5  
Seleccione la operación (+, -, *, /): *  
Resultado: 50  
```
<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
```java
import java.util.Scanner;

public class Calculadora {
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

        // Realizar la operación
        switch (operacion) {
            case '+': resultado = num1 + num2; break;
            case '-': resultado = num1 - num2; break;
            case '*': resultado = num1 * num2; break;
            case '/': resultado = num2 != 0 ? num1 / num2 : 0; break;
            default:
                System.out.println("Operación inválida.");
                operacionValida = false;
        }

        if (operacionValida) {
            System.out.println("Resultado: " + resultado);
        }

        scanner.close();
    }
}
```
</details>

---