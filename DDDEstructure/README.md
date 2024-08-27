# Estructura DDD

Vamos a ver como funciona la estructura de un proyecto DDD.

## ¿Qué es DDD?

DDD (Domain Driven Design) es una metodología de diseño de software que se centra en el dominio del problema, en lugar de en la tecnología. Es decir, se centra en el negocio y en cómo se resuelven los problemas de negocio.

Imagina que estás creando una ciudad en tu juego favorito de construcción de ciudades. En esta ciudad, hay diferentes barrios y cada barrio tiene su propósito: hay uno donde viven las personas, otro donde trabajan, otro donde estudian, y así sucesivamente. Cada barrio tiene sus propias reglas y formas de hacer las cosas.

**El Diseño Dirigido por el Dominio (DDD) es como construir esa ciudad de manera que cadaa barrio esté bien organizado y funcione perfectamente con los demás.** Se enfoca en entender y modelar el "dominio" o el tema del que trata la aplicación, como si estuvieras organizando todos los barrios para que la ciudad funcione bien.

## Componentes Clave de DDD

Para entender mejor, vamos a ver cómo sería una ciudad siguiendo los principios de DDD:

- **Dominio**:
  - **Qué es**: El dominio es el tema principal o la "idea" de lo que trata tu ciudad (o tu aplicación).
  - **Ejemplo**: Imagina que el dominio de tu ciudad es "educación". Toda la ciudad está construida alrededor del concepto de enseñar y aprender.

- **Entidades**:
    - **Qué es**: Las entidades son cosas importantes en tu ciudad que tienen una identidad única. Son como los personajes principales de una historia.
    - **Ejemplo**: En nuestra ciudad educativa, las entidades podrían ser "Estudiante", "Profesor" y "Curso". Cada uno tiene características únicas: cada estudiante tiene un nombre, una edad y un ID único; cada profesor tiene un nombre y asignaturas que enseña; y cada curso tiene un título y una duración.

- **Objetos de Valor**:
    - **Qué es**: Son cosas que no tienen identidad única por sí mismas, pero que son importantes con su valor. Son como características de los personajes.
    - **Ejemplo**: En nuestra ciudad, un "Nombre" puede ser un objeto de valor. No importa si hay dos estudiantes con el mismo nombre, porque lo importante es el valor del nombre, no la identidad del nombre en sí.
  
- **Agregados**:
    - **Qué es**: Un agregado es como un grupo de entidades que están relacionadas y se tratan como una sola unidad. Piensa en esto como un equipo dentro de tu ciudad.
    - **Ejemplo**: En nuestro caso, un "Curso" podrías ser un agregado que incluye a los estudiantes y al profesor que lo enseña. Todo el equipo del curso se trata como una sola unidad, porque si algo cambia en el curso, podría afectar a todos los estudiantes y al profesor.

- **Repositorios**:
  - **Qué es**: Los repositorios son como grandes bibliotecas o almacenes donde guardamos y organizamos nuestras entidades.
  - **Ejemplo**: En la ciudad, tendríamos un repositorio para los "Estudiantes" y otro para los "Profesores". Es donde buscaríamos un estudiante o profesor cuando necesitamos información sobre ellos.

- **Servicios de Dominio**:
  - **Qué es**: Son trabajos o tareas que no pertenecen a ninguna entidad en particular, pero que son importantes para el dominio.
  - **Ejemplo**: Un servicio de dominio en nuestra ciudad educativa podría ser "Asignar Estudiantes a un Curso". No pertenece a ningún estudiante o curso en particular, pero es un trabajo necesario para el funcionamiento de la ciudad.

## Ejemplo de DDD: Ciudad Educativa

Ahora, pongamos todo esto junto a nuestra "Ciudad Educativa":

- **Dominio**: Educación
- **Entidades**: Estudiante, Profesor, Curso
- **Objetos de Valor**: Nombre, Edad, Materia
- **Agregados**: Un curso es un agregado que incluye estudiantes y un profesor.
- **Repositorios**: Tenemos un repositorio de estudiantes y otro de profesores para organizar y buscar información.
- **Servicios de Dominio**: "Asignar Estudiantes a un Curso" es una tarea que ayuda a que la ciudad funcione bien.

## ¿Por qué es útil DDD?

DDD es útil porque te ayuda a organizar tu "ciudad" (o tu aplicación) de manera que todo funcione de forma clara y lógica. Cada "barrio" (o parte de la aplicación) sabe lo que debe hacer y cómo interactuar con los demás barrios. Esto hace que la ciudad sea fácil de manejar y mejorar.

En resumen, **DDD es como construir una ciudad de manera que cada parte esté bien estructurada y cumpla con su propósito, asegurando que todo funcione de manera armoniosa.**