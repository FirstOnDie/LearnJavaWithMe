package org.fractal.hexagonalestructure.adapters.cli;

import org.fractal.hexagonalestructure.ports.input.BankingUseCase;

import java.util.Scanner;

public class CliBankingAdapter {
    private final BankingUseCase bankingUseCase;

    public CliBankingAdapter(BankingUseCase bankingUseCase) {
        this.bankingUseCase = bankingUseCase;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Ingrese un comando (create/deposit/withdraw/balance/exit):");
            String command = scanner.nextLine();

            switch (command) {
                case "create":
                    System.out.println("Ingrese ID de la cuenta:");
                    String accountId = scanner.nextLine();
                    bankingUseCase.createAccount(accountId);
                    System.out.println("Cuenta creada.");
                    break;
                case "deposit":
                    System.out.println("Ingrese ID de la cuenta:");
                    accountId = scanner.nextLine();
                    System.out.println("Ingrese monto a depositar:");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    bankingUseCase.deposit(accountId, depositAmount);
                    System.out.println("Depósito realizado.");
                    break;
                case "withdraw":
                    System.out.println("Ingrese ID de la cuenta:");
                    accountId = scanner.nextLine();
                    System.out.println("Ingrese monto a retirar:");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    bankingUseCase.withdraw(accountId, withdrawAmount);
                    System.out.println("Retiro realizado.");
                    break;
                case "balance":
                    System.out.println("Ingrese ID de la cuenta:");
                    accountId = scanner.nextLine();
                    double balance = bankingUseCase.getBalance(accountId);
                    System.out.println("El balance es: " + balance);
                    break;
                case "exit":
                    scanner.close();
                    return;
                default:
                    System.out.println("Comando no reconocido.");
                    break;
            }
        }
    }
}