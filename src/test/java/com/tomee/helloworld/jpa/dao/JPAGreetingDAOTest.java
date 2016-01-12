package com.tomee.helloworld.jpa.dao;

import com.tomee.helloworld.jpa.entity.JPAGreetingEntity;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by valerie on 1/12/16.
 */
public class JPAGreetingDAOTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloworldTest");

    private static JPAGreetingDAO jpaGreetingDAO = new JPAGreetingDAO();

    @BeforeClass
    public static void setUpClass(){
        jpaGreetingDAO.setEntityManager(emf.createEntityManager());
    }

    @AfterClass
    public static void tearDown(){
        emf = null;
        jpaGreetingDAO = null;
    }

    public void test01Persist(){
        try{
            JPAGreetingEntity jpaGreetingEntity = new JPAGreetingEntity();
            jpaGreetingEntity.setExpression("test");

            jpaGreetingDAO.getEntityManager().getTransaction().begin();
            jpaGreetingDAO.persist(jpaGreetingEntity);
            jpaGreetingDAO.getEntityManager().getTransaction().commit();

            jpaGreetingEntity = jpaGreetingDAO.find(new Long(1));

            assertNotNull(jpaGreetingEntity.getId());
            assertNotNull(jpaGreetingEntity.getExpression());
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }
}