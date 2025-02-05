# **📌 Día 20: Manejo de Conexiones y Pool de Conexiones en Java** 🏗️

📌 **Objetivo del día:**  
✅ Aprender a **optimizar la conexión con bases de datos** en Java.  
✅ Usar **Connection Pooling** con **HikariCP** y **Apache DBCP**.  
✅ Comparar rendimiento entre **conexiones simples vs pool de conexiones**.  
✅ **Ejercicio:** Implementar un **sistema con pool de conexiones para optimizar consultas SQL**.

---

# **1️⃣ ¿Qué es un Pool de Conexiones?**

📌 **Problema sin pool de conexiones:**  
✔ Cada vez que una consulta se ejecuta, Java crea una **nueva conexión** a la base de datos.  
✔ Esto **consume muchos recursos** y **ralentiza** la aplicación.  
✔ En sistemas de alto tráfico, puede **colapsar la base de datos**.

📌 **Solución con Pool de Conexiones:**  
✔ En lugar de crear una conexión nueva cada vez, **se reutiliza una conexión existente**.  
✔ Se mantiene un **conjunto de conexiones activas** (pool).  
✔ Se mejora **rendimiento y escalabilidad**.

---

# **2️⃣ Comparación: Conexión Simple vs Pool de Conexiones**

📌 **❌ Conexión Tradicional (Mala Práctica)**
```java
public class ConexionSinPool {
    public static Connection conectar() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
            return null;
        }
    }
}
```
✔ **Desventajas:**  
❌ Cada consulta abre una nueva conexión.  
❌ **Alto consumo de recursos** en consultas repetitivas.

📌 **✅ Conexión con Pool (Buena Práctica - HikariCP)**
```java
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionPool {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/tienda");
        config.setUsername("root");
        config.setPassword("");
        config.setMaximumPoolSize(10); // Máximo de conexiones en el pool
        config.setMinimumIdle(2); // Mínimo de conexiones en reposo
        ds = new HikariDataSource(config);
    }

    public static Connection conectar() throws SQLException {
        return ds.getConnection();
    }
}
```
✔ **Ventajas:**  
✅ **Reutiliza conexiones**, reduciendo la sobrecarga.  
✅ **Mejora el rendimiento** en sistemas de alto tráfico.

---

# **3️⃣ Configurar Pool de Conexiones en Maven**

📌 **Agregar Dependencia de HikariCP en `pom.xml`**
```xml
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>5.0.1</version>
</dependency>
```
📌 **Agregar Dependencia de Apache Commons DBCP (Otra opción de pool)**
```xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-dbcp2</artifactId>
    <version>2.9.0</version>
</dependency>
```

---

# **4️⃣ Uso del Pool de Conexiones en un DAO**

📌 **Implementación del DAO con HikariCP**
```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    // Insertar pedido
    public void agregarPedido(String cliente, String producto, int cantidad, double precio) {
        String sql = "INSERT INTO pedidos (cliente, producto, cantidad, precio) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionPool.conectar();
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

    // Obtener todos los pedidos
    public List<String> obtenerPedidos() {
        List<String> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection conexion = ConexionPool.conectar();
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
}
```

---

# **📌 Ejercicio del Día 20: Optimización de Consultas con Pool de Conexiones** 🎯

📌 **Objetivo:**  
✔ Implementar un **DAO optimizado** con pool de conexiones (`HikariCP`).  
✔ Comparar rendimiento **antes y después del pool**.  
✔ Usar **Apache Commons DBCP como alternativa**.

📌 **Clase `Main` con Menú para probar el Pool de Conexiones:**
```java
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PedidoDAO pedidoDAO = new PedidoDAO();

        while (true) {
            System.out.println("\n📦 GESTOR DE PEDIDOS (OPTIMIZADO)");
            System.out.println("1. Agregar Pedido");
            System.out.println("2. Mostrar Pedidos");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (opcion == 3) break;

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
                default -> System.out.println("❌ Opción no válida.");
            }
        }
        scanner.close();
    }
}
```

✅ **Ejemplo de salida esperada:**
```
📦 GESTOR DE PEDIDOS (OPTIMIZADO)
1. Agregar Pedido
2. Mostrar Pedidos
3. Salir
Opción: 1
Cliente: Ana
Producto: Teclado
Cantidad: 5
Precio: 25.99
✅ Pedido agregado con éxito.

Opción: 2
📦 Pedidos Registrados:
ID: 1 | Cliente: Ana | Producto: Teclado | Cantidad: 5 | Precio: 25.99€
```

---

<details>
    <summary>Solución</summary>

## **1️⃣ Configuración de la Base de Datos** 🛠️

📌 **Ejecutar en MySQL:**
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

📌 **Agregar Dependencia HikariCP en `pom.xml` (Si usas Maven):**
```xml
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>5.0.1</version>
</dependency>
```

📌 **Agregar Dependencia Apache Commons DBCP (Otra opción de pool de conexiones):**
```xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-dbcp2</artifactId>
    <version>2.9.0</version>
</dependency>
```

---

## **2️⃣ Implementación del Pool de Conexiones con HikariCP**

📌 **Clase `ConexionPool` para manejar el pool de conexiones:**
```java
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionPool {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/tienda");
        config.setUsername("root");  // Cambiar si tienes usuario diferente
        config.setPassword("");      // Si tienes contraseña, agrégala
        config.setMaximumPoolSize(10); // Máximo de conexiones en el pool
        config.setMinimumIdle(2); // Mínimo de conexiones en reposo
        config.setIdleTimeout(30000); // Tiempo de inactividad antes de cerrar conexiones
        config.setMaxLifetime(1800000); // Vida máxima de una conexión en el pool

        ds = new HikariDataSource(config);
    }

    public static Connection conectar() throws SQLException {
        return ds.getConnection();
    }
}
```
✅ **Ventajas:**  
✔ **Optimiza rendimiento y reduce carga en la base de datos.**  
✔ **Reutiliza conexiones en lugar de crear nuevas constantemente.**

---

## **3️⃣ Implementación del DAO con Pool de Conexiones**

📌 **Clase `PedidoDAO` para manejar pedidos en MySQL:**
```java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    // Insertar un pedido en la base de datos
    public void agregarPedido(String cliente, String producto, int cantidad, double precio) {
        String sql = "INSERT INTO pedidos (cliente, producto, cantidad, precio) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionPool.conectar();
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

    // Obtener todos los pedidos
    public List<String> obtenerPedidos() {
        List<String> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection conexion = ConexionPool.conectar();
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

        try (Connection conexion = ConexionPool.conectar();
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

        try (Connection conexion = ConexionPool.conectar();
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

---

## **4️⃣ Implementación del Menú Interactivo**

📌 **Clase `Main` con opciones para gestionar pedidos:**
```java
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PedidoDAO pedidoDAO = new PedidoDAO();

        while (true) {
            System.out.println("\n📦 GESTOR DE PEDIDOS (OPTIMIZADO)");
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
📦 GESTOR DE PEDIDOS (OPTIMIZADO)
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
```

---

</details>