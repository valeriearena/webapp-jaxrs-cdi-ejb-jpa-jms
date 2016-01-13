package com.tomee.helloworld.cdi;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by valerie on 1/10/16.
 */
public class PojoService {

    private static final Logger logger = Logger.getLogger(PojoService.class.getName());

    private String greeting = "POJO HELLO";

    public void setGreeting(String greeting){

        logger.log(Level.FINE, "----------------SETTING PojoService [{0}]!!!----------------", this.greeting);
        this.greeting = greeting;
    }

    public String getGreeting() {
        logger.log(Level.FINE, "----------------GETTING PojoService [{0}]!!!----------------", this.greeting);
        return greeting;
    }
}
