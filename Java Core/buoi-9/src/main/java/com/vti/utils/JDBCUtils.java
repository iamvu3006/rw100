package com.vti.utils;

import java.sql.*;

public class JDBCUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/rw100_testing_system";
        String username = "root";
        String password = "";// mk mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
        }
        return DriverManager.getConnection(url, username, password);
    }

    //close connection cho 3 interface: Connection, Statement, ResultSet
    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đóng kết nối: " + e.getMessage());
        }
    }
}
