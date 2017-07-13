package com.inst.classloader;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Home {

    final static Logger logger = Logger.getLogger(Home.class);

    public static final String ENTER_FILE_NAME = "File name:";
    public static final String MENU = "Select the menu item: \n1)Load class (&& method).\n2)I don't want to see it.";
    public static final String ERROR_LOAD_FILE = "Error load class.";


    public static void main(String[] args) throws ClassNotFoundException {
        while (true) {
            System.out.println(MENU);
            BufferedReader bufferRead = new BufferedReader(
                    new InputStreamReader(System.in));
            String answer = null;
            try {
                answer = bufferRead.readLine();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            MyClassLoader myClassLoader = new MyClassLoader();
            if (answer != null) {
                switch (answer) {
                    case "1": {
                        try {
                            System.out.println(ENTER_FILE_NAME);
                            Class classToLoad = myClassLoader.loadClass(bufferRead.readLine());
                            myClassLoader.invokeMethod(classToLoad);
                        } catch (Exception e) {
                            logger.error(ERROR_LOAD_FILE);
                        }
                    }
                    break;
                    case "2": {
                        System.exit(0);
                    }
                }
            }
        }
    }
}
