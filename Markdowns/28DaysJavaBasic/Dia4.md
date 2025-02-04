# **📌 Día 4: Estructuras de Control (if, switch, while, for)** 🚀

📌 **Objetivo del día:**  
✅ Aprender a usar **condicionales (`if`, `else`, `switch`)** para tomar decisiones.  
✅ Comprender los **bucles (`for`, `while`, `do-while`)** para repetir instrucciones.  
✅ Explorar el uso de **`break` y `continue`** en los bucles.  
✅ **Ejercicio:** Crear un **juego de adivinanza** con `while`.

---

## **1️⃣ Condicionales en Java (`if`, `else`, `switch`)**

Las **estructuras condicionales** permiten ejecutar diferentes bloques de código según una condición.

### **1.1 Uso de `if` y `else`**
📌 **Ejemplo en Java:**
```java
public class Condicionales {
    public static void main(String[] args) {
        int edad = 18;

        if (edad >= 18) {
            System.out.println("Eres mayor de edad.");
        } else {
            System.out.println("Eres menor de edad.");
        }
    }
}
```
✅ **Salida esperada:**
```
Eres mayor de edad.
```

---

### **1.2 Uso de `if-else if-else`**
📌 **Ejemplo en Java:**
```java
public class Notas {
    public static void main(String[] args) {
        int nota = 85;

        if (nota >= 90) {
            System.out.println("Excelente");
        } else if (nota >= 75) {
            System.out.println("Aprobado");
        } else {
            System.out.println("Reprobado");
        }
    }
}
```
✅ **Salida esperada:**
```
Aprobado
```

---

### **1.3 Uso de `switch` (Para Múltiples Opciones)**
📌 **Ejemplo en Java:**
```java
public class DiasSemana {
    public static void main(String[] args) {
        int dia = 3;

        switch (dia) {
            case 1:
                System.out.println("Lunes");
                break;
            case 2:
                System.out.println("Martes");
                break;
            case 3:
                System.out.println("Miércoles");
                break;
            default:
                System.out.println("Día no válido");
        }
    }
}
```
✅ **Salida esperada:**
```
Miércoles
```

⚠ **Nota:** Sin `break`, las siguientes instrucciones se ejecutarán en cascada.

---

## **2️⃣ Bucles (`for`, `while`, `do-while`)**

Los **bucles** permiten repetir instrucciones mientras se cumpla una condición.

---

### **2.1 Bucle `for` (Para Iteraciones Definidas)**
📌 **Ejemplo en Java:**
```java
public class Contador {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Número: " + i);
        }
    }
}
```
✅ **Salida esperada:**
```
Número: 1  
Número: 2  
Número: 3  
Número: 4  
Número: 5  
```

---

### **2.2 Bucle `while` (Para Iteraciones Indefinidas)**
📌 **Ejemplo en Java:**
```java
public class ContadorWhile {
    public static void main(String[] args) {
        int i = 1;
        while (i <= 5) {
            System.out.println("Número: " + i);
            i++;
        }
    }
}
```
✅ **Salida esperada:**
```
Número: 1  
Número: 2  
Número: 3  
Número: 4  
Número: 5  
```

---

### **2.3 Bucle `do-while` (Ejecuta al Menos Una Vez)**
📌 **Ejemplo en Java:**
```java
import java.util.Scanner;

public class EjemploDoWhile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú:");
            System.out.println("1. Opción 1");
            System.out.println("2. Opción 2");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
        } while (opcion != 3);

        System.out.println("Programa finalizado.");
        scanner.close();
    }
}
```
✅ **Salida esperada:**
```
Menú:
1. Opción 1
2. Opción 2
3. Salir
Seleccione una opción: 2
Menú:
1. Opción 1
2. Opción 2
3. Salir
Seleccione una opción: 3
Programa finalizado.
```

⚠ **Nota:** `do-while` ejecuta el código al menos una vez antes de verificar la condición.

---

## **3️⃣ Uso de `break` y `continue` en Bucles**

📌 **`break`**: Sale completamente del bucle.  
📌 **`continue`**: Salta la iteración actual y sigue con la siguiente.

📌 **Ejemplo de `break` (detener el bucle si se cumple una condición):**
```java
public class BreakEjemplo {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            if (i == 5) {
                System.out.println("Se detiene en: " + i);
                break;
            }
            System.out.println(i);
        }
    }
}
```
✅ **Salida esperada:**
```
1  
2  
3  
4  
Se detiene en: 5  
```

📌 **Ejemplo de `continue` (omitir una iteración específica):**
```java
public class ContinueEjemplo {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            if (i == 3) {
                System.out.println("Saltando número " + i);
                continue;
            }
            System.out.println(i);
        }
    }
}
```
✅ **Salida esperada:**
```
1  
2  
Saltando número 3  
4  
5  
```

---

# **📌 Ejercicio del Día 4: Juego de Adivinanza con `while`** 🎮

📌 **Objetivo:** Crear un juego donde el usuario debe adivinar un número aleatorio entre 1 y 100.  
✔ **El programa debe dar pistas:** "Más alto" o "Más bajo".  
✔ **Debe continuar hasta que el usuario acierte.**

✔ **Ejemplo de salida esperada:**
```
Adivina el número (entre 1 y 100): 50  
Más alto  
Adivina el número (entre 1 y 100): 75  
Más bajo  
Adivina el número (entre 1 y 100): 63  
¡Felicidades! Has adivinado el número 63.
```

---

<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
```java
import java.util.Scanner;
import java.util.Random;

public class JuegoAdivinanza {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int numeroSecreto = random.nextInt(100) + 1; // Número aleatorio entre 1 y 100
        int intento = 0;
        
        System.out.println("🎮 Bienvenido al Juego de Adivinanza!");
        
        while (intento != numeroSecreto) {
            System.out.print("Adivina el número (entre 1 y 100): ");
            intento = scanner.nextInt();
            
            if (intento < numeroSecreto) {
                System.out.println("Más alto ⬆️");
            } else if (intento > numeroSecreto) {
                System.out.println("Más bajo ⬇️");
            } else {
                System.out.println("🎉 ¡Felicidades! Has adivinado el número " + numeroSecreto + ".");
            }
        }
        
        scanner.close();
    }
}
```

</details>