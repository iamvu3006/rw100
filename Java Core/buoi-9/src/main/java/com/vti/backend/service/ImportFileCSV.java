package com.vti.backend.service;

import com.vti.dto.ImportError;
import com.vti.entity.Department;
import com.vti.backend.repository.IDepartmentRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ImportFileCSV<T, K, E> {
    // T là context chứa dữ liệu để validation
    // K là entity Account, Department, Position liên quan đến ds không có lỗi
    // E là đối tượng Map với file csv có lỗi, chứa dữ liệu và message lỗi

    void validation(String line, T context, List<K> entities, List<ImportError<E>> importErrors);

    void saveAll(List<K> entities);

    void exportFileError(List<ImportError<E>> importErrors, String pathError);

    default String importFileCSV(String pathName, T context, String pathError) {
        File file = new File(pathName);
        if (!file.exists()) {
            return "File không tồn tại";
        }
        // doc du lieu tu file va dua du lieu cho repository de lưu vao DB
        if (!pathName.endsWith(".csv")) {
            return "Định dạng file không đúng";
        }

        List<K> entities = new ArrayList<>();
        List<ImportError<E>> importErrors = new ArrayList<>();// chua ds department se dc them moi
        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {// doc du lieu tu file
            String line = "";
            br.readLine();// lay dòng dau tien, bo no di
            while ((line = br.readLine()) != null) {
                //validation
                this.validation(line, context, entities, importErrors);
            }
            //  luu vao DB
            this.saveAll(entities);

            // xuat file loi
            this.exportFileError(importErrors, pathError);
        } catch (Exception e) {
        }
        String message = "";
        if (importErrors.isEmpty()) {
            message = "Import thành công";
        }
        if (entities.isEmpty()) {
            message = "Import ko thành công, đã xuất file lỗi " + pathError;
        }
        if (!importErrors.isEmpty() && !entities.isEmpty()) {
            message = "Import thành công " + entities.size() +
                    "entities, đã xuất lỗi ra file " + pathError;
        }
        return message;
    }
}
