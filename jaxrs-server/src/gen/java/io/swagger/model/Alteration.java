package io.swagger.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-03T17:38:12.196Z")
public class Alteration {
    @JsonProperty("lobbyName")
    private String lobbyName = null;

    @JsonProperty("option")
    private String option = null;

    @JsonProperty("replacement")
    private String replacement = null;

    public Alteration lobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
        return this;
    }

    @JsonProperty("lobbyName")
    @ApiModelProperty(value = "")
    public String getLobbyName() { return lobbyName; }

    public void setLobbyName(String lobbyName) { this.lobbyName = lobbyName; }

    public Alteration option(String option) {
        this.option = option;
        return this;
    }

    @JsonProperty("option")
    @ApiModelProperty(value = "")
    public String getOption() { return option; }

    public void setOption(String option) { this.option = option; }

    @JsonProperty("replacement")
    @ApiModelProperty(value = "")
    public String getReplacement() { return replacement; }

    public void setReplacement(String replacement) { this.replacement = replacement; }
}
