package com.vti.backend.repository.impl;

import com.vti.backend.repository.IUserRepository;
import com.vti.entity.Admin;
import com.vti.entity.Employee;
import com.vti.entity.User;
import com.vti.enums.Role;
import com.vti.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements IUserRepository {

    @Override
    public void seedUsersIfEmpty() {
        String countSql = "SELECT COUNT(*) FROM `User`";
        String insertSql = "INSERT INTO `User` (`FullName`, `Email`, `Password`, `Role`, `ExpInYear`, `ProSkill`) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = JDBCUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(countSql)) {

            if (resultSet.next() && resultSet.getInt(1) == 0) {
                insertSeedUser(connection, insertSql, new Admin(0, "System Admin", "admin@vti.com.vn", "Admin123", 5));
                insertSeedUser(connection, insertSql, new Employee(0, "Nguyen Van A", "nguyenvana@vti.com.vn", "Employee1A", "java"));
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Không thể khởi tạo dữ liệu mặc định.", exception);
        }
    }

    private void insertSeedUser(Connection connection, String insertSql, User user) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole().toDatabaseValue());
            if (user instanceof Admin) {
                preparedStatement.setObject(5, ((Admin) user).getExpInYear());
                preparedStatement.setNull(6, java.sql.Types.VARCHAR);
            } else {
                preparedStatement.setNull(5, java.sql.Types.INTEGER);
                preparedStatement.setString(6, ((Employee) user).getProSkill());
            }
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT `id`, `FullName`, `Email`, `Password`, `Role`, `ExpInYear`, `ProSkill` FROM `User` ORDER BY `id`";
        List<User> users = new ArrayList<>();

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                users.add(mapUser(resultSet));
            }
            return users;
        } catch (SQLException exception) {
            throw new IllegalStateException("Không thể lấy danh sách user.", exception);
        }
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT `id`, `FullName`, `Email`, `Password`, `Role`, `ExpInYear`, `ProSkill` FROM `User` WHERE `id` = ?";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapUser(resultSet);
                }
                return null;
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Không thể tìm user theo id.", exception);
        }
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM `User` WHERE `id` = ?";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new IllegalStateException("Không thể xóa user.", exception);
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        String sql = "SELECT `id`, `FullName`, `Email`, `Password`, `Role`, `ExpInYear`, `ProSkill` FROM `User` WHERE `Email` = ? AND `Password` = ?";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapUser(resultSet);
                }
                return null;
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Không thể login.", exception);
        }
    }

    @Override
    public boolean isEmailExists(String email) {
        String sql = "SELECT 1 FROM `User` WHERE `Email` = ?";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Không thể kiểm tra email trùng.", exception);
        }
    }

    @Override
    public boolean createEmployee(String fullName, String email, String password) {
        String sql = "INSERT INTO `User` (`FullName`, `Email`, `Password`, `Role`, `ExpInYear`, `ProSkill`) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, Role.EMPLOYEE.toDatabaseValue());
            preparedStatement.setNull(5, java.sql.Types.INTEGER);
            preparedStatement.setNull(6, java.sql.Types.VARCHAR);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new IllegalStateException("Không thể tạo employee.", exception);
        }
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String fullName = resultSet.getString("FullName");
        String email = resultSet.getString("Email");
        String password = resultSet.getString("Password");
        Role role = Role.fromDatabaseValue(resultSet.getString("Role"));

        if (role == Role.ADMIN) {
            Integer expInYear = resultSet.getObject("ExpInYear", Integer.class);
            return new Admin(id, fullName, email, password, expInYear);
        }

        String proSkill = resultSet.getString("ProSkill");
        return new Employee(id, fullName, email, password, proSkill);
    }
}
