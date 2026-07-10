package com.vti.service;

import com.vti.dto.PositionDTO;
import com.vti.form.PositionCreateOrUpdateForm;

import java.util.List;

public interface IPositionService {
    List<PositionDTO> findAll();

    PositionDTO findById(Integer id);

    void deleteById(Integer id);

    void create(PositionCreateOrUpdateForm position);

    void update(PositionCreateOrUpdateForm position, Integer id);
}