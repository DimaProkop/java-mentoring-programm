package com.inst.multithreading;

import com.inst.multithreading.domain.Consumer;
import com.inst.multithreading.domain.Producer;
import com.inst.multithreading.domain.SharedResource;
import org.apache.log4j.Logger;


/**
 * Created by Dmitry.
 */
public class Home {

    private static Logger logger = Logger.getLogger(Home.class);

    public static void fun(String[] args) throws InterruptedException {
        Consumer consumer = new Consumer();
        Thread thread = new Thread(consumer);
        thread.start();
        Thread.sleep(1500);

        Producer producer = new Producer();
        Thread thread1 = new Thread(producer);
        thread1.start();
        String[] arr = new String[1];

        if (args.length != 0 && !"".equals(args[0])) {
            int i = Integer.parseInt(args[0]);
            ++i;
            arr[0] = String.valueOf(i);
        } else {
            arr[0] = "1";
        }
        System.out.println("This is attempt №" + arr[0]);
        Home.main(arr);
    }

    public static void main(String[] args) throws InterruptedException {
        Consumer consumer = new Consumer();
        Thread thread = new Thread(consumer);
        thread.start();

        Producer producer = new Producer();
        Thread thread1 = new Thread(producer);
        thread1.start();

        SharedResource resource = new SharedResource();
        resource.initialize();

        for (;;) {

            resource.give(consumer.consume());
            Thread.sleep(2000);
            logger.debug("COUNTER -> " + resource.getCounter());


            resource.give(producer.consume());
            logger.debug("COUNTER -> " + resource.getCounter());

        }

    }
}
