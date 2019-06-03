package io.swagger.api.factories;

import io.swagger.api.VotesApiService;
import io.swagger.api.impl.VotesApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-03T17:38:12.196Z")
public class VotesApiServiceFactory {
    private final static VotesApiService service = new VotesApiServiceImpl();

    public static VotesApiService getVotesApi() {
        return service;
    }
}
