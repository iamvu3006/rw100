package com.vti.backend.repository.impl;

import com.vti.backend.repository.IPositionRepository;
import com.vti.entity.Position;
import com.vti.enums.PositionName;
import com.vti.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PositionRepositoryImpl implements IPositionRepository {
    @Override
    public List<Position> findAll() {
        List<Position> positions = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection()) {
            String sql = "SELECT * FROM position";
            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    int id = rs.getInt("position_id");
                    String name = rs.getString("position_name");
                    positions.add(new Position(id, PositionName.valueOf(name)));
                }
            }
        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
            e.printStackTrace();
        }
        return positions;
    }

    @Override
    public boolean create(PositionName name) {
        try (Connection connection = JDBCUtils.getConnection()) {
            String sql = "INSERT INTO position (position_name) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name.name());
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm position: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(int id, PositionName name) {
        try (Connection connection = JDBCUtils.getConnection()) {
            String sql = "UPDATE position SET position_name = ? WHERE position_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name.name());
                preparedStatement.setInt(2, id);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật position: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = JDBCUtils.getConnection()) {
            String sql = "DELETE FROM position WHERE position_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa position: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int countByName(PositionName name) throws ClassNotFoundException {
        return countByName(name, null);
    }

    @Override
    public int countByName(PositionName name, Integer excludeId) throws ClassNotFoundException {
        String sql = "SELECT COUNT(1) FROM position WHERE LOWER(TRIM(position_name)) = LOWER(TRIM(?))";
        if (excludeId != null) {
            sql += " AND position_id <> ?";
        }

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name.name());
            if (excludeId != null) {
                preparedStatement.setInt(2, excludeId);
            }
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi kiểm tra position: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int countById(int id) throws ClassNotFoundException {
        String sql = "SELECT COUNT(1) FROM position WHERE position_id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi kiểm tra id position: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
}