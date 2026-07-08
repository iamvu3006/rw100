package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    // lấy ra ds account
    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll() {
        List<AccountDTO> accounts = accountService.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // lấy ra thông tin account theo id - khóa chính
    @GetMapping("/{idSearch}")// http://localhost:8080/api/v1/accounts/1
    public ResponseEntity<AccountDTO> findById(@PathVariable(name = "idSearch") Integer id) {
        AccountDTO account = accountService.findById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    // xóa theo id
    @DeleteMapping("/{idDelete}")// http://localhost:8080/api/v1/accounts/1
    public ResponseEntity<String> deleteById(@PathVariable(name = "idDelete") Integer id) {
        accountService.deleteById(id);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }

    // tạo mới 1 account
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Account account) {
        accountService.create(account);
        return new ResponseEntity<>("Tạo mới thành công", HttpStatus.CREATED);
    }

    // update theo id
    @PutMapping("/{idUpdate}")
    public ResponseEntity<String> update(@RequestBody Account account,
                                         @PathVariable(name = "idUpdate") Integer id) {
        accountService.update(account, id);
        return new ResponseEntity<>("Update thành công", HttpStatus.OK);
    }
}