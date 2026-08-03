package com.vti.controller;

import com.vti.dto.CategoryDTO;
import com.vti.form.CategoryCreateForm;
import com.vti.form.CategoryUpdateForm;
import com.vti.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CategoryCreateForm form) {
        categoryService.create(form);
        return new ResponseEntity<>("Tạo danh mục món ăn thành công!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody CategoryUpdateForm form, @PathVariable Integer id) {
        categoryService.update(form, id);
        return new ResponseEntity<>("Cập nhật danh mục món ăn thành công!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>("Xóa danh mục món ăn thành công!", HttpStatus.OK);
    }
}
