package com.vti.enums;

public enum Role {
    ADMIN,
    EMPLOYEE;

    public static Role fromDatabaseValue(String value) {
        return Role.valueOf(value.toUpperCase());
    }

    public String toDatabaseValue() {
        return name();
    }
}
