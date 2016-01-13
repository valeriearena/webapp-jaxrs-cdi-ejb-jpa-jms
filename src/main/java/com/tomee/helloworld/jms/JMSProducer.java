package com.tomee.helloworld.jms;

import com.tomee.helloworld.jpa.entity.JPASalutationEntity;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by valerie on 1/11/16.
 */
@Stateless
public class JMSProducer {

    private static final Logger logger = Logger.getLogger(JMSProducer.class.getName());

    @Resource(name = "helloworldQueue")
    private Queue destination;

    @Resource
    private ConnectionFactory connectionFactory;

    public boolean send(JPASalutationEntity jpaSalutationEntity){

        try{
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);

            ObjectMessage message = session.createObjectMessage();
            message.setObject(jpaSalutationEntity);

            producer.send(destination, message);

            session.close();
            connection.close();

            logger.log(Level.FINE,"REPLICATION - PRODUCED JMS MESSAGE: [{0}]", jpaSalutationEntity);

            return true;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE,"REPLICATION - ERROR SENDING JMS MESSAGE {0}.", e);
            e.printStackTrace();
            return false;
        }

    }

}
