package com.tomee.helloworld.jpa.dao;

import com.tomee.helloworld.jpa.entity.JPASalutationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPASalutationDAO {

    private static final Logger logger = LoggerFactory.getLogger(JPASalutationDAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(JPASalutationEntity jpaSalutationEntity){
        entityManager.persist(jpaSalutationEntity);
    }

    public JPASalutationEntity find(Long id){

        return entityManager.find(JPASalutationEntity.class, id);
    }

    public JPASalutationEntity update(JPASalutationEntity jpaSalutationEntity){
        return entityManager.merge(jpaSalutationEntity);
    }

    public void delete(Long id){
        entityManager.remove(id);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
