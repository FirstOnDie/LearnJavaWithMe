# **📌 DÍA 6 y 7: PROYECTO PRÁCTICO DE JAVA PURO**

📌 **Objetivo del Proyecto:**  
Vamos a construir un **Sistema de Gestión de Empleados** en **Java puro** que incluye:  
✅ **POO Avanzada** (Encapsulación, Herencia, Polimorfismo)  
✅ **Uso de Streams API y Colecciones**  
✅ **Lectura y escritura en archivos (JSON y TXT)**  
✅ **Manejo de excepciones y logs**  
✅ **Pruebas unitarias con JUnit y Mockito**

**¡Todo en un solo proyecto!** 🔥

---
<details>
    <summary>Solución</summary>

## **🛠️ 1️⃣ Estructura del Proyecto**
📌 **Creamos la siguiente estructura en `src/`:**

```
📂 src/
 ├── 📂 model/        # Clases de negocio
 │    ├── Empleado.java
 │    ├── Departamento.java
 ├── 📂 repository/   # Acceso a datos
 │    ├── EmpleadoRepository.java
 ├── 📂 service/      # Lógica de negocio
 │    ├── EmpleadoService.java
 ├── 📂 utils/        # Utilidades
 │    ├── JsonUtils.java
 │    ├── Logger.java
 ├── 📂 tests/        # Pruebas unitarias
 │    ├── EmpleadoServiceTest.java
 ├── Main.java        # Punto de entrada
```

---

## **1️⃣ MODELO: Clases `Empleado` y `Departamento`**
📌 **Creamos `Empleado.java` en `model/`**

```java
package model;

import java.io.Serializable;

public class Empleado implements Serializable {
    private int id;
    private String nombre;
    private double salario;
    private Departamento departamento;

    public Empleado(int id, String nombre, double salario, Departamento departamento) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
        this.departamento = departamento;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getSalario() { return salario; }
    public Departamento getDepartamento() { return departamento; }

    @Override
    public String toString() {
        return id + " - " + nombre + " - " + salario + " - " + departamento;
    }
}
```
📌 **Creamos `Departamento.java` en `model/`**
```java
package model;

public enum Departamento {
    IT, VENTAS, RRHH, FINANZAS
}
```
✅ **Usamos `enum` para representar departamentos.**

---

## **2️⃣ REPOSITORIO: Gestión de Empleados con JSON**
📌 **Creamos `EmpleadoRepository.java` en `repository/`**

```java
package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Empleado;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepository {
    private static final String FILE_PATH = "empleados.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Empleado> obtenerEmpleados() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();
            return objectMapper.readValue(file, new TypeReference<List<Empleado>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void guardarEmpleados(List<Empleado> empleados) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), empleados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
✅ **Leemos y guardamos empleados en `empleados.json` con Jackson.**

---

## **3️⃣ SERVICIO: Lógica de negocio en `EmpleadoService.java`**
📌 **Creamos `EmpleadoService.java` en `service/`**

```java
package service;

import model.Empleado;
import repository.EmpleadoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmpleadoService {
    private EmpleadoRepository repositorio = new EmpleadoRepository();

    public List<Empleado> obtenerTodos() {
        return repositorio.obtenerEmpleados();
    }

    public Optional<Empleado> buscarPorId(int id) {
        return repositorio.obtenerEmpleados().stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public void agregarEmpleado(Empleado empleado) {
        List<Empleado> empleados = repositorio.obtenerEmpleados();
        empleados.add(empleado);
        repositorio.guardarEmpleados(empleados);
    }

    public void eliminarEmpleado(int id) {
        List<Empleado> empleados = repositorio.obtenerEmpleados()
                .stream()
                .filter(e -> e.getId() != id)
                .collect(Collectors.toList());
        repositorio.guardarEmpleados(empleados);
    }
}
```
✅ **Lógica de negocio: agregar, eliminar, buscar empleados.**

---

## **4️⃣ UTILIDADES: Logs y JSON**
📌 **Creamos `Logger.java` en `utils/`**

```java
package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private static final String LOG_FILE = "logs.txt";

    public static void escribirLog(String mensaje) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(LocalDateTime.now() + " - " + mensaje + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
✅ **Cada acción se guarda en `logs.txt`.**

---

## **5️⃣ PUNTO DE ENTRADA: `Main.java`**
📌 **Creamos `Main.java` para interactuar con el usuario**

```java
import model.Empleado;
import model.Departamento;
import service.EmpleadoService;
import utils.Logger;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmpleadoService servicio = new EmpleadoService();

        while (true) {
            System.out.println("\n1. Ver empleados\n2. Agregar empleado\n3. Eliminar empleado\n4. Salir");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                List<Empleado> empleados = servicio.obtenerTodos();
                empleados.forEach(System.out::println);
            } else if (opcion == 2) {
                System.out.print("ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Salario: ");
                double salario = scanner.nextDouble();
                System.out.print("Departamento (IT, VENTAS, RRHH, FINANZAS): ");
                Departamento depto = Departamento.valueOf(scanner.next().toUpperCase());

                servicio.agregarEmpleado(new Empleado(id, nombre, salario, depto));
                Logger.escribirLog("Empleado agregado: " + nombre);
            } else if (opcion == 3) {
                System.out.print("ID a eliminar: ");
                int id = scanner.nextInt();
                servicio.eliminarEmpleado(id);
                Logger.escribirLog("Empleado eliminado: " + id);
            } else {
                break;
            }
        }
        scanner.close();
    }
}
```
✅ **Menú interactivo para gestionar empleados.**

---

## **6️⃣ PRUEBAS UNITARIAS CON JUnit Y Mockito**
📌 **Creamos `EmpleadoServiceTest.java` en `tests/`**

```java
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.EmpleadoService;
import repository.EmpleadoRepository;
import model.Empleado;
import model.Departamento;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmpleadoServiceTest {

    @Test
    void testObtenerTodos() {
        EmpleadoRepository repoMock = mock(EmpleadoRepository.class);
        when(repoMock.obtenerEmpleados()).thenReturn(Arrays.asList(new Empleado(1, "Ana", 3000, Departamento.IT)));

        EmpleadoService servicio = new EmpleadoService();
        List<Empleado> empleados = servicio.obtenerTodos();

        assertEquals(1, empleados.size());
    }
}
```
✅ **Prueba automatizada para `EmpleadoService`.**

---

</details>