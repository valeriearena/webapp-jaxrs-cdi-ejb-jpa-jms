package com.tomee.helloworld.jpa.dao;

import com.tomee.helloworld.jpa.entity.JPATechnologyEntity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Created by valerie on 1/13/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JPATechnologyDAOTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloworldH2Test");

    private static JPATechnologyDAO jpaTechnologyDAO = new JPATechnologyDAO();

    @BeforeClass
    public static void setUpClass(){
        jpaTechnologyDAO.setEntityManager(emf.createEntityManager());
    }

    @AfterClass
    public static void tearDown(){
        emf = null;
        jpaTechnologyDAO = null;
    }

    @Test
    public void test01Persist(){
        try{
            JPATechnologyEntity jpaTechnologyEntity = new JPATechnologyEntity();
            jpaTechnologyEntity.getJpaSalutationEntity().setSalutation("test");
            jpaTechnologyEntity.setTechnology("test");
            jpaTechnologyEntity.setDescription("test");

            jpaTechnologyDAO.getEntityManager().getTransaction().begin();
            jpaTechnologyDAO.persist(jpaTechnologyEntity);
            jpaTechnologyDAO.getEntityManager().getTransaction().commit();

            jpaTechnologyEntity = jpaTechnologyDAO.find(new Long(1));

            assertNotNull(jpaTechnologyEntity.getId());
            assertNotNull(jpaTechnologyEntity.getJpaSalutationEntity().getId());
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test02Find(){
        try{
            jpaTechnologyDAO.getEntityManager().getTransaction().begin();
            JPATechnologyEntity jpaTechnologyEntity = jpaTechnologyDAO.find(new Long(1));
            jpaTechnologyDAO.getEntityManager().getTransaction().commit();

            assertNotNull(jpaTechnologyEntity.getId());
            assertNotNull(jpaTechnologyEntity.getJpaSalutationEntity().getId());
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test03Update(){
        try{
            jpaTechnologyDAO.getEntityManager().getTransaction().begin();
            JPATechnologyEntity jpaTechnologyEntity = jpaTechnologyDAO.find(new Long(1));

            String oldName = jpaTechnologyEntity.getTechnology();

            jpaTechnologyEntity.setTechnology("jpa");

            jpaTechnologyDAO.update(jpaTechnologyEntity);
            jpaTechnologyDAO.getEntityManager().getTransaction().commit();

            jpaTechnologyDAO.getEntityManager().getTransaction().begin();
            jpaTechnologyEntity = jpaTechnologyDAO.find(new Long(1));
            jpaTechnologyDAO.getEntityManager().getTransaction().commit();

            boolean assertFlag = jpaTechnologyEntity.getTechnology().equals(oldName);
            assertFalse(assertFlag);
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test04FindAll(){
        try{
            jpaTechnologyDAO.getEntityManager().getTransaction().begin();
            List<JPATechnologyEntity> list = jpaTechnologyDAO.findAll();
            jpaTechnologyDAO.getEntityManager().getTransaction().commit();
            boolean assertFlag = (list.size() == 1);

            assertTrue(assertFlag);
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }

    }

}