package frontend;

import backend.QLAccount;
import backend.QLDepartment;
import backend.QLPosition;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        //QLDepartment
//        QLDepartment.showDepartment();
//        QLDepartment.findByNameAndId("Sale", 2);
//        QLDepartment.showDepartmentHasAtLeast2Employees();
//
//        //QLPosition
//        QLPosition.showAllPosition();
//        QLPosition.findByPositionName("Dev");
//
//        //QLAccount
//        QLAccount.showAllAccount();
//        QLAccount.findByFullname("Nguyen Van A");
//        QLAccount.findByFullnameAndUsername("Nguyen Van An", "annguyen");

        //insertDepartment();
        //deleteDepartment();
        updateDepartment();
    }
    public static void insertDepartment () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên phòng ban mới:");
        String name = scanner.nextLine();
        boolean check = QLDepartment.createDepartment(name);
        if (check) {
            System.out.println("Thêm phòng ban thành công");
        } else {
            System.out.println("Thêm phòng ban thất bại");
        }
    }

    public static void deleteDepartment () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên phòng ban cần xóa:");
        String name = scanner.nextLine();
        boolean check = QLDepartment.deleteDepartment(name);
        if (check) {
            System.out.println("Xóa phòng ban thành công");
        } else {
            System.out.println("Xóa phòng ban thất bại");
        }
    }

    public static void updateDepartment () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập ID phòng ban cần update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc bỏ dòng mới sau khi đọc số nguyên

        System.out.println("Nhập tên phòng ban sau khi update:");
        String name = scanner.nextLine();
        boolean check = QLDepartment.updateDepartment(id, name);
        if (check) {
            System.out.println("Update phòng ban thành công");
        } else {
            System.out.println("Update phòng ban thất bại");
        }
    }
}