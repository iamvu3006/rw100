package com.vti.repository.impl;

import com.vti.entity.Account;
import com.vti.entity.Group;
import com.vti.entity.GroupAccount;
import com.vti.repository.IGroupAccountRepository;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class GroupAccountRepositoryImpl implements IGroupAccountRepository {
    private final SessionFactory sessionFactory = HibernateUtils.sessionFactory;

    @Override
    public List<GroupAccount> findAll() {
        Session session = sessionFactory.openSession();
        try {
            String hql = "FROM GroupAccount";
            Query<GroupAccount> query = session.createQuery(hql, GroupAccount.class);
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public GroupAccount findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            return session.find(GroupAccount.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public void create(GroupAccount groupAccount) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.persist(groupAccount);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Integer id, Account newAccount, Group newGroup) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            // tìm bản ghi group_account cần update
            GroupAccount groupAccount = session.find(GroupAccount.class, id);
            groupAccount.setAccount(newAccount);
            groupAccount.setGroup(newGroup);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            // tìm bản ghi group_account cần xóa
            GroupAccount groupAccount = session.find(GroupAccount.class, id);
            session.remove(groupAccount);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}