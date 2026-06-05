package com.vti.utils;

import java.util.regex.Pattern;

public final class ValidateUtils {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])[A-Za-z0-9]{6,12}$");
    private static final Pattern FULL_NAME_PATTERN = Pattern.compile("^[\\p{L} ]+$");

    private ValidateUtils() {
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isValidLoginPassword(String password) {
        return "123456".equals(password) || isValidPassword(password);
    }

    public static boolean isValidFullName(String fullName) {
        return fullName != null && FULL_NAME_PATTERN.matcher(fullName.trim()).matches();
    }
}
