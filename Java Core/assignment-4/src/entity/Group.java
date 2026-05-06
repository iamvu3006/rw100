package entity;

import java.util.Date;


public class Group {
    private int id;
    private String groupName;
    private Account creator;
    private Account[] accounts;
    private Date createDate;

    public Group() {
    }

    public Group(String groupName, Account creator, Account[] accounts, Date createDate) {
        this.groupName = groupName;
        this.creator = creator;
        this.accounts = accounts;
        this.createDate = createDate;
    }

    public Group(String groupName, Account creator, String[] usernames, Date createDate) {
        this.groupName = groupName;
        this.creator = creator;
        this.accounts = convertUsernamesToAccounts(usernames);
        this.createDate = createDate;
    }

    private Account[] convertUsernamesToAccounts(String[] usernames) {
        if (usernames == null) {
            return new Account[0];
        }
        Account[] result = new Account[usernames.length];
        for (int i = 0; i < usernames.length; i++) {
            Account account = new Account();
            account.setUserName(usernames[i]);
            result[i] = account;
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

