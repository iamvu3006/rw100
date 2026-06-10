package com.vti.backend.controller;

import com.vti.backend.service.IStudentService;
import com.vti.backend.service.impl.StudentServiceImpl;
import com.vti.entity.Student;

import java.time.LocalDate;
import java.util.List;

public class StudentController {

    private final IStudentService studentService = new StudentServiceImpl();

    public List<Student> findAll() {
        return studentService.findAll();
    }

    public boolean create(String fullName, String email, LocalDate dateOfBirth, int majorId) {
        return studentService.create(fullName, email, dateOfBirth, majorId);
    }

    public boolean checkExistEmail(String email) {
        return studentService.checkExistEmail(email);
    }

    public boolean updateMajor(int studentId, int majorId) {
        return studentService.updateMajor(studentId, majorId);
    }

    public boolean deleteById(int studentId) {
        return studentService.deleteById(studentId);
    }

    public List<Student> findByMajorName(String majorName) {
        return studentService.findByMajorName(majorName);
    }

    public boolean validatePassword(String password) {
        return studentService.validatePassword(password);
    }
}
