package com.vti.backend.service.impl;

import com.vti.backend.repository.IDepartmentRepository;
import com.vti.backend.repository.impl.DepartmentRepositoryImpl;
import com.vti.backend.service.IDepartmentService;
import com.vti.dto.ImportError;
import com.vti.dto.context.DepartmentContext;
import com.vti.dto.csv.DepartmentCsv;
import com.vti.entity.Department;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DepartmentServiceImpl implements IDepartmentService {
    private static final String BASE_CSV_PATH = "C:\\Users\\Admin\\OneDrive\\Documents\\rw100\\csv";
    private final IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl();

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public boolean create(String name) {
        return departmentRepository.create(name);
    }

    @Override
    public boolean update(int id, String name) {
        return departmentRepository.update(id, name);
    }

    @Override
    public boolean delete(int id) {
        return departmentRepository.delete(id);
    }

    @Override
    public boolean checkExistNameAndIdNot(String name, Integer id) {
        return departmentRepository.checkExistNameAndIdNot(name, id);
    }

    @Override
    public boolean checkExistID(Integer id) {
        return departmentRepository.checkExistID(id);
    }

    @Override
    public String importDepartmentFromCSV(String pathName) {
        File file = new File(pathName);
        if (!file.exists()) {
            return "File không tồn tại";
        }
        if (!pathName.endsWith(".csv")) {
            return "Định dạng file không đúng";
        }

        Map<String, Department> mapByName = departmentRepository.mapByName();
        DepartmentContext context = new DepartmentContext(mapByName);
        String pathError = BASE_CSV_PATH + "\\output_error_department.csv";
        return this.importFileCSV(pathName, context, pathError);
    }

    public void saveAll(List<Department> entities) {
        departmentRepository.createListDepartment(entities);
    }

    @Override
    public void exportFileError(List<ImportError<DepartmentCsv>> importErrors, String pathError) {
        if (!importErrors.isEmpty()) {
            try {
                File parent = new File(pathError).getParentFile();
                if (parent != null && !parent.exists() && !parent.mkdirs()) {
                    throw new IOException("Không thể tạo thư mục chứa file lỗi CSV");
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter(pathError));
                bw.write("department_name,error_message");
                bw.newLine();
                for (ImportError<DepartmentCsv> error : importErrors) {
                    String ln = error.getCsv().toString() + "," + String.join("|", error.getMessage());
                    bw.write(ln);
                    bw.newLine();
                }
                bw.flush();
                bw.close();
            } catch (Exception e) {
                throw new RuntimeException("Không thể ghi file lỗi CSV", e);
            }
        }
    }

    @Override
    public void validation(String line, DepartmentContext context, List<Department> entities, List<ImportError<DepartmentCsv>> importErrors) {
        List<String> errors = new ArrayList<>();
        String[] fields = line.split(",", -1);
        String departmentName = fields.length > 0 ? fields[0] : "";

        if (Objects.isNull(departmentName) || departmentName.trim().isEmpty()) {
            errors.add("Tên phòng ban ko được để trống");
        } else if (departmentName.length() > 100) {
            errors.add("Tên phòng ban ko được dài quá 100 kí tự");
        } else if (context.getMapByName().get(departmentName) != null) {
            errors.add("Tên phòng ban đã tồn tại");
        }
        if (errors.isEmpty()) {
            Department dep = new Department(departmentName);
            entities.add(dep);
            context.getMapByName().put(departmentName, dep);
        } else {
            DepartmentCsv csv = new DepartmentCsv(departmentName);
            ImportError<DepartmentCsv> importError = new ImportError<>(csv, errors);
            importErrors.add(importError);
        }
    }
}
