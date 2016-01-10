package com.tomee.helloworld.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    @Schedule(second="*/30", minute = "*", hour = "*")
    public void doWork(){
        logger.info("----------------HELLO from ScheduleService!!!----------------");
    }

    @PostConstruct
    public void init(){
        logger.info("----------------INITIALIZING ScheduleService!!!----------------");
    }
}