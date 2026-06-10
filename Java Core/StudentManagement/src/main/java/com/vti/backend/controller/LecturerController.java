package com.vti.backend.controller;

import com.vti.backend.service.ILecturerService;
import com.vti.backend.service.impl.LecturerServiceImpl;
import com.vti.entity.Lecturer;

public class LecturerController {

    private final ILecturerService lecturerService = new LecturerServiceImpl();
    public Lecturer findById(int lecturerId) {
        return lecturerService.findById(lecturerId);
    }
}
