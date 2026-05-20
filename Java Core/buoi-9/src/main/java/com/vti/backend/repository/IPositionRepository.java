package com.vti.backend.repository;


import com.vti.entity.Position;
import com.vti.enums.PositionName;

import java.util.List;

public interface IPositionRepository {
    List<Position> findAll();
    boolean create(PositionName name);
    boolean update(int id, PositionName name);
    boolean delete(int id);
    int countByName(PositionName name) throws ClassNotFoundException;
    int countByName(PositionName name, Integer excludeId) throws ClassNotFoundException;
    int countById(int id) throws ClassNotFoundException;
}