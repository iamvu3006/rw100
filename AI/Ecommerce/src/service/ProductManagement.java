package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.Product;

public class ProductManagement {
    private final List<Product> products = new ArrayList<>();

    public boolean add(Product product) {
        if (findById(product.getId()) != null) return false;
        products.add(product);
        return true;
    }

    public boolean removeById(int id) {
        return products.removeIf(product -> product.getId() == id);
    }

    public Product findById(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }

    public List<Product> findByPriceRange(double min, double max) {
        return products.stream().filter(p -> p.getPrice() >= min && p.getPrice() <= max).toList();
    }

    public List<Product> topThreeMostExpensive() {
        return products.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed()).limit(3).toList();
    }

    public List<Product> getAll() {
        return List.copyOf(products);
    }

    public void exportTo(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("DANH SACH SAN PHAM");
        products.forEach(product -> lines.add(product.toString()));
        Files.write(path, lines);
    }
}
