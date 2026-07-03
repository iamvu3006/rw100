package com.vti.repository.impl;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class AccountRepositoryImpl implements IAccountRepository {

    private final SessionFactory sessionFactory = HibernateUtils.sessionFactory;

    @Override
    public List<Account> findAll() {
        Session session = sessionFactory.openSession();
        String hql = "FROM Account";
        Query<Account> query = session.createQuery(hql, Account.class);
        List<Account> accounts = query.list();
        return accounts;
    }

    @Override
    public Account findById(Integer id) {
        Session session = sessionFactory.openSession();
//        String hql = "FROM Account a where id = :idParam";
//        Query<Account> query = session.createQuery(hql, Account.class);
//        query.setParameter("idParam", id);
//        return query.uniqueResult();
        Account account = session.find(Account.class, id);
        session.close();
        return account;
    }

    @Override
    public void create(Account account) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.persist(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Integer id, String newFullName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            // tìm account có id như trên
            Account account = session.find(Account.class, id);
            account.setFullName(newFullName);
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
            // tim acc co id như tren
            Account account = session.find(Account.class, id);
            session.remove(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }


    public static void main(String[] args) {
        AccountRepositoryImpl repository = new AccountRepositoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
//        List<Account> accounts = accountRepository.findAll();
//        for (Account acc: accounts) {
//            System.out.println(acc);
//        }
//        System.out.println("Account có id = 1 là : "+ repository.findById(1));
        // thêm account
//        Account account = new Account();
//        account.setFullName("demo");
//        account.setEmail("demo@gmail.com");
//        account.setUsername("demo");
//
//        Department department = departmentRepository.findById(1);
//        account.setDep(department);
//
//        repository.create(account);.

        // update tên cho account có id 12
//        repository.update(12, "new update");

        // xóa account có id = 12
        repository.delete(12);
    }
}