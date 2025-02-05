# **📌 Mini-Proyecto Semana 2: "Sistema de Gestión de Productos"** 🛒

📌 **Objetivo del Mini-Proyecto:**  
✅ Implementar un sistema para **gestionar productos** en un inventario.  
✅ Utilizar **colecciones (`List`, `HashMap`)** para almacenar productos.  
✅ Aplicar **POO** con clases y métodos bien estructurados.  
✅ Implementar **búsqueda, actualización y eliminación de productos**.  
✅ **Guardar y cargar productos desde un archivo (`File`, `BufferedReader`, `PrintWriter`)**.

---

# **📌 1️⃣ Requisitos del Sistema**

📌 **Clase `Producto`:**  
✔ Atributos: `id`, `nombre`, `precio`, `cantidad`.  
✔ Métodos: `mostrarInfo()`.

📌 **Clase `Inventario`:**  
✔ **Agregar producto.**  
✔ **Buscar producto por ID.**  
✔ **Actualizar cantidad/precio.**  
✔ **Eliminar producto.**  
✔ **Mostrar lista de productos.**  
✔ **Guardar y cargar productos desde un archivo (`productos.txt`).**

📌 **Menú interactivo en `Main` para gestionar el inventario.**

📌 **Ejemplo de salida esperada:**
```
📦 Sistema de Gestión de Productos  
1. Agregar producto  
2. Buscar producto  
3. Actualizar producto  
4. Eliminar producto  
5. Mostrar inventario  
6. Guardar y salir  
Opción: 1  
Ingrese nombre: Laptop  
Ingrese precio: 750.0  
Ingrese cantidad: 10  
✅ Producto agregado con ID: 1  

Opción: 5  
📜 Inventario Actual:  
ID: 1 | Nombre: Laptop | Precio: 750.0€ | Cantidad: 10  
```

---

<details>
    <summary>Solución</summary>

# **📌 2️⃣ Implementación en Java**

📌 **Clase `Producto`:**
```java
import java.io.Serializable;

public class Producto implements Serializable {
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

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void mostrarInfo() {
        System.out.println("ID: " + id + " | Nombre: " + nombre + " | Precio: " + precio + "€ | Cantidad: " + cantidad);
    }
}
```

---

📌 **Clase `Inventario`:**
```java
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> productos;
    private static final String ARCHIVO = "productos.txt";

    public Inventario() {
        productos = new ArrayList<>();
        cargarDesdeArchivo();
    }

    // Agregar producto
    public void agregarProducto(String nombre, double precio, int cantidad) {
        int id = productos.size() + 1;
        Producto nuevoProducto = new Producto(id, nombre, precio, cantidad);
        productos.add(nuevoProducto);
        System.out.println("✅ Producto agregado con ID: " + id);
    }

    // Buscar producto por ID
    public Producto buscarProducto(int id) {
        return productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    // Actualizar producto
    public void actualizarProducto(int id, double nuevoPrecio, int nuevaCantidad) {
        Producto producto = buscarProducto(id);
        if (producto != null) {
            producto.setPrecio(nuevoPrecio);
            producto.setCantidad(nuevaCantidad);
            System.out.println("✅ Producto actualizado.");
        } else {
            System.out.println("❌ Producto no encontrado.");
        }
    }

    // Eliminar producto
    public void eliminarProducto(int id) {
        Producto producto = buscarProducto(id);
        if (producto != null) {
            productos.remove(producto);
            System.out.println("✅ Producto eliminado.");
        } else {
            System.out.println("❌ Producto no encontrado.");
        }
    }

    // Mostrar inventario
    public void mostrarInventario() {
        if (productos.isEmpty()) {
            System.out.println("📦 No hay productos en el inventario.");
        } else {
            System.out.println("📜 Inventario Actual:");
            productos.forEach(Producto::mostrarInfo);
        }
    }

    // Guardar productos en un archivo
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Producto p : productos) {
                writer.write(p.getId() + "," + p.getNombre() + "," + p.getPrecio() + "," + p.getCantidad());
                writer.newLine();
            }
            System.out.println("💾 Inventario guardado correctamente.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el inventario.");
        }
    }

    // Cargar productos desde un archivo
    public void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double precio = Double.parseDouble(datos[2]);
                int cantidad = Integer.parseInt(datos[3]);
                productos.add(new Producto(id, nombre, precio, cantidad));
            }
            System.out.println("📂 Inventario cargado correctamente.");
        } catch (IOException e) {
            System.out.println("❌ Error al cargar el inventario.");
        }
    }
}
```

---

📌 **Clase `Main` con Menú Interactivo:**
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();

        while (true) {
            System.out.println("\n📦 Sistema de Gestión de Productos");
            System.out.println("1. Agregar producto");
            System.out.println("2. Buscar producto");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Mostrar inventario");
            System.out.println("6. Guardar y salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (opcion == 6) {
                inventario.guardarEnArchivo();
                System.out.println("👋 Saliendo del sistema...");
                break;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese precio: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Ingrese cantidad: ");
                    int cantidad = scanner.nextInt();
                    inventario.agregarProducto(nombre, precio, cantidad);
                }
                case 2 -> {
                    System.out.print("Ingrese ID del producto: ");
                    int id = scanner.nextInt();
                    Producto producto = inventario.buscarProducto(id);
                    if (producto != null) producto.mostrarInfo();
                    else System.out.println("❌ Producto no encontrado.");
                }
                case 3 -> {
                    System.out.print("Ingrese ID del producto: ");
                    int id = scanner.nextInt();
                    System.out.print("Ingrese nuevo precio: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Ingrese nueva cantidad: ");
                    int cantidad = scanner.nextInt();
                    inventario.actualizarProducto(id, precio, cantidad);
                }
                case 4 -> {
                    System.out.print("Ingrese ID del producto: ");
                    int id = scanner.nextInt();
                    inventario.eliminarProducto(id);
                }
                case 5 -> inventario.mostrarInventario();
                default -> System.out.println("❌ Opción no válida.");
            }
        }
        scanner.close();
    }
}
```

---

</details>