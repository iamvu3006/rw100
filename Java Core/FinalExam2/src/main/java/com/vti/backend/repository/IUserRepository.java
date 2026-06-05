package com.vti.backend.repository;

import com.vti.entity.User;

import java.util.List;

public interface IUserRepository {

    void seedUsersIfEmpty();

    List<User> findAll();

    User findById(int id);

    boolean deleteById(int id);

    User findByEmailAndPassword(String email, String password);

    boolean isEmailExists(String email);

    boolean createEmployee(String fullName, String email, String password);
}
