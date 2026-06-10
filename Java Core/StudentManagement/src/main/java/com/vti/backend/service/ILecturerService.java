package com.vti.backend.service;

import com.vti.entity.Lecturer;

public interface ILecturerService {
    Lecturer findById(int lecturerId);
}