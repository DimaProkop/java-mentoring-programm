package com.inst.hibernate.util;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;
import com.inst.hibernate.initializer.Initializer;

import java.util.List;

/**
 * Created by Dmitry.
 */
public class MenuHelper {

    public static boolean showAll(Initializer initializer) {
        List<Client> clients = initializer.getAllClient();
        boolean answer = false;
        if (clients.size() != 0) {
            clients.forEach(c -> {
                System.out.println("ID: " + c.getId() + "| Name: " + c.getName());
                System.out.println("======= Accounts ========");
                if (c.getAccounts().size() != 0) {
                    c.getAccounts().forEach(a -> System.out.println("ID: " + a.getId() + "| Money: " + a.getMoney()));
                    System.out.println("-------------------------------");
                } else {
                    System.out.println("Accounts not exist!");
                }
            });
        } else {
            answer = true;
            System.out.println("Clients not exist!");
        }
        return answer;
    }

    public static boolean showAccounts(Initializer initializer) {
        List<Account> accounts = initializer.getAllAccounts();
        boolean answer = false;
        if (accounts.size() != 0) {
            accounts.forEach(a -> System.out.println("ID: " + a.getId() + "| Money: " + a.getMoney()));
            System.out.println("-------------------------------");
        } else {
            System.out.println("Accounts not exist!");
            answer = true;
        }
        return answer;
    }

    public static boolean showClients(Initializer initializer) {
        List<Client> clients = initializer.getAllClient();
        boolean answer = false;
        if (clients.size() != 0) {
            clients.forEach(c -> System.out.println("ID: " + c.getId() + "| Name: " + c.getName()));
        } else {
            System.out.println("Clients not exist!");
            answer = true;
        }
        return answer;
    }
}
