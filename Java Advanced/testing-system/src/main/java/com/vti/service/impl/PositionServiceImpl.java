package com.vti.service.impl;

import com.vti.dto.PositionDTO;
import com.vti.entity.Position;
import com.vti.form.PositionCreateOrUpdateForm;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IPositionRepository;
import com.vti.service.IPositionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PositionServiceImpl implements IPositionService {

    @Autowired
    private IPositionRepository positionRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PositionDTO> findAll() {
        return positionRepository.findAll().stream()
                .map(position -> modelMapper.map(position, PositionDTO.class))
                .toList();
    }

    @Override
    public PositionDTO findById(Integer id) {
        Position position = positionRepository.findById(id).orElse(null);
        if (Objects.isNull(position)) {
            return null;
        }
        return modelMapper.map(position, PositionDTO.class);
    }

    @Override
    public void deleteById(Integer id) {
        positionRepository.deleteById(id);
    }

    @Override
    public void create(PositionCreateOrUpdateForm position) {
        Position newPosition = new Position();
        newPosition.setName(position.getName());
        positionRepository.save(newPosition);
    }

    @Override
    public void update(PositionCreateOrUpdateForm position, Integer id) {
        Position positionUpdate = positionRepository.findById(id).orElse(null);
        if (Objects.isNull(positionUpdate)) {
            throw new RuntimeException("Position Not Found");
        }
        positionUpdate.setName(position.getName());
        positionRepository.save(positionUpdate);
    }
}
