package com.vti.backend.repository.impl;

import com.vti.backend.repository.IStudentRepository;
import com.vti.entity.Student;
import com.vti.utils.JDBCUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Triển khai các thao tác CRUD với bảng Student trong database.
 */
public class StudentRepositoryImpl implements IStudentRepository {

    /**
     * Câu 2: Lấy toàn bộ danh sách sinh viên, JOIN với Major để lấy tên chuyên ngành.
     */
    @Override
    public List<Student> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();

        try {
            // Bước 1: Kết nối database
            connection = JDBCUtils.getConnection();

            // Bước 2: Câu SQL JOIN Student với Major
            String sql = "SELECT s.student_id, s.full_name, s.email, s.date_of_birth, " +
                    "s.major_id, m.major_name " +
                    "FROM Student s " +
                    "LEFT JOIN Major m ON s.major_id = m.major_id " +
                    "ORDER BY s.student_id ASC";

            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            // Bước 3: Ánh xạ ResultSet sang đối tượng Student
            while (rs.next()) {
                int studentId    = rs.getInt("student_id");
                String fullName  = rs.getString("full_name");
                String email     = rs.getString("email");
                Date dob         = rs.getDate("date_of_birth");
                LocalDate dateOfBirth = (dob != null) ? dob.toLocalDate() : null;
                int majorId      = rs.getInt("major_id");
                String majorName = rs.getString("major_name");

                Student student = new Student(studentId, fullName, email, dateOfBirth, majorId, majorName);
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement, rs);
        }

        return students;
    }

    /**
     * Câu 3: Thêm mới sinh viên vào bảng Student.
     */
    @Override
    public boolean create(String fullName, String email, LocalDate dateOfBirth, int majorId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Bước 1: Kết nối database
            connection = JDBCUtils.getConnection();

            // Bước 2: Câu SQL INSERT sinh viên mới
            String sql = "INSERT INTO Student (full_name, email, date_of_birth, major_id) " +
                    "VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, email);
            preparedStatement.setDate(3, (dateOfBirth != null) ? Date.valueOf(dateOfBirth) : null);
            preparedStatement.setInt(4, majorId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, preparedStatement, null);
        }

        return false;
    }

    /**
     * Câu 3: Kiểm tra email sinh viên đã tồn tại trong hệ thống hay chưa.
     */
    @Override
    public boolean checkExistEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean isExist = false;

        try {
            // Bước 1: Kết nối database
            connection = JDBCUtils.getConnection();

            // Bước 2: Truy vấn kiểm tra email
            String sql = "SELECT student_id FROM Student WHERE email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            rs = statement.executeQuery();

            // Nếu có kết quả thì email đã tồn tại
            if (rs.next()) {
                isExist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement, rs);
        }

        return isExist;
    }

    /**
     * Câu 4: Cập nhật chuyên ngành mới cho sinh viên theo student_id.
     */
    @Override
    public boolean updateMajor(int studentId, int majorId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Bước 1: Kết nối database
            connection = JDBCUtils.getConnection();

            // Bước 2: Câu SQL UPDATE chuyên ngành
            String sql = "UPDATE Student SET major_id = ? WHERE student_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, majorId);
            preparedStatement.setInt(2, studentId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, preparedStatement, null);
        }

        return false;
    }

    /**
     * Câu 5: Xóa sinh viên theo student_id.
     */
    @Override
    public boolean deleteById(int studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Bước 1: Kết nối database
            connection = JDBCUtils.getConnection();

            // Bước 2: Câu SQL DELETE sinh viên
            String sql = "DELETE FROM Student WHERE student_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, preparedStatement, null);
        }

        return false;
    }

    /**
     * Câu 6: Tìm kiếm sinh viên theo tên chuyên ngành (không phân biệt hoa/thường).
     */
    @Override
    public List<Student> findByMajorName(String majorName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();

        try {
            // Bước 1: Kết nối database
            connection = JDBCUtils.getConnection();

            // Bước 2: Câu SQL JOIN tìm kiếm theo major_name
            String sql = "SELECT s.student_id, s.full_name, s.email, s.date_of_birth, " +
                    "s.major_id, m.major_name " +
                    "FROM Student s " +
                    "LEFT JOIN Major m ON s.major_id = m.major_id " +
                    "WHERE m.major_name LIKE ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + majorName + "%");
            rs = statement.executeQuery();

            // Bước 3: Ánh xạ kết quả sang danh sách Student
            while (rs.next()) {
                int studentId     = rs.getInt("student_id");
                String fullName   = rs.getString("full_name");
                String email      = rs.getString("email");
                Date dob          = rs.getDate("date_of_birth");
                LocalDate dateOfBirth = (dob != null) ? dob.toLocalDate() : null;
                int majorId       = rs.getInt("major_id");
                String mName      = rs.getString("major_name");

                Student student = new Student(studentId, fullName, email, dateOfBirth, majorId, mName);
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement, rs);
        }

        return students;
    }
}