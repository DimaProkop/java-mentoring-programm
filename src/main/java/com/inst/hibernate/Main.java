package com.inst.hibernate;

import com.inst.hibernate.initializer.Initializer;
import com.inst.hibernate.initializer.impl.InitializerImpl;
import com.inst.hibernate.util.Launcher;
import com.inst.hibernate.util.nosql.MongoManager;
import com.mongodb.DBCollection;
import org.bson.types.ObjectId;


/**
 * Main.
 */
public class Main {

    public static void main(String[] args) {

        Initializer initializer = new InitializerImpl();
        initializer.init();
        //build menu
        Launcher launcher = new Launcher(initializer);
        launcher.start();
    }
}
