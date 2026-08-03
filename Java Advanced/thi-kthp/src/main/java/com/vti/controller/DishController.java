package com.vti.controller;

import com.vti.dto.DishDTO;
import com.vti.form.DishCreateForm;
import com.vti.form.DishFilterForm;
import com.vti.form.DishUpdateForm;
import com.vti.service.IDishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dishes")
@CrossOrigin("*")
public class DishController {

    @Autowired
    private IDishService dishService;

    @GetMapping
    public ResponseEntity<Page<DishDTO>> findAll(DishFilterForm form, Pageable pageable) {
        return new ResponseEntity<>(dishService.findAll(form, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(dishService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody DishCreateForm form) {
        dishService.create(form);
        return new ResponseEntity<>("Thêm món ăn thành công!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody DishUpdateForm form, @PathVariable Integer id) {
        dishService.update(form, id);
        return new ResponseEntity<>("Cập nhật món ăn thành công!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        dishService.deleteById(id);
        return new ResponseEntity<>("Xóa món ăn thành công!", HttpStatus.OK);
    }
}
