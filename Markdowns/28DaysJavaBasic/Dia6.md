# **📌 Día 6: Arrays y Manipulación de Datos en Java** 🚀

📌 **Objetivo del día:**  
✅ Aprender a **declarar, inicializar y recorrer arrays** en Java.  
✅ Manipular datos con **métodos útiles (`Arrays.sort`, `Arrays.fill`, etc.)**.  
✅ Comprender las diferencias entre **arrays estáticos y dinámicos**.  
✅ **Ejercicio:** Crear un programa que ordene y busque elementos en un array.

---

## **1️⃣ ¿Qué es un Array en Java?**
📌 **Un array es una estructura de datos que almacena múltiples valores del mismo tipo en una sola variable.**

✔ Los elementos de un array están **indexados**, comenzando desde `0`.  
✔ Un array tiene un **tamaño fijo** y no puede cambiar después de su creación.

📌 **Ejemplo de array en Java:**
```java
int[] numeros = {10, 20, 30, 40, 50};  
System.out.println(numeros[0]); // Accede al primer elemento (10)
```
✅ **Salida esperada:**
```
10
```

---

## **2️⃣ Declaración e Inicialización de Arrays**

### **2.1 Declaración de un Array**
📌 **Forma 1: Declaración y Asignación Directa**
```java
int[] numeros = {10, 20, 30, 40, 50};
```
📌 **Forma 2: Declaración con `new` y Tamaño Fijo**
```java
int[] edades = new int[3]; // Array con 3 elementos (inicializados en 0)
```
📌 **Forma 3: Inicialización Manual**
```java
int[] temperaturas = new int[4];
temperaturas[0] = 25;
temperaturas[1] = 30;
temperaturas[2] = 18;
temperaturas[3] = 21;
```

---

## **3️⃣ Recorrer un Array con `for` y `for-each`**

📌 **Uso de `for` para recorrer un array:**
```java
public class RecorrerArray {
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5};

        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Elemento en índice " + i + ": " + numeros[i]);
        }
    }
}
```
✅ **Salida esperada:**
```
Elemento en índice 0: 1  
Elemento en índice 1: 2  
Elemento en índice 2: 3  
Elemento en índice 3: 4  
Elemento en índice 4: 5  
```

📌 **Uso de `for-each` para recorrer un array (forma simplificada):**
```java
public class RecorrerArrayForEach {
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5};

        for (int num : numeros) {
            System.out.println("Número: " + num);
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
💡 **Nota:** `for-each` no permite acceder al índice de los elementos.

---

## **4️⃣ Métodos Útiles para Manipulación de Arrays**

📌 **Ordenar un array con `Arrays.sort()`**
```java
import java.util.Arrays;

public class OrdenarArray {
    public static void main(String[] args) {
        int[] numeros = {5, 2, 8, 1, 3};
        Arrays.sort(numeros);

        System.out.println("Array ordenado: " + Arrays.toString(numeros));
    }
}
```
✅ **Salida esperada:**
```
Array ordenado: [1, 2, 3, 5, 8]
```

📌 **Rellenar un array con `Arrays.fill()`**
```java
import java.util.Arrays;

public class RellenarArray {
    public static void main(String[] args) {
        int[] valores = new int[5];
        Arrays.fill(valores, 7);

        System.out.println("Array rellenado: " + Arrays.toString(valores));
    }
}
```
✅ **Salida esperada:**
```
Array rellenado: [7, 7, 7, 7, 7]
```

📌 **Buscar un elemento con `Arrays.binarySearch()`**  
💡 **Nota:** El array **debe estar ordenado previamente**.
```java
import java.util.Arrays;

public class BuscarElemento {
    public static void main(String[] args) {
        int[] numeros = {1, 3, 5, 7, 9};
        int posicion = Arrays.binarySearch(numeros, 5);

        System.out.println("El número 5 está en la posición: " + posicion);
    }
}
```
✅ **Salida esperada:**
```
El número 5 está en la posición: 2
```

📌 **Copiar un array con `Arrays.copyOf()`**
```java
import java.util.Arrays;

public class CopiarArray {
    public static void main(String[] args) {
        int[] original = {10, 20, 30};
        int[] copia = Arrays.copyOf(original, original.length);

        System.out.println("Copia del array: " + Arrays.toString(copia));
    }
}
```
✅ **Salida esperada:**
```
Copia del array: [10, 20, 30]
```

---

## **5️⃣ Arrays Multidimensionales (Matrices)**

📌 **Declaración de un array bidimensional (matriz):**
```java
int[][] matriz = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

📌 **Acceder a elementos de una matriz:**
```java
System.out.println(matriz[1][2]); // Imprime el elemento en la fila 1, columna 2 (6)
```

📌 **Recorrer una matriz con `for` anidados:**
```java
public class MatrizEjemplo {
    public static void main(String[] args) {
        int[][] matriz = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
```
✅ **Salida esperada:**
```
1 2 3  
4 5 6  
7 8 9  
```

---

# **📌 Ejercicio del Día 6: Ordenar y Buscar un Elemento en un Array** 🎯

📌 **Objetivo:**  
✔ Crear un programa que **ordene un array** y permita **buscar un número ingresado por el usuario**.

✔ **Ejemplo de salida esperada:**
```
Ingrese 5 números:  
10 5 8 3 7  
Array ordenado: [3, 5, 7, 8, 10]  
Ingrese un número a buscar: 7  
El número 7 se encuentra en la posición 2.
```

---
<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
```java
import java.util.Arrays;
import java.util.Scanner;

public class OrdenarYBuscar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numeros = new int[5];

        // Leer 5 números del usuario
        System.out.println("Ingrese 5 números:");
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = scanner.nextInt();
        }

        // Ordenar el array
        Arrays.sort(numeros);
        System.out.println("Array ordenado: " + Arrays.toString(numeros));

        // Buscar un número en el array
        System.out.print("Ingrese un número a buscar: ");
        int buscado = scanner.nextInt();
        int posicion = Arrays.binarySearch(numeros, buscado);

        if (posicion >= 0) {
            System.out.println("El número " + buscado + " se encuentra en la posición " + posicion + ".");
        } else {
            System.out.println("El número no está en el array.");
        }

        scanner.close();
    }
}
```

</details>