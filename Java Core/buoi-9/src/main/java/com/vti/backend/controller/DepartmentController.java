package com.vti.backend.controller;

import com.vti.backend.service.IDepartmentService;
import com.vti.backend.service.impl.DepartmentServiceImpl;
import com.vti.entity.Department;

import java.util.List;

public class DepartmentController {
    // khởi tạo service
    IDepartmentService departmentService = new DepartmentServiceImpl();

    public List<Department> findAll() {
        return departmentService.findAll();// lấy ds từ service
    }

    public boolean create(String name) {
        return departmentService.create(name);
    }

    public boolean update(int id, String name) {
        return departmentService.update(id, name);
    }

    public boolean delete(int id) {
        return departmentService.delete(id);
    }

    public boolean checkExistNameAndIdNot(String name, Integer id) {
        return departmentService.checkExistNameAndIdNot(name, id);
    }

    public boolean checkExistID(Integer id) {
        return departmentService.checkExistID(id);
    }

    public String importDepartmentFromCSV(String pathName) {//return String: thng báo ra import thanh cong hay that bai
        return departmentService.importDepartmentFromCSV(pathName);
    }
}
