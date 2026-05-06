package frontend;

import backend.QLCB;
import entity.CongNhan;
import entity.KySu;
import entity.NhanVien;
import entity.Officer;
import enums.Gender;

import java.util.List;
import java.util.Scanner;

public class Program8 {
    public static void main(String[] args) {
        QLCB qlcb = new QLCB();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== QUẢN LÝ CÁN BỘ ==========");
            System.out.println("1. Thêm mới cán bộ");
            System.out.println("2. Tìm kiếm theo họ tên");
            System.out.println("3. Hiện thị thông tin danh sách cán bộ");
            System.out.println("4. Xóa cán bộ theo tên");
            System.out.println("5. Thoát khỏi chương trình");
            System.out.print("Chọn chức năng: ");

            int choice = readInt(scanner);
            switch (choice) {
                case 1:
                    addOfficer(scanner, qlcb);
                    break;
                case 2:
                    searchOfficer(scanner, qlcb);
                    break;
                case 3:
                    displayOfficers(qlcb);
                    break;
                case 4:
                    deleteOfficer(scanner, qlcb);
                    break;
                case 5:
                    System.out.println("Đã thoát khỏi chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Vui lòng chọn chức năng từ 1 đến 5.");
            }
        }
    }

    private static void addOfficer(Scanner scanner, QLCB qlcb) {
        System.out.println("Chọn loại cán bộ cần thêm:");
        System.out.println("1. Công nhân");
        System.out.println("2. Kỹ sư");
        System.out.println("3. Nhân viên");
        System.out.print("Lựa chọn: ");
        int type = readInt(scanner);

        System.out.print("Nhập họ tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập tuổi: ");
        int age = readInt(scanner);
        Gender gender = readGender(scanner);
        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();

        Officer officer;
        switch (type) {
            case 1:
                CongNhan worker = new CongNhan(name, age, gender, address, 1);
                System.out.print("Nhập bậc (1 đến 10): ");
                scanner.nextLine();
                worker.setBac(readInt(scanner));
                officer = worker;
                break;
            case 2:
                System.out.print("Nhập ngành đào tạo: ");
                String nganh = scanner.nextLine();
                officer = new KySu(name, age, gender, address, nganh);
                break;
            case 3:
                System.out.print("Nhập công việc: ");
                String congViec = scanner.nextLine();
                officer = new NhanVien(name, age, gender, address, congViec);
                break;
            default:
                System.out.println("Loại cán bộ không hợp lệ.");
                return;
        }

        qlcb.addOfficer(officer);
        System.out.println("Đã thêm cán bộ thành công.");
    }

    private static void searchOfficer(Scanner scanner, QLCB qlcb) {
        System.out.print("Nhập họ tên cần tìm: ");
        String name = scanner.nextLine();
        List<Officer> officers = qlcb.searchOfficersByName(name);

        if (officers.isEmpty()) {
            System.out.println("Không tìm thấy cán bộ phù hợp.");
            return;
        }

        System.out.println("Danh sách cán bộ tìm được:");
        for (Officer officer : officers) {
            System.out.println(officer.getDisplayInfo());
        }
    }

    private static void displayOfficers(QLCB qlcb) {
        List<String> details = qlcb.getOfficerDetails();
        if (details.isEmpty()) {
            System.out.println("Danh sách cán bộ đang trống.");
            return;
        }

        System.out.println("Danh sách cán bộ:");
        for (String detail : details) {
            System.out.println(detail);
        }
    }

    private static void deleteOfficer(Scanner scanner, QLCB qlcb) {
        System.out.print("Nhập tên cán bộ cần xóa: ");
        String name = scanner.nextLine();
        boolean deleted = qlcb.deleteOfficerByName(name);
        if (deleted) {
            System.out.println("Đã xóa cán bộ thành công.");
        } else {
            System.out.println("Không tìm thấy cán bộ cần xóa.");
        }
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException exception) {
                System.out.print("Vui lòng nhập một số nguyên hợp lệ: ");
            }
        }
    }

    private static Gender readGender(Scanner scanner) {
        while (true) {
            System.out.println("Chọn giới tính:");
            System.out.println("1. Nam");
            System.out.println("2. Nữ");
            System.out.println("3. Khác");
            System.out.print("Lựa chọn: ");

            int choice = readInt(scanner);
            switch (choice) {
                case 1:
                    return Gender.MALE;
                case 2:
                    return Gender.FEMALE;
                case 3:
                    return Gender.OTHER;
                default:
                    System.out.println("Lựa chọn giới tính không hợp lệ.");
            }
        }
    }
}

