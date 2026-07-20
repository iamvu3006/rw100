package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.form.AccountCreateOrUpdateForm;
import com.vti.form.AccountSearchForm;
import com.vti.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@CrossOrigin("*")//http://127.0.0.1:5500/
@Validated
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping
    public ResponseEntity<Page<AccountDTO>> findAll(AccountSearchForm form, Pageable pageable) {
        return new ResponseEntity<>(accountService.findAll(form, pageable), HttpStatus.OK);
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
    public ResponseEntity<String> create(@RequestBody @Valid AccountCreateOrUpdateForm form) {
        accountService.create(form);
        return new ResponseEntity<>("Created", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody AccountCreateOrUpdateForm form, @PathVariable(name = "id") Integer id) {
        accountService.update(form, id);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }


    @GetMapping("/findByUsername")
    public ResponseEntity<AccountDTO> findByUsername(@RequestParam String username) {

        return new ResponseEntity<>(accountService.findByUsername(username), HttpStatus.OK);
    }
}