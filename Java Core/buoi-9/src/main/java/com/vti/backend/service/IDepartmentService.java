package com.vti.backend.service;

import com.vti.dto.context.DepartmentContext;
import com.vti.dto.csv.DepartmentCsv;
import com.vti.entity.Department;

import java.util.List;

public interface IDepartmentService extends ImportFileCSV<DepartmentContext, Department, DepartmentCsv>{
    List<Department> findAll();
    boolean create(String name);
    boolean update(int id, String name);
    boolean delete(int id);
    boolean checkExistNameAndIdNot(String name, Integer id);
    boolean checkExistID(Integer id);
    String importDepartmentFromCSV(String pathName);
}
