package com.inst.management.domain;

/**
 * Created by Dmitry.
 */
public class Car {

    private String name;
    private int age;
    private Driver driver;

    public Car() {
        this.driver = new Driver();
    }

    public Car(String name, int age) {
        super();
        this.name = name;
        this.age = age;
        this.driver = new Driver("dimbI4", 22);
    }
}
