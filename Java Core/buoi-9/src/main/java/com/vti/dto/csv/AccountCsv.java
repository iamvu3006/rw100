package com.vti.dto.csv;

public class AccountCsv {
    private String username;
    private String fullName;
    private String email;
    private String departmentId;
    private String positionId;

    public AccountCsv(String email, String fullName, String username, String departmentId, String positionId) {
        this.departmentId = departmentId;
        this.email = email;
        this.fullName = fullName;
        this.positionId = positionId;
        this.username = username;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(username).append(",");
        stringBuilder.append(email).append(",");
        stringBuilder.append(fullName).append(",");
        stringBuilder.append(departmentId).append(",");
        stringBuilder.append(positionId);
        return stringBuilder.toString();
    }
}
