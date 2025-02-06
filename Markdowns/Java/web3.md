# **📌 Java y Web3** 🚀🔗

La integración de **Java con Blockchain** está ganando popularidad en aplicaciones empresariales, permitiendo interactuar con redes como **Ethereum, Hyperledger Fabric y Corda**. A través de herramientas como **Web3j**, podemos conectar Java con contratos inteligentes en **Ethereum** y otras plataformas descentralizadas.

---

## **🔹 ¿Por qué usar Java en Blockchain?**
✅ **Confiabilidad y rendimiento:** Java es seguro, escalable y usado en entornos empresariales.  
✅ **Interoperabilidad:** Java puede integrarse con APIs blockchain como **Web3j, Hyperledger Fabric SDK y Corda SDK**.  
✅ **Smart Contracts en Java:** Algunas blockchains como **Hyperledger Fabric** permiten escribir contratos inteligentes en Java.

---

# **📌 Conectando Java con Ethereum usando Web3j**
### **🔹 ¿Qué es Web3j?**
**Web3j** es una biblioteca que permite interactuar con **Ethereum** desde Java, incluyendo:  
✔ Conectar con la red Ethereum (Mainnet, Testnet o privada).  
✔ Enviar transacciones y consultar balances.  
✔ Interactuar con contratos inteligentes.

📌 **Ejemplo: Obtener el número del último bloque en Ethereum**
```java
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.http.HttpService;

public class EthereumClient {
    public static void main(String[] args) throws Exception {
        // Conectar con la red Ethereum (usando Infura o un nodo propio)
        Web3j web3 = Web3j.build(new HttpService("https://mainnet.infura.io/v3/TU_INFURA_API"));

        // Obtener el último número de bloque
        EthBlockNumber blockNumber = web3.ethBlockNumber().send();
        System.out.println("Último bloque: " + blockNumber.getBlockNumber());
    }
}
```
✅ **Esto conecta una aplicación Java con Ethereum y obtiene el último bloque.**

📌 **Dependencia en Maven:**
```xml
<dependency>
    <groupId>org.web3j</groupId>
    <artifactId>core</artifactId>
    <version>4.9.4</version>
</dependency>
```

---

# **📌 Consultar el saldo de una billetera en Ethereum**
Podemos usar **Web3j** para obtener el saldo de cualquier cuenta en Ethereum.

📌 **Ejemplo: Consultar el balance de ETH de una dirección**
```java
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigDecimal;

public class EthereumBalance {
    public static void main(String[] args) throws Exception {
        Web3j web3 = Web3j.build(new HttpService("https://mainnet.infura.io/v3/TU_INFURA_API"));

        // Dirección de la billetera Ethereum
        String direccion = "0x742d35Cc6634C0532925a3b844Bc454e4438f44e";

        // Obtener saldo en Wei (unidad mínima de Ethereum)
        EthGetBalance balance = web3.ethGetBalance(direccion, org.web3j.protocol.core.DefaultBlockParameterName.LATEST).send();

        // Convertir de Wei a ETH
        BigDecimal saldoETH = Convert.fromWei(balance.getBalance().toString(), Convert.Unit.ETHER);

        System.out.println("Saldo de la cuenta: " + saldoETH + " ETH");
    }
}
```
✅ **Esto obtiene el saldo de una billetera en Ethereum y lo muestra en ETH.**

---

# **📌 Interactuar con Contratos Inteligentes desde Java**
Podemos usar Web3j para llamar funciones de **Smart Contracts en Solidity**.

📌 **Ejemplo: Llamar a un contrato inteligente**
```java
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

public class SmartContractInteraction {
    public static void main(String[] args) throws Exception {
        Web3j web3 = Web3j.build(new HttpService("https://mainnet.infura.io/v3/TU_INFURA_API"));

        // Dirección del contrato inteligente en Ethereum
        String contrato = "0x1234567890abcdef1234567890abcdef12345678";

        // Crear una instancia del contrato
        MiContrato contratoInstancia = MiContrato.load(contrato, web3, new TransactionManager(), new DefaultGasProvider());

        // Llamar a una función del contrato
        TransactionReceipt respuesta = contratoInstancia.miFuncion().send();
        System.out.println("Resultado: " + respuesta.getStatus());
    }
}
```
✅ **Esto permite llamar funciones de contratos inteligentes directamente desde Java.**

---

# **📌 Smart Contracts en Java con Hyperledger Fabric**
📌 **Hyperledger Fabric** permite escribir contratos inteligentes en **Java**, a diferencia de Ethereum que usa **Solidity**.

📌 **Ejemplo: Contrato Inteligente en Hyperledger Fabric**
```java
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;

@Contract(name = "MiContrato")
public class MiContrato implements ContractInterface {

    @Transaction()
    public String guardarDato(Context ctx, String clave, String valor) {
        ctx.getStub().putStringState(clave, valor);
        return "Dato guardado correctamente";
    }

    @Transaction()
    public String obtenerDato(Context ctx, String clave) {
        return ctx.getStub().getStringState(clave);
    }
}
```
✅ **Esto define un contrato inteligente en Java que almacena datos en la Blockchain.**

---

# **📌 Comparación: Web3j vs Hyperledger Fabric SDK vs Corda**
| **Característica** | **Ethereum (Web3j)** | **Hyperledger Fabric (Java SDK)** | **Corda** |
|-------------------|-----------------|-------------------------|--------|
| **Modelo** | Blockchain pública | Blockchain privada | Blockchain híbrida |
| **Contratos Inteligentes** | Solidity | Java, Go, Node.js | Kotlin, Java |
| **Casos de uso** | Criptomonedas, DeFi | Empresas, Finanzas, Supply Chain | Finanzas, contratos legales |

---

# **📌 ¿Cuándo usar Java en Blockchain?**
✅ **Cuando necesitas integrarte con Ethereum (Web3j).**  
✅ **Si quieres programar contratos inteligentes en Hyperledger Fabric usando Java.**  
✅ **Para desarrollar aplicaciones financieras y empresariales en Corda.**  
✅ **Si deseas consultar información en Blockchain desde aplicaciones Java empresariales.**

---

# **📌 Conclusión**
🔥 **Java es una excelente opción para interactuar con Blockchain**, gracias a herramientas como **Web3j, Hyperledger Fabric SDK y Corda SDK**.  
🚀 **Puedes crear aplicaciones financieras, contratos inteligentes y plataformas descentralizadas con la potencia de Java.**  
🔗 **La combinación de Java + Blockchain abre nuevas oportunidades en Web3 y la descentralización.**
