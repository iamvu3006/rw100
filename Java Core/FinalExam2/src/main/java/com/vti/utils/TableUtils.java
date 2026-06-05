package com.vti.utils;

import com.vti.entity.User;

import java.util.List;

public final class TableUtils {

    private TableUtils() {
    }

    public static void printUserSummaryTable(List<User> users) {
        System.out.println("+------+---------------------------+--------------------------------+");
        System.out.println("| ID   | Full Name                 | Email                          |");
        System.out.println("+------+---------------------------+--------------------------------+");
        for (User user : users) {
            System.out.printf("| %-4d | %-25s | %-30s |%n", user.getId(), user.getFullName(), user.getEmail());
        }
        System.out.println("+------+---------------------------+--------------------------------+");
    }
}
