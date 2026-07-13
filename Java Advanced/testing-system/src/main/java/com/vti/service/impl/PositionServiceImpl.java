package com.vti.service.impl;

import com.vti.entity.Position;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IPositionRepository;
import com.vti.service.IPositionService;
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

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position findById(Integer id) {
        return positionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        // xoa acc co position_id = id lien quan

        positionRepository.deleteById(id);
    }

    @Override
    public void create(Position account) {
        positionRepository.save(account);
    }

    @Override
    public void update(Position position, Integer id) {
        // tìm position theo id
        Position positionUpdate = positionRepository.findById(id).orElse(null);
        if (Objects.isNull(position)) {
            throw new RuntimeException("Position Not Found");
        }
        // luu vao DB
        positionUpdate.setName(position.getName());
        positionRepository.save(positionUpdate);
    }
}