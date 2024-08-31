# DAO (Data Access Object)

## Explicación:

El patrón DAO se utiliza para separar la lógica de acceso a la base de datos de la lógica de negocio. Facilita el manejo de operaciones de base de datos como CRUD (crear, leer, actualizar y eliminar).

## Ejemplo con código:

```java

import java.util.ArrayList;
import java.util.List;

// DAO Interface
public interface UsuarioDAO {
    void agregarUsuario(Usuario usuario);
    List<Usuario> obtenerUsuarios();
}

// Implementación DAO
public class UsuarioDAOImpl implements UsuarioDAO {
    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarios;
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        usuarioDAO.agregarUsuario(new Usuario("Carlos"));

        for (Usuario usuario : usuarioDAO.obtenerUsuarios()) {
            System.out.println(usuario.getNombre());
        }
    }
}
```
## Explicación del ejemplo:

- DAO Interface (UsuarioDAO): Define las operaciones de base de datos.
- Implementación DAO (UsuarioDAOImpl): Realiza las operaciones reales de la base de datos.
- Ejecución (Main): Utiliza el DAO para agregar y obtener usuarios.

## Dónde se usa:
Se utiliza en aplicaciones que necesitan interactuar con bases de datos, como sistemas de gestión, aplicaciones de inventario, etc.

**Ventajas:**

- Separación de lógica de acceso a datos: Mejora la modularidad y facilita el mantenimiento.
- Reutilización: El código DAO puede ser reutilizado en diferentes partes de la aplicación.

**Inconvenientes:**

- Complejidad: Añade otra capa que puede complicar el diseño si no es necesario.
- Más código: Puede resultar en más código, especialmente para aplicaciones pequeñas.