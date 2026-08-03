package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountSearchForm;
import com.vti.form.AccountUpdateForm;
import com.vti.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@CrossOrigin("*")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping
    public ResponseEntity<Page<AccountDTO>> findAll(AccountSearchForm form, Pageable pageable) {
        return new ResponseEntity<>(accountService.findAll(form, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/by-username")
    public ResponseEntity<AccountDTO> findByUsername(@RequestParam String username) {
        return new ResponseEntity<>(accountService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody AccountCreateForm form) {
        accountService.create(form);
        return new ResponseEntity<>("Tạo mới tài khoản thành công!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody AccountUpdateForm form, @PathVariable Integer id) {
        accountService.update(form, id);
        return new ResponseEntity<>("Cập nhật tài khoản thành công!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        accountService.deleteById(id);
        return new ResponseEntity<>("Xóa tài khoản thành công!", HttpStatus.OK);
    }
}