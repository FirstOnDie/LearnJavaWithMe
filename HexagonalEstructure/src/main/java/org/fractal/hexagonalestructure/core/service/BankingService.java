package org.fractal.hexagonalestructure.core.service;

import org.fractal.hexagonalestructure.core.domain.BankAccount;
import org.fractal.hexagonalestructure.ports.input.BankingUseCase;
import org.fractal.hexagonalestructure.ports.output.AccountRepository;

public class BankingService implements BankingUseCase {
    private final AccountRepository accountRepository;

    public BankingService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(String accountId) {
        BankAccount account = new BankAccount(accountId);
        accountRepository.save(account);
    }

    @Override
    public void deposit(String accountId, double amount) {
        BankAccount account = accountRepository.findById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        account.deposit(amount);
        accountRepository.save(account);
    }

    @Override
    public void withdraw(String accountId, double amount) {
        BankAccount account = accountRepository.findById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        account.withdraw(amount);
        accountRepository.save(account);
    }

    @Override
    public double getBalance(String accountId) {
        BankAccount account = accountRepository.findById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        return account.getBalance();
    }
}