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
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM position";
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("position_id");
                String name = rs.getString("position_name");
                positions.add(new Position(id, PositionName.valueOf(name)));
            }
        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
            System.out.println(e.getMessage());
        } finally {
            JDBCUtils.closeConnection(connection, statement, rs);
        }
        return positions;
    }

    @Override
    public boolean create(PositionName name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO position (position_name) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name.name());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm position: " + e.getMessage());
            return false;
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement, null);
        }
    }

    @Override
    public boolean update(int id, PositionName name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "UPDATE position SET position_name = ? WHERE position_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name.name());
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật position: " + e.getMessage());
            return false;
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement, null);
        }
    }

    @Override
    public boolean delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM position WHERE position_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa position: " + e.getMessage());
            return false;
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement, null);
        }
    }

    @Override
    public int countByName(PositionName name) {
        return countByName(name, null);
    }

    @Override
    public int countByName(PositionName name, Integer excludeId) {
        String sql = "SELECT COUNT(1) FROM position WHERE LOWER(TRIM(position_name)) = LOWER(TRIM(?))";
        if (excludeId != null) {
            sql += " AND position_id <> ?";
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name.name());
            if (excludeId != null) {
                preparedStatement.setInt(2, excludeId);
            }
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi kiểm tra position: " + e.getMessage());
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement, rs);
        }
        return 0;
    }

    @Override
    public int countById(int id) {
        String sql = "SELECT COUNT(1) FROM position WHERE position_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi kiểm tra id position: " + e.getMessage());
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement, rs);
        }
        return 0;
    }
}