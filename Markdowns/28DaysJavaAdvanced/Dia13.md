# **📌 Día 13: Patrones de Comportamiento en Java**
Hoy aprenderás:  
✅ **Observer** (Suscripción a eventos)  
✅ **Strategy** (Seleccionar comportamientos en tiempo de ejecución)  
✅ **Chain of Responsibility** (Encadenar procesamiento de solicitudes)  
✅ **Ejercicio: Sistema de Notificaciones**

---

📌 **¿Por qué es importante?**  
Los **Patrones de Comportamiento** se enfocan en cómo los objetos interactúan y se comunican entre sí, permitiendo **código más flexible y extensible**.

---

# **1️⃣ Observer: Suscripción a Eventos**
📌 **¿Qué es?**  
El **patrón Observer** permite que un objeto (**Sujeto**) notifique automáticamente a múltiples **Observadores** cuando cambia su estado.

📌 **Casos de uso:**  
✅ **Notificaciones en tiempo real (Eventos, WebSockets, UI)**  
✅ **Publicación-suscripción (Pub/Sub)**

---

## **🔹 Ejemplo: Sistema de Notificaciones**
📌 **Interfaz del observador:**
```java
interface Observador {
    void actualizar(String mensaje);
}
```
📌 **Implementaciones de observadores:**
```java
class Usuario implements Observador {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println(nombre + " recibió notificación: " + mensaje);
    }
}
```
📌 **Clase Sujeto (Administrador de Notificaciones):**
```java
import java.util.ArrayList;
import java.util.List;

class Notificador {
    private List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void enviarNotificacion(String mensaje) {
        for (Observador obs : observadores) {
            obs.actualizar(mensaje);
        }
    }
}
```
📌 **Ejemplo de uso:**
```java
public class Main {
    public static void main(String[] args) {
        Notificador notificador = new Notificador();
        
        Observador usuario1 = new Usuario("Carlos");
        Observador usuario2 = new Usuario("Ana");

        notificador.agregarObservador(usuario1);
        notificador.agregarObservador(usuario2);

        notificador.enviarNotificacion("¡Nueva actualización disponible!");
    }
}
```
📌 **Salida esperada:**
```
Carlos recibió notificación: ¡Nueva actualización disponible!
Ana recibió notificación: ¡Nueva actualización disponible!
```
✅ **Múltiples usuarios reciben notificaciones automáticamente.**

---

# **2️⃣ Strategy: Selección Dinámica de Comportamientos**
📌 **¿Qué es?**  
El **patrón Strategy** permite definir múltiples estrategias y seleccionar **cuál usar en tiempo de ejecución**.

📌 **Casos de uso:**  
✅ **Algoritmos intercambiables (ordenación, compresión, pago)**  
✅ **Permite cambiar el comportamiento sin modificar el código existente**

---

## **🔹 Ejemplo: Estrategia de Envío de Mensajes**
📌 **Interfaz común para estrategias:**
```java
interface EstrategiaEnvio {
    void enviarMensaje(String mensaje);
}
```
📌 **Estrategias concretas:**
```java
class EnvioEmail implements EstrategiaEnvio {
    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("Enviando Email: " + mensaje);
    }
}

class EnvioSMS implements EstrategiaEnvio {
    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("Enviando SMS: " + mensaje);
    }
}

class EnvioWhatsApp implements EstrategiaEnvio {
    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("Enviando WhatsApp: " + mensaje);
    }
}
```
📌 **Contexto que usa diferentes estrategias:**
```java
class Notificador {
    private EstrategiaEnvio estrategia;

    public void setEstrategia(EstrategiaEnvio estrategia) {
        this.estrategia = estrategia;
    }

    public void enviar(String mensaje) {
        if (estrategia == null) {
            throw new IllegalStateException("Estrategia de envío no definida");
        }
        estrategia.enviarMensaje(mensaje);
    }
}
```
📌 **Ejemplo de uso:**
```java
public class Main {
    public static void main(String[] args) {
        Notificador notificador = new Notificador();

        notificador.setEstrategia(new EnvioEmail());
        notificador.enviar("Hola, Strategy!");

        notificador.setEstrategia(new EnvioWhatsApp());
        notificador.enviar("Hola por WhatsApp!");
    }
}
```
📌 **Salida esperada:**
```
Enviando Email: Hola, Strategy!
Enviando WhatsApp: Hola por WhatsApp!
```
✅ **Podemos cambiar de estrategia sin modificar el código base.**

---

