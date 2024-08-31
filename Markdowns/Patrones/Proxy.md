# Proxy

## Explicación:

El patrón Proxy proporciona un sustituto o marcador de posición para otro objeto. Un proxy controla el acceso a su objeto representado, permitiendo agregar funcionalidad adicional antes o después de las llamadas al objeto real.

## Ejemplo con código:

```java
// Interfaz de servicio
public interface Servicio {
    void peticion();
}

// Servicio real
public class ServicioReal implements Servicio {
    @Override
    public void peticion() {
        System.out.println("Servicio Real: Procesando solicitud.");
    }
}

// Proxy
public class ServicioProxy implements Servicio {
    private ServicioReal servicioReal;

    @Override
    public void peticion() {
        if (servicioReal == null) {
            servicioReal = new ServicioReal();
        }
        System.out.println("Proxy: Comprobación de acceso antes de enviar la solicitud.");
        servicioReal.peticion();
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        Servicio servicio = new ServicioProxy();
        servicio.peticion();
    }
}
```

## Explicación del ejemplo:

- Proxy (ServicioProxy): Actúa como intermediario para el ServicioReal.
- Servicio real (ServicioReal): Realiza la operación cuando el proxy lo permite.
- Ejecución (Main): Utiliza el proxy para controlar el acceso al servicio real.

## Dónde se usa:

Se utiliza para controlar el acceso a objetos que son costosos de crear o que requieren control de acceso adicional, como en aplicaciones de seguridad o manejo de recursos remotos.

**Ventajas:**

- Control de acceso: Puede agregar seguridad y control adicional al objeto.
- Creación diferida: Puede retrasar la creación del objeto real hasta que sea necesario.

**Inconvenientes:**

- Complejidad adicional: Introduce otra capa que puede complicar el diseño.
- Potencial de sobrecarga: Puede ralentizar el rendimiento si se abusa del patrón.