package backend;

import entity.Department;

import java.util.Arrays;
import java.util.Comparator;

public class Exercise5 {
    public Department[] question1To5CreateDepartments() {
        Department[] departments = new Department[2];
        Department department1 = new Department();
        department1.setId(1);
        department1.setName("Phòng A");
        departments[0] = department1;

        Department department2 = new Department();
        department2.setId(2);
        department2.setName("Phòng B");
        departments[1] = department2;

        return departments;
    }

    public String question1Department1ToString(Department[] departments) {
        return departments[0].toString();
    }

    public String[] question2AllDepartmentsToString(Department[] departments) {
        String[] result = new String[departments.length];
        for (int i = 0; i < departments.length; i++) {
            result[i] = departments[i].toString();
        }
        return result;
    }

    public String question3Department1Address(Department[] departments) {
        Department department = departments[0];
        return department.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(department));
    }

    public boolean question4IsDepartment1Name(Department[] departments, String name) {
        return departments[0].getName().equals(name);
    }

    public boolean question5IsSameName(Department department1, Department department2) {
        if (department1 == null || department2 == null) {
            return false;
        }
        if (department1.getName() == null || department2.getName() == null) {
            return false;
        }
        return department1.getName().equals(department2.getName());
    }

    public Department[] question6CreateDepartmentsForSorting() {
        String[] names = {"Accounting", "Boss of director", "Marketing", "Sale", "Waiting room"};
        Department[] departments = new Department[names.length];
        for (int i = 0; i < names.length; i++) {
            Department department = new Department();
            department.setId(i + 1);
            department.setName(names[i]);
            departments[i] = department;
        }
        return departments;
    }

    public Department[] question6SortDepartmentsByName(Department[] departments) {
        Department[] sorted = Arrays.copyOf(departments, departments.length);
        Arrays.sort(sorted, Comparator.comparing(Department::getName));
        return sorted;
    }

    public Department[] question7SortDepartmentsByNameManual(Department[] departments) {
        Department[] sorted = Arrays.copyOf(departments, departments.length);
        for (int i = 0; i < sorted.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sorted.length; j++) {
                if (sorted[j].getName().compareTo(sorted[minIndex].getName()) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Department temp = sorted[i];
                sorted[i] = sorted[minIndex];
                sorted[minIndex] = temp;
            }
        }
        return sorted;
    }
}
