package frontend;

import backend.QLDepartment;
import entity.Department;

import java.util.List;
import java.util.Scanner;

public class DepartmentFunction {
    private static Scanner scanner = new Scanner(System.in);

    public static void run() throws ClassNotFoundException {
        while (true) {
            System.out.println("=== Mời bạn chọn chức năng ===");
            System.out.println("1. Xem ds phòng ban");
            System.out.println("2. Thêm mới phòng ban");
            System.out.println("3. Update phòng ban");
            System.out.println("4. Xóa phòng ban");
            System.out.println("5. Tìm kiếm phòng ban");
            System.out.println("6. Thoát");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    List<Department> departments = QLDepartment.findAllDepartment();
                    showDepartment(departments);
                    break;
                case "2":
                    insertDepartment();
                    break;
                case "3":
                    updateDepartment();
                    break;
                case "4":
                    deleteDepartment();
                    break;
                case "5":
                    findDepartmentByNameAndId();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Chọn sai, chọn lại!");

            }
        }
    }

    public static void insertDepartment () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên phòng ban mới:");
        String name = scanner.nextLine();
        boolean check = QLDepartment.createDepartment(name);
        if (check) {
            System.out.println("Thêm phòng ban thành công");
        } else {
            System.out.println("Thêm phòng ban thất bại");
        }
    }

    public static void deleteDepartment () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên phòng ban cần xóa:");
        String name = scanner.nextLine();
        boolean check = QLDepartment.deleteDepartment(name);
        if (check) {
            System.out.println("Xóa phòng ban thành công");
        } else {
            System.out.println("Xóa phòng ban thất bại");
        }
    }

    public static void updateDepartment () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập ID phòng ban cần update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc bỏ dòng mới sau khi đọc số nguyên

        System.out.println("Nhập tên phòng ban sau khi update:");
        String name = scanner.nextLine();
        boolean check = QLDepartment.updateDepartment(id, name);
        if (check) {
            System.out.println("Update phòng ban thành công");
        } else {
            System.out.println("Update phòng ban thất bại");
        }
    }

    public static void findDepartmentByNameAndId() throws ClassNotFoundException {
        System.out.println("Nhập ID cần tìm: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nhập tên phòng ban cần tìm: ");
        String name = scanner.nextLine();
        java.util.List<Department> departments = QLDepartment.findByNameAndId(name, id);
        showDepartment(departments);
    }

    private static void showDepartment(List<Department> departments) {
        System.out.println("+--------------+------------------------------+");
        System.out.printf("| %-12s | %-28s |%n", "ID", "Department Name");
        System.out.println("+--------------+------------------------------+");
        for (Department department : departments) {
            System.out.printf("| %-12d | %-28s |%n", department.getId(), department.getName());
        }
        System.out.println("+--------------+------------------------------+");
    }

    private static void showDepartmentWithTotalMember(List<Department> departments, List<Integer> totalMembers) {
        System.out.println("+--------------+------------------------------+--------------+");
        System.out.printf("| %-12s | %-28s | %-12s |%n", "ID", "Department Name", "TotalMember");
        System.out.println("+--------------+------------------------------+--------------+");
        for (int i = 0; i < departments.size(); i++) {
            Department department = departments.get(i);
            Integer totalMember = totalMembers.get(i);
            System.out.printf("| %-12d | %-28s | %-12d |%n", department.getId(), department.getName(), totalMember);
        }
        System.out.println("+--------------+------------------------------+--------------+");
    }
}