package com.vti.backend.repository;

import com.vti.entity.Account;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IAccountRepository {
    Map<String, Account> mapByUsername();
    List<Account> findAll();

    boolean create(String email, String username, String fullName, int departmentID, int positionID);

    boolean update(int id, String updateName, String email, String username, int departmentId, int positionId);

    boolean delete(int id);

    Map<String, Account> mapAccountByUsername();

    Map<String, Account> mapAccountByEmail();

    boolean checkUsernameExist(String username, Integer id);

    boolean checkEmailExist(String email);

    boolean checkIdExist(Integer id);

    boolean update(int id, String updateName);

    boolean createAccounts(List<Account> accounts);
}
