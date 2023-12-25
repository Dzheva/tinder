package application;

import application.constants.Endpoint;
import application.filters.GlobalFilter;
import application.servlets.*;
import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.EnumSet;

public class WebServer {
    private final Server server;

    public WebServer(String host, int port) {
        server = new Server();
        server.addConnector(createConnector(host, port));
        server.setHandler(createHandler());
    }

    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private ServletContextHandler createHandler() {
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        contextHandler.addFilter(
                new FilterHolder(new GlobalFilter()), "/*", EnumSet.of(DispatcherType.REQUEST));
        contextHandler.addServlet(createResourcesHolder(), "/static/*");
        contextHandler.addServlet(new ServletHolder(new Index()), Endpoint.INDEX);
        contextHandler.addServlet(new ServletHolder(new Login()), Endpoint.LOGIN);
        contextHandler.addServlet(new ServletHolder(new Users()), Endpoint.USERS);
        contextHandler.addServlet(new ServletHolder(new Likes()), Endpoint.LIKES);
        contextHandler.addServlet(new ServletHolder(new ChatServlet()), Endpoint.CHAT);
        return contextHandler;
    }

    private ServerConnector createConnector(String host, int port) {
        ServerConnector connector = new ServerConnector(server);
        connector.setHost(host);
        connector.setPort(port);
        return connector;
    }

    private ServletHolder createResourcesHolder() {
        ServletHolder resourcesHolder = new ServletHolder("default", new DefaultServlet());
        resourcesHolder.setInitParameter("resourceBase", "src/main/resources");
        resourcesHolder.setInitParameter("dirAllowed", "false");
        return resourcesHolder;
    }
}
