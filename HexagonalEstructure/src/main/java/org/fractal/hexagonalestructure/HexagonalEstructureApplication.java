package org.fractal.hexagonalestructure;

import org.fractal.hexagonalestructure.adapters.cli.CliBankingAdapter;
import org.fractal.hexagonalestructure.adapters.persistence.InMemoryAccountRepository;
import org.fractal.hexagonalestructure.core.service.BankingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HexagonalEstructureApplication {

    public static void main(String[] args) {

            InMemoryAccountRepository accountRepository = new InMemoryAccountRepository();
            BankingService bankingService = new BankingService(accountRepository);
            CliBankingAdapter cliBankingAdapter = new CliBankingAdapter(bankingService);

            cliBankingAdapter.run();
    }

}
