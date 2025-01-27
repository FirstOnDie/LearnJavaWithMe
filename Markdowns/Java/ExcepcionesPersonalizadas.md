### **Excepciones personalizadas en Java**

Las excepciones personalizadas son útiles cuando queremos definir errores específicos de nuestra aplicación, proporcionando mensajes más claros y controlando mejor los fallos. Se crean extendiendo la clase base `Exception` (o `RuntimeException`, dependiendo del tipo de excepción).

---

#### **Estructura básica**
```java
public class MiExcepcionPersonalizada extends Exception {
    public MiExcepcionPersonalizada(String mensaje) {
        super(mensaje);
    }
}
```

---

#### **¿Cuándo usar `Exception` o `RuntimeException`?**
1. **`Exception`**: Excepciones verificadas (checked exceptions) → El compilador obliga a manejarlas (usando `try-catch` o `throws`).
2. **`RuntimeException`**: Excepciones no verificadas (unchecked exceptions) → No es obligatorio manejarlas.

---

### **Ejercicio práctico: Validación de usuario**
Supongamos que tenemos un sistema donde un usuario debe registrarse con un nombre de usuario. Queremos lanzar una excepción personalizada si el nombre no cumple ciertas condiciones (ej.: longitud mínima de 5 caracteres).

---

#### **Código**
```java
public class NombreUsuarioInvalidoException extends Exception {
    public NombreUsuarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}

public class RegistroUsuario {
    
    public void registrarUsuario(String nombreUsuario) throws NombreUsuarioInvalidoException {
        if (nombreUsuario == null || nombreUsuario.length() < 5) {
            throw new NombreUsuarioInvalidoException("El nombre de usuario debe tener al menos 5 caracteres.");
        }
        System.out.println("Usuario registrado con éxito: " + nombreUsuario);
    }

    public static void main(String[] args) {
        RegistroUsuario registro = new RegistroUsuario();
        try {
            registro.registrarUsuario("Ana"); // Lanza excepción
        } catch (NombreUsuarioInvalidoException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            registro.registrarUsuario("Juan123"); // Registro exitoso
        } catch (NombreUsuarioInvalidoException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

---

#### **Salida esperada**
```
Error: El nombre de usuario debe tener al menos 5 caracteres.
Usuario registrado con éxito: Juan123
```

---

### **Tarea extra (opcional)** 📝
Añade una nueva excepción personalizada llamada `EdadUsuarioInvalidaException`. Lanza esta excepción si la edad del usuario es menor a 18 años en un método de validación. Combínala con la validación del nombre y prueba ambos casos.


<details>
    <summary>Solución</summary>

```java
    class NombreUsuarioInvalidoException extends Exception {
        public NombreUsuarioInvalidoException(String mensaje) {
            super(mensaje);
        }
    }

    class EdadUsuarioInvalidaException extends Exception {
        public EdadUsuarioInvalidaException(String mensaje) {
            super(mensaje);
        }
    }

    public class RegistroUsuario {

    public void registrarUsuario(String nombreUsuario, int edad) 
            throws NombreUsuarioInvalidoException, EdadUsuarioInvalidaException {
        validarNombreUsuario(nombreUsuario);
        validarEdadUsuario(edad);
        System.out.println("Usuario registrado con éxito: " + nombreUsuario + ", Edad: " + edad);
    }

    private void validarNombreUsuario(String nombreUsuario) throws NombreUsuarioInvalidoException {
        if (nombreUsuario == null || nombreUsuario.length() < 5) {
            throw new NombreUsuarioInvalidoException("El nombre de usuario debe tener al menos 5 caracteres.");
        }
    }

    private void validarEdadUsuario(int edad) throws EdadUsuarioInvalidaException {
        if (edad < 18) {
            throw new EdadUsuarioInvalidaException("La edad debe ser mayor o igual a 18 años.");
        }
    }

    public static void main(String[] args) {
        RegistroUsuario registro = new RegistroUsuario();

        try {
            registro.registrarUsuario("Ana", 25); // Caso exitoso
        } catch (NombreUsuarioInvalidoException | EdadUsuarioInvalidaException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            registro.registrarUsuario("Ana", 15); // Edad inválida
        } catch (NombreUsuarioInvalidoException | EdadUsuarioInvalidaException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            registro.registrarUsuario("Juan", 20); // Caso exitoso
        } catch (NombreUsuarioInvalidoException | EdadUsuarioInvalidaException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            registro.registrarUsuario("Al", 17); // Nombre y edad inválidos
        } catch (NombreUsuarioInvalidoException | EdadUsuarioInvalidaException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```
</details>
