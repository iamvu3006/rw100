package frontend;

import backend.Exercise1;

public class Program1 {
    public static void main(String[] args) {
        Exercise1 exercise = new Exercise1();
        int roundedSalary1 = exercise.question1RoundSalary1();
        int roundedSalary2 = exercise.question1RoundSalary2();
        String fiveDigits = exercise.question2RandomFiveDigits();
        String lastTwoDigits = exercise.question3LastTwoDigits(fiveDigits);
        float quotient = exercise.question4Divide(10, 3);

        System.out.println("Câu 1: Làm tròn lương tài khoản 1: " + roundedSalary1);
        System.out.println("Câu 1: Làm tròn lương tài khoản 2: " + roundedSalary2);
        System.out.println("Câu 2: Số ngẫu nhiên có 5 chữ số: " + fiveDigits);
        System.out.println("Câu 3: Hai số cuối của số ở Câu 2: " + lastTwoDigits);
        System.out.println("Câu 4: Thương của 10 và 3: " + quotient);
    }
}
