package com.vti.backend.controller;

import com.vti.backend.service.IDepartmentService;
import com.vti.backend.service.impl.DepartmentServiceImpl;
import com.vti.entity.Department;

import java.util.List;

public class DepartmentController {
    // khởi tạo service
    IDepartmentService departmentService = new DepartmentServiceImpl();

    public List<Department> findAll() {
        return findAllDepartment();// lấy ds từ service
    }

    public boolean create(String name) {
        return insertDepartment(new Department(0, name));
    }

    public boolean update(int id, String name) {
        return updateDepartment(new Department(id, name));
    }

    public boolean delete(int id) {
        return deleteDepartment(id);
    }

    public boolean checkExistName(String name, Integer id) {
        try {
            return departmentService.checkExistName(name, id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Department> findAllDepartment() {
        try {
            return departmentService.findAll();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    public boolean insertDepartment(Department department) {
        try {
            return departmentService.insert(department);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDepartment(Department department) {
        try {
            return departmentService.update(department);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDepartment(int id) {
        try {
            return departmentService.delete(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}