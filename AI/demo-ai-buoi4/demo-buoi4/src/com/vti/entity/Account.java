package com.vti.entity;

public class Account {
    private int id;
    private String username;
    private String fullname;
    private Department department;
    private Position position;

    public Account() {
    }

    public Account(int id, String username, String fullname, Department department, Position position) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.department = department;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", department=" + department +
                ", position=" + position +
                '}';
    }
}
