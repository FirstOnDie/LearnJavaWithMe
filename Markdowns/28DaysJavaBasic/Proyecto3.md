# **📌 Ejercicio Semana 3: Mini-proyecto "Gestor de Notas con Base de Datos"** 📝🔍

📌 **Objetivo:**  
✅ Implementar una aplicación en Java que administre notas en una base de datos MySQL usando **JDBC y Pool de Conexiones (HikariCP)**.  
✅ Implementar funciones **CRUD**:
- **`INSERT`** → Agregar nuevas notas.
- **`SELECT`** → Ver todas las notas.
- **`UPDATE`** → Modificar una nota existente.
- **`DELETE`** → Eliminar notas.  
  ✅ Usar un **menú interactivo** para gestionar notas.  
  ✅ **Persistencia en base de datos** con conexión optimizada.

---

<details>
    <summary>Solución</summary>

## **1️⃣ Configuración de la Base de Datos** 🛠️

📌 **Ejecutar en MySQL:**
```sql
CREATE DATABASE notas_db;
USE notas_db;

CREATE TABLE notas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    contenido TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
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

📌 **Agregar Dependencia de MySQL JDBC (Si usas Maven):**
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
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
        config.setJdbcUrl("jdbc:mysql://localhost:3306/notas_db");
        config.setUsername("root");  // Cambia según tu configuración
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
✅ **Optimiza el rendimiento y evita sobrecarga en la base de datos.**

---

## **3️⃣ Implementación del DAO para la Gestión de Notas**

📌 **Clase `NotaDAO` con funciones CRUD:**
```java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaDAO {
    // Agregar una nueva nota
    public void agregarNota(String titulo, String contenido) {
        String sql = "INSERT INTO notas (titulo, contenido) VALUES (?, ?)";

        try (Connection conexion = ConexionPool.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setString(2, contenido);
            stmt.executeUpdate();

            System.out.println("✅ Nota agregada con éxito.");
        } catch (SQLException e) {
            System.out.println("❌ Error al agregar nota: " + e.getMessage());
        }
    }

    // Obtener todas las notas
    public List<String> obtenerNotas() {
        List<String> notas = new ArrayList<>();
        String sql = "SELECT * FROM notas";

        try (Connection conexion = ConexionPool.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String contenido = rs.getString("contenido");
                String fecha = rs.getString("fecha_creacion");

                notas.add("ID: " + id + " | Título: " + titulo + " | Contenido: " + contenido + " | Fecha: " + fecha);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener notas: " + e.getMessage());
        }
        return notas;
    }

    // Actualizar una nota
    public void actualizarNota(int id, String nuevoTitulo, String nuevoContenido) {
        String sql = "UPDATE notas SET titulo = ?, contenido = ? WHERE id = ?";

        try (Connection conexion = ConexionPool.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, nuevoTitulo);
            stmt.setString(2, nuevoContenido);
            stmt.setInt(3, id);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("✅ Nota actualizada.");
            } else {
                System.out.println("❌ Nota no encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar nota: " + e.getMessage());
        }
    }

    // Eliminar una nota
    public void eliminarNota(int id) {
        String sql = "DELETE FROM notas WHERE id = ?";

        try (Connection conexion = ConexionPool.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filasEliminadas = stmt.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("✅ Nota eliminada.");
            } else {
                System.out.println("❌ Nota no encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar nota: " + e.getMessage());
        }
    }
}
```

---

## **4️⃣ Implementación del Menú Interactivo**

📌 **Clase `Main` con opciones para gestionar notas:**
```java
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NotaDAO notaDAO = new NotaDAO();

        while (true) {
            System.out.println("\n📝 GESTOR DE NOTAS");
            System.out.println("1. Agregar Nota");
            System.out.println("2. Mostrar Notas");
            System.out.println("3. Actualizar Nota");
            System.out.println("4. Eliminar Nota");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (opcion == 5) break;

            switch (opcion) {
                case 1 -> {
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Contenido: ");
                    String contenido = scanner.nextLine();
                    notaDAO.agregarNota(titulo, contenido);
                }
                case 2 -> {
                    List<String> notas = notaDAO.obtenerNotas();
                    if (notas.isEmpty()) {
                        System.out.println("📂 No hay notas registradas.");
                    } else {
                        notas.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("ID de la nota: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nuevo título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Nuevo contenido: ");
                    String contenido = scanner.nextLine();
                    notaDAO.actualizarNota(id, titulo, contenido);
                }
                case 4 -> {
                    System.out.print("ID de la nota: ");
                    int id = scanner.nextInt();
                    notaDAO.eliminarNota(id);
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
📝 GESTOR DE NOTAS
1. Agregar Nota
2. Mostrar Notas
3. Actualizar Nota
4. Eliminar Nota
5. Salir
Opción: 1
Título: Revisión de código
Contenido: Revisar los cambios en el proyecto.
✅ Nota agregada con éxito.

Opción: 2
📂 Notas Registradas:
ID: 1 | Título: Revisión de código | Contenido: Revisar los cambios en el proyecto | Fecha: 2024-02-03 14:30:21
```

---

</details>