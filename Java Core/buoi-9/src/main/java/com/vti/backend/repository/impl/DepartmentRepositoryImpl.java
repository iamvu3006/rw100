package com.vti.backend.repository.impl;

import com.vti.backend.repository.IDepartmentRepository;
import com.vti.entity.Department;
import com.vti.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl implements IDepartmentRepository {
     @Override
    public List<Department> findAll() throws ClassNotFoundException {
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
                 return departments;
             }
         } catch (Exception e) {
             System.out.println("Kết nối với database không thành công" + e.getMessage());
             return new ArrayList<>();
         }
    }

    @Override
    public boolean insert(Department department) throws ClassNotFoundException {
        try (Connection connection = JDBCUtils.getConnection()) {
            String sql = "INSERT INTO department (department_name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, department.getName());
                int result = statement.executeUpdate();
                return result > 0;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm phòng ban: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Department department) throws ClassNotFoundException {
        try (Connection connection = JDBCUtils.getConnection()) {
            String sql = "UPDATE department SET department_name = ? WHERE department_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, department.getName());
                statement.setInt(2, department.getId());
                int result = statement.executeUpdate();
                return result > 0;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật phòng ban: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) throws ClassNotFoundException {
        try (Connection connection = JDBCUtils.getConnection()) {
            String sql = "DELETE FROM department WHERE department_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                int result = statement.executeUpdate();
                return result > 0;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa phòng ban: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
