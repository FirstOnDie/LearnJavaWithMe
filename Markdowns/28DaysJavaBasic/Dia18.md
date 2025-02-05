# **📌 Día 18: Introducción a Bases de Datos con Java (JDBC)** 🗄️

📌 **Objetivo del día:**  
✅ Conectar Java con una base de datos mediante **JDBC (Java Database Connectivity)**.  
✅ Aprender a realizar **CRUD (Create, Read, Update, Delete)** en una base de datos.  
✅ Usar **`PreparedStatement`** para prevenir **inyección SQL**.  
✅ **Ejercicio:** Implementar un **sistema de gestión de clientes con una base de datos MySQL**.

---

# **1️⃣ ¿Qué es JDBC?**

📌 **JDBC (Java Database Connectivity)** es una API que permite conectar Java con bases de datos.  
✔ Permite ejecutar consultas SQL desde Java.  
✔ Funciona con **MySQL, PostgreSQL, SQL Server, SQLite, Oracle, etc.**.  
✔ Usa **`DriverManager`** para conectar con la base de datos.

📌 **Pasos para usar JDBC:**  
1️⃣ **Agregar el Driver JDBC** (Ejemplo: MySQL → `mysql-connector-java.jar`).  
2️⃣ **Establecer conexión con la base de datos (`Connection`)**.  
3️⃣ **Ejecutar consultas SQL (`Statement` o `PreparedStatement`)**.  
4️⃣ **Leer los resultados (`ResultSet`)**.  
5️⃣ **Cerrar la conexión** para liberar recursos.

---

# **2️⃣ Configuración de MySQL y JDBC**

📌 **1️⃣ Descargar e instalar MySQL** (o usar SQLite si prefieres una opción sin instalación).  
📌 **2️⃣ Descargar el Driver JDBC de MySQL:**  
👉 [https://dev.mysql.com/downloads/connector/j/](https://dev.mysql.com/downloads/connector/j/)  
📌 **3️⃣ Agregar `mysql-connector-java.jar` al proyecto (si usas un IDE como IntelliJ o Eclipse).**

📌 **4️⃣ Crear una base de datos y tabla en MySQL:**
```sql
CREATE DATABASE tienda;
USE tienda;

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    correo VARCHAR(50),
    edad INT
);
```

---

# **3️⃣ Conectar Java con MySQL usando JDBC**

📌 **Ejemplo: Conectar Java con MySQL**
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
            Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos.");
            return conexion;
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        conectar(); // Probar conexión
    }
}
```

✅ **Salida esperada si la conexión es correcta:**
```
✅ Conexión exitosa a la base de datos.
```

✔ **`DriverManager.getConnection(URL, USUARIO, PASSWORD)` establece la conexión.**

---

# **4️⃣ CRUD en Base de Datos con JDBC**

📌 **Insertar un cliente en la base de datos (`INSERT`)**
```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {
    public void agregarCliente(String nombre, String correo, int edad) {
        String sql = "INSERT INTO clientes (nombre, correo, edad) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, correo);
            stmt.setInt(3, edad);

            stmt.executeUpdate();
            System.out.println("✅ Cliente agregado con éxito.");

        } catch (SQLException e) {
            System.out.println("❌ Error al agregar cliente: " + e.getMessage());
        }
    }
}
```

📌 **Leer clientes (`SELECT`)**
```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
    public void mostrarClientes() {
        String sql = "SELECT * FROM clientes";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                int edad = rs.getInt("edad");

                System.out.println("ID: " + id + " | Nombre: " + nombre + " | Correo: " + correo + " | Edad: " + edad);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener clientes: " + e.getMessage());
        }
    }
}
```

📌 **Actualizar un cliente (`UPDATE`)**
```java
public void actualizarCliente(int id, String nuevoCorreo) {
    String sql = "UPDATE clientes SET correo = ? WHERE id = ?";

    try (Connection conexion = ConexionBD.conectar();
         PreparedStatement stmt = conexion.prepareStatement(sql)) {

        stmt.setString(1, nuevoCorreo);
        stmt.setInt(2, id);

        int filasActualizadas = stmt.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("✅ Cliente actualizado.");
        } else {
            System.out.println("❌ Cliente no encontrado.");
        }

    } catch (SQLException e) {
        System.out.println("❌ Error al actualizar cliente: " + e.getMessage());
    }
}
```

📌 **Eliminar un cliente (`DELETE`)**
```java
public void eliminarCliente(int id) {
    String sql = "DELETE FROM clientes WHERE id = ?";

    try (Connection conexion = ConexionBD.conectar();
         PreparedStatement stmt = conexion.prepareStatement(sql)) {

        stmt.setInt(1, id);
        int filasEliminadas = stmt.executeUpdate();

        if (filasEliminadas > 0) {
            System.out.println("✅ Cliente eliminado.");
        } else {
            System.out.println("❌ Cliente no encontrado.");
        }

    } catch (SQLException e) {
        System.out.println("❌ Error al eliminar cliente: " + e.getMessage());
    }
}
```

---

# **📌 Ejercicio del Día 18: Sistema de Gestión de Clientes con Base de Datos** 🎯

📌 **Objetivo:**  
✔ Implementar una **clase `ClienteDAO`** para manejar clientes en MySQL.  
✔ Métodos:
- `agregarCliente(String nombre, String correo, int edad)`
- `mostrarClientes()`
- `actualizarCliente(int id, String nuevoCorreo)`
- `eliminarCliente(int id)`

📌 **Clase `Main` con Menú Interactivo:**
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO();

        while (true) {
            System.out.println("\n📂 GESTOR DE CLIENTES");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Mostrar Clientes");
            System.out.println("3. Actualizar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (opcion == 5) break;

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Correo: ");
                    String correo = scanner.nextLine();
                    System.out.print("Edad: ");
                    int edad = scanner.nextInt();
                    clienteDAO.agregarCliente(nombre, correo, edad);
                }
                case 2 -> clienteDAO.mostrarClientes();
                case 3 -> {
                    System.out.print("ID del cliente: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nuevo correo: ");
                    String correo = scanner.nextLine();
                    clienteDAO.actualizarCliente(id, correo);
                }
                case 4 -> {
                    System.out.print("ID del cliente: ");
                    int id = scanner.nextInt();
                    clienteDAO.eliminarCliente(id);
                }
                default -> System.out.println("❌ Opción no válida.");
            }
        }
        scanner.close();
    }
}
```

