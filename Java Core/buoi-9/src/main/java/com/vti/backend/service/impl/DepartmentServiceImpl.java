package com.vti.backend.service.impl;

import com.vti.backend.repository.IDepartmentRepository;
import com.vti.backend.repository.impl.DepartmentRepositoryImpl;
import com.vti.backend.service.IDepartmentService;
import com.vti.entity.Department;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {

    //khởi tạo đối tượng deparmentRepository để gọi phương thức findAll() trong DepartmentRepository
    private IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
    
    @Override
    public List<Department> findAll() throws ClassNotFoundException {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    @Override
    public boolean insert(Department department) throws ClassNotFoundException {
        return departmentRepository.insert(department);
    }

    @Override
    public boolean update(Department department) throws ClassNotFoundException {
        return departmentRepository.update(department);
    }

    @Override
    public boolean delete(int id) throws ClassNotFoundException {
        return departmentRepository.delete(id);
    }
}
