package backend;

import entity.Position;
import enums.PositionName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLPosition {
    public static void showAllPosition() throws ClassNotFoundException {
        //bước 1: kết nối database
        String url = "jdbc:mysql://localhost:3306/rw100_testing_system";
        String username = "root";
        String password = "";// mk mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Kết nối với database thành công!");

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
        //bước 1: kết nối database
        String url = "jdbc:mysql://localhost:3306/rw100_testing_system";
        String username = "root";
        String password = "";// mk mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Kết nối với database thành công!");

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
