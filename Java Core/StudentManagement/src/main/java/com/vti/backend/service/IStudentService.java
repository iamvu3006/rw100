package com.vti.backend.service;

import com.vti.entity.Student;

import java.time.LocalDate;
import java.util.List;

public interface IStudentService {
    List<Student> findAll();

    boolean create(String fullName, String email, LocalDate dateOfBirth, int majorId);

    boolean checkExistEmail(String email);

    boolean updateMajor(int studentId, int majorId);

    boolean deleteById(int studentId);

    List<Student> findByMajorName(String majorName);

    boolean validatePassword(String password);
}