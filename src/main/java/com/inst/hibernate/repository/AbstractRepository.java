package com.inst.hibernate.repository;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dmitry.
 */
public interface AbstractRepository<T> {

    void update(Session session, T entity);

    T getById(Session session, Long id) throws SQLException;

    void add(Session session, T entity);

    List<T> getAll(Session session) throws SQLException;

    void delete(Session session, T entity);
}
