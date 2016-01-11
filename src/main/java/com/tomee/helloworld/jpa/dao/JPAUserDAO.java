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
    private EntityManager em;

    public void persist(JPAUserEntity jpaUserEntity){
        em.persist(jpaUserEntity);
    }

    public JPAUserEntity find(Long id){

        return em.find(JPAUserEntity.class, id);
    }

    public JPAUserEntity update(JPAUserEntity jpaUserEntity){
        return em.merge(jpaUserEntity);
    }

    public void delete(Long id){
        em.remove(id);
    }

    public List<JPAUserEntity> findAll() {
        TypedQuery<JPAUserEntity> query  = em.createNamedQuery("JPAUserEntity.findAll", JPAUserEntity.class);
        return query.getResultList();
    }
}
