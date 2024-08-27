package org.fractal.dddestructure.domain.model;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private List<Product> products;

    public Order(String orderId) {
        this.orderId = orderId;
        this.products = new ArrayList<>();
    }

    public String getOrderId() {
        return orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public double calculateTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}