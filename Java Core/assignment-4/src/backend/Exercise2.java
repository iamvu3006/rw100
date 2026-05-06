package backend;

import entity.Account;

import java.util.Date;

public class Exercise2 {
    public Account[] question1InitAccounts() {
        Account[] accounts = new Account[5];
        for (int i = 0; i < accounts.length; i++) {
            Account account = new Account();
            account.setId(i + 1);
            account.setEmail("Email " + (i + 1));
            account.setUserName("User name " + (i + 1));
            account.setFullName("Full name " + (i + 1));
            account.setCreateDate(new Date());
            accounts[i] = account;
        }
        return accounts;
    }
}

