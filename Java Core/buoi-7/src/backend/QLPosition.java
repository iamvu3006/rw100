package backend;

import entity.Position;
import enums.PositionName;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLPosition {
    public static List<Position> showAllPosition() {
        try (Connection connection = JDBCUtils.getConnection()) {
            //bước 1: kết nối database
            //bước 2: lấy tất cả position
            String sql = "SELECT * FROM position";
            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                List<Position> positions = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt("position_id"); // Lấy position_id từ database
                    PositionName positionName = PositionName.valueOf(rs.getString("position_name").toUpperCase());
                    Position position = new Position(id, positionName);
                    positions.add(position);
                }

                showPosition(positions);
                return positions;
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<Position> findByPositionName(String searchPositionName) {
        try (Connection connection = JDBCUtils.getConnection()) {
            //bước 1: kết nối database

            //bước 2: tìm position theo tên
            String sql = "SELECT * FROM position WHERE position_name LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + searchPositionName + "%");

                try (ResultSet rs = statement.executeQuery()) {
                    List<Position> positions = new ArrayList<>();

                    while (rs.next()) {
                        int id = rs.getInt("position_id");
                        PositionName positionName = PositionName.valueOf(rs.getString("position_name").toUpperCase());
                        Position position = new Position(id, positionName);
                        positions.add(position);
                    }

                    showPosition(positions);
                    return positions;
                }
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void showPosition(List<Position> positions) {
        System.out.println("+--------------+--------------------+");
        System.out.printf("| %-12s | %-18s |%n", "ID", "Position Name");
        System.out.println("+--------------+--------------------+");
        for (Position position : positions) {
            System.out.printf("| %-12d | %-18s |%n", position.getId(), position.getName());
        }
        System.out.println("+--------------+--------------------+");
    }

    public static boolean createPosition(String name) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO position (position_name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            int c = statement.executeUpdate();
            JDBCUtils.closeConnection(connection, statement, null);
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updatePosition(int id, String updateName) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "UPDATE position SET position_name = ? WHERE position_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, updateName);
            statement.setInt(2, id);
            int c = statement.executeUpdate();
            JDBCUtils.closeConnection(connection, statement, null);
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deletePosition(String deleteName) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM position WHERE position_name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, deleteName);
            int c = statement.executeUpdate();
            JDBCUtils.closeConnection(connection, statement, null);
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
