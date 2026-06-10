package com.vti.frontend;

import com.vti.backend.controller.AccountController;
import com.vti.backend.controller.LecturerController;
import com.vti.backend.controller.StudentController;
import com.vti.entity.Account;
import com.vti.entity.Lecturer;
import com.vti.entity.Student;
import com.vti.utils.ScannerUtils;

import java.time.LocalDate;
import java.util.List;

public class StudentFunction {

    private final AccountController  accountController  = new AccountController();
    private final StudentController  studentController  = new StudentController();
    private final LecturerController lecturerController = new LecturerController();

    private static final int MAX_LOGIN_ATTEMPTS = 3;

    public void login() {
        System.out.println("+------------------------------------------+");
        System.out.println("|         HỆ THỐNG QUẢN LÝ SINH VIÊN      |");
        System.out.println("+------------------------------------------+");

        int attempts = 0;

        while (attempts < MAX_LOGIN_ATTEMPTS) {
            System.out.print("Nhập Email   : ");
            String email = ScannerUtils.inputEmail();

            System.out.print("Nhập Password: ");
            String password = ScannerUtils.inputPassword();

            // Gọi controller xác thực
            Account account = accountController.login(email, password);

            if (account != null) {
                System.out.println("\nĐăng nhập thành công! Xin chào, " + account.getFullName() + "!");
                // Chuyển sang menu chính
                showMenu();
                return;
            } else {
                attempts++;
                int remaining = MAX_LOGIN_ATTEMPTS - attempts;
                if (remaining > 0) {
                    System.out.println("Email hoặc mật khẩu không đúng! Còn " + remaining + " lần thử.");
                } else {
                    System.out.println("Bạn đã nhập sai quá " + MAX_LOGIN_ATTEMPTS + " lần. Chương trình kết thúc.");
                }
            }
        }
    }

    private void showMenu() {
        boolean running = true;

        while (running) {
            printMenuBorder();

            System.out.print("Nhập lựa chọn: ");
            int choice = ScannerUtils.inputInt();

            switch (choice) {
                case 1:
                    showAllStudents();
                    break;
                case 2:
                    addNewStudent();
                    break;
                case 3:
                    updateStudentMajor();
                    break;
                case 4:
                    deleteStudentById();
                    break;
                case 5:
                    findStudentByMajor();
                    break;
                case 6:
                    findLecturerById();
                    break;
                case 7:
                    checkPassword();
                    break;
                case 8:
                    System.out.println("Tạm biệt! Hẹn gặp lại.");
                    running = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn từ 1 đến 8.");
            }
        }
    }

    private void printMenuBorder() {
        System.out.println("\n+------------------------------------------+");
        System.out.println("|           MỜI BẠN CHỌN CHỨC NĂNG        |");
        System.out.println("+------------------------------------------+");
        System.out.println("| 1. Xem danh sách thông tin sinh viên     |");
        System.out.println("| 2. Thêm sinh viên mới                    |");
        System.out.println("| 3. Cập nhật chuyên ngành cho sinh viên   |");
        System.out.println("| 4. Xóa sinh viên theo ID                 |");
        System.out.println("| 5. Tìm kiếm sinh viên theo chuyên ngành  |");
        System.out.println("| 6. Tìm kiếm giảng viên theo ID           |");
        System.out.println("| 7. Chức năng kiểm tra Password           |");
        System.out.println("| 8. Exit                                  |");
        System.out.println("+------------------------------------------+");
    }

    private void showAllStudents() {
        System.out.println("\n===== DANH SÁCH SINH VIÊN =====");
        List<Student> students = studentController.findAll();

        if (students.isEmpty()) {
            System.out.println("Không có sinh viên nào trong hệ thống.");
            return;
        }

        printStudentTableHeader();
        for (Student student : students) {
            printStudentRow(student);
        }
        printTableFooter();
        System.out.println("Tổng số sinh viên: " + students.size());
    }

