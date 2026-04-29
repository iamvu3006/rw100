package frontend;

import backend.Exercise5;
import entity.Department;

public class Program5 {
    public static void main(String[] args) {
        Exercise5 exercise = new Exercise5();
        Department[] departments = exercise.question1To5CreateDepartments();

        System.out.println("Exercise5 Q1: " + exercise.question1Department1ToString(departments));

        String[] all = exercise.question2AllDepartmentsToString(departments);
        for (String line : all) {
            System.out.println("Exercise5 Q2: " + line);
        }

        System.out.println("Exercise5 Q3 address: " + exercise.question3Department1Address(departments));
        System.out.println("Exercise5 Q4 name is Phong A: " + exercise.question4IsDepartment1Name(departments, "Phong A"));
        System.out.println("Exercise5 Q5 same name: " + exercise.question5IsSameName(departments[0], departments[1]));

        Department[] sorting = exercise.question6CreateDepartmentsForSorting();
        Department[] sorted = exercise.question6SortDepartmentsByName(sorting);
        for (Department department : sorted) {
            System.out.println("Exercise5 Q6 sorted: " + department.name);
        }

        Department[] sortedManual = exercise.question7SortDepartmentsByNameManual(sorting);
        for (Department department : sortedManual) {
            System.out.println("Exercise5 Q7 sorted: " + department.name);
        }
    }
}

