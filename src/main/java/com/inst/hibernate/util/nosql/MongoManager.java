package com.inst.hibernate.util.nosql;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * Created by Dmitry.
 */
public class MongoManager {

    private static MongoManager manager;
    private static MongoClient mongoClient;
    private static DB db;

    private static final String LOCALHOST = "localhost";
    private static final int PORT = 27017;
    private static final String DB_NAME = "newDB";

    public static MongoManager getInstance() {
        if (manager == null) {
            manager = new MongoManager();
        }
        return manager;
    }

    public MongoClient getMongoClient() {
        if (mongoClient == null) {
            mongoClient = new MongoClient(LOCALHOST, PORT);
        }
        return mongoClient;
    }

    public DB getDB(String databaseName) {
        if(db == null) {
            db = getMongoClient().getDB(databaseName == null ? DB_NAME : databaseName);
            if(db.getCollection(Account.class.getSimpleName()) == null) {
                db.createCollection(Account.class.getSimpleName(), new BasicDBObject());
            }else if(db.getCollection(Client.class.getSimpleName()) == null) {
                db.createCollection(Client.class.getSimpleName(), new BasicDBObject());
            }
        }
        return db;
    }
}
