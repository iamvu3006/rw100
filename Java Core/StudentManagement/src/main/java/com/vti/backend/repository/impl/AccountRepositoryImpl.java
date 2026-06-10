package com.vti.backend.repository.impl;

import com.vti.backend.repository.IAccountRepository;
import com.vti.entity.Account;
import com.vti.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Triển khai thao tác xác thực tài khoản với bảng Account.
 */
public class AccountRepositoryImpl implements IAccountRepository {

    /**
     * Câu 1: Kiểm tra email và password trong bảng Account.
     *
     * @return Account nếu thông tin đúng, null nếu sai
     */
    @Override
    public Account login(String email, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Account account = null;

        try {
            // Bước 1: Kết nối database
            connection = JDBCUtils.getConnection();

            // Bước 2: Truy vấn tài khoản theo email và password
            String sql = "SELECT * FROM Account WHERE email = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            rs = statement.executeQuery();

            // Bước 3: Ánh xạ kết quả sang đối tượng Account
            if (rs.next()) {
                int accountId  = rs.getInt("account_id");
                String fullName = rs.getString("full_name");
                account = new Account(accountId, email, password, fullName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement, rs);
        }

        return account;
    }
}