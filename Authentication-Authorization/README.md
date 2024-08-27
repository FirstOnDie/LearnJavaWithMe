# Authenticación y Autorización

**JWT (JSON Web Token)** es un estándar para crear tokens de acceso que permiten la autenticación y autorización de usuarios de manera segura en aplicaciones web. En el contexto de Spring Boot, JWT se utiliza para proteger APIs y asegurarse de que solo los usuarios autenticados y autorizados puedan acceder a ciertos recursos.

# ¿Cómo Funciona JWT?
- **Autenticación:**
  - Un usuario envía sus credenciales (nombre de usuario y contraseña) al servidor para autenticarse.
  - El servidor valida las credenciales y, si son correctas, genera un JWT que contiene información del usuario y un tiempo de expiración.
  - Este token es enviado de vuelta al cliente y es almacenado (usualmente en el almacenamiento local del navegador o en cookies seguras).

- **Autorización:**
  - Para acceder a rutas protegidas, el cliente envía el JWT en los encabezados de la solicitud HTTP.
  - El servidor verifica la validez del JWT (firma y fecha de expiración) y, si es válido, permite el acceso al recurso solicitado.