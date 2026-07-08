package com.vti.repository;

import com.vti.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByName(String name);
    //kiểm tra xem tên đã tồn tại chưa
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}