package com.vti.backend.repository.impl;

import com.vti.backend.repository.ILecturerRepository;
import com.vti.entity.Lecturer;
import com.vti.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Triển khai thao tác truy vấn giảng viên từ bảng Lecturer.
 */
public class LecturerRepositoryImpl implements ILecturerRepository {

    /**
     * Câu 6: Tìm giảng viên theo lecturer_id.
     *
     * @param lecturerId ID cần tìm
     * @return Lecturer nếu tìm thấy, null nếu không có
     */
    @Override
    public Lecturer findById(int lecturerId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Lecturer lecturer = null;

        try {
            // Bước 1: Kết nối database
            connection = JDBCUtils.getConnection();

            // Bước 2: Truy vấn giảng viên theo ID
            String sql = "SELECT * FROM Lecturer WHERE lecturer_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, lecturerId);
            rs = statement.executeQuery();

            // Bước 3: Ánh xạ kết quả sang đối tượng Lecturer
            if (rs.next()) {
                int id          = rs.getInt("lecturer_id");
                String fullName = rs.getString("full_name");
                String email    = rs.getString("email");
                String department = rs.getString("department");
                lecturer = new Lecturer(id, fullName, email, department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement, rs);
        }

        return lecturer;
    }
}