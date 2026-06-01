package com.vti.dto.context;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;

import java.util.List;
import java.util.Map;

public class AccountContext {
    private Map<String, Account> mapByUsername;
    private Map<String, Account> mapByEmail;
    private List<Department> departments;
    private List<Position> positions;

    public AccountContext(Map<String, Account> mapByUsername, Map<String, Account> mapByEmail,
                          List<Department> departments, List<Position> positions) {
        this.mapByUsername = mapByUsername;
        this.mapByEmail = mapByEmail;
        this.departments = departments;
        this.positions = positions;
    }

    public Map<String, Account> getMapByUsername() {
        return mapByUsername;
    }

    public void setMapByUsername(Map<String, Account> mapByUsername) {
        this.mapByUsername = mapByUsername;
    }

    public Map<String, Account> getMapByEmail() {
        return mapByEmail;
    }

    public void setMapByEmail(Map<String, Account> mapByEmail) {
        this.mapByEmail = mapByEmail;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}

