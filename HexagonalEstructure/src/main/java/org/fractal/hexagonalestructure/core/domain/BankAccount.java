package org.fractal.hexagonalestructure.core.domain;

public class BankAccount {
    private String accountId;
    private double balance;

    public BankAccount(String accountId) {
        this.accountId = accountId;
        this.balance = 0.0;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }
        if (this.balance < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance -= amount;
    }
}
