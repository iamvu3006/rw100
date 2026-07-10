package com.vti.controller;

import com.vti.dto.PositionDTO;
import com.vti.form.PositionCreateOrUpdateForm;
import com.vti.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/positions")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @GetMapping
    public ResponseEntity<List<PositionDTO>> findAll() {
        return new ResponseEntity<>(positionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionDTO> findById(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(positionService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Integer id) {
        positionService.deleteById(id);
        return new ResponseEntity<>("position deleted", HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody PositionCreateOrUpdateForm form) {
        positionService.create(form);
        return new ResponseEntity<>("position created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody PositionCreateOrUpdateForm form, @PathVariable(name = "id") Integer id) {
        positionService.update(form, id);
        return new ResponseEntity<>("position updated", HttpStatus.OK);
    }

}