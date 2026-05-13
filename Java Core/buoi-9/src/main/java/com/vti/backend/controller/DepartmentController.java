package com.vti.backend.controller;

import com.vti.backend.service.IDepartmentService;
import com.vti.backend.service.impl.DepartmentServiceImpl;
import com.vti.entity.Department;

import java.util.List;

public class DepartmentController {
    //khởi tạo service
    private IDepartmentService departmentService = new DepartmentServiceImpl();

    public List<Department> findAllDepartment() throws ClassNotFoundException {
        return departmentService.findAll();
    }

    public boolean insertDepartment(Department department) throws ClassNotFoundException {
        return departmentService.insert(department);
    }

    public boolean updateDepartment(Department department) throws ClassNotFoundException {
        return departmentService.update(department);
    }

    public boolean deleteDepartment(int id) throws ClassNotFoundException {
        return departmentService.delete(id);
    }
}
