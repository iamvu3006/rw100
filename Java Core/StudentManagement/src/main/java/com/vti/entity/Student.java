package com.vti.entity;

import java.time.LocalDate;

public class Student {

    private int studentId;
    private String fullName;
    private String email;
    private LocalDate dateOfBirth;
    private int majorId;
    private String majorName; // Tên chuyên ngành (JOIN từ bảng Major)

    public Student() {
    }

    public Student(String fullName, String email, LocalDate dateOfBirth, int majorId) {
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.majorId = majorId;
    }

    public Student(int studentId, String fullName, String email, LocalDate dateOfBirth,
                   int majorId, String majorName) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.majorId = majorId;
        this.majorName = majorName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}