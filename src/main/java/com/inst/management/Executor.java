package com.inst.management;

import com.inst.management.domain.Driver;

/**
 * Created by Dmitry.
 */
public class Executor {

    public static void main(String[] args) {
        String str = new String("smth");

        //Stack over flow
        Driver driver = new Driver("", 0);
        driver.toString();

        //Java heap space
        while (true) {
            str += str;
        }
    }
}
