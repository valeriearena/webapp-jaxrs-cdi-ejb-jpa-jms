package com.tomee.helloworld.jpa.dao;

import com.tomee.helloworld.jpa.entity.JPAUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by valerie on 1/11/16.
 */
public class JPAUserDAO {

    private static final Logger logger = LoggerFactory.getLogger(JPAUserDAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(JPAUserEntity jpaUserEntity){
        entityManager.persist(jpaUserEntity);
    }

    public JPAUserEntity find(Long id){

        return entityManager.find(JPAUserEntity.class, id);
    }

    public JPAUserEntity update(JPAUserEntity jpaUserEntity){
        return entityManager.merge(jpaUserEntity);
    }

    public void delete(Long id){
        entityManager.remove(id);
    }

    public List<JPAUserEntity> findAll() {
        TypedQuery<JPAUserEntity> query  = entityManager.createNamedQuery("JPAUserEntity.findAll", JPAUserEntity.class);
        return query.getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
