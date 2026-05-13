package frontend;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Main Menu ===");
            System.out.println("1. Department functions");
            System.out.println("2. Account functions");
            System.out.println("3. Position functions");
            System.out.println("4. Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    DepartmentFunction.run();
                    break;
                case "2":
                    AccountFunction.run();
                    break;
                case "3":
                    PositionFunction.run();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Chọn sai, chọn lại!");
            }
        }
    }
}