package com.inst.hibernate.service;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;

import java.util.List;

/**
 * Created by Dmitry.
 */
public interface ClientService {

    void add(Client client);

    void update(Client client);

    void delete(Client client);

    Client get(Long id);

    void addAccount(Account account, Client client);

    List<Client> getAll();

    List<Account> getAccountsByIdClient(Long id);
}
