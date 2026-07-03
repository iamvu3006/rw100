package com.vti.repository.impl;

import com.vti.entity.Group;
import com.vti.repository.IGroupRepository;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class GroupRepositoryImpl implements IGroupRepository {
    private final SessionFactory sessionFactory = HibernateUtils.sessionFactory;

    @Override
    public List<Group> findAll() {
        Session session = sessionFactory.openSession();
        try {
            String hql = "FROM Group";
            Query<Group> query = session.createQuery(hql, Group.class);
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public Group findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            return session.find(Group.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public void create(Group group) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.persist(group);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Integer id, String newGroupName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            // tìm group cần update
            Group group = session.find(Group.class, id);
            group.setGroupName(newGroupName);
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
            // tìm group cần xóa
            Group group = session.find(Group.class, id);
            session.remove(group);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}