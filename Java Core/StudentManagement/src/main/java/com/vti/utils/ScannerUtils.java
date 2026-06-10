package com.vti.utils;

import com.vti.common.StringCommon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ScannerUtils {

    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static int inputInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.print("Nhập lại (phải là số nguyên): ");
            }
        }
    }

    public static int inputID() {
        while (true) {
            int number = ScannerUtils.inputInt();
            if (number > 0) {
                return number;
            } else {
                System.out.print("ID phải lớn hơn 0! Nhập lại: ");
            }
        }
    }

    public static String inputString() {
        while (true) {
            String input = sc.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input.trim();
            } else {
                System.out.print("Không được để trống! Nhập lại: ");
            }
        }
    }

    public static String inputEmail() {
        while (true) {
            String email = sc.nextLine().trim();
            if (email.isEmpty() || !email.matches(StringCommon.EMAIL_REGEX)) {
                System.out.print("Email không đúng định dạng! Nhập lại: ");
            } else {
                return email;
            }
        }
    }

    public static LocalDate inputDate() {
        while (true) {
            try {
                String input = sc.nextLine().trim();
                return LocalDate.parse(input, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.print("Ngày sinh không hợp lệ! Định dạng dd/MM/yyyy. Nhập lại: ");
            }
        }
    }

    public static String inputPassword() {
        return ScannerUtils.inputString();
    }
}