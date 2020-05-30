package com.inst.hibernate.repository.sql.impl;

import com.inst.hibernate.domain.PersistObject;
import com.inst.hibernate.repository.sql.AbstractRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dmitry.
 */
public class AbstractRepositoryImpl<T extends PersistObject> implements AbstractRepository<T> {

    private static final Logger logger = LogManager.getLogger(AbstractRepositoryImpl.class);

    private Class clazz;

    AbstractRepositoryImpl(Class clazz) {
        this.clazz = clazz;
    }

    public void update(Session session, T entity) {
        session.update(entity);
    }

    public T getById(Session session, Long id) throws SQLException {
        return (T) session.get(clazz, id);
    }

    public void add(Session session, T entity) {
        session.save(entity);
    }

    public List<T> getAll(Session session) throws SQLException {
        Criteria criteria = session.createCriteria(clazz)
                .addOrder(Order.asc("id"));
        return criteria.list();
    }

    public void delete(Session session, T entity) {
        session.delete(entity);
    }
}
