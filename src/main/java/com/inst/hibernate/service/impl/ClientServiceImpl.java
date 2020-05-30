package com.inst.hibernate.service.impl;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;
import com.inst.hibernate.repository.nosql.AccountMongoRepositoryImpl;
import com.inst.hibernate.repository.nosql.ClientMongoRepositoryImpl;
import com.inst.hibernate.repository.sql.AccountRepository;
import com.inst.hibernate.repository.sql.ClientRepository;
import com.inst.hibernate.service.ClientService;
import com.inst.hibernate.util.SessionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.inst.hibernate.util.nosql.WrapperService.accountToMongoObject;
import static com.inst.hibernate.util.nosql.WrapperService.clientToMongoObject;
import static com.inst.hibernate.util.nosql.WrapperService.clientFromMongoObject;
import static com.inst.hibernate.util.nosql.WrapperService.getClientsFromMongoObjects;

/**
 * Created by Dmitry.
 */
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;
    private ClientMongoRepositoryImpl clientMongoRepository;
    private AccountMongoRepositoryImpl accountMongoRepository;
    private static boolean flag;

    public ClientServiceImpl(final ClientRepository clientRepository, final AccountRepository accountRepository,
                             final ClientMongoRepositoryImpl clientMongoRepository, final AccountMongoRepositoryImpl accountMongoRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.clientMongoRepository = clientMongoRepository;
        this.accountMongoRepository = accountMongoRepository;
    }

    public void add(final Client client) {
        Session session = SessionManager.getInstance().getSession();
        try {
            if (flag) {
                clientMongoRepository.add(clientToMongoObject(client));
            } else {
                clientRepository.add(session, client);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    public void update(final Client oldClient, final Client client) {
        Session session = SessionManager.getInstance().getSession();
        try {
            if (flag) {
                clientMongoRepository.update(clientToMongoObject(oldClient), clientToMongoObject(client));
            } else {
                clientRepository.update(session, client);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    public void delete(final Client client) {
        Session session = SessionManager.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (flag) {
                clientMongoRepository.delete(clientToMongoObject(client));
            } else {
                clientRepository.delete(session, client);
            }
            transaction.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    public Client get(final Long id, final String name) {
        Client client = null;
        Session session = SessionManager.getInstance().getSession();
        try {
            if (flag) {
                client = clientFromMongoObject(clientMongoRepository.getByName(name));
            } else {
                client = clientRepository.getById(session, id);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
        return client;
    }

    public void addAccount(final Account account, final Client client) {
        Session session = SessionManager.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Client clone = client.clone();
        try {
            if (account.getId() == null) {
                accountMongoRepository.add(accountToMongoObject(account));
                accountRepository.add(session, account);
                transaction.commit();
            } else {
                Account oldAccount = account.clone();
                Account newAccount = client.addAccount(account);
                accountMongoRepository.update(accountToMongoObject(oldAccount),
                        accountToMongoObject(newAccount));
                accountRepository.update(session, newAccount);
            }
            clientMongoRepository.update(clientToMongoObject(clone), clientToMongoObject(client));
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
            if (flag) {
                clients = getClientsFromMongoObjects(clientMongoRepository.getAll());
            } else {
                clients = clientRepository.getAll(session);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SessionManager.getInstance().closeSession(session);
        }
        return clients;
    }

    public List<Account> getAccountsByIdClient(final Long id) {
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

    @Override
    public void switchDB(boolean flag) {
        this.flag = flag;
    }
}
