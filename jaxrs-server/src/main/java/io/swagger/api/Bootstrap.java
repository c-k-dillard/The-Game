package io.swagger.api;

import db.PGDriver;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.*;

import io.swagger.models.auth.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.sql.SQLException;

public class Bootstrap extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        Info info = new Info()
                .title("Swagger Server")
                .description("This is a sample server Petstore server.  You can find out more about     Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).      For this sample, you can use the api key `special-key` to test the authorization     filters.")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact()
                        .email("apiteam@swagger.io"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));

        ServletContext context = config.getServletContext();
        Swagger swagger = new Swagger().info(info);

        new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);

        // Open postgres connection
        PGDriver.database =
                PGDriver.establishConnection("cdillard", "cdillard", "asdf");

        PGDriver.executeStmt(PGDriver.database, PGDriver.dropEntries, "Drop entries");
        PGDriver.executeStmt(PGDriver.database, PGDriver.createEntries, "Create entries");
        PGDriver.executeStmt(PGDriver.database, PGDriver.dropSelections, "Drop options");
        PGDriver.executeStmt(PGDriver.database, PGDriver.createSelections, "Create options");
    }
}
