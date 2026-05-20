package com.vti.backend.repository;

import com.vti.entity.Account;

import java.util.List;
import java.util.Map;

public interface IAccountRepository {
    List<Account> findAll();

    boolean create(String email, String username, String fullName, int departmentID, int positionID);

    boolean update(int id, String updateName, String email, String username, int departmentId, int positionId);

    boolean delete(int id);

    Map<String, Account> mapAccountByUsername();

    int countByUsername(String username) throws ClassNotFoundException;

    int countByUsername(String username, Integer excludeId) throws ClassNotFoundException;

    int countByEmail(String email) throws ClassNotFoundException;

    int countByEmail(String email, Integer excludeId) throws ClassNotFoundException;

    int countById(int id) throws ClassNotFoundException;

    int countDepartmentById(int departmentId) throws ClassNotFoundException;

    int countPositionById(int positionId) throws ClassNotFoundException;
}