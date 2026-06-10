package com.vti.entity;

public class Lecturer {

    private int lecturerId;
    private String fullName;
    private String email;
    private String department;

    public Lecturer() {
    }

    public Lecturer(int lecturerId, String fullName, String email, String department) {
        this.lecturerId = lecturerId;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "lecturerId=" + lecturerId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}