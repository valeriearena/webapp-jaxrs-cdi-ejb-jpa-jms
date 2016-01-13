package com.tomee.helloworld.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Startup
public class EJBScheduleService {

    private static final Logger logger = Logger.getLogger(EJBScheduleService.class.getName());

    @Schedule(second="*/30", minute = "*", hour = "*")
    public void doWork(){
        logger.log(Level.FINE,"----------------HELLO from EJBScheduleService!!!----------------");
    }

    @PostConstruct
    public void init(){
        logger.log(Level.FINE,"*******************INITIALIZING EJBScheduleService!!!*******************");
    }
}