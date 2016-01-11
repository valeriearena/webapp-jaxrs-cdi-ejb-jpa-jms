package com.tomee.helloworld.ejb;

import com.tomee.helloworld.jaxb.JAXBGreeting;
import com.tomee.helloworld.jms.JMSProducer;
import com.tomee.helloworld.jpa.entity.JPAGreetingEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by valerie on 1/11/16.
 */
@Stateless
public class EJBQueueService {

    private static final Logger logger = LoggerFactory.getLogger(EJBQueueService.class);

    @Inject
    private JMSProducer jmsProducer;

    public void send(JAXBGreeting jaxbGreeting){

        JPAGreetingEntity jpaGreetingEntity = new JPAGreetingEntity();
        jpaGreetingEntity.setExpression(jaxbGreeting.getExpression());

        jmsProducer.send(jpaGreetingEntity);

    }
}
