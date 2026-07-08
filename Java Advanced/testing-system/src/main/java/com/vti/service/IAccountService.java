package com.vti.service;

import com.vti.entity.Account;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();

    Account findById(Integer id);

    void deleteById(Integer id);

    void create(Account account);

    void update(Account account, Integer id);
}