package com.inst.hibernate.repository.sql.impl;

import com.inst.hibernate.domain.Client;
import com.inst.hibernate.repository.sql.ClientRepository;

/**
 * Created by Dmitry.
 */
public class ClientRepositoryImpl extends AbstractRepositoryImpl<Client> implements ClientRepository {

    public ClientRepositoryImpl() {
        super(Client.class);
    }
}
