package com.tomee.helloworld.ejb;

import com.tomee.helloworld.jaxb.JAXBHelloWorld;
import com.tomee.helloworld.jpa.dao.JPASalutationDAO;
import com.tomee.helloworld.jpa.dao.JPATechnologyDAO;
import com.tomee.helloworld.jpa.entity.JPASalutationEntity;
import com.tomee.helloworld.jpa.entity.JPATechnologyEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by valerie on 1/13/16.
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EJBTransactionServiceTest {

    @Mock
    private JPASalutationDAO jpaSalutationDAO;

    @Mock
    private JPATechnologyDAO jpaTechnologyDAO;

    private EJBTransactionService ejbTransactionService = new EJBTransactionService();

    @Before
    public void setUp() throws Exception {

        ejbTransactionService.setJpaSalutationDAO(jpaSalutationDAO);
        ejbTransactionService.setJpaTechnologyDAO(jpaTechnologyDAO);
    }

    @After
    public void tearDown() throws Exception {
        jpaSalutationDAO = null;
        jpaTechnologyDAO = null;
        ejbTransactionService = null;
    }

    @Test
    public void testAddSalutation() throws Exception {

        JAXBHelloWorld jaxbHelloWorld = new JAXBHelloWorld();
        jaxbHelloWorld.setSalutation("Hello Mockito!");

        ejbTransactionService.addSalutation(jaxbHelloWorld);

        Mockito.verify(jpaSalutationDAO, Mockito.times(1)).persist(any(JPASalutationEntity.class));
    }

    @Test
    public void testUpdateSalutation() throws Exception {

        JAXBHelloWorld jaxbHelloWorld = new JAXBHelloWorld();
        jaxbHelloWorld.setSalutationId(new Long(1));

        JPASalutationEntity jpaSalutationEntity = new JPASalutationEntity();
        jpaSalutationEntity.setSalutation("Hi Mockito!!");
        jpaSalutationEntity.setId(new Long(1));

        when(this.jpaSalutationDAO.find(new Long(1))).thenReturn(jpaSalutationEntity);

        ejbTransactionService.updateSalutation(jaxbHelloWorld);

        Mockito.verify(jpaSalutationDAO, Mockito.times(1)).update(any(JPASalutationEntity.class));

    }

    @Test
    public void testGetSalutation() throws Exception {

        JPASalutationEntity jpaSalutationEntity = new JPASalutationEntity();
        jpaSalutationEntity.setSalutation("Hi Mockito!!");
        jpaSalutationEntity.setId(new Long(1));

        when(this.jpaSalutationDAO.find(new Long(1))).thenReturn(jpaSalutationEntity);

        JAXBHelloWorld jaxbHelloWorld = ejbTransactionService.getSalutation(new Long(1));
        assertTrue(jaxbHelloWorld.getSalutation().equals("Hi Mockito!!"));
    }

    @Test
    public void testAddTechnology() throws Exception {

        JAXBHelloWorld jaxbHelloWorld = new JAXBHelloWorld();
        jaxbHelloWorld.setSalutation("Hello Mockito!");
        jaxbHelloWorld.setTechnology("EJB");
        jaxbHelloWorld.setDescription("Unit testing my EJB");

        ejbTransactionService.addTechnology(jaxbHelloWorld);

        Mockito.verify(jpaSalutationDAO, Mockito.times(1)).persist(any(JPASalutationEntity.class));
        Mockito.verify(jpaTechnologyDAO, Mockito.times(1)).persist(any(JPATechnologyEntity.class));
    }

    @Test
    public void testAddTechnologyCascadePersist() throws Exception {

        JAXBHelloWorld jaxbHelloWorld = new JAXBHelloWorld();
        jaxbHelloWorld.setSalutation("Hello Mockito!");
        jaxbHelloWorld.setTechnology("EJB");
        jaxbHelloWorld.setDescription("Unit testing my EJB");

        ejbTransactionService.addTechnologyCascadePersist(jaxbHelloWorld);

        Mockito.verify(jpaSalutationDAO, Mockito.times(0)).persist(any(JPASalutationEntity.class));
        Mockito.verify(jpaTechnologyDAO, Mockito.times(1)).persist(any(JPATechnologyEntity.class));
    }

    @Test
    public void testUpdateTechnology() throws Exception {

        JPASalutationEntity jpaSalutationEntity = new JPASalutationEntity();
        jpaSalutationEntity.setSalutation("Hi Mockito!!");
        jpaSalutationEntity.setId(new Long(1));

        JPATechnologyEntity jpaTechnologyEntity = new JPATechnologyEntity();
        jpaTechnologyEntity.setTechnology("Mockito");
        jpaTechnologyEntity.setDescription("Mockito is a test framework!");
        jpaTechnologyEntity.setId(new Long(1));

        jpaTechnologyEntity.setJpaSalutationEntity(jpaSalutationEntity);

        JAXBHelloWorld jaxbHelloWorld = new JAXBHelloWorld();
        jaxbHelloWorld.setTechnologyId(new Long(1));
        jaxbHelloWorld.setSalutationId(new Long(1));
        jaxbHelloWorld.setSalutation("Hello Mockito!");
        jaxbHelloWorld.setTechnology("EJB");
        jaxbHelloWorld.setDescription("Unit testing my EJB");

        when(this.jpaTechnologyDAO.find(new Long(1))).thenReturn(jpaTechnologyEntity);

        ejbTransactionService.updateTechnology(jaxbHelloWorld);

        Mockito.verify(jpaTechnologyDAO, Mockito.times(1)).update(any(JPATechnologyEntity.class));
    }

    @Test
    public void testUpdateTechnologyCascadeUpdate() throws Exception {

        JPASalutationEntity jpaSalutationEntity = new JPASalutationEntity();
        jpaSalutationEntity.setSalutation("Hi Mockito!!");
        jpaSalutationEntity.setId(new Long(1));

        JPATechnologyEntity jpaTechnologyEntity = new JPATechnologyEntity();
        jpaTechnologyEntity.setTechnology("Mockito");
        jpaTechnologyEntity.setDescription("Mockito is a test framework!");
        jpaTechnologyEntity.setId(new Long(1));

        jpaTechnologyEntity.setJpaSalutationEntity(jpaSalutationEntity);

        JAXBHelloWorld jaxbHelloWorld = new JAXBHelloWorld();
        jaxbHelloWorld.setTechnologyId(new Long(1));
        jaxbHelloWorld.setSalutationId(new Long(1));
        jaxbHelloWorld.setSalutation("Hello Mockito!");
        jaxbHelloWorld.setTechnology("EJB");
        jaxbHelloWorld.setDescription("Unit testing my EJB");

        when(this.jpaTechnologyDAO.find(new Long(1))).thenReturn(jpaTechnologyEntity);

        ejbTransactionService.updateTechnologyCascadeUpdate(jaxbHelloWorld);

        Mockito.verify(jpaTechnologyDAO, Mockito.times(1)).update(any(JPATechnologyEntity.class));
    }

    @Test
    public void testGetTechnology() throws Exception {

        JPASalutationEntity jpaSalutationEntity = new JPASalutationEntity();
        jpaSalutationEntity.setSalutation("Hi Mockito!!");
        jpaSalutationEntity.setId(new Long(1));

        JPATechnologyEntity jpaTechnologyEntity = new JPATechnologyEntity();
        jpaTechnologyEntity.setTechnology("Mockito");
        jpaTechnologyEntity.setDescription("Mockito is a test framework!");
        jpaTechnologyEntity.setId(new Long(1));
        jpaTechnologyEntity.setJpaSalutationEntity(jpaSalutationEntity);

        when(this.jpaTechnologyDAO.find(new Long(1))).thenReturn(jpaTechnologyEntity);

        JAXBHelloWorld jaxbHelloWorld = ejbTransactionService.getTechnology(new Long(1));
        assertTrue(jaxbHelloWorld.getSalutation().equals("Hi Mockito!!"));
    }

    @Test
    public void testFindAll() throws Exception {

        JPASalutationEntity jpaSalutationEntityOne = new JPASalutationEntity();
        jpaSalutationEntityOne.setSalutation("Hi Mockito!!");
        jpaSalutationEntityOne.setId(new Long(1));

        JPATechnologyEntity jpaTechnologyEntityOne = new JPATechnologyEntity();
        jpaTechnologyEntityOne.setTechnology("Mockito");
        jpaTechnologyEntityOne.setDescription("Mockito is a test framework!");
        jpaTechnologyEntityOne.setId(new Long(1));
        jpaTechnologyEntityOne.setJpaSalutationEntity(jpaSalutationEntityOne);

        JPASalutationEntity jpaSalutationEntityTwo = new JPASalutationEntity();
        jpaSalutationEntityTwo.setSalutation("Hi Mockito!!");
        jpaSalutationEntityTwo.setId(new Long(1));

        JPATechnologyEntity jpaTechnologyEntityTwo = new JPATechnologyEntity();
        jpaTechnologyEntityTwo.setTechnology("Mockito");
        jpaTechnologyEntityTwo.setDescription("Mockito is a test framework!");
        jpaTechnologyEntityTwo.setId(new Long(1));
        jpaTechnologyEntityTwo.setJpaSalutationEntity(jpaSalutationEntityOne);

        List<JPATechnologyEntity> entityList = new ArrayList<>();
        entityList.add(jpaTechnologyEntityOne);
        entityList.add(jpaTechnologyEntityTwo);

        when(this.jpaTechnologyDAO.findAll()).thenReturn(entityList);

        List<JAXBHelloWorld> helloWorldList = ejbTransactionService.findAll();
        assertTrue(helloWorldList.size() == 2);

    }
}