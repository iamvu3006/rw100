package com.vti.service;

import com.vti.entity.Position;

import java.util.List;

public interface IPositionService {
    List<Position> findAll();

    Position findById(Integer id);

    void deleteById(Integer id);

    void create(Position account);

    void update(Position account, Integer id);
}