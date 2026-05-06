package entity;

import java.util.Date;

public class Account {
    private int id;
    private String email;
    private String userName;
    private String fullName;
    private Department department;
    private Position position;
    private Group[] groups;
    private Date createDate;

    public Account() {
    }

    public Account(int id, String email, String userName, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.fullName = buildFullName(firstName, lastName);
    }

    public Account(int id, String email, String userName, String firstName, String lastName, Position position) {
        this(id, email, userName, firstName, lastName);
        this.position = position;
        this.createDate = new Date();
    }

    public Account(int id, String email, String userName, String firstName, String lastName, Position position, Date createDate) {
        this(id, email, userName, firstName, lastName);
        this.position = position;
        this.createDate = createDate;
    }

    private String buildFullName(String firstName, String lastName) {
        String safeFirstName = firstName == null ? "" : firstName.trim();
        String safeLastName = lastName == null ? "" : lastName.trim();
        if (safeFirstName.isEmpty()) {
            return safeLastName;
        }
        if (safeLastName.isEmpty()) {
            return safeFirstName;
        }
        return safeFirstName + " " + safeLastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Group[] getGroups() {
        return groups;
    }

    public void setGroups(Group[] groups) {
        this.groups = groups;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

