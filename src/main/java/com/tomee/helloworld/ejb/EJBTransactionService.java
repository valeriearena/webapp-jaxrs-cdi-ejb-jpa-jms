package com.tomee.helloworld.ejb;

import com.tomee.helloworld.jaxb.JAXBHelloWorld;
import com.tomee.helloworld.jpa.dao.JPASalutationDAO;
import com.tomee.helloworld.jpa.dao.JPATechnologyDAO;
import com.tomee.helloworld.jpa.entity.JPASalutationEntity;
import com.tomee.helloworld.jpa.entity.JPATechnologyEntity;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by valerie on 1/10/16.
 */
@Stateless
@TransactionManagement(value= TransactionManagementType.CONTAINER)
@TransactionAttribute(value= TransactionAttributeType.REQUIRED)
public class EJBTransactionService {


    private static final Logger logger = Logger.getLogger(EJBSingletonService.class.getName());

    @Inject
    private JPASalutationDAO jpaSalutationDAO;

    @Inject
    private JPATechnologyDAO jpaTechnologyDAO;


    public void addGreeting(JAXBHelloWorld jaxbHelloWorld){

        logger.log(Level.FINE,"----------------PERSIST SALUTATION [{0}]!!!----------------", jaxbHelloWorld);

        JPASalutationEntity jpaSalutationEntity = new JPASalutationEntity();
        jpaSalutationEntity.setSalutation(jaxbHelloWorld.getSalutation());

        jpaSalutationDAO.persist(jpaSalutationEntity);

    }

    public void updateGreeting(JAXBHelloWorld jaxbHelloWorld){

        logger.log(Level.FINE, "----------------UPDATE SALUTATION [{0}]!!!----------------", jaxbHelloWorld);

        JPASalutationEntity jpaSalutationEntity = jpaSalutationDAO.find(jaxbHelloWorld.getSalutationId());

        jpaSalutationEntity.setSalutation(jaxbHelloWorld.getSalutation());
        jpaSalutationDAO.update(jpaSalutationEntity);

    }

    public JAXBHelloWorld getGreeting(Long id){

        JPASalutationEntity jpaSalutationEntity = jpaSalutationDAO.find(id);

        JAXBHelloWorld jaxbHelloWorld = new JAXBHelloWorld();
        jaxbHelloWorld.setSalutation(jpaSalutationEntity.getSalutation());
        jaxbHelloWorld.setSalutationId(jpaSalutationEntity.getId());

        logger.log(Level.FINE, "----------------GET SALUTATION [{}]!!!----------------", jaxbHelloWorld);

        return jaxbHelloWorld;
    }

    public void addUser(JAXBHelloWorld jaxbHelloWorld){

        logger.log(Level.FINE, "----------------PERSIST TECHNOLOGY and SALUTATION SEPARATELY [{}]!!!----------------", jaxbHelloWorld);

        JPASalutationEntity jpaSalutationEntity = new JPASalutationEntity();
        jpaSalutationEntity.setSalutation(jaxbHelloWorld.getSalutation());

        JPATechnologyEntity jpaTechnologyEntity = new JPATechnologyEntity();
        jpaTechnologyEntity.setTechnologyName(jaxbHelloWorld.getTechnology());
        jpaTechnologyEntity.setTechnologyDescription(jaxbHelloWorld.getDescription());

        jpaSalutationDAO.persist(jpaSalutationEntity);
        jpaTechnologyDAO.persist(jpaTechnologyEntity);

    }

    public void addUserCascadePersist(JAXBHelloWorld jaxbHelloWorld){

        logger.log(Level.FINE, "----------------PERSIST TECHNOLOGY CASCADE PERSIST TO SALUTATION [{}]!!!----------------", jaxbHelloWorld);

        JPASalutationEntity jpaSalutationEntity = new JPASalutationEntity();
        jpaSalutationEntity.setSalutation(jaxbHelloWorld.getSalutation());

        JPATechnologyEntity jpaTechnologyEntity = new JPATechnologyEntity();
        jpaTechnologyEntity.setTechnologyName(jaxbHelloWorld.getTechnology());
        jpaTechnologyEntity.setTechnologyDescription(jaxbHelloWorld.getDescription());

        jpaTechnologyEntity.setJpaSalutationEntity(jpaSalutationEntity);

        jpaTechnologyDAO.persist(jpaTechnologyEntity);

    }

