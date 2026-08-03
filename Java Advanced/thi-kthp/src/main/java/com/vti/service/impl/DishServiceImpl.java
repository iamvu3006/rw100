package com.vti.service.impl;

import com.vti.dto.DishDTO;
import com.vti.entity.Category;
import com.vti.entity.Dish;
import com.vti.exception.BusinessException;
import com.vti.form.DishCreateForm;
import com.vti.form.DishFilterForm;
import com.vti.form.DishUpdateForm;
import com.vti.repository.ICategoryRepository;
import com.vti.repository.IDishRepository;
import com.vti.service.IDishService;
import com.vti.specification.DishSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DishServiceImpl implements IDishService {

    @Autowired
    private IDishRepository dishRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<DishDTO> findAll(DishFilterForm form, Pageable pageable) {
        Page<Dish> dishPage = dishRepository.findAll(DishSpecification.buildWhere(form), pageable);
        return dishPage.map(dish -> {
            DishDTO dto = modelMapper.map(dish, DishDTO.class);
            if (dish.getCategory() != null) {
                dto.setCategoryId(dish.getCategory().getId());
                dto.setCategoryName(dish.getCategory().getName());
            }
            return dto;
        });
    }

    @Override
    public DishDTO findById(Integer id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Món ăn không tồn tại with ID: " + id).build());
        DishDTO dto = modelMapper.map(dish, DishDTO.class);
        if (dish.getCategory() != null) {
            dto.setCategoryId(dish.getCategory().getId());
            dto.setCategoryName(dish.getCategory().getName());
        }
        return dto;
    }

    @Override
    @Transactional
    public void create(DishCreateForm form) {
        Category category = categoryRepository.findById(form.getCategoryId())
                .orElseThrow(() -> BusinessException.builder().message("Danh mục không tồn tại with ID: " + form.getCategoryId()).build());

        Dish dish = Dish.builder()
                .name(form.getName())
                .price(form.getPrice())
                .description(form.getDescription())
                .imageUrl(form.getImageUrl())
                .status(form.getStatus())
                .category(category)
                .build();

        dishRepository.save(dish);
    }

    @Override
    @Transactional
    public void update(DishUpdateForm form, Integer id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Món ăn không tồn tại with ID: " + id).build());

        Category category = categoryRepository.findById(form.getCategoryId())
                .orElseThrow(() -> BusinessException.builder().message("Danh mục không tồn tại with ID: " + form.getCategoryId()).build());

        dish.setName(form.getName());
        dish.setPrice(form.getPrice());
        dish.setDescription(form.getDescription());
        dish.setImageUrl(form.getImageUrl());
        dish.setStatus(form.getStatus());
        dish.setCategory(category);

        dishRepository.save(dish);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (!dishRepository.existsById(id)) {
            throw BusinessException.builder().message("Món ăn không tồn tại with ID: " + id).build();
        }
        dishRepository.deleteById(id);
    }
}
