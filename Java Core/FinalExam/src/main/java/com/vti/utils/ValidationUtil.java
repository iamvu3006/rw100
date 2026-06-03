package com.vti.utils;

import com.vti.enums.GraduationRank;

import java.util.regex.Pattern;

public class ValidationUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{9,12}$");
    private static final Pattern PASSWORD_UPPER = Pattern.compile(".*[A-Z].*");

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidExpInYear(int year) {
        return year >= 0 && year <= 10;
    }

    public static boolean isValidPassword(String password) {
        if (password == null) return false;
        int len = password.length();
        if (len < 6 || len > 12) return false;
        return PASSWORD_UPPER.matcher(password).matches();
    }

    public static GraduationRank parseGraduationRank(String input) {
        if (input == null) return null;
        switch (input.trim().toLowerCase()) {
            case "excellence":
                return GraduationRank.EXCELLENCE;
            case "good":
                return GraduationRank.GOOD;
            case "fair":
                return GraduationRank.FAIR;
            case "poor":
                return GraduationRank.POOR;
            default:
                return null;
        }
    }
}