    public void updateUser(JAXBHelloWorld jaxbHelloWorld){

        logger.log(Level.FINE, "----------------UPDATE TECHNOLOGY [{}]!!!----------------", jaxbHelloWorld);

        JPATechnologyEntity jpaTechnologyEntity = jpaTechnologyDAO.find(jaxbHelloWorld.getTechnologyId());

        jpaTechnologyEntity.setTechnologyName(jaxbHelloWorld.getTechnology());
        jpaTechnologyEntity.setTechnologyDescription(jaxbHelloWorld.getDescription());

        jpaTechnologyDAO.update(jpaTechnologyEntity);
    }

    public void updateUserCascadeUpdate(JAXBHelloWorld jaxbHelloWorld){

        logger.log(Level.FINE, "----------------UPDATE TECHNOLOGY CASCADE UPDATE TO SALUTATION [{}]!!!----------------", jaxbHelloWorld);

        JPATechnologyEntity jpaTechnologyEntity = jpaTechnologyDAO.find(jaxbHelloWorld.getTechnologyId());

        jpaTechnologyEntity.setTechnologyName(jaxbHelloWorld.getTechnology());
        jpaTechnologyEntity.setTechnologyDescription(jaxbHelloWorld.getDescription());

        jpaTechnologyEntity.getJpaSalutationEntity().setSalutation(jaxbHelloWorld.getSalutation());

        jpaTechnologyDAO.update(jpaTechnologyEntity);
    }

    public JAXBHelloWorld getUser(Long id){

        JPATechnologyEntity jpaTechnologyEntity = jpaTechnologyDAO.find(id);

        JAXBHelloWorld jaxbHelloWorld = new JAXBHelloWorld();
        jaxbHelloWorld.setSalutation(jpaTechnologyEntity.getJpaSalutationEntity().getSalutation());
        jaxbHelloWorld.setSalutationId(jpaTechnologyEntity.getJpaSalutationEntity().getId());
        jaxbHelloWorld.setTechnologyId(jpaTechnologyEntity.getId());
        jaxbHelloWorld.setTechnology(jpaTechnologyEntity.getTechnologyName());
        jaxbHelloWorld.setDescription(jpaTechnologyEntity.getTechnologyDescription());

        logger.log(Level.FINE, "----------------GET TECHNOLOGY [{}]!!!----------------", jaxbHelloWorld);

        return jaxbHelloWorld;
    }

    public List<JAXBHelloWorld> findAll(){

        List<JPATechnologyEntity> jpaTechnologyEntityList = jpaTechnologyDAO.findAll();

        JAXBHelloWorld jaxbHelloWorld = null;
        List<JAXBHelloWorld> jaxbGreetingsList = new ArrayList<>();

        for(JPATechnologyEntity jpaTechnologyEntity : jpaTechnologyEntityList){
            jaxbHelloWorld = new JAXBHelloWorld();

            jaxbHelloWorld.setSalutationId(jpaTechnologyEntity.getJpaSalutationEntity().getId());
            jaxbHelloWorld.setSalutation(jpaTechnologyEntity.getJpaSalutationEntity().getSalutation());

            jaxbHelloWorld.setTechnologyId(jpaTechnologyEntity.getId());
            jaxbHelloWorld.setTechnology(jpaTechnologyEntity.getTechnologyName());
            jaxbHelloWorld.setDescription(jpaTechnologyEntity.getTechnologyDescription());
            jaxbGreetingsList.add(jaxbHelloWorld);
        }

        logger.log(Level.FINE, "----------------GET ALL TECHNOLOGIES [{}]!!!----------------", jaxbGreetingsList.size());

        return jaxbGreetingsList;
    }


}
