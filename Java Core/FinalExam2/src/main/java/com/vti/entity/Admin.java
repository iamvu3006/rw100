package com.vti.entity;

import com.vti.enums.Role;

public class Admin extends User {

    private Integer expInYear;

    public Admin() {
        setRole(Role.ADMIN);
    }

    public Admin(int id, String fullName, String email, String password, Integer expInYear) {
        super(id, fullName, email, password, Role.ADMIN);
        this.expInYear = expInYear;
    }

    public Integer getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(Integer expInYear) {
        this.expInYear = expInYear;
    }

    @Override
    public String getDetailInformation() {
        return String.format(
                "Admin{id=%d, fullName='%s', email='%s', expInYear=%s}",
                getId(),
                getFullName(),
                getEmail(),
                expInYear == null ? "null" : expInYear.toString());
    }
}
