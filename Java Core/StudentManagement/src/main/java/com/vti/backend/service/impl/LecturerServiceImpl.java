package com.vti.backend.service.impl;

import com.vti.backend.repository.ILecturerRepository;
import com.vti.backend.repository.impl.LecturerRepositoryImpl;
import com.vti.backend.service.ILecturerService;
import com.vti.entity.Lecturer;

public class LecturerServiceImpl implements ILecturerService {

    private final ILecturerRepository lecturerRepository = new LecturerRepositoryImpl();

    @Override
    public Lecturer findById(int lecturerId) {
        // Kiểm tra ID hợp lệ (phải > 0)
        if (lecturerId <= 0) {
            System.out.println("ID giảng viên không hợp lệ!");
            return null;
        }
        return lecturerRepository.findById(lecturerId);
    }
}