package com.tomee.helloworld.resource;

import com.tomee.helloworld.bean.Person;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Created by valerie on 12/28/15.
 */
@Path("/hi")
public class HelloWorldResource {
    private static final Logger logger = Logger.getLogger(HelloWorldResource.class.getName());


    @GET
    @Path("/ping/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping(@PathParam("name") String name) {
        return "pong " + name;
    }

    @Path("/xml")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String postXml(Person person) {

        logger.info(person.toString());

        return person.toString();
    }

    @Path("/json")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postJson(Person person) {

        logger.info(person.toString());

        return person.toString();
    }

}

