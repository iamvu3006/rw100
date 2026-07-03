package com.vti.repository;

import com.vti.entity.Account;

import java.util.List;

public interface IAccountRepository {
    List<Account> findAll();
    Account findById(Integer id);
    void create(Account account);
    void update(Integer id, String newFullName);
    void delete(Integer id);
}