# **3️⃣ Chain of Responsibility: Procesamiento Encadenado**
📌 **¿Qué es?**  
El **patrón Chain of Responsibility** permite pasar una solicitud a través de una **cadena de manejadores** hasta que uno la procese.

📌 **Casos de uso:**  
✅ **Procesamiento de solicitudes en cascada (middleware, validaciones, seguridad)**  
✅ **Permite agregar nuevos manejadores sin modificar los existentes**

---

## **🔹 Ejemplo: Validación de Autenticación**
📌 **Interfaz de manejador:**
```java
abstract class ManejadorAutenticacion {
    protected ManejadorAutenticacion siguiente;

    public void setSiguiente(ManejadorAutenticacion siguiente) {
        this.siguiente = siguiente;
    }

    public abstract void manejar(String usuario, String password);
}
```
📌 **Manejadores concretos:**
```java
class VerificarUsuario extends ManejadorAutenticacion {
    @Override
    public void manejar(String usuario, String password) {
        if ("admin".equals(usuario)) {
            System.out.println("Usuario válido");
            if (siguiente != null) {
                siguiente.manejar(usuario, password);
            }
        } else {
            System.out.println("Usuario no encontrado");
        }
    }
}

class VerificarPassword extends ManejadorAutenticacion {
    @Override
    public void manejar(String usuario, String password) {
        if ("1234".equals(password)) {
            System.out.println("Contraseña correcta, acceso permitido.");
        } else {
            System.out.println("Contraseña incorrecta, acceso denegado.");
        }
    }
}
```
📌 **Ejemplo de uso:**
```java
public class Main {
    public static void main(String[] args) {
        ManejadorAutenticacion usuario = new VerificarUsuario();
        ManejadorAutenticacion password = new VerificarPassword();

        usuario.setSiguiente(password);

        usuario.manejar("admin", "1234"); // Caso exitoso
        usuario.manejar("admin", "wrong"); // Contraseña incorrecta
        usuario.manejar("invitado", "1234"); // Usuario no encontrado
    }
}
```
📌 **Salida esperada:**
```
Usuario válido
Contraseña correcta, acceso permitido.

Usuario válido
Contraseña incorrecta, acceso denegado.

Usuario no encontrado
```
✅ **Cada manejador decide si procesa la solicitud o la pasa al siguiente.**

---

# **4️⃣ Ejercicio Práctico: Sistema de Notificaciones**
📌 **Queremos:**  
1️⃣ Soportar **diferentes estrategias** (`SMS`, `Email`, `Push`).  
2️⃣ Usar **Observer** para notificar múltiples usuarios.  
3️⃣ Usar **Chain of Responsibility** para validar el mensaje antes de enviarlo.

🚀 **Aplica lo aprendido en los ejemplos anteriores para resolverlo!**

---

<details>
    <summary>Solución</summary>

📌 **Estructura del proyecto:**
```
📂 src/
 ├── 📂 observer/
 │    ├── Observador.java
 │    ├── Usuario.java
 │    ├── Notificador.java
 ├── 📂 strategy/
 │    ├── EstrategiaEnvio.java
 │    ├── EnvioEmail.java
 │    ├── EnvioSMS.java
 │    ├── EnvioPush.java
 ├── 📂 chain/
 │    ├── ManejadorValidacion.java
 │    ├── ValidarLongitud.java
 │    ├── ValidarPalabrasProhibidas.java
 ├── Main.java
```

---

## **1️⃣ Implementar `Observer`: Múltiples usuarios reciben notificaciones**
📌 **Interfaz del observador (`Observador.java`)**
```java
package observer;

public interface Observador {
    void recibirNotificacion(String mensaje);
}
```

📌 **Clase `Usuario.java` (Implementa Observer)**
```java
package observer;

public class Usuario implements Observador {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void recibirNotificacion(String mensaje) {
        System.out.println(nombre + " recibió: " + mensaje);
    }
}
```

📌 **Clase `Notificador.java` (Sujeto que notifica a los observadores)**
```java
package observer;

import java.util.ArrayList;
import java.util.List;

public class Notificador {
    private List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void notificar(String mensaje) {
        for (Observador obs : observadores) {
            obs.recibirNotificacion(mensaje);
        }
    }
}
```

✅ **Con esto, podemos notificar a múltiples usuarios automáticamente.**

---

## **2️⃣ Implementar `Strategy`: Diferentes estrategias de envío**
📌 **Interfaz `EstrategiaEnvio.java` (Definir diferentes formas de enviar notificaciones)**
```java
package strategy;

public interface EstrategiaEnvio {
    void enviarMensaje(String mensaje);
}
```

