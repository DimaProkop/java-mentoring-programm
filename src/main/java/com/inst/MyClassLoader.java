package com.inst;

import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Dmitry.
 */
public class MyClassLoader {
    private static Logger LOGGER = Logger.getLogger(MyClassLoader.class);
    public static final String SYSTEM_PATH = "file://";
    public static final String CLASS_NAME = "folder.SuperClass";
    public static final String METHOD_WAS_LOADED = "method was successfully invoked.";
    public static final String CLASS_WAS_LOADED = "the test class has been loaded.";
    public static final String METHOD = "showMessage";

    public Class loadClass(String path) throws MalformedURLException, ClassNotFoundException {
        String fullPath = SYSTEM_PATH + path;
        URL[] urls = { new URL(fullPath) };
        URLClassLoader child = new URLClassLoader(urls,
                MyClassLoader.class.getClassLoader());
        LOGGER.debug(CLASS_WAS_LOADED);
        return Class.forName(CLASS_NAME, true, child);
    }

    public void invokeMethod(Class classToLoad) throws Exception {
        Method method = classToLoad.getDeclaredMethod(METHOD);
        Object instance = classToLoad.newInstance();
        method.invoke(instance);
        LOGGER.debug(METHOD_WAS_LOADED);
    }
}
