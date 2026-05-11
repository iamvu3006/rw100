package backend;

import entity.Position;
import enums.PositionName;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLPosition {
    public static void showAllPosition() throws ClassNotFoundException {
        try {
            //bước 1: kết nối database
            Connection connection = JDBCUtils.getConnection();

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

                for (Position position : positions) {
                    System.out.println("ID: " + position.getId() + ", Name: " + position.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
        }
    }

    public static void findByPositionName(String searchPositionName) throws ClassNotFoundException, SQLException {
        try {
            //bước 1: kết nối database
            Connection connection = JDBCUtils.getConnection();

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

                    for (Position position : positions) {
                        System.out.println("ID: " + position.getId() + ", Name: " + position.getName());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
        }
    }

}
