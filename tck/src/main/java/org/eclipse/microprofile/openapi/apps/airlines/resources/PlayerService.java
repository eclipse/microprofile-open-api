package org.eclipse.microprofile.openapi.apps.airlines.resources;

import javax.enterprise.context.Dependent;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Dependent
@RegisterRestClient
@Path("/")
public interface PlayerService {

    @GET
    @Path("/player/{playerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPlayerById(@PathParam("playerId") String id);

    @POST
    @Path("/rank/{playerId}/recordGame")
    public void recordGame(@PathParam("playerId") String id, @QueryParam("place") int place, @HeaderParam("Authorization") String token);

}
