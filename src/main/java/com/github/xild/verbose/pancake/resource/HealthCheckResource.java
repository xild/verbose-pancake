package com.github.xild.verbose.pancake.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
public class HealthCheckResource {
 
    public enum HealthcheckResponse {
        LIVE, DEAD
    }

    @GET
    @Path("/healthcheck")
    public Response isHealthy() {
        return Response.ok().entity(HealthcheckResponse.LIVE).build();
    }

    @GET
    @Path("/check/status")
    public Response healthCheckSimple() {
        return isHealthy();
    }

}