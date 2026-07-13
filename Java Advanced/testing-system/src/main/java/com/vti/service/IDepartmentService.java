package com.vti.service;

import com.vti.entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> findAll();

    Department findById(Integer id);

    void deleteById(Integer id);

    void create(Department department);

    void update(Department department, Integer id);

    Department findByName(String name);
}