package com.vti.entity;

import com.vti.enums.Role;

public abstract class User {

    private int id;
    private String fullName;
    private String email;
    private String password;
    private Role role;

    protected User() {
    }

    protected User(int id, String fullName, String email, String password, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getBasicInformation() {
        return String.format("%-5d | %-25s | %-30s", id, fullName, email);
    }

    public abstract String getDetailInformation();
}
