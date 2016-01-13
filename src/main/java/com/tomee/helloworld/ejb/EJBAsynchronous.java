package com.tomee.helloworld.ejb;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class EJBAsynchronous {

    private static final Logger logger = Logger.getLogger(EJBAsynchronous.class.getName());

    @Asynchronous
    public void loop(){

        for(int i = 0; i < 10000; i++){

            if(i%1000 == 0){
                logger.log(Level.FINE,"*******************ASYNCHRONOUS TASK RUNNING: cnt [{0}]*******************", i);
                try{Thread.sleep(5000);}catch(Exception e){}
            }
        }
    }


}
