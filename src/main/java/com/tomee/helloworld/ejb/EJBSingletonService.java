package com.tomee.helloworld.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;


/**
 * Created by valerie on 1/9/16.
 */
@Singleton
public class EJBSingletonService {

    private static final Logger logger = LoggerFactory.getLogger(EJBSingletonService.class);
    private String greeting = "SINGLETON HELLO";

    public void setGreeting(String greeting){

        logger.info("----------------SETTING EJBSingletonService [{}]!!!----------------", this.greeting);
        this.greeting = greeting;
    }

    public String getGreeting() {
        logger.info("----------------GETTING EJBSingletonService [{}]!!!----------------", this.greeting);
        return greeting;
    }
}
