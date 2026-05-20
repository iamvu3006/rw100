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
        if (department == null || department.getName() == null || department.getName().trim().isEmpty()) {
            System.out.println("Tên phòng ban không được để trống");
            return false;
        }
        if (checkExistName(department.getName(), null)) {
            System.out.println("Tên phòng ban đã tồn tại");
            return false;
        }
        return departmentRepository.insert(department);
    }

    @Override
    public boolean update(Department department) throws ClassNotFoundException {
        if (department == null || department.getId() <= 0) {
            System.out.println("ID phòng ban không hợp lệ");
            return false;
        }
        if (departmentRepository.countById(department.getId()) == 0) {
            System.out.println("ID phòng ban không tồn tại");
            return false;
        }
        if (department.getName() == null || department.getName().trim().isEmpty()) {
            System.out.println("Tên phòng ban không được để trống");
            return false;
        }
        if (checkExistName(department.getName(), department.getId())) {
            System.out.println("Tên phòng ban đã tồn tại");
            return false;
        }
        return departmentRepository.update(department);
    }

    @Override
    public boolean delete(int id) throws ClassNotFoundException {
        if (id <= 0) {
            System.out.println("ID phòng ban không hợp lệ");
            return false;
        }
        if (departmentRepository.countById(id) == 0) {
            System.out.println("ID phòng ban không tồn tại");
            return false;
        }
        return departmentRepository.delete(id);
    }

    @Override
    public boolean checkExistName(String name, Integer id) throws ClassNotFoundException {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return departmentRepository.countByName(name, id) > 0;
    }
}
