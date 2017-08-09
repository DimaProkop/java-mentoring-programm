package com.inst.hibernate.initializer.impl;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;
import com.inst.hibernate.initializer.Initializer;
import com.inst.hibernate.repository.nosql.AccountMongoRepositoryImpl;
import com.inst.hibernate.repository.nosql.ClientMongoRepositoryImpl;
import com.inst.hibernate.repository.sql.AccountRepository;
import com.inst.hibernate.repository.sql.ClientRepository;
import com.inst.hibernate.repository.sql.impl.AccountRepositoryImpl;
import com.inst.hibernate.repository.sql.impl.ClientRepositoryImpl;
import com.inst.hibernate.service.AccountService;
import com.inst.hibernate.service.ClientService;
import com.inst.hibernate.service.impl.AccountServiceImpl;
import com.inst.hibernate.service.impl.ClientServiceImpl;
import com.inst.hibernate.util.nosql.MongoManager;
import com.mongodb.DBCollection;

import java.util.List;

/**
 * Init all services
 */
public class InitializerImpl implements Initializer {

    private AccountService accountService;
    private ClientService clientService;
    private boolean switchDatabase = false;

    @Override
    public void switchDB(final boolean flag) {
        this.switchDatabase = flag;
        accountService.switchDB(flag);
        clientService.switchDB(flag);
    }

    public void init() {
        MongoManager.getInstance().getMongoClient();
        final AccountRepository accountRepository = new AccountRepositoryImpl();
        final AccountMongoRepositoryImpl accountMongoRepository =
                new AccountMongoRepositoryImpl(getCollection(Account.class.getSimpleName()));
        accountService = new AccountServiceImpl(accountRepository, accountMongoRepository);
        final ClientRepository clientRepository = new ClientRepositoryImpl();
        final ClientMongoRepositoryImpl clientMongoRepository =
                new ClientMongoRepositoryImpl(getCollection(Client.class.getSimpleName()));
        clientService = new ClientServiceImpl(clientRepository, accountRepository,
                clientMongoRepository, accountMongoRepository);
    }

    private DBCollection getCollection(final String nameCollection) {
        return MongoManager.getInstance().getDB().getCollection(nameCollection);
    }

    public void addAccount(final Account account) {
        accountService.add(account);
    }

    public void deleteAccount(final Account account) {
        accountService.delete(account);
    }

    public void updateAccount(final Account oldAccount, final Account account) {
        accountService.update(oldAccount, account);
    }

    public Account getAccount(final Long id, final String name) {
        return accountService.get(id, name);
    }

    public List<Account> getAllAccounts() {
        return accountService.getAll();
    }

    public void addClient(final Client client) {
        clientService.add(client);
    }

    public void updateClient(final Client oldClient, final Client client) {
        clientService.update(oldClient, client);
    }

    public void deleteClient(final Client client) {
        clientService.delete(client);
    }

    @Override
    public void addAccountToClient(final Account account,final Client client) {
        clientService.addAccount(account, client);
    }

    public Client getClient(final Long id, final String name) {
        return clientService.get(id, name);
    }

    public List<Client> getAllClient() {
        return clientService.getAll();
    }

    public List<Account> getAccountsByIdClient(final Long id) {
        return clientService.getAccountsByIdClient(id);
    }

    public void turnSwitchDatabase(final boolean switchDatabase) {
        this.switchDatabase = switchDatabase;
    }
}
