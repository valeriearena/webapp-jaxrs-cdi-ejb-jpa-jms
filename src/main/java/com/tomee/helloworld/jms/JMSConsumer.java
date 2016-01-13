package com.tomee.helloworld.jms;

import com.tomee.helloworld.jpa.entity.JPASalutationEntity;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "helloworldQueue") })
public class JMSConsumer implements MessageListener {

    private static final Logger logger = Logger.getLogger(JMSConsumer.class.getName());

    public void onMessage(Message message) {

        JPASalutationEntity jpaSalutationEntity = null;
        try {
            ObjectMessage objectMessage = (ObjectMessage)message;
            jpaSalutationEntity = (JPASalutationEntity)objectMessage.getObject();

            logger.log(Level.FINE,"REPLICATION - CONSUMER JMS MESSAGE: [{0}]", jpaSalutationEntity);
        }
        catch (Exception e) {
            logger.log(Level.FINE,"REPLICATION - ERROR SENDING JMS MESSAGE {0}.", e);
            e.printStackTrace();
        }

    }

    }
