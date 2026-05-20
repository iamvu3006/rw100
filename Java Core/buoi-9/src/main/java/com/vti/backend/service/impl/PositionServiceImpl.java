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
        try {
            if (name == null) {
                System.out.println("Tên position không được để trống");
                return false;
            }
            if (positionRepository.countByName(name) > 0) {
                System.out.println("Tên position đã tồn tại");
                return false;
            }
            return positionRepository.create(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(int id, PositionName name) {
        try {
            if (id <= 0) {
                System.out.println("ID position không hợp lệ");
                return false;
            }
            if (positionRepository.countById(id) == 0) {
                System.out.println("ID position không tồn tại");
                return false;
            }
            if (name == null) {
                System.out.println("Tên position không được để trống");
                return false;
            }
            if (positionRepository.countByName(name, id) > 0) {
                System.out.println("Tên position đã tồn tại");
                return false;
            }
            return positionRepository.update(id, name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            if (id <= 0) {
                System.out.println("ID position không hợp lệ");
                return false;
            }
            if (positionRepository.countById(id) == 0) {
                System.out.println("ID position không tồn tại");
                return false;
            }
            return positionRepository.delete(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}