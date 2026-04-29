import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        SampleData sampleData = createSampleData();

        runExercise1(sampleData);
        runExercise2(sampleData);
        runExercise3(sampleData);
        runExercise4(sampleData);
        runExercise5(sampleData);
        runExercise6(sampleData);
    }

    private static void runExercise1(SampleData sampleData) {
        Account[] accounts = sampleData.accounts;
        Department[] departments = sampleData.departments;
        Group[] groups = sampleData.groups;

        System.out.println("===== Exercise 1: Flow Control =====");

        System.out.println("Question 1:");
        if (accounts[1].department == null) {
            System.out.println("Nhan vien nay chua co phong ban");
        } else {
            System.out.println("Phong ban cua nhan vien nay la " + accounts[1].department.name);
        }

        System.out.println("Question 2:");
        printGroupMessageIfElse(accounts[1]);

        System.out.println("Question 3:");
        String question3Message =
                accounts[1].department == null
                        ? "Nhan vien nay chua co phong ban"
                        : "Phong ban cua nhan vien nay la " + accounts[1].department.name;
        System.out.println(question3Message);

        System.out.println("Question 4:");
        String question4Message =
                accounts[0].position.positionName == Position.PositionName.DEV
                        ? "Day la Developer"
                        : "Nguoi nay khong phai la Developer";
        System.out.println(question4Message);

        System.out.println("Question 5:");
        switch (groups[0].accounts.length) {
            case 1:
                System.out.println("Nhom co mot thanh vien");
                break;
            case 2:
                System.out.println("Nhom co hai thanh vien");
                break;
            case 3:
                System.out.println("Nhom co ba thanh vien");
                break;
            default:
                System.out.println("Nhom co nhieu thanh vien");
                break;
        }

        System.out.println("Question 6:");
        printGroupMessageSwitchCase(accounts[1]);

        System.out.println("Question 7:");
        switch (accounts[0].position.positionName) {
            case DEV:
                System.out.println("Day la Developer");
                break;
            case TEST:
            case SCRUM_MASTER:
            case PM:
            default:
                System.out.println("Nguoi nay khong phai la Developer");
                break;
        }

        System.out.println("Question 8:");
        for (Account account : accounts) {
            printAccountBasicInfo(account);
        }

        System.out.println("Question 9:");
        for (Department department : departments) {
            System.out.println("Department id: " + department.id + ", name: " + department.name);
        }

        System.out.println("Question 10:");
        for (int i = 0; i < accounts.length; i++) {
            printAccountWithIndex(accounts[i], i + 1);
        }

        System.out.println("Question 11:");
        for (int i = 0; i < departments.length; i++) {
            System.out.println("Thong tin department thu " + (i + 1) + " la:");
            System.out.println("Id: " + departments[i].id);
            System.out.println("Name: " + departments[i].name);
        }

        System.out.println("Question 12:");
        for (int i = 0; i < departments.length; i++) {
            if (i == 2) {
                break;
            }
            System.out.println("Thong tin department thu " + (i + 1) + " la:");
            System.out.println("Id: " + departments[i].id);
            System.out.println("Name: " + departments[i].name);
        }

        System.out.println("Question 13:");
        for (int i = 0; i < accounts.length; i++) {
            if (i == 1) {
                continue;
            }
            printAccountBasicInfo(accounts[i]);
        }

        System.out.println("Question 14:");
        for (Account account : accounts) {
            if (account.id < 4) {
                printAccountBasicInfo(account);
            }
        }

        System.out.println("Question 15:");
        for (int i = 2; i <= 20; i += 2) {
            System.out.println(i);
        }

        System.out.println("Question 16 (WHILE):");
        int index = 0;
        while (index < accounts.length) {
            printAccountWithIndex(accounts[index], index + 1);
            index++;
        }

        index = 0;
        while (index < departments.length) {
            System.out.println("Thong tin department thu " + (index + 1) + " la:");
            System.out.println("Id: " + departments[index].id);
            System.out.println("Name: " + departments[index].name);
            index++;
        }

        index = 0;
        while (index < departments.length) {
            if (index == 2) {
                break;
            }
            System.out.println("Thong tin department thu " + (index + 1) + " la:");
            System.out.println("Id: " + departments[index].id);
            System.out.println("Name: " + departments[index].name);
            index++;
        }

        index = 0;
        while (index < accounts.length) {
            if (index == 1) {
                index++;
                continue;
            }
            printAccountBasicInfo(accounts[index]);
            index++;
        }

        index = 0;
        while (index < accounts.length) {
            if (accounts[index].id >= 4) {
                break;
            }
            printAccountBasicInfo(accounts[index]);
            index++;
        }

        int number = 1;
        while (number <= 20) {
            if (number % 2 != 0) {
                number++;
                continue;
            }
            System.out.println(number);
            number++;
        }

        System.out.println("Question 17 (DO-WHILE):");
        index = 0;
        do {
            printAccountWithIndex(accounts[index], index + 1);
            index++;
        } while (index < accounts.length);

        index = 0;
        do {
            System.out.println("Thong tin department thu " + (index + 1) + " la:");
            System.out.println("Id: " + departments[index].id);
            System.out.println("Name: " + departments[index].name);
            index++;
        } while (index < departments.length);

        index = 0;
        do {
            if (index == 2) {
                break;
            }
            System.out.println("Thong tin department thu " + (index + 1) + " la:");
            System.out.println("Id: " + departments[index].id);
            System.out.println("Name: " + departments[index].name);
            index++;
        } while (index < departments.length);

        index = 0;
        do {
            if (index == 1) {
                index++;
                continue;
            }
            printAccountBasicInfo(accounts[index]);
            index++;
        } while (index < accounts.length);

        index = 0;
        do {
            if (accounts[index].id >= 4) {
                break;
            }
            printAccountBasicInfo(accounts[index]);
            index++;
        } while (index < accounts.length);

        number = 1;
        do {
            if (number % 2 != 0) {
                number++;
                continue;
            }
            System.out.println(number);
            number++;
        } while (number <= 20);
    }

    private static void runExercise2(SampleData sampleData) {
        Account[] accounts = sampleData.accounts;

        System.out.println("===== Exercise 2: System.out.printf =====");

        System.out.println("Question 1:");
        int number = 5;
        System.out.printf("So nguyen la: %d%n", number);

        System.out.println("Question 2:");
        int largeNumber = 100000000;
        System.out.printf("So nguyen dinh dang: %,d%n", largeNumber);

        System.out.println("Question 3:");
        double floatNumber = 5.567098;
        System.out.printf("So thuc sau khi lam tron 4 chu so: %.4f%n", floatNumber);

        System.out.println("Question 4:");
        String fullName = "Nguyen Van A";
        System.out.printf("Ten toi la \"%s\" va toi dang doc than.%n", fullName);

        System.out.println("Question 5:");
        Date now = new Date();
        SimpleDateFormat nowFormat = new SimpleDateFormat("dd/MM/yyyy HH'h':mm'p':ss's'");
        System.out.println(nowFormat.format(now));

        System.out.println("Question 6:");
        System.out.printf("%-25s %-20s %-20s%n", "Email", "FullName", "Department");
        System.out.printf("%-25s %-20s %-20s%n", "-------------------------", "--------------------", "--------------------");
        for (Account account : accounts) {
            String departmentName = account.department == null ? "Chua co phong ban" : account.department.name;
            System.out.printf("%-25s %-20s %-20s%n", account.email, account.fullName, departmentName);
        }
    }

    private static void runExercise3(SampleData sampleData) {
        Exam exam = sampleData.exams[0];

        System.out.println("===== Exercise 3: Date Format =====");

        System.out.println("Question 1:");
        Locale vietnameseLocale = Locale.forLanguageTag("vi-VN");
        DateFormat vietnameseDateFormat = DateFormat.getDateInstance(DateFormat.FULL, vietnameseLocale);
        System.out.println("Exam thu 1: " + exam.code + " - " + vietnameseDateFormat.format(exam.createDate));

        System.out.println("Question 2:");
        SimpleDateFormat yearMonthDayHourMinuteSecond = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        System.out.println("Exam da tao ngay: " + yearMonthDayHourMinuteSecond.format(exam.createDate));

        System.out.println("Question 3:");
        SimpleDateFormat yearOnly = new SimpleDateFormat("yyyy");
        System.out.println(yearOnly.format(exam.createDate));

        System.out.println("Question 4:");
        SimpleDateFormat monthYear = new SimpleDateFormat("MM/yyyy");
        System.out.println(monthYear.format(exam.createDate));

        System.out.println("Question 5:");
        SimpleDateFormat monthDay = new SimpleDateFormat("MM-dd");
        System.out.println(monthDay.format(exam.createDate));
    }

    private static void runExercise4(SampleData sampleData) {
        System.out.println("===== Exercise 4: Random Number =====");

        System.out.println("Question 1:");
        int randomInt = Exercise4.question1RandomInt();
        System.out.println(randomInt);

        System.out.println("Question 2:");
        double randomDouble = Exercise4.question2RandomDouble();
        System.out.println(randomDouble);

        System.out.println("Question 3:");
        String[] classmateNames = {"An", "Binh", "Chi", "Dung", "Hanh"};
        String randomName = Exercise4.question3RandomName(classmateNames);
        System.out.println("Ten ban duoc chon: " + randomName);

        System.out.println("Question 4:");
        LocalDate randomDateRange = Exercise4.question4RandomDateInRange();
        System.out.println(Exercise4.formatDate(randomDateRange));

        System.out.println("Question 5:");
        LocalDate randomDateLastYear = Exercise4.question5RandomDateLastYear();
        System.out.println(Exercise4.formatDate(randomDateLastYear));

        System.out.println("Question 6:");
        LocalDate randomPastDate = Exercise4.question6RandomPastDate();
        System.out.println(Exercise4.formatDate(randomPastDate));

        System.out.println("Question 7:");
        int randomThreeDigits = Exercise4.question7RandomThreeDigitNumber();
        System.out.println(randomThreeDigits);
    }

    private static void runExercise5(SampleData sampleData) {
        System.out.println("===== Exercise 5: Input from console =====");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Question 1:");
        int[] integers = Exercise5.question1InputThreeIntegers(scanner);
        System.out.println("Ban da nhap: " + Arrays.toString(integers));

        System.out.println("Question 2:");
        double[] doubles = Exercise5.question2InputTwoDoubles(scanner);
        System.out.println("Ban da nhap: " + Arrays.toString(doubles));

        System.out.println("Question 3:");
        String fullName = Exercise5.question3InputFullName(scanner);
        System.out.println("Ho va ten: " + fullName);

        System.out.println("Question 4:");
        LocalDate birthday = Exercise5.question4InputBirthday(scanner);
        System.out.println("Ngay sinh: " + Exercise5.formatDate(birthday));

        System.out.println("Question 5:");
        Account newAccount = Exercise5.question5CreateAccount(scanner, sampleData.departments, sampleData.positions);
        System.out.println("Thong tin account: " + Exercise5.formatAccount(newAccount));

        System.out.println("Question 6:");
        Department newDepartment = Exercise5.question6CreateDepartment(scanner);
        System.out.println("Thong tin department: " + Exercise5.formatDepartment(newDepartment));

        System.out.println("Question 7:");
        int evenNumber = Exercise5.question7InputEvenNumber(scanner);
        System.out.println("So chan vua nhap: " + evenNumber);

        System.out.println("Question 8:");
        Exercise5.question8CreateAccountOrDepartment(scanner, sampleData.departments, sampleData.positions);

        System.out.println("Question 9:");
        Exercise5.question9AddGroupToAccount(scanner, sampleData.accounts, sampleData.groups);

        System.out.println("Question 10:");
        Exercise5.question10MenuWithContinue(scanner, sampleData.accounts, sampleData.departments, sampleData.positions, sampleData.groups);

        System.out.println("Question 11:");
        Exercise5.question11AddAccountToRandomGroup(scanner, sampleData.accounts, sampleData.groups);
    }

    private static void runExercise6(SampleData sampleData) {
        System.out.println("===== Exercise 6: Method =====");

        System.out.println("Question 1:");
        Exercise6.question1PrintEvenNumbersLessThan10();

        System.out.println("Question 2:");
        Exercise6.question2PrintAccounts(sampleData.accounts);

        System.out.println("Question 3:");
        Exercise6.question3PrintPositiveNumbersLessThan10();
    }

    private static void printGroupMessageIfElse(Account account) {
        if (account.groups == null || account.groups.length == 0) {
            System.out.println("Nhan vien nay chua co group");
        } else if (account.groups.length <= 2) {
            System.out.println("Group cua nhan vien nay la " + joinGroupNames(account.groups));
        } else if (account.groups.length == 3) {
            System.out.println("Nhan vien nay la nguoi quan trong, tham gia nhieu group");
        } else {
            System.out.println("Nhan vien nay la nguoi hong chuyen, tham gia tat ca cac group");
        }
    }

    private static void printGroupMessageSwitchCase(Account account) {
        int groupCount = account.groups == null ? 0 : account.groups.length;
        switch (groupCount) {
            case 0:
                System.out.println("Nhan vien nay chua co group");
                break;
            case 1:
            case 2:
                System.out.println("Group cua nhan vien nay la " + joinGroupNames(account.groups));
                break;
            case 3:
                System.out.println("Nhan vien nay la nguoi quan trong, tham gia nhieu group");
                break;
            default:
                System.out.println("Nhan vien nay la nguoi hong chuyen, tham gia tat ca cac group");
                break;
        }
    }

    private static String joinGroupNames(Group[] groups) {
        StringBuilder groupNames = new StringBuilder();
        for (int i = 0; i < groups.length; i++) {
            groupNames.append(groups[i].name);
            if (i < groups.length - 1) {
                groupNames.append(", ");
            }
        }
        return groupNames.toString();
    }

    private static void printAccountBasicInfo(Account account) {
        String departmentName = account.department == null ? "Chua co phong ban" : account.department.name;
        System.out.println("Email: " + account.email + ", FullName: " + account.fullName + ", Department: " + departmentName);
    }

    private static void printAccountWithIndex(Account account, int index) {
        String departmentName = account.department == null ? "Chua co phong ban" : account.department.name;
        System.out.println("Thong tin account thu " + index + " la:");
        System.out.println("Email: " + account.email);
        System.out.println("Full name: " + account.fullName);
        System.out.println("Phong ban: " + departmentName);
    }

    private static SampleData createSampleData() {
        SampleData sampleData = new SampleData();

        sampleData.departments = new Department[5];
        String[] departmentNames = {"Sale", "Marketing", "Human Resource", "Security", "Development"};
        for (int i = 0; i < sampleData.departments.length; i++) {
            sampleData.departments[i] = new Department();
            sampleData.departments[i].id = i + 1;
            sampleData.departments[i].name = departmentNames[i];
        }

        sampleData.positions = new Position[5];
        Position.PositionName[] positionNames = {
                Position.PositionName.DEV,
                Position.PositionName.TEST,
                Position.PositionName.SCRUM_MASTER,
                Position.PositionName.PM,
                Position.PositionName.DEV
        };
        for (int i = 0; i < sampleData.positions.length; i++) {
            sampleData.positions[i] = new Position();
            sampleData.positions[i].id = i + 1;
            sampleData.positions[i].positionName = positionNames[i];
        }

        sampleData.accounts = new Account[5];
        String[] emails = {
                "hongnt1@gmail.com",
                "thuylt2@gmail.com",
                "nampt3@gmail.com",
                "anhnt4@gmail.com",
                "minhlt5@gmail.com"
        };
        String[] usernames = {"hongnt", "thuylt", "nampt", "anhnt", "minhlt"};
        String[] fullNames = {
                "Nguyen Thi Hong",
                "Le Thi Thuy",
                "Pham Van Nam",
                "Nguyen Thi Anh",
                "Le Minh Long"
        };

        for (int i = 0; i < sampleData.accounts.length; i++) {
            sampleData.accounts[i] = new Account();
            sampleData.accounts[i].id = i + 1;
            sampleData.accounts[i].email = emails[i];
            sampleData.accounts[i].userName = usernames[i];
            sampleData.accounts[i].fullName = fullNames[i];
            sampleData.accounts[i].department = sampleData.departments[i];
            sampleData.accounts[i].position = sampleData.positions[i];
            sampleData.accounts[i].createDate = createDate(2026, 4, i + 1, 8 + i, 15, 20);
        }

        sampleData.accounts[1].department = null;

        sampleData.groups = new Group[5];
        String[] groupNames = {
                "Java Fresher",
                "C# Fresher",
                "Java Web",
                "DevOps",
                "Testing"
        };
        for (int i = 0; i < sampleData.groups.length; i++) {
            sampleData.groups[i] = new Group();
            sampleData.groups[i].id = i + 1;
            sampleData.groups[i].name = groupNames[i];
            sampleData.groups[i].creator = sampleData.accounts[i];
            sampleData.groups[i].createDate = createDate(2026, 4, 6 + i, 9, 0, 0);
        }

        sampleData.accounts[0].groups = new Group[]{sampleData.groups[0], sampleData.groups[1], sampleData.groups[2], sampleData.groups[3]};
        sampleData.accounts[1].groups = new Group[]{sampleData.groups[0], sampleData.groups[1]};
        sampleData.accounts[2].groups = new Group[]{sampleData.groups[0]};
        sampleData.accounts[3].groups = new Group[]{sampleData.groups[2], sampleData.groups[3], sampleData.groups[4]};
        sampleData.accounts[4].groups = new Group[]{sampleData.groups[3]};

        sampleData.groups[0].accounts = new Account[]{sampleData.accounts[0], sampleData.accounts[1], sampleData.accounts[2]};
        sampleData.groups[1].accounts = new Account[]{sampleData.accounts[0], sampleData.accounts[1]};
        sampleData.groups[2].accounts = new Account[]{sampleData.accounts[0], sampleData.accounts[3]};
        sampleData.groups[3].accounts = new Account[]{sampleData.accounts[0], sampleData.accounts[3], sampleData.accounts[4]};
        sampleData.groups[4].accounts = new Account[]{sampleData.accounts[3]};

        sampleData.exams = new Exam[3];
        for (int i = 0; i < sampleData.exams.length; i++) {
            sampleData.exams[i] = new Exam();
            sampleData.exams[i].id = i + 1;
            sampleData.exams[i].code = "EX00" + (i + 1);
            sampleData.exams[i].title = "Exam " + (i + 1);
            sampleData.exams[i].duration = 45 + i * 15;
            sampleData.exams[i].creator = sampleData.accounts[i];
            sampleData.exams[i].createDate = createDate(2026, 4, 21 + i, 11, 16, 20);
        }

        return sampleData;
    }

    private static Date createDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private static class SampleData {
        Department[] departments;
        Position[] positions;
        Account[] accounts;
        Group[] groups;
        Exam[] exams;
    }
}
