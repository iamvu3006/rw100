package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.dto.DishDTO;
import com.vti.form.DishCreateForm;
import com.vti.form.DishFilterForm;
import com.vti.form.DishUpdateForm;

public interface IDishService {
    Page<DishDTO> findAll(DishFilterForm form, Pageable pageable);

    DishDTO findById(Integer id);

    void create(DishCreateForm form);

    void update(DishUpdateForm form, Integer id);

    boolean deleteById(Integer id); // true = đã xóa thật, false = chỉ chuyển ngừng phục vụ
}