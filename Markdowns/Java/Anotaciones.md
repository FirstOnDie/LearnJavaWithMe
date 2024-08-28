# Anotaciones

Las anotaciones en Java son como pequeños ayudantes que hacen el trabajo más fácil para los programadores. Imagina que estás en una gran cocina con muchos ingredientes y herramientas. Las anotaciones son como etiquetas mágicas que te ayudan a decirle a los otros cocineros o a las máquinas exactamente qué hacer sin tener que explicar cada pequeño detalle. Algunas anotaciones ayudan a organizar los datos, otras aseguran que las reglas se sigan correctamente, y otras hacen que tu código sea más limpio y fácil de entender.

## Índice de anotaciones

1. Java Básico

   -    [@Override](#override)
   -    [@Deprecated](#deprecated)
   -    [@SuppressWarnings](#suppresswarnings)
   -    [@SafeVarargs](#safevarargs)
   -    [@FunctionalInterface](#functionalinterface)
   
2. Pruebas Unitarias (JUnit)

    -    [@Test](#test)
   
3. JPA (Java Persistence API)

   -    [@Entity](#entity)
   -    [@Id](#id)
   -    [@GeneratedValue](#generatedvalue)
   -    [@Column](#column)
   
4. Spring Framework

   -    Inyección de Dependencias y Componentes
        -    [@Autowired](#autowired)
        -    [@Service](#service)
        -    [@Repository](#repository)
        -    [@Component](#component)
   
   -   Controladores Web (Spring MVC y Spring REST)
       -    [@Controller](#controller)
       -    [@RestController](#restcontroller)
       -    [@RequestMapping](#requestmapping)
       -    [@GetMapping](#getmapping)
       -    [@PostMapping](#postmapping)
       -    [@RequestParam](#requestparam)

---
### @Override
**¿Qué hace?**
La anotación `@Override` le dice a Java que un método está reemplazando a uno con el mismo nombre en una clase base. Piensa en esto como si fueras a un concurso de canto donde cada participante tiene que cantar la misma canción. Si decides cantar la canción de una manera diferente, necesitas decirle a los jueces: "Voy a hacer mi propia versión". Eso es lo que hace `@Override`: indica que estás haciendo tu propia versión de algo que ya existe.

Ejemplo:

```java
class Animal {
    public void hacerSonido() {
        System.out.println("Algun sonido");
    }
}

class Perro extends Animal {
    @Override
    public void hacerSonido() {
        System.out.println("Guau");
    }
}
```
**Explicación más detallada:**

Aquí tenemos una clase llamada `Animal` que tiene un método llamado `hacerSonido`. Luego tenemos una clase llamada `Perro` que extiende (o hereda de) `Animal`. En `Perro`, usamos `@Override` para decir: "Voy a hacer mi propia versión del método `hacerSonido`". Así que cuando llamamos al método `hacerSonido` en un `Perro`, en lugar de hacer un sonido genérico, el perro ladra "Guau".

---
### @Deprecated
**¿Qué hace?**
La anotación `@Deprecated` se utiliza para indicar que algo es antiguo y ya no debería usarse. Imagina que tienes un juguete favorito, pero ahora está roto o ya no es seguro. Tus padres te dicen que no juegues más con él porque hay uno nuevo y mejor. `@Deprecated` es como esa advertencia: "Este juguete es viejo y no deberías usarlo".

Ejemplo:

```java
class Calculadora {
    @Deprecated
    public int sumarViejo(int a, int b) {
        return a + b;
    }

    public int sumar(int a, int b) {
        return a + b;
    }
}
```
**Explicación más detallada:**

En este ejemplo, tenemos una clase`Calculadora` con dos métodos para sumar números. El método `sumarViejo` está marcado con `@Deprecated`, lo que significa que este método es viejo y hay una mejor manera de hacer lo mismo, que es el método `sumar`. Si alguien intenta usar `sumarViejo`, verá una advertencia de que debería usar el nuevo método `sumar` en su lugar.

---
### @SuppressWarnings
**¿Qué hace?**
`@SuppressWarnings` le dice a Java que ignore ciertas advertencias que podrían aparecer. Imagina que tienes una alarma en casa que a veces se activa sin razón. Es molesto, así que decides apagar esa alarma. `@SuppressWarnings` hace algo similar: le dice a Java que no muestre ciertas advertencias que ya sabes que están ahí.

Ejemplo:

```java
public class Main {
    @SuppressWarnings("unchecked")
    public void metodo() {
        // Código que genera una advertencia
    }
}
```
**Explicación más detallada:**

Supongamos que tienes un código que funciona bien, pero que genera una advertencia innecesaria. Puedes usar `@SuppressWarnings("unchecked")` para decirle a Java que ignore esa advertencia específica. Es como decirle a Java: "¡No te preocupes por esto, sé lo que estoy haciendo!".

---
### @SafeVarargs
**¿Qué hace?**
`@SafeVarargs` se usa para indicar que un método que utiliza un número variable de argumentos es seguro de usar. Es como decir: "No te preocupes, estoy manejando esto de manera segura".

Ejemplo:

```java
public class Main {
    @SafeVarargs
    public final <T> void imprimir(T... elementos) {
        for (T elemento : elementos) {
            System.out.println(elemento);
        }
    }
}
```
**Explicación más detallada:**

Aquí, el método `imprimir` puede tomar una cantidad variable de argumentos (como una lista de cosas para imprimir). La anotación `@SafeVarargs` le dice a Java que no se preocupe, que hemos comprobado que este uso es seguro. Es como asegurarle a alguien que te está observando que no se va a caer ningún plato mientras haces malabares.

---
### @FunctionalInterface
**¿Qué hace?**
Una `@FunctionalInterface` es una interfaz que solo tiene un método abstracto, es decir, una forma de hacer una cosa específica. Imagina que es como una herramienta que solo tiene una función, como un destornillador que solo atornilla.

Ejemplo:

```java
@FunctionalInterface
interface Operacion {
    int operar(int a, int b);
}
```
**Explicación más detallada:**

Aquí, la interfaz `Operacion` es una interfaz funcional porque solo tiene un método llamado `operar`. Usamos `@FunctionalInterface` para asegurarnos de que nadie agregue más métodos a esta interfaz. Es como decir: "Esta herramienta es solo para esta tarea y nada más".

---
### @Test
**¿Qué hace?**
`@Test` se utiliza en pruebas para marcar un método como una prueba que se ejecutará para verificar que el código funciona como debería. Es como cuando haces un experimento en la escuela para comprobar si tu teoría es correcta.

Ejemplo:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {
    @Test
    public void testSumar() {
        Calculadora calc = new Calculadora();
        assertEquals(5, calc.sumar(2, 3));
    }
}
```
**Explicación más detallada:**

Aquí, `@Test` le dice a Java que este método `testSumar` es una prueba. En la prueba, estamos comprobando que el método `sumar` en la clase `Calculadora` funciona correctamente. Es como hacer una prueba para ver si una receta de cocina sale como se esperaba.

---
### @Entity
**¿Qué hace?**
`@Entity` marca una clase como una entidad que se mapeará a una tabla en una base de datos. Imagina que tienes una hoja de cálculo en la que cada fila representa un jugador de un equipo. Cada fila es como una entidad que almacena datos específicos sobre un jugador.

Ejemplo:

```java
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class Usuario {
    @Id
    private Long id;
    private String nombre;
}
```
**Explicación más detallada:**

Aquí, `Usuario` es una entidad porque hemos usado `@Entity`. Esto significa que en nuestra base de datos habrá una tabla llamada `Usuario`, y cada objeto `Usuario` que creamos será una fila en esa tabla, como tener un libro de registros donde se anota información de cada usuario.

---
### @Id
***¿Qué hace?***
`@Id` se usa para indicar que un campo en una entidad es la clave primaria en la base de datos, lo que significa que este campo debe ser único para cada fila. Es como tener un número de estudiante en la escuela que es único para cada alumno.

Ejemplo:

```java

@Entity
class Usuario {
    @Id
    private Long id;
    private String nombre;
}
```
**Explicación más detallada:**

Aquí, el campo `id` está marcado con `@Id`, lo que significa que cada `Usuario` en la base de datos tendrá un `id` único. Es como una tarjeta de identificación que asegura que cada persona tiene un número único.

---
### @GeneratedValue
**¿Qué hace?**
`@GeneratedValue` indica que el valor de la clave primaria se generará automáticamente. Es como cuando el banco te da un número de cuenta automáticamente cuando abres una nueva cuenta.

Ejemplo:

```java
@Entity
class Usuario {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
}
```
**Explicación más detallada:**

Aquí, cada vez que se crea un nuevo `Usuario`, la base de datos genera automáticamente un nuevo `id` único para él. Es como que te asignen un número automáticamente cuando te unes a un club.

---
### @Column
**¿Qué hace?**
`@Column` se usa para personalizar los detalles de una columna en la base de datos. Es como si quisieras dar un nombre especial a una columna en tu hoja de cálculo.

Ejemplo:

```java
@Entity
class Usuario {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "nombre_usuario")
    private String nombre;
}
```
**Explicación más detallada:**

Aquí, `nombre` en la clase `Usuario` se almacena en la base de datos en una columna llamada `nombre_usuario`. Es como decir: "Quiero que mi columna tenga un nombre especial en la base de datos".

---
### @Autowired
**¿Qué hace?**
`@Autowired` se utiliza para que Spring inyecte automáticamente un objeto para nosotros. Es como tener un robot ayudante que sabe exactamente dónde están todas las herramientas que necesitas y te las trae cuando las pides.

Ejemplo:

```java
import org.springframework.beans.factory.annotation.Autowired;

public class ServicioUsuario {
    @Autowired
    private RepositorioUsuario repositorio;

    public Usuario obtenerUsuario(Long id) {
        return repositorio.findById(id).orElse(null);
    }
}
```
**Explicación más detallada:**

En este ejemplo, `@Autowired` se usa para que Spring encuentre automáticamente un objeto `RepositorioUsuario` y lo ponga en `ServicioUsuario`. Es como si tu robot ayudante te diera un martillo cada vez que dices "martillo", sin tener que buscarlo tú mismo.

---
### @Component
**¿Qué hace?**
`@Component` se utiliza en Spring para marcar una clase como un "componente" que se puede usar en otros lugares de la aplicación. Es como poner una etiqueta en una herramienta en tu caja de herramientas, para que siempre sepas que está ahí y puedas usarla cuando la necesites.

Ejemplo:

```java
import org.springframework.stereotype.Component;

@Component
public class MiComponente {
    public void hacerAlgo() {
        System.out.println("Haciendo algo...");
    }
}
```
**Explicación más detallada:**

Aquí, `MiComponente` está marcado como un componente, lo que significa que Spring lo reconocerá automáticamente y lo pondrá a disposición para ser utilizado en cualquier parte de la aplicación. Es como si tuvieras una herramienta universal que siempre está disponible en tu caja de herramientas.

---
### @Service
**¿Qué hace?**
`@Service` es una anotación de Spring que se utiliza para indicar que una clase es un "servicio", que normalmente contiene la lógica de negocio de la aplicación. Es como una estación de servicio en una fábrica que se encarga de hacer una tarea específica, como ensamblar una parte del producto.

Ejemplo:

```java

import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario {
    public Usuario obtenerUsuario(Long id) {
    // tu lógica
    return new Usuario();
    }
}
```
**Explicación más detallada:**

En este ejemplo, `ServicioUsuario` está marcado como un servicio, lo que significa que es responsable de alguna parte importante de la lógica de negocio. Es como una máquina en la línea de montaje que realiza un trabajo específico.

---
### @Repository
**¿Qué hace?**
`@Repository` se utiliza en Spring para marcar una clase como un repositorio de datos, que se encarga de interactuar con la base de datos. Es como tener un archivador en el que puedes guardar y buscar documentos importantes.

Ejemplo:

```java
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
}
```
**Explicación más detallada:**

Aquí, `RepositorioUsuario` es una interfaz que está marcada como un repositorio. Esto significa que Spring sabe que esta clase se usará para guardar y buscar `Usuario` en la base de datos. Es como una oficina de archivo que gestiona todos los documentos de los usuarios.

---
### @Controller
**¿Qué hace?**
`@Controller` se utiliza en Spring MVC para marcar una clase que se encargará de manejar las solicitudes web y devolver vistas. Imagina que es como un recepcionista en un hotel que recibe a los huéspedes y les da la información que necesitan.

Ejemplo:

```java

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorInicio {
    @GetMapping("/")
    public String inicio() {
        return "inicio";
    }
}
```
**Explicación más detallada:**

En este ejemplo, `ControladorInicio` es un controlador que maneja las solicitudes que llegan a la raíz del sitio web (`"/"`). Es como un recepcionista que recibe a todos los visitantes que llegan al hotel y los dirige a la página de inicio.

---
### @RestController
**¿Qué hace?**
`@RestController` es una combinación de `@Controller` y `@ResponseBody`. Se utiliza en Spring MVC para crear controladores que manejan solicitudes web y devuelven datos directamente en lugar de vistas. Es como un repartidor de pizzas que entrega las pizzas directamente a la puerta del cliente.

Ejemplo:

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiControlador {
    @GetMapping("/saludo")
    public String saludo() {
        return "Hola, Mundo!";
    }
}
```
***Explicación más detallada:***

Aquí, ApiControlador es un controlador REST que maneja solicitudes web enviadas a `"/saludo"` y devuelve directamente una respuesta de texto. Es como un repartidor que lleva las pizzas (datos) directamente a la puerta de tu casa (navegador o aplicación cliente).

---
### @RequestMapping
**¿Qué hace?**
`@RequestMapping` se utiliza en Spring MVC para mapear solicitudes HTTP a métodos específicos en un controlador. Imagina que es como un mapa que muestra qué camino tomar para llegar a una habitación específica en un edificio.

Ejemplo:

```java

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiControlador {
    @RequestMapping("/saludo")
    public String saludo() {
        return "Hola, Mundo!";
    }
}
```
**Explicación más detallada:**

En este ejemplo, `@RequestMapping("/api")` en la clase significa que todas las solicitudes que comienzan con `/api` serán manejadas por este controlador. Luego, `@RequestMapping("/saludo")` en el método saludo significa que este método maneja solicitudes a `/api/saludo`. Es como un directorio de oficinas que muestra qué habitaciones se encuentran en qué piso.

---
### @GetMapping
**¿Qué hace?**
`@GetMapping` es una anotación específica para manejar solicitudes HTTP GET en Spring MVC. Piensa en esto como una señal que indica que una calle es solo de ida, solo en una dirección.

Ejemplo:

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiControlador {
    @GetMapping("/saludo")
    public String saludo() {
        return "Hola, Mundo!";
    }
}
```
**Explicación más detallada:**

Aquí, `@GetMapping("/saludo")` significa que este método maneja solicitudes GET a `/saludo`. Es como una calle de una sola dirección, solo las solicitudes GET pueden ir por ese camino.

---
### @PostMapping
**¿Qué hace?**
`@PostMapping` se utiliza en Spring MVC para manejar solicitudes HTTP POST. Imagina que es como una entrada especial en un edificio donde solo se puede entrar pero no salir.

Ejemplo:

```java
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiControlador {
    @PostMapping("/usuario")
    public String crearUsuario(@RequestBody Usuario usuario) {
        return "Usuario creado: " + usuario.getNombre();
    }
}
```
**Explicación más detallada:**

En este ejemplo, `@PostMapping("/usuario")` significa que este método maneja solicitudes POST a `/usuario`. Es como un buzón de sugerencias donde las personas solo pueden dejar notas (datos) pero no sacarlas.

---
### @RequestParam
**¿Qué hace?**
`@RequestParam` se utiliza para extraer parámetros de la solicitud HTTP en un método de un controlador en Spring. Es como una casilla en un formulario donde puedes escribir una respuesta específica.

Ejemplo:

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiControlador {
    @GetMapping("/saludar")
    public String saludar(@RequestParam String nombre) {
        return "Hola, " + nombre + "!";
    }
}
```
**Explicación más detallada:**
En este ejemplo, `@RequestParam String nombre` significa que este método espera recibir un parámetro llamado `nombre` en la solicitud. Es como un formulario en el que te piden tu nombre y, dependiendo de lo que escribas, te saludan de esa manera.