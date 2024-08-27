package org.fractal.dddestructure.domain.repository;
import org.fractal.dddestructure.domain.model.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {
    private Map<String, Order> orders = new HashMap<>();

    public void save(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public Order findById(String orderId) {
        return orders.get(orderId);
    }
}