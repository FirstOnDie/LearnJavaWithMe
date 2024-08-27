package org.fractal.hexagonalestructure.adapters.persistence;

import org.fractal.hexagonalestructure.core.domain.BankAccount;
import org.fractal.hexagonalestructure.ports.output.AccountRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAccountRepository implements AccountRepository {
    private final Map<String, BankAccount> database = new HashMap<>();

    @Override
    public void save(BankAccount account) {
        database.put(account.getAccountId(), account);
    }

    @Override
    public BankAccount findById(String accountId) {
        return database.get(accountId);
    }
}
