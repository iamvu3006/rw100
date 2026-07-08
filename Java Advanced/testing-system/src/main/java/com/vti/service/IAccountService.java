package com.vti.service;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;

import java.util.List;

public interface IAccountService {
    List<AccountDTO> findAll();

    AccountDTO findById(Integer id);

    void deleteById(Integer id);

    void create(Account account);

    void update(Account account, Integer id);
}