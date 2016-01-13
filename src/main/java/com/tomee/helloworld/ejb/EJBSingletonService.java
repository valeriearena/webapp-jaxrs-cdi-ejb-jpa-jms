package com.tomee.helloworld.ejb;

import javax.ejb.Singleton;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class EJBSingletonService {

    private static final Logger logger = Logger.getLogger(EJBSingletonService.class.getName());

    private String salutation = "SINGLETON HELLO";

    public void setSalutation(String salutation){

        logger.log(Level.FINE,"----------------SET EJBSingletonService [{0}]!!!----------------", this.salutation);
        this.salutation = salutation;
    }

    public String getSalutation() {
        logger.log(Level.FINE,"----------------GET EJBSingletonService [{0}]!!!----------------", this.salutation);
        return salutation;
    }
}
