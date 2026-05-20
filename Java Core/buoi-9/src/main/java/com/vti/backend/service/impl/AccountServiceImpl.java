package com.vti.backend.service.impl;

import com.vti.backend.repository.impl.AccountRepositoryImpl;
import com.vti.backend.service.IAccountService;
import com.vti.entity.Account;

import java.util.List;
import java.util.Map;

public class AccountServiceImpl implements IAccountService {
    // khoi tao accountRepository
    private AccountRepositoryImpl accountRepository = new AccountRepositoryImpl();


    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean create(String email, String username, String fullName, int departmentID, int positionID) {
        try {
            if (!isValidText(username)) {
                System.out.println("Username không được để trống");
                return false;
            }
            if (!isValidText(fullName)) {
                System.out.println("Full name không được để trống");
                return false;
            }
            if (!isValidEmail(email)) {
                System.out.println("Email không hợp lệ");
                return false;
            }
            if (accountRepository.countByUsername(username) > 0) {
                System.out.println("Username đã tồn tại");
                return false;
            }
            if (accountRepository.countByEmail(email) > 0) {
                System.out.println("Email đã tồn tại");
                return false;
            }
            if (accountRepository.countDepartmentById(departmentID) == 0) {
                System.out.println("Department không tồn tại");
                return false;
            }
            if (accountRepository.countPositionById(positionID) == 0) {
                System.out.println("Position không tồn tại");
                return false;
            }
            return accountRepository.create(email, username, fullName, departmentID, positionID);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(int id, String updateName, String email, String username, int departmentId, int positionId) {
        try {
            if (id <= 0) {
                System.out.println("ID account không hợp lệ");
                return false;
            }
            if (accountRepository.countById(id) == 0) {
                System.out.println("ID account không tồn tại");
                return false;
            }
            if (!isValidText(username)) {
                System.out.println("Username không được để trống");
                return false;
            }
            if (!isValidText(updateName)) {
                System.out.println("Full name không được để trống");
                return false;
            }
            if (!isValidEmail(email)) {
                System.out.println("Email không hợp lệ");
                return false;
            }
            if (accountRepository.countByUsername(username, id) > 0) {
                System.out.println("Username đã tồn tại");
                return false;
            }
            if (accountRepository.countByEmail(email, id) > 0) {
                System.out.println("Email đã tồn tại");
                return false;
            }
            if (accountRepository.countDepartmentById(departmentId) == 0) {
                System.out.println("Department không tồn tại");
                return false;
            }
            if (accountRepository.countPositionById(positionId) == 0) {
                System.out.println("Position không tồn tại");
                return false;
            }
            return accountRepository.update(id, updateName, email, username, departmentId, positionId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            if (id <= 0) {
                System.out.println("ID account không hợp lệ");
                return false;
            }
            if (accountRepository.countById(id) == 0) {
                System.out.println("ID account không tồn tại");
                return false;
            }
            return accountRepository.delete(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map<String, Account> mapAccountByUsername() {
        return accountRepository.mapAccountByUsername();
    }

    private boolean isValidText(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && !email.startsWith("@") && !email.endsWith("@");
    }
}