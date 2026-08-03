package com.vti.service;

import com.vti.dto.AccountDTO;
import com.vti.dto.AccountLoginDTO;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountSearchForm;
import com.vti.form.AccountUpdateForm;
import com.vti.form.LoginForm;
import com.vti.form.RegisterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAccountService {
    Page<AccountDTO> findAll(AccountSearchForm form, Pageable pageable);

    AccountDTO findById(Integer id);

    AccountDTO findByUsername(String username);

    void create(AccountCreateForm form);

    void update(AccountUpdateForm form, Integer id);

    void deleteById(Integer id);

    AccountLoginDTO login(LoginForm loginForm);

    void register(RegisterForm form);
}