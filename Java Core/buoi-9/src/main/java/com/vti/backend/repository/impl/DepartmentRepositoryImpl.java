package com.vti.backend.repository.impl;

import com.vti.backend.repository.IDepartmentRepository;
import com.vti.entity.Department;
import com.vti.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class DepartmentRepositoryImpl implements IDepartmentRepository {
    // lấy ra toàn bộ department
    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();// lưu lại dữ liệu lấy từ DB
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng department
            String sql = "select * from department order by department_id asc;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            while (rs.next()) {// lặp qua qua từng dòng của rs
                int id = rs.getInt("department_id");// lấy giá trị từ cloumn department_id
                String name = rs.getString("department_name");//lấy giá trị từ cloumn department_name

                Department dep = new Department(id, name);
                departments.add(dep);
            }
            // đóng các kết nối
            JDBCUtils.closeConnection(connection, statement, rs);
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return departments;
    }

    @Override
    public boolean create(String name) {
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: tiến hành thêm mới department
            String sql = "insert into department (department_name) values (?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            int c = preparedStatement.executeUpdate();// executeUpdate sẽ trả về 1 số nguyên, đại diện cho số dòng bị thay đổi trong DB
            JDBCUtils.closeConnection(connection, preparedStatement, null);
            return c > 0;
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return false;
    }
    @Override
    public boolean createListDepartment(List<Department> list) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // b1: kết nối đến DB
            connection = JDBCUtils.getConnection();
            // b2: tiến hành thêm mới department
            String sql = "insert into department (department_name) values (?);";
            preparedStatement = connection.prepareStatement(sql);
            for (Department department : list) {
                preparedStatement.setString(1, department.getName());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();// executeBatch chay cau lenh insert nhieu ptu
            return true;
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement, null);
        }
        return false;
    }

    @Override
    public Map<String, Department> mapByName() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        //  key       value
        Map<String, Department> mapByName = new HashMap<>();// lưu lại dữ liệu lấy từ DB
        try {
            // b1: kết nối đến DB
            connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng department
            String sql = "select * from department order by department_id asc;";
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            while (rs.next()) {// lặp qua qua từng dòng của rs
                int id = rs.getInt("department_id");// lấy giá trị từ cloumn department_id
                String name = rs.getString("department_name");//lấy giá trị từ cloumn department_name

                Department dep = new Department(id, name);
                mapByName.put(name, dep);
            }
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        } finally {
            JDBCUtils.closeConnection(connection, statement, rs);
        }
        return mapByName;
    }


//    @Override
//    public boolean createListDepartment(List<Department> list) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            // b1: kết nối đến DB
//            connection = JDBCUtils.getConnection();
//            // b2: tiến hành thêm mới department
//            String sql = "insert into department (department_name) values (?);";
//            preparedStatement = connection.prepareStatement(sql);
//            for (Department department : list) {
//                preparedStatement.setString(1, department.getName());
//                preparedStatement.addBatch();
//            }
//            preparedStatement.executeBatch();// executeBatch chay cau lenh insert nhieu ptu
//            return true;
//        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
//            e.printStackTrace();// show ra exception
//        } finally {
//            JDBCUtils.closeConnection(connection, preparedStatement, null);
//        }
//        return false;
//    }

    @Override
    public boolean update(int id, String name) {
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: tiến hành update department
            String sql = "update department set department_name = ? where department_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            int c = preparedStatement.executeUpdate();// executeUpdate sẽ trả về 1 số nguyên, đại diện cho số dòng bị thay đổi trong DB
            // đóng các kết nối
            JDBCUtils.closeConnection(connection, preparedStatement, null);
            return c > 0;
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: tiến hành xóa department
            String sql = "delete from department where department_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int c = preparedStatement.executeUpdate();// executeUpdate sẽ trả về 1 số nguyên, đại diện cho số dòng bị thay đổi trong DB
            JDBCUtils.closeConnection(connection, preparedStatement, null);
            return c > 0;
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return false;
    }

    @Override
    public boolean checkExistID(Integer id) {
        boolean check = false;
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng department
            String sql = "select * from department where department_id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            if (rs.next()) {// lặp qua qua từng dòng của rs
                check = true;
            }
            // đóng các kết nối
            JDBCUtils.closeConnection(connection, preparedStatement, rs);
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return check;
    }


    @Override
    public boolean checkExistNameAndIdNot(String name, Integer id) {// neu id null thi tao moi
        boolean check = false;
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng department
            String sql = "select * from department where department_name like ? ";
            if (Objects.nonNull(id)) {// check update
                sql += "and department_id != ? ";
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            if (Objects.nonNull(id)) {// check update
                preparedStatement.setInt(2, id);
            }

            ResultSet rs = preparedStatement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            if (rs.next()) {// lặp qua qua từng dòng của rs
                check = true;
            }
            // đóng các kết nối
            JDBCUtils.closeConnection(connection, preparedStatement, rs);
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return check;
    }


}
