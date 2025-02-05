# **📌 Excepciones Personalizadas en Java** 🚨

📌 **Las excepciones personalizadas** permiten definir errores específicos de nuestra aplicación, proporcionando **mensajes más claros** y un **control más preciso de los fallos**. Se crean extendiendo `Exception` o `RuntimeException`, dependiendo de si queremos **excepciones verificadas** o **no verificadas**.

✅ **Beneficios de las excepciones personalizadas:**  
✔ Mejor claridad en los errores.  
✔ Evita usar excepciones genéricas (`IllegalArgumentException`, `NullPointerException`).  
✔ Facilita el mantenimiento del código.  
✔ Permite manejar errores específicos de la aplicación.

---

## **📌 1️⃣ Creación de una Excepción Personalizada** 🔧

📌 **Ejemplo de excepción personalizada básica:**
```java
public class MiExcepcionPersonalizada extends Exception {
    public MiExcepcionPersonalizada(String mensaje) {
        super(mensaje);
    }
}
```
✅ **Ventajas:**  
✔ `MiExcepcionPersonalizada` actúa como una excepción específica.  
✔ Puede usarse para capturar errores concretos en nuestra aplicación.

📌 **Ejemplo de uso en un método:**
```java
public void verificarNumero(int numero) throws MiExcepcionPersonalizada {
    if (numero < 0) {
        throw new MiExcepcionPersonalizada("El número no puede ser negativo.");
    }
}
```

---

## **📌 2️⃣ ¿Cuándo usar `Exception` o `RuntimeException`?** ⚠

| **Tipo** | **Descripción** | **Ejemplo de uso** |
|----------|---------------|--------------------|
| **`Exception` (Checked Exception)** | Se deben manejar obligatoriamente con `try-catch` o `throws`. | Validaciones de negocio (ej.: usuario inválido, saldo insuficiente). |
| **`RuntimeException` (Unchecked Exception)** | No es obligatorio manejarlas con `try-catch`. | Errores inesperados (ej.: división por cero, `NullPointerException`). |

📌 **Ejemplo de Excepción Verificada (`Checked Exception`)**
```java
public class DatosInvalidosException extends Exception {
    public DatosInvalidosException(String mensaje) {
        super(mensaje);
    }
}
```
📌 **Ejemplo de Excepción No Verificada (`Unchecked Exception`)**
```java
public class OperacionNoPermitidaException extends RuntimeException {
    public OperacionNoPermitidaException(String mensaje) {
        super(mensaje);
    }
}
```
---

## **📌 3️⃣ Ejemplo Práctico: Validación de Usuario** 👤

Supongamos que tenemos un sistema donde un usuario debe registrarse con un **nombre válido** y una **edad mayor a 18 años**. Si no cumple estas condiciones, lanzaremos **excepciones personalizadas**.

📌 **Definimos dos excepciones:**
```java
public class NombreUsuarioInvalidoException extends Exception {
    public NombreUsuarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}

public class EdadUsuarioInvalidaException extends Exception {
    public EdadUsuarioInvalidaException(String mensaje) {
        super(mensaje);
    }
}
```

📌 **Clase que valida los datos del usuario:**
```java
public class RegistroUsuario {

    public void registrarUsuario(String nombreUsuario, int edad) 
            throws NombreUsuarioInvalidoException, EdadUsuarioInvalidaException {
        validarNombreUsuario(nombreUsuario);
        validarEdadUsuario(edad);
        System.out.println("✅ Usuario registrado con éxito: " + nombreUsuario + ", Edad: " + edad);
    }

    private void validarNombreUsuario(String nombreUsuario) throws NombreUsuarioInvalidoException {
        if (nombreUsuario == null || nombreUsuario.length() < 5) {
            throw new NombreUsuarioInvalidoException("❌ El nombre de usuario debe tener al menos 5 caracteres.");
        }
    }

    private void validarEdadUsuario(int edad) throws EdadUsuarioInvalidaException {
        if (edad < 18) {
            throw new EdadUsuarioInvalidaException("❌ La edad debe ser mayor o igual a 18 años.");
        }
    }

    public static void main(String[] args) {
        RegistroUsuario registro = new RegistroUsuario();

        try {
            registro.registrarUsuario("Ana", 25); // ✅ Caso exitoso
        } catch (NombreUsuarioInvalidoException | EdadUsuarioInvalidaException e) {
            System.err.println(e.getMessage());
        }

        try {
            registro.registrarUsuario("Juan", 15); // ❌ Edad inválida
        } catch (NombreUsuarioInvalidoException | EdadUsuarioInvalidaException e) {
            System.err.println(e.getMessage());
        }

        try {
            registro.registrarUsuario("Al", 20); // ❌ Nombre inválido
        } catch (NombreUsuarioInvalidoException | EdadUsuarioInvalidaException e) {
            System.err.println(e.getMessage());
        }

        try {
            registro.registrarUsuario("Pepe", 17); // ❌ Nombre y edad inválidos
        } catch (NombreUsuarioInvalidoException | EdadUsuarioInvalidaException e) {
            System.err.println(e.getMessage());
        }
    }
}
```

📌 **Salida esperada en consola:**
```
✅ Usuario registrado con éxito: Ana, Edad: 25
❌ La edad debe ser mayor o igual a 18 años.
❌ El nombre de usuario debe tener al menos 5 caracteres.
❌ El nombre de usuario debe tener al menos 5 caracteres.
```

✅ **Ventajas de esta implementación:**  
✔ **Validaciones separadas:** Mejor organización del código.  
✔ **Mensajes de error claros:** Facilita la depuración.  
✔ **Uso de excepciones específicas:** Evita lanzar excepciones genéricas.

---

## **📌 4️⃣ Excepciones Personalizadas con Constructores Adicionales** 🛠

Podemos agregar más constructores para incluir **causas (`Throwable cause`)** y permitir **mensajes predeterminados**.

📌 **Ejemplo con diferentes constructores:**
```java
public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException() {
        super("Saldo insuficiente en la cuenta.");
    }

    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }

    public SaldoInsuficienteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
```
📌 **Ejemplo de uso:**
```java
public class CuentaBancaria {
    private double saldo;

    public CuentaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto > saldo) {
            throw new SaldoInsuficienteException("❌ No puedes retirar " + monto + ", saldo disponible: " + saldo);
        }
        saldo -= monto;
        System.out.println("✅ Retiro exitoso, nuevo saldo: " + saldo);
    }

    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(100);

        try {
            cuenta.retirar(150);
        } catch (SaldoInsuficienteException e) {
            System.err.println(e.getMessage());
        }
    }
}
```
📌 **Salida esperada:**
```
❌ No puedes retirar 150.0, saldo disponible: 100.0
```

✅ **Ventajas:**  
✔ **Constructores flexibles para distintos casos de uso.**  
✔ **Se puede rastrear el origen del error con `Throwable cause`.**

---