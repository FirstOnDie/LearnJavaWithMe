# **📌 Test-Driven Development (TDD)** 🚀

El **Desarrollo Guiado por Pruebas (TDD - Test-Driven Development)** es una técnica en la que **primero escribes las pruebas y luego el código** para hacerlas pasar.

🔹 **Escribes una prueba que falla.**  
🔹 **Escribes el código mínimo para que pase la prueba.**  
🔹 **Refactorizas el código para mejorarlo.**

💡 **Piensa en TDD como aprender a andar en bicicleta con rueditas**:  
1️⃣ **Intentas montar la bici (escribes una prueba).**  
2️⃣ **Al principio, te caes (la prueba falla).**  
3️⃣ **Ajustas las rueditas (escribes el código mínimo).**  
4️⃣ **Ahora puedes andar mejor (la prueba pasa).**  
5️⃣ **Finalmente, mejoras la bici para que sea más rápida y estable (refactorización).**

---

## **📌 Ciclo de TDD: "Rojo -> Verde -> Refactor"** 🔴🟢🔧

### **1️⃣ Escribir una prueba (Rojo 🔴)**
Primero escribimos una prueba para la funcionalidad deseada. Como no hay código, la prueba fallará.

### **2️⃣ Escribir el código mínimo (Verde 🟢)**
Luego escribimos el código mínimo para hacer que la prueba pase.

### **3️⃣ Refactorizar (Optimizar el código) 🔧**
Finalmente, mejoramos el código sin cambiar su comportamiento.

¡Repetimos este ciclo hasta completar la funcionalidad! 🔄

---

## **Ejemplo práctico: Sumar dos números con TDD**

### **1️⃣ Escribimos la prueba antes de escribir el código (Rojo 🔴)**
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    @Test
    public void testSuma() {
        Calculadora calc = new Calculadora();
        int resultado = calc.sumar(2, 3);
        assertEquals(5, resultado);
    }
}
```
🔴 **Falla porque la clase `Calculadora` aún no existe.**

---

### **2️⃣ Escribimos el código mínimo para pasar la prueba (Verde 🟢)**
```java
public class Calculadora {
    public int sumar(int a, int b) {
        return a + b;
    }
}
```
🟢 **Ahora la prueba pasa porque implementamos `sumar()`.**

---

### **3️⃣ Refactorizamos para mejorar el código (🔧)**
Nuestro código ya es simple, pero en otros casos podríamos optimizarlo.

---

## **📌 Otro Ejemplo: Comprobar si un número es par**

### **1️⃣ Escribimos la prueba (Rojo 🔴)**
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumeroTest {

    @Test
    public void testEsPar() {
        Numero numero = new Numero();
        assertTrue(numero.esPar(4));
        assertFalse(numero.esPar(3));
    }
}
```
🔴 **Falla porque `Numero` aún no existe.**

---

### **2️⃣ Escribimos el código mínimo (Verde 🟢)**
```java
public class Numero {
    public boolean esPar(int num) {
        return num % 2 == 0;
    }
}
```
🟢 **¡Ahora la prueba pasa!**

---

### **3️⃣ Refactorizamos (🔧)**
En este caso, el código ya es óptimo, así que no necesitamos hacer cambios.

---

## **📌 Beneficios de TDD**
✅ **Código más confiable**: Como se prueban todas las funcionalidades, el código tiene menos errores.  
✅ **Código más limpio**: Se escribe solo el código necesario para pasar las pruebas.  
✅ **Fácil de mantener**: Si cambias algo, las pruebas te avisan si rompiste algo.  
✅ **Mejor diseño**: TDD obliga a pensar en cómo se usará el código antes de escribirlo.
