package com.vti.service.impl;

import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;
import com.vti.form.DepartmentCreateOrUpdateForm;
import com.vti.repository.IDepartmentRepository;
import com.vti.service.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll().stream()
                .map(department -> modelMapper.map(department, DepartmentDTO.class))
                .toList();
    }

    @Override
    public DepartmentDTO findById(Integer id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (Objects.isNull(department)) {
            return null;
        }
        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    public void deleteById(Integer id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public void create(DepartmentCreateOrUpdateForm department) {
        if (departmentRepository.existsByNameAndIdNot(department.getName(), null)) {
            throw new RuntimeException("Department already exists");
        }

        Department newDepartment = new Department();
        newDepartment.setName(department.getName());
        departmentRepository.save(newDepartment);
    }

    @Override
    public void update(DepartmentCreateOrUpdateForm department, Integer id) {
        Department departmentUpdate = departmentRepository.findById(id).orElse(null);
        if (Objects.isNull(departmentUpdate)) {
            throw new RuntimeException("ID not found!");
        }
        if (departmentRepository.existsByNameAndIdNot(department.getName(), id)) {
            throw new RuntimeException("Department already exists");
        }

        departmentUpdate.setName(department.getName());
        departmentRepository.save(departmentUpdate);
    }

    @Override
    public DepartmentDTO findByName(String name) {
        Department department = departmentRepository.findByName(name);
        if (Objects.isNull(department)) {
            return null;
        }
        return modelMapper.map(department, DepartmentDTO.class);
    }
}
