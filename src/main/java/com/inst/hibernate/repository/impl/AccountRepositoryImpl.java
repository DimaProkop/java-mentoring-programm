package com.inst.hibernate.repository.impl;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.repository.AbstractRepository;
import com.inst.hibernate.repository.AccountRepository;

/**
 * Created by Dmitry.
 */
public class AccountRepositoryImpl extends AbstractRepositoryImpl<Account> implements AccountRepository {

    public AccountRepositoryImpl() {
        super(Account.class);
    }
}
