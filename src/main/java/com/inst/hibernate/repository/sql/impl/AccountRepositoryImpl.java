package com.inst.hibernate.repository.sql.impl;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.repository.sql.AccountRepository;

/**
 * Created by Dmitry.
 */
public class AccountRepositoryImpl extends AbstractRepositoryImpl<Account> implements AccountRepository {

    public AccountRepositoryImpl() {
        super(Account.class);
    }
}
