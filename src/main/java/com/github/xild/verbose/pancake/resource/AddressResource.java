package com.github.xild.verbose.pancake.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
public class AddressResource {
 
    @POST
    @Path("/OI")
    public Response getAddressByCep() {
        return Response.ok().entity("OK").build();
    }

}