package com.vti.backend.service;
import java.util.List;
import com.vti.entity.Department;

public interface IDepartmentService {
    List<Department> findAll() throws ClassNotFoundException;
    boolean insert(Department department) throws ClassNotFoundException;
    boolean update(Department department) throws ClassNotFoundException;
    boolean delete(int id) throws ClassNotFoundException;
    boolean checkExistName(String name, Integer id) throws ClassNotFoundException;
}
