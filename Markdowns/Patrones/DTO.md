# DTO (Data Transfer Object)

## Explicación:

El patrón DTO se utiliza para transferir datos entre diferentes capas de una aplicación. Un DTO es un objeto simple que solo contiene atributos y métodos de acceso.

## Ejemplo con código:

```java

// DTO
public class UsuarioDTO {
    private String nombre;
    private int edad;

    public UsuarioDTO(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Ana", 10);
        System.out.println("Nombre: " + usuarioDTO.getNombre());
        System.out.println("Edad: " + usuarioDTO.getEdad());
    }
}
```

## Explicación del ejemplo:

- DTO (UsuarioDTO): Contiene datos simples (nombre y edad) para transferir entre capas sin lógica adicional.
- Ejecución (Main): Crea un UsuarioDTO y muestra los datos.

## Dónde se usa:

Se usa cuando se necesita transferir datos a través de diferentes capas o sistemas sin exponer la lógica de negocio interna.

**Ventajas:**

- Simplifica el transporte de datos: Facilita el envío y recepción de datos entre diferentes capas.
- Seguridad: Evita exponer el modelo de datos interno a otras capas o sistemas.

**Inconvenientes:**

- Más objetos: Puede aumentar el número de objetos en la aplicación.
- Código duplicado: Los DTOs pueden contener datos similares a los modelos, causando redundancia.
