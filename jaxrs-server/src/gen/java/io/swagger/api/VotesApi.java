package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.VotesApiService;
import io.swagger.api.factories.VotesApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Votes;

import java.util.Map;
import java.util.List;

import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/votes")


@io.swagger.annotations.Api(description = "the votes API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-03T17:38:12.196Z")
public class VotesApi {
    private final VotesApiService delegate;

    public VotesApi(@Context ServletConfig servletContext) {
        VotesApiService delegate = null;

        if (servletContext != null) {
            String implClass = servletContext.getInitParameter("VotesApi.implementation");
            if (implClass != null && !"".equals(implClass.trim())) {
                try {
                    delegate = (VotesApiService) Class.forName(implClass).newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (delegate == null) {
            delegate = VotesApiServiceFactory.getVotesApi();
        }

        this.delegate = delegate;
    }

    @POST
    @Path("/submit")

    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Send votes to backend", notes = "", response = Void.class, tags = {"votes",})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Operation successful", response = Void.class)})
    public Response createVote(@ApiParam(value = "Created votes object", required = true) Votes body
            , @Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.createVote(body, securityContext);
    }
}
