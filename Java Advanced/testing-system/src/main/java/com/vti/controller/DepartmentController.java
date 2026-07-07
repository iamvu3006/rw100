package com.vti.controller;

import com.vti.entity.Department;
import com.vti.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;// = new ();

    // lấy ra ds department
    @GetMapping
    public ResponseEntity<List<Department>> findAll() {
        List<Department> departments = departmentService.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    //lay ra thong tin department theo id  - khóa chính
    @GetMapping("/{idSearch}")// http://localhost:8080/api/v1/departments/13
    public ResponseEntity<Department> findById(@PathVariable(name = "idSearch") Integer id) {
        Department department = departmentService.findById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    // xóa theo id
    @DeleteMapping("/{idDelete}")// http://localhost:8080/api/v1/departments/13
    public ResponseEntity<String> deleteById(@PathVariable(name = "idDelete") Integer id) {
        departmentService.deleteById(id);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }

    // tao moi 1 department
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Department department) {
        departmentService.create(department);
        return new ResponseEntity<>("Tạo mới thành công", HttpStatus.CREATED);
    }

    // update theo id
    @PutMapping("/{idUpdate}")
    public ResponseEntity<String> update(@RequestBody Department department,
                                         @PathVariable(name = "idUpdate") Integer id) {
        departmentService.update(department, id);
        return new ResponseEntity<>("Update thành công", HttpStatus.OK);
    }
}