# **📌 Día 1: Introducción a Java y Configuración del Entorno** 🚀

📌 **Objetivo del día:**  
✅ Instalar **JDK** y configurar un entorno de desarrollo en **IntelliJ IDEA o Eclipse**  
✅ Entender la **estructura de un programa en Java**  
✅ Escribir y ejecutar el primer **"Hola Mundo"** en Java

---

## **1️⃣ Instalación del Entorno de Desarrollo**

### **1.1 Descargar e Instalar el JDK (Java Development Kit)**
Java necesita el **JDK (Java Development Kit)** para compilar y ejecutar programas.

🔹 **Descargar el JDK:**  
📍 [https://jdk.java.net/](https://jdk.java.net/) (versión más reciente)  
📍 [https://www.oracle.com/java/technologies/javase-downloads.html](https://www.oracle.com/java/technologies/javase-downloads.html) (versión estable de Oracle)

✔ Instala la versión **JDK 17 o superior** (Java 21 es la más reciente en 2024).  
✔ **Verifica la instalación** ejecutando en terminal o cmd:
```sh
java -version
```
✔ Si ves algo como esto, ¡todo está bien!
```
java version "17.0.1" 2024-01-20 LTS
```

---

### **1.2 Instalar un IDE (Entorno de Desarrollo Integrado)**
Recomiendo usar uno de estos dos:

✅ **IntelliJ IDEA Community Edition** (recomendada)  
📍 [https://www.jetbrains.com/idea/download](https://www.jetbrains.com/idea/download)

✅ **Eclipse IDE**  
📍 [https://www.eclipse.org/downloads/](https://www.eclipse.org/downloads/)

💡 **Si prefieres línea de comandos**, puedes usar un editor como **VS Code** o **Notepad++** con el terminal.

---

## **2️⃣ Primer Programa en Java: "Hola Mundo"**

### **2.1 Estructura Básica de un Programa en Java**
📌 En Java, todo código debe estar dentro de una **clase** y tener un **método `main`**, que es el punto de entrada.

📌 **Ejemplo básico de "Hola Mundo" en Java:**
```java
public class HolaMundo {
    public static void main(String[] args) {
        System.out.println("¡Hola, mundo! Bienvenido a Java 🚀");
    }
}
```

📌 **Explicación:**  
✔ `public class HolaMundo {}` → Define una **clase** llamada `HolaMundo`.  
✔ `public static void main(String[] args) {}` → Método principal donde comienza la ejecución.  
✔ `System.out.println("¡Hola, mundo!");` → Muestra un mensaje en la consola.

### **2.2 Ejecutar el Programa**
🔹 **En IntelliJ IDEA:**  
1️⃣ Crea un nuevo proyecto **"Java Application"**  
2️⃣ Crea un archivo `HolaMundo.java`  
3️⃣ Escribe el código y presiona ▶️ (Run)

🔹 **Desde la terminal:**  
1️⃣ Guarda el archivo como `HolaMundo.java`  
2️⃣ Compila el programa con:
```sh
javac HolaMundo.java
```
3️⃣ Ejecuta el programa con:
```sh
java HolaMundo
```

✅ **Salida esperada en consola:**
```
¡Hola, mundo! Bienvenido a Java 🚀
```

---

## **3️⃣ Explicación Detallada del Código**
### **📌 Conceptos clave del Día 1**

| Concepto | Explicación |
|----------|------------|
| **Clase** | Es la estructura base en Java, donde definimos objetos y lógica. |
| **Método `main`** | Es el punto de inicio de ejecución de cualquier programa Java. |
| **`System.out.println()`** | Imprime un mensaje en la consola. |
| **Compilación (`javac`)** | Convierte código Java en bytecode para la JVM. |
| **Ejecución (`java`)** | Ejecuta el bytecode en la JVM. |

💡 **Curiosidad:**  
Java es un lenguaje **compilado e interpretado**, lo que significa que se traduce primero a **bytecode** (`.class`), que luego es ejecutado por la **Java Virtual Machine (JVM)** en cualquier sistema operativo.

---

## **4️⃣ Ejercicio del Día 1** ✍️
📌 **Ejercicio 1:** Crea un programa que imprima tu nombre, edad y ciudad.

✔ **Ejemplo de salida esperada:**
```
¡Hola! Mi nombre es Juan Pérez.
Tengo 25 años y vivo en Madrid, España.
```

<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
```java
public class InformacionPersonal {
    public static void main(String[] args) {
        System.out.println("¡Hola! Mi nombre es Juan Pérez.");
        System.out.println("Tengo 25 años y vivo en Madrid, España.");
    }
}
```

</details>

📌 **Ejercicio 2:** Crea un programa que muestre la suma de dos números enteros.

✔ **Ejemplo de salida esperada:**
```
La suma de 10 + 5 es: 15
```

<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
```java
public class SumaNumeros {
    public static void main(String[] args) {
        int numero1 = 10;
        int numero2 = 5;
        int suma = numero1 + numero2;
        
        System.out.println("La suma de " + numero1 + " + " + numero2 + " es: " + suma);
    }
}
```

</details>

📌 **Ejercicio 3 (Opcional):** Crea un programa que muestre un **ASCII Art** en la consola.

✔ **Ejemplo:**
```
  /\\_/\\  
 ( o.o )  < Soy un gato programador 😺  
  > ^ <  
```

<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
```java
public class AsciiArt {
    public static void main(String[] args) {
        System.out.println("  /\\_/\\  ");
        System.out.println(" ( o.o )  < Soy un gato programador 😺 ");
        System.out.println("  > ^ <  ");
    }
}
```

</details>

