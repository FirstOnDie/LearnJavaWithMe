# 📌 **Hibernate: ORM en Java**

## **¿Qué es Hibernate?**
Hibernate es un **framework de ORM (Object-Relational Mapping)** que permite mapear objetos de Java con tablas en bases de datos relacionales.

✅ **Ventajas de Hibernate:**
- No necesitas escribir SQL manualmente (usa **HQL** y Criteria API).
- Maneja automáticamente las transacciones.
- Permite realizar consultas y operaciones CRUD fácilmente.
- Funciona con varias bases de datos sin cambiar código.
- Implementa caching para mejorar rendimiento.

📌 **Ejemplo:** En lugar de escribir esto en SQL:
```sql
SELECT * FROM empleados WHERE edad > 30;
```
Con Hibernate, simplemente haces:
```java
session.createQuery("FROM Empleado WHERE edad > 30").list();
```
🔥 **¡Mucho más limpio!**

---

# **1️⃣ Configuración de Hibernate**

## **📌 Paso 1: Agregar dependencias en `pom.xml` (para Maven)**
Si usas **Maven**, agrega Hibernate y una base de datos como **H2 o MySQL**:
```xml
<dependencies>
    <!-- Hibernate Core -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.3.1.Final</version>
    </dependency>

    <!-- Driver para MySQL -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>

    <!-- H2 Database (opcional para pruebas) -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>2.1.214</version>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```
✅ **Esto instalará Hibernate y el driver para conectarnos a MySQL o H2.**

---

## **📌 Paso 2: Configurar `hibernate.cfg.xml`**
📌 **Creamos el archivo de configuración en `src/main/resources`**:
```xml
<hibernate-configuration>
    <session-factory>
        <!-- Conexión a la base de datos -->
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/mi_base</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">admin</property>

        <!-- Dialecto de Hibernate para MySQL -->
        <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>

        <!-- Mostrar las consultas SQL en la consola -->
        <property name="hibernate.show_sql">true</property>

        <!-- Crear o actualizar las tablas automáticamente -->
        <property name="hibernate.hbm2ddl.auto">update</property>
    </session-factory>
</hibernate-configuration>
```
📌 **Explicación:**  
✔️ Configuramos la conexión a MySQL (cambia usuario/contraseña según tu setup).  
✔️ `hibernate.dialect` optimiza las consultas según la base de datos.  
✔️ `hibernate.show_sql=true` nos permite ver las consultas generadas.  
✔️ `hibernate.hbm2ddl.auto=update` crea/modifica las tablas automáticamente.

---

# **2️⃣ Creando una entidad en Hibernate**
📌 **Creamos una clase `Empleado.java` y la anotamos con `@Entity`**
```java
import jakarta.persistence.*;

@Entity // Indica que esta clase es una tabla en la BD
@Table(name = "empleados") // Especificamos el nombre de la tabla
public class Empleado {

    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "edad")
    private int edad;

    // Constructor vacío necesario para Hibernate
    public Empleado() {}

    public Empleado(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
}
```
📌 **Explicación de las anotaciones:**  
✔️ `@Entity`: Marca la clase como una entidad de la base de datos.  
✔️ `@Table(name="empleados")`: Asigna un nombre específico a la tabla.  
✔️ `@Id`: Define el campo como clave primaria.  
✔️ `@GeneratedValue`: Indica que `id` se generará automáticamente.  
✔️ `@Column`: Define las columnas y sus restricciones.

---

# **3️⃣ Insertar datos con Hibernate**
Ahora, creamos una clase `Main.java` para probar nuestra conexión.

```java
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Crear la sesión de Hibernate
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Empleado.class)
                                                    .buildSessionFactory();

        // Abrir sesión
        Session session = factory.getCurrentSession();

        try {
            // Crear un nuevo empleado
            Empleado nuevoEmpleado = new Empleado("Carlos", 28);

            // Iniciar transacción
            session.beginTransaction();

            // Guardar el empleado en la BD
            session.save(nuevoEmpleado);

            // Confirmar transacción
            session.getTransaction().commit();

            System.out.println("¡Empleado guardado con éxito!");
        } finally {
            factory.close();
        }
    }
}
```
📌 **Explicación:**
1. **Creamos una `SessionFactory`** para manejar conexiones.
2. **Abrimos una `Session`** para interactuar con la base de datos.
3. **Creamos un objeto `Empleado` y lo guardamos con `session.save()`.**
4. **Usamos transacciones (`beginTransaction()`, `commit()`) para asegurar que los cambios sean persistidos.**

### **Salida esperada en consola:**
```
Hibernate: insert into empleados (edad, nombre) values (?, ?)
¡Empleado guardado con éxito!
```

📌 **Consulta en MySQL**
```sql
SELECT * FROM empleados;
```
| id | nombre | edad |
|----|--------|------|
| 1  | Carlos | 28   |

---

# **✅ Próximos pasos**
1. **Consulta de empleados con Hibernate:**
```java
List<Empleado> empleados = session.createQuery("FROM Empleado").list();
```
2. **Eliminar empleados con Hibernate:**
```java
session.delete(empleado);
```
3. **Actualizar datos con `session.update()`**.

### **📚 Ejercicio:**

Vamos a crear un CRUD en Hibernate

<details>
    <summary>Solución</summary>

# 📌 **Proyecto Completo: CRUD con Hibernate**
Vamos a construir un pequeño sistema para gestionar empleados en una base de datos MySQL.

✔ **Tecnologías utilizadas:**
- Java 17+
- Hibernate 6.3.1
- MySQL
- Maven

✔ **Operaciones implementadas:**  
✅ **Crear un empleado**  
✅ **Listar empleados**  
✅ **Actualizar un empleado**  
✅ **Eliminar un empleado**

