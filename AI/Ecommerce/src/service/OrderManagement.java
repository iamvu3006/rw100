package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Order;
import model.Product;

public class OrderManagement {
    private final List<Order> orders = new ArrayList<>();

    public Order create(int id, Customer customer, List<Product> products) {
        if (orders.stream().anyMatch(order -> order.getId() == id)) {
            throw new IllegalArgumentException("Ma don hang da ton tai.");
        }
        Order order = new Order(id, customer, products);
        orders.add(order);
        return order;
    }

    public List<Order> getAll() { return List.copyOf(orders); }

    public List<Order> findByCustomer(String keyword) {
        String normalized = keyword.trim().toLowerCase();
        return orders.stream().filter(order -> {
            Customer customer = order.getCustomer();
            return customer.getName().toLowerCase().contains(normalized)
                    || customer.getEmail().toLowerCase().contains(normalized);
        }).toList();
    }

    public void exportTo(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("DANH SACH DON HANG");
        orders.forEach(order -> lines.add(order + System.lineSeparator()));
        Files.write(path, lines);
    }
}
