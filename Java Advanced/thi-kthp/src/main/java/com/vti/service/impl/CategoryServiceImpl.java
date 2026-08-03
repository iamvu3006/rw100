package com.vti.service.impl;

import com.vti.dto.CategoryDTO;
import com.vti.entity.Category;
import com.vti.exception.BusinessException;
import com.vti.form.CategoryCreateForm;
import com.vti.form.CategoryUpdateForm;
import com.vti.repository.ICategoryRepository;
import com.vti.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Danh mục không tồn tại with ID: " + id).build());
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    @Transactional
    public void create(CategoryCreateForm form) {
        if (categoryRepository.existsByName(form.getName())) {
            throw BusinessException.builder().message("Tên danh mục đã tồn tại").build();
        }
        Category category = Category.builder()
                .name(form.getName())
                .description(form.getDescription())
                .build();
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void update(CategoryUpdateForm form, Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Danh mục không tồn tại with ID: " + id).build());

        if (!category.getName().equals(form.getName()) && categoryRepository.existsByName(form.getName())) {
            throw BusinessException.builder().message("Tên danh mục đã tồn tại").build();
        }

        category.setName(form.getName());
        category.setDescription(form.getDescription());
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw BusinessException.builder().message("Danh mục không tồn tại with ID: " + id).build();
        }
        categoryRepository.deleteById(id);
    }
}
