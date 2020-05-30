package com.inst.hibernate.repository.nosql;

import com.mongodb.DBCollection;

/**
 * Created by Dmitry.
 */
public class ClientMongoRepositoryImpl extends AbstractMongoRepositoryImpl {

    public ClientMongoRepositoryImpl(DBCollection collection) {
        super(collection);
    }
}
