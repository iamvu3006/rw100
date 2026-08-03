package com.vti.service;

import com.vti.dto.CategoryDTO;
import com.vti.form.CategoryCreateForm;
import com.vti.form.CategoryUpdateForm;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findById(Integer id);

    void create(CategoryCreateForm form);

    void update(CategoryUpdateForm form, Integer id);

    void deleteById(Integer id);
}
