package com.inst.multithreading.domain;

/**
 * Created by Dmitry.
 */
public class Producer implements Runnable {

    public void run() {
        System.out.println("Thread [" + this.getClass().getSimpleName() + "] greetings you!");
    }
}
