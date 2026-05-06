package frontend;

import backend.Exercise5;
import entity.Department;

public class Program5 {
    public static void main(String[] args) {
        Exercise5 exercise = new Exercise5();
        Department[] departments = exercise.question1To5CreateDepartments();

        System.out.println("Câu 1: Thông tin phòng ban thứ 1: " + exercise.question1Department1ToString(departments));

        String[] all = exercise.question2AllDepartmentsToString(departments);
        for (String line : all) {
            System.out.println("Câu 2: Thông tin phòng ban: " + line);
        }

        System.out.println("Câu 3: Địa chỉ của phòng ban thứ 1: " + exercise.question3Department1Address(departments));
        System.out.println("Câu 4: Phòng ban thứ 1 có tên là \"Phòng A\": "
                + exercise.question4IsDepartment1Name(departments, "Phòng A"));
        System.out.println("Câu 5: Hai phòng ban có tên giống nhau: " + exercise.question5IsSameName(departments[0], departments[1]));

        Department[] sorting = exercise.question6CreateDepartmentsForSorting();
        Department[] sorted = exercise.question6SortDepartmentsByName(sorting);
        for (Department department : sorted) {
            System.out.println("Câu 6: Danh sách phòng ban theo thứ tự tăng dần: " + department.getName());
        }

        Department[] sortedManual = exercise.question7SortDepartmentsByNameManual(sorting);
        for (Department department : sortedManual) {
            System.out.println("Câu 7: Danh sách phòng ban sau khi sắp xếp theo tên: " + department.getName());
        }
    }
}
