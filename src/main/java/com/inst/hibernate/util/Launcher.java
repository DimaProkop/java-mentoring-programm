package com.inst.hibernate.util;

import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;
import com.inst.hibernate.initializer.Initializer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Scanner;

import static com.inst.hibernate.util.MenuHelper.showAccounts;
import static com.inst.hibernate.util.MenuHelper.showAll;
import static com.inst.hibernate.util.MenuHelper.showClients;

/**
 * Launcher {Menu without validation}
 */
public class Launcher {

    private static final Logger logger = LogManager.getLogger(Launcher.class);

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INCORRECT_MESSAGE = "Incorrect, try it.";
    private static final String SUCCESSFUL_MESSAGE = "Successful.";
    private static final String ACCOUNT_NOT_EXIST = "Account with such ID does not exist!";
    private static final String ENTER_ID = "Enter ID: ";
    private static final String ENTER_NAME = "Enter NAME: ";
    private static final String CLIENT_NOT_EXIST = "Client with such ID does not exist!";
    private static boolean flag;
    private static final String MENU = "\nSelect the item:\n" +
            "1. Show all clients and [their accounts]" +
            "\n CLIENT MENU: \n2. Add client (name) \n3. Delete client (ID) \n4. Add account to Client (ID) " +
            "\n5. Show all my accounts (ID)\n" +
            " ACCOUNT MENU: \n6. Add account (money) \n7. Delete account (ID) \n8. Edit account (ID) \n" +
            "****************************\n9. Switch DB\n";
    private static boolean exit = true;
    private Initializer initializer;

    public Launcher(Initializer initializer) {
        this.initializer = initializer;
    }

    public void start() {
        while (exit) {
            callMenu();
        }
    }

    private void callMenu() {
        System.out.println("Now used database >>>> " + (flag ? "Mongo" : "Postgres"));
        System.out.println(MENU);
        String str;
        switch (scanner.next()) {
            case "1":
                if (showAll(initializer)) {
                    callMenu();
                }
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
                if (showClients(initializer)) {
                    callMenu();
                }
                System.out.println(flag ? ENTER_NAME : ENTER_ID);
                str = scanner.next();
                Client dClient;
                if ("".equals(str)) {
                    System.out.println(INCORRECT_MESSAGE);
                } else {
                    dClient = flag ? initializer.getClient(null, str)
                            : initializer.getClient(Long.parseLong(str), null);
                    if (dClient != null) {
                        initializer.deleteClient(dClient);
                        System.out.println(SUCCESSFUL_MESSAGE.concat(" The client removed"));
                    } else {
                        System.out.println(CLIENT_NOT_EXIST);
                    }
                }
                break;
            case "4":
                if (showClients(initializer)) {
                    callMenu();
                }
                System.out.println(flag ? "Enter NAME client: " : "Enter ID client: ");
                Client fClient;
                str = scanner.next();
                if ("".equals(str)) {
                    System.out.println(INCORRECT_MESSAGE);
                } else {
                    fClient = flag ? initializer.getClient(null, str)
                            : initializer.getClient(Long.parseLong(str), null);
                    if (fClient != null) {
                        if (showAccounts(initializer)) {
                            callMenu();
                        }
                        System.out.println(flag ? "Enter money (bad solution)" : "Enter ID your account: ");
                        Account fAccount;
                        str = scanner.next();
                        if ("".equals(str)) {
                            System.out.println(INCORRECT_MESSAGE);
                        } else {
                            fAccount = flag ? initializer.getAccount(null, str)
                                    : initializer.getAccount(Long.parseLong(str), null);
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
                if (showClients(initializer)) {
                    callMenu();
                }
                System.out.print(flag ? ENTER_NAME : ENTER_ID);
                str = scanner.next();
                Client iClient;
                if ("".equals(str)) {
                    System.out.println(INCORRECT_MESSAGE);
                } else {
                    iClient = flag ? initializer.getClient(null, str) :
                            initializer.getClient(Long.parseLong(str), null);
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
                if (showAccounts(initializer)) {
                    callMenu();
                }
                System.out.println(flag ? ENTER_NAME : ENTER_ID);
                str = scanner.next();
                Account dAccount;
                if ("".equals(str)) {
                    System.out.println(INCORRECT_MESSAGE);
                } else {
                    dAccount = flag ? initializer.getAccount(null, str)
                            : initializer.getAccount(Long.parseLong(str), null);
                    if (dAccount != null) {
                        initializer.deleteAccount(dAccount);
                        System.out.println(SUCCESSFUL_MESSAGE.concat(" The account removed"));
                    } else {
                        System.out.println(ACCOUNT_NOT_EXIST);
                    }
                }
                break;
            case "8":
                if (showAccounts(initializer)) {
                    callMenu();
                }
                System.out.println(flag ? "Enter MONEY " : ENTER_ID);
                Account editAccount;
                str = scanner.next();
                if ("".equals(str)) {
                    System.out.println(INCORRECT_MESSAGE);
                } else {
                    editAccount = flag ? initializer.getAccount(null, str)
                            : initializer.getAccount(Long.parseLong(str), null);
                    System.out.println("Would you like edit your current count? y/n");
                    str = scanner.next();
                    if ("y".equals(str)) {
                        System.out.println("Enter new count your money -> ");
                        str = scanner.next();
                        Account oldAccount = editAccount.clone();
                        editAccount.setMoney(Integer.parseInt(str));
                        initializer.updateAccount(oldAccount, editAccount);
                        System.out.println(SUCCESSFUL_MESSAGE);
                    } else {
                        callMenu();
                    }
                }
                break;
            case "9":
                flag = !flag;
                initializer.switchDB(flag);
                break;
            default:
                exit = false;
        }

        System.out.println("\n ==========================================");
    }
}
