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

@Stateless
@TransactionManagement(value= TransactionManagementType.CONTAINER)
@TransactionAttribute(value= TransactionAttributeType.REQUIRED)
public class EJBTransactionService {


    private static final Logger logger = Logger.getLogger(EJBSingletonService.class.getName());

    @Inject
    private JPASalutationDAO jpaSalutationDAO;

    @Inject
    private JPATechnologyDAO jpaTechnologyDAO;


    public void addSalutation(JAXBHelloWorld jaxbHelloWorld){

        logger.log(Level.FINE,"----------------PERSIST SALUTATION [{0}]!!!----------------", jaxbHelloWorld);

        JPASalutationEntity jpaSalutationEntity = new JPASalutationEntity();
        jpaSalutationEntity.setSalutation(jaxbHelloWorld.getSalutation());

        jpaSalutationDAO.persist(jpaSalutationEntity);

    }

    public void updateSalutation(JAXBHelloWorld jaxbHelloWorld){

        logger.log(Level.FINE, "----------------UPDATE SALUTATION [{0}]!!!----------------", jaxbHelloWorld);

        JPASalutationEntity jpaSalutationEntity = jpaSalutationDAO.find(jaxbHelloWorld.getSalutationId());

        jpaSalutationEntity.setSalutation(jaxbHelloWorld.getSalutation());
        jpaSalutationDAO.update(jpaSalutationEntity);

    }

    public JAXBHelloWorld getSalutation(Long id){

        JPASalutationEntity jpaSalutationEntity = jpaSalutationDAO.find(id);

        JAXBHelloWorld jaxbHelloWorld = new JAXBHelloWorld();
        jaxbHelloWorld.setSalutation(jpaSalutationEntity.getSalutation());
        jaxbHelloWorld.setSalutationId(jpaSalutationEntity.getId());

        logger.log(Level.FINE, "----------------GET SALUTATION [{0}]!!!----------------", jaxbHelloWorld);

        return jaxbHelloWorld;
    }

    public void addTechnology(JAXBHelloWorld jaxbHelloWorld){

        logger.log(Level.FINE, "----------------PERSIST TECHNOLOGY and SALUTATION SEPARATELY [{0}]!!!----------------", jaxbHelloWorld);

        JPASalutationEntity jpaSalutationEntity = new JPASalutationEntity();
        jpaSalutationEntity.setSalutation(jaxbHelloWorld.getSalutation());

        JPATechnologyEntity jpaTechnologyEntity = new JPATechnologyEntity();
        jpaTechnologyEntity.setTechnology(jaxbHelloWorld.getTechnology());
        jpaTechnologyEntity.setDescription(jaxbHelloWorld.getDescription());

        jpaSalutationDAO.persist(jpaSalutationEntity);
        jpaTechnologyDAO.persist(jpaTechnologyEntity);

    }

    public void addTechnologyCascadePersist(JAXBHelloWorld jaxbHelloWorld){

        logger.log(Level.FINE, "----------------PERSIST TECHNOLOGY CASCADE PERSIST TO SALUTATION [{0}]!!!----------------", jaxbHelloWorld);

        JPASalutationEntity jpaSalutationEntity = new JPASalutationEntity();
        jpaSalutationEntity.setSalutation(jaxbHelloWorld.getSalutation());

        JPATechnologyEntity jpaTechnologyEntity = new JPATechnologyEntity();
        jpaTechnologyEntity.setTechnology(jaxbHelloWorld.getTechnology());
        jpaTechnologyEntity.setDescription(jaxbHelloWorld.getDescription());

        jpaTechnologyEntity.setJpaSalutationEntity(jpaSalutationEntity);

        jpaTechnologyDAO.persist(jpaTechnologyEntity);

    }

    public void updateTechnology(JAXBHelloWorld jaxbHelloWorld){

        logger.log(Level.FINE, "----------------UPDATE TECHNOLOGY [{0}]!!!----------------", jaxbHelloWorld);

        JPATechnologyEntity jpaTechnologyEntity = jpaTechnologyDAO.find(jaxbHelloWorld.getTechnologyId());

        jpaTechnologyEntity.setTechnology(jaxbHelloWorld.getTechnology());
        jpaTechnologyEntity.setDescription(jaxbHelloWorld.getDescription());

        jpaTechnologyDAO.update(jpaTechnologyEntity);
    }

    public void updateTechnologyCascadeUpdate(JAXBHelloWorld jaxbHelloWorld){

        logger.log(Level.FINE, "----------------UPDATE TECHNOLOGY CASCADE UPDATE TO SALUTATION [{0}]!!!----------------", jaxbHelloWorld);

        JPATechnologyEntity jpaTechnologyEntity = jpaTechnologyDAO.find(jaxbHelloWorld.getTechnologyId());

        jpaTechnologyEntity.setTechnology(jaxbHelloWorld.getTechnology());
        jpaTechnologyEntity.setDescription(jaxbHelloWorld.getDescription());

        jpaTechnologyEntity.getJpaSalutationEntity().setSalutation(jaxbHelloWorld.getSalutation());

        jpaTechnologyDAO.update(jpaTechnologyEntity);
    }

    public JAXBHelloWorld getTechnology(Long id){

        JPATechnologyEntity jpaTechnologyEntity = jpaTechnologyDAO.find(id);

        JAXBHelloWorld jaxbHelloWorld = new JAXBHelloWorld();
        jaxbHelloWorld.setSalutation(jpaTechnologyEntity.getJpaSalutationEntity().getSalutation());
        jaxbHelloWorld.setSalutationId(jpaTechnologyEntity.getJpaSalutationEntity().getId());
        jaxbHelloWorld.setTechnologyId(jpaTechnologyEntity.getId());
        jaxbHelloWorld.setTechnology(jpaTechnologyEntity.getTechnology());
        jaxbHelloWorld.setDescription(jpaTechnologyEntity.getDescription());

        logger.log(Level.FINE, "----------------GET TECHNOLOGY [{0}]!!!----------------", jaxbHelloWorld);

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
            jaxbHelloWorld.setTechnology(jpaTechnologyEntity.getTechnology());
            jaxbHelloWorld.setDescription(jpaTechnologyEntity.getTechnology());
            jaxbGreetingsList.add(jaxbHelloWorld);
        }

        logger.log(Level.FINE, "----------------GET ALL TECHNOLOGIES [{0}]!!!----------------", jaxbGreetingsList.size());

        return jaxbGreetingsList;
    }

    public JPASalutationDAO getJpaSalutationDAO() {
        return jpaSalutationDAO;
    }

    public void setJpaSalutationDAO(JPASalutationDAO jpaSalutationDAO) {
        this.jpaSalutationDAO = jpaSalutationDAO;
    }

    public JPATechnologyDAO getJpaTechnologyDAO() {
        return jpaTechnologyDAO;
    }

    public void setJpaTechnologyDAO(JPATechnologyDAO jpaTechnologyDAO) {
        this.jpaTechnologyDAO = jpaTechnologyDAO;
    }
}
