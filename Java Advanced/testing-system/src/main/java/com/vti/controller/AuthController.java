package com.vti.controller;

import com.vti.form.LoginForm;
import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {
    // đăng nhập, đăng kí, quên mk
    @Autowired
    private IAccountService accountService;

//    @GetMapping("/login")
//    public ResponseEntity<?> login(Principal principal) {
//        return new ResponseEntity<>(accountService.login(principal), HttpStatus.OK);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        return new ResponseEntity<>(accountService.login(loginForm), HttpStatus.OK);
    }
}