package io.swagger.api.impl;

import db.PGDriver;
import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Votes;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-03T17:38:12.196Z")
public class VotesApiServiceImpl extends VotesApiService {
    @Override
    public Response createVote(Votes body, SecurityContext securityContext) throws NotFoundException {
        // Insert information into database
        try {
            Statement stmt = PGDriver.database.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = String.format("INSERT INTO entries (lobbies, users, selections, vote_count)" +
                    " VALUES('%s', '%s', '%s', %d)" +
                    " ON CONFLICT (lobbies, users, selections)" +
                    " DO UPDATE" +
                    " SET vote_count = EXCLUDED.vote_count;", body.getLobbyName(), body.getUser(),
                    body.getSelection(), body.getCount());

            // If not already set to false, set autocommit to false because it interferes with other commands
            PGDriver.database.setAutoCommit(false);

            stmt.executeUpdate(sql);

            ResultSet rs = stmt.executeQuery("SELECT COUNT(options) FROM selections WHERE lobby_name = '" +
                    body.getLobbyName() + "';");

            int totalOptions = 0;

            while (rs.next()) {
                totalOptions = rs.getInt("count");
            }

            System.out.println(totalOptions);

            rs = stmt.executeQuery(String.format("SELECT COUNT(users) FROM entries WHERE lobbies = " +
                    "'%s' AND users = '%s'", body.getLobbyName(), body.getUser()));

            int userCount = 0;
            rs.beforeFirst();
            while (rs.next()) {
                userCount = rs.getInt("count");
            }

            System.out.println(userCount);

            if (userCount == totalOptions) {
                sql = String.format("INSERT INTO status (lobby, username, has_voted) " +
                        " VALUES ('%s', '%s', '%s')" +
                        " ON CONFLICT (lobby, username)" +
                        " DO UPDATE" +
                        " SET has_voted = EXCLUDED.has_voted" +
                        ";", body.getLobbyName(), body.getUser(), "voted");
                stmt.executeUpdate(sql);
            } else {
                sql = String.format("INSERT INTO status (lobby, username, has_voted) " +
                        " VALUES ('%s', '%s', '%s')" +
                        " ON CONFLICT (lobby, username)" +
                        " DO UPDATE" +
                        " SET has_voted = EXCLUDED.has_voted" +
                        ";", body.getLobbyName(), body.getUser(), "voting");
                stmt.executeUpdate(sql);
            }

            stmt.close();
            PGDriver.database.commit();
        } catch (Exception e) {
            PGDriver.exceptionHandle(e);
        }

        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Voted!")).build();
    }
}
