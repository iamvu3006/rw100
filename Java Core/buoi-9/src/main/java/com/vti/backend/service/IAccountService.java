package com.vti.backend.service;

import com.vti.dto.context.AccountContext;
import com.vti.dto.csv.AccountCsv;
import com.vti.entity.Account;

import java.util.List;
import java.util.Map;

public interface IAccountService extends ImportFileCSV<AccountContext, Account, AccountCsv> {
    List<Account> findAll();

    boolean create(String email, String username, String fullName, int departmentID, int positionID);

    boolean update(int id, String updateName, String email, String username, int departmentId, int positionId);

    boolean delete(int id);

    Map<String, Account> mapAccountByUsername();

    boolean checkUsernameExist(String username,  Integer id);

    boolean checkEmailExist(String email);

    boolean checkIdExist(Integer id);

    boolean update(int id, String updateName);

    String importAccountFromCSV(String pathName);
}
