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

        System.out.println("Exercise1 Q1 salary1 rounded: " + roundedSalary1);
        System.out.println("Exercise1 Q1 salary2 rounded: " + roundedSalary2);
        System.out.println("Exercise1 Q2 random 5 digits: " + fiveDigits);
        System.out.println("Exercise1 Q3 last two digits: " + lastTwoDigits);
        System.out.println("Exercise1 Q4 divide 10/3: " + quotient);
    }
}

