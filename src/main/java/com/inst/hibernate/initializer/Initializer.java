package com.inst.hibernate.initializer;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;

import java.util.List;

/**
 * Created by Dmitry.
 */
public interface Initializer {

    void init();

    //accounts
    void addAccount(Account account);

    void deleteAccount(Account account);

    void updateAccount(Account account);

    Account getAccount(Long id);

    List<Account> getAllAccounts();

    void addClient(Client client);

    void updateClient(Client client);

    void deleteClient(Client client);

    void addAccountToClient(Account account, Client client);

    Client getClient(Long id);

    List<Client> getAllClient();

    List<Account> getAccountsByIdClient(Long id);
}
