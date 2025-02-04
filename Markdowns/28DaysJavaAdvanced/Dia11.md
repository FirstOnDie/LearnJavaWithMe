# **📌 Día 11: Patrones Creacionales en Java**
Hoy aprenderás:  
✅ **Singleton** (Garantiza una única instancia)  
✅ **Factory Method** (Crea objetos sin exponer su lógica)  
✅ **Builder** (Facilita la creación de objetos complejos)  
✅ **Ejercicio: Sistema de generación de reportes**

---

📌 **¿Por qué es importante?**  
Los **Patrones de Diseño** ayudan a escribir **código reutilizable, estructurado y mantenible**. Los **Patrones Creacionales** se centran en **cómo crear objetos** de manera eficiente.

---

# **1️⃣ Singleton: Una única instancia global**
📌 **¿Qué es?**  
El patrón **Singleton** garantiza que una clase tenga **solo una instancia** y proporciona un acceso global a ella.

📌 **Casos de uso:**  
✅ **Gestión de conexiones a BD**  
✅ **Manejo de configuración global**  
✅ **Gestión de logs**

---

## **🔹 Implementación de Singleton en Java**
```java
public class Configuracion {
    private static Configuracion instancia;
    private String urlBaseDatos;

    private Configuracion() { // Constructor privado
        this.urlBaseDatos = "jdbc:mysql://localhost:3306/empresa";
    }

    public static Configuracion getInstancia() {
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    public String getUrlBaseDatos() {
        return urlBaseDatos;
    }
}
```
📌 **Usamos `private static Configuracion instancia` para almacenar la única instancia.**

✅ **Ejemplo de uso:**
```java
public class Main {
    public static void main(String[] args) {
        Configuracion config = Configuracion.getInstancia();
        System.out.println(config.getUrlBaseDatos());
    }
}
```
📌 **Salida esperada:**
```
jdbc:mysql://localhost:3306/empresa
```
✅ **Siempre devuelve la misma instancia.**

---

# **2️⃣ Factory Method: Creación flexible de objetos**
📌 **¿Qué es?**  
El **Factory Method** proporciona una interfaz para **crear objetos sin especificar su clase exacta**.

📌 **Casos de uso:**  
✅ **Crear diferentes tipos de objetos sin modificar el código**  
✅ **Centralizar la lógica de creación**

---

## **🔹 Ejemplo: Crear diferentes tipos de vehículos**
📌 **Interfaz común para vehículos**
```java
interface Vehiculo {
    void conducir();
}
```
📌 **Implementaciones concretas**
```java
class Coche implements Vehiculo {
    public void conducir() {
        System.out.println("Conduciendo un coche");
    }
}

class Moto implements Vehiculo {
    public void conducir() {
        System.out.println("Conduciendo una moto");
    }
}
```
📌 **Factory Method para crear vehículos**
```java
class VehiculoFactory {
    public static Vehiculo crearVehiculo(String tipo) {
        if (tipo.equalsIgnoreCase("coche")) {
            return new Coche();
        } else if (tipo.equalsIgnoreCase("moto")) {
            return new Moto();
        }
        throw new IllegalArgumentException("Tipo de vehículo no soportado");
    }
}
```
📌 **Ejemplo de uso:**
```java
public class Main {
    public static void main(String[] args) {
        Vehiculo vehiculo1 = VehiculoFactory.crearVehiculo("coche");
        vehiculo1.conducir();

        Vehiculo vehiculo2 = VehiculoFactory.crearVehiculo("moto");
        vehiculo2.conducir();
    }
}
```
📌 **Salida esperada:**
```
Conduciendo un coche
Conduciendo una moto
```
✅ **Podemos agregar más tipos de vehículos sin modificar el código existente.**

---

# **3️⃣ Builder: Creación de objetos complejos paso a paso**
📌 **¿Qué es?**  
El **Builder Pattern** permite crear objetos **paso a paso**, ideal para **objetos con muchas propiedades opcionales**.

📌 **Casos de uso:**  
✅ **Evitar constructores largos**  
✅ **Facilitar la configuración de objetos**

---

## **🔹 Ejemplo: Crear un `Usuario` con diferentes opciones**
📌 **Clase `Usuario` con Builder**
```java
class Usuario {
    private String nombre;
    private int edad;
    private String email;

    private Usuario(Builder builder) {
        this.nombre = builder.nombre;
        this.edad = builder.edad;
        this.email = builder.email;
    }

    public static class Builder {
        private String nombre;
        private int edad;
        private String email;

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setEdad(int edad) {
            this.edad = edad;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }

    @Override
    public String toString() {
        return "Usuario{nombre='" + nombre + "', edad=" + edad + ", email='" + email + "'}";
    }
}
```
📌 **Ejemplo de uso:**
```java
public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario.Builder()
                .setNombre("Carlos")
                .setEdad(30)
                .setEmail("carlos@gmail.com")
                .build();

        System.out.println(usuario);
    }
}
```
📌 **Salida esperada:**
```
Usuario{nombre='Carlos', edad=30, email='carlos@gmail.com'}
```
✅ **Código más limpio y fácil de extender.**

---

# **4️⃣ Ejercicio Práctico: Sistema de Generación de Reportes**
📌 **Queremos:**  
1️⃣ Un `Reporte` que se cree usando **Builder**.  
2️⃣ Un `ReporteFactory` que genere diferentes tipos de reportes.  
3️⃣ Un `Logger` que use **Singleton** para registrar eventos.

---

<details>
    <summary>Solución</summary>

## **🔹 Código del ejercicio resuelto**
📌 **Clase `Reporte.java` con Builder**
```java
class Reporte {
    private String titulo;
    private String contenido;
    private String autor;

    private Reporte(Builder builder) {
        this.titulo = builder.titulo;
        this.contenido = builder.contenido;
        this.autor = builder.autor;
    }

    public static class Builder {
        private String titulo;
        private String contenido;
        private String autor;

        public Builder setTitulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder setContenido(String contenido) {
            this.contenido = contenido;
            return this;
        }

        public Builder setAutor(String autor) {
            this.autor = autor;
            return this;
        }

        public Reporte build() {
            return new Reporte(this);
        }
    }

    @Override
    public String toString() {
        return "Reporte{" + "titulo='" + titulo + "', contenido='" + contenido + "', autor='" + autor + "'}";
    }
}
```

📌 **Clase `ReporteFactory.java`**
```java
class ReporteFactory {
    public static Reporte crearReporteFinanciero() {
        return new Reporte.Builder()
                .setTitulo("Reporte Financiero")
                .setContenido("Contenido del reporte financiero...")
                .setAutor("Departamento Finanzas")
                .build();
    }

    public static Reporte crearReporteVentas() {
        return new Reporte.Builder()
                .setTitulo("Reporte de Ventas")
                .setContenido("Contenido del reporte de ventas...")
                .setAutor("Departamento Ventas")
                .build();
    }
}
```

📌 **Ejemplo de uso:**
```java
public class Main {
    public static void main(String[] args) {
        Reporte reporte = ReporteFactory.crearReporteFinanciero();
        System.out.println(reporte);
    }
}
```
📌 **Salida esperada:**
```
Reporte{titulo='Reporte Financiero', contenido='Contenido del reporte financiero...', autor='Departamento Finanzas'}
```
✅ **Generamos reportes flexibles y bien estructurados.**

---

</details>