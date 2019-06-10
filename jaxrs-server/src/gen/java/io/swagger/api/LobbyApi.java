package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.LobbyApiService;
import io.swagger.api.factories.LobbyApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Selection;

import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/lobby")


@io.swagger.annotations.Api(description = "the lobby API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-07T18:48:14.778Z")
public class LobbyApi  {
    private final LobbyApiService delegate;

    public LobbyApi(@Context ServletConfig servletContext) {
        LobbyApiService delegate = null;

        if (servletContext != null) {
            String implClass = servletContext.getInitParameter("LobbyApi.implementation");
            if (implClass != null && !"".equals(implClass.trim())) {
                try {
                    delegate = (LobbyApiService) Class.forName(implClass).newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (delegate == null) {
            delegate = LobbyApiServiceFactory.getLobbyApi();
        }

        this.delegate = delegate;
    }

    @POST
    @Path("/create")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Creates a new lobby", notes = "Creates a new lobby", response = Void.class, tags={ "lobby", })
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "", response = Void.class) })
    public Response createLobby(@ApiParam(value = "Information about the lobby" ,required=true) Selection body
            ,@Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.createLobby(body,securityContext);
    }
    @PUT
    @Path("/{lobbyName}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Edits a currently existing lobby", notes = "Edits a currently existing lobby", response = Void.class, tags={ "lobby", })
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Operation successful", response = Void.class) })
    public Response editLobby(@ApiParam(value = "Name of lobby to edit",required=true) @PathParam("lobbyName") String lobbyName
            ,@ApiParam(value = "What needs to be edited in the lobby" ,required=true) Selection body
            ,@Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.editLobby(lobbyName,body,securityContext);
    }
    @GET
    @Path("/list")

    @Produces({ "appliation/json" })
    @io.swagger.annotations.ApiOperation(value = "List all lobbies", notes = "", response = String.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Operation successful", response = String.class, responseContainer = "List"),

            @io.swagger.annotations.ApiResponse(code = 404, message = "Lobby name not found", response = Void.class) })
    public Response listLobbies(@ApiParam(value = "Filter by lobby name") @QueryParam("lobbyName") String lobbyName
            , @Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.listLobbies(lobbyName,securityContext);
    }
    @GET
    @Path("/test")

    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "test", notes = "test", response = Void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Operation successful", response = Void.class) })
    public Response lobbyTest(@Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.lobbyTest(securityContext);
    }
}
