package com.inst.hibernate.domain;

import com.inst.hibernate.repository.impl.AbstractRepositoryImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry.
 */
@Table
@Entity(name = "client")
public class Client extends PersistObject implements Serializable {

    private static final Logger logger = LogManager.getLogger(Client.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
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
        return accounts;
    }

    public void setAccount(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Account> addAccount(Account account) {
        if (accounts == null) {
            accounts = new ArrayList<Account>();
        } else {
            if (account == null) {
                logger.info("Account is null");
            } else {
                try {
                    accounts.add(account);
                } catch (Exception e) {
                    logger.info(e.getMessage());
                }
            }
        }
        return accounts;
    }
}
