package com.vti.utils;

import java.sql.*;

public class JDBCUtils {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/rw100_testing_system";
        String username = "root";
        String password = "";// mk mysql
        Connection connection = null;

        try {
            // b1: kết nối đến DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
//            if (connection != null) {
//                System.out.println("Kết nối DB thành công");
//            }
        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
            e.printStackTrace();
        }
        return connection;
    }

    // close cho 3 interface connection,statement,rs
    public static void closeConnection(Connection connection, Statement statement, ResultSet rs) {
        // nếu cái nào có dữ liệu (đang mở) != null
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
