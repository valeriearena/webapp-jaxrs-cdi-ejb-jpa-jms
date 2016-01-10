package com.tomee.helloworld.ejb;

import com.tomee.helloworld.jaxb.JAXBGreeting;
import com.tomee.helloworld.jpa.dao.JPAGreetingDAO;
import com.tomee.helloworld.jpa.entity.JPAGreetingEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by valerie on 1/10/16.
 */
@Stateless
public class EJBTransactionService {

    private static final Logger logger = LoggerFactory.getLogger(EJBTransactionService.class);

    @Inject
    JPAGreetingDAO jpaGreetingDAO;

    public void addGreeting(JAXBGreeting jaxbGreeting){

        JPAGreetingEntity jpaGreetingEntity = new JPAGreetingEntity();
        jpaGreetingEntity.setExpression(jaxbGreeting.getExpression());

        jpaGreetingDAO.persist(jpaGreetingEntity);
    }

}
