package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/rw100_testing_system";
        String username = "root";
        String password = "";// mk mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Kết nối với database thành công!");
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
        }
        return DriverManager.getConnection(url, username, password);
    }
}