    private void addNewStudent() {
        System.out.println("\n===== THÊM SINH VIÊN MỚI =====");

        System.out.print("Nhập họ tên sinh viên: ");
        String fullName = ScannerUtils.inputString();

        System.out.print("Nhập email: ");
        String email = ScannerUtils.inputEmail();

        // Kiểm tra email đã tồn tại chưa
        if (studentController.checkExistEmail(email)) {
            System.out.println("Email \"" + email + "\" đã tồn tại trong hệ thống! Không thể thêm.");
            return;
        }

        System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
        LocalDate dateOfBirth = ScannerUtils.inputDate();

        System.out.print("Nhập ID chuyên ngành: ");
        int majorId = ScannerUtils.inputID();

        // Gọi controller thực hiện thêm mới
        boolean success = studentController.create(fullName, email, dateOfBirth, majorId);

        if (success) {
            System.out.println("Thêm sinh viên \"" + fullName + "\" thành công!");
        } else {
            System.out.println("Thêm sinh viên thất bại! Vui lòng kiểm tra lại ID chuyên ngành.");
        }
    }

    private void updateStudentMajor() {
        System.out.println("\n===== CẬP NHẬT CHUYÊN NGÀNH CHO SINH VIÊN =====");

        System.out.print("Nhập ID sinh viên: ");
        int studentId = ScannerUtils.inputID();

        System.out.print("Nhập ID chuyên ngành mới: ");
        int majorId = ScannerUtils.inputID();

        // Gọi controller thực hiện cập nhật
        boolean success = studentController.updateMajor(studentId, majorId);

        if (success) {
            System.out.println("Cập nhật chuyên ngành thành công cho sinh viên ID = " + studentId);
        } else {
            System.out.println("Cập nhật thất bại! Sinh viên ID = " + studentId + " không tồn tại hoặc ID chuyên ngành không hợp lệ.");
        }
    }

    private void deleteStudentById() {
        System.out.println("\n===== XÓA SINH VIÊN THEO ID =====");

        System.out.print("Nhập ID sinh viên cần xóa: ");
        int studentId = ScannerUtils.inputID();

        // Xác nhận trước khi xóa
        System.out.print("Bạn có chắc muốn xóa sinh viên ID = " + studentId + "? (y/n): ");
        String confirm = ScannerUtils.inputString();

        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("Hủy thao tác xóa.");
            return;
        }

        // Gọi controller thực hiện xóa
        boolean success = studentController.deleteById(studentId);

