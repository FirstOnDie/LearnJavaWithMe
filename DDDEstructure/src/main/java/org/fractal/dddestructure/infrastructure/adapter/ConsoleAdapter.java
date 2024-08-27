package org.fractal.dddestructure.infrastructure.adapter;
import org.fractal.dddestructure.domain.model.Product;
import org.fractal.dddestructure.domain.service.OrderService;

import java.util.Scanner;

public class ConsoleAdapter {
    private final OrderService orderService;

    public ConsoleAdapter(OrderService orderService) {
        this.orderService = orderService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Ingrese una acción (add/show/exit):");
            String action = scanner.nextLine();

            switch (action) {
                case "add":
                    System.out.println("Ingrese ID del pedido:");
                    String orderId = scanner.nextLine();
                    System.out.println("Ingrese ID del producto:");
                    String productId = scanner.nextLine();
                    System.out.println("Ingrese nombre del producto:");
                    String productName = scanner.nextLine();
                    System.out.println("Ingrese precio del producto:");
                    double price = Double.parseDouble(scanner.nextLine());

                    Product product = new Product(productId, productName, price);
                    orderService.addProductToOrder(orderId, product);
                    break;

                case "show":
                    System.out.println("Ingrese ID del pedido para ver el total:");
                    orderId = scanner.nextLine();
                    double total = orderService.calculateOrderTotal(orderId);
                    System.out.println("El total del pedido " + orderId + " es: " + total);
                    break;

                case "exit":
                    scanner.close();
                    return;

                default:
                    System.out.println("Acción no reconocida.");
                    break;
            }
        }
    }
}