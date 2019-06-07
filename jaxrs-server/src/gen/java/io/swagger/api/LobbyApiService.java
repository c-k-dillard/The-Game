package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Selection;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-03T17:38:12.196Z")
public abstract class LobbyApiService {
    public abstract Response createLobby(Selection body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response editLobby(Selection body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getLobby(String lobbyName,SecurityContext securityContext) throws NotFoundException;
    public abstract Response listLobbies(String lobbyName,SecurityContext securityContext) throws NotFoundException;
    public abstract Response lobbyTest(SecurityContext securityContext) throws NotFoundException;
}
