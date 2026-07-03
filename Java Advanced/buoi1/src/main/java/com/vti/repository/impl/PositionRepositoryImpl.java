package com.vti.repository.impl;

import com.vti.entity.Position;
import com.vti.enums.PositionName;
import com.vti.repository.IPositionRepository;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PositionRepositoryImpl implements IPositionRepository {
    private final SessionFactory sessionFactory = HibernateUtils.sessionFactory;

    @Override
    public void create(Position position) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.persist(position);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        IPositionRepository repository = new PositionRepositoryImpl();
        Position position = new Position();
        position.setName(PositionName.DEV);
        repository.create(position);
    }
}