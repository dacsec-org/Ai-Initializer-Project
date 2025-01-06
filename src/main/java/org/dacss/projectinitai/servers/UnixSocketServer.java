package org.dacss.projectinitai.servers;

import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;
import java.nio.file.*;

@Component
public class UnixSocketServer {
    private static volatile boolean running = true;

    public void startServer() {
        Path socketPath = Paths.get("/tmp/unix_socket");
        try {
            if (Files.exists(socketPath)) {
                Files.delete(socketPath);
            }
            try (ServerSocket serverSocket = new ServerSocket()) {
                serverSocket.bind(UnixDomainSocketAddress.of(socketPath));
                Notification.show("Server started on " + socketPath);

                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    running = false;
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        Notification.show("Error closing the server socket: " + e.getMessage());
                    }
                }));

                while (running) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        new Thread(new ClientHandler(clientSocket)).start();
                    } catch (SocketException e) {
                        if (!running) {
                            Notification.show("Server is shutting down");
                        } else {
                            throw e;
                        }
                    }
                }
            }
        } catch (IOException e) {
            Notification.show("Error starting the server: " + e.getMessage());
        }
    }
}
