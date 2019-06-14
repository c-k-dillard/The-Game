package io.swagger.api.impl;

import db.PGDriver;

import io.swagger.annotations.ApiParam;
import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Selection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.swagger.api.NotFoundException;

import java.io.InputStream;
import java.util.Map;

import io.swagger.util.Json;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
    public Response removeFromLobby(String lobbyName, Alteration body, SecurityContext securityContext) throws NotFoundException {
        try {
            // Remove row from database
            Statement stmt = PGDriver.database.createStatement();
            PGDriver.database.setAutoCommit(false);
            String sql = String.format("DELETE FROM selections " +
                    "WHERE lobby_name = '%s' AND options = '%s'",
                    lobbyName, body.getOption());

            // Close statement and execute sql
            stmt.executeUpdate(sql);
            stmt.close();
            PGDriver.database.commit();
        } catch (Exception e) {
            PGDriver.exceptionHandle(e);
        }

        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "deleted!")).build();
    }

    @Override
    public Response addToLobby(String lobbyName, Alteration body, SecurityContext securityContext) throws NotFoundException {
        try {
            // Insert statement addition to lobby selection
            Statement stmt = PGDriver.database.createStatement();
            PGDriver.database.setAutoCommit(false);

            String sql = String.format("INSERT INTO selections (lobby_name, options)" +
                    "VALUES ('%s', '%s');", lobbyName, body.getOption());
            stmt.executeUpdate(sql);

            // Close statement and commit
            stmt.close();
            PGDriver.database.commit();
        } catch (Exception e) {
            PGDriver.exceptionHandle(e);
        }

        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response editLobby(String lobbyName, Alteration body, SecurityContext securityContext) throws NotFoundException {
        try {
            Statement stmt = PGDriver.database.createStatement();
            PGDriver.database.setAutoCommit(false);

            String sql = String.format("UPDATE selections " +
                    "SET options = '%s' " +
                    "WHERE options = '%s' AND lobby_name = '%s'",
                    body.getReplacement(), body.getOption(), lobbyName);

            stmt.executeUpdate(sql);

            // Close statement and commit
            stmt.close();
            PGDriver.database.commit();
        } catch (Exception e) {
            PGDriver.exceptionHandle(e);
        }

        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "edited!")).build();
    }

    @Override
    public Response getLobby(String lobbyName, SecurityContext securityContext) throws NotFoundException {
        JSONArray json = new JSONArray();

        // Query database for entries from lobby lobbyName
        try {
            Statement stmt = PGDriver.database.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT options FROM SELECTIONS WHERE " +
                    "lobby_name = '" + lobbyName + "'");

            while (rs.next()) {
                json.add(rs.getString("options"));
            }
        } catch (Exception e) {
            PGDriver.exceptionHandle(e);
        }
        return Response.ok().entity(json).build();
    }

    @Override
    public Response listLobbies(@QueryParam("lobbyName") String lobbyName, SecurityContext securityContext) throws NotFoundException {
        // Create json array
        System.out.println("Check2");
        JSONArray json = new JSONArray();

        try {
            Statement stmt = PGDriver.database.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT lobby_name FROM selections " +
                    "WHERE lobby_name LIKE" +
                    " '%" + lobbyName + "%' ORDER BY lobby_name ASC;");

            while (rs.next()) {
                json.add(rs.getString("lobby_name"));
            }
        } catch (Exception e) {
            PGDriver.exceptionHandle(e);
        }

        return Response.ok().entity(json.toJSONString()).build();
    }

    @Override
    public Response printVotesInLobby(String lobbyName, SecurityContext securityContext) throws NotFoundException {
        // Print all voting information
//        List<FinalVote> selection = new ArrayList<FinalVote>();
        JSONObject voteMap = new JSONObject();

        try {
            Statement stmt = PGDriver.database.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            PGDriver.database.setAutoCommit(false);

            // Create list of selections
//            ResultSet rs = stmt.executeQuery("SELECT options FROM selections WHERE lobby_name = '" +
//                    lobbyName + "';");
//
//            while (rs.next()) {
//                selection.add(new FinalVote(rs.getString("options")));
//            }

            // Use selections list to establish voting information
            ResultSet rs = stmt.executeQuery("SELECT users, selections, vote_count from entries WHERE " +
                    "lobbies = '" + lobbyName + "';");

            while (rs.next()) {
                String select = rs.getString("selections");
                int voteCount = rs.getInt("vote_count");
//                for (int i = 0; i < selection.size(); ++i) {
//
//                    if (rs.getString("selections").equals(selection.get(i).selection)) {
//                        selection.get(i).vote += rs.getInt("vote_count");
//                    }
//                }

                if (voteMap.containsKey(select)) {
                    int currentCount = (int)voteMap.get(select);
                    voteMap.put(select, currentCount + voteCount);
                } else {
                    voteMap.put(select, voteCount);
                }
            }

//            for (int i = 0; i < selection.size(); ++i) {
//                JSONObject j = new JSONObject();
//                j.put("selection", selection.get(i).selection);
//                j.put("vote", selection.get(i).vote);
//                ja.add(j);
//
//            }
        } catch (Exception e) {
            PGDriver.exceptionHandle(e);
        }

        return Response.ok().entity(voteMap.toJSONString()).build();
    }
}
