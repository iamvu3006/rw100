package com.vti.backend.service.impl;

import com.vti.backend.repository.IPositionRepository;
import com.vti.backend.repository.impl.PositionRepositoryImpl;
import com.vti.backend.service.IPositionService;
import com.vti.entity.Position;
import com.vti.enums.PositionName;

import java.util.List;

public class PositionServiceImpl implements IPositionService {
    // khoi tao positionRepository
    private IPositionRepository positionRepository = new PositionRepositoryImpl();

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public boolean create(PositionName name) {
        return positionRepository.create(name);
    }

    @Override
    public boolean update(int id, PositionName name) {
        return positionRepository.update(id, name);
    }

    @Override
    public boolean delete(int id) {
        return positionRepository.delete(id);
    }
}
