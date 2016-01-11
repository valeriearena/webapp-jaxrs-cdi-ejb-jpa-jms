package com.tomee.helloworld.jms;

import com.tomee.helloworld.jpa.entity.JPAGreetingEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by valerie on 1/11/16.
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "helloworldQueue") })
public class JMSConsumer implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(JMSConsumer.class);

    public void onMessage(Message message) {

        JPAGreetingEntity jpaGreetingEntity = null;
        try {
            ObjectMessage objectMessage = (ObjectMessage)message;
            jpaGreetingEntity = (JPAGreetingEntity)objectMessage.getObject();

            logger.debug("REPLICATION - CONSUMER JMS MESSAGE: [{}]",jpaGreetingEntity);
        }
        catch (Exception e) {
            logger.error("REPLICATION - ERROR SENDING JMS MESSAGE {}.", e);
            e.printStackTrace();
        }

    }

    }
