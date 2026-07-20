package com.vti.controller;

import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

public class AuthController {
    @Autowired
    private IAccountService accountService;

    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal)
    {
        return new ResponseEntity<>(accountService.login(principal), HttpStatus.OK);
    }
}
