package com.vti.frontend;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import com.vti.backend.AccountManagement;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.entity.Position.PositionName;

public class App {
    private static final String TABLE_BORDER =
            "+----+----------------+----------------------+----------------+-------------+";

    public static void main(String[] args) {
        System.setOut(new PrintStream(
                new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in);
        AccountManagement accountManagement = new AccountManagement();
        int choice;

        do {
            printMenu();
            choice = readInt(scanner, "Mời bạn chọn chức năng (0-5): ");

            switch (choice) {
                case 1:
                    printAccounts(accountManagement.getListAccount());
                    break;
                case 2:
                    addAccount(scanner, accountManagement);
                    break;
                case 3:
                    updateAccount(scanner, accountManagement);
                    break;
                case 4:
                    deleteAccount(scanner, accountManagement);
                    break;
                case 5:
                    findAccount(scanner, accountManagement);
                    break;
                case 0:
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 0 đến 5.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== CHƯƠNG TRÌNH QUẢN LÝ TÀI KHOẢN =====");
        System.out.println("1. In danh sách toàn bộ tài khoản");
        System.out.println("2. Thêm tài khoản mới");
        System.out.println("3. Cập nhật thông tin tài khoản");
        System.out.println("4. Xóa tài khoản");
        System.out.println("5. Tìm kiếm tài khoản theo ID");
        System.out.println("0. Thoát");
        System.out.println("==========================================");
    }

    private static void addAccount(Scanner scanner, AccountManagement accountManagement) {
        int id = readInt(scanner, "Nhập ID: ");
        String username = readText(scanner, "Nhập username: ");
        String fullname = readText(scanner, "Nhập fullname: ");
        Department department = readDepartment(scanner);
        Position position = readPosition(scanner);

        accountManagement.addAccount(new Account(id, username, fullname, department, position));
        System.out.println("Thêm tài khoản thành công.");
    }

    private static void updateAccount(Scanner scanner, AccountManagement accountManagement) {
        int id = readInt(scanner, "Nhập ID tài khoản cần cập nhật: ");
        String username = readText(scanner, "Nhập username mới: ");
        String fullname = readText(scanner, "Nhập fullname mới: ");
        Department department = readDepartment(scanner);
        Position position = readPosition(scanner);

        boolean updated = accountManagement.updateAccount(id, username, fullname, department, position);
        System.out.println(updated ? "Cập nhật tài khoản thành công." : "Không tìm thấy tài khoản.");
    }

    private static void deleteAccount(Scanner scanner, AccountManagement accountManagement) {
        int id = readInt(scanner, "Nhập ID tài khoản cần xóa: ");
        boolean deleted = accountManagement.deleteAccount(id);
        System.out.println(deleted ? "Xóa tài khoản thành công." : "Không tìm thấy tài khoản.");
    }

    private static void findAccount(Scanner scanner, AccountManagement accountManagement) {
        int id = readInt(scanner, "Nhập ID tài khoản cần tìm: ");
        Account account = accountManagement.getAccountById(id);
        if (account == null) {
            System.out.println("Không tìm thấy");
        } else {
            printAccounts(java.util.Collections.singletonList(account));
        }
    }

    private static void printAccounts(List<Account> accounts) {
        if (accounts.isEmpty()) {
            System.out.println("Danh sách tài khoản đang trống.");
            return;
        }

        System.out.println(TABLE_BORDER);
        System.out.printf("| %-2s | %-14s | %-20s | %-14s | %-11s |%n",
                "ID", "Username", "Fullname", "Department", "Position");
        System.out.println(TABLE_BORDER);
        for (Account account : accounts) {
            String department = account.getDepartment() == null
                    ? ""
                    : account.getDepartment().getName();
            String position = account.getPosition() == null
                    ? ""
                    : formatPosition(account.getPosition().getName());
            System.out.printf("| %-2d | %-14s | %-20s | %-14s | %-11s |%n",
                    account.getId(), account.getUsername(), account.getFullname(),
                    department, position);
        }
        System.out.println(TABLE_BORDER);
    }

    private static Department readDepartment(Scanner scanner) {
        String name = readText(scanner, "Nhập tên department: ");
        return new Department(0, name);
    }

    private static Position readPosition(Scanner scanner) {
        while (true) {
            String input = readText(scanner, "Nhập tên position (Dev, Test, ScrumMaster, PM): ");
            PositionName positionName = parsePositionName(input);
            if (positionName != null) {
                return new Position(0, positionName);
            }
            System.out.println("Position không hợp lệ. Vui lòng nhập Dev, Test, ScrumMaster hoặc PM.");
        }
    }

    private static PositionName parsePositionName(String input) {
        String normalized = input.trim().replaceAll("[\\s_-]", "").toUpperCase();
        switch (normalized) {
            case "DEV":
                return PositionName.DEV;
            case "TEST":
                return PositionName.TEST;
            case "SCRUMMASTER":
                return PositionName.SCRUM_MASTER;
            case "PM":
                return PositionName.PM;
            default:
                return null;
        }
    }

    private static String formatPosition(PositionName positionName) {
        switch (positionName) {
            case SCRUM_MASTER:
                return "ScrumMaster";
            default:
                return positionName.name();
        }
    }

    private static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập một số nguyên.");
            }
        }
    }

    private static String readText(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}
