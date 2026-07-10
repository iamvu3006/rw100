package com.vti.service;

import com.vti.dto.DepartmentDTO;
import com.vti.form.DepartmentCreateOrUpdateForm;

import java.util.List;

public interface IDepartmentService {
    List<DepartmentDTO> findAll();

    DepartmentDTO findById(Integer id);

    void deleteById(Integer id);

    void create(DepartmentCreateOrUpdateForm department);

    void update(DepartmentCreateOrUpdateForm department, Integer id);

    DepartmentDTO findByName(String name);
}