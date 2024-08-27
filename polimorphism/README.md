# Polimorfismo

**Polimorfismo** es una palabra grande que significa "muchas formas". En programación, y especialmente en Java, el polimorfismo permite que un objeto tome muchas formas diferentes. Imagina que es como si un juguete pudiera transformarse en diferentes animales: un día es un perro, otro día es un gato, ¡y otro día es un pájaro!

## Ejemplo Simple: Juguetes de Animales
Imagina que tienes una colección de juguetes de animales y que todos los animales pueden hacer un sonido, pero cada uno hace un sonido diferente:

- Perro: hace "guau".
- Gato: hace "miau".
- Pájaro: hace "pío".

Todos estos juguetes tienen un botón, y cuando presionas el botón, hacen su sonido especial.

Código Java Sencillo: Juguetes de Animales
Vamos a ver cómo podríamos hacer esto en Java:

```java

class Animal {
    public void makeSound() {
        System.out.println("Algun sonido de animal");
    }
}

class Perro extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Guau");
    }
}

class Gato extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Miau");
    }
}

class Pajaro extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Pío");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal miPerro = new Perro();
        Animal miGato = new Gato();
        Animal miPajaro = new Pajaro();

        miPerro.makeSound();  // Imprime "Guau"
        miGato.makeSound();   // Imprime "Miau"
        miPajaro.makeSound(); // Imprime "Pío"
    }
}
```
**¿Qué Está Pasando Aquí?**

- **Clase Animal**: Es como un juguete genérico de animal que no sabe qué sonido hacer exactamente. Solo sabe que debería hacer "Algún sonido de animal".


- **Clases Perro, Gato, y Pájaro**: Estas son clases especiales que "extienden" la clase Animal. Es como si fueran versiones mejoradas del juguete de animal que saben exactamente qué sonido hacer.

  - Perro sabe hacer "Guau".
  - Gato sabe hacer "Miau".
  - Pajaro sabe hacer "Pío".
    

- **Método makeSound()**: Este método es lo que llamas cuando presionas el botón del juguete. Cada clase de animal tiene su propia versión de makeSound() que hace un sonido diferente.

## ¿Por qué es divertido el Polimorfismo?
El polimorfismo hace que tu código sea más flexible y fácil de cambiar. Imagina que tienes muchos juguetes de animales diferentes y todos tienen un botón que llama al método **`makeSound()`**. Gracias al polimorfismo, no necesitas saber exactamente qué tipo de animal es antes de presionar el botón. Sabes que, sin importar el tipo de animal, va a hacer algún tipo de sonido.

**Otro Ejemplo: Transporte**
Vamos a imaginar ahora que tenemos diferentes tipos de transporte. Todos pueden moverse, pero cada uno se mueve de una manera diferente.

1. Bicicleta: Se mueve pedaleando.
2. Coche: Se mueve acelerando.
3. Avión: Se mueve volando.

```java
class Transporte {
    public void moverse() {
        System.out.println("El transporte se está moviendo");
    }
}

class Bicicleta extends Transporte {
    @Override
    public void moverse() {
        System.out.println("La bicicleta se mueve pedaleando");
    }
}

class Coche extends Transporte {
    @Override
    public void moverse() {
        System.out.println("El coche se mueve acelerando");
    }
}

class Avion extends Transporte {
    @Override
    public void moverse() {
        System.out.println("El avión se mueve volando");
    }
}

public class MainTransporte {
    public static void main(String[] args) {
        Transporte miBicicleta = new Bicicleta();
        Transporte miCoche = new Coche();
        Transporte miAvion = new Avion();

        miBicicleta.moverse();  // Imprime "La bicicleta se mueve pedaleando"
        miCoche.moverse();      // Imprime "El coche se mueve acelerando"
        miAvion.moverse();      // Imprime "El avión se mueve volando"
    }
}
```
## Resumen
- Polimorfismo en programación es como tener juguetes mágicos que pueden ser cualquier cosa.
- Permite que un objeto tenga muchas formas y actúe de maneras diferentes, dependiendo del tipo de objeto que realmente es.
- Hace que tu código sea más fácil de manejar, ya que puedes tener una clase genérica que se comporta de manera específica dependiendo del contexto.

