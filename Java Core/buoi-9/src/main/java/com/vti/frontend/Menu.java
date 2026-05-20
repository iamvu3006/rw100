package com.vti.frontend;

import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);

    public void run() throws ClassNotFoundException {
        while (true) {
            System.out.println("=== Mời bạn chọn chức năng ===");
            System.out.println("1. Làm việc với department");
            System.out.println("2. Làm việc với position");
            System.out.println("3. Làm việc với account");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    DepartmentFunction departmentFunction = new DepartmentFunction();
                    departmentFunction.run();
                    break;
                case "2":
                    PositionFunction positionFunction = new PositionFunction();
                    positionFunction.run();
                    break;
                case "3":
                    AccountFunction accountFunction = new AccountFunction();
                    accountFunction.run();
                    break;
                default:
                    System.out.println("Mời chọn lại");
            }
        }
    }
}