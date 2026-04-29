import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Exercise5 {
    private static final DateTimeFormatter BIRTHDAY_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Random RANDOM = new Random();

    public static int[] question1InputThreeIntegers(Scanner scanner) {
        int[] numbers = new int[3];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = readInt(scanner, "Nhap so nguyen thu " + (i + 1) + ": ");
        }
        return numbers;
    }

    public static double[] question2InputTwoDoubles(Scanner scanner) {
        double[] numbers = new double[2];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = readDouble(scanner, "Nhap so thuc thu " + (i + 1) + ": ");
        }
        return numbers;
    }

    public static String question3InputFullName(Scanner scanner) {
        return readNonEmptyString(scanner, "Nhap ho va ten: ");
    }

    public static LocalDate question4InputBirthday(Scanner scanner) {
        return readDate(scanner, "Nhap ngay sinh (dd/MM/yyyy): ");
    }

    public static Account question5CreateAccount(Scanner scanner, Department[] departments, Position[] positions) {
        Account account = new Account();
        account.id = readInt(scanner, "Nhap id account: ");
        account.email = readNonEmptyString(scanner, "Nhap email: ");
        account.userName = readNonEmptyString(scanner, "Nhap username: ");
        account.fullName = readNonEmptyString(scanner, "Nhap full name: ");
        account.department = readDepartment(scanner, departments);
        account.position = readPosition(scanner, positions);
        account.createDate = new Date();
        return account;
    }

    public static Department question6CreateDepartment(Scanner scanner) {
        Department department = new Department();
        department.id = readInt(scanner, "Nhap id department: ");
        department.name = readNonEmptyString(scanner, "Nhap ten department: ");
        return department;
    }

    public static int question7InputEvenNumber(Scanner scanner) {
        while (true) {
            int number = readInt(scanner, "Nhap so chan: ");
            if (number % 2 == 0) {
                return number;
            }
            System.out.println("Vui long nhap so chan.");
        }
    }

    public static void question8CreateAccountOrDepartment(Scanner scanner, Department[] departments, Position[] positions) {
        while (true) {
            System.out.println("Moi ban nhap vao chuc nang muon su dung");
            int option = readInt(scanner, "Nhap 1 (tao account) hoac 2 (tao department): ");
            if (option == 1) {
                Account account = question5CreateAccount(scanner, departments, positions);
                System.out.println("Da tao account: " + formatAccount(account));
                return;
            }
            if (option == 2) {
                Department department = question6CreateDepartment(scanner);
                System.out.println("Da tao department: " + formatDepartment(department));
                return;
            }
            System.out.println("Moi ban nhap lai");
        }
    }

    public static void question9AddGroupToAccount(Scanner scanner, Account[] accounts, Group[] groups) {
        printAccountUsernames(accounts);
        String username = readNonEmptyString(scanner, "Nhap username cua account: ");
        Account account = findAccountByUsername(accounts, username);
        if (account == null) {
            System.out.println("Khong tim thay account.");
            return;
        }

        printGroupNames(groups);
        String groupName = readNonEmptyString(scanner, "Nhap ten group: ");
        Group group = findGroupByName(groups, groupName);
        if (group == null) {
            System.out.println("Khong tim thay group.");
            return;
        }

        boolean added = addAccountToGroup(account, group);
        if (added) {
            System.out.println("Da them account vao group: " + group.name);
        } else {
            System.out.println("Account da co trong group.");
        }
    }

    public static void question10MenuWithContinue(Scanner scanner, Account[] accounts, Department[] departments,
                                                  Position[] positions, Group[] groups) {
        while (true) {
            System.out.println("Moi ban nhap vao chuc nang muon su dung");
            int option = readInt(scanner, "Nhap 1 (tao account), 2 (tao department), 3 (them group vao account): ");
            if (option == 1) {
                Account account = question5CreateAccount(scanner, departments, positions);
                System.out.println("Da tao account: " + formatAccount(account));
            } else if (option == 2) {
                Department department = question6CreateDepartment(scanner);
                System.out.println("Da tao department: " + formatDepartment(department));
            } else if (option == 3) {
                question9AddGroupToAccount(scanner, accounts, groups);
            } else {
                System.out.println("Moi ban nhap lai");
                continue;
            }

            String answer = readNonEmptyString(scanner, "Ban co muon thuc hien chuc nang khac khong? (Co/Khong): ");
            if (!answer.equalsIgnoreCase("Co")) {
                return;
            }
        }
    }

    public static void question11AddAccountToRandomGroup(Scanner scanner, Account[] accounts, Group[] groups) {
        printAccountUsernames(accounts);
        String username = readNonEmptyString(scanner, "Nhap username cua account: ");
        Account account = findAccountByUsername(accounts, username);
        if (account == null) {
            System.out.println("Khong tim thay account.");
            return;
        }
        if (groups == null || groups.length == 0) {
            System.out.println("Chua co group nao.");
            return;
        }
        Group randomGroup = groups[RANDOM.nextInt(groups.length)];
        boolean added = addAccountToGroup(account, randomGroup);
        if (added) {
            System.out.println("Da them account vao group ngau nhien: " + randomGroup.name);
        } else {
            System.out.println("Account da co trong group: " + randomGroup.name);
        }
    }

    public static String formatAccount(Account account) {
        if (account == null) {
            return "";
        }
        String departmentName = account.department == null ? "Chua co phong ban" : account.department.name;
        String positionName = account.position == null ? "Chua co position" : account.position.positionName.name();
        return "id=" + account.id + ", email=" + account.email + ", username=" + account.userName
                + ", fullName=" + account.fullName + ", department=" + departmentName + ", position=" + positionName;
    }

    public static String formatDepartment(Department department) {
        if (department == null) {
            return "";
        }
        return "id=" + department.id + ", name=" + department.name;
    }

    public static String formatDate(LocalDate date) {
        if (date == null) {
            return "";
        }
        return BIRTHDAY_FORMAT.format(date);
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Gia tri khong hop le, vui long nhap lai.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                System.out.println("Gia tri khong hop le, vui long nhap lai.");
            }
        }
    }

    private static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Gia tri khong duoc de trong.");
        }
    }

    private static LocalDate readDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, BIRTHDAY_FORMAT);
            } catch (DateTimeParseException exception) {
                System.out.println("Ngay khong hop le, vui long nhap lai.");
            }
        }
    }

    private static Department readDepartment(Scanner scanner, Department[] departments) {
        if (departments == null || departments.length == 0) {
            return null;
        }
        while (true) {
            int departmentId = readInt(scanner, "Nhap id department: ");
            Department department = findDepartmentById(departments, departmentId);
            if (department != null) {
                return department;
            }
            System.out.println("Khong tim thay department, vui long nhap lai.");
        }
    }

    private static Position readPosition(Scanner scanner, Position[] positions) {
        while (true) {
            int option = readInt(scanner, "Nhap position (1: DEV, 2: TEST, 3: SCRUM_MASTER, 4: PM): ");
            Position.PositionName positionName = mapPositionName(option);
            if (positionName != null) {
                Position position = new Position();
                position.id = option;
                position.positionName = positionName;
                return position;
            }
            System.out.println("Gia tri position khong hop le, vui long nhap lai.");
        }
    }

    private static Position.PositionName mapPositionName(int option) {
        switch (option) {
            case 1:
                return Position.PositionName.DEV;
            case 2:
                return Position.PositionName.TEST;
            case 3:
                return Position.PositionName.SCRUM_MASTER;
            case 4:
                return Position.PositionName.PM;
            default:
                return null;
        }
    }

    private static Department findDepartmentById(Department[] departments, int id) {
        if (departments == null) {
            return null;
        }
        for (Department department : departments) {
            if (department != null && department.id == id) {
                return department;
            }
        }
        return null;
    }

    private static Account findAccountByUsername(Account[] accounts, String username) {
        if (accounts == null) {
            return null;
        }
        for (Account account : accounts) {
            if (account != null && account.userName != null && account.userName.equalsIgnoreCase(username)) {
                return account;
            }
        }
        return null;
    }

    private static Group findGroupByName(Group[] groups, String name) {
        if (groups == null) {
            return null;
        }
        for (Group group : groups) {
            if (group != null && group.name != null && group.name.equalsIgnoreCase(name)) {
                return group;
            }
        }
        return null;
    }

    private static void printAccountUsernames(Account[] accounts) {
        System.out.println("Danh sach username:");
        if (accounts == null || accounts.length == 0) {
            System.out.println("(Khong co account)");
            return;
        }
        for (Account account : accounts) {
            if (account != null) {
                System.out.println("- " + account.userName);
            }
        }
    }

    private static void printGroupNames(Group[] groups) {
        System.out.println("Danh sach group:");
        if (groups == null || groups.length == 0) {
            System.out.println("(Khong co group)");
            return;
        }
        for (Group group : groups) {
            if (group != null) {
                System.out.println("- " + group.name);
            }
        }
    }

    private static boolean addAccountToGroup(Account account, Group group) {
        if (account == null || group == null) {
            return false;
        }
        if (containsAccount(group.accounts, account)) {
            return false;
        }
        if (!containsGroup(account.groups, group)) {
            account.groups = appendGroup(account.groups, group);
        }
        group.accounts = appendAccount(group.accounts, account);
        return true;
    }

    private static boolean containsGroup(Group[] groups, Group target) {
        if (groups == null) {
            return false;
        }
        for (Group group : groups) {
            if (group == target) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsAccount(Account[] accounts, Account target) {
        if (accounts == null) {
            return false;
        }
        for (Account account : accounts) {
            if (account == target) {
                return true;
            }
        }
        return false;
    }

    private static Group[] appendGroup(Group[] groups, Group group) {
        if (groups == null) {
            return new Group[]{group};
        }
        Group[] newGroups = new Group[groups.length + 1];
        System.arraycopy(groups, 0, newGroups, 0, groups.length);
        newGroups[groups.length] = group;
        return newGroups;
    }

    private static Account[] appendAccount(Account[] accounts, Account account) {
        if (accounts == null) {
            return new Account[]{account};
        }
        Account[] newAccounts = new Account[accounts.length + 1];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        newAccounts[accounts.length] = account;
        return newAccounts;
    }
}