---

## **1️⃣ Configurar dependencias en `pom.xml`**
Si usas **Maven**, agrega estas dependencias para Hibernate y MySQL:
```xml
<dependencies>
    <!-- Hibernate Core -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.3.1.Final</version>
    </dependency>

    <!-- Driver para MySQL -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>

    <!-- Logging (Opcional, útil para depuración) -->
    <dependency>
        <groupId>org.jboss.logging</groupId>
        <artifactId>jboss-logging</artifactId>
        <version>3.5.3.Final</version>
    </dependency>
</dependencies>
```
📌 **Esto instala Hibernate y el driver de MySQL.**

---

## **2️⃣ Configurar `hibernate.cfg.xml`**
📌 **Creamos el archivo de configuración en `src/main/resources`**
```xml
<hibernate-configuration>
    <session-factory>
        <!-- Configuración de conexión a MySQL -->
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/empresa</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">admin</property>

        <!-- Dialecto de Hibernate para MySQL -->
        <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>

        <!-- Mostrar consultas SQL en consola -->
        <property name="hibernate.show_sql">true</property>

        <!-- Crear o actualizar las tablas automáticamente -->
        <property name="hibernate.hbm2ddl.auto">update</property>
    </session-factory>
</hibernate-configuration>
```
📌 **Explicación:**  
✔ `hibernate.connection.url` → Define la base de datos `empresa`.  
✔ `hibernate.show_sql=true` → Muestra las consultas SQL en consola.  
✔ `hibernate.hbm2ddl.auto=update` → Crea o actualiza las tablas automáticamente.

---

## **3️⃣ Crear la entidad `Empleado.java`**
📌 **Clase que representa la tabla `empleados` en la BD**
```java
import jakarta.persistence.*;

@Entity
@Table(name = "empleados") // Nombre de la tabla
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "edad")
    private int edad;

    // Constructor vacío requerido por Hibernate
    public Empleado() {}

    public Empleado(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    @Override
    public String toString() {
        return "Empleado{id=" + id + ", nombre='" + nombre + "', edad=" + edad + '}';
    }
}
```
📌 **Explicación:**  
✔ `@Entity` → Indica que esta clase es una entidad en la BD.  
✔ `@Table(name = "empleados")` → Define el nombre de la tabla.  
✔ `@Id` + `@GeneratedValue(strategy = GenerationType.IDENTITY)` → Auto-incrementa la clave primaria.  
✔ `@Column(name = "nombre", nullable = false, length = 50)` → Define una columna en la BD.

---

## **4️⃣ Implementar `EmpleadoDAO.java`**
📌 **Clase para manejar las operaciones CRUD**
```java
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class EmpleadoDAO {

    private static SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Empleado.class)
            .buildSessionFactory();

    // Insertar un empleado en la base de datos
    public static void guardarEmpleado(Empleado empleado) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(empleado);
            session.getTransaction().commit();
            System.out.println("Empleado guardado con éxito: " + empleado);
        } finally {
            session.close();
        }
    }

    // Leer todos los empleados
    public static List<Empleado> obtenerEmpleados() {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            List<Empleado> empleados = session.createQuery("FROM Empleado", Empleado.class).list();
            session.getTransaction().commit();
            return empleados;
        } finally {
            session.close();
        }
    }

    // Actualizar un empleado
    public static void actualizarEmpleado(Long id, String nuevoNombre, int nuevaEdad) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Empleado empleado = session.get(Empleado.class, id);
            if (empleado != null) {
                empleado.setNombre(nuevoNombre);
                empleado.setEdad(nuevaEdad);
                session.update(empleado);
                System.out.println("Empleado actualizado: " + empleado);
            }
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    // Eliminar un empleado
    public static void eliminarEmpleado(Long id) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Empleado empleado = session.get(Empleado.class, id);
            if (empleado != null) {
                session.delete(empleado);
                System.out.println("Empleado eliminado: " + empleado);
            }
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    // Cerrar la fábrica de sesiones
    public static void cerrar() {
        factory.close();
    }
}
```
📌 **Explicación:**  
✔ `guardarEmpleado()` → Inserta un empleado en la BD.  
✔ `obtenerEmpleados()` → Recupera todos los empleados.  
✔ `actualizarEmpleado()` → Modifica un empleado existente.  
✔ `eliminarEmpleado()` → Borra un empleado por ID.

---

## **5️⃣ Ejecutar la aplicación**
📌 **Clase `Main.java` para probar nuestro CRUD**
```java
public class Main {
    public static void main(String[] args) {
        // Insertar empleados
        EmpleadoDAO.guardarEmpleado(new Empleado("Carlos", 28));
        EmpleadoDAO.guardarEmpleado(new Empleado("Ana", 34));

        // Leer empleados
        System.out.println("Lista de empleados:");
        EmpleadoDAO.obtenerEmpleados().forEach(System.out::println);

        // Actualizar empleado
        EmpleadoDAO.actualizarEmpleado(1L, "Carlos Gómez", 30);

        // Eliminar un empleado
        EmpleadoDAO.eliminarEmpleado(2L);

        // Cerrar sesión
        EmpleadoDAO.cerrar();
    }
}
```
📌 **Salida esperada en consola:**
```
Empleado guardado con éxito: Empleado{id=1, nombre='Carlos', edad=28}
Empleado guardado con éxito: Empleado{id=2, nombre='Ana', edad=34}
Lista de empleados:
Empleado{id=1, nombre='Carlos', edad=28}
Empleado{id=2, nombre='Ana', edad=34}
Empleado actualizado: Empleado{id=1, nombre='Carlos Gómez', edad=30}
Empleado eliminado: Empleado{id=2, nombre='Ana', edad=34}
```


</details>
