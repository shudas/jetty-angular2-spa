package com.shudas.example.jettyangular2spa.health.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Simple Class to show us receiving HTTP requests.
 */
@Singleton
@Path("health")
public class HealthResource {
    private static final Logger log = LoggerFactory.getLogger(HealthResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/ping")
    public String ping() {
        return "Success!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/fail")
    public String fail() {
        log.debug("Failing on purpose because that is what this endpoint does.");
        throw new WebApplicationException("This endpoint fails on purpose", Response.Status.SERVICE_UNAVAILABLE);
    }
}
