package io.swagger.api.impl;

import db.PGDriver;

import io.swagger.annotations.ApiParam;
import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Selection;

import java.sql.Statement;
import java.util.List;

import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.json.simple.JSONArray;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-03T17:38:12.196Z")
public class LobbyApiServiceImpl extends LobbyApiService {
    @Override
    public Response createLobby(Selection body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        System.out.println(body);

        // Generate SQL Statement(s)
        try {
            PGDriver.database.setAutoCommit(false);
            Statement stmt = PGDriver.database.createStatement();

            for (String option : body.getOptions()) {
                String sql = String.format("INSERT INTO selections (lobby_name, options)" +
                        "VALUES ('%s', '%s');", body.getLobbyName(), option);
                stmt.executeUpdate(sql);
            }

            // Close statement and commit all SQL statements to database.
            stmt.close();
            PGDriver.database.commit();
        } catch (Exception e) {
            PGDriver.exceptionHandle(e);
        }

        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "created!")).build();
    }

    @Override
    public Response editLobby(String lobbyName, Selection body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response getLobby(String lobbyName, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response listLobbies(@QueryParam("lobbyName") String lobbyName, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        System.out.println("Check2");
        JSONArray json = new JSONArray();
        json.add(lobbyName);
        return Response.ok().entity(json.toJSONString()).build();
    }

    @Override
    public Response lobbyTest(SecurityContext securityContext) throws NotFoundException {
        // do some testing!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "test!")).build();
    }
}
