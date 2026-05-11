package backend;

import entity.Department;
import utils.JDBCUtils;

import java.sql.*;
import java.util.*;

public class QLDepartment {
    public static List<Department> showDepartment() {
        try (Connection connection = JDBCUtils.getConnection()) {
            //bước 1: kết nối database
            String sql = "SELECT * FROM department";
            try (Statement statement = connection.createStatement(); //tạo đối tượng statement để thực hiện truy vấn SQL
                 ResultSet rs = statement.executeQuery(sql)) {
                List<Department> departments = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt("department_id"); //lấy dữ liệu cột department_id
                    String name = rs.getString("department_name"); //lấy ữ liệu cột department_name
                    departments.add(new Department(id, name));
                }

                showDepartment(departments);
                return departments;
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
            return new ArrayList<>();
        }
    }

    //tìm các phòng ban có chữ xyz chưa biết trước
    public static List<Department> findByNameAndId(String searchName, int searchId) {
        try (Connection connection = JDBCUtils.getConnection()) {
            //bước 1: kết nối database
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
                departments.add(new Department(id, deptName));
            }

            showDepartment(departments);
            return departments;
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
            return new ArrayList<>();
        }
    }

    // in ra các phòng ban có >=2 nhân viên
    public static List<Department> showDepartmentHasAtLeast2Employees() {
        try (Connection connection = JDBCUtils.getConnection()) {
            //bước 1: kết nối database
            //bước 2: lấy các phòng ban có từ 2 nhân viên trở lên
            String sql =
                    "SELECT d.department_id, d.department_name, COUNT(a.account_id) AS total_member " +
                            "FROM department d " +
                            "JOIN `account` a ON d.department_id = a.department_id " +
                            "GROUP BY d.department_id, d.department_name " +
                            "HAVING COUNT(a.account_id) >= 2";

            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                List<Department> departments = new ArrayList<>();
                List<Integer> totalMembers = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt("department_id"); //lấy giá trị của cột department_id
                    String name = rs.getString("department_name"); //lấy giá trị của cột department_name
                    int totalMember = rs.getInt("total_member"); //lấy giá trị của cột total_member (số lượng nhân viên trong phòng ban)
                    departments.add(new Department(id, name));
                    totalMembers.add(totalMember);
                }

                showDepartmentWithTotalMember(departments, totalMembers);
                return departments;
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void showDepartment(List<Department> departments) {
        System.out.println("+--------------+------------------------------+");
        System.out.printf("| %-12s | %-28s |%n", "ID", "Department Name");
        System.out.println("+--------------+------------------------------+");
        for (Department department : departments) {
            System.out.printf("| %-12d | %-28s |%n", department.getId(), department.getName());
        }
        System.out.println("+--------------+------------------------------+");
    }

    private static void showDepartmentWithTotalMember(List<Department> departments, List<Integer> totalMembers) {
        System.out.println("+--------------+------------------------------+--------------+");
        System.out.printf("| %-12s | %-28s | %-12s |%n", "ID", "Department Name", "TotalMember");
        System.out.println("+--------------+------------------------------+--------------+");
        for (int i = 0; i < departments.size(); i++) {
            Department department = departments.get(i);
            Integer totalMember = totalMembers.get(i);
            System.out.printf("| %-12d | %-28s | %-12d |%n", department.getId(), department.getName(), totalMember);
        }
        System.out.println("+--------------+------------------------------+--------------+");
    }

    public static boolean createDepartment(String name) {
        try {
            //bước 1: kết nối database
            Connection connection = JDBCUtils.getConnection();
            //bước 2: tạo phòng ban mới
            String sql = "INSERT INTO department (department_name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql); //thực hiện truy vấn SQL với tham số
            statement.setString(1, name); //truyền giá trị name vào dấu ? thứ 1
            //thực thi câu lệnh sql
            int c = statement.executeUpdate();
//            if (c > 0) {
//                return true;
//            }
//            else {
//                return false;
//            }
            JDBCUtils.closeConnection(connection, statement, null);
            return c > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //nhập vào tên phòng ban và xóa phòng ban đó đi
    public static boolean deleteDepartment(String deleteName) {
        try {
            //bước 1: kết nối database
            Connection connection = JDBCUtils.getConnection();
            //bước 2: xóa phòng ban
            String sql = "DELETE FROM department WHERE  department_name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql); //thực hiện truy vấn SQL với tham số
            statement.setString(1, deleteName); //truyền giá trị name vào dấu ? thứ 1
            //thực thi câu lệnh sql
            int c = statement.executeUpdate();
//            if (c > 0) {
//                return true;
//            }
//            else {
//                return false;
//            }
            JDBCUtils.closeConnection(connection, statement, null);
            return c > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateDepartment(int id, String updateName) {
        try {
            //bước 1: kết nối database
            Connection connection = JDBCUtils.getConnection();
            //bước 2: update phòng ban
            String sql = "update department set department_name = ? where department_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql); //thực hiện truy vấn SQL với tham số
            statement.setString(1, updateName); //truyền giá trị name vào dấu ? thứ 1
            statement.setInt(2, id); //truyền giá trị 1 vào dấu ? thứ 2
            //thực thi câu lệnh sql
            int c = statement.executeUpdate();
//            if (c > 0) {
//                return true;
//            }
//            else {
//                return false;
//            }
            JDBCUtils.closeConnection(connection, statement, null);
            return c > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}