📌 **Implementaciones concretas (`Email`, `SMS`, `Push`)**
```java
package strategy;

public class EnvioEmail implements EstrategiaEnvio {
    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("📧 Enviando Email: " + mensaje);
    }
}
```
```java
package strategy;

public class EnvioSMS implements EstrategiaEnvio {
    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("📱 Enviando SMS: " + mensaje);
    }
}
```
```java
package strategy;

public class EnvioPush implements EstrategiaEnvio {
    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("🔔 Enviando Notificación Push: " + mensaje);
    }
}
```

📌 **Clase `GestorNotificaciones.java` (Contexto que usa Strategy)**
```java
package strategy;

public class GestorNotificaciones {
    private EstrategiaEnvio estrategia;

    public void setEstrategia(EstrategiaEnvio estrategia) {
        this.estrategia = estrategia;
    }

    public void enviar(String mensaje) {
        if (estrategia == null) {
            throw new IllegalStateException("No se ha definido una estrategia de envío");
        }
        estrategia.enviarMensaje(mensaje);
    }
}
```
✅ **Podemos cambiar dinámicamente la estrategia de envío sin modificar el código.**

---

## **3️⃣ Implementar `Chain of Responsibility`: Validar mensajes antes de enviarlos**
📌 **Clase base `ManejadorValidacion.java`**
```java
package chain;

public abstract class ManejadorValidacion {
    protected ManejadorValidacion siguiente;

    public void setSiguiente(ManejadorValidacion siguiente) {
        this.siguiente = siguiente;
    }

    public abstract boolean validar(String mensaje);
}
```

📌 **Validación de longitud (`ValidarLongitud.java`)**
```java
package chain;

public class ValidarLongitud extends ManejadorValidacion {
    @Override
    public boolean validar(String mensaje) {
        if (mensaje.length() > 100) {
            System.out.println("❌ Error: El mensaje es demasiado largo.");
            return false;
        }
        return siguiente == null || siguiente.validar(mensaje);
    }
}
```

📌 **Validación de palabras prohibidas (`ValidarPalabrasProhibidas.java`)**
```java
package chain;

import java.util.List;

public class ValidarPalabrasProhibidas extends ManejadorValidacion {
    private List<String> palabrasProhibidas = List.of("spam", "oferta");

    @Override
    public boolean validar(String mensaje) {
        for (String palabra : palabrasProhibidas) {
            if (mensaje.toLowerCase().contains(palabra)) {
                System.out.println("❌ Error: El mensaje contiene palabras prohibidas.");
                return false;
            }
        }
        return siguiente == null || siguiente.validar(mensaje);
    }
}
```
📌 **Ejemplo de uso de Chain of Responsibility:**
```java
package chain;

public class ValidadorMensajes {
    public static boolean validarMensaje(String mensaje) {
        ManejadorValidacion validador1 = new ValidarLongitud();
        ManejadorValidacion validador2 = new ValidarPalabrasProhibidas();

        validador1.setSiguiente(validador2);

        return validador1.validar(mensaje);
    }
}
```
✅ **Ahora el mensaje pasa por validaciones antes de ser enviado.**

---

## **4️⃣ Integración en `Main.java`**
📌 **Ejecutamos el sistema completo**
```java
import observer.*;
import strategy.*;
import chain.*;

public class Main {
    public static void main(String[] args) {
        // 1️⃣ Crear usuarios (Observer)
        Usuario usuario1 = new Usuario("Carlos");
        Usuario usuario2 = new Usuario("Ana");

        Notificador notificador = new Notificador();
        notificador.agregarObservador(usuario1);
        notificador.agregarObservador(usuario2);

        // 2️⃣ Definir estrategia de envío
        GestorNotificaciones gestor = new GestorNotificaciones();
        gestor.setEstrategia(new EnvioEmail()); // Cambiar a EnvioSMS o EnvioPush según necesidad

        // 3️⃣ Validar y enviar mensaje
        String mensaje = "¡Oferta especial para ti!";
        if (ValidadorMensajes.validarMensaje(mensaje)) {
            gestor.enviar(mensaje);
            notificador.notificar(mensaje);
        }
    }
}
```
📌 **Salida esperada:**
```
❌ Error: El mensaje contiene palabras prohibidas.
```
📌 **Si el mensaje fuera válido:**
```
📧 Enviando Email: ¡Hola, este es un mensaje válido!
Carlos recibió: ¡Hola, este es un mensaje válido!
Ana recibió: ¡Hola, este es un mensaje válido!
```
✅ **El mensaje solo se envía si pasa las validaciones.**

---
</details>