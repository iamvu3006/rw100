package com.vti.entity;

import com.vti.enums.CandidateRole;

public class Candidate {
    private static int counter = 1;
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private CandidateRole role;

    public Candidate() {
        this.id = counter++;
    }

    public Candidate(String firstName, String lastName, String phone, String email, String password, CandidateRole role) {
        this.id = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public CandidateRole getRole() {
        return role;
    }

    public void setRole(CandidateRole role) {
        this.role = role;
    }
}
