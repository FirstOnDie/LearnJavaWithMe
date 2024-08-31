# Service Layer (Capa de Servicio)

## Explicación:

La Capa de Servicio organiza la lógica de negocio en una aplicación. Es como un intermediario que procesa las solicitudes del cliente y devuelve una respuesta después de realizar las operaciones necesarias.

## Ejemplo con código:

```java

// Servicio
public class UsuarioServicio {
    public String obtenerSaludo(String nombre) {
        return "Hola, " + nombre;
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        UsuarioServicio servicio = new UsuarioServicio();
        String saludo = servicio.obtenerSaludo("Carlos");
        System.out.println(saludo);
    }
}
```
## Explicación del ejemplo:

- Servicio (UsuarioServicio): Proporciona lógica de negocio para obtener un saludo.
- Ejecución (Main): Utiliza el servicio para obtener un saludo y mostrarlo.

## Dónde se usa:

Se utiliza para centralizar la lógica de negocio y coordinar las operaciones entre diferentes componentes de la aplicación.

**Ventajas:**

- Centralización de lógica: Facilita la gestión de la lógica de negocio en un solo lugar.
- Reutilización de código: Permite reutilizar la lógica de negocio en diferentes partes de la aplicación.

**Inconvenientes:**

- Sobrecarga: Puede ser una sobrecarga adicional si la aplicación es pequeña.
- Dependencia: Las capas de servicio pueden aumentar la dependencia entre componentes.