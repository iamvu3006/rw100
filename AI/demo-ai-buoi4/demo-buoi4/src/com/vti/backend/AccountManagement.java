package com.vti.backend;

import java.util.ArrayList;
import java.util.List;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.entity.Position.PositionName;

public class AccountManagement {
    private List<Account> accounts;

    public AccountManagement() {
        accounts = new ArrayList<>();

        Department itDepartment = new Department(1, "Phòng IT");
        Department devDepartment = new Department(2, "Phòng Dev");
        Department testDepartment = new Department(3, "Phòng Test");

        Position pmPosition = new Position(1, PositionName.PM);
        Position devPosition = new Position(2, PositionName.DEV);
        Position testPosition = new Position(3, PositionName.TEST);

        accounts.add(new Account(1, "admin", "Administrator", itDepartment, pmPosition));
        accounts.add(new Account(2, "user1", "Nguyen Van A", devDepartment, devPosition));
        accounts.add(new Account(3, "user2", "Tran Thi B", testDepartment, testPosition));
    }

    public List<Account> getListAccount() {
        return new ArrayList<>(accounts);
    }

    public void printAccounts() {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public Account getAccountById(int id) {
        for (Account account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public boolean updateAccount(int id, String newUsername, String newFullname,
            Department newDepartment, Position newPosition) {
        Account account = getAccountById(id);
        if (account == null) {
            return false;
        }

        account.setUsername(newUsername);
        account.setFullname(newFullname);
        account.setDepartment(newDepartment);
        account.setPosition(newPosition);
        return true;
    }

    public boolean deleteAccount(int id) {
        Account account = getAccountById(id);
        if (account == null) {
            return false;
        }

        return accounts.remove(account);
    }
}
