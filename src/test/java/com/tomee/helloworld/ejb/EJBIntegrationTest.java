package com.tomee.helloworld.ejb;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by valerie on 1/17/16.
 */
public class EJBIntegrationTest {

    private static EmbeddedContainerTest embeddedContainerTest = new EmbeddedContainerTest();

    @Before
    public void setup() throws Exception{
        embeddedContainerTest.setUp();

    }

    @Test
    public void test() throws Exception {

    }
}
