package frontend;

import backend.QLAccount;
import entity.Account;
import entity.Department;
import entity.Position;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AccountFunction {
    private static Scanner scanner = new Scanner(System.in);

    public static void run() throws ClassNotFoundException {
        while (true) {
            System.out.println("=== Mời bạn chọn chức năng (Account) ===");
            System.out.println("1. Xem ds account");
            System.out.println("2. Thêm mới account");
            System.out.println("3. Update account");
            System.out.println("4. Xóa account");
            System.out.println("5. Tìm kiếm account");
            System.out.println("6. Thoát");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    try {
                        List<Account> accounts = QLAccount.showAllAccount();
                        showAccount(accounts);
                    } catch (Exception e) {
                        System.out.println("Lỗi khi lấy danh sách account: " + e.getMessage());
                    }
                    break;
                case "2":
                    insertAccount();
                    break;
                case "3":
                    updateAccount();
                    break;
                case "4":
                    deleteAccount();
                    break;
                case "5":
                    findAccountByFullnameAndUsername();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Chọn sai, chọn lại!");
            }
        }
    }

    public static void insertAccount () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập username mới:");
        String username = scanner.nextLine();
        System.out.println("Nhập full name:");
        String fullName = scanner.nextLine();
        System.out.println("Nhập email:");
        String email = scanner.nextLine();
        System.out.println("Nhập department_id:");
        int departmentId = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập position_id:");
        int positionId = Integer.parseInt(scanner.nextLine());

        boolean check = QLAccount.createAccount(username, fullName, email, departmentId, positionId);
        if (check) {
            System.out.println("Thêm account thành công");
        } else {
            System.out.println("Thêm account thất bại");
        }
    }

    public static void deleteAccount () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập username account cần xóa:");
        String username = scanner.nextLine();
        boolean check = QLAccount.deleteAccount(username);
        if (check) {
            System.out.println("Xóa account thành công");
        } else {
            System.out.println("Xóa account thất bại");
        }
    }

    public static void updateAccount () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập ID account cần update:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập username:");
        String username = scanner.nextLine();
        System.out.println("Nhập full name:");
        String fullName = scanner.nextLine();
        System.out.println("Nhập email:");
        String email = scanner.nextLine();
        System.out.println("Nhập department_id:");
        int departmentId = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập position_id:");
        int positionId = Integer.parseInt(scanner.nextLine());

        boolean check = QLAccount.updateAccount(id, username, fullName, email, departmentId, positionId);
        if (check) {
            System.out.println("Update account thành công");
        } else {
            System.out.println("Update account thất bại");
        }
    }

    public static void findAccountByFullnameAndUsername() throws ClassNotFoundException {
        System.out.println("Nhập fullname cần tìm: ");
        String fullname = scanner.nextLine();
        System.out.println("Nhập username cần tìm: ");
        String username = scanner.nextLine();
        try {
            List<Account> accounts = QLAccount.findByFullnameAndUsername(fullname, username);
            showAccount(accounts);
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm account: " + e.getMessage());
        }
    }

    private static void showAccount(List<Account> accounts) {
        System.out.println("+----+------------------------------+--------------------+----------------------+------------------------------+--------------------+");
        System.out.printf("| %-3s | %-28s | %-18s | %-20s | %-28s | %-18s |%n",
                "ID", "Email", "Username", "FullName", "Department", "Position");
        System.out.println("+----+------------------------------+--------------------+----------------------+------------------------------+--------------------+");
        for (Account account : accounts) {
            System.out.printf("| %-3d | %-28s | %-18s | %-20s | %-28s | %-18s |%n",
                    account.getId(),
                    account.getEmail(),
                    account.getUsername(),
                    account.getFullName(),
                    account.getDepartment() != null ? account.getDepartment().getName() : "",
                    account.getPosition() != null ? account.getPosition().getName() : "");
        }
        System.out.println("+----+------------------------------+--------------------+----------------------+------------------------------+--------------------+");
    }
}

