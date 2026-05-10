package backend;

import entity.Account;
import entity.Department;
import entity.Position;
import enums.PositionName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLAccount {
    public static void showAllAccount() throws ClassNotFoundException, SQLException {
        //bước 1: kết nối database
        String url = "jdbc:mysql://localhost:3306/rw100_testing_system";
        String username = "root";
        String password = "";// mk mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Kết nối với database thành công!");

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

                for (Account account : accounts) {
                    System.out.println("ID: " + account.getId()
                            + ", FullName: " + account.getFullName()
                            + ", Email: " + account.getEmail()
                            + ", Username: " + account.getUsername()
                            + ", Department: " + account.getDepartment().getName()
                            + ", Position: " + account.getPosition().getName());
                }
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
        }
    }

    public static void findByFullname(String searchFullname) throws ClassNotFoundException, SQLException {
        //bước 1: kết nối database
        String url = "jdbc:mysql://localhost:3306/rw100_testing_system";
        String username = "root";
        String password = "";// mk mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Kết nối với database thành công!");

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

                    for (Account account : accounts) {
                        System.out.println("ID: " + account.getId()
                                + ", FullName: " + account.getFullName()
                                + ", Email: " + account.getEmail()
                                + ", Username: " + account.getUsername()
                                + ", Department: " + account.getDepartment().getName()
                                + ", Position: " + account.getPosition().getName());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
        }
    }

    public static void findByFullnameAndUsername(String searchFullname, String searchUsername) throws ClassNotFoundException, SQLException {
        //bước 1: kết nối database
        String url = "jdbc:mysql://localhost:3306/rw100_testing_system";
        String username = "root";
        String password = "";// mk mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Kết nối với database thành công!");

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

                    for (Account account : accounts) {
                        System.out.println("ID: " + account.getId()
                                + ", FullName: " + account.getFullName()
                                + ", Email: " + account.getEmail()
                                + ", Username: " + account.getUsername()
                                + ", Department: " + account.getDepartment().getName()
                                + ", Position: " + account.getPosition().getName());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Kết nối với database không thành công" + e.getMessage());
        }
    }
}

