package com.tomee.helloworld.ejb;

import com.tomee.helloworld.jaxb.JAXBGreeting;
import com.tomee.helloworld.jpa.dao.JPAGreetingDAO;
import com.tomee.helloworld.jpa.dao.JPAUserDAO;
import com.tomee.helloworld.jpa.entity.JPAGreetingEntity;
import com.tomee.helloworld.jpa.entity.JPAUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.*;
import javax.inject.Inject;

/**
 * Created by valerie on 1/10/16.
 */
@Stateless
@TransactionManagement(value= TransactionManagementType.CONTAINER)
@TransactionAttribute(value= TransactionAttributeType.REQUIRED)
public class EJBTransactionService {

    private static final Logger logger = LoggerFactory.getLogger(EJBTransactionService.class);

    @Inject
    private JPAGreetingDAO jpaGreetingDAO;

    @Inject
    private JPAUserDAO jpaUserDAO;


    public void addGreeting(JAXBGreeting jaxbGreeting){

        logger.info("----------------PERSISTING GREETING [{}]!!!----------------", jaxbGreeting);

        JPAGreetingEntity jpaGreetingEntity = new JPAGreetingEntity();
        jpaGreetingEntity.setExpression(jaxbGreeting.getExpression());

        jpaGreetingDAO.persist(jpaGreetingEntity);

    }

    public void updateGreeting(JAXBGreeting jaxbGreeting){

        logger.info("----------------UPDATING GREETING [{}]!!!----------------", jaxbGreeting);

        JPAGreetingEntity jpaGreetingEntity = jpaGreetingDAO.find(jaxbGreeting.getGreetingId());

        jpaGreetingEntity.setExpression(jaxbGreeting.getExpression());
        jpaGreetingDAO.update(jpaGreetingEntity);

    }

    public JAXBGreeting getGreeting(Long id){

        JPAGreetingEntity jpaGreetingEntity = jpaGreetingDAO.find(id);

        JAXBGreeting jaxbGreeting = new JAXBGreeting();
        jaxbGreeting.setExpression(jpaGreetingEntity.getExpression());
        jaxbGreeting.setGreetingId(jpaGreetingEntity.getId());

        logger.info("----------------GETTING GREETING [{}]!!!----------------", jaxbGreeting);

        return jaxbGreeting;
    }

    public void addUser(JAXBGreeting jaxbGreeting){

        logger.info("----------------PERSISTING USER and GREETING SEPARATELY [{}]!!!----------------", jaxbGreeting);

        JPAGreetingEntity jpaGreetingEntity = new JPAGreetingEntity();
        jpaGreetingEntity.setExpression(jaxbGreeting.getExpression());

        JPAUserEntity jpaUserEntity = new JPAUserEntity();
        jpaUserEntity.setName(jaxbGreeting.getName());
        jpaUserEntity.setCity(jaxbGreeting.getCity());
        jpaUserEntity.setState(jaxbGreeting.getState());

        jpaGreetingDAO.persist(jpaGreetingEntity);
        jpaUserDAO.persist(jpaUserEntity);

    }

    public void addUserCascadePersist(JAXBGreeting jaxbGreeting){

        logger.info("----------------PERSIST USER CASCADE PERSIST TO GREETING [{}]!!!----------------", jaxbGreeting);

        JPAGreetingEntity jpaGreetingEntity = new JPAGreetingEntity();
        jpaGreetingEntity.setExpression(jaxbGreeting.getExpression());

        JPAUserEntity jpaUserEntity = new JPAUserEntity();
        jpaUserEntity.setName(jaxbGreeting.getName());
        jpaUserEntity.setCity(jaxbGreeting.getCity());
        jpaUserEntity.setState(jaxbGreeting.getState());

        jpaUserEntity.setJpaGreetingEntity(jpaGreetingEntity);

        jpaUserDAO.persist(jpaUserEntity);

    }

    public void updateUser(JAXBGreeting jaxbGreeting){

        logger.info("----------------UPDATING USER [{}]!!!----------------", jaxbGreeting);

        JPAUserEntity jpaUserEntity = jpaUserDAO.find(jaxbGreeting.getUserId());

        jpaUserEntity.setName(jaxbGreeting.getName());
        jpaUserEntity.setCity(jaxbGreeting.getCity());
        jpaUserEntity.setState(jaxbGreeting.getState());

        jpaUserDAO.update(jpaUserEntity);
    }

    public void updateUserCascadeUpdate(JAXBGreeting jaxbGreeting){

        logger.info("----------------UPDATE USER CASCADE UPDATE TO GREETING [{}]!!!----------------", jaxbGreeting);

        JPAUserEntity jpaUserEntity = jpaUserDAO.find(jaxbGreeting.getUserId());

        jpaUserEntity.setName(jaxbGreeting.getName());
        jpaUserEntity.setCity(jaxbGreeting.getCity());
        jpaUserEntity.setState(jaxbGreeting.getState());
        jpaUserEntity.getJpaGreetingEntity().setExpression(jaxbGreeting.getExpression());

        jpaUserDAO.update(jpaUserEntity);
    }

    public JAXBGreeting getUser(Long id){

        JPAUserEntity jpaUserEntity = jpaUserDAO.find(id);

        JAXBGreeting jaxbGreeting = new JAXBGreeting();
        jaxbGreeting.setExpression(jpaUserEntity.getJpaGreetingEntity().getExpression());
        jaxbGreeting.setGreetingId(jpaUserEntity.getJpaGreetingEntity().getId());
        jaxbGreeting.setUserId(jpaUserEntity.getId());
        jaxbGreeting.setName(jpaUserEntity.getName());
        jaxbGreeting.setCity(jpaUserEntity.getCity());
        jpaUserEntity.setState(jpaUserEntity.getState());

        logger.info("----------------GETTING USER [{}]!!!----------------", jaxbGreeting);

        return jaxbGreeting;
    }


}
