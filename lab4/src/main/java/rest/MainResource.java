package rest;

import jakarta.ejb.EJB;
import jakarta.json.Json;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rest.filters.authorization.Authorized;

import rest.json.ResultData;
import services.results.ResultService;
import exceptions.UserNotFoundException;
import beans.Result;

@Path("/results")
@Authorized
@Produces(MediaType.APPLICATION_JSON)
public class MainResource {
    @EJB
    private ResultService resultService;

    // since token is valid we can assume that user from token exists

    @GET
    public Response getResultsData(@Context HttpHeaders headers) {
        String username = headers.getHeaderString("username");
        return Response.ok(resultService.getAllJSON(username)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addResult(@Context HttpHeaders headers, @Valid ResultData resultData) {
        String username = headers.getHeaderString("username");
        Result result = new Result(resultData.getX(), resultData.getY(), resultData.getR());
        try {
            resultService.add(result, username);
            return Response.ok(jsonMessage("result added (owner is " + username + ")")).build();
        } catch (UserNotFoundException e) {
            return Response.serverError().entity("User not found despite having a valid token").build();
        }
    }

    @DELETE
    public Response clear(@Context HttpHeaders headers) {
        String username = headers.getHeaderString("username");
        resultService.clear(username);
        return Response.ok().build();
    }

    private String jsonMessage(String message) {
        return Json.createObjectBuilder().add("message", message).build().toString();
    }
}
