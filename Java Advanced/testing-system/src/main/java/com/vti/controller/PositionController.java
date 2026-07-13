package com.vti.controller;

import com.vti.entity.Position;
import com.vti.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/positions")
@CrossOrigin(origins = "*") //http://127.0.0.1:5500
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @GetMapping
    public ResponseEntity<List<Position>> findAll() {
        return new ResponseEntity<>(positionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> findById(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(positionService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Integer id) {
        positionService.deleteById(id);
        return new ResponseEntity<>("position deleted", HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<String> create(Position account) {
        positionService.create(account);
        return new ResponseEntity<>("position created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(Position account, @PathVariable(name = "id") Integer id) {
        positionService.update(account, id);
        return new ResponseEntity<>("position updated", HttpStatus.OK);
    }

}