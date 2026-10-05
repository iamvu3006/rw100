package model;

import java.util.Locale;

public class Product {
    private final int id;
    private final String name;
    private final double price;
    private final Category category;

    public Product(int id, String name, double price, Category category) {
        if (id <= 0 || name == null || name.isBlank() || price < 0 || category == null) {
            throw new IllegalArgumentException("Thong tin san pham khong hop le.");
        }
        this.id = id;
        this.name = name.trim();
        this.price = price;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public Category getCategory() { return category; }

    @Override
    public String toString() {
        return String.format(Locale.US, "#%d | %-20s | %,10.2f | %s", id, name, price, category);
    }
}
