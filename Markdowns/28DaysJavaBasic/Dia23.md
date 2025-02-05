# **📌 Día 23: JavaFX y Creación de Interfaces Gráficas (GUI) en Java** 🎨🖥️

📌 **Objetivo del día:**  
✅ Aprender a construir **interfaces gráficas con JavaFX**.  
✅ Conocer los **componentes básicos** como botones, etiquetas y campos de texto.  
✅ Manejar **eventos y listeners** en JavaFX.  
✅ **Ejercicio:** Implementar una **calculadora básica con JavaFX**.

---

## **1️⃣ ¿Qué es JavaFX?**

📌 **JavaFX** es el framework moderno de Java para crear **interfaces gráficas (GUI)**.  
✔ Soporta **botones, formularios, tablas, imágenes y más**.  
✔ **Es independiente de Swing** y más flexible.  
✔ Permite **animaciones, gráficos y efectos visuales avanzados**.

📌 **¿Cómo instalar JavaFX?**  
✔ **Si usas Maven**, agrega en `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>21</version>
    </dependency>
</dependencies>
```
✔ **Si usas JDK 11+**, debes descargar **JavaFX SDK** de:  
👉 [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)  
✔ **Si usas IntelliJ o Eclipse**, activa JavaFX en las configuraciones del proyecto.

---

## **2️⃣ Crear una Ventana con JavaFX**

📌 **Ejemplo básico de una ventana en JavaFX:**
```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HolaJavaFX extends Application {
    @Override
    public void start(Stage escenario) {
        Label etiqueta = new Label("¡Hola, JavaFX!");
        StackPane raiz = new StackPane(etiqueta);
        Scene escena = new Scene(raiz, 300, 200);
        
        escenario.setTitle("Mi Primera Ventana");
        escenario.setScene(escena);
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```
✅ **Salida esperada:** Una ventana con el texto "¡Hola, JavaFX!".

✔ **Explicación:**  
1️⃣ `Stage` → Representa la ventana principal.  
2️⃣ `Scene` → Contiene todos los elementos gráficos.  
3️⃣ `Label` → Etiqueta de texto.  
4️⃣ `StackPane` → Organiza los elementos en la ventana.

---

## **3️⃣ Agregar Botones y Campos de Texto**

📌 **Ejemplo con `Button` y `TextField` para ingresar texto:**
```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EntradaTexto extends Application {
    @Override
    public void start(Stage escenario) {
        Label etiqueta = new Label("Ingresa tu nombre:");
        TextField campoTexto = new TextField();
        Button boton = new Button("Saludar");
        Label resultado = new Label();

        boton.setOnAction(e -> resultado.setText("¡Hola, " + campoTexto.getText() + "!"));

        VBox layout = new VBox(10, etiqueta, campoTexto, boton, resultado);
        Scene escena = new Scene(layout, 300, 200);

        escenario.setTitle("Saludo en JavaFX");
        escenario.setScene(escena);
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```
✅ **Salida esperada:**  
✔ Una ventana con un campo de texto y un botón.  
✔ Al escribir un nombre y hacer clic en el botón, aparece **"¡Hola, [nombre]!"**.

---

## **4️⃣ Manejo de Eventos y Listeners**

📌 **JavaFX usa "listeners" para manejar eventos de botones y otros componentes.**  
✔ **Ejemplo de un botón con eventos:**
```java
boton.setOnAction(e -> System.out.println("Botón presionado"));
```
✔ **Ejemplo con más lógica:**
```java
boton.setOnAction(e -> {
    String texto = campoTexto.getText();
    resultado.setText("Bienvenido, " + texto + "!");
});
```

---

# **📌 Ejercicio del Día 23: Calculadora con JavaFX** 🎯

📌 **Objetivo:**  
✔ Crear una **calculadora con JavaFX**.  
✔ Botones para **sumar, restar, multiplicar y dividir**.  
✔ Usar **campos de texto** para ingresar números.

📌 **Ejemplo de interfaz esperada:**
```
Número 1: [____]
Número 2: [____]
[Sumar] [Restar] [Multiplicar] [Dividir]
Resultado: [_____]
```

---
<details>
    <summary>Solución</summary>

### **✅ Solución en JavaFX**
📌 **Clase `CalculadoraJavaFX`:**
```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculadoraJavaFX extends Application {
    @Override
    public void start(Stage escenario) {
        Label etiqueta1 = new Label("Número 1:");
        TextField campo1 = new TextField();
        Label etiqueta2 = new Label("Número 2:");
        TextField campo2 = new TextField();
        Label resultado = new Label("Resultado: ");
        
        Button btnSumar = new Button("Sumar");
        Button btnRestar = new Button("Restar");
        Button btnMultiplicar = new Button("Multiplicar");
        Button btnDividir = new Button("Dividir");

        // Eventos para los botones
        btnSumar.setOnAction(e -> {
            double num1 = Double.parseDouble(campo1.getText());
            double num2 = Double.parseDouble(campo2.getText());
            resultado.setText("Resultado: " + (num1 + num2));
        });

        btnRestar.setOnAction(e -> {
            double num1 = Double.parseDouble(campo1.getText());
            double num2 = Double.parseDouble(campo2.getText());
            resultado.setText("Resultado: " + (num1 - num2));
        });

        btnMultiplicar.setOnAction(e -> {
            double num1 = Double.parseDouble(campo1.getText());
            double num2 = Double.parseDouble(campo2.getText());
            resultado.setText("Resultado: " + (num1 * num2));
        });

        btnDividir.setOnAction(e -> {
            double num1 = Double.parseDouble(campo1.getText());
            double num2 = Double.parseDouble(campo2.getText());
            if (num2 != 0) {
                resultado.setText("Resultado: " + (num1 / num2));
            } else {
                resultado.setText("❌ Error: División por cero");
            }
        });

        VBox layout = new VBox(10, etiqueta1, campo1, etiqueta2, campo2, 
                btnSumar, btnRestar, btnMultiplicar, btnDividir, resultado);
        Scene escena = new Scene(layout, 300, 300);

        escenario.setTitle("Calculadora JavaFX");
        escenario.setScene(escena);
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

✅ **Salida esperada:**  
✔ Una **calculadora funcional** con botones para operar.  
✔ Muestra el **resultado** en pantalla.

📌 **Explicación:**  
✔ **`setOnAction()`** define eventos para los botones.  
✔ **`VBox`** organiza los elementos en una columna vertical.  
✔ **`Double.parseDouble()`** convierte texto en número.

---

</details>