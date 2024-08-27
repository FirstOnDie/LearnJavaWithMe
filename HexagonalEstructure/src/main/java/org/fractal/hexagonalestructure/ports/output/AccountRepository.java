package org.fractal.hexagonalestructure.ports.output;

import org.fractal.hexagonalestructure.core.domain.BankAccount;

public interface AccountRepository {
    void save(BankAccount account);
    BankAccount findById(String accountId);
}
