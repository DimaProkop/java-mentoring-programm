package com.inst.hibernate.repository.nosql;

import com.mongodb.DBCollection;

/**
 * Created by Dmitry.
 */
public class AccountMongoRepositoryImpl extends AbstractMongoRepositoryImpl {

    public AccountMongoRepositoryImpl(DBCollection collection) {
        super(collection);
    }
}
