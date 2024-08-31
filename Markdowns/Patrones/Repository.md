# Repository (Repositorio)

## Explicación:
El patrón Repository es una capa intermedia que maneja las operaciones de almacenamiento y recuperación de objetos de una base de datos. Actúa como un almacén donde se pueden buscar y guardar objetos.

## Ejemplo con código:

```java
import java.util.HashMap;
import java.util.Map;

// Repositorio
public class UsuarioRepositorio {
    private Map<String, Usuario> usuarios = new HashMap<>();

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNombre(), usuario);
    }

    public Usuario obtenerUsuario(String nombre) {
        return usuarios.get(nombre);
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        UsuarioRepositorio repositorio = new UsuarioRepositorio();
        Usuario usuario = new Usuario("Carlos");
        repositorio.agregarUsuario(usuario);

        Usuario resultado = repositorio.obtenerUsuario("Carlos");
        System.out.println("Usuario encontrado: " + resultado.getNombre());
    }
}
```

## Explicación del ejemplo:

- Repositorio (UsuarioRepositorio): Gestiona el almacenamiento y la recuperación de usuarios.
- Ejecución (Main): Utiliza el repositorio para agregar y buscar un usuario.

## Dónde se usa:

Se utiliza en aplicaciones que necesitan acceder a la base de datos o cualquier tipo de almacenamiento persistente, facilitando la separación entre la lógica de negocio y la lógica de acceso a datos.

**Ventajas:**

- Separación de responsabilidades: Separa la lógica de negocio de la lógica de acceso a datos.
- Facilita las pruebas: Es más fácil probar la lógica de negocio sin depender de la base de datos.

**Inconvenientes:**

- Más complejidad: Añade otra capa que puede complicar el diseño.
- Aumento del número de clases: Puede resultar en un aumento del número de clases.