        if (success) {
            System.out.println("Xóa sinh viên ID = " + studentId + " thành công!");
        } else {
            System.out.println("Xóa thất bại! Sinh viên ID = " + studentId + " không tồn tại.");
        }
    }

    private void findStudentByMajor() {
        System.out.println("\n===== TÌM KIẾM SINH VIÊN THEO CHUYÊN NGÀNH =====");

        System.out.print("Nhập tên chuyên ngành: ");
        String majorName = ScannerUtils.inputString();

        // Gọi controller tìm kiếm
        List<Student> students = studentController.findByMajorName(majorName);

        if (students.isEmpty()) {
            System.out.println("Không tìm thấy sinh viên nào thuộc chuyên ngành \"" + majorName + "\".");
            return;
        }

        System.out.println("Kết quả tìm kiếm theo chuyên ngành: \"" + majorName + "\"");
        printStudentTableHeader();
        for (Student student : students) {
            printStudentRow(student);
        }
        printTableFooter();
        System.out.println("Tổng số kết quả: " + students.size());
    }

    private void findLecturerById() {
        System.out.println("\n===== TÌM KIẾM GIẢNG VIÊN THEO ID =====");

        System.out.print("Nhập ID giảng viên: ");
        int lecturerId = ScannerUtils.inputID();

        // Gọi controller tìm kiếm
        Lecturer lecturer = lecturerController.findById(lecturerId);

        if (lecturer == null) {
            System.out.println("Không tìm thấy giảng viên với ID = " + lecturerId);
        } else {
            System.out.println("\n--- Thông tin giảng viên ---");
            System.out.printf("%-15s: %d%n",    "ID",         lecturer.getLecturerId());
            System.out.printf("%-15s: %s%n",    "Họ tên",     lecturer.getFullName());
            System.out.printf("%-15s: %s%n",    "Email",      lecturer.getEmail());
            System.out.printf("%-15s: %s%n",    "Bộ môn",     lecturer.getDepartment());
            System.out.println("----------------------------");
        }
    }

    private void checkPassword() {
        System.out.println("\n===== KIỂM TRA PASSWORD =====");
        System.out.println("Tiêu chí hợp lệ:");
        System.out.println("  a. Độ dài từ 8 đến 20 ký tự");
        System.out.println("  b. Có ít nhất 1 chữ IN HOA (A-Z)");
        System.out.println("  c. Có ít nhất 1 chữ thường (a-z)");
        System.out.println("  d. Có ít nhất 1 chữ số (0-9)");
        System.out.println("  e. Có ít nhất 1 ký tự đặc biệt: @#$%^&+!");
        System.out.println("  f. Không chứa khoảng trắng");
        System.out.println("-----------------------------");

        System.out.print("Nhập password cần kiểm tra: ");
        String password = ScannerUtils.inputPassword();

        // Hiển thị chi tiết từng tiêu chí
        boolean lenOk        = password.length() >= 8 && password.length() <= 20;
        boolean upperOk      = password.matches(".*[A-Z].*");
        boolean lowerOk      = password.matches(".*[a-z].*");
        boolean digitOk      = password.matches(".*[0-9].*");
        boolean specialOk    = password.matches(".*[@#$%^&+!].*");
        boolean noSpaceOk    = !password.contains(" ");

        System.out.println("\n--- Kết quả kiểm tra ---");
        System.out.println("  a. Độ dài 8-20 ký tự       : " + (lenOk     ? "✔ ĐẠT" : "✘ KHÔNG ĐẠT (độ dài hiện tại: " + password.length() + ")"));
        System.out.println("  b. Có chữ IN HOA            : " + (upperOk   ? "✔ ĐẠT" : "✘ KHÔNG ĐẠT"));
        System.out.println("  c. Có chữ thường            : " + (lowerOk   ? "✔ ĐẠT" : "✘ KHÔNG ĐẠT"));
        System.out.println("  d. Có chữ số                : " + (digitOk   ? "✔ ĐẠT" : "✘ KHÔNG ĐẠT"));
        System.out.println("  e. Có ký tự đặc biệt        : " + (specialOk ? "✔ ĐẠT" : "✘ KHÔNG ĐẠT"));
        System.out.println("  f. Không chứa khoảng trắng  : " + (noSpaceOk ? "✔ ĐẠT" : "✘ KHÔNG ĐẠT"));

        // Kết luận tổng hợp
        boolean isValid = studentController.validatePassword(password);
        System.out.println("------------------------");
        if (isValid) {
            System.out.println("=> KẾT QUẢ: Password HỢP LỆ ✔");
        } else {
            System.out.println("=> KẾT QUẢ: Password KHÔNG HỢP LỆ ✘");
        }
    }

    private void printStudentTableHeader() {
        System.out.println("+--------+-----------------------------+----------------------------+------------+------------------------+");
        System.out.printf("| %-6s | %-27s | %-26s | %-10s | %-22s |%n",
                "ID", "Họ tên", "Email", "Ngày sinh", "Chuyên ngành");
        System.out.println("+--------+-----------------------------+----------------------------+------------+------------------------+");
    }

    private void printStudentRow(Student student) {
        String dob = (student.getDateOfBirth() != null)
                ? student.getDateOfBirth().toString() : "N/A";
        String major = (student.getMajorName() != null)
                ? student.getMajorName() : "N/A";

        System.out.printf("| %-6d | %-27s | %-26s | %-10s | %-22s |%n",
                student.getStudentId(),
                student.getFullName(),
                student.getEmail(),
                dob,
                major);
    }

    private void printTableFooter() {
        System.out.println("+--------+-----------------------------+----------------------------+------------+------------------------+");
    }
}