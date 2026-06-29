package com.vti.backend;

import com.vti.entity.Department;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        // lấy ds department từ DB
        // b1: tạo session kết nối đến DB
        SessionFactory sessionFactory;
        Configuration cfg = new Configuration();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        // kết nối đến DB
        Session session = sessionFactory.openSession(); // tạo connection

        // lấy dữ liệu từ bảng department
//        List<Department> departments = new ArrayList<>();
//        String hql = "From Department";
//        Query<Department> query = session.createQuery(hql, Department.class);
//        departments = query.list();
//        for (Department de : departments) {
//            System.out.println(de.toString());
//        }

        //thêm mới 1 department
        session.beginTransaction();

        Department department = new Department();
        department.setName("Phòng ban demo");

        session.persist(department);
        session.getTransaction().commit();


        //update tên "departmentName5" cho department có id = 5
        //b1: tìm department có id = 5
        List<Department> departments = new ArrayList<>();
        String hql = "From Department where id = :idParam";
        Query<Department> query = session.createQuery(hql, Department.class);
        query.setParameter("idParam", 5);
        Department departmentUpdate = query.getSingleResult();
        departments = query.list();
        for (Department de : departments) {
            System.out.println(de.toString());
        }
        //b2: update thông tin cho department trên
    }
}