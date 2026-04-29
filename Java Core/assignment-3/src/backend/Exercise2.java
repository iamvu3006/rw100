package backend;

import entity.Account;

import java.util.Date;

public class Exercise2 {
    public Account[] question1InitAccounts() {
        Account[] accounts = new Account[5];
        for (int i = 0; i < accounts.length; i++) {
            Account account = new Account();
            account.id = i + 1;
            account.email = "Email " + (i + 1);
            account.userName = "User name " + (i + 1);
            account.fullName = "Full name " + (i + 1);
            account.createDate = new Date();
            accounts[i] = account;
        }
        return accounts;
    }
}

