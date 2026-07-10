package com.vti.controller;

import com.vti.dto.DepartmentDTO;
import com.vti.form.DepartmentCreateOrUpdateForm;
import com.vti.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> findAll() {
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idSearch}")
    public ResponseEntity<DepartmentDTO> findById(@PathVariable(name = "idSearch") Integer id) {
        return new ResponseEntity<>(departmentService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<DepartmentDTO> findByName(@RequestParam(name = "name") String name) {
        return new ResponseEntity<>(departmentService.findByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{idDelete}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "idDelete") Integer id) {
        departmentService.deleteById(id);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody DepartmentCreateOrUpdateForm form) {
        departmentService.create(form);
        return new ResponseEntity<>("Tạo mới thành công", HttpStatus.CREATED);
    }

    @PutMapping("/{idUpdate}")
    public ResponseEntity<String> update(@RequestBody DepartmentCreateOrUpdateForm form,
                                         @PathVariable(name = "idUpdate") Integer id) {
        departmentService.update(form, id);
        return new ResponseEntity<>("Update thành công", HttpStatus.OK);
    }
}
