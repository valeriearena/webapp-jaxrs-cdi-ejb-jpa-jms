package com.tomee.helloworld.jpa.dao;

import com.tomee.helloworld.jpa.entity.JPASalutationEntity;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Created by valerie on 1/13/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JPASalutationDAOTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloworldH2");

    private static JPASalutationDAO jpaSalutationDAO = new JPASalutationDAO();

    @BeforeClass
    public static void setUp() throws Exception {
        jpaSalutationDAO.setEntityManager(emf.createEntityManager());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        emf = null;
        jpaSalutationDAO = null;
    }

    @Test
    public void test01Persist() throws Exception {

     try{
            JPASalutationEntity jpaSalutationEntity = new JPASalutationEntity();
            jpaSalutationEntity.setSalutation("test");

            jpaSalutationDAO.getEntityManager().getTransaction().begin();
            jpaSalutationDAO.persist(jpaSalutationEntity);
            jpaSalutationDAO.getEntityManager().getTransaction().commit();

            jpaSalutationEntity = jpaSalutationDAO.find(new Long(1));

            assertNotNull(jpaSalutationEntity.getId());
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }

    }

    @Test
    public void test02Find() throws Exception {
        try{
            jpaSalutationDAO.getEntityManager().getTransaction().begin();
            JPASalutationEntity jpaSalutationEntity = jpaSalutationDAO.find(new Long(1));
            jpaSalutationDAO.getEntityManager().getTransaction().commit();

            assertNotNull(jpaSalutationEntity.getId());
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test03Update() throws Exception {
        try{
            jpaSalutationDAO.getEntityManager().getTransaction().begin();
            JPASalutationEntity jpaSalutationEntity = jpaSalutationDAO.find(new Long(1));

            String oldName = jpaSalutationEntity.getSalutation();

            jpaSalutationEntity.setSalutation("val");

            jpaSalutationDAO.update(jpaSalutationEntity);
            jpaSalutationDAO.getEntityManager().getTransaction().commit();

            jpaSalutationDAO.getEntityManager().getTransaction().begin();
            jpaSalutationEntity = jpaSalutationDAO.find(new Long(1));
            jpaSalutationDAO.getEntityManager().getTransaction().commit();

            boolean assertFlag = jpaSalutationEntity.getSalutation().equals(oldName);
            assertFalse(assertFlag);
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

}