package com.vti.frontend;

import com.vti.backend.controller.DepartmentController;
import com.vti.entity.Department;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DepartmentFunction {
    private static Scanner scanner = new Scanner(System.in);

    //khởi tạo đối tượng Controller
    private DepartmentController departmentController = new DepartmentController();

    public void run() throws ClassNotFoundException {
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
                    List<Department> departments = departmentController.findAllDepartment();
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

    private void insertDepartment() throws ClassNotFoundException {
        System.out.println("Nhập tên phòng ban mới:");
        String name = scanner.nextLine();
        Department department = new Department();
        department.setName(name);
        boolean result = departmentController.insertDepartment(department);
        if (result) {
            System.out.println("✓ Thêm phòng ban thành công!");
        } else {
            System.out.println("✗ Thêm phòng ban thất bại!");
        }
    }

    private void updateDepartment() throws ClassNotFoundException {
        System.out.println("Nhập ID phòng ban cần update:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập tên phòng ban sau khi update:");
        String name = scanner.nextLine();
        
        Department department = new Department(id, name);
        boolean result = departmentController.updateDepartment(department);
        if (result) {
            System.out.println("✓ Update phòng ban thành công!");
        } else {
            System.out.println("✗ Update phòng ban thất bại!");
        }
    }

    private void deleteDepartment() throws ClassNotFoundException {
        System.out.println("Nhập ID phòng ban cần xóa:");
        int id = Integer.parseInt(scanner.nextLine());
        
        boolean result = departmentController.deleteDepartment(id);
        if (result) {
            System.out.println("✓ Xóa phòng ban thành công!");
        } else {
            System.out.println("✗ Xóa phòng ban thất bại!");
        }
    }

    private void findDepartmentByNameAndId() throws ClassNotFoundException {
        System.out.println("Nhập ID cần tìm (nhập 0 để bỏ qua):");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập tên phòng ban cần tìm (nhập để bỏ qua):");
        String name = scanner.nextLine();
        
        // Lấy tất cả phòng ban rồi filter theo điều kiện
        List<Department> allDepartments = departmentController.findAllDepartment();
        List<Department> result = allDepartments.stream()
            .filter(dept -> (id == 0 || dept.getId() == id) && (name.isEmpty() || dept.getName().contains(name)))
            .collect(Collectors.toList());
        
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy phòng ban phù hợp!");
        } else {
            showDepartment(result);
        }
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
}