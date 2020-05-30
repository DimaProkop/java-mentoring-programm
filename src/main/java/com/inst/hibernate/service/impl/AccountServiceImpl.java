package com.inst.hibernate.service.impl;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.repository.nosql.AccountMongoRepositoryImpl;
import com.inst.hibernate.repository.sql.AccountRepository;
import com.inst.hibernate.service.AccountService;
import com.inst.hibernate.util.SessionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static com.inst.hibernate.util.nosql.WrapperService.accountToMongoObject;
import static com.inst.hibernate.util.nosql.WrapperService.accountFromMongoObject;
import static com.inst.hibernate.util.nosql.WrapperService.getAccountsFromMongoObjects;

import java.util.List;

/**
 * Created by Dmitry.
 */
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);

    private AccountRepository accountRepository;
    private AccountMongoRepositoryImpl accountMongoRepository;
    private boolean flag = false;

    public AccountServiceImpl(final AccountRepository accountRepository, final AccountMongoRepositoryImpl accountMongoRepository) {
        this.accountRepository = accountRepository;
        this.accountMongoRepository = accountMongoRepository;
    }

    public void add(final Account account) {
        Session session = SessionManager.getInstance().getSession();
        try {
            if (flag) {
                accountMongoRepository.add(accountToMongoObject(account));
            } else {
                accountRepository.add(session, account);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    public void update(final Account oldAccount,final Account account) {
        Session session = SessionManager.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (flag) {
                accountMongoRepository.update(accountToMongoObject(oldAccount), accountToMongoObject(account));
            } else {
                accountRepository.update(session, account);
            }
            transaction.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    public void delete(final Account account) {
        Session session = SessionManager.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (flag) {
                accountMongoRepository.delete(accountToMongoObject(account));
            } else {
                accountRepository.delete(session, account);
            }
            transaction.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    public Account get(final Long id, String name) {
        Account account = null;
        Session session = SessionManager.getInstance().getSession();
        try {
            if (flag) {
                account = accountFromMongoObject(accountMongoRepository.getByName(name));
            } else {
                account = accountRepository.getById(session, id);
            }
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
            if (flag) {
                accounts = getAccountsFromMongoObjects(accountMongoRepository.getAll());
            } else {
                accounts = accountRepository.getAll(session);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
        return accounts;
    }

    @Override
    public void switchDB(final boolean flag) {
        this.flag = flag;
    }
}
