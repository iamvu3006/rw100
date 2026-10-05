import java.util.Scanner;

import com.vti.backend.StudentBackend;
import com.vti.entity.Student;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentBackend backend = new StudentBackend();

        System.out.println("=== QUAN LY SINH VIEN ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (!backend.login(username, password)) {
            System.out.println("Dang nhap that bai!");
            scanner.close();
            return;
        }

        boolean isAdmin = backend.isAdmin(username);
        System.out.println("Dang nhap thanh cong voi quyen " + (isAdmin ? "admin." : "user."));
        int choice;
        do {
            printMenu(isAdmin);
            choice = readInt(scanner, "Chon chuc nang: ");
            switch (choice) {
            case 1:
                if (isAdmin) {
                    addStudent(scanner, backend);
                } else {
                    printPermissionDenied();
                }
                break;
            case 2:
                if (isAdmin) {
                    updateStudent(scanner, backend);
                } else {
                    printPermissionDenied();
                }
                break;
            case 3:
                if (isAdmin) {
                    deleteStudent(scanner, backend);
                } else {
                    printPermissionDenied();
                }
                break;
            case 4:
                System.out.println("--- DANH SACH SINH VIEN ---");
                backend.getAllStudent("");
                break;
            case 5:
                findStudent(scanner, backend);
                break;
            case 6:
                checkStudent(scanner, backend);
                break;
            case 0:
                System.out.println("Ket thuc chuong trinh.");
                break;
            default:
                System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu(boolean isAdmin) {
        if (isAdmin) {
            System.out.println("\n1. Them sinh vien");
            System.out.println("2. Sua sinh vien");
            System.out.println("3. Xoa sinh vien");
        }
        System.out.println("4. Hien thi tat ca sinh vien");
        System.out.println("5. Tim sinh vien theo id");
        System.out.println("6. Kiem tra sinh vien ton tai");
        System.out.println("0. Thoat");
    }

    private static void printPermissionDenied() {
        System.out.println("Ban khong co quyen thuc hien chuc nang nay.");
    }

    private static void addStudent(Scanner scanner, StudentBackend backend) {
        Student student = readStudent(scanner, false);
        if (backend.exists(String.valueOf(student.getId()))) {
            System.out.println("Id sinh vien da ton tai.");
            return;
        }
        backend.addStudent(student);
        System.out.println("Them sinh vien thanh cong.");
    }

    private static void updateStudent(Scanner scanner, StudentBackend backend) {
        Student student = readStudent(scanner, true);
        if (!backend.exists(String.valueOf(student.getId()))) {
            System.out.println("Khong tim thay sinh vien.");
            return;
        }
        backend.updateStudent(student);
        System.out.println("Cap nhat sinh vien thanh cong.");
    }

    private static void deleteStudent(Scanner scanner, StudentBackend backend) {
        String id = readText(scanner, "Nhap id sinh vien can xoa: ");
        Student student = backend.findStudentById(id);
        if (student == null) {
            System.out.println("Khong tim thay sinh vien.");
            return;
        }
        backend.deleteStudent(student);
        System.out.println("Xoa sinh vien thanh cong.");
    }

    private static void findStudent(Scanner scanner, StudentBackend backend) {
        String id = readText(scanner, "Nhap id sinh vien can tim: ");
        Student student = backend.findStudentById(id);
        if (student == null) {
            System.out.println("Khong tim thay sinh vien.");
            return;
        }
        backend.printStudent(student);
    }

    private static void checkStudent(Scanner scanner, StudentBackend backend) {
        String id = readText(scanner, "Nhap id sinh vien: ");
        System.out.println(backend.exists(id) ? "Sinh vien ton tai." : "Sinh vien khong ton tai.");
    }

    private static Student readStudent(Scanner scanner, boolean updating) {
        String action = updating ? "sua" : "them";
        System.out.println("Nhap thong tin sinh vien can " + action + ":");
        int id = readInt(scanner, "Id: ");
        String name = readText(scanner, "Ten: ");
        int age = readInt(scanner, "Tuoi: ");
        double score = readDouble(scanner, "Diem: ");
        return new Student(id, name, age, score);
    }

    private static int readInt(Scanner scanner, String message) {
        while (true) {
            try {
                return Integer.parseInt(readText(scanner, message));
            } catch (NumberFormatException exception) {
                System.out.println("Vui long nhap mot so nguyen hop le.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String message) {
        while (true) {
            try {
                return Double.parseDouble(readText(scanner, message));
            } catch (NumberFormatException exception) {
                System.out.println("Vui long nhap mot so thuc hop le.");
            }
        }
    }

    private static String readText(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}
