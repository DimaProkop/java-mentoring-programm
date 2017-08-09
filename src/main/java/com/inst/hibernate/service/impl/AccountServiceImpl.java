package com.inst.hibernate.service.impl;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;
import com.inst.hibernate.repository.AccountRepository;
import com.inst.hibernate.service.AccountService;
import com.inst.hibernate.util.SessionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Dmitry.
 */
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void add(Account account) {
        Session session = SessionManager.getInstance().getSession();
        try {
            accountRepository.add(session, account);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    public void update(Account account) {
        Session session = SessionManager.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            accountRepository.update(session, account);
            transaction.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    public void delete(Account account) {
        Session session = SessionManager.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            accountRepository.delete(session, account);
            transaction.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    public Account get(Long id) {
        Account account = null;
        Session session = SessionManager.getInstance().getSession();
        try {
            account = accountRepository.getById(session, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
        return account;
    }

    public List<Account> getAll() {
        List<Account> accounts = null;
        Session session = SessionManager.getInstance().getSession();
        try {
            accounts = accountRepository.getAll(session);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
        return accounts;
    }
}
