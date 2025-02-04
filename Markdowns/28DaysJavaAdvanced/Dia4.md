
# **📌 Día 4: Manejo de JSON y XML en Java**

Hoy veremos:  
✅ **Manejo de JSON con Jackson y Gson**  
✅ **Conversión entre POJOs y JSON**  
✅ **Manejo de XML con JAXB**  
✅ **Ejercicio: Cargar y guardar configuración en JSON**

---

📌 **¿Por qué es importante?**  
La mayoría de las aplicaciones modernas necesitan trabajar con **JSON** (para APIs REST, configuraciones, etc.) y **XML** (para documentos, configuraciones en Spring, etc.). Hoy aprenderás a **leer, escribir y convertir objetos Java** en JSON y XML.

---

# **1️⃣ JSON en Java con Jackson y Gson**

📌 **¿Qué es JSON?**  
JSON (**JavaScript Object Notation**) es un formato de intercambio de datos liviano y fácil de leer.

📌 **Ejemplo de JSON:**
```json
{
  "nombre": "Carlos",
  "edad": 30,
  "activo": true
}
```

---

## **🔹 Opción 1: Convertir Objetos Java a JSON con Jackson**
📌 **Jackson es la librería más usada en Java para trabajar con JSON.**

### **1️⃣ Agregar dependencia en `pom.xml` (si usas Maven)**
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.0</version>
</dependency>
```

### **2️⃣ Crear la clase `Usuario.java`**
```java
import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {
    private String nombre;
    private int edad;
    
    @JsonProperty("activo") // Personalizar clave en JSON
    private boolean estaActivo;

    public Usuario() {} // Constructor vacío para Jackson

    public Usuario(String nombre, int edad, boolean estaActivo) {
        this.nombre = nombre;
        this.edad = edad;
        this.estaActivo = estaActivo;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public boolean isEstaActivo() { return estaActivo; }
}
```

### **3️⃣ Convertir de Java a JSON**
```java
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertirJson {
    public static void main(String[] args) {
        try {
            Usuario usuario = new Usuario("Carlos", 30, true);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(usuario);
            System.out.println("JSON generado: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
📌 **Salida esperada:**
```json
{"nombre":"Carlos","edad":30,"activo":true}
```
✅ **Fácil y limpio.**

---

## **🔹 Opción 2: Convertir JSON a Objeto con Jackson**
```java
public class LeerJson {
    public static void main(String[] args) {
        try {
            String json = "{\"nombre\":\"Carlos\",\"edad\":30,\"activo\":true}";

            ObjectMapper objectMapper = new ObjectMapper();
            Usuario usuario = objectMapper.readValue(json, Usuario.class);

            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Edad: " + usuario.getEdad());
            System.out.println("Activo: " + usuario.isEstaActivo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
📌 **Salida esperada:**
```
Nombre: Carlos
Edad: 30
Activo: true
```
✅ **Convierte JSON en un objeto Java sin esfuerzo.**

---

## **🔹 Opción 3: Usar Gson para JSON**
📌 **Otra opción popular es `Gson` de Google.**

### **1️⃣ Agregar dependencia en `pom.xml`**
```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
```

### **2️⃣ Convertir Objeto a JSON con Gson**
```java
import com.google.gson.Gson;

public class ConvertirJsonGson {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Ana", 25, false);
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        System.out.println("JSON generado: " + json);
    }
}
```
📌 **Salida esperada:**
```json
{"nombre":"Ana","edad":25,"estaActivo":false}
```
✅ **Menos configuraciones que Jackson, ideal para proyectos pequeños.**

---

# **2️⃣ Convertir Objetos Java a XML con JAXB**

📌 **¿Qué es XML?**  
XML (**eXtensible Markup Language**) es un formato de datos estructurado similar a HTML, usado para **configuraciones, almacenamiento de datos, y comunicación entre sistemas**.

📌 **Ejemplo de XML:**
```xml
<Usuario>
    <nombre>Carlos</nombre>
    <edad>30</edad>
    <activo>true</activo>
</Usuario>
```

---

## **🔹 Convertir Objeto Java a XML con JAXB**
📌 **JAXB (Java Architecture for XML Binding) permite convertir Java a XML fácilmente.**

### **1️⃣ Agregar dependencia en `pom.xml`**
```xml
<dependency>
    <groupId>jakarta.xml.bind</groupId>
    <artifactId>jakarta.xml.bind-api</artifactId>
    <version>4.0.0</version>
</dependency>
<dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
    <version>4.0.0</version>
</dependency>
```

### **2️⃣ Crear `Usuario.java` con anotaciones JAXB**
```java
import jakarta.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario {
    private String nombre;
    private int edad;
    private boolean activo;

    public Usuario() {} // Constructor vacío requerido

    public Usuario(String nombre, int edad, boolean activo) {
        this.nombre = nombre;
        this.edad = edad;
        this.activo = activo;
    }
}
```

### **3️⃣ Convertir un objeto Java a XML**
```java
import jakarta.xml.bind.*;

import java.io.StringWriter;

public class ConvertirXml {
    public static void main(String[] args) {
        try {
            Usuario usuario = new Usuario("Carlos", 30, true);
            JAXBContext contexto = JAXBContext.newInstance(Usuario.class);
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(usuario, writer);

            System.out.println("XML generado:\n" + writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
📌 **Salida esperada:**
```xml
<Usuario>
    <nombre>Carlos</nombre>
    <edad>30</edad>
    <activo>true</activo>
</Usuario>
```
✅ **Así de fácil convertimos objetos en XML.**

---

# **3️⃣ Ejercicio Práctico: Cargar y Guardar Configuración en JSON**

📌 **Queremos:**  
1️⃣ **Leer una configuración desde `config.json`**  
2️⃣ **Modificar valores y guardar cambios**

```json
{
  "baseDeDatos": "MySQL",
  "usuario": "admin",
  "timeout": 30
}
```

<details>
    <summary>Solución</summary>

### **1️⃣ Código para leer y escribir la configuración**
```java
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConfiguracionApp {
    private static final String CONFIG_FILE = "config.json";

    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Leer configuración
            Map<String, Object> config = objectMapper.readValue(new File(CONFIG_FILE), Map.class);
            System.out.println("Configuración actual: " + config);

            // Modificar valor
            config.put("timeout", 60);

            // Guardar cambios
            objectMapper.writeValue(new File(CONFIG_FILE), config);
            System.out.println("Configuración actualizada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
✅ **Así manejamos configuraciones en JSON dinámicamente.**

</details>