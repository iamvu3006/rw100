package backend;

import entity.Account;
import entity.Department;
import entity.Position;
import enums.PositionName;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLAccount {
    public static List<Account> showAllAccount() throws ClassNotFoundException, SQLException {
        try {
            //bước 1: kết nối database
            Connection connection = JDBCUtils.getConnection();

            //bước 2: lấy tất cả account
            String sql = "SELECT a.account_id, a.email, a.username, a.full_name, " +
                    "d.department_id, d.department_name, p.position_id, p.position_name " +
                    "FROM `account` a " +
                    "JOIN department d ON a.department_id = d.department_id " +
                    "JOIN position p ON a.position_id = p.position_id";

            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                List<Account> accounts = new ArrayList<>();

                while (rs.next()) {
                    int accountId = rs.getInt("account_id");
                    String email = rs.getString("email");
                    String usernameAccount = rs.getString("username");
                    String fullName = rs.getString("full_name");

                    int departmentId = rs.getInt("department_id");
                    String departmentName = rs.getString("department_name");
                    Department department = new Department(departmentId, departmentName);

                    int positionId = rs.getInt("position_id");
                    PositionName positionName = PositionName.valueOf(rs.getString("position_name").toUpperCase());
                    Position position = new Position(positionId, positionName);

                    Account account = new Account(accountId, usernameAccount, fullName, email, department, position);
                    accounts.add(account);
                }

                showAccount(accounts);
                return accounts;
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<Account> findByFullname(String searchFullname) throws ClassNotFoundException, SQLException {
        try {
            //bước 1: kết nối database
            Connection connection = JDBCUtils.getConnection();

            //bước 2: tìm account theo fullname
            String sql = "SELECT a.account_id, a.email, a.username, a.full_name, " +
                    "d.department_id, d.department_name, p.position_id, p.position_name " +
                    "FROM `account` a " +
                    "JOIN department d ON a.department_id = d.department_id " +
                    "JOIN position p ON a.position_id = p.position_id " +
                    "WHERE a.full_name LIKE ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + searchFullname + "%");

                try (ResultSet rs = statement.executeQuery()) {
                    List<Account> accounts = new ArrayList<>();

                    while (rs.next()) {
                        int accountId = rs.getInt("account_id");
                        String email = rs.getString("email");
                        String usernameAccount = rs.getString("username");
                        String fullName = rs.getString("full_name");

                        int departmentId = rs.getInt("department_id");
                        String departmentName = rs.getString("department_name");
                        Department department = new Department(departmentId, departmentName);

                        int positionId = rs.getInt("position_id");
                        PositionName positionName = PositionName.valueOf(rs.getString("position_name").toUpperCase());
                        Position position = new Position(positionId, positionName);

                        Account account = new Account(accountId, usernameAccount, fullName, email, department, position);
                        accounts.add(account);
                    }

                    showAccount(accounts);
                    return accounts;
                }
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<Account> findByFullnameAndUsername(String searchFullname, String searchUsername) throws ClassNotFoundException, SQLException {
        try {
            Connection connection = JDBCUtils.getConnection();

            //bước 2: tìm account theo fullname và username
            String sql = "SELECT a.account_id, a.email, a.username, a.full_name, " +
                    "d.department_id, d.department_name, p.position_id, p.position_name " +
                    "FROM `account` a " +
                    "JOIN department d ON a.department_id = d.department_id " +
                    "JOIN position p ON a.position_id = p.position_id " +
                    "WHERE a.full_name LIKE ? AND a.username LIKE ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + searchFullname + "%");
                statement.setString(2, "%" + searchUsername + "%");

                try (ResultSet rs = statement.executeQuery()) {
                    List<Account> accounts = new ArrayList<>();

                    while (rs.next()) {
                        int accountId = rs.getInt("account_id");
                        String email = rs.getString("email");
                        String usernameAccount = rs.getString("username");
                        String fullName = rs.getString("full_name");

                        int departmentId = rs.getInt("department_id");
                        String departmentName = rs.getString("department_name");
                        Department department = new Department(departmentId, departmentName);

                        int positionId = rs.getInt("position_id");
                        PositionName positionName = PositionName.valueOf(rs.getString("position_name").toUpperCase());
                        Position position = new Position(positionId, positionName);

                        Account account = new Account(accountId, usernameAccount, fullName, email, department, position);
                        accounts.add(account);
                    }

                    showAccount(accounts);
                    return accounts;
                }
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void showAccount(List<Account> accounts) {
        System.out.println("+----------+-------------------------+------------------------------+--------------------+------------------------------+--------------------+");
        System.out.printf("| %-8s | %-23s | %-28s | %-18s | %-28s | %-18s |%n",
                "ID", "FullName", "Email", "Username", "Department", "Position");
        System.out.println("+----------+-------------------------+------------------------------+--------------------+------------------------------+--------------------+");
        for (Account account : accounts) {
            System.out.printf("| %-8d | %-23s | %-28s | %-18s | %-28s | %-18s |%n",
                    account.getId(),
                    account.getFullName(),
                    account.getEmail(),
                    account.getUsername(),
                    account.getDepartment().getName(),
                    account.getPosition().getName());
        }
        System.out.println("+----------+-------------------------+------------------------------+--------------------+------------------------------+--------------------+");
    }

    public static boolean createAccount(String username, String fullName, String email, int departmentId, int positionId) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO `account` (username, full_name, email, department_id, position_id, create_date) VALUES (?, ?, ?, ?, ?, now())";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, fullName);
            statement.setString(3, email);
            statement.setInt(4, departmentId);
            statement.setInt(5, positionId);
            int c = statement.executeUpdate();
            JDBCUtils.closeConnection(connection, statement, null);
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateAccount(int id, String username, String fullName, String email, int departmentId, int positionId) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "UPDATE `account` SET username = ?, full_name = ?, email = ?, department_id = ?, position_id = ? WHERE account_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, fullName);
            statement.setString(3, email);
            statement.setInt(4, departmentId);
            statement.setInt(5, positionId);
            statement.setInt(6, id);
            int c = statement.executeUpdate();
            JDBCUtils.closeConnection(connection, statement, null);
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteAccount(String username) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM `account` WHERE username LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            int c = statement.executeUpdate();
            JDBCUtils.closeConnection(connection, statement, null);
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

