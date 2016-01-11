package com.tomee.helloworld.jaxrs;

import com.tomee.helloworld.cdi.PojoService;
import com.tomee.helloworld.ejb.EJBQueueService;
import com.tomee.helloworld.ejb.EJBSingletonService;
import com.tomee.helloworld.ejb.EJBTransactionService;
import com.tomee.helloworld.jaxb.JAXBGreeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @Inject
    private EJBQueueService ejbQueueService;

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
    public Response jaxRs(JAXBGreeting jaxbGreeting) {

        logger.info("----------------REST JAXR Service [{}]!!!----------------",jaxbGreeting);
        return Response.status(Response.Status.OK).entity(jaxbGreeting).build();
    }

    @GET
    @Path("/singleton")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSingleton() {

        ejbSingletonService.getGreeting();
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/singleton/{greeting}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response postSingleton(@PathParam("greeting") String greeting) {

        ejbSingletonService.setGreeting(greeting);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/pojo")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPojo() {

        pojoService.getGreeting();
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/pojo/{greeting}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response postPojo(@PathParam("greeting") String greeting) {

        pojoService.setGreeting(greeting);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/addGreeting")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addGreeting(JAXBGreeting jaxbGreeting) {

        ejbTransactionService.addGreeting(jaxbGreeting);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/updateGreeting")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response updateGreeting(JAXBGreeting jaxbGreeting) {

        ejbTransactionService.updateGreeting(jaxbGreeting);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/getGreeting/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getGreeting(@PathParam("id") Long id) {

        JAXBGreeting jaxbGreeting = ejbTransactionService.getGreeting(id);
        return Response.status(Response.Status.OK).entity(jaxbGreeting).build();
    }

    @POST
    @Path("/addUser")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addUser(JAXBGreeting jaxbGreeting) {

        ejbTransactionService.addUser(jaxbGreeting);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/addUserCascadePersist")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addUserCascadePersist(JAXBGreeting jaxbGreeting) {

        ejbTransactionService.addUserCascadePersist(jaxbGreeting);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/getUser/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getUser(@PathParam("id") Long id) {

        JAXBGreeting jaxbGreeting = ejbTransactionService.getUser(id);
        return Response.status(Response.Status.OK).entity(jaxbGreeting).build();
    }

    @GET
    @Path("/getAll")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getAll() {

        List<JAXBGreeting> greetingList = ejbTransactionService.findAll();

        GenericEntity<List<JAXBGreeting>> list = new GenericEntity<List<JAXBGreeting>>(greetingList) {};
        return Response.ok(list).build();
    }


    @POST
    @Path("/jms")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response jmsSend(JAXBGreeting jaxbGreeting) {

        ejbQueueService.send(jaxbGreeting);
        return Response.status(Response.Status.OK).build();
    }

}

