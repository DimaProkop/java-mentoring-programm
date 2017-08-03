package com.inst.hibernate.service;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;

import java.util.List;

/**
 * Created by Dmitry.
 */
public interface ClientService {

    void switchDB(final boolean flag);

    void add(final Client client);

    void update(final Client oldClient, final Client client);

    void delete(final Client client);

    Client get(final Long id, String name);

    void addAccount(final Account account,final Client client);

    List<Client> getAll();

    List<Account> getAccountsByIdClient(final Long id);
}
