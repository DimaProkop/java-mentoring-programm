package com.inst.hibernate.initializer.impl;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;
import com.inst.hibernate.initializer.Initializer;
import com.inst.hibernate.repository.AccountRepository;
import com.inst.hibernate.repository.ClientRepository;
import com.inst.hibernate.repository.impl.AccountRepositoryImpl;
import com.inst.hibernate.repository.impl.ClientRepositoryImpl;
import com.inst.hibernate.service.AccountService;
import com.inst.hibernate.service.ClientService;
import com.inst.hibernate.service.impl.AccountServiceImpl;
import com.inst.hibernate.service.impl.ClientServiceImpl;

import java.util.List;

/**
 * Init all services
 */
public class InitializerImpl implements Initializer{

    private AccountService accountService;
    private ClientService clientService;

    public void init() {
        AccountRepository accountRepository = new AccountRepositoryImpl();
        accountService = new AccountServiceImpl(accountRepository);
        ClientRepository clientRepository = new ClientRepositoryImpl();
        clientService = new ClientServiceImpl(clientRepository, accountRepository);
    }

    public void addAccount(Account account) {
        accountService.add(account);
    }

    public void deleteAccount(Account account) {
        accountService.delete(account);
    }

    public void updateAccount(Account account) {
        accountService.update(account);
    }

    public Account getAccount(Long id) {
        return accountService.get(id);
    }

    public List<Account> getAllAccounts() {
        return accountService.getAll();
    }

    public void addClient(Client client) {
        clientService.add(client);
    }

    public void updateClient(Client client) {
        clientService.update(client);
    }

    public void deleteClient(Client client) {
        clientService.delete(client);
    }

    @Override
    public void addAccountToClient(Account account, Client client) {
        clientService.addAccount(account, client);
    }

    public Client getClient(Long id) {
        return clientService.get(id);
    }

    public List<Client> getAllClient() {
        return clientService.getAll();
    }

    public List<Account> getAccountsByIdClient(Long id) {
        return clientService.getAccountsByIdClient(id);
    }
}
