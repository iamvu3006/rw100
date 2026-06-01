package com.vti.backend.repository.impl;

import com.vti.backend.repository.IAccountRepository;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.enums.PositionName;
import com.vti.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AccountRepositoryImpl implements IAccountRepository {
    // lấy ra các cặp username và account tương ứng


    @Override
    public Map<String, Account> mapByUsername() {
            //  key ,  value    key ko được trùng lặp
        Map<String, Account> mapByUsername = new HashMap<>();// lưu lại dữ liệu lấy từ DB
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng account
            String sql = "select acc.*, de.department_name, po.position_name \n" +
                    "from account acc\n" +
                    "left join department de on acc.department_id = de.department_id\n" +
                    "left join position po on acc.position_id = po.position_id;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            while (rs.next()) {// lặp qua qua từng dòng của rs
                Integer id = rs.getInt("account_id");// lấy giá trị từ cloumn account_id
                String email = rs.getString("email");//lấy giá trị từ cloumn account_name
                String userName = rs.getString("username");
                String fullName = rs.getString("full_name");
                Integer departmentID = rs.getInt("department_id");
                String departmentName = rs.getString("department_name");
                Integer positionID = rs.getInt("position_id");
                String positionName = rs.getString("position_name");
                Date createDate = rs.getDate("create_date");

                Department department = new Department(departmentID, departmentName);
                Position position = new Position(positionID, PositionName.valueOf(positionName));

                Account account = new Account(id, userName, fullName, email, department, position, createDate);
                mapByUsername.put(userName, account);
            }
        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
            e.printStackTrace();
        }
        return mapByUsername;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();// lưu lại dữ liệu lấy từ DB
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng account
            String sql = "select acc.*, de.department_name, po.position_name \n" +
                    "from account acc\n" +
                    "left join department de on acc.department_id = de.department_id\n" +
                    "left join position po on acc.position_id = po.position_id;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            while (rs.next()) {// lặp qua qua từng dòng của rs
                Integer id = rs.getInt("account_id");// lấy giá trị từ cloumn account_id
                String email = rs.getString("email");//lấy giá trị từ cloumn account_name
                String userName = rs.getString("username");
                String fullName = rs.getString("full_name");
                Integer departmentID = rs.getInt("department_id");
                String departmentName = rs.getString("department_name");
                Integer positionID = rs.getInt("position_id");
                String positionName = rs.getString("position_name");
                Date createDate = rs.getDate("create_date");

                Department department = new Department(departmentID, departmentName);
                Position position = new Position(positionID, PositionName.valueOf(positionName));

                Account account = new Account(id, userName, fullName, email, department, position, createDate);
                accounts.add(account);
            }
        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public boolean create(String email, String username, String fullName, int departmentID, int positionID) {
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: tiến hành thêm mới account
            String sql = "INSERT INTO account (email, username, full_name, department_id, position_id)\n" +
                    "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, fullName);
            preparedStatement.setInt(4, departmentID);
            preparedStatement.setInt(5, positionID);

            int c = preparedStatement.executeUpdate();// executeUpdate sẽ trả về 1 số nguyên, đại diện cho số dòng bị thay đổi trong DB
            return  c > 0;
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return false;
    }

    @Override
    public boolean update(int id, String updateName, String email, String username, int departmentId, int positionId) {
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: tiến hành update account
            String sql = "update account set full_name = ?, email = ?, username = ?, department_id = ?, position_id = ? " +
                    "where account_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updateName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, username);
            preparedStatement.setInt(4, departmentId);
            preparedStatement.setInt(5, positionId);
            preparedStatement.setInt(6, id);

            int c = preparedStatement.executeUpdate();// executeUpdate sẽ trả về 1 số nguyên, đại diện cho số dòng bị thay đổi trong DB
            return  c > 0;
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
            // b2: tiến hành xóa account
            String sql = "delete from account where account_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int c = preparedStatement.executeUpdate();// executeUpdate sẽ trả về 1 số nguyên, đại diện cho số dòng bị thay đổi trong DB
            return  c > 0;
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return false;
    }

    @Override
    public Map<String, Account> mapAccountByUsername() {
        Map<String, Account> mapAccountByUsername = new HashMap<>();
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng account
            String sql = "select acc.*, de.department_name, po.position_name \n" +
                    "from account acc\n" +
                    "left join department de on acc.department_id = de.department_id\n" +
                    "left join position po on acc.position_id = po.position_id;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            while (rs.next()) {// lặp qua qua từng dòng của rs
                Integer id = rs.getInt("account_id");// lấy giá trị từ cloumn account_id
                String email = rs.getString("email");//lấy giá trị từ cloumn account_name
                String userName = rs.getString("username");
                String fullName = rs.getString("full_name");
                Integer departmentID = rs.getInt("department_id");
                String departmentName = rs.getString("department_name");
                Integer positionID = rs.getInt("position_id");
                String positionName = rs.getString("position_name");
                Date createDate = rs.getDate("create_date");

                Department department = new Department(departmentID, departmentName);
                Position position = new Position(positionID, PositionName.valueOf(positionName));

                Account account = new Account(id, userName, fullName, email, department, position, createDate);

                mapAccountByUsername.put(userName, account);
            }
        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
            e.printStackTrace();
        }
        return mapAccountByUsername;
    }

    @Override
    public Map<String, Account> mapAccountByEmail() {
        Map<String, Account> mapAccountByEmail = new HashMap<>();
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng account
            String sql = "select acc.*, de.department_name, po.position_name \n" +
                    "from account acc\n" +
                    "left join department de on acc.department_id = de.department_id\n" +
                    "left join position po on acc.position_id = po.position_id;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            while (rs.next()) {// lặp qua qua từng dòng của rs
                Integer id = rs.getInt("account_id");// lấy giá trị từ cloumn account_id
                String email = rs.getString("email");//lấy giá trị từ cloumn account_name
                String userName = rs.getString("username");
                String fullName = rs.getString("full_name");
                Integer departmentID = rs.getInt("department_id");
                String departmentName = rs.getString("department_name");
                Integer positionID = rs.getInt("position_id");
                String positionName = rs.getString("position_name");
                Date createDate = rs.getDate("create_date");

                Department department = new Department(departmentID, departmentName);
                Position position = new Position(positionID, PositionName.valueOf(positionName));

                Account account = new Account(id, userName, fullName, email, department, position, createDate);

                mapAccountByEmail.put(email, account);
            }
        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
            e.printStackTrace();
        }
        return mapAccountByEmail;
    }

    //    @Override
