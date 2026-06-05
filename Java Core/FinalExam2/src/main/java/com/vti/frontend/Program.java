package com.vti.frontend;

import com.vti.backend.controller.UserController;
import com.vti.entity.User;
import com.vti.enums.Role;
import com.vti.utils.ScannerUtils;

import java.util.Scanner;

public final class Program {

    private Program() {
    }

    public static void run() {
        UserController userController = new UserController();
        Scanner scanner = new Scanner(System.in);
        userController.initializeData();

        while (true) {
            printLoginMenu();
            int choice = ScannerUtils.inputInt(scanner, "Chọn chức năng: ");
            if (choice == 1) {
                loginFlow(scanner, userController);
            } else if (choice == 2) {
                System.out.println("Tạm biệt.");
                return;
            } else {
                System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void loginFlow(Scanner scanner, UserController userController) {
        try {
            String email = ScannerUtils.inputRequiredString(scanner, "Nhập Email: ");
            String password = ScannerUtils.inputRequiredString(scanner, "Nhập Password: ");
            User loggedInUser = userController.login(email, password);
            if (loggedInUser == null) {
                System.out.println("Email hoặc Password không đúng.");
                return;
            }
            System.out.println("Đăng nhập thành công với quyền: " + loggedInUser.getRole());
            openMainMenu(scanner, userController, loggedInUser);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void openMainMenu(Scanner scanner, UserController userController, User loggedInUser) {
        while (true) {
            printMainMenu(loggedInUser.getRole());
            int choice = ScannerUtils.inputInt(scanner, "Chọn chức năng: ");
            if (choice == 1) {
                userController.displayAllUsers();
            } else if (choice == 2) {
                int id = ScannerUtils.inputInt(scanner, "Nhập id user: ");
                userController.displayUserById(id);
            } else if (choice == 3) {
                int id = ScannerUtils.inputInt(scanner, "Nhập id user cần xóa: ");
                userController.deleteUserById(id);
            } else if (choice == 4) {
                System.out.println("Đăng xuất thành công.");
                return;
            } else if (choice == 5 && loggedInUser.getRole() == Role.ADMIN) {
                createEmployeeFlow(scanner, userController);
            } else {
                System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void createEmployeeFlow(Scanner scanner, UserController userController) {
        try {
            String fullName = ScannerUtils.inputRequiredString(scanner, "Nhập FullName của employee: ");
            String email = ScannerUtils.inputRequiredString(scanner, "Nhập Email của employee: ");
            userController.createEmployee(fullName, email);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void printLoginMenu() {
        System.out.println();
        System.out.println("===== LOGIN MENU =====");
        System.out.println("1. Login");
        System.out.println("2. Exit");
    }

    private static void printMainMenu(Role role) {
        System.out.println();
        System.out.println("===== MAIN MENU =====");
        System.out.println("1. In danh sách user");
        System.out.println("2. Tìm user theo id");
        System.out.println("3. Xóa user theo id");
        if (role == Role.ADMIN) {
            System.out.println("4. Logout");
            System.out.println("5. Tạo employee mới");
        } else {
            System.out.println("4. Logout");
        }
    }
}
