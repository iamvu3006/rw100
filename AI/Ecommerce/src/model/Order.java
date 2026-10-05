package model;

import java.util.List;
import java.util.Locale;

public class Order {
    private final int id;
    private final Customer customer;
    private final List<Product> products;

    public Order(int id, Customer customer, List<Product> products) {
        if (id <= 0 || customer == null || products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Don hang phai co khach hang va it nhat mot san pham.");
        }
        this.id = id;
        this.customer = customer;
        this.products = List.copyOf(products);
    }

    public int getId() { return id; }
    public Customer getCustomer() { return customer; }
    public List<Product> getProducts() { return products; }

    public double getTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(String.format("Don hang #%d | %s%n", id, customer));
        products.forEach(product -> result.append("  - ").append(product).append(System.lineSeparator()));
        result.append(String.format(Locale.US, "  Tong tien: %,.2f", getTotalPrice()));
        return result.toString();
    }
}
