package com.vti.backend.controller;

import com.vti.backend.service.IUserService;
import com.vti.backend.service.impl.UserServiceImpl;
import com.vti.entity.User;
import com.vti.utils.TableUtils;

import java.util.List;

public class UserController {

    private final IUserService userService;

    public UserController() {
        this.userService = new UserServiceImpl();
    }

    public void initializeData() {
        userService.initializeData();
    }

    public void displayAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Chưa có user nào trong database.");
            return;
        }
        TableUtils.printUserSummaryTable(users);
    }

    public void displayUserById(int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            System.out.println("Không tìm thấy user có id = " + id);
            return;
        }
        System.out.println(user.getDetailInformation());
    }

    public void deleteUserById(int id) {
        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            System.out.println("Xóa user thành công.");
        } else {
            System.out.println("Không tìm thấy user để xóa.");
        }
    }

    public User login(String email, String password) {
        return userService.login(email, password);
    }

    public void createEmployee(String fullName, String email) {
        userService.createEmployee(fullName, email);
        System.out.println("Tạo employee thành công với password mặc định 123456.");
    }
}
