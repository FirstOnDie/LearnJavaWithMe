# Gateway

## Explicación:

El patrón Gateway actúa como un punto de acceso para integrar diferentes sistemas o servicios. Funciona como una puerta que permite la comunicación entre dos partes, asegurando que la información pase de manera segura y controlada.

## Ejemplo con código:

```java 
// Clase Gateway
public class ServicioGateway {
    public String obtenerDatosDeServicioExterno() {
        // Aquí se conectaría a un servicio externo y obtendría datos
        return "Datos desde el servicio externo";
    }
}

// Ejecución
public class Main {
    public static void main(String[] args) {
        ServicioGateway gateway = new ServicioGateway();
        String datos = gateway.obtenerDatosDeServicioExterno();
        System.out.println(datos);
    }
}
```

## Explicación del ejemplo:

- Gateway (ServicioGateway): Representa una puerta de entrada para acceder a un servicio externo. Simplifica la interacción con servicios externos proporcionando una interfaz unificada.
- Ejecución (Main): Utiliza el gateway para obtener datos del servicio externo.

## Dónde se usa:

Se utiliza cuando se necesita integrar varios servicios o sistemas, como en aplicaciones empresariales que interactúan con servicios de terceros.

**Ventajas:**

- Simplificación: Proporciona una interfaz simple para interactuar con servicios complejos.
- Seguridad: Controla el acceso y asegura la comunicación entre sistemas.

**Inconvenientes:**

- Dependencia: Puede crear un punto único de fallo si el gateway no está disponible.
- Latencia: Puede introducir retrasos si no se gestiona correctamente.