package com.inst.multithreading.domain;

/**
 * Created by Dmitry.
 */
public class SharedResource {

    private volatile int counter;

    public SharedResource() {
    }

    public synchronized void give(int count) {
        setCounter(getCounter() + count);
    }

    public void initialize() {
        setCounter(10);
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
