package ui;

import service.CalculatorService;
import service.OrderManagement;
import service.ProductManagement;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Category;
import model.Customer;
import model.Order;
import model.Product;

public class ConsoleApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final ProductManagement productManagement = new ProductManagement();
    private final OrderManagement orderManagement = new OrderManagement();
    private final CalculatorService calculatorService = new CalculatorService();

    public void run() {
        int choice;
        do {
            showMenu();
            choice = readInt(scanner, "Chon chuc nang: ");
            switch (choice) {
                case 1 -> manageProducts();
                case 2 -> manageOrders();
                case 3 -> calculator();
                case 0 -> System.out.println("\nTam biet! Hen gap lai.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

    private void showMenu() {
        System.out.println("\n+--------------------------------------+");
        System.out.println("|       E-COMMERCE CONSOLE APP         |");
        System.out.println("+--------------------------------------+");
        System.out.println("| 1. Quan ly san pham                  |");
        System.out.println("| 2. Quan ly don hang                  |");
        System.out.println("| 3. May tinh don gian                 |");
        System.out.println("| 0. Thoat                             |");
        System.out.println("+--------------------------------------+");
    }

    private void manageProducts() {
        int choice;
        do {
            System.out.println("\n--- QUAN LY SAN PHAM ---");
            System.out.println("1. Them  2. Xoa  3. Tim theo gia  4. Top 3 dat nhat  5. Xuat TXT  0. Quay lai");
            choice = readInt(scanner, "Chon: ");
            switch (choice) {
                case 1 -> addProduct();
                case 2 -> System.out.println(productManagement.removeById(readInt(scanner, "ID can xoa: "))
                        ? "Da xoa san pham." : "Khong tim thay san pham.");
                case 3 -> {
                    double min = readDouble(scanner, "Gia tu: ");
                    double max = readDouble(scanner, "Gia den: ");
                    printProducts(productManagement.findByPriceRange(Math.min(min, max), Math.max(min, max)));
                }
                case 4 -> printProducts(productManagement.topThreeMostExpensive());
                case 5 -> exportProducts();
                case 0 -> { }
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

    private void addProduct() {
        try {
            int id = readInt(scanner, "ID: ");
            String name = readNonEmpty(scanner, "Ten: ");
            double price = readDouble(scanner, "Gia: ");
            Category category = readCategory();
            System.out.println(productManagement.add(new Product(id, name, price, category))
                    ? "Da them san pham." : "ID da ton tai.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Loi: " + exception.getMessage());
        }
    }

    private void manageOrders() {
        int choice;
        do {
            System.out.println("\n--- QUAN LY DON HANG ---");
            System.out.println("1. Tao don  2. Danh sach don  3. Tong tien  4. Tim theo khach hang  5. Xuat TXT  0. Quay lai");
            choice = readInt(scanner, "Chon: ");
            switch (choice) {
                case 1 -> createOrder();
                case 2 -> printOrders(orderManagement.getAll());
                case 3 -> orderManagement.getAll().forEach(order ->
                        System.out.println("Don #" + order.getId() + ": " + order.getTotalPrice()));
                case 4 -> printOrders(orderManagement.findByCustomer(readNonEmpty(scanner, "Ten/email: ")));
                case 5 -> exportOrders();
                case 0 -> { }
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

    private void createOrder() {
        if (productManagement.getAll().isEmpty()) {
            System.out.println("Can co san pham truoc khi tao don.");
            return;
        }
        try {
            Customer customer = new Customer(readInt(scanner, "ID khach hang: "),
                    readNonEmpty(scanner, "Ten khach hang: "),
                    readNonEmpty(scanner, "Email: "));
            List<Product> selected = new ArrayList<>();
            System.out.println("Nhap ID san pham, nhap 0 de ket thuc:");
            int productId;
            while ((productId = readInt(scanner, "ID san pham: ")) != 0) {
                Product product = productManagement.findById(productId);
                if (product == null) System.out.println("Khong tim thay san pham.");
                else selected.add(product);
            }
            System.out.println("Da tao: " + orderManagement.create(readInt(scanner, "ID don hang: "), customer, selected));
        } catch (IllegalArgumentException exception) {
            System.out.println("Loi: " + exception.getMessage());
        }
    }

    private void calculator() {
        try {
            double first = readDouble(scanner, "So thu nhat: ");
            char operator = readNonEmpty(scanner, "Phep toan (+ - * /): ").charAt(0);
            double second = readDouble(scanner, "So thu hai: ");
            System.out.println("Ket qua = " + calculatorService.calculate(first, second, operator));
        } catch (IllegalArgumentException exception) {
            System.out.println("Loi: " + exception.getMessage());
        }
    }

    private Category readCategory() {
        Category[] categories = Category.values();
        for (int i = 0; i < categories.length; i++) System.out.println((i + 1) + ". " + categories[i]);
        int index = readInt(scanner, "Danh muc: ");
        if (index < 1 || index > categories.length) throw new IllegalArgumentException("Danh muc khong hop le.");
        return categories[index - 1];
    }

    private void printProducts(List<Product> products) {
        if (products.isEmpty()) System.out.println("Khong co du lieu.");
        else products.forEach(System.out::println);
    }

    private void printOrders(List<Order> orders) {
        if (orders.isEmpty()) System.out.println("Khong co du lieu.");
        else orders.forEach(order -> System.out.println(order + "\n"));
    }

    private void exportProducts() {
        try { productManagement.exportTo(Path.of("products.txt")); System.out.println("Da xuat products.txt."); }
        catch (Exception exception) { System.out.println("Khong the xuat file: " + exception.getMessage()); }
    }

    private void exportOrders() {
        try { orderManagement.exportTo(Path.of("orders.txt")); System.out.println("Da xuat orders.txt."); }
        catch (Exception exception) { System.out.println("Khong the xuat file: " + exception.getMessage()); }
    }

    static int readInt(Scanner sc, String msg) {
        while (true) try { System.out.print(msg); return Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException exception) { System.out.println("Vui long nhap so nguyen."); }
    }

    static double readDouble(Scanner sc, String msg) {
        while (true) try { System.out.print(msg); return Double.parseDouble(sc.nextLine().trim()); }
        catch (NumberFormatException exception) { System.out.println("Vui long nhap so thuc."); }
    }

    static String readNonEmpty(Scanner sc, String msg) {
        while (true) { System.out.print(msg); String value = sc.nextLine().trim(); if (!value.isEmpty()) return value; System.out.println("Khong duoc de trong."); }
    }
}
