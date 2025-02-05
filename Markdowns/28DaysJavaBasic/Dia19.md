# **📌 Día 19: Consultas SQL en Java (SELECT, INSERT, UPDATE, DELETE) con JDBC** 🗄️

📌 **Objetivo del día:**  
✅ Aprender a ejecutar consultas SQL en Java usando **JDBC**.  
✅ Implementar las operaciones **CRUD**:  
✔ **`SELECT`** → Leer datos de la base de datos.  
✔ **`INSERT`** → Agregar nuevos registros.  
✔ **`UPDATE`** → Modificar registros existentes.  
✔ **`DELETE`** → Eliminar registros.  
✅ **Ejercicio:** Implementar un **sistema de gestión de pedidos con consultas SQL**.

---

## **1️⃣ Configuración de la Base de Datos**

📌 **1️⃣ Crear Base de Datos y Tabla en MySQL**  
Ejecutar en MySQL Workbench o cualquier cliente MySQL:
```sql
CREATE DATABASE tienda;
USE tienda;

CREATE TABLE pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente VARCHAR(100) NOT NULL,
    producto VARCHAR(100) NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL
);
```

📌 **2️⃣ Configurar JDBC en el Proyecto**  
Si usas **Maven**, agrega el conector JDBC de MySQL en `pom.xml`:
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```
Si **NO usas Maven**, descarga `mysql-connector-java.jar` y agrégalo manualmente.

---

## **2️⃣ Conexión a la Base de Datos con JDBC**

📌 **Clase `ConexionBD` para gestionar la conexión:**
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/tienda";
    private static final String USUARIO = "root"; // Cambiar según configuración
    private static final String PASSWORD = ""; // Si tienes contraseña, agrégala

    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos.");
            return conexion;
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
            return null;
        }
    }
}
```
✅ **Prueba:** Ejecuta `ConexionBD.conectar();` para verificar la conexión.

---

## **3️⃣ Consultas SQL en Java (CRUD con JDBC)**

📌 **Clase `PedidoDAO` con métodos SQL (`SELECT`, `INSERT`, `UPDATE`, `DELETE`)**

```java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    // Insertar un pedido en la base de datos
    public void agregarPedido(String cliente, String producto, int cantidad, double precio) {
        String sql = "INSERT INTO pedidos (cliente, producto, cantidad, precio) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, cliente);
            stmt.setString(2, producto);
            stmt.setInt(3, cantidad);
            stmt.setDouble(4, precio);
            stmt.executeUpdate();

            System.out.println("✅ Pedido agregado con éxito.");
        } catch (SQLException e) {
            System.out.println("❌ Error al agregar pedido: " + e.getMessage());
        }
    }

    // Leer todos los pedidos
    public List<String> obtenerPedidos() {
        List<String> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String cliente = rs.getString("cliente");
                String producto = rs.getString("producto");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");

                pedidos.add("ID: " + id + " | Cliente: " + cliente + " | Producto: " + producto +
                        " | Cantidad: " + cantidad + " | Precio: " + precio + "€");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener pedidos: " + e.getMessage());
        }
        return pedidos;
    }

    // Actualizar un pedido
    public void actualizarPedido(int id, int nuevaCantidad) {
        String sql = "UPDATE pedidos SET cantidad = ? WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, nuevaCantidad);
            stmt.setInt(2, id);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("✅ Pedido actualizado.");
            } else {
                System.out.println("❌ Pedido no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar pedido: " + e.getMessage());
        }
    }

    // Eliminar un pedido
    public void eliminarPedido(int id) {
        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filasEliminadas = stmt.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("✅ Pedido eliminado.");
            } else {
                System.out.println("❌ Pedido no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar pedido: " + e.getMessage());
        }
    }
}
```
✅ **Uso:** Maneja pedidos en la base de datos con seguridad.

---

## **4️⃣ Menú Interactivo para Gestionar Pedidos**

📌 **Clase `Main` con opciones CRUD:**
```java
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PedidoDAO pedidoDAO = new PedidoDAO();

        while (true) {
            System.out.println("\n📦 GESTOR DE PEDIDOS");
            System.out.println("1. Agregar Pedido");
            System.out.println("2. Mostrar Pedidos");
            System.out.println("3. Actualizar Pedido");
            System.out.println("4. Eliminar Pedido");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (opcion == 5) break;

            switch (opcion) {
                case 1 -> {
                    System.out.print("Cliente: ");
                    String cliente = scanner.nextLine();
                    System.out.print("Producto: ");
                    String producto = scanner.nextLine();
                    System.out.print("Cantidad: ");
                    int cantidad = scanner.nextInt();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    pedidoDAO.agregarPedido(cliente, producto, cantidad, precio);
                }
                case 2 -> {
                    List<String> pedidos = pedidoDAO.obtenerPedidos();
                    if (pedidos.isEmpty()) {
                        System.out.println("📂 No hay pedidos registrados.");
                    } else {
                        pedidos.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("ID del pedido: ");
                    int id = scanner.nextInt();
                    System.out.print("Nueva cantidad: ");
                    int cantidad = scanner.nextInt();
                    pedidoDAO.actualizarPedido(id, cantidad);
                }
                case 4 -> {
                    System.out.print("ID del pedido: ");
                    int id = scanner.nextInt();
                    pedidoDAO.eliminarPedido(id);
                }
                default -> System.out.println("❌ Opción no válida.");
            }
        }
        scanner.close();
    }
}
```

✅ **Ejemplo de salida esperada:**
```
📦 GESTOR DE PEDIDOS
1. Agregar Pedido
2. Mostrar Pedidos
3. Actualizar Pedido
4. Eliminar Pedido
5. Salir
Opción: 1
Cliente: Juan
Producto: Laptop
Cantidad: 2
Precio: 1200.50
✅ Pedido agregado con éxito.

Opción: 2
📦 Pedidos Registrados:
ID: 1 | Cliente: Juan | Producto: Laptop | Cantidad: 2 | Precio: 1200.50€
```
