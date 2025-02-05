# **📌 Día 24: Eventos y Controladores en JavaFX (Arquitectura MVC)** 🎭🖥️

📌 **Objetivo del día:**  
✅ Aprender sobre el **patrón MVC (Modelo-Vista-Controlador)** en JavaFX.  
✅ Separar la **lógica de negocio** de la **interfaz gráfica**.  
✅ Manejar eventos con **controladores externos**.  
✅ **Ejercicio:** Implementar una **aplicación de tareas con MVC en JavaFX**.

---

## **1️⃣ ¿Qué es el Patrón MVC en JavaFX?** 🎭

📌 **MVC (Modelo-Vista-Controlador)** separa el código en **tres partes**:

| Componente | Función | Ejemplo |
|------------|---------|---------|
| **Modelo (Model)** | Lógica de negocio y datos | Clases que manejan datos (Ej. `Tarea.java`) |
| **Vista (View)** | Interfaz gráfica | Archivos `.fxml` con botones y etiquetas |
| **Controlador (Controller)** | Manejo de eventos | Clase que controla la lógica de UI |

✅ **Beneficios:**  
✔ Código **más limpio y escalable**.  
✔ Separación clara entre **diseño y lógica**.  
✔ **Fácil mantenimiento y pruebas.**

---

## **2️⃣ Creación de una Aplicación con MVC en JavaFX**

📌 **Ejemplo: Aplicación de Tareas (To-Do List)**

✔ **Carpeta del Proyecto:**
```
TareasApp/
 ├── src/
 │   ├── modelo/ (Model)
 │   │   ├── Tarea.java
 │   ├── vista/ (View)
 │   │   ├── tareas.fxml
 │   ├── controlador/ (Controller)
 │   │   ├── TareaController.java
 │   ├── Main.java
 ├── pom.xml
```

---

## **3️⃣ Paso 1: Crear el Modelo (`Tarea.java`)**

📌 **Clase `Tarea` (Lógica de Negocio):**
```java
package modelo;

public class Tarea {
    private String descripcion;
    private boolean completada;

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void completar() {
        this.completada = true;
    }

    @Override
    public String toString() {
        return (completada ? "✅ " : "🔲 ") + descripcion;
    }
}
```
✅ **Explicación:**  
✔ `Tarea` representa una tarea con **una descripción y un estado (`completada`)**.  
✔ Método `completar()` cambia su estado a **"completada"**.

---

## **4️⃣ Paso 2: Crear la Vista (`tareas.fxml`)**

📌 **Archivo `tareas.fxml` (Interfaz Gráfica)**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<VBox spacing="10" alignment="CENTER" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1"
      fx:controller="controlador.TareaController">
    <Label text="Lista de Tareas" style="-fx-font-size: 18px;"/>
    <TextField fx:id="campoTarea" promptText="Nueva tarea"/>
    <Button text="Agregar Tarea" onAction="#agregarTarea"/>
    <ListView fx:id="listaTareas"/>
