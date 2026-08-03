package com.vti.controller;

import com.vti.dto.DiningTableDTO;
import com.vti.enums.TableStatus;
import com.vti.form.DiningTableCreateForm;
import com.vti.form.DiningTableUpdateForm;
import com.vti.service.IDiningTableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tables")
@CrossOrigin("*")
public class DiningTableController {

    @Autowired
    private IDiningTableService diningTableService;

    @GetMapping
    public ResponseEntity<List<DiningTableDTO>> findAll() {
        return new ResponseEntity<>(diningTableService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiningTableDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(diningTableService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody DiningTableCreateForm form) {
        diningTableService.create(form);
        return new ResponseEntity<>("Thêm bàn ăn mới thành công!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody DiningTableUpdateForm form, @PathVariable Integer id) {
        diningTableService.update(form, id);
        return new ResponseEntity<>("Cập nhật thông tin bàn ăn thành công!", HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Integer id, @RequestParam TableStatus status) {
        diningTableService.updateStatus(id, status);
        return new ResponseEntity<>("Cập nhật trạng thái bàn thành công!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        diningTableService.deleteById(id);
        return new ResponseEntity<>("Xóa bàn ăn thành công!", HttpStatus.OK);
    }
}
