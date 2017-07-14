package com;

import com.inst.hibernate.domain.Client;
import com.inst.hibernate.initializer.Initializer;
import com.inst.hibernate.initializer.impl.InitializerImpl;
import junit.framework.TestCase;
import org.junit.runner.RunWith;

/**
 * Created by Dmitry.
 */
public class ClientTest extends TestCase {

    private Initializer initializer;

    @Override
    protected void runTest() throws Throwable {
        initializer = new InitializerImpl();
        initializer.init();
        super.runTest();
    }


    public void test() {
        initializer.addClient(new Client("Test"));
        System.out.print("Success.");
    }
}
