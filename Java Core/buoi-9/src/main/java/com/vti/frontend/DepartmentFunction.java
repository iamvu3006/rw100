package com.vti.frontend;

import com.vti.backend.controller.DepartmentController;
import com.vti.entity.Department;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DepartmentFunction {
    private static Scanner scanner = new Scanner(System.in);
    // khoi tao doi tuong controller
    private DepartmentController departmentController = new DepartmentController();

    public void run() {
        while (true) {
            System.out.println("=== Mời bạn chọn chức năng ===");
            System.out.println("1. Xem ds phòng ban");
            System.out.println("2. Thêm mới phòng ban");
            System.out.println("3. Update phòng ban");
            System.out.println("4. Xóa phòng ban");
            System.out.println("5. Tìm kiếm phòng ban");
            System.out.println("6. Import file CSV");
            System.out.println("7. Thoát");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    // lay ds phong ban tu controller
                    List<Department> departments = departmentController.findAll();
                    this.showDepartment(departments);
                    break;
                case "2":
                    this.insertDepartment();
                    break;
                case "3":
                    this.updateDepartment();
                    break;
                case "4":
                    this.deleteDepartment();
                    break;
                case "6":
                    this.importDepartmentFromCSV();
                    break;
                default:
                    System.out.println("Chọn sai, chọn lại!");
            }
        }
    }

    // đọc du lieu tu file CSV và luu vào DB
    public void importDepartmentFromCSV() {
        System.out.println("=== Import file CSV ===");
        System.out.println("Mời bạn nhập đường dẫn đến file:");
        //C:\Users\Admin\Desktop\rw100\csv\input_department.csv
        String pathName = scanner.nextLine();
        String message = departmentController.importDepartmentFromCSV(pathName);
        System.out.println(message);
    }

    public void deleteDepartment() {
        System.out.println("Nhập ID phòng ban cần xóa: ");
        int id;
        while (true) {
            id = scanner.nextInt();
            scanner.nextLine();
            // kiem tra xem id co ton tai ko
            if (id <= 0 ) {
                System.out.println("Nhập lai ID: ");
                continue;
            }
            if (!departmentController.checkExistID(id)) {
                System.out.println("ID nay ko ton tai, nhap lai: ");
                continue;
            }
            break;
        }
        boolean check = departmentController.delete(id);
        if (check) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Xóa không thành công");
        }
    }

    public void insertDepartment() {
        String name = "";
        System.out.println("Nhập tên phòng ban: ");
        while (true) {
            name = scanner.nextLine();
            // kiem tra xem ten có bị null hay empty hay ko
            if (Objects.isNull(name) || name.trim().isEmpty()) {
                System.out.println("Nhập lại tên: ");
                continue;
            } else {
                // kiem tra xem ten da ton tai chua
                if (departmentController.checkExistNameAndIdNot(name, null)) {
                    System.out.println("Tên này đã được sử dụng, Nhập lại: ");
                    continue;
                }
                break;
            }
        }


        boolean check = departmentController.create(name);
        if (check) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Thêm không thành công");
        }
    }

    public void showDepartment(List<Department> departments) {
        System.out.println("+-----+--------------------+");
        System.out.printf("|%5s|%20s|\n", "ID", "Tên phòng ban");
        System.out.println("+-----+--------------------+");
        for (Department department : departments) {
            System.out.printf("|%5s|%20s|\n", department.getId(), department.getName());
        }
        if (departments.size() == 0) {
            System.out.println("Không tìm thấy");
        }
        System.out.println("+-----+--------------------+");
    }

    public void updateDepartment() {
        System.out.println("Nhập ID phòng ban cần sửa: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        // kieem tra xem id hop le va ton tai

        // kierm tra xem id co hop le va ton tai ko
        System.out.println("Nhập tên phòng ban cần sửa: ");
        String name= "";
        while (true) {
            name = scanner.nextLine();
            // kiem tra xem ten có bị null hay empty hay ko
            if (Objects.isNull(name) || name.trim().isEmpty()) {
                System.out.println("Nhập lại tên: ");
                continue;
            } else {
                // kiem tra xem ten da ton tai chua
                if (departmentController.checkExistNameAndIdNot(name, id)) {
                    System.out.println("Tên này đã được sử dụng, Nhập lại: ");
                    continue;
                }
                break;
            }
        }
        boolean check = departmentController.update(id, name);
        if (check) {
            System.out.println("Update thành công");
        } else {
            System.out.println("Update không thành công");
        }
    }
}
