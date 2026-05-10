package backend;

import entity.Department;

import java.sql.*;
import java.util.*;

public class QLDepartment {
    public static void showDepartment() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/rw100_testing_system";
        String username = "root";
        String password = "";// mk mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Kết nối với database thành công!");

            String sql = "SELECT * FROM department";
            try (Statement statement = connection.createStatement(); //tạo đối tượng statement để thực hiện truy vấn SQL
                 ResultSet rs = statement.executeQuery(sql)) {
                List<Department> departments = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt("department_id"); //lấy dữ liệu cột department_id
                    String name = rs.getString("department_name"); //lấy ữ liệu cột department_name
                    Department department = new Department(id, name);
                    departments.add(department);
                }

                for (Department department : departments) {
                    System.out.println("ID: " + department.getId() + ", Name: " + department.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
        }
    }

    //tìm các phòng ban có chữ xyz chưa biết trước
    public static void findByNameAndId(String searchName, int searchId) throws ClassNotFoundException, SQLException {
        //bước 1: kết nối database
        String url = "jdbc:mysql://localhost:3306/rw100_testing_system";
        String username = "root";
        String password = "";// mk mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Kết nối với database thành công!");

            //bước 2: tìm các phòng ban có tên là name
            String sql = "SELECT * FROM department WHERE department_name LIKE ? and department_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql); //thực hiện truy vấn SQL với tham số

            statement.setString(1, searchName); //truyền giá trị searchName vào dấu ? thứ 1
            statement.setInt(2, searchId); //truyền giá trị 2 vào dấu ? thứ 2

            ResultSet rs = statement.executeQuery();

            List<Department> departments = new ArrayList<>(); //lưu lại giá trị lấy từ database

            while (rs.next()) { //lặp qua từng dòng của rs
                int id = rs.getInt("department_id"); //lấy giá trị của cột department_id
                String deptName = rs.getString("department_name"); //lấy giá trị của cột department_name
                Department department = new Department(id, deptName);
                departments.add(department);
            }

            for (Department department : departments) {
                System.out.println("ID: " + department.getId() + ", Name: " + department.getName());
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
        }
    }

    // in ra các phòng ban có >=2 nhân viên
    public static void showDepartmentHasAtLeast2Employees() throws ClassNotFoundException, SQLException {
        //bước 1: kết nối database
        String url = "jdbc:mysql://localhost:3306/rw100_testing_system";
        String username = "root";
        String password = "";// mk mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Kết nối với database thành công!");

            //bước 2: lấy các phòng ban có từ 2 nhân viên trở lên
            String sql =
                    "SELECT d.department_id, d.department_name, COUNT(a.account_id) AS total_member " +
                    "FROM department d " +
                    "JOIN `account` a ON d.department_id = a.department_id " +
                    "GROUP BY d.department_id " +
                    "HAVING COUNT(a.account_id) >= 2";

            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    int id = rs.getInt("department_id"); //lấy giá trị của cột department_id
                    String name = rs.getString("department_name"); //lấy giá trị của cột department_name
                    int totalMember = rs.getInt("total_member"); //lấy giá trị của cột total_member (số lượng nhân viên trong phòng ban)
                    System.out.println("ID: " + id + ", Name: " + name + ", Total Member: " + totalMember);
                }
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
        }
    }
}