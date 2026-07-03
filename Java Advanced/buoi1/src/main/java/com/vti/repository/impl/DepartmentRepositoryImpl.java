package com.vti.repository.impl;

import com.vti.entity.Department;
import com.vti.repository.IDepartmentRepository;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl implements IDepartmentRepository {
    private final SessionFactory sessionFactory = HibernateUtils.sessionFactory;

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            String hql = "From Department";// gọi đén class chứ ko phải table ở sql
            Query<Department> query = session.createQuery(hql, Department.class);
            departments = query.list();// lay ds
        } finally {
            session.close();
        }
        return departments;
    }

    @Override
    public Department findById(Integer id) {
        Department department = new Department();
        Session session = sessionFactory.openSession();
        try {
            String hql = "From Department where id = :idParam";
            Query<Department> query = session.createQuery(hql, Department.class);
            query.setParameter("idParam", 1);
            department = query.uniqueResult();
        } finally {
            session.close();
        }

        return department;
    }

    @Override
    public void create(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Department department = new Department();
            department.setName(name);

            session.persist(department);
            // commit dữ liệu nếu thành công
            session.getTransaction().commit();
        } catch (Exception e) {
            // hoàn lại dữ liệu nếu gặp lỗi
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(String updateName, Integer id) {
        Session session = sessionFactory.openSession();// Connection JDBC
        session.beginTransaction();
        try {
            // tifm ra department caafn update
//            Department department = this.findById(id);
            Department department = session.find(Department.class, id);

            department.setName(updateName);
            session.getTransaction().commit();
        } catch (Exception e) {
            // hoàn lại dữ liệu nếu gặp lỗi
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
            // tìm department cần xóa
            Department department = session.find(Department.class, id);
            session.remove(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}