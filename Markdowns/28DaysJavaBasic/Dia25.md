# **📌 Día 25: Conectar JavaFX con una Base de Datos (JavaFX + JDBC)** 🗄️🖥️

📌 **Objetivo del día:**  
✅ Aprender a conectar **JavaFX con una base de datos MySQL** mediante **JDBC**.  
✅ Implementar un **CRUD visual** con **tabla dinámica** en JavaFX.  
✅ **Ejercicio:** Crear una aplicación de **gestión de productos** con JavaFX y MySQL.

---

## **1️⃣ Configuración de la Base de Datos**

📌 **1️⃣ Crear la base de datos y tabla en MySQL**  
Ejecutar en MySQL Workbench o cualquier cliente MySQL:
```sql
CREATE DATABASE tienda;
USE tienda;

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL
);
```
📌 **2️⃣ Agregar Dependencias en `pom.xml`** (si usas Maven):
```xml
<dependencies>
    <!-- Conector JDBC para MySQL -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>

    <!-- JavaFX -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21</version>
    </dependency>
</dependencies>
```
📌 **3️⃣ Configurar la Conexión a la Base de Datos**  
✔ **Crear una clase `ConexionBD.java` para manejar la conexión a MySQL.**
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/tienda";
    private static final String USUARIO = "root"; 
    private static final String PASSWORD = ""; 

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
            return null;
        }
    }
}
```
✅ **Prueba:** Llamar `ConexionBD.conectar();` para verificar la conexión.

---

## **2️⃣ Estructura del Proyecto (JavaFX + JDBC)**

✔ **Carpeta del Proyecto:**
```
TiendaApp/
 ├── src/
 │   ├── modelo/ (Model)
 │   │   ├── Producto.java
 │   ├── vista/ (View)
 │   │   ├── productos.fxml
 │   ├── controlador/ (Controller)
 │   │   ├── ProductoController.java
 │   ├── ConexionBD.java
 │   ├── Main.java
 ├── pom.xml
```

---

## **3️⃣ Crear el Modelo (`Producto.java`)**

📌 **Clase `Producto.java` (Representa un producto de la base de datos)**
```java
package modelo;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
}
```

---

## **4️⃣ Crear la Vista (`productos.fxml`)**

📌 **Archivo `productos.fxml` (Interfaz de usuario con tabla y formularios)**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<VBox spacing="10" alignment="CENTER" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1"
      fx:controller="controlador.ProductoController">
    
    <Label text="Gestión de Productos" style="-fx-font-size: 18px;"/>
    
    <TableView fx:id="tablaProductos">
        <columns>
            <TableColumn text="ID" fx:id="columnaId"/>
            <TableColumn text="Nombre" fx:id="columnaNombre"/>
            <TableColumn text="Precio" fx:id="columnaPrecio"/>
            <TableColumn text="Stock" fx:id="columnaStock"/>
        </columns>
    </TableView>

    <HBox spacing="10">
        <TextField fx:id="campoNombre" promptText="Nombre"/>
        <TextField fx:id="campoPrecio" promptText="Precio"/>
        <TextField fx:id="campoStock" promptText="Stock"/>
        <Button text="Agregar" onAction="#agregarProducto"/>
    </HBox>
    
</VBox>
```
✅ **Explicación:**  
✔ `TableView` muestra los productos en la base de datos.  
✔ `TextField` permite ingresar datos.  
✔ `Button` llama a `agregarProducto()` cuando se presiona.

---

## **5️⃣ Crear el Controlador (`ProductoController.java`)**

📌 **Clase `ProductoController.java` (Maneja la lógica de la vista)**
```java
package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Producto;
import java.sql.*;

public class ProductoController {
    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, Integer> columnaId;
    @FXML private TableColumn<Producto, String> columnaNombre;
    @FXML private TableColumn<Producto, Double> columnaPrecio;
    @FXML private TableColumn<Producto, Integer> columnaStock;
    @FXML private TextField campoNombre;
    @FXML private TextField campoPrecio;
    @FXML private TextField campoStock;

    private ObservableList<Producto> productos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Asociar columnas con atributos de Producto
        columnaId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        columnaNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        columnaPrecio.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrecio()).asObject());
        columnaStock.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStock()).asObject());

        tablaProductos.setItems(productos);
        cargarProductos();
    }

    private void cargarProductos() {
        productos.clear();
        String sql = "SELECT * FROM productos";

        try (Connection conexion = ConexionBD.conectar();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al cargar productos: " + e.getMessage());
        }
    }

    @FXML
    public void agregarProducto() {
        String nombre = campoNombre.getText();
        double precio = Double.parseDouble(campoPrecio.getText());
        int stock = Integer.parseInt(campoStock.getText());

        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setDouble(2, precio);
            stmt.setInt(3, stock);
            stmt.executeUpdate();

            cargarProductos();
            campoNombre.clear();
            campoPrecio.clear();
            campoStock.clear();
        } catch (SQLException e) {
            System.out.println("❌ Error al agregar producto: " + e.getMessage());
        }
    }
}
```
