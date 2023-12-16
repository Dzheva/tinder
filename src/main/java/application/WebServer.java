package application;

import application.servlets.Index;
import application.servlets.LikeUsersServlet;
import application.servlets.Login;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

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
        contextHandler.addServlet(createResourcesHolder(), "/static/*");
        contextHandler.setContextPath("/");
        contextHandler.addServlet(new ServletHolder(new Index()), "/");
        contextHandler.addServlet(new ServletHolder(new Login()), "/login");
        contextHandler.addServlet(new ServletHolder(new LikeUsersServlet()), "/users");

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
