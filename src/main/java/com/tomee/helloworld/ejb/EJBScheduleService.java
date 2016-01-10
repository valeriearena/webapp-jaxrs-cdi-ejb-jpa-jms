package com.tomee.helloworld.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class EJBScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(EJBScheduleService.class);

    @Schedule(second="*/30", minute = "*", hour = "*")
    public void doWork(){
        logger.info("----------------HELLO from EJBScheduleService!!!----------------");
    }

    @PostConstruct
    public void init(){
        logger.info("----------------INITIALIZING EJBScheduleService!!!----------------");
    }
}