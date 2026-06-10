package com.vti.backend.service.impl;

import com.vti.backend.repository.IStudentRepository;
import com.vti.backend.repository.impl.StudentRepositoryImpl;
import com.vti.backend.service.IStudentService;
import com.vti.common.StringCommon;
import com.vti.entity.Student;

import java.time.LocalDate;
import java.util.List;

public class StudentServiceImpl implements IStudentService {
    private final IStudentRepository studentRepository = new StudentRepositoryImpl();

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public boolean create(String fullName, String email, LocalDate dateOfBirth, int majorId) {
        return studentRepository.create(fullName, email, dateOfBirth, majorId);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return studentRepository.checkExistEmail(email);
    }

    @Override
    public boolean updateMajor(int studentId, int majorId) {
        // Kiểm tra các ID đầu vào hợp lệ
        if (studentId <= 0 || majorId <= 0) {
            System.out.println("ID không hợp lệ!");
            return false;
        }
        return studentRepository.updateMajor(studentId, majorId);
    }

    @Override
    public boolean deleteById(int studentId) {
        // Kiểm tra ID hợp lệ
        if (studentId <= 0) {
            System.out.println("ID sinh viên không hợp lệ!");
            return false;
        }
        return studentRepository.deleteById(studentId);
    }

    @Override
    public List<Student> findByMajorName(String majorName) {
        return studentRepository.findByMajorName(majorName);
    }

    @Override
    public boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return password.matches(StringCommon.PASSWORD_REGEX);
    }
}