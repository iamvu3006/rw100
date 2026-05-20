package com.vti.backend.repository;

import com.vti.entity.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll() throws ClassNotFoundException;
    boolean insert(Department department) throws ClassNotFoundException;
    boolean update(Department department) throws ClassNotFoundException;
    boolean delete(int id) throws ClassNotFoundException;
    int countByName(String name) throws ClassNotFoundException;
    int countByName(String name, Integer excludeId) throws ClassNotFoundException;
    int countById(int id) throws ClassNotFoundException;
}
