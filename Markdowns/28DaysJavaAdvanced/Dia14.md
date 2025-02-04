# **📌 Día 14: Arquitectura Hexagonal y Clean Architecture**

Hoy aprenderás:  
✅ **Conceptos de Arquitectura Hexagonal y Clean Architecture**  
✅ **Separación de capas y uso de Interfaces**  
✅ **Inyección de Dependencias**  
✅ **Ejercicio: Aplicación Backend Escalable**

---

📌 **¿Por qué es importante?**  
Las **arquitecturas limpias y modulares** permiten desarrollar aplicaciones escalables, flexibles y fáciles de mantener. Separar **lógica de negocio, infraestructura y presentación** mejora la reutilización y facilita las pruebas.

---

# **1️⃣ Arquitectura Hexagonal vs. Clean Architecture**

📌 **Arquitectura Hexagonal (Ports & Adapters)**  
🔹 Divide la aplicación en **núcleo de negocio** y **adaptadores externos**.  
🔹 Los datos entran y salen mediante **puertos e interfaces**.  
🔹 Facilita el reemplazo de tecnologías sin afectar la lógica central.

📌 **Clean Architecture** (Robert C. Martin)  
🔹 Organiza el código en **capas concéntricas**.  
🔹 La **capa más interna** contiene la lógica de negocio.  
🔹 La **capa externa** maneja la infraestructura (bases de datos, APIs, UI).

📌 **Comparación de Capas**

| **Capa** | **Hexagonal** | **Clean Architecture** |
|----------|--------------|------------------------|
| **Dominio** | Núcleo de negocio | Entidades y Casos de Uso |
| **Aplicación** | Servicios y puertos | Interactúa con el Dominio |
| **Infraestructura** | Adaptadores (DB, APIs) | Bases de Datos, Frameworks |

📌 **Beneficios:**  
✅ **Separación de responsabilidades**.  
✅ **Fácil testeo y mantenimiento**.  
✅ **Menos acoplamiento con tecnologías externas**.

---

# **2️⃣ Diseño de una Aplicación con Clean Architecture**

📌 **Estructura del Proyecto:**
```
📂 src/
 ├── 📂 domain/        # Lógica de Negocio
 │    ├── modelo/
 │    │    ├── Usuario.java
 │    ├── puertos/
 │    │    ├── UsuarioRepositorio.java
 ├── 📂 application/   # Casos de Uso
 │    ├── servicio/
 │    │    ├── UsuarioServicio.java
 ├── 📂 infrastructure/ # Infraestructura
 │    ├── persistencia/
 │    │    ├── UsuarioRepositorioImpl.java
 │    ├── web/
 │    │    ├── UsuarioController.java
 ├── Main.java
```

---

# **3️⃣ Implementación Paso a Paso**

---

## **1️⃣ Capa de Dominio (Reglas de Negocio)**
📌 **Modelo `Usuario.java`**
```java
package domain.modelo;

public class Usuario {
    private String id;
    private String nombre;
    private String email;

    public Usuario(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
}
```
✅ **Clase pura que representa la entidad `Usuario` sin dependencias externas.**

📌 **Puerto (`UsuarioRepositorio.java`)**
```java
package domain.puertos;

import domain.modelo.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorio {
    List<Usuario> obtenerTodos();
    Optional<Usuario> obtenerPorId(String id);
    void guardar(Usuario usuario);
}
```
✅ **Define cómo interactuamos con la capa de datos sin depender de tecnología específica.**

---

## **2️⃣ Capa de Aplicación (Casos de Uso / Servicios)**
📌 **Servicio de negocio `UsuarioServicio.java`**
```java
package application.servicio;

import domain.modelo.Usuario;
import domain.puertos.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;

public class UsuarioServicio {
    private final UsuarioRepositorio repositorio;

    public UsuarioServicio(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Usuario> listarUsuarios() {
        return repositorio.obtenerTodos();
    }

    public Optional<Usuario> buscarUsuario(String id) {
        return repositorio.obtenerPorId(id);
    }

    public void registrarUsuario(Usuario usuario) {
        repositorio.guardar(usuario);
    }
}
```
✅ **La lógica de negocio no depende de la infraestructura (como bases de datos).**

---

## **3️⃣ Capa de Infraestructura (Base de Datos y API)**
📌 **Repositorio de base de datos `UsuarioRepositorioImpl.java`**
```java
package infrastructure.persistencia;

import domain.modelo.Usuario;
import domain.puertos.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositorioImpl implements UsuarioRepositorio {
    private final List<Usuario> baseDatosSimulada = new ArrayList<>();

    @Override
    public List<Usuario> obtenerTodos() {
        return baseDatosSimulada;
    }

    @Override
    public Optional<Usuario> obtenerPorId(String id) {
        return baseDatosSimulada.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public void guardar(Usuario usuario) {
        baseDatosSimulada.add(usuario);
    }
}
```
✅ **Podemos cambiar la implementación (por ejemplo, usar MySQL o MongoDB) sin afectar el dominio.**

📌 **Controlador Web `UsuarioController.java`**
```java
package infrastructure.web;

import application.servicio.UsuarioServicio;
import domain.modelo.Usuario;
import java.util.Scanner;

public class UsuarioController {
    private final UsuarioServicio servicio;

    public UsuarioController(UsuarioServicio servicio) {
        this.servicio = servicio;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Listar Usuarios\n2. Registrar Usuario\n3. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                servicio.listarUsuarios().forEach(u -> 
                    System.out.println(u.getId() + " - " + u.getNombre() + " - " + u.getEmail()));
            } else if (opcion == 2) {
                System.out.print("ID: ");
                String id = scanner.nextLine();
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                servicio.registrarUsuario(new Usuario(id, nombre, email));
                System.out.println("Usuario registrado con éxito.");
            } else {
                break;
            }
        }
        scanner.close();
    }
}
```
✅ **Maneja la entrada de usuario sin afectar la lógica de negocio.**

---

## **4️⃣ Integración en `Main.java`**
📌 **Ejecutamos la aplicación completa**
```java
import application.servicio.UsuarioServicio;
import infrastructure.persistencia.UsuarioRepositorioImpl;
import infrastructure.web.UsuarioController;

public class Main {
    public static void main(String[] args) {
        UsuarioRepositorioImpl repositorio = new UsuarioRepositorioImpl();
        UsuarioServicio servicio = new UsuarioServicio(repositorio);
        UsuarioController controlador = new UsuarioController(servicio);

        controlador.mostrarMenu();
    }
}
```
📌 **Ejemplo de uso:**
```
1. Listar Usuarios
2. Registrar Usuario
3. Salir
> 2
ID: 1
Nombre: Juan Pérez
Email: juan@gmail.com
Usuario registrado con éxito.
```
✅ **La aplicación es modular y flexible, permitiendo cambios sin romper el código.**

---
