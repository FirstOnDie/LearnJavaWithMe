# **📌 Patrones de Diseño y Arquitectura en Java**

Los **patrones de diseño y arquitectura** son soluciones reutilizables para problemas comunes en el desarrollo de software. Son guías que ayudan a escribir código más **estructurado, flexible y mantenible**.

📌 **Diferencias clave:**  
✔ **Patrones de Diseño** → Se enfocan en problemas a nivel de código, cómo organizar clases y objetos.  
✔ **Patrones de Arquitectura** → Se enfocan en la estructura global del sistema.

---  

## **📌 1️⃣ Patrones de Diseño**

Los **patrones de diseño** son soluciones probadas para problemas comunes en la programación orientada a objetos.

💡 **Metáfora**: Si construir una casa es hacer un software, los patrones de diseño serían **los planos para escaleras, ventanas y puertas**. No construyen la casa completa, pero resuelven problemas específicos de manera eficiente.

📌 **Categorías de Patrones de Diseño:**

### 🔹 **Patrones Creacionales**
✔ **Enfocados en la creación eficiente de objetos.**
- 🏗️ [Patrón Singleton](Singleton.md) → Garantiza que haya solo una instancia de una clase.
- 🏭 [Patrón Factory](Factory.md) → Facilita la creación de objetos sin especificar su clase concreta.
- 📝 [Patrón Prototype](Prototype.md) → Permite clonar objetos sin acoplarse a sus clases.
- 🔄 [Patrón Builder](Builder.md) → Construye objetos complejos paso a paso.

### 🔹 **Patrones Estructurales**
✔ **Se enfocan en la composición y estructura de clases y objetos.**
- 🔌 [Patrón Adapter](Adapter.md) → Permite la compatibilidad entre interfaces incompatibles.
- 🎭 [Patrón Decorator](Decorator.md) → Agrega funcionalidad a objetos de manera flexible.
- 🏛️ [Patrón Facade](Facade.md) → Proporciona una interfaz simplificada a un sistema complejo.
- 🏠 [Patrón Composite](Composite.md) → Permite tratar objetos individuales y conjuntos de manera uniforme.
- ⚡ [Patrón Proxy](Proxy.md) → Controla el acceso a otro objeto.
- 🎭 [Patrón Flyweight](Flyweight.md) → Reduce el uso de memoria compartiendo objetos.

### 🔹 **Patrones de Comportamiento**
✔ **Definen cómo los objetos interactúan entre sí.**
- 🔄 [Patrón Strategy](Strategy.md) → Permite cambiar algoritmos en tiempo de ejecución.
- 🔗 [Patrón Chain of Responsibility](ChainOfResponsibility.md) → Pasa solicitudes a través de una cadena de manejadores.
- 📢 [Patrón Observer](Observer.md) → Un objeto notifica cambios a múltiples objetos suscritos.
- 📜 [Patrón Command](Command.md) → Encapsula solicitudes en objetos.
- 🎮 [Patrón State](State.md) → Permite que un objeto cambie su comportamiento según su estado.
- 🤝 [Patrón Mediator](Mediator.md) → Centraliza la comunicación entre objetos.
- 🛠️ [Patrón Template Method](TemplateMethod.md) → Define la estructura de un algoritmo sin implementarlo completamente.
- 🏛️ [Patrón Visitor](Visitor.md) → Agrega operaciones a objetos sin modificar sus clases.
- 🔙 [Patrón Memento](Memento.md) → Guarda el estado de un objeto para restaurarlo después.
- 🔡 [Patrón Interpreter](Interpreter.md) → Evalúa gramáticas y expresiones.
- 🚀 [Patrón Null Object](NullObject.md) → Evita valores nulos con implementaciones vacías.

---

## **📌 2️⃣ Patrones de Arquitectura**

Los **patrones de arquitectura** son modelos que definen la organización y estructura de un sistema.

💡 **Metáfora**: Si los patrones de diseño son los planos para elementos individuales de una casa (ventanas, escaleras), los patrones de arquitectura serían **los planos completos de la casa**, organizando habitaciones, tuberías y electricidad.

📌 **Categorías de Patrones de Arquitectura:**

### 🔹 **Patrones de Arquitectura de Aplicación**
✔ **Definen la estructura de una aplicación para hacerla escalable y mantenible.**
- 🏗️ [Patrón MVC](MVC.md) → Separa la lógica de negocio, la presentación y el control.
- 🗄️ [Patrón DAO](DAO.md) → Separa la lógica de acceso a datos del resto de la aplicación.
- 📦 [Patrón DTO](DTO.md) → Usa objetos de transferencia para optimizar la comunicación entre capas.
- 🎭 [Patrón Service Layer](ServiceLayer.md) → Centraliza la lógica de negocio en una capa de servicios.
- 📂 [Patrón Repository](Repository.md) → Encapsula la lógica de acceso a datos, permitiendo persistencia flexible.

### 🔹 **Patrones de Comunicación y Conectividad**
✔ **Definen cómo se comunican los componentes de un sistema.**
- 🔀 [Patrón Gateway](Gateway.md) → Actúa como punto de entrada para llamadas externas.
- 🏡 [Patrón Adapter](Adapter.md) → Adapta una interfaz para que sea compatible con otra.
- 🎭 [Patrón Facade](Facade.md) → Simplifica interacciones con un sistema complejo.

### 🔹 **Patrones de Arquitectura Distribuida**
✔ **Se utilizan en sistemas con múltiples servicios y microservicios.**
- 🔗 [Patrón Chain of Responsibility](ChainOfResponsibility.md) → Pasa solicitudes a través de múltiples manejadores.
- 🔀 [Patrón Command](Command.md) → Encapsula solicitudes en objetos.
- 🌐 [Patrón Proxy](Proxy.md) → Controla acceso a servicios remotos o costosos.
- 🔄 [Patrón Observer](ObserverArq.md) → Facilita la comunicación entre servicios con eventos.
- 🤝 [Patrón Mediator](Mediator.md) → Coordina la comunicación entre servicios.

---

## **📌 3️⃣ Diferencias Clave entre Patrones de Diseño y Arquitectura**

| Característica | Patrones de Diseño | Patrones de Arquitectura |
|--------------|------------------|------------------|
| **Propósito** | Resolver problemas específicos de implementación. | Definir la estructura global del sistema. |
| **Nivel** | Código y clases individuales. | Organización de módulos y componentes. |
| **Ejemplo** | `Factory`, `Singleton`, `Observer`. | `MVC`, `Repository`, `Gateway`. |

---