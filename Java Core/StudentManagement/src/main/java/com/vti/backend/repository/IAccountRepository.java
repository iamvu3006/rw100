package com.vti.backend.repository;

import com.vti.entity.Account;

public interface IAccountRepository {
    Account login(String email, String password);
}