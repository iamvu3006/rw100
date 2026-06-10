package com.vti.backend.repository;

import com.vti.entity.Lecturer;

public interface ILecturerRepository {
    Lecturer findById(int lecturerId);
}