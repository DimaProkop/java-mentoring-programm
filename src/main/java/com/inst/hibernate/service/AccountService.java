package com.inst.hibernate.service;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;

import java.util.List;

/**
 * Created by Dmitry.
 */
public interface AccountService {

    void add(Account account);

    void update(Account account);

    void delete(Account account);

    Account get(Long id);

    List<Account> getAll();
}
