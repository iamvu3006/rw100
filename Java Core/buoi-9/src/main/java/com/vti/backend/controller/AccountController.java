package com.vti.backend.controller;

import com.vti.backend.service.IAccountService;
import com.vti.backend.service.impl.AccountServiceImpl;
import com.vti.entity.Account;

import java.util.List;
import java.util.Map;

public class AccountController {
    // khoi tao accountService
    private IAccountService accountService = new AccountServiceImpl();


    public List<Account> findAll() {
        return accountService.findAll();
    }

    public boolean create(String email, String username, String fullName, int departmentID, int positionID) {
        return accountService.create(email, username, fullName, departmentID, positionID);
    }

    public boolean update(int id, String updateName, String email, String username, int departmentId, int positionId) {
        return accountService.update(id, updateName, email, username, departmentId, positionId);
    }

    public boolean delete(int id) {
        return accountService.delete(id);
    }

    public Map<String, Account> mapAccountByUsername() {
        return accountService.mapAccountByUsername();
    }

    public boolean checkUsernameExist(String username, Integer id) {
        return accountService.checkUsernameExist(username, id);
    }

    public boolean checkEmailExist(String email) {
        return accountService.checkEmailExist(email);
    }

    public boolean checkIdExist(Integer id) {
        return accountService.checkIdExist(id);
    }

    public boolean update(int id, String updateName) {
        return accountService.update(id, updateName);
    }

    public String importAccountFromCSV(String pathName) {
        return accountService.importAccountFromCSV(pathName);
    }
}
