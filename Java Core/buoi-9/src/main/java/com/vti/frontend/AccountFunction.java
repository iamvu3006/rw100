package com.vti.frontend;

import com.vti.backend.controller.AccountController;
import com.vti.backend.controller.DepartmentController;
import com.vti.backend.controller.PositionController;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.utils.ScannerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AccountFunction {
    // khoi tao accountController
    private AccountController accountController = new AccountController();
    private DepartmentController departmentController = new DepartmentController();
    private PositionController positionController = new PositionController();
    private static Scanner sc = new Scanner(System.in);

    public void run() {
        List<Account> accounts = new ArrayList<>();
        while (true) {
            System.out.println("=== Mời bạn chọn chức năng ===");
            System.out.println("1. Xem ds account");
            System.out.println("2. Thêm mới account");
            System.out.println("3. Xóa account theo tên");
            System.out.println("4. Update account theo ID");
            System.out.println("5. Map account by username");
            System.out.println("6. Import CSV");
            System.out.println("7. Thoát");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    accounts = accountController.findAll();
                    showAccount(accounts);
                    break;
                case "2":
                    insertAccount();
                    break;
                case "3":
                    deleteAccount();
                    break;
                case "4":
                    updateAccount();
                    break;
                case "5":
                    Map<String, Account> map = accountController.mapAccountByUsername();
                    Account acc = map.get("dong");

                    break;
                case "6":
                    this.importAccountFromCSV();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Nhập sai, nhập lại.");
            }
        }
    }

    private void importAccountFromCSV() {
        System.out.println("=== Import file CSV ===");
        System.out.println("Mời bạn nhập đường dẫn đến file:");
        //C:\Users\Admin\Desktop\rw100\csv\input_account.csv
        String pathName = sc.nextLine();
        String message = accountController.importAccountFromCSV(pathName);
        System.out.println(message);
    }

    public void showAccount(List<Account> accounts) {
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("|%5s|%20s|%20s|%20s|%20s|%20s|\n", "ID", "FullName", "Email", "Username", "Tên", "Tên chức vụ");
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
        for (Account account : accounts) {
            System.out.printf("|%5s|%20s|%20s|%20s|%20s|%20s|\n", account.getId(), account.getFullName(), account.getEmail(), account.getUsername(), account.getDepartment().getName(), account.getPosition().getName().name());
        }
        if (accounts.isEmpty()) {
            System.out.println("Không tìm thấy");
        }
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
    }

    public void insertAccount() {
        String fullName;
        String username;
        String email;

        System.out.println("Nhập email: ");
        while (true) {
            email = ScannerUtils.inputEmail();
            // check trung
            if (accountController.checkEmailExist(email)) {
                System.out.println("email đã tồn tại. Nhập lại:");
                continue;
            }
            break;
        }

        // validation username
        System.out.println("Nhập username: ");
        while (true) {
            username = ScannerUtils.inputString();
            // check trung
            if (accountController.checkUsernameExist(username, null)) {
                System.out.println("Username đã tồn tại. Nhập lại:");
                continue;
            }
            break;
        }

        // validation fullName
        System.out.println("Nhập fullName: ");
        fullName = ScannerUtils.inputString();

        System.out.println("Chọn ID department: ");
        List<Department> departments = departmentController.findAll();
        Integer depID;
        while (true) {
            for (Department department : departments) {
                System.out.println("ID: " + department.getId() + ", DepartmentName: " + department.getName());
            }
            depID = ScannerUtils.inputIntGreaterThenZero();
            // check departmentID có tồn tại ko
            boolean checkExists = departmentController.checkExistID(depID);//Integer.valueOf("abc")
            if (!checkExists) {
                System.out.println("Không ton tại deparmentID này:");

            } else {
                break;
            }
        }

        System.out.println("Chọn ID position: ");
        List<Position> positions = positionController.findAll();
        Integer poID;
        while (true) {
            for (Position position : positions) {
                System.out.println("ID: " + position.getId() + ", PositionName: " + position.getName());
            }

            poID = ScannerUtils.inputIntGreaterThenZero();
            // check positionID có tồn tại ko
            boolean checkExists = checkExistPosition(positions, String.valueOf(poID));//Integer.valueOf("abc")
            if (!checkExists) {
                System.out.println("Không ton tại positionID này:");
            } else {
                break;
            }
        }
        boolean check = accountController.create(email, username, fullName, depID, poID);
        if (check) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Thêm mới thất bại");
        }
    }


    public void deleteAccount() {
        int id;
        System.out.println("Nhập ID cần xóa: ");
        while (true) {
            id = ScannerUtils.inputIntGreaterThenZero();
            // kiem tra xem id nay co ton tai ko
            if (!accountController.checkIdExist(id)) {
                System.out.println(" ID này không ton tai. Nhap lai: ");
            } else {
                break;
            }
        }

        boolean check = accountController.delete(id);
        if (check) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Xóa thất bại");
        }
    }


    public void updateAccount() {
        Integer id;
        String username;
        System.out.println("Nhập ID cần sửa: ");
        while (true) {
            id = ScannerUtils.inputIntGreaterThenZero();
            // kiem tra xem id nay co ton tai ko
            if (!accountController.checkIdExist(id)) {
                System.out.println(" ID này không ton tai. Nhap lai: ");
            } else {
                break;
            }
        }

        System.out.println("Nhập username: ");
        while (true) {
            username = ScannerUtils.inputString();
            // check trung
            if (accountController.checkUsernameExist(username, id)) {
                System.out.println("Username đã tồn tại. Nhập lại:");
                continue;
            }
            break;
        }

        boolean check = accountController.update(id, username);
        if (check) {
            System.out.println("Update thành công");
        } else {
            System.out.println("Update thất bại");
        }
    }

    public boolean checkExistDepartment(List<Department> departments, String id) {
        for (Department department : departments) {
            if (id.equals(String.valueOf(department.getId()))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkExistPosition(List<Position> positions, String id) {
        for (Position position : positions) {
            if (id.equals(String.valueOf(position.getId()))) {
                return true;
            }
        }
        return false;
    }
}
