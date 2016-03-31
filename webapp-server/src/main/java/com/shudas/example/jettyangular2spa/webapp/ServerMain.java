package com.shudas.example.jettyangular2spa.webapp;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.nio.file.Paths;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        String WEB_APP = Paths.get(ServerMain.class.getResource("/webapp").toURI()).toFile().getPath();
        String WEB_XML = Paths.get(ServerMain.class.getResource("/webapp/WEB-INF/web.xml").toURI()).toFile().getPath();
        webapp.setDescriptor(WEB_XML);
        webapp.setResourceBase(WEB_APP);
        server.setHandler(webapp);

        server.start();
        server.join();
    }
}