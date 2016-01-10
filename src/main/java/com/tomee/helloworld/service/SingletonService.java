package com.tomee.helloworld.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;


/**
 * Created by valerie on 1/9/16.
 */
@Singleton
public class SingletonService {

    private static final Logger logger = LoggerFactory.getLogger(SingletonService.class);
    private String greeting = "SINGLETON HELLO";

    public void setGreeting(String greeting){

        logger.info("----------------SETTING SingletonService [{}]!!!----------------", this.greeting);
        this.greeting = greeting;
    }

    public String getGreeting() {
        logger.info("----------------GETTING SingletonService [{}]!!!----------------", this.greeting);
        return greeting;
    }
}
