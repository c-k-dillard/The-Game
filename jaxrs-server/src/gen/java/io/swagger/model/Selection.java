/*
 * Swagger Petstore
 * This is a sample server Petstore server.  You can find out more about     Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).      For this sample, you can use the api key `special-key` to test the authorization     filters.
 *
 * OpenAPI spec version: 1.0.0
 * Contact: apiteam@swagger.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * Selection
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-03T17:38:12.196Z")
public class Selection {
    @JsonProperty("lobbyName")
    private String lobbyName = null;

    @JsonProperty("options")
    private List<String> options = null;

    public Selection lobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
        return this;
    }

    /**
     * Get lobbyName
     *
     * @return lobbyName
     **/
    @JsonProperty("lobbyName")
    @ApiModelProperty(value = "")
    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public Selection options(List<String> options) {
        this.options = options;
        return this;
    }

    public Selection addOptionsItem(String optionsItem) {
        if (this.options == null) {
            this.options = new ArrayList<String>();
        }
        this.options.add(optionsItem);
        return this;
    }

    /**
     * Get options
     *
     * @return options
     **/
    @JsonProperty("options")
    @ApiModelProperty(value = "")
    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Selection selection = (Selection) o;
        return Objects.equals(this.lobbyName, selection.lobbyName) &&
                Objects.equals(this.options, selection.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lobbyName, options);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Selection {\n");

        sb.append("    lobbyName: ").append(toIndentedString(lobbyName)).append("\n");
        sb.append("    options: ").append(toIndentedString(options)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

