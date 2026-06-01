package com.vti.dto.context;

import com.vti.entity.Department;

import java.util.Map;

public class DepartmentContext {
    private Map<String, Department> mapByDepartmentName;

    public DepartmentContext(Map<String, Department> mapByName) {
        this.mapByDepartmentName = mapByName;
    }

    public Map<String, Department> getMapByName() {
        return mapByDepartmentName;
    }

    public void setMapByName(Map<String, Department> mapByName) {
        this.mapByDepartmentName = mapByName;
    }
}
