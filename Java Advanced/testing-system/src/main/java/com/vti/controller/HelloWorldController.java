package com.vti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("api/v1/hello")
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<String> hello(Locale locale) {
        return new ResponseEntity<>(messageSource.getMessage("hello", null, locale), HttpStatus.OK) ;
    }
}