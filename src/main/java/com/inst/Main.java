package com.inst;

import com.inst.classes.A;
import com.inst.classes.C;
import com.inst.utils.MyClassLoader;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws ClassNotFoundException {
        if(logger.isDebugEnabled()) {
            logger.debug("hey");
        }
    }
}
