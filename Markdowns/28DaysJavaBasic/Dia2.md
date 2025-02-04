# **📌 Día 2: Tipos de Datos y Variables en Java** 🚀

📌 **Objetivo del día:**  
✅ Entender los **tipos de datos primitivos y de referencia** en Java.  
✅ Aprender a declarar, inicializar y usar **variables**.  
✅ Realizar **conversiones y casting** entre diferentes tipos de datos.  
✅ **Ejercicio:** Crear un programa para conversión de unidades.

---

## **1️⃣ Tipos de Datos en Java**

📌 **Java tiene dos tipos de datos principales:**  
1️⃣ **Primitivos**: Almacenan valores simples como números y caracteres.  
2️⃣ **De Referencia**: Apuntan a objetos almacenados en memoria (como `String`, `List`).

---

### **1.1 Tipos de Datos Primitivos en Java**
Los **tipos primitivos** son los más básicos y no requieren instanciación con `new`.

| Tipo | Tamaño | Rango | Ejemplo |
|------|--------|--------|---------|
| `byte` | 8 bits | -128 a 127 | `byte edad = 25;` |
| `short` | 16 bits | -32,768 a 32,767 | `short salario = 15000;` |
| `int` | 32 bits | -2,147,483,648 a 2,147,483,647 | `int numero = 1000;` |
| `long` | 64 bits | -9 quintillones a +9 quintillones | `long estrellas = 9000000000L;` |
| `float` | 32 bits | Decimales (precisión simple) | `float altura = 1.75f;` |
| `double` | 64 bits | Decimales (precisión doble) | `double pi = 3.1416;` |
| `char` | 16 bits | Un solo carácter Unicode | `char letra = 'A';` |
| `boolean` | 1 bit | `true` o `false` | `boolean esJavaGenial = true;` |

📌 **Ejemplo en Java:**
```java
public class TiposPrimitivos {
    public static void main(String[] args) {
        int edad = 30;
        double temperatura = 36.5;
        char inicial = 'J';
        boolean esJavaGenial = true;

        System.out.println("Edad: " + edad);
        System.out.println("Temperatura: " + temperatura);
        System.out.println("Inicial: " + inicial);
        System.out.println("¿Java es genial? " + esJavaGenial);
    }
}
```

✅ **Salida esperada:**
```
Edad: 30  
Temperatura: 36.5  
Inicial: J  
¿Java es genial? true  
```

---

### **1.2 Tipos de Datos de Referencia en Java**
Los **tipos de referencia** almacenan la dirección de un objeto en memoria.

📌 **Ejemplo de tipos de referencia:**  
✔ `String` → Almacena texto.  
✔ `Arrays` → Almacena múltiples valores del mismo tipo.  
✔ `List`, `Map`, `Set` → Estructuras de datos avanzadas.

📌 **Ejemplo de uso de `String`:**
```java
public class TiposReferencia {
    public static void main(String[] args) {
        String nombre = "Juan Pérez";
        String saludo = "Hola, " + nombre + "!";

        System.out.println(saludo);
    }
}
```
✅ **Salida esperada:**
```
Hola, Juan Pérez!
```

💡 **Diferencia clave:**
- **Los tipos primitivos almacenan valores directamente.**
- **Los tipos de referencia almacenan una dirección de memoria.**

---

## **2️⃣ Conversión de Datos y Casting en Java**

📌 **Existen dos tipos de conversiones en Java:**  
✔ **Conversión Implícita (Widening Casting)** → **Automática**, sin pérdida de datos.  
✔ **Conversión Explícita (Narrowing Casting)** → **Necesita conversión manual**, puede perder precisión.

---

### **2.1 Conversión Implícita (Widening Casting)**
Cuando pasamos de un tipo más pequeño a uno más grande, **Java lo hace automáticamente**.

📌 **Ejemplo:** Convertir `int` a `double`
```java
public class ConversionImplicita {
    public static void main(String[] args) {
        int numeroEntero = 100;
        double numeroDecimal = numeroEntero; // Conversión automática

        System.out.println("Entero: " + numeroEntero);
        System.out.println("Convertido a double: " + numeroDecimal);
    }
}
```
✅ **Salida esperada:**
```
Entero: 100  
Convertido a double: 100.0  
```

---

### **2.2 Conversión Explícita (Narrowing Casting)**
Cuando pasamos de un tipo más grande a uno más pequeño, **debemos hacer la conversión manualmente** usando `(tipo)`.

📌 **Ejemplo:** Convertir `double` a `int`
```java
public class ConversionExplicita {
    public static void main(String[] args) {
        double numeroDecimal = 9.7;
        int numeroEntero = (int) numeroDecimal; // Conversión manual (pierde decimales)

        System.out.println("Double: " + numeroDecimal);
        System.out.println("Convertido a int: " + numeroEntero);
    }
}
```
✅ **Salida esperada:**
```
Double: 9.7  
Convertido a int: 9  
```
⚠ **Nota:** Se pierden los decimales en la conversión de `double` a `int`.

---

## **3️⃣ Ejercicio del Día 2** ✍️

📌 **Ejercicio:** Crear un programa que convierta unidades de temperatura.

✔ **Fórmula de conversión:**
```
Fahrenheit = (Celsius × 9/5) + 32
```
✔ **Entrada:** Un número en **grados Celsius** ingresado por el usuario.  
✔ **Salida esperada:**
```
Ingrese la temperatura en Celsius: 25
La temperatura en Fahrenheit es: 77.0°F
```

<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
```java
import java.util.Scanner;

public class ConversorTemperatura {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario que ingrese la temperatura en Celsius
        System.out.print("Ingrese la temperatura en Celsius: ");
        double celsius = scanner.nextDouble();

        // Realizar la conversión
        double fahrenheit = (celsius * 9 / 5) + 32;

        // Mostrar el resultado
        System.out.println("La temperatura en Fahrenheit es: " + fahrenheit + "°F");

        scanner.close();
    }
}
```

✅ **Explicación del código:**  
✔ `Scanner scanner = new Scanner(System.in);` → Permite capturar la entrada del usuario.  
✔ `double celsius = scanner.nextDouble();` → Guarda el número ingresado como `double`.  
✔ `fahrenheit = (celsius * 9 / 5) + 32;` → Aplica la fórmula de conversión.  
✔ `System.out.println()` → Muestra la temperatura convertida en pantalla.

</details>