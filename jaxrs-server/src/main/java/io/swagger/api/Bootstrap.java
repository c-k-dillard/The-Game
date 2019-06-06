package io.swagger.api;

import db.PGDriver;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.*;

import io.swagger.models.auth.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

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
//    PGDriver.init(PGDriver.database, "cdillard", "cdillard", "asdf");
        PGDriver.database =
                PGDriver.establishConnection(PGDriver.database, "cdillard", "cdillard", "asdf");

        PGDriver.executeStmt(PGDriver.database, PGDriver.drop, "Drop");
        PGDriver.executeStmt(PGDriver.database, PGDriver.create, "Create");
    }
}
