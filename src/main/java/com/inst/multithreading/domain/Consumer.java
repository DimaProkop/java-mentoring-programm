package com.inst.multithreading.domain;

import com.inst.multithreading.Home;
import com.inst.multithreading.service.Executor;
import org.apache.log4j.Logger;

/**
 * Created by Dmitry.
 */
public class Consumer implements Runnable, Executor{

    private static Logger logger = Logger.getLogger(Consumer.class);

    public void run() {
        logger.debug("Thread [" + this.getClass().getSimpleName() + "] start.");
    }

    public int consume() {
        logger.debug("Consume on -5 point..");
        return -5;
    }
}
