package com.inst.hibernate.repository.impl;

import com.inst.hibernate.domain.Client;
import com.inst.hibernate.repository.ClientRepository;

/**
 * Created by Dmitry.
 */
public class ClientRepositoryImpl extends AbstractRepositoryImpl<Client> implements ClientRepository {

    public ClientRepositoryImpl() {
        super(Client.class);
    }
}
