package com.vti.service.impl;

import com.vti.entity.Department;
import com.vti.repository.IDepartmentRepository;
import com.vti.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;// = new

    @Override
    public List<Department> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    @Override
    public Department findById(Integer id) {
        Department department = departmentRepository.findById(id).orElse(null);
        //orElse(null)   : nếu optional ko có gtrri thì sẽ gán luôn = gtri null
        // DB   Ko có id 20,   coos tình tìm id = 20
        return department;
    }

    @Override
    public void deleteById(Integer id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public void create(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void update(Department department, Integer id) {
        //  tìm department can update theo id
        Department departmentUpdate = departmentRepository.findById(id).orElse(null);
        if (Objects.isNull(departmentUpdate)) {
            throw new RuntimeException("ID not found!");
        } else {
            // lưu lại thông tin update
            departmentUpdate.setName(department.getName());
            departmentRepository.save(departmentUpdate);
        }
    }
}