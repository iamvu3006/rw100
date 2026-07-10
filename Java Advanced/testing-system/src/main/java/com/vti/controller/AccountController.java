package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.form.AccountCreateOrUpdateForm;
import com.vti.service.IAccountService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll() {
        return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Integer id) {
        accountService.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AccountCreateOrUpdateForm form) {
        accountService.create(form);
        return new ResponseEntity<>("Created", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody AccountCreateOrUpdateForm form, @PathVariable(name = "id") Integer id) {
        accountService.update(form, id);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }
}