package backend;

import entity.Student;

public class Exercise7 {
    public Student createStudent(String name, String hometown) {
        return new Student(name, hometown);
    }

    public void setGradePoint(Student student, float gradePoint) {
        if (student == null) {
            return;
        }
        student.setGradePoint(gradePoint);
    }

    public void addGradePoint(Student student, float additionalPoint) {
        if (student == null) {
            return;
        }
        student.addGradePoint(additionalPoint);
    }

    public String getStudentInfo(Student student) {
        if (student == null) {
            return "Không có thông tin sinh viên.";
        }
        return student.getDisplayInfo();
    }
}

