package com.vti.service;

import com.vti.dto.AccountDTO;
import com.vti.dto.AccountLoginDTO;
import com.vti.form.AccountCreateOrUpdateForm;
import com.vti.form.AccountSearchForm;
import com.vti.form.LoginForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAccountService {
    Page<AccountDTO> findAll(AccountSearchForm form, Pageable pageable);

    AccountDTO findById(Integer id);

    void deleteById(Integer id);

    void create(AccountCreateOrUpdateForm account);

    void update(AccountCreateOrUpdateForm account, Integer id);

    AccountDTO findByUsername(String username);

    AccountLoginDTO login(LoginForm loginForm);
}