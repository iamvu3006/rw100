package com.vti.backend.service;

import com.vti.entity.User;

import java.util.List;

public interface IUserService {

    void initializeData();

    List<User> getAllUsers();

    User getUserById(int id);

    boolean deleteUserById(int id);

    User login(String email, String password);

    void createEmployee(String fullName, String email);
}
