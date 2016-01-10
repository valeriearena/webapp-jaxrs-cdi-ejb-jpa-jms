package com.tomee.helloworld.resource;

import com.tomee.helloworld.bean.Greeting;
import com.tomee.helloworld.service.PojoService;
import com.tomee.helloworld.service.SingletonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by valerie on 12/28/15.
 */
@Path("/hello")
public class HelloWorldResource {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldResource.class);

    @Inject
    private SingletonService singletonService;

    @Inject
    private PojoService pojoService;

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
    public String jaxRs(Greeting greeting) {

        return greeting.toString();
    }

    @GET
    @Path("/singleton")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSingleton() {

        return singletonService.getGreeting();
    }

    @POST
    @Path("/singleton/{greeting}")
    @Produces(MediaType.TEXT_PLAIN)
    public String postSingleton(@PathParam("greeting") String greeting) {

        singletonService.setGreeting(greeting);
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
}

