package com.tomee.helloworld.jaxrs;

import com.tomee.helloworld.ejb.EJBSingletonService;
import com.tomee.helloworld.ejb.EJBTransactionService;
import com.tomee.helloworld.jaxb.JAXBGreeting;
import com.tomee.helloworld.cdi.PojoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by valerie on 12/28/15.
 */
@Path("/hello")
public class JAXRSResource {

    private static final Logger logger = LoggerFactory.getLogger(JAXRSResource.class);

    @Inject
    private EJBSingletonService ejbSingletonService;

    @Inject
    private PojoService pojoService;

    @Inject
    private EJBTransactionService ejbTransactionService;

    @GET
    @Path("/ping/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping(@PathParam("name") String name) {
        return "pong " + name;
    }

    @POST
    @Path("/jaxrs")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public String jaxRs(JAXBGreeting jaxbGreeting) {

        logger.info("----------------REST JAXR Service [{}]!!!----------------",jaxbGreeting);
        return jaxbGreeting.toString();
    }

    @GET
    @Path("/singleton")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSingleton() {

        return ejbSingletonService.getGreeting();
    }

    @POST
    @Path("/singleton/{greeting}")
    @Produces(MediaType.TEXT_PLAIN)
    public String postSingleton(@PathParam("greeting") String greeting) {

        ejbSingletonService.setGreeting(greeting);
        return greeting;
    }

    @GET
    @Path("/pojo")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPojo() {

        return pojoService.getGreeting();
    }

    @POST
    @Path("/pojo/{greeting}")
    @Produces(MediaType.TEXT_PLAIN)
    public String postPojo(@PathParam("greeting") String greeting) {

        pojoService.setGreeting(greeting);
        return greeting;
    }

    @POST
    @Path("/jpa")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public String jpaPersist(JAXBGreeting jaxbGreeting) {

        ejbTransactionService.addGreeting(jaxbGreeting);
        return jaxbGreeting.toString();

    }
}

