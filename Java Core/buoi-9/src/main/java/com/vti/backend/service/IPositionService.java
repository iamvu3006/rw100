package com.vti.backend.service;

import com.vti.entity.Position;
import com.vti.enums.PositionName;

import java.util.List;

public interface IPositionService {
    List<Position> findAll();
    boolean create(PositionName name);
    boolean update(int id, PositionName name);
    boolean delete(int id);
}
