package com.inst.hibernate.util;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;
import com.inst.hibernate.initializer.Initializer;

import java.util.List;

import static com.inst.hibernate.Main.main;

/**
 * Created by Dmitry.
 */
public class MenuHelper {

    public static void showAll(Initializer initializer, String[] args) {
        List<Client> clients = initializer.getAllClient();
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
            System.out.println("Clients not exist!");
            main(args);
        }
    }

    public static void showAccounts(Initializer initializer, String[] args) {
        List<Account> accounts = initializer.getAllAccounts();
        if (accounts.size() != 0) {
            accounts.forEach(a -> System.out.println("ID: " + a.getId() + "| Money: " + a.getMoney()));
            System.out.println("-------------------------------");
        } else {
            System.out.println("Accounts not exist!");
            main(args);
        }
    }

    public static void showClients(Initializer initializer, String[] args) {
        List<Client> clients = initializer.getAllClient();
        if (clients.size() != 0) {
            clients.forEach(c -> System.out.println("ID: " + c.getId() + "| Name: " + c.getName()));
        } else {
            System.out.println("Clients not exist!");
            main(args);
        }
    }
}
