package com.vti.backend.service.impl;

import com.vti.backend.repository.IUserRepository;
import com.vti.backend.repository.impl.UserRepositoryImpl;
import com.vti.backend.service.IUserService;
import com.vti.entity.User;
import com.vti.utils.ValidateUtils;

import java.util.List;

public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public void initializeData() {
        userRepository.seedUsersIfEmpty();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean deleteUserById(int id) {
        return userRepository.deleteById(id);
    }

    @Override
    public User login(String email, String password) {
        if (!ValidateUtils.isValidEmail(email)) {
            throw new IllegalArgumentException("Email không đúng định dạng.");
        }
        if (!ValidateUtils.isValidLoginPassword(password)) {
            throw new IllegalArgumentException("Password phải từ 6 tới 12 ký tự và có ít nhất 1 chữ hoa.");
        }
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public void createEmployee(String fullName, String email) {
        if (!ValidateUtils.isValidFullName(fullName)) {
            throw new IllegalArgumentException("FullName chỉ được chứa chữ và khoảng trắng.");
        }
        if (!ValidateUtils.isValidEmail(email)) {
            throw new IllegalArgumentException("Email không đúng định dạng.");
        }
        if (userRepository.isEmailExists(email)) {
            throw new IllegalArgumentException("Email đã tồn tại.");
        }
        boolean created = userRepository.createEmployee(fullName, email, "123456");
        if (!created) {
            throw new IllegalStateException("Không thể tạo employee.");
        }
    }
}
