package backend;

import entity.Account;
import entity.Department;
import entity.Position;
import enums.PositionName;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLTK {
    private static List<Account> accounts = new ArrayList<>();
    private static List<Department> departments = new ArrayList<>();
    private static List<Position> positions = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void run() {
        departments.add(new Department(1, "Sale"));
        departments.add(new Department(2, "Marketing"));
        departments.add(new Department(3, "Bảo vệ"));

        positions.add(new Position(1, PositionName.DEV));
        positions.add(new Position(2, PositionName.TEST));

        accounts.add(new Account(1, "username1", "fullname1", "email1", new Department(1, "Sale"), new Position(1, PositionName.DEV)));
        accounts.add(new Account(2, "username2", "fullname2", "email2", new Department(2, "Marketing"), new Position(2, PositionName.DEV)));
        accounts.add(new Account(3, "username3", "fullname3", "email3", new Department(3, "Bảo vệ"), new Position(1, PositionName.DEV)));

        while (true) {
            System.out.println("==== Mời bạn chọn chức năng ====");
            System.out.println("1. Thêm phòng ban");
            System.out.println("2. Thêm chức vụ");
            System.out.println("3. Thêm account");
            System.out.println("4. Xem ds phòng ban");
            System.out.println("5. Xem ds chức vụ");
            System.out.println("6. Xem ds account");
            System.out.println("7, Tìm kiếm account theo tên phòng ban");
            System.out.println("8. Tìm kiếm account theo tên chức vụ");
            System.out.println("9. Xóa account theo fullname");

            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("=== Chức năng tạo phòng ban ===");
                    addDepartment();
                    break;
                case "2":
                    System.out.println("=== Chức năng tạo chức vụ ===");
                    addPosition();
                    break;
                case "3":
                    System.out.println("=== Chức năng tạo account ===");
                    addAccount();
                    break;
                case "4":
                    System.out.println("=== Chức năng xem ds phòng ban ===");
                    showDepartments();
                    break;
                case "5":
                    System.out.println("=== Chức năng xem ds chức vụ ===");
                    showPositions();
                    break;
                case "6":
                    System.out.println("=== Chức năng xem ds account ===");
                    showAccounts();
                    break;
                case "7":
                    System.out.println("=== Chức năng tìm kiếm account theo tên phòng ban ===");
                    findAccountByDepartmentName();
                    break;
                case "8":
                    System.out.println("=== Chức năng tìm kiếm account theo tên Chức vụ ===");
                    findAccountByPositionName();
                    break;
                case "9":
                    System.out.println("=== Chức năng xóa account theo fullname ===");
                    deleteAccountByFullName();
                    break;
                default:
                    System.out.println("Nhập sai! Mời bạn chọn lại!");

            }
        }


    }

    public static void addDepartment() {
        System.out.println("Mời bạn nhập ID: ");
        int depId = sc.nextInt();
        sc.nextLine();
        System.out.println("Mời bạn nhập tên: ");
        String depName = sc.nextLine();

        Department department = new Department(depId, depName);
        departments.add(department);
        System.out.println("Thêm phòng ban thành công!!!");
    }

    public static void addAccount() {
        System.out.println("Mời bạn nhập ID: ");
        int accID = sc.nextInt();
        sc.nextLine();
        System.out.println("Mời bạn nhập email: ");
        String email = sc.nextLine();
        System.out.println("Mời bạn nhập username: ");
        String username = sc.nextLine();
        System.out.println("Mời bạn nhập fullname: ");
        String fullname = sc.nextLine();
        System.out.println("Mời bạn chọn departmentID");
        for (Department department : departments) {
            System.out.println(department.getId() + " : " + department.getName());
        }
        int departmentID = sc.nextInt();
        sc.nextLine();
        // tìm department theo id
        Department department = new Department();
        for (Department de : departments) {
            if (departmentID == de.getId()) {
                department = de;
                break;
            }
        }

        System.out.println("Mời bạn chọn positionID");
        for (Position po : positions) {
            System.out.println(po.getId() + " : " + po.getName());
        }
        int positionID = sc.nextInt();
        sc.nextLine();
        // tìm position theo id
        Position position = new Position();
        for (Position po : positions) {
            if (positionID == po.getId()) {
                position = po;
                break;
            }
        }
        Account account = new Account(accID, email, username, fullname, department, position);
        accounts.add(account);
        System.out.println("Thêm account thành công!!!");
    }

    public static void addPosition() {
        System.out.println("Mời bạn nhập ID: ");
        int poID = sc.nextInt();
        sc.nextLine();
        System.out.println("Mời bạn nhập tên: 1. DEV    2. TEST     3. CRUM_MASTER      Khác. PM");
        String poNameChoice = sc.nextLine();
        PositionName poName;
        switch (poNameChoice) {
            case "1":
                poName = PositionName.DEV;
                break;
            case "2":
                poName = PositionName.TEST;
                break;
            case "3":
                poName = PositionName.SCRUM_MASTER;
                break;
            default:
                poName = PositionName.PM;
        }

        Position position = new Position(poID, poName);
        positions.add(position);
        System.out.println("Thêm chức vụ thành công!!!");
    }

    public static void showAccounts() {
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("|%5s|%20s|%20s|%20s|%20s|%20s|\n", "ID", "FullName", "Email", "Username", "Tên phòng ban", "Tên chức vụ");
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
        for (Account account : accounts) {
            System.out.printf("|%5s|%20s|%20s|%20s|%20s|%20s|\n", account.getId(), account.getFullName(), account.getEmail(), account.getUsername(), account.getDepartment().getName(), account.getPosition().getName().name());
        }
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
    }

    public static void showDepartments() {
        System.out.println("+-----+--------------------+");
        System.out.printf("|%5s|%20s|\n", "ID", "Tên phòng ban");
        System.out.println("+-----+--------------------+");
        for (Department department : departments) {
            System.out.printf("|%5s|%20s|\n", department.getId(), department.getName());
        }
        System.out.println("+-----+--------------------+");
    }

    public static void showPositions() {
        System.out.println("+-----+--------------------+");
        System.out.printf("|%5s|%20s|\n", "ID", "Tên chức vụ");
        System.out.println("+-----+--------------------+");
        for (Position po : positions) {
            System.out.printf("|%5s|%20s|\n", po.getId(), po.getName());
        }
        System.out.println("+-----+--------------------+");
    }

    public static void findAccountByDepartmentName() {
        System.out.println("Mời bạn nhập tên phòng ban: ");
        String depName = sc.nextLine();
        boolean checkDepartmentNameExists = false;
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("|%5s|%20s|%20s|%20s|%20s|%20s|\n", "ID", "FullName", "Email", "Username", "Tên phòng ban", "Tên chức vụ");
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
        for (Account account : accounts) {
            if (depName.equalsIgnoreCase(account.getDepartment().getName())) {
                System.out.printf("|%5s|%20s|%20s|%20s|%20s|%20s|\n", account.getId(), account.getFullName(), account.getEmail(), account.getUsername(), account.getDepartment().getName(), account.getPosition().getName().name());
                checkDepartmentNameExists = true;
            }
        }
        if (!checkDepartmentNameExists) {
            System.out.println("Không tim thấy account nào!");
        }
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");

    }

    public static void findAccountByPositionName() {
        System.out.println("Mời bạn nhập tên chức vụ: ");
        String poName = sc.nextLine();
        boolean checkPositionNameExists = false;
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("|%5s|%20s|%20s|%20s|%20s|%20s|\n", "ID", "FullName", "Email", "Username", "Tên phòng ban", "Tên chức vụ");
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
        for (Account account : accounts) {
            if (poName.equalsIgnoreCase(account.getPosition().getName().name())) {
                System.out.printf("|%5s|%20s|%20s|%20s|%20s|%20s|\n", account.getId(), account.getFullName(), account.getEmail(), account.getUsername(), account.getDepartment().getName(), account.getPosition().getName().name());
                checkPositionNameExists = true;
            }
        }
        if (!checkPositionNameExists) {
            System.out.println("Không tim thấy account nào!");
        }
        System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");

    }

    public static void deleteAccountByFullName() {
        System.out.println("Mời bạn nhập tên cần xóa: ");
        String accNameDelete = sc.nextLine();
        List<Account> accountsDelete = new ArrayList<>();
        for (Account account : accounts) {
            if (accNameDelete.equalsIgnoreCase(account.getFullName())) {
                accountsDelete.add(account);
            }
        }
        boolean checkAccountNameExists = accounts.removeAll(accountsDelete);
        if (!checkAccountNameExists) {
            System.out.println("Không có account nào có tên như trên trong hệ thống!");
        } else {
            System.out.println("Xóa thành công!");
        }
    }
}