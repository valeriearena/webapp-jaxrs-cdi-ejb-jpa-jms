package com.tomee.helloworld.jpa.dao;

import com.tomee.helloworld.jpa.entity.JPAGreetingEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by valerie on 1/10/16.
 */
public class JPAGreetingDAO {

    private static final Logger logger = LoggerFactory.getLogger(JPAGreetingDAO.class);

    @PersistenceContext
    private EntityManager em;

    public void persist(JPAGreetingEntity JPAGreetingEntity){
        em.persist(JPAGreetingEntity);
    }

    public JPAGreetingEntity find(Integer id){

        return em.find(JPAGreetingEntity.class, id);
    }

    public JPAGreetingEntity update(JPAGreetingEntity JPAGreetingEntity){
        return em.merge(JPAGreetingEntity);
    }

    public void delete(Integer id){
        em.remove(id);
    }

}
