package com.tomee.helloworld.jaxrs;

import com.tomee.helloworld.cdi.PojoService;
import com.tomee.helloworld.ejb.EJBQueueService;
import com.tomee.helloworld.ejb.EJBSingletonService;
import com.tomee.helloworld.ejb.EJBTransactionService;
import com.tomee.helloworld.jaxb.JAXBHelloWorld;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by valerie on 12/28/15.
 */
@Path("/helloworld")
public class JAXRSResource {

    private static final Logger logger = Logger.getLogger(JAXRSResource.class.getName());

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
    public Response jaxRs(JAXBHelloWorld jaxbHelloWorld) {

        logger.log(Level.FINE,"----------------REST JAXRS Service [{0}]!!!----------------", jaxbHelloWorld);
        return Response.status(Response.Status.OK).entity(jaxbHelloWorld).build();
    }

    @GET
    @Path("/singleton")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSingleton() {

        ejbSingletonService.getSalutation();
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/singleton/{salutation}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response postSingleton(@PathParam("salutation") String salutation) {

        ejbSingletonService.setSalutation(salutation);
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
    @Path("/pojo/{salutation}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response postPojo(@PathParam("salutation") String salutation) {

        pojoService.setGreeting(salutation);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/addSalutation")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addSalutation(JAXBHelloWorld jaxbHelloWorld) {

        ejbTransactionService.addGreeting(jaxbHelloWorld);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/updateSalutation")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response updateSalutation(JAXBHelloWorld jaxbHelloWorld) {

        ejbTransactionService.updateGreeting(jaxbHelloWorld);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/getSalutation/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getSalutation(@PathParam("id") Long id) {

        JAXBHelloWorld jaxbHelloWorld = ejbTransactionService.getGreeting(id);
        return Response.status(Response.Status.OK).entity(jaxbHelloWorld).build();
    }

    @POST
    @Path("/addTechnology")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addTechnology(JAXBHelloWorld jaxbHelloWorld) {

        ejbTransactionService.addUser(jaxbHelloWorld);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/addTechnologyCascadePersist")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addTechnologyCascadePersist(JAXBHelloWorld jaxbHelloWorld) {

        ejbTransactionService.addUserCascadePersist(jaxbHelloWorld);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/getTechnology/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getTechnology(@PathParam("id") Long id) {

        JAXBHelloWorld jaxbHelloWorld = ejbTransactionService.getUser(id);
        return Response.status(Response.Status.OK).entity(jaxbHelloWorld).build();
    }

    @GET
    @Path("/getAll")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getAll() {

        List<JAXBHelloWorld> greetingList = ejbTransactionService.findAll();

        GenericEntity<List<JAXBHelloWorld>> list = new GenericEntity<List<JAXBHelloWorld>>(greetingList) {};
        return Response.ok(list).build();
    }


    @POST
    @Path("/jms")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response jmsSend(JAXBHelloWorld jaxbHelloWorld) {

        ejbQueueService.send(jaxbHelloWorld);
        return Response.status(Response.Status.OK).build();
    }

}