//    public boolean checkUsernameExist(String username) {
//        boolean checkUsernameExist = false;
//        try {
//            // b1: kết nối đến DB
//            Connection connection = JDBCUtils.getConnection();
//            // b2: lấy dữ liệu từ bảng account
//            String sql = "select * from account where username like ?";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, username);
//
//            ResultSet rs = statement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
//            if (rs.next()) {// lặp qua qua từng dòng của rs
//                checkUsernameExist = true;
//            }
//            JDBCUtils.closeConnection(connection, statement, rs);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return checkUsernameExist;
//    }
    @Override
    public boolean checkUsernameExist(String username, Integer id) {
        boolean checkUsernameExist = false;
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng account
//            String sql = "select * from account where username like ?";
//            if (Objects.nonNull(id)) {
//                sql += " and account_id != ?;";
//            }
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, username);
            //if (Objects.nonNull(id)) {
//                statement.setInt(2, id);
//            }

            String sql =  "select * from account where username like ? and (account_id != ? or ? is null);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setInt(2, id);
            statement.setInt(3, id);

            ResultSet rs = statement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            if (rs.next()) {// lặp qua qua từng dòng của rs
                checkUsernameExist = true;
            }
            JDBCUtils.closeConnection(connection, statement, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkUsernameExist;
    }

    @Override
    public boolean checkEmailExist(String email) {
        boolean checkEmailExist = false;
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng account
            String sql = "select * from account where email like ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            if (rs.next()) {// lặp qua qua từng dòng của rs
                checkEmailExist = true;
            }
            JDBCUtils.closeConnection(connection, statement, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkEmailExist;
    }

    @Override
    public boolean checkIdExist(Integer id) {
        boolean checkIdExist = false;
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng account
            String sql = "select * from account where account_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            if (rs.next()) {// lặp qua qua từng dòng của rs
                checkIdExist = true;
            }
            JDBCUtils.closeConnection(connection, statement, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkIdExist;
    }

    @Override
    public boolean update(int id, String updateName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // b1: kết nối đến DB
            connection = JDBCUtils.getConnection();
            // b2: tiến hành update account
            String sql = "update account set username = ? where account_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updateName);
            preparedStatement.setInt(2, id);

            int c = preparedStatement.executeUpdate();// executeUpdate sẽ trả về 1 số nguyên, đại diện cho số dòng bị thay đổi trong DB
            return  c > 0;
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        } finally {
            //ddosng ket noi
            JDBCUtils.closeConnection(connection, preparedStatement, null);
        }
        return false;
    }

    @Override
    public boolean createAccounts(List<Account> accounts) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // b1: kết nối đến DB
            connection = JDBCUtils.getConnection();
            // b2: tiến hành thêm mới account
            String sql = "INSERT INTO account (email, username, full_name, department_id, position_id)\n" +
                    "VALUES (?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            for (Account account : accounts) {
                preparedStatement.setString(1, account.getEmail());
                preparedStatement.setString(2, account.getUsername());
                preparedStatement.setString(3, account.getFullName());
                preparedStatement.setInt(4, account.getDepartment().getId());
                preparedStatement.setInt(5, account.getPosition().getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

            return  true;
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }  finally {
            JDBCUtils.closeConnection(connection, preparedStatement, null);
        }
        return false;
    }


    public void chia(int a, int b) {
        System.out.println(a/b);// a= 10   b=0
    }
}
