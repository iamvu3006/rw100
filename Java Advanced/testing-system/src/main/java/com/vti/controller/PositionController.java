package com.vti.controller;

import com.vti.entity.Position;
import com.vti.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/positions")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    // lấy ra ds position
    @GetMapping
    public ResponseEntity<List<Position>> findAll() {
        List<Position> positions = positionService.findAll();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    // lấy ra thông tin position theo id - khóa chính
    @GetMapping("/{idSearch}")// http://localhost:8080/api/v1/positions
    public ResponseEntity<Position> findById(@PathVariable(name = "idSearch") Integer id) {
        Position position = positionService.findById(id);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    // xóa theo id
    @DeleteMapping("/{idDelete}")// http://localhost:8080/api/v1/positions
    public ResponseEntity<String> deleteById(@PathVariable(name = "idDelete") Integer id) {
        positionService.deleteById(id);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }

    // tạo mới 1 position
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Position position) {
        positionService.create(position);
        return new ResponseEntity<>("Tạo mới thành công", HttpStatus.CREATED);
    }

    // update theo id
    @PutMapping("/{idUpdate}")
    public ResponseEntity<String> update(@RequestBody Position position,
                                         @PathVariable(name = "idUpdate") Integer id) {
        positionService.update(position, id);
        return new ResponseEntity<>("Update thành công", HttpStatus.OK);
    }

    //tìm kiếm theo position_name
    @GetMapping("/name/{nameSearch}")// http://localhost:8080/api/v1/positions/name/DEV
    public ResponseEntity<Position> findByName(@PathVariable(name = "nameSearch") String name) {
        Position position = positionService.findByName(name);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }
}