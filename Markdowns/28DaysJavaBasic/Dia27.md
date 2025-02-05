# **📌 Día 27: Proyecto Final - Desarrollo de un Sistema de Inventario** 📦🔍

📌 **Objetivo del día:**  
✅ Construir una **aplicación completa de gestión de inventario** en Java.  
✅ Integrar **JavaFX + MySQL + JDBC** con **MVC y SOLID**.  
✅ Implementar **CRUD visual** para administrar productos.

---

## **1️⃣ Requisitos del Proyecto**

📌 **Funcionalidades del Sistema de Inventario:**  
✔ **Agregar, editar y eliminar productos.**  
✔ **Mostrar los productos en una tabla.**  
✔ **Guardar los datos en una base de datos MySQL.**  
✔ **Diseño basado en MVC + SOLID.**

📌 **Tecnologías utilizadas:**  
✅ **JavaFX** → Interfaz gráfica.  
✅ **MySQL + JDBC** → Persistencia de datos.  
✅ **Patrón MVC** → Separación de responsabilidades.  
✅ **Principios SOLID** → Código limpio y escalable.

---

## **2️⃣ Configuración del Proyecto**

📌 **Estructura del Proyecto:**
```
InventarioApp/
 ├── src/
 │   ├── modelo/ (Model)
 │   │   ├── Producto.java
 │   │   ├── ProductoDAO.java
 │   ├── vista/ (View)
 │   │   ├── inventario.fxml
 │   ├── controlador/ (Controller)
 │   │   ├── ProductoController.java
 │   ├── ConexionBD.java
 │   ├── Main.java
 ├── pom.xml
```

📌 **Base de Datos MySQL (`inventario_db`)**
```sql
CREATE DATABASE inventario_db;
USE inventario_db;

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    cantidad INT NOT NULL
);
```

📌 **Dependencias en `pom.xml` (para Maven)**
```xml
<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21</version>
    </dependency>
</dependencies>
```

---

## **3️⃣ Implementación del Modelo (`Producto.java` y `ProductoDAO.java`)**

📌 **Clase `Producto.java` (Representa un producto)**
```java
package modelo;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(int id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
}
```

📌 **Clase `ProductoDAO.java` (Acceso a la base de datos - CRUD)**
```java
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conexion = ConexionBD.conectar();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }

    public void agregarProducto(String nombre, double precio, int cantidad) {
        String sql = "INSERT INTO productos (nombre, precio, cantidad) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setDouble(2, precio);
            stmt.setInt(3, cantidad);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error al agregar producto: " + e.getMessage());
        }
    }
}
```

---

## **4️⃣ Implementación de la Vista (`inventario.fxml`)**

📌 **Archivo `inventario.fxml` (Interfaz gráfica con tabla y formulario)**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<VBox spacing="10" alignment="CENTER" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1"
      fx:controller="controlador.ProductoController">
    
    <Label text="Gestión de Inventario" style="-fx-font-size: 18px;"/>
    
    <TableView fx:id="tablaProductos">
        <columns>
            <TableColumn text="ID" fx:id="columnaId"/>
            <TableColumn text="Nombre" fx:id="columnaNombre"/>
            <TableColumn text="Precio" fx:id="columnaPrecio"/>
            <TableColumn text="Cantidad" fx:id="columnaCantidad"/>
        </columns>
    </TableView>

    <HBox spacing="10">
        <TextField fx:id="campoNombre" promptText="Nombre"/>
        <TextField fx:id="campoPrecio" promptText="Precio"/>
        <TextField fx:id="campoCantidad" promptText="Cantidad"/>
        <Button text="Agregar" onAction="#agregarProducto"/>
    </HBox>
    
</VBox>
```

---

## **5️⃣ Implementación del Controlador (`ProductoController.java`)**

📌 **Clase `ProductoController.java` (Maneja la lógica de la vista)**
```java
package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Producto;
import modelo.ProductoDAO;
import java.sql.*;

public class ProductoController {
    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, Integer> columnaId;
    @FXML private TableColumn<Producto, String> columnaNombre;
    @FXML private TableColumn<Producto, Double> columnaPrecio;
    @FXML private TableColumn<Producto, Integer> columnaCantidad;
    @FXML private TextField campoNombre;
    @FXML private TextField campoPrecio;
    @FXML private TextField campoCantidad;

    private ProductoDAO productoDAO = new ProductoDAO();
    private ObservableList<Producto> productos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        columnaId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        columnaNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        columnaPrecio.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrecio()).asObject());
        columnaCantidad.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCantidad()).asObject());

        tablaProductos.setItems(productos);
        cargarProductos();
    }

    private void cargarProductos() {
        productos.setAll(productoDAO.obtenerProductos());
    }

    @FXML
    public void agregarProducto() {
        String nombre = campoNombre.getText();
        double precio = Double.parseDouble(campoPrecio.getText());
        int cantidad = Integer.parseInt(campoCantidad.getText());

        productoDAO.agregarProducto(nombre, precio, cantidad);
        cargarProductos();

        campoNombre.clear();
        campoPrecio.clear();
        campoCantidad.clear();
    }
}
```

---
