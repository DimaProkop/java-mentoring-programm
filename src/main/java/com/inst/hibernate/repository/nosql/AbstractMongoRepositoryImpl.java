package com.inst.hibernate.repository.nosql;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.PersistObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry.
 */
public class AbstractMongoRepositoryImpl<T extends PersistObject> {

    private DBCollection collection;

    public AbstractMongoRepositoryImpl(final DBCollection collection) {
        this.collection = collection;
    }

    public void update(final BasicDBObject query,final BasicDBObject entity) {
        collection.update(query, entity);
    }

    public BasicDBObject getByName(final String name) {
        BasicDBObject query = new BasicDBObject();
        if(Account.class.getSimpleName().equals(collection.getName())) {
            query.put("client", name);
        }else {
            query.put("name", name);
        }
        DBCursor cursor = collection.find(query);
        return cursor.hasNext() ? (BasicDBObject) cursor.next() : null;
    }

    public void add(final BasicDBObject entity) {
        collection.insert(entity);
    }

    public List<BasicDBObject> getAll() {
        List<BasicDBObject> objects = new ArrayList<>();
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            objects.add((BasicDBObject) cursor.next());
        }
        return objects;
    }

    public void delete(final BasicDBObject entity) {
        collection.remove(entity);
    }
}
