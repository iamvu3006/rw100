package com.vti.service;

import com.vti.dto.DishDTO;
import com.vti.form.DishCreateForm;
import com.vti.form.DishFilterForm;
import com.vti.form.DishUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDishService {
    Page<DishDTO> findAll(DishFilterForm form, Pageable pageable);

    DishDTO findById(Integer id);

    void create(DishCreateForm form);

    void update(DishUpdateForm form, Integer id);

    void deleteById(Integer id);
}
