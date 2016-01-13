package com.tomee.helloworld.ejb;

import com.tomee.helloworld.jaxb.JAXBHelloWorld;
import com.tomee.helloworld.jms.JMSProducer;
import com.tomee.helloworld.jpa.entity.JPASalutationEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * Created by valerie on 1/11/16.
 */
@Stateless
public class EJBQueueService {

    private static final Logger logger = Logger.getLogger(EJBQueueService.class.getName());

    @Inject
    private JMSProducer jmsProducer;

    public void send(JAXBHelloWorld jaxbHelloWorld){

        JPASalutationEntity jpaSalutationEntity = new JPASalutationEntity();
        jpaSalutationEntity.setSalutation(jaxbHelloWorld.getSalutation());

        jmsProducer.send(jpaSalutationEntity);

    }
}
