package com.vti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.DishDTO;
import com.vti.form.DishCreateForm;
import com.vti.form.DishFilterForm;
import com.vti.form.DishUpdateForm;
import com.vti.service.IDishService;

import jakarta.validation.Valid;

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
        boolean deleted = dishService.deleteById(id);
        String message = deleted
                ? "Xóa món ăn thành công!"
                : "Món ăn đã từng có trong đơn hàng nên không thể xóa hẳn — hệ thống đã tự động chuyển sang trạng thái Ngừng phục vụ.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
