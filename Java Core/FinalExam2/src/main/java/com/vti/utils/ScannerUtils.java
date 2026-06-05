package com.vti.utils;

import java.util.Scanner;

public final class ScannerUtils {

    private ScannerUtils() {
    }

    public static String inputRequiredString(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Giá trị không được để trống.");
        }
    }

    public static int inputInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException exception) {
                System.out.println("Vui lòng nhập số hợp lệ.");
            }
        }
    }
}
