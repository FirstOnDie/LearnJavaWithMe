# **✅ Ejercicio Semana 4: Proyecto Final Completo 🎯**

📌 **Objetivo:**  
✅ Construir una aplicación **completa y funcional** en Java.  
✅ Integrar **JavaFX, MySQL, JDBC, SOLID, MVC y Docker**.  
✅ Implementar un **CRUD avanzado** con filtros y búsquedas.  
✅ Desplegar la aplicación como **JAR ejecutable y en Docker**.

---

# **📌 1️⃣ Proyecto: Sistema de Gestión de Ventas 🛒**

📌 **Requisitos del sistema:**  
✔ **Módulo de Productos:** Agregar, editar, eliminar y buscar productos.  
✔ **Módulo de Ventas:** Registrar ventas y calcular el total.  
✔ **Reportes:** Mostrar resumen de ventas diarias.  
✔ **Base de datos MySQL con persistencia de datos.**  
✔ **Interfaz gráfica en JavaFX.**

📌 **Tecnologías utilizadas:**  
✅ **JavaFX** → Interfaz gráfica.  
✅ **MySQL + JDBC** → Persistencia de datos.  
✅ **Patrón MVC + SOLID** → Código modular y escalable.  
✅ **Docker** → Contenedor para el despliegue.

---

# **📌 2️⃣ Configuración del Proyecto**

📌 **Estructura del Proyecto:**
```
VentasApp/
 ├── src/
 │   ├── modelo/ (Model)
 │   │   ├── Producto.java
 │   │   ├── ProductoDAO.java
 │   │   ├── Venta.java
 │   │   ├── VentaDAO.java
 │   ├── vista/ (View)
 │   │   ├── productos.fxml
 │   │   ├── ventas.fxml
 │   ├── controlador/ (Controller)
 │   │   ├── ProductoController.java
 │   │   ├── VentaController.java
 │   ├── ConexionBD.java
 │   ├── Main.java
 ├── Dockerfile
 ├── pom.xml
```

📌 **Base de Datos MySQL (`ventas_db`)**
```sql
CREATE DATABASE ventas_db;
USE ventas_db;

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT,
    cantidad INT NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (producto_id) REFERENCES productos(id)
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

# **📌 3️⃣ Implementación del Modelo**

📌 **Clase `Producto.java` (Entidad Producto)**
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

    public void reducirStock(int cantidad) {
        this.stock -= cantidad;
    }
}
```

📌 **Clase `Venta.java` (Entidad Venta)**
```java
package modelo;

import java.time.LocalDateTime;

public class Venta {
    private int id;
    private Producto producto;
    private int cantidad;
    private double total;
    private LocalDateTime fecha;

    public Venta(int id, Producto producto, int cantidad, double total, LocalDateTime fecha) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getTotal() { return total; }
    public LocalDateTime getFecha() { return fecha; }
}
```

---

# **📌 4️⃣ Implementación del Controlador**

📌 **Clase `ProductoController.java` (Gestión de Productos)**
```java
package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Producto;
import modelo.ProductoDAO;

public class ProductoController {
    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, Integer> columnaId;
    @FXML private TableColumn<Producto, String> columnaNombre;
    @FXML private TableColumn<Producto, Double> columnaPrecio;
    @FXML private TableColumn<Producto, Integer> columnaStock;
    @FXML private TextField campoNombre;
    @FXML private TextField campoPrecio;
    @FXML private TextField campoStock;

    private ProductoDAO productoDAO = new ProductoDAO();
    private ObservableList<Producto> productos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        columnaId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        columnaNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        columnaPrecio.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrecio()).asObject());
        columnaStock.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStock()).asObject());

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
        int stock = Integer.parseInt(campoStock.getText());

        productoDAO.agregarProducto(nombre, precio, stock);
        cargarProductos();

        campoNombre.clear();
        campoPrecio.clear();
        campoStock.clear();
    }
}
```

---

# **📌 5️⃣ Despliegue con Docker**

📌 **1️⃣ Crear el `Dockerfile` para ejecutar la aplicación en un contenedor:**
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/ventas-app.jar /app.jar
CMD ["java", "-jar", "/app.jar"]
```

📌 **2️⃣ Construir y ejecutar la imagen:**
```sh
docker build -t ventas-app .
docker run -p 8080:8080 ventas-app
```

✅ **Nuestra aplicación ahora puede ejecutarse en cualquier entorno con Docker.**

---
