package com.vti.utils;

import java.util.Objects;
import java.util.Scanner;

public class ScannerUtils { //hỗ trợ nhập và validation dữ liệu
    private static Scanner scanner = new Scanner(System.in);

    public static String inputString() {
        String text;
        while (true){
            text = scanner.nextLine();
            if (Objects.isNull(text) || text.trim().isEmpty()) {
                System.out.println("Input không được để trống. Vui lòng nhập lại.");
            } else {
                return text;
            }
        }
    }

    public static Integer inputInt() {
        String text;
        while (true) {
            text = scanner.nextLine();
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("Input phải là số nguyên. Vui lòng nhập lại.");
            }
        }
    }

    public static Integer inputIntGreaterThanZero() {
        String text;
        while (true) {
            Integer integer = ScannerUtils.inputInt();
            if (integer <= 0) {
                System.out.println("Input phải là số nguyên lớn hơn 0. Vui lòng nhập lại.");
            } else {
                return integer;
            }
        }
    }

    public static String inputEmail() {
        String email;
        while (true) {
            email = ScannerUtils.inputString();

            //biểu thức chính quy
            String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            if (!email.matches(regex)) {
                System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
            } else {
                return email;
            }
        }
    }
}
