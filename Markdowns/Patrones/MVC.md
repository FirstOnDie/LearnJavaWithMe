# MVC (Model-View-Controller)

## Explicación:
El patrón MVC divide una aplicación en tres componentes interconectados:

- Modelo (Model): Gestiona los datos y la lógica del negocio.
- Vista (View): Muestra la información al usuario.
- Controlador (Controller): Maneja la entrada del usuario, actualiza el modelo y la vista.

## Ejemplo con código:

```java
// Modelo
public class Usuario {
private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

// Vista
public class VistaUsuario {
    public void mostrarUsuario(String nombre) {
        System.out.println("Nombre del Usuario: " + nombre);
    }
}

// Controlador
public class ControladorUsuario {
    private Usuario modelo;
    private VistaUsuario vista;

    public ControladorUsuario(Usuario modelo, VistaUsuario vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void actualizarVista() {
        vista.mostrarUsuario(modelo.getNombre());
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Carlos");
        VistaUsuario vista = new VistaUsuario();
        ControladorUsuario controlador = new ControladorUsuario(usuario, vista);

        controlador.actualizarVista();
    }
}
```

## Explicación del ejemplo:

- Modelo (Usuario): Almacena los datos del usuario.
- Vista (VistaUsuario): Muestra el nombre del usuario.
- Controlador (ControladorUsuario): Obtiene el nombre del modelo y lo muestra a través de la vista.

## Dónde se usa:

Se utiliza comúnmente en aplicaciones web y de escritorio para separar la lógica de negocio de la interfaz de usuario.

### Ventajas:

- Separación de preocupaciones: Facilita la gestión y el mantenimiento del código.
- Modularidad: Permite modificar una parte sin afectar a las otras.

### Inconvenientes:

- Complejidad: Puede ser más complejo de implementar para aplicaciones pequeñas.
- Más archivos: Requiere más clases, lo que puede aumentar la cantidad de archivos en un proyecto.
