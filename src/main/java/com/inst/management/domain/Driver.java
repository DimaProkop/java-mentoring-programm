package com.inst.management.domain;

/**
 * Created by Dmitry.
 */
public class Driver {

    private String name;
    private int age;
    private Car car;

    public Driver() {
    }

    public Driver(String name, int age) {
        super();
        this.name = name;
        this.age = age;
        this.car = new Car("audi", 2017);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
