package com.inst.hibernate;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;
import com.inst.hibernate.initializer.Initializer;
import com.inst.hibernate.initializer.impl.InitializerImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Scanner;

import static com.inst.hibernate.util.MenuHelper.showAccounts;
import static com.inst.hibernate.util.MenuHelper.showAll;
import static com.inst.hibernate.util.MenuHelper.showClients;

/**
 * Menu without validation.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INCORRECT_MESSAGE = "Incorrect, try it.";
    private static final String SUCCESSFUL_MESSAGE = "Successful.";
    private static final String ACCOUNT_NOT_EXIST = "Account with such ID does not exist!";
    private static final String ENTER_ID = "Enter ID: ";
    private static final String ENTER_NAME = "Enter NAME: ";
    private static final String CLIENT_NOT_EXIST = "Client with such ID does not exist!";
    private static boolean flag;
    private static final String MENU = "Now used database >>>> ".concat(flag ? "Mongo" : "Postgres") + "\nSelect the item:\n" +
            "1. Show all clients and [their accounts]" +
            "\n CLIENT MENU: \n2. Add client (name) \n3. Delete client (ID) \n4. Add account to Client (ID) " +
            "\n5. Show all my accounts (ID)\n" +
            " ACCOUNT MENU: \n6. Add account (money) \n7. Delete account (ID) \n8. Edit account (ID) \n" +
            "****************************\n9. Switch DB\n";

    public static void main(String[] args) {

        Initializer initializer = new InitializerImpl();
        initializer.init();
        //build menu
        System.out.println(MENU);
        String str;
        switch (scanner.next()) {
            case "1":
                showAll(initializer, args);
                break;
            case "2":
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
            case "3":
                showClients(initializer, args);
                if (flag) {
                    System.out.println(ENTER_NAME);
                } else {
                    System.out.println(ENTER_ID);
                }
                str = scanner.next();
                Client dClient;
                if ("".equals(str)) {
                    System.out.println(INCORRECT_MESSAGE);
                } else {
                    if (flag) {
                        dClient = initializer.getClient(null, str);
                    } else {
                        dClient = initializer.getClient(Long.parseLong(str), null);
                    }
                    if (dClient != null) {
                        initializer.deleteClient(dClient);
                        System.out.println(SUCCESSFUL_MESSAGE.concat(" The client removed"));
                    } else {
                        System.out.println(CLIENT_NOT_EXIST);
                    }
                }
                break;
            case "4":
                showClients(initializer, args);
                if (flag) {
                    System.out.println("Enter NAME client: ");
                } else {
                    System.out.println("Enter ID client: ");
                }
                Client fClient;
                str = scanner.next();
                if ("".equals(str)) {
                    System.out.println(INCORRECT_MESSAGE);
                } else {
                    if (flag) {
                        fClient = initializer.getClient(null, str);
                    } else {
                        fClient = initializer.getClient(Long.parseLong(str), null);
                    }
                    if (fClient != null) {
                        showAccounts(initializer, args);
                        System.out.println("Enter ID your account: ");
                        Account fAccount;
                        str = scanner.next();
                        if ("".equals(str)) {
                            System.out.println(INCORRECT_MESSAGE);
                        } else {
                            fAccount = initializer.getAccount(Long.parseLong(str), null);
                            if (fAccount != null) {
                                initializer.addAccountToClient(fAccount, fClient);
                                System.out.println(SUCCESSFUL_MESSAGE);
                            } else {
                                logger.info(ACCOUNT_NOT_EXIST);
                                System.out.println(ACCOUNT_NOT_EXIST);
                            }
                        }
                    } else {
                        logger.info(CLIENT_NOT_EXIST);
                        System.out.println(CLIENT_NOT_EXIST);
                    }
                }
                break;
            case "5":
                showClients(initializer, args);
                System.out.println("Enter your ID: ");
                str = scanner.next();
                Client iClient;
                if ("".equals(str)) {
                    System.out.println(INCORRECT_MESSAGE);
                } else {
                    iClient = initializer.getClient(Long.parseLong(str), null);
                    if (iClient != null) {
                        iClient.getAccounts().forEach(a -> System.out.println("ID: " + a.getId() + "| Money: " + a.getMoney()));
                    } else {
                        System.out.println(CLIENT_NOT_EXIST);
                    }
                }
                break;
            case "6":
                System.out.println("Give me your money :D (enter pls) - ");
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
            case "7":
                showAccounts(initializer, args);
                if (flag) {
                    System.out.println(ENTER_NAME);
                } else {
                    System.out.println(ENTER_ID);
                }
                str = scanner.next();
                Account dAccount;
                if ("".equals(str)) {
                    System.out.println(INCORRECT_MESSAGE);
                } else {
                    if (flag) {
                        dAccount = initializer.getAccount(null, str);
                    } else {
                        dAccount = initializer.getAccount(Long.parseLong(str), null);
                    }
                    if (dAccount != null) {
                        initializer.deleteAccount(dAccount);
                        System.out.println(SUCCESSFUL_MESSAGE.concat(" The account removed"));
                    } else {
                        System.out.println(ACCOUNT_NOT_EXIST);
                    }
                }
                break;
            case "8":
                showAccounts(initializer, args);
                System.out.println(ENTER_ID);
                Account editAccount;
                str = scanner.next();
                if ("".equals(str)) {
                    System.out.println(INCORRECT_MESSAGE);
                } else {
                    editAccount = initializer.getAccount(Long.parseLong(str), null);
                    System.out.println("Would you like edit your current count? y/n");
                    str = scanner.next();
                    if ("y".equals(str)) {
                        System.out.println("Enter new count your money -> ");
                        str = scanner.next();
                        editAccount.setMoney(Integer.parseInt(str));
                        initializer.updateAccount(new Account(), editAccount);
                        System.out.println(SUCCESSFUL_MESSAGE);
                    } else {
                        main(args);
                    }
                }
                break;
            case "9":
                flag = !flag;
                initializer.switchDB(flag);
                break;
            default:
                System.exit(0);
        }

        System.out.println("\n ==========================================");
        main(args);
    }
}
