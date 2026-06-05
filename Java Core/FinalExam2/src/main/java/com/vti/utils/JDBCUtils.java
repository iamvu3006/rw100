package com.vti.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class JDBCUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/rw100_final_exam_2?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private JDBCUtils() {
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException exception) {
            throw new IllegalStateException("Không thể kết nối database.", exception);
        }
    }

    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException exception) {
                throw new IllegalStateException("Không thể đóng ResultSet.", exception);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException exception) {
                throw new IllegalStateException("Không thể đóng Statement.", exception);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException exception) {
                throw new IllegalStateException("Không thể đóng Connection.", exception);
            }
        }
    }
}
