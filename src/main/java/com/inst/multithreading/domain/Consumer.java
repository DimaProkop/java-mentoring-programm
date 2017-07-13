package com.inst.multithreading.domain;

/**
 * Created by Dmitry.
 */
public class Consumer implements Runnable{

    public void run() {
        System.out.println("Thread [" + this.getClass().getSimpleName() + "] greetings you!");
    }
}
