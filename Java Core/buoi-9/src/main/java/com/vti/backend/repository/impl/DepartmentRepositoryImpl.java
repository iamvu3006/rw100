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
           Connection connection = null;
           Statement statement = null;
           ResultSet rs = null;
           try {
               connection = JDBCUtils.getConnection();
               String sql = "SELECT * FROM department";
               statement = connection.createStatement();
               rs = statement.executeQuery(sql);

               List<Department> departments = new ArrayList<>();
               while (rs.next()) {
                   int id = rs.getInt("department_id");
                   String name = rs.getString("department_name");
                   departments.add(new Department(id, name));
               }
               return departments;
           } catch (Exception e) {
               System.out.println("Kết nối với database không thành công" + e.getMessage());
               return new ArrayList<>();
           } finally {
               JDBCUtils.closeConnection(connection, statement, rs);
           }
    }

    @Override
    public boolean insert(Department department) throws ClassNotFoundException {
          Connection connection = null;
          PreparedStatement statement = null;
          try {
              connection = JDBCUtils.getConnection();
              String sql = "INSERT INTO department (department_name) VALUES (?)";
              statement = connection.prepareStatement(sql);
              statement.setString(1, department.getName());
              int result = statement.executeUpdate();
              return result > 0;
          } catch (Exception e) {
              System.out.println("Lỗi khi thêm phòng ban: " + e.getMessage());
              e.printStackTrace();
              return false;
          } finally {
              JDBCUtils.closeConnection(connection, statement, null);
          }
    }

    @Override
    public boolean update(Department department) throws ClassNotFoundException {
          Connection connection = null;
          PreparedStatement statement = null;
          try {
              connection = JDBCUtils.getConnection();
              String sql = "UPDATE department SET department_name = ? WHERE department_id = ?";
              statement = connection.prepareStatement(sql);
              statement.setString(1, department.getName());
              statement.setInt(2, department.getId());
              int result = statement.executeUpdate();
              return result > 0;
          } catch (Exception e) {
              System.out.println("Lỗi khi cập nhật phòng ban: " + e.getMessage());
              e.printStackTrace();
              return false;
          } finally {
              JDBCUtils.closeConnection(connection, statement, null);
          }
    }

    @Override
    public boolean delete(int id) throws ClassNotFoundException {
          Connection connection = null;
          PreparedStatement statement = null;
          try {
              connection = JDBCUtils.getConnection();
              String sql = "DELETE FROM department WHERE department_id = ?";
              statement = connection.prepareStatement(sql);
              statement.setInt(1, id);
              int result = statement.executeUpdate();
              return result > 0;
          } catch (Exception e) {
              System.out.println("Lỗi khi xóa phòng ban: " + e.getMessage());
              e.printStackTrace();
              return false;
          } finally {
              JDBCUtils.closeConnection(connection, statement, null);
          }
    }

    @Override
    public int countByName(String name) throws ClassNotFoundException {
        return countByName(name, null);
    }

    @Override
    public int countByName(String name, Integer excludeId) throws ClassNotFoundException {
        String sql = "SELECT COUNT(1) FROM department WHERE LOWER(TRIM(department_name)) = LOWER(TRIM(?))";
        if (excludeId != null) {
            sql += " AND department_id <> ?";
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            if (excludeId != null) {
                statement.setInt(2, excludeId);
            }

            rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi kiểm tra phòng ban: " + e.getMessage());
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, statement, rs);
        }
        return 0;
    }

    @Override
    public int countById(int id) throws ClassNotFoundException {
        String sql = "SELECT COUNT(1) FROM department WHERE department_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi kiểm tra id phòng ban: " + e.getMessage());
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, statement, rs);
        }
        return 0;
    }
}
