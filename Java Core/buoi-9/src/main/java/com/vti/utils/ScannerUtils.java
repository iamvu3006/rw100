package com.vti.utils;

import java.util.Objects;
import java.util.Scanner;

public class ScannerUtils {// ho tro nhapp + validation du lieu
    private static Scanner scanner = new Scanner(System.in);
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+.-]+@[a-zA-Z0-9.-]+$";
    public static final String NUMBER_REGEX = "^[0-9]+$";
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final Integer ZERO = 0;

    public static String inputString() {
        String text;
        while (true) {
            text = scanner.nextLine();
            if (Objects.isNull(text) || text.trim().isEmpty()) {
                System.out.println("Nhập lại:");
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
            } catch (Exception e) {
                System.out.println("Vui long nhập số, chọn lại:");
            }
        }
    }

    public static Integer inputIntGreaterThenZero() {
        while (true) {
            Integer integer = ScannerUtils.inputInt();
            if (integer <= 0) {
                System.out.println("Vui long nhập số > 0");
            } else  {
                return integer;
            }
        }
    }

    public static String inputEmail() {
        String email;
        while (true) {
            // kiem tra xem có null  hay empty ko
            email = ScannerUtils.inputString();

            // bieu thuc chinh quy
            String regex = EMAIL_REGEX;
            if (!email.matches(regex)) {
                System.out.println("Sai định dạng email. Nhập lại:");
            } else  {
                return email;
            }
        }
    }

}
