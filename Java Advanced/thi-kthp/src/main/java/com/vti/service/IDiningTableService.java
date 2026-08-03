package com.vti.service;

import com.vti.dto.DiningTableDTO;
import com.vti.enums.TableStatus;
import com.vti.form.DiningTableCreateForm;
import com.vti.form.DiningTableUpdateForm;

import java.util.List;

public interface IDiningTableService {
    List<DiningTableDTO> findAll();

    DiningTableDTO findById(Integer id);

    void create(DiningTableCreateForm form);

    void update(DiningTableUpdateForm form, Integer id);

    void updateStatus(Integer id, TableStatus status);

    void deleteById(Integer id);
}
