package model;

public class Customer {
    private final int id;
    private final String name;
    private final String email;

    public Customer(int id, String name, String email) {
        if (id <= 0 || name == null || name.isBlank() || email == null
                || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Thong tin khach hang hoac email khong hop le.");
        }
        this.id = id;
        this.name = name.trim();
        this.email = email.trim();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("#%d - %s <%s>", id, name, email);
    }
}
