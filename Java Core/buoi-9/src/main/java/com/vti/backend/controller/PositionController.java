package com.vti.backend.controller;

import com.vti.backend.service.IPositionService;
import com.vti.backend.service.impl.PositionServiceImpl;
import com.vti.entity.Position;
import com.vti.enums.PositionName;

import java.util.List;

public class PositionController {
    // khoi tao positionService
    private IPositionService positionService = new PositionServiceImpl();

    public List<Position> findAll() {
        return positionService.findAll();
    }

    public boolean create(PositionName name) {
        return positionService.create(name);
    }

    public boolean update(int id, PositionName name) {
        return positionService.update(id, name);
    }

    public boolean delete(int id) {
        return positionService.delete(id);
    }
}
