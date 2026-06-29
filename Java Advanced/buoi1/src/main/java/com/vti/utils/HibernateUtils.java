package com.vti.utils;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    public static SessionFactory sessionFactory;

    static {
        Configuration cfg = new Configuration();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        // kết nối đến DB
        Session session = sessionFactory.openSession();
    }
}
