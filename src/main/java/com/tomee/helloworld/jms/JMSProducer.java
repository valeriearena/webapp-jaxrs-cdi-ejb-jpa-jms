package com.tomee.helloworld.jms;

import com.tomee.helloworld.jpa.entity.JPAGreetingEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;



/**
 * Created by valerie on 1/11/16.
 */
@Stateless
public class JMSProducer {

    private static final Logger logger = LoggerFactory.getLogger(JMSProducer.class);

    @Resource(name = "helloworldQueue")
    private Queue destination;

    @Resource
    private ConnectionFactory connectionFactory;

    public boolean send(JPAGreetingEntity jpaGreetingEntity){

        try{
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);

            ObjectMessage message = session.createObjectMessage();
            message.setObject(jpaGreetingEntity);

            producer.send(destination, message);

            session.close();
            connection.close();

            logger.debug("REPLICATION - PRODUCED JMS MESSAGE: [{}]",jpaGreetingEntity);

            return true;
        }
        catch (Exception e) {
            logger.error("REPLICATION - ERROR SENDING JMS MESSAGE {}.", e);
            e.printStackTrace();
            return false;
        }

    }

}
