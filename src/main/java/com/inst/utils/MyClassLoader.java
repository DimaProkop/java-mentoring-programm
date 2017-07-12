package com.inst.utils;

/**
 * Created by Dmitry.
 */
public class MyClassLoader extends ClassLoader {

    public MyClassLoader(){
        super(MyClassLoader.class.getClassLoader());
    }
}
