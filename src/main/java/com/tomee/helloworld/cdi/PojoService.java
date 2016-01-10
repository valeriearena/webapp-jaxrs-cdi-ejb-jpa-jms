package com.tomee.helloworld.cdi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by valerie on 1/10/16.
 */
public class PojoService {

    private static final Logger logger = LoggerFactory.getLogger(PojoService.class);
    private String greeting = "POJO HELLO";

    public void setGreeting(String greeting){

        logger.info("----------------SETTING PojoService [{}]!!!----------------",this.greeting);
        this.greeting = greeting;
    }

    public String getGreeting() {
        logger.info("----------------GETTING PojoService [{}]!!!----------------", this.greeting);
        return greeting;
    }
}
