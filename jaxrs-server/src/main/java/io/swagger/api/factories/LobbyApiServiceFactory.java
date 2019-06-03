package io.swagger.api.factories;

import io.swagger.api.LobbyApiService;
import io.swagger.api.impl.LobbyApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-03T17:38:12.196Z")
public class LobbyApiServiceFactory {
    private final static LobbyApiService service = new LobbyApiServiceImpl();

    public static LobbyApiService getLobbyApi() {
        return service;
    }
}
