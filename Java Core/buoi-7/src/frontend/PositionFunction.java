package frontend;

import backend.QLPosition;
import entity.Position;

import java.util.List;
import java.util.Scanner;

public class PositionFunction {
    private static Scanner scanner = new Scanner(System.in);

    public static void run() throws ClassNotFoundException {
        while (true) {
            System.out.println("=== Mời bạn chọn chức năng (Position) ===");
            System.out.println("1. Xem ds position");
            System.out.println("2. Thêm mới position");
            System.out.println("3. Update position");
            System.out.println("4. Xóa position");
            System.out.println("5. Tìm kiếm position");
            System.out.println("6. Thoát");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    List<Position> positions = QLPosition.showAllPosition();
                    showPosition(positions);
                    break;
                case "2":
                    insertPosition();
                    break;
                case "3":
                    updatePosition();
                    break;
                case "4":
                    deletePosition();
                    break;
                case "5":
                    findPositionByName();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Chọn sai, chọn lại!");

            }
        }
    }

    public static void insertPosition () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên position mới (VD: DEV, TEST, PM, SCRUM_MASTER):");
        String name = scanner.nextLine();
        boolean check = QLPosition.createPosition(name);
        if (check) {
            System.out.println("Thêm position thành công");
        } else {
            System.out.println("Thêm position thất bại");
        }
    }

    public static void deletePosition () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên position cần xóa:");
        String name = scanner.nextLine();
        boolean check = QLPosition.deletePosition(name);
        if (check) {
            System.out.println("Xóa position thành công");
        } else {
            System.out.println("Xóa position thất bại");
        }
    }

    public static void updatePosition () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập ID position cần update:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nhập tên position sau khi update:");
        String name = scanner.nextLine();
        boolean check = QLPosition.updatePosition(id, name);
        if (check) {
            System.out.println("Update position thành công");
        } else {
            System.out.println("Update position thất bại");
        }
    }

    public static void findPositionByName() throws ClassNotFoundException {
        System.out.println("Nhập tên position cần tìm: ");
        String name = scanner.nextLine();
        List<Position> positions = QLPosition.findByPositionName(name);
        showPosition(positions);
    }

    private static void showPosition(List<Position> positions) {
        System.out.println("+--------------+--------------------+");
        System.out.printf("| %-12s | %-18s |%n", "ID", "Position Name");
        System.out.println("+--------------+--------------------+");
        for (Position position : positions) {
            System.out.printf("| %-12d | %-18s |%n", position.getId(), position.getName());
        }
        System.out.println("+--------------+--------------------+");
    }
}

