# **📌 WebAssembly + Java** 🚀🖥️

Hasta hace poco, ejecutar Java en el navegador **sin usar un applet o JavaScript** parecía imposible. Sin embargo, **WebAssembly (Wasm)** ha cambiado esto, permitiendo que **Java se ejecute directamente en el navegador** con un rendimiento cercano al código nativo.

---

# **🔹 ¿Qué es WebAssembly (Wasm)?**
**WebAssembly (Wasm)** es un **formato de código binario** que se ejecuta en el navegador con un rendimiento cercano al nativo. Permite ejecutar lenguajes como **C, Rust, Go, y ahora también Java** en el navegador.

📌 **Ventajas de WebAssembly:**  
✔ **Rendimiento superior** → Mucho más rápido que JavaScript.  
✔ **Ejecuta Java en el navegador** sin necesidad de una JVM.  
✔ **Multiplataforma** → Funciona en **Chrome, Firefox, Safari y Edge**.  
✔ **Más seguro** → Se ejecuta en un sandbox dentro del navegador.

---

# **🔹 ¿Cómo ejecutar Java en WebAssembly?**
Para compilar **Java a WebAssembly**, existen herramientas como:

1️⃣ **JWebAssembly** → Convierte Java directamente a WebAssembly sin JVM.  
2️⃣ **TeaVM** → Convierte Java a JavaScript o WebAssembly, optimizando el código.  
3️⃣ **GraalVM Native Image** → Permite ejecutar Java en Wasm con rendimiento nativo.

---  

# **📌 1️⃣ Usando JWebAssembly – Ejecutar Java en el Navegador**
📌 **Ejemplo: Código Java compilado a WebAssembly**
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hola desde WebAssembly!");
    }
}
```
✅ **Este código se compilará a WebAssembly y se ejecutará en el navegador.**

📌 **Pasos para compilar y ejecutar con JWebAssembly:**

### **1. Agregar la dependencia de JWebAssembly**
```xml
<dependency>
    <groupId>de.inetsoftware</groupId>
    <artifactId>jwebassembly</artifactId>
    <version>0.5.0</version>
</dependency>
```
### **2. Compilar el código Java a WebAssembly**
```bash
java -jar jwebassembly.jar --compile Main.java -o Main.wasm
```
✅ **Esto generará un archivo `Main.wasm` que podemos ejecutar en un navegador.**

---

# **📌 2️⃣ Usando TeaVM – Compilar Java a WebAssembly o JavaScript**
📌 **Ejemplo: Convertir Java a WebAssembly con TeaVM**
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hola desde TeaVM en WebAssembly!");
    }
}
```
✅ **Esto generará código WebAssembly o JavaScript optimizado.**

📌 **Pasos para compilar con TeaVM:**

### **1. Agregar la dependencia de TeaVM**
```xml
<dependency>
    <groupId>org.teavm</groupId>
    <artifactId>teavm-wasm</artifactId>
    <version>0.7.0</version>
</dependency>
```
### **2. Compilar el código Java a WebAssembly**
```bash
java -jar teavm-wasm.jar --main-class=Main -o Main.wasm
```
✅ **Ahora `Main.wasm` se puede cargar en un navegador.**

---

# **📌 3️⃣ Usando GraalVM para compilar Java a WebAssembly**
📌 **Ejemplo: Compilar un servicio Java a WebAssembly con GraalVM**
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("¡Java en WebAssembly con GraalVM!");
    }
}
```
📌 **Pasos para compilar con GraalVM:**

### **1. Instalar GraalVM y Wasm Backend**
```bash
gu install native-image
gu install wasm
```
### **2. Compilar el código a WebAssembly**
```bash
native-image --target=wasm Main.java -o Main.wasm
```
✅ **Esto generará un archivo WebAssembly (`Main.wasm`) listo para el navegador.**

---

# **📌 Ejecutando WebAssembly en el Navegador**
📌 **Ejemplo de cómo cargar el código WebAssembly en HTML + JavaScript**
```html
<!DOCTYPE html>
<html>
<head>
    <title>Ejecutando Java en WebAssembly</title>
</head>
<body>
    <script>
        WebAssembly.instantiateStreaming(fetch('Main.wasm')).then(obj => {
            console.log(obj.instance.exports.main());
        });
    </script>
</body>
</html>
```
✅ **Ahora podemos ejecutar Java directamente en el navegador sin una JVM.**

---

# **📌 Comparación de Herramientas para Java + WebAssembly**
| **Herramienta** | **Convierte Java a...** | **Ventajas** | **Desventajas** |
|--------------|------------------|------------|--------------|
| **JWebAssembly** | WebAssembly puro | No necesita JVM, fácil de usar | Aún en desarrollo, menos optimización |
| **TeaVM** | WebAssembly o JavaScript | Código optimizado, soporte para más APIs | Puede requerir ajustes en el código Java |
| **GraalVM** | WebAssembly nativo | Máximo rendimiento | Configuración más compleja |

---

# **📌 ¿Cuándo usar WebAssembly con Java?**
✅ **Ejecutar Java en el navegador** sin necesidad de JavaScript.  
✅ **Mejorar el rendimiento de aplicaciones web** sin depender de la JVM.  
✅ **Hacer aplicaciones Java Serverless** que corren en Wasm en la nube.  
✅ **Migrar aplicaciones Java a Web sin cambiar el código.**

---

# **📌 Conclusión**
🚀 **Java en WebAssembly es el futuro del desarrollo web**, permitiendo ejecutar código Java en el navegador con rendimiento nativo.  
🔥 Herramientas como **JWebAssembly, TeaVM y GraalVM** hacen posible esta integración.  
🌍 **Ideal para aplicaciones web, serverless y microservicios en la nube.**
