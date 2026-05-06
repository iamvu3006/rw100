package frontend;

import backend.Exercise7;
import entity.Student;

import java.util.Scanner;

public class Program7 {
    public static void main(String[] args) {
        Exercise7 exercise = new Exercise7();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập tên sinh viên: ");
        String name = scanner.nextLine();

        System.out.print("Nhập quê quán của sinh viên: ");
        String hometown = scanner.nextLine();

        Student student = exercise.createStudent(name, hometown);
        System.out.println("Câu 1: Đã tạo sinh viên với điểm học lực mặc định là 0.");
        System.out.println("Câu 1: " + exercise.getStudentInfo(student));

        exercise.setGradePoint(student, 5.5f);
        System.out.println("Câu 2: Sau khi gán điểm 5.5: " + exercise.getStudentInfo(student));

        exercise.addGradePoint(student, 2.0f);
        System.out.println("Câu 3: Sau khi cộng thêm 2.0 điểm: " + exercise.getStudentInfo(student));

        scanner.close();
    }
}

