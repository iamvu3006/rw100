package com.vti.dto.csv;

public class DepartmentCsv {
    private String departmentName;

    public DepartmentCsv(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return departmentName;
    }
}