<details>
    <summary>Solución</summary>

### **📌 Ejercicio Resuelto: Sistema de Gestión de Clientes con MySQL y JDBC** 🗄️  

📌 **Objetivo:**  
✅ Crear una aplicación en Java que administre clientes en una base de datos MySQL usando JDBC.  
✅ Implementar las funciones:
- **Agregar clientes (`INSERT`)**
- **Mostrar clientes (`SELECT`)**
- **Actualizar clientes (`UPDATE`)**
- **Eliminar clientes (`DELETE`)**  
  ✅ Utilizar **`PreparedStatement`** para prevenir inyección SQL.  
  ✅ Usar un **menú interactivo** para manejar clientes.

---

## **1️⃣ Configuración de la Base de Datos**

📌 **1️⃣ Crear Base de Datos y Tabla en MySQL:**  
Ejecutar el siguiente SQL en **MySQL**:
```sql
CREATE DATABASE tienda;
USE tienda;

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    correo VARCHAR(50) UNIQUE NOT NULL,
    edad INT NOT NULL
);
```

📌 **2️⃣ Agregar el Driver JDBC de MySQL**
- **Si usas Maven**, agregar en `pom.xml`:
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```
- **Si no usas Maven**, descarga `mysql-connector-java.jar` y agrégalo al proyecto.

---

## **2️⃣ Implementación en Java**

📌 **Clase `ConexionBD` para conectar con MySQL:**
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/tienda";
    private static final String USUARIO = "root";
    private static final String PASSWORD = ""; // Cambiar si es necesario

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
✅ **Uso:** Llama a `ConexionBD.conectar();` para probar la conexión.

---

📌 **Clase `Cliente` (POJO - Plain Old Java Object)**
```java
public class Cliente {
    private int id;
    private String nombre;
    private String correo;
    private int edad;

    public Cliente(int id, String nombre, String correo, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
    }

    public void mostrarInfo() {
        System.out.println("ID: " + id + " | Nombre: " + nombre + " | Correo: " + correo + " | Edad: " + edad);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public int getEdad() {
        return edad;
    }
}
```
✅ **Uso:** Define un objeto `Cliente` con su información.

---

📌 **Clase `ClienteDAO` (Acceso a Datos)**
```java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    // Agregar un cliente
    public void agregarCliente(String nombre, String correo, int edad) {
        String sql = "INSERT INTO clientes (nombre, correo, edad) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, correo);
            stmt.setInt(3, edad);
            stmt.executeUpdate();

            System.out.println("✅ Cliente agregado con éxito.");
        } catch (SQLException e) {
            System.out.println("❌ Error al agregar cliente: " + e.getMessage());
        }
    }

    // Obtener todos los clientes
    public List<Cliente> obtenerClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getInt("edad")
                );
                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener clientes: " + e.getMessage());
        }
        return lista;
    }

    // Actualizar un cliente
    public void actualizarCliente(int id, String nuevoCorreo) {
        String sql = "UPDATE clientes SET correo = ? WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, nuevoCorreo);
            stmt.setInt(2, id);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("✅ Cliente actualizado.");
            } else {
                System.out.println("❌ Cliente no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar cliente: " + e.getMessage());
        }
    }

    // Eliminar un cliente
    public void eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filasEliminadas = stmt.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("✅ Cliente eliminado.");
            } else {
                System.out.println("❌ Cliente no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar cliente: " + e.getMessage());
        }
    }
}
```
✅ **Uso:** Permite manejar clientes en la base de datos con seguridad.

---

📌 **Clase `Main` con Menú Interactivo:**
```java
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO();

        while (true) {
            System.out.println("\n📂 GESTOR DE CLIENTES");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Mostrar Clientes");
            System.out.println("3. Actualizar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (opcion == 5) break;

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Correo: ");
                    String correo = scanner.nextLine();
                    System.out.print("Edad: ");
                    int edad = scanner.nextInt();
                    clienteDAO.agregarCliente(nombre, correo, edad);
                }
                case 2 -> {
                    List<Cliente> clientes = clienteDAO.obtenerClientes();
                    if (clientes.isEmpty()) {
                        System.out.println("📂 No hay clientes registrados.");
                    } else {
                        clientes.forEach(Cliente::mostrarInfo);
                    }
                }
                case 3 -> {
                    System.out.print("ID del cliente: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nuevo correo: ");
                    String correo = scanner.nextLine();
                    clienteDAO.actualizarCliente(id, correo);
                }
                case 4 -> {
                    System.out.print("ID del cliente: ");
                    int id = scanner.nextInt();
                    clienteDAO.eliminarCliente(id);
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
📂 GESTOR DE CLIENTES
1. Agregar Cliente
2. Mostrar Clientes
3. Actualizar Cliente
4. Eliminar Cliente
5. Salir
Opción: 1
Nombre: Ana
Correo: ana@email.com
Edad: 28
✅ Cliente agregado con éxito.
```

</details>