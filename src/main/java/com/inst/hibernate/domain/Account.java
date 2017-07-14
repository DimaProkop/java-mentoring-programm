package com.inst.hibernate.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dmitry.
 */
@Entity
@Table(name = "account")
public class Account extends PersistObject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int money;

    public Account() {
    }

    public Account(int money) {
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
