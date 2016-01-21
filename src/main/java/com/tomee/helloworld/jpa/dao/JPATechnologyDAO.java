package com.tomee.helloworld.jpa.dao;

import com.tomee.helloworld.jpa.entity.JPATechnologyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class JPATechnologyDAO {

    private static final Logger logger = LoggerFactory.getLogger(JPATechnologyDAO.class);

    @PersistenceContext(unitName="helloworldH2")
    private EntityManager entityManager;

    public void persist(JPATechnologyEntity jpaTechnologyEntity){
        entityManager.persist(jpaTechnologyEntity);
    }

    public JPATechnologyEntity find(Long id){

        return entityManager.find(JPATechnologyEntity.class, id);
    }

    public JPATechnologyEntity update(JPATechnologyEntity jpaTechnologyEntity){
        return entityManager.merge(jpaTechnologyEntity);
    }

    public void delete(Long id){
        entityManager.remove(id);
    }

    public List<JPATechnologyEntity> findAll() {
        TypedQuery<JPATechnologyEntity> query  = entityManager.createNamedQuery("JPATechnologyEntity.findAll", JPATechnologyEntity.class);
        return query.getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
