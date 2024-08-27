package org.fractal.dddestructure.domain.service;

import org.fractal.dddestructure.domain.model.Order;
import org.fractal.dddestructure.domain.model.Product;
import org.fractal.dddestructure.domain.repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addProductToOrder(String orderId, Product product) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            order = new Order(orderId);
        }
        order.addProduct(product);
        orderRepository.save(order);
    }

    public double calculateOrderTotal(String orderId) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        return order.calculateTotal();
    }
}