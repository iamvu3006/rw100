package com.vti.backend.service;

import com.vti.entity.Account;

public interface IAccountService {

    Account login(String email, String password);
}