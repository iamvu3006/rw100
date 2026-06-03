package com.vti.utils;

import java.sql.*;

public class JDBCUtils {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/rw100_final_exam?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String username = "root";
        String password = "";
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
            e.printStackTrace();
        }
        return connection;
    }

    // close resources in proper order: ResultSet -> Statement -> Connection
    public static void closeConnection(Connection connection, Statement statement, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
