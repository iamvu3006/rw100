package com.vti.frontend;

import com.vti.backend.controller.DepartmentController;
import com.vti.entity.Department;
import com.vti.utils.ScannerUtils;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentFunction {
    // use ScannerUtils to centralize input validation

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

            String choice = ScannerUtils.inputString();
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
        String name = ScannerUtils.inputString();
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
        int id;
        while (true) {
            try {
                id = Integer.parseInt(ScannerUtils.inputString());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Giá trị phải là số nguyên. Vui lòng nhập lại.");
            }
        }
        System.out.println("Nhập tên phòng ban sau khi update:");
        String name = ScannerUtils.inputString();
        
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
        int id;
        while (true) {
            try {
                id = Integer.parseInt(ScannerUtils.inputString());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Giá trị phải là số nguyên. Vui lòng nhập lại.");
            }
        }
        
        boolean result = departmentController.deleteDepartment(id);
        if (result) {
            System.out.println("✓ Xóa phòng ban thành công!");
        } else {
            System.out.println("✗ Xóa phòng ban thất bại!");
        }
    }

    private void findDepartmentByNameAndId() throws ClassNotFoundException {
        System.out.println("Nhập ID cần tìm (nhập 0 để bỏ qua):");
        int id;
        while (true) {
            try {
                id = Integer.parseInt(ScannerUtils.inputString());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Giá trị phải là số nguyên. Vui lòng nhập lại.");
            }
        }
        System.out.println("Nhập tên phòng ban cần tìm (nhập để bỏ qua):");
        String name = ScannerUtils.inputString();
        
        // Lấy tất cả phòng ban rồi filter theo điều kiện
        List<Department> allDepartments = departmentController.findAllDepartment();
        final int searchId = id;
        final String searchName = name;
        List<Department> result = allDepartments.stream()
            .filter(dept -> (searchId == 0 || dept.getId() == searchId) && (searchName.isEmpty() || dept.getName().contains(searchName)))
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