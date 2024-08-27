package org.fractal.dddestructure;

import org.fractal.dddestructure.domain.repository.OrderRepository;
import org.fractal.dddestructure.domain.service.OrderService;
import org.fractal.dddestructure.infrastructure.adapter.ConsoleAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DddEstructureApplication {

    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(orderRepository);
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(orderService);

        consoleAdapter.run();
    }

}
