package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.dto.AccountLoginDTO;
import com.vti.form.LoginForm;
import com.vti.form.RegisterForm;
import com.vti.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<AccountLoginDTO> login(@Valid @RequestBody LoginForm loginForm) {
        return new ResponseEntity<>(accountService.login(loginForm), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterForm form) {
        accountService.register(form);
        return new ResponseEntity<>("Đăng ký tài khoản thành công!", HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<AccountDTO> getCurrentUser(Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        AccountDTO accountDTO = accountService.findByUsername(principal.getName());
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }
}