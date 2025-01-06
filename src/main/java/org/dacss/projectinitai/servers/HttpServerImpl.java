package org.dacss.projectinitai.servers;

import com.vaadin.flow.component.notification.Notification;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerImpl {

    private static volatile boolean running = true;
    private HttpServer server;

    public void startServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/echo", new EchoHandler());
            server.setExecutor(null);
            server.start();
            Notification.show("Server started on port 8080");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                running = false;
                stopServer();
                Notification.show("Server is shutting down");
            }));
        } catch (IOException e) {
            Notification.show("Error starting the server: " + e.getMessage());
        }
    }

    public void stopServer() {
        if (server != null) {
            server.stop(0);
        }
    }
}
