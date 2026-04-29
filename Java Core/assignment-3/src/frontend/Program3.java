package frontend;

import backend.Exercise3;

public class Program3 {
    public static void main(String[] args) {
        Exercise3 exercise = new Exercise3();
        System.out.println("Câu 1: Lương sau khi convert sang float: " + exercise.question1ConvertSalary());
        System.out.println("Câu 2: Chuyển String sang int: " + exercise.question2StringToInt());
        System.out.println("Câu 3: Chuyển Integer sang int: " + exercise.question3IntegerToInt());
    }
}
