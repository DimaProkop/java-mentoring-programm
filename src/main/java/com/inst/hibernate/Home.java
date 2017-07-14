package com.inst.hibernate;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;
import com.inst.hibernate.initializer.Initializer;
import com.inst.hibernate.initializer.impl.InitializerImpl;
import com.inst.hibernate.util.SessionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.Scanner;

/**
 * Created by Dmitry.
 */
public class Home {

    private static final Logger logger = LogManager.getLogger(Home.class);

    private static Scanner scanner = new Scanner(System.in);
    private static String INCORRECT_MESSAGE = "Incorrect, try it.";
    private static String SUCCESSFUL_MESSAGE = "Successful.";


    public static void main(String[] args) {
        Initializer initializer = new InitializerImpl();
        initializer.init();
        System.out.println("Select the item: \n1. Add client \n2. Get client name \n3. Add account\n4. Add account to client\n5. Exit");
        String str = "";
        switch (scanner.next()) {
            case "1":
                System.out.println("Enter your name: ");
                Client newClient = new Client();
                str = scanner.next();
                if ("".equals(str)) {
                    System.out.println(INCORRECT_MESSAGE);
                } else {
                    newClient.setName(str);
                    initializer.addClient(newClient);
                    System.out.println(SUCCESSFUL_MESSAGE);
                }
                break;
            case "2":
                System.out.println("Enter ID: ");
                Client client = null;
                str = scanner.next();
                if ("".equals(str)) {
                    System.out.println(INCORRECT_MESSAGE);
                } else {
                    client = initializer.getClient(Long.parseLong(str));
                    if (client != null) {
                        initializer.addAccountToClient(new Account(250), client);
                        System.out.println("NAME ---> " + client.getName());
                    } else {
                        logger.info("Client with such ID does not exist!");
                        System.out.println("Client with such ID does not exist!");
                    }
                }
                break;
            case "3":
                System.out.println("give me your money :D  ----> ");
                Account newAccount = new Account();
                str = scanner.next();
                if ("".equals(str)) {
                    System.out.println(INCORRECT_MESSAGE);
                } else {
                    newAccount.setMoney(Integer.parseInt(str));
                    initializer.addAccount(newAccount);
                    System.out.println(SUCCESSFUL_MESSAGE);
                }
                break;
            case "4":
                System.out.println("Enter ID client: ");
                Client fClient = null;
                str = scanner.next();
                if ("".equals(str)) {
                    System.out.println(INCORRECT_MESSAGE);
                } else {
                    fClient = initializer.getClient(Long.parseLong(str));
                    if (fClient != null) {
                        System.out.println("Enter ID your account: ");
                        Account fAccount = null;
                        str = scanner.next();
                        if ("".equals(str)) {
                            System.out.println(INCORRECT_MESSAGE);
                        } else {
                            fAccount = initializer.getAccount(Long.parseLong(str));
                            if(fAccount != null) {
                                initializer.addAccountToClient(fAccount, fClient);
                            }else {
                                logger.info("Account with such ID does not exist!");
                                System.out.println("Account with such ID does not exist!");
                            }
                        }
                    } else {
                        logger.info("Client with such ID does not exist!");
                        System.out.println("Client with such ID does not exist!");
                    }
                }
                break;
            case "5":
                System.exit(0);
            default:
                System.exit(0);
        }

        main(args);
    }
}
