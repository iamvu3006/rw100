package frontend;

import backend.Exercise6;

public class Program6 {
    public static void main(String[] args) {
        Exercise6 exercise = new Exercise6();
        int[] numbers = {1, 2, 5, 3, 4};
        System.out.println("Exercise6 Q1 max: " + exercise.question1GetMaxValue(numbers));
        System.out.println("Exercise6 Q1 min: " + exercise.question1GetMinValue(numbers));

        System.out.println("Exercise6 Q2 continue with Y: " + exercise.question2ShouldContinueAdding("Y"));
        System.out.println("Exercise6 Q2 continue with N: " + exercise.question2ShouldContinueAdding("N"));
    }
}