## Tipos de polimorfismo

En Java, hay cuatro tipos principales de polimorfismo:

- Polimorfismo de Sobrecarga (Overloading Polymorphism)
- Polimorfismo de Sobrescritura (Overriding Polymorphism)
- Polimorfismo de Coerción (Coercion Polymorphism)
- Polimorfismo Paramétrico (Parametric Polymorphism)

### Polimorfismo de Sobrecarga (Overloading Polymorphism)
**¿Qué es?**
Imagina que tienes un robot que puede saludar de diferentes maneras. Puede decir "Hola" con o sin nombre, o con una forma especial. Todo depende de cómo le des las instrucciones. Este es el polimorfismo de sobrecarga, donde un método puede hacer cosas diferentes dependiendo de los parámetros que le pasas.

Crearemos podemos ver un code de ejemplo aquí [MainRobot](src/main/java/org/fractal/MainRobot.java).

En este ejemplo vemos lo siguiente:

- **Método saludar**: El robot tiene tres maneras diferentes de saludar. Puede hacerlo sin nombre, con nombre, o con nombre y de manera formal o informal.
- **Sobrecarga**: Todos estos métodos se llaman igual, pero tienen diferentes parámetros. Java sabe cuál usar dependiendo de cómo llamas al método.

### Polimorfismo de Sobrescritura (Overriding Polymorphism)
**¿Qué es?**
Imagina que tienes un juguete que es un animal. Todos los juguetes pueden hacer un sonido, pero cada animal hace un sonido diferente. Este es el polimorfismo de sobrescritura, donde un método en una clase hija reemplaza el método en la clase padre.
Crearemos podemos ver un code de ejemplo aquí [MainAnimal](src/main/java/org/fractal/MainAnimal.java).

En este ejemplo vemos lo siguiente:

- **Método hacerSonido**: Cada animal tiene su propia forma de hacer sonido. La clase Perro y la clase Gato sobrescriben el método hacerSonido de la clase Animal.
- **Sobrescritura**: Aquí, el método de la clase hija reemplaza al método de la clase padre. Esto permite que cada tipo de animal haga su propio sonido.

### Polimorfismo de Coerción (Coercion Polymorphism)
**¿Qué es?**
Piensa en el polimorfismo de coerción como cuando una cosa se convierte en otra de forma automática. Es como cuando estás construyendo una torre con bloques y puedes usar tanto bloques grandes como pequeños, pero solo apilas los pequeños sobre los grandes, no al revés. Java convierte los tipos para que funcionen juntos.
Crearemos podemos ver un code de ejemplo aquí [MainCoercion](src/main/java/org/fractal/MainCoercion.java).

En este ejemplo vemos lo siguiente:

- **Conversión automática**: Java convierte automáticamente el int (un número entero) en un double (un número decimal) para que puedan funcionar juntos. Esto se llama coerción.

### Polimorfismo Paramétrico (Parametric Polymorphism)
**¿Qué es?**
Imagina que tienes una caja mágica que puede contener cualquier tipo de juguete, ya sea un coche, un avión, o un barco. No importa qué tipo de juguete pongas en la caja, siempre se comportará como el tipo de juguete que contiene. Este es el polimorfismo paramétrico, donde puedes usar un tipo de dato genérico que funciona con diferentes tipos.
Crearemos podemos ver un code de ejemplo aquí [MainCaja](src/main/java/org/fractal/MainCaja.java).

En este ejemplo vemos lo siguiente:

- **Clase Caja<T>**: Aquí T es un tipo de dato genérico. Puede ser cualquier tipo de objeto. La caja puede contener diferentes tipos de cosas, como un String (texto) o un Integer (número).
- **Polimorfismo Paramétrico**: Te permite escribir código que funciona con cualquier tipo de dato.
