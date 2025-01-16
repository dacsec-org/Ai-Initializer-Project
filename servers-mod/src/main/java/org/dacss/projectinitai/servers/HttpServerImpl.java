//package org.dacss.projectinitai.servers;
///**/
//
//import com.vaadin.flow.component.notification.Notification;
//import com.sun.net.httpserver.HttpServer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.net.*;
//
///**
// * <h1>{@link HttpServerImpl}</h1>
// * <p>
// * Server that listens on a HTTP port.
// * this is the secondary local server for the application used for downloading files.
// */
//@Component
//public class HttpServerImpl {
//
//    private static final Logger log = LoggerFactory.getLogger(HttpServerImpl.class);
//    private static volatile boolean running = true;
//    private HttpServer server;
//
//    /**
//     * <h1>{@link #startServer()}</h1>
//     * Starts the HTTP server.
//     */
//    public void startServer() {
//        try {
//            server = HttpServer.create(new InetSocketAddress(8080), 0);
//            server.createContext("/echo", new EchoHandler());
//            server.setExecutor(null);
//            server.start();
//            log.info("HTTP Server started");
//
//            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//                running = false;
//                stopServer();
//                Notification.show("Server is shutting down");
//            }));
//        } catch (IOException implServerExc) {
//            log.error("Error starting the HTTP server", implServerExc);
//        }
//    }
//
//    /**
//     * <h1>{@link #stopServer()}</h1>
//     * Stops the HTTP server.
//     */
//    public void stopServer() {
//        if (server != null) {
//            server.stop(0);
//        }
//    }
//
//    /**
//     * <h1>{@link #pingServer()}</h1>
//     * Pings the HTTP server to check if it's up.
//     * @see PingServerUtil#pingServers()
//     */
//    public void pingServer() {
//        try {
//            URI uri = new URI("http://localhost:8080/echo");
//            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
//            connection.setRequestMethod("GET");
//            int responseCode = connection.getResponseCode();
//            if (responseCode == 200) {
//                Notification.show("HTTP Server is up and running");
//            } else {
//                Notification.show("HTTP Server is not responding");
//            }
//        } catch (IOException | URISyntaxException e) {
//            Notification.show(STR."Error pinging the HTTP server: \{e.getMessage()}");
//        }
//    }
//}
