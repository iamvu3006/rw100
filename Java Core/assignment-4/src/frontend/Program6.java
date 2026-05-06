package frontend;

import backend.Exercise6;

public class Program6 {
    public static void main(String[] args) {
        Exercise6 exercise = new Exercise6();
        int[] numbers = {1, 2, 5, 3, 4};
        System.out.println("Câu 1: Giá trị lớn nhất trong mảng: " + exercise.question1GetMaxValue(numbers));
        System.out.println("Câu 1: Giá trị nhỏ nhất trong mảng: " + exercise.question1GetMinValue(numbers));

        System.out.println("Câu 2: Tiếp tục nhập khi trả lời 'Y': "
                + (exercise.question2ShouldContinueAdding("Y") ? "Có" : "Không"));
        System.out.println("Câu 2: Tiếp tục nhập khi trả lời 'N': "
                + (exercise.question2ShouldContinueAdding("N") ? "Có" : "Không"));
    }
}
