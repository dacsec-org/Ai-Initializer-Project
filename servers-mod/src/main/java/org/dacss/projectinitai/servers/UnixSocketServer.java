//package org.dacss.projectinitai.servers;
//
//import com.vaadin.flow.component.notification.Notification;
//import org.dacss.projectinitai.utilities.PingServerUtil;
//import org.springframework.stereotype.Component;
//
//import java.io.*;
//import java.net.*;
//import java.nio.file.*;
//
///**
// * <h1>{@link UnixSocketServer}</h1>
// * Server that listens on a Unix Domain Socket.
// * this is the default local server for the application.
// */
//@Component
//public class UnixSocketServer {
//    private static volatile boolean running = true;
//
//    /**
//     * <h1>{@link #startServer()}</h1>
//     * Starts the Unix Socket server.
//     */
//    public void startServer() {
//        Path socketPath = Paths.get("/tmp/unix_socket");
//        try {
//            if (Files.exists(socketPath)) {
//                Files.delete(socketPath);
//            }
//            try (ServerSocket serverSocket = new ServerSocket()) {
//                serverSocket.bind(UnixDomainSocketAddress.of(socketPath));
//                Notification.show("Server started on " + socketPath);
//
//                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//                    running = false;
//                    try {
//                        serverSocket.close();
//                    } catch (IOException e) {
//                        Notification.show("Error closing the server socket: " + e.getMessage());
//                    }
//                }));
//
//                while (running) {
//                    try {
//                        Socket clientSocket = serverSocket.accept();
//                        new Thread(new ClientHandler(clientSocket)).start();
//                    } catch (SocketException e) {
//                        if (!running) {
//                            Notification.show("Server is shutting down");
//                        } else {
//                            throw e;
//                        }
//                    }
//                }
//            }
//        } catch (IOException e) {
//            Notification.show("Error starting the server: " + e.getMessage());
//        }
//    }
//
//    /**
//     * <h1>{@link #pingServer()}</h1>
//     * Pings the Unix Socket server to check if it's up.
//     * @see PingServerUtil#pingServers()
//     */
//    public void pingServer() {
//        Path socketPath = Paths.get("/tmp/unix_socket");
//        try (Socket socket = new Socket()) {
//            socket.connect(UnixDomainSocketAddress.of(socketPath), 1000);
//            Notification.show("Unix Socket Server is up and running");
//        } catch (IOException e) {
//            Notification.show("Error pinging the Unix Socket server: " + e.getMessage());
//        }
//    }
//}
