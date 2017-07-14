package com.inst.multithreading.domain;

import com.inst.multithreading.service.Executor;
import org.apache.log4j.Logger;

/**
 * Created by Dmitry.
 */
public class Producer implements Runnable, Executor {

    private static Logger logger = Logger.getLogger(Producer.class);

    public void run() {
        logger.debug("Thread [" + this.getClass().getSimpleName() + "] start.");
    }

    public int consume() {
        logger.debug("Consume on  +10 point..");
        return +10;
    }
}
