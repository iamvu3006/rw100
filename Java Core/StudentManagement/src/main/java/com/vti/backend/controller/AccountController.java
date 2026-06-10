package com.vti.backend.controller;

import com.vti.backend.service.IAccountService;
import com.vti.backend.service.impl.AccountServiceImpl;
import com.vti.entity.Account;

public class AccountController {

    private final IAccountService accountService = new AccountServiceImpl();
    public Account login(String email, String password) {
        return accountService.login(email, password);
    }
}
