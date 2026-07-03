package com.vti.repository;

import com.vti.entity.Account;
import com.vti.entity.Group;
import com.vti.entity.GroupAccount;

import java.util.List;

public interface IGroupAccountRepository {
    List<GroupAccount> findAll();
    GroupAccount findById(Integer id);
    void create(GroupAccount groupAccount);
    void update(Integer id, Account newAccount, Group newGroup);
    void delete(Integer id);
}