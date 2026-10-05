package service;

public class CalculatorService {
    public double calculate(double first, double second, char operator) {
        return switch (operator) {
            case '+' -> first + second;
            case '-' -> first - second;
            case '*' -> first * second;
            case '/' -> {
                if (second == 0) throw new IllegalArgumentException("Khong the chia cho 0.");
                yield first / second;
            }
            default -> throw new IllegalArgumentException("Phep toan khong duoc ho tro.");
        };
    }
}
