# **📌 Patrón de Diseño Iterator en Java** 🔄📚

📌 **¿Qué es el Patrón Iterator?**  
El **patrón Iterator** es una técnica de diseño que nos permite recorrer una colección de elementos **uno por uno**, sin preocuparnos por **cómo están organizados internamente**.

💡 **Ejemplo en la vida real:**  
Imagina que estás en una **biblioteca** 📚 y quieres ver los libros en una estantería.
- En lugar de **sacar todos los libros a la vez** (lo que sería un desastre 😅), **usas una herramienta mágica** que te da un libro a la vez.
- Esta **herramienta mágica** es un **iterador**, que te permite recorrer la estantería **sin importar cómo están organizados los libros internamente**.

📌 **¿Para qué sirve el Patrón Iterator?**  
✅ Permite recorrer colecciones de datos de forma **ordenada y estructurada**.  
✅ Oculta la implementación interna de la colección (mayor encapsulamiento).  
✅ Facilita la creación de diferentes tipos de iteradores (ejemplo: recorrer solo libros de ficción).

---

# **📌 Cómo Funciona el Patrón Iterator** 🔄

📌 **Estructura del Patrón Iterator:**

1️⃣ **Interfaz `Iterator`** (Define cómo se recorre la colección).  
2️⃣ **Clase `BookIterator`** (Implementa la lógica de recorrido).  
3️⃣ **Interfaz `Aggregate`** (Define quién puede proporcionar un iterador).  
4️⃣ **Clase `Library`** (Almacena los elementos y genera el iterador).  
5️⃣ **Cliente** (Utiliza el iterador para recorrer los elementos).

💡 **Metáfora:**
- 📚 **Library** → La biblioteca donde están los libros.
- 🔄 **Iterator** → La herramienta mágica que te da los libros uno por uno.
- 🧑 **Cliente** → Tú, que usas el iterador para ver los libros.

---

# **📌 Implementación en Java** 🖥️

📌 **1️⃣ Crear la Clase `Book`**
```java
class Book {
    private String title;
    private String genre;
    private String id;

    public Book(String title, String genre, String id) {
        this.title = title;
        this.genre = genre;
        this.id = id;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ")";
    }
}
```
✅ **Clase que representa un libro con título y género.**

---

📌 **2️⃣ Crear la Interfaz `Iterator`**
```java
interface Iterator {
    boolean hasNext(); // ¿Hay más elementos?
    Object next(); // Obtener el siguiente elemento
}
```
✅ **Define cómo recorrer la colección:**  
✔ `hasNext()` → Verifica si hay más elementos.  
✔ `next()` → Devuelve el siguiente elemento.

---

📌 **3️⃣ Crear el `BookIterator` (Iterador Concreto)**
```java
import java.util.List;

class BookIterator implements Iterator {
    private List<Book> books;
    private int position = 0;

    public BookIterator(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean hasNext() {
        return position < books.size();
    }

    @Override
    public Object next() {
        return hasNext() ? books.get(position++) : null;
    }
}
```
✅ **Se encarga de recorrer la colección de libros.**

---

📌 **4️⃣ Crear la Interfaz `Aggregate`**
```java
interface Aggregate {
    Iterator createIterator();
}
```
✅ **Define un método para obtener un iterador.**

---

📌 **5️⃣ Crear `Library` (Agregador Concreto)**
```java
import java.util.ArrayList;
import java.util.List;

class Library implements Aggregate {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Iterator createIterator() {
        return new BookIterator(books);
    }
}
```
✅ **Se encarga de almacenar los libros y crear un iterador.**

---

📌 **6️⃣ Cliente: Recorrer la Biblioteca con el Iterador**
```java
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("1984", "Ficción", "1"));
        library.addBook(new Book("Drácula", "Terror", "2"));
        library.addBook(new Book("El principito", "Infantil", "3"));
        
        Iterator iterator = library.createIterator();
        
        while (iterator.hasNext()) {
            System.out.println("📖 " + iterator.next());
        }
    }
}
```
✅ **El cliente usa el iterador para obtener cada libro sin preocuparse por la estructura interna.**

---

# **📌 Explicación Paso a Paso** 📝

1️⃣ **Creamos una lista de libros en `Library`**.  
2️⃣ **Obtenemos un `BookIterator` desde `Library.createIterator()`**.  
3️⃣ **Usamos `hasNext()` para verificar si hay más libros**.  
4️⃣ **Usamos `next()` para obtener cada libro uno por uno**.

📌 **Salida esperada:**
```
📖 1984 (Ficción)
📖 Drácula (Terror)
📖 El principito (Infantil)
```

---

# **📌 Beneficios del Patrón Iterator** ✅

✔ **Encapsulamiento** → El cliente no necesita saber cómo se almacenan los elementos.  
✔ **Flexibilidad** → Podemos crear iteradores personalizados (ejemplo: solo libros de terror).  
✔ **Código limpio** → Separación de responsabilidades entre colección y recorrido.

---

# **📌 Extensión: Iterador de Libros por Género** 🎭

📌 **¿Qué pasa si queremos recorrer solo libros de un género específico?**

✅ **Creamos `GenreIterator` para filtrar por género.**

```java
class GenreIterator implements Iterator {
    private List<Book> books;
    private int position = 0;
    private String genre;

    public GenreIterator(List<Book> books, String genre) {
        this.books = books;
        this.genre = genre;
    }

    @Override
    public boolean hasNext() {
        while (position < books.size()) {
            if (books.get(position).toString().contains(genre)) {
                return true;
            }
            position++;
        }
        return false;
    }

    @Override
    public Object next() {
        return hasNext() ? books.get(position++) : null;
    }
}
```
📌 **Ahora podemos recorrer solo los libros de un género específico:**
```java
Iterator terrorIterator = new GenreIterator(library.getBooks(), "Terror");

while (terrorIterator.hasNext()) {
    System.out.println("📖 " + terrorIterator.next());
}
```
✅ **Salida esperada (solo libros de terror):**
```
📖 Drácula (Terror)
```

---