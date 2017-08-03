package com.inst.hibernate.initializer;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;

import java.util.List;

/**
 * Created by Dmitry.
 */
public interface Initializer {

    void switchDB(final boolean flag);

    void init();

    //accounts
    void addAccount(final Account account);

    void deleteAccount(final Account account);

    void updateAccount(final Account oldAccount,final Account account);

    Account getAccount(final Long id,final String name);

    List<Account> getAllAccounts();

    void addClient(final Client client);

    void updateClient(final Client oldClient,final Client client);

    void deleteClient(final Client client);

    void addAccountToClient(final Account account,final Client client);

    Client getClient(final Long id,final String name);

    List<Client> getAllClient();

    List<Account> getAccountsByIdClient(final Long id);
}
