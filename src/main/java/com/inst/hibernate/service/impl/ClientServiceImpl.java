package com.inst.hibernate.service.impl;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;
import com.inst.hibernate.repository.AccountRepository;
import com.inst.hibernate.repository.ClientRepository;
import com.inst.hibernate.service.ClientService;
import com.inst.hibernate.util.SessionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Dmitry.
 */
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    public ClientServiceImpl(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public void add(Client client) {
        Session session = SessionManager.getInstance().getSession();
        try {
            clientRepository.add(session, client);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    public void update(Client client) {
        Session session = SessionManager.getInstance().getSession();
        try {
            clientRepository.update(session, client);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    public void delete(Client client) {
        Session session = SessionManager.getInstance().getSession();
        try {
            clientRepository.delete(session, client);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    public Client get(Long id) {
        Client client = null;
        Session session = SessionManager.getInstance().getSession();
        try {
            client = clientRepository.getById(session, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
        return client;
    }

    public void addAccount(Account account, Client client) {
        Session session = SessionManager.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (account.getId() == null) {
                accountRepository.add(session, account);
                transaction.commit();
            } else {
                client.addAccount(account);
            }
            session = SessionManager.getInstance().getSession();
            clientRepository.update(session, client);
            transaction.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    public List<Client> getAll() {
        List<Client> clients = null;
        Session session = SessionManager.getInstance().getSession();
        try {
            clients = clientRepository.getAll(session);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
        return clients;
    }

    public List<Account> getAccountsByIdClient(Long id) {
        List<Account> accounts = null;
        Session session = SessionManager.getInstance().getSession();
        try {
            accounts = clientRepository.getById(session, id).getAccounts();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
        return accounts;
    }
}
