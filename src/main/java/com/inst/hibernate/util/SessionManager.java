package com.inst.hibernate.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Dmitry.
 */
public class SessionManager {

    private static final Logger logger = LogManager.getLogger(SessionManager.class);


    private static SessionManager sessionManager;
    private static SessionFactory sessionFactory;


    public static SessionManager getInstance() {
        if (sessionManager == null) {
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }

    public Session getSession() {
        if (sessionFactory != null) {
            return sessionFactory.getCurrentSession();
        } else {
            Configuration configuration = new Configuration();
            configuration.configure();

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory.openSession();
    }

    public void closeSession(Session session) {
        try {
            if (session != null) {
                session.close();
            }
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }
    }
}