</VBox>
```
✅ **Explicación:**  
✔ **`TextField`** → Campo para ingresar nuevas tareas.  
✔ **`Button`** → Botón para agregar tareas.  
✔ **`ListView`** → Lista donde se mostrarán las tareas.  
✔ **`fx:controller="controlador.TareaController"`** → Conecta con el **Controlador**.

---

## **5️⃣ Paso 3: Crear el Controlador (`TareaController.java`)**

📌 **Clase `TareaController` (Maneja los Eventos)**
```java
package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modelo.Tarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TareaController {
    @FXML private TextField campoTarea;
    @FXML private ListView<Tarea> listaTareas;

    private ObservableList<Tarea> tareas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listaTareas.setItems(tareas);
    }

    @FXML
    public void agregarTarea() {
        String descripcion = campoTarea.getText();
        if (!descripcion.isEmpty()) {
            tareas.add(new Tarea(descripcion));
            campoTarea.clear();
        }
    }
}
```
✅ **Explicación:**  
✔ `initialize()` carga la lista al iniciar la aplicación.  
✔ `agregarTarea()` crea una nueva tarea y la agrega a la lista.  
✔ `@FXML` indica que los elementos vienen del archivo **FXML**.

---

## **6️⃣ Paso 4: Ejecutar la Aplicación (`Main.java`)**

📌 **Clase `Main.java` (Punto de Entrada)**
```java
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage escenario) throws Exception {
        Parent raiz = FXMLLoader.load(getClass().getResource("/vista/tareas.fxml"));
        Scene escena = new Scene(raiz, 300, 400);
        
        escenario.setTitle("Gestor de Tareas");
        escenario.setScene(escena);
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```
✅ **Explicación:**  
✔ **`FXMLLoader.load()`** carga la vista desde el archivo `tareas.fxml`.  
✔ **Crea una ventana de 300x400 píxeles con el título "Gestor de Tareas"**.

---

# **📌 Ejercicio del Día 24: Agregar Función de Completar Tarea** 🎯

📌 **Objetivo:**  
✔ Agregar un botón en la vista para **marcar tareas como completadas**.  
✔ Al hacer doble clic en una tarea, se marcará como **completada**.

📌 **Modificar `tareas.fxml` para incluir doble clic en la lista:**
```xml
<ListView fx:id="listaTareas" onMouseClicked="#completarTarea"/>
```

📌 **Modificar `TareaController.java` para manejar el doble clic:**
```java
@FXML
public void completarTarea() {
    Tarea seleccionada = listaTareas.getSelectionModel().getSelectedItem();
    if (seleccionada != null) {
        seleccionada.completar();
        listaTareas.refresh(); // Refrescar la lista
    }
}
```

✅ **Salida esperada:**  
✔ **Al hacer doble clic en una tarea, se marca como completada con "✅"**.  
✔ **Ejemplo:**
```
🔲 Comprar leche  → ✅ Comprar leche
🔲 Estudiar Java  → ✅ Estudiar Java
```

---

<details>
    <summary>Solución</summary>

## **1️⃣ Modificar el Modelo (`Tarea.java`)**

📌 **Agregar método `setCompletada()` para cambiar el estado de la tarea:**
```java
package modelo;

public class Tarea {
    private String descripcion;
    private boolean completada;

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void completar() {
        this.completada = !this.completada; // Cambia el estado
    }

    @Override
    public String toString() {
        return (completada ? "✅ " : "🔲 ") + descripcion;
    }
}
```
✅ **Explicación:**  
✔ **`completar()` cambia el estado de la tarea** (toggle ✅/🔲).  
✔ **`toString()`** muestra la tarea con **"✅" si está completada**.

---

## **2️⃣ Modificar la Vista (`tareas.fxml`)**

📌 **Permitir doble clic en la lista para completar una tarea:**
```xml
<ListView fx:id="listaTareas" onMouseClicked="#completarTarea"/>
```
✅ **Explicación:**  
✔ **Cada vez que se haga doble clic en una tarea, se llamará a `completarTarea()`.**

---

## **3️⃣ Modificar el Controlador (`TareaController.java`)**

📌 **Agregar el método `completarTarea()` en el controlador:**
```java
package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modelo.Tarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TareaController {
    @FXML private TextField campoTarea;
    @FXML private ListView<Tarea> listaTareas;

    private ObservableList<Tarea> tareas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listaTareas.setItems(tareas);
    }

    @FXML
    public void agregarTarea() {
        String descripcion = campoTarea.getText();
        if (!descripcion.isEmpty()) {
            tareas.add(new Tarea(descripcion));
            campoTarea.clear();
        }
    }

    @FXML
    public void completarTarea() {
        Tarea seleccionada = listaTareas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            seleccionada.completar(); // Cambia el estado
            listaTareas.refresh(); // Refresca la vista para actualizar el estado
        }
    }
}
```
✅ **Explicación:**  
✔ **`completarTarea()`** obtiene la tarea seleccionada y la marca como completada.  
✔ **`listaTareas.refresh()`** fuerza la actualización en la interfaz.

---

## **4️⃣ Ejecutar la Aplicación (`Main.java`)**

📌 **No es necesario modificar `Main.java`, pero lo incluimos por completitud:**
```java
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage escenario) throws Exception {
        Parent raiz = FXMLLoader.load(getClass().getResource("/vista/tareas.fxml"));
        Scene escena = new Scene(raiz, 300, 400);
        
        escenario.setTitle("Gestor de Tareas");
        escenario.setScene(escena);
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```
---

# **📌 Resultado Esperado** 🎯

✅ **Al hacer doble clic en una tarea, esta se marca como completada (✅).**  
✅ **Ejemplo antes de hacer doble clic:**
```
🔲 Comprar leche  
🔲 Estudiar Java  
```
✅ **Ejemplo después de hacer doble clic en "Estudiar Java":**
```
🔲 Comprar leche  
✅ Estudiar Java  
```

---

</details>
