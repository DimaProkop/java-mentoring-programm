package com.inst.hibernate.util.nosql;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry.
 */
public class WrapperService {

    public static Account accountFromMongoObject(BasicDBObject object) {
        Account account = new Account();
        ObjectId id = (ObjectId) object.get("_id");
        account.setId(ByteBuffer.wrap(id.toByteArray()).getLong());
        account.setMoney((Integer) object.get("money"));
        Client client = new Client();
        client.setName((String) object.get("client"));
        account.setClient(client);
        return account;
    }

    public static Client clientFromMongoObject(BasicDBObject object) {
        Client client = new Client();
        ObjectId id = (ObjectId) object.get("_id");
        client.setId(ByteBuffer.wrap(id.toByteArray()).getLong());
        client.setName((String) object.get("name"));
        client.setAccounts(getAccountsFromMongoObjects((List<BasicDBObject>) object.get("accounts")));
        return client;
    }

    public static BasicDBObject accountToMongoObject(Account entity) {
        BasicDBObject document = new BasicDBObject();
        document.put("money", entity.getMoney());
        document.put("client", entity.getClient().getName());
        return document;
    }

    public static BasicDBObject clientToMongoObject(Client entity) {
        BasicDBObject document = new BasicDBObject();
        document.put("name", entity.getName());
        List<BasicDBObject> accounts = fillListForMongoObject(entity.getAccounts());
        document.put("accounts", accounts);
        return document;
    }

    private static List<BasicDBObject> fillListForMongoObject(List<Account> accounts) {
        List<BasicDBObject> objects = new ArrayList<>();
        accounts.forEach(account -> objects.add(accountToMongoObject(account)));
        return objects;
    }

    public static List<Account> getAccountsFromMongoObjects(List<BasicDBObject> objects) {
        List<Account> accounts = new ArrayList<>();
        objects.forEach(obj -> accounts.add(accountFromMongoObject(obj)));
        return accounts;
    }

    public static List<Client> getClientsFromMongoObjects(List<BasicDBObject> objects) {
        List<Client> clients = new ArrayList<>();
        objects.forEach(obj -> clients.add(clientFromMongoObject(obj)));
        return clients;
    }
}
