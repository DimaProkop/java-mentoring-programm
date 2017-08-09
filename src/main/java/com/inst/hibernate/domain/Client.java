package com.inst.hibernate.domain;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Transient;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry.
 */
@Entity
@Table(name = "client")
public class Client extends PersistObject implements Serializable, Cloneable {

    private static final Logger logger = LogManager.getLogger(Client.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade = CascadeType.ALL)
    @Reference
    private List<Account> accounts;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account addAccount(Account account) {
        if (accounts == null) {
            accounts = new ArrayList<>();
        } else {
            if (account == null) {
                logger.info("Account is null");
            } else {
                try {
                    //not safe
                    account.setClient(this);
                    accounts.add(account);
                } catch (Exception e) {
                    logger.info(e.getMessage());
                }
            }
        }
        return account;
    }

    @Override
    public Client clone() {
        try {
            return (Client) super.clone();
        } catch (CloneNotSupportedException ex) {
            logger.info(ex.getMessage());
            logger.error(ex.getMessage(), ex);
            throw new InternalError();
        }
    }
}
