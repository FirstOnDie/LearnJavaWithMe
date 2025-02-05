# **📌 Día 8: Encapsulación y Modificadores de Acceso en Java** 🚀

📌 **Objetivo del día:**  
✅ Aprender sobre los **modificadores de acceso (`private`, `protected`, `public`, `default`)**.  
✅ Aplicar **encapsulación** con **getters y setters**.  
✅ **Ejercicio:** Implementar una **clase `CuentaBancaria`** con saldo privado y métodos públicos.

---

## **1️⃣ ¿Qué es la Encapsulación?**

📌 **La encapsulación es el principio de ocultar los datos de una clase y exponer solo lo necesario.**  
✔ Protege los atributos de modificaciones indebidas.  
✔ Solo se puede acceder a los datos a través de **métodos específicos (`getters` y `setters`)**.

💡 **Ejemplo sin encapsulación (Mala práctica)**
```java
public class Persona {
    String nombre; // Atributo público (cualquiera puede modificarlo)
}

public class Main {
    public static void main(String[] args) {
        Persona persona = new Persona();
        persona.nombre = "Juan"; // Se accede y modifica directamente (no recomendado)
        System.out.println(persona.nombre);
    }
}
```
✅ **Salida esperada:**
```
Juan
```
⚠ **Problema:** No hay control sobre cómo se modifica `nombre`.

---

## **2️⃣ Modificadores de Acceso en Java**

📌 **Los modificadores de acceso controlan la visibilidad de los atributos y métodos.**

| Modificador | Accesible en la misma clase | Accesible en el mismo paquete | Accesible en clases hijas | Accesible en cualquier clase |
|------------|----------------|------------------|----------------|------------------|
| `private` | ✅ Sí | ❌ No | ❌ No | ❌ No |
| `default` (sin palabra clave) | ✅ Sí | ✅ Sí | ❌ No | ❌ No |
| `protected` | ✅ Sí | ✅ Sí | ✅ Sí | ❌ No |
| `public` | ✅ Sí | ✅ Sí | ✅ Sí | ✅ Sí |

📌 **Ejemplo de uso de los modificadores:**
```java
public class Persona {
    private String nombre; // Solo accesible dentro de esta clase
    protected int edad;    // Accesible dentro del mismo paquete y en clases hijas
    public String ciudad;  // Accesible desde cualquier parte
}
```

📌 **Ejemplo de acceso desde otra clase:**
```java
public class Main {
    public static void main(String[] args) {
        Persona persona = new Persona();
        persona.ciudad = "Madrid"; // ✅ Correcto (es público)
        // persona.nombre = "Juan"; // ❌ Error: nombre es privado
    }
}
```

✅ **Conclusión:**  
✔ Usa **`private`** para proteger atributos.  
✔ Usa **`public`** solo cuando sea necesario.  
✔ Usa **`protected`** para permitir acceso a clases hijas.

---

## **3️⃣ Getters y Setters: Accediendo a Datos Encapsulados**

📌 **Los `getters` y `setters` permiten acceder y modificar atributos privados de manera controlada.**

📌 **Ejemplo de uso de `get` y `set` en Java:**
```java
public class Persona {
    private String nombre;

    // Getter (obtener el valor del atributo)
    public String getNombre() {
        return nombre;
    }

    // Setter (modificar el valor del atributo)
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
```

📌 **Uso de `getNombre()` y `setNombre()` en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        Persona persona = new Persona();
        persona.setNombre("Ana"); // Se usa el setter
        System.out.println(persona.getNombre()); // Se usa el getter
    }
}
```
✅ **Salida esperada:**
```
Ana
```
✔ **Ventaja:** Controlamos qué valores se asignan y se obtienen.

📌 **Validación en un `setter` (ejemplo de control de datos):**
```java
public void setEdad(int edad) {
    if (edad >= 0) {
        this.edad = edad;
    } else {
        System.out.println("❌ La edad no puede ser negativa.");
    }
}
```

---

# **📌 Ejercicio del Día 8: Clase `CuentaBancaria`** 🎯

📌 **Objetivo:**  
✔ Crear una **clase `CuentaBancaria`** con atributos encapsulados:
- `numeroCuenta` (`String`)
- `saldo` (`double`)

✔ Implementar métodos:
- `depositar(double cantidad)` → Aumenta el saldo.
- `retirar(double cantidad)` → Disminuye el saldo (verifica saldo suficiente).
- `getSaldo()` → Devuelve el saldo actual.

📌 **Ejemplo de salida esperada:**
```
Cuenta creada con saldo: 1000.0€
Depósito de 500.0€. Nuevo saldo: 1500.0€
Retiro de 2000.0€. ❌ Saldo insuficiente.
Retiro de 300.0€. Nuevo saldo: 1200.0€
```

---

<details>
    <summary>Solución</summary>

### **✅ Solución en Java:**
```java
public class CuentaBancaria {
    private String numeroCuenta;
    private double saldo;

    // Constructor
    public CuentaBancaria(String numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    // Método para depositar dinero
    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.println("Depósito de " + cantidad + "€. Nuevo saldo: " + saldo + "€");
        } else {
            System.out.println("❌ No se puede depositar una cantidad negativa.");
        }
    }

    // Método para retirar dinero
    public void retirar(double cantidad) {
        if (cantidad > saldo) {
            System.out.println("❌ Saldo insuficiente.");
        } else if (cantidad > 0) {
            saldo -= cantidad;
            System.out.println("Retiro de " + cantidad + "€. Nuevo saldo: " + saldo + "€");
        } else {
            System.out.println("❌ No se puede retirar una cantidad negativa.");
        }
    }

    // Método getter para obtener el saldo
    public double getSaldo() {
        return saldo;
    }

    // Método getter para obtener el número de cuenta
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
}
```

📌 **Uso de la clase `CuentaBancaria` en `main()`:**
```java
public class Main {
    public static void main(String[] args) {
        // Crear una cuenta bancaria con saldo inicial
        CuentaBancaria cuenta = new CuentaBancaria("12345678", 1000.0);
        
        System.out.println("Cuenta creada con saldo: " + cuenta.getSaldo() + "€");
        
        // Realizar transacciones
        cuenta.depositar(500.0);
        cuenta.retirar(2000.0); // ❌ Saldo insuficiente
        cuenta.retirar(300.0);  // ✅ Retiro exitoso
    }
}
```

✅ **Salida esperada:**
```
Cuenta creada con saldo: 1000.0€
Depósito de 500.0€. Nuevo saldo: 1500.0€
Retiro de 2000.0€. ❌ Saldo insuficiente.
Retiro de 300.0€. Nuevo saldo: 1200.0€
```

---

</details>