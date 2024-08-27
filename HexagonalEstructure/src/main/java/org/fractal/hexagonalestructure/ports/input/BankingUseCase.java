package org.fractal.hexagonalestructure.ports.input;

public interface BankingUseCase {
    void createAccount(String accountId);
    void deposit(String accountId, double amount);
    void withdraw(String accountId, double amount);
    double getBalance(String accountId);
}