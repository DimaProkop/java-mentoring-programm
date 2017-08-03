package com.inst.hibernate.service;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;

import java.util.List;

/**
 * Created by Dmitry.
 */
public interface AccountService {

    void switchDB(final boolean flag);

    void add(final Account account);

    void update(Account oldAccount, Account account);

    void delete(final Account account);

    Account get(Long id, String name);

    List<Account> getAll();
}
