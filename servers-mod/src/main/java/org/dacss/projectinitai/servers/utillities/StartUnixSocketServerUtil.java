package org.dacss.projectinitai.servers.utillities;
/**/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnixDomainSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h1>{@link StartUnixSocketServerUtil}</h1>
 * Server that listens on a Unix Domain Socket.
 * This is the default local server for the application.
 */
public final class StartUnixSocketServerUtil {

    private static final Logger logger = LoggerFactory.getLogger(StartUnixSocketServerUtil.class);
    private static final Path SOCKET_PATH = Paths.get("/tmp/unix_socket");
    private static volatile boolean running = true;

    private StartUnixSocketServerUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * <h1>{@link #startServer()}</h1>
     * Starts the Unix Socket server.
     */
    public static void startServer() {
        try {
            if (Files.exists(SOCKET_PATH)) {
                Files.delete(SOCKET_PATH);
            }
            try (ServerSocket serverSocket = new ServerSocket()) {
                serverSocket.bind(UnixDomainSocketAddress.of(SOCKET_PATH));
                logger.info("Server started on {}", SOCKET_PATH);

                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    running = false;
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        logger.error("Error closing the server socket: {}", e.getMessage());
                    }
                }));

                while (running) {
                    try (Socket clientSocket = serverSocket.accept()) {
                        // Handle client connection
                        logger.info("Client connected: {}", clientSocket.getRemoteSocketAddress());
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error starting the server: {}", e.getMessage());
        }
    }

    /**
     * <h1>{@link #pingServer()}</h1>
     * Pings the Unix Socket server to check if it is up.
     */
    public static void pingServer() {
        try (Socket socket = new Socket()) {
            socket.connect(UnixDomainSocketAddress.of(SOCKET_PATH));
            logger.info("Unix Socket server is up and running on {}", SOCKET_PATH);
        } catch (IOException e) {
            logger.warn("Failed to ping Unix Socket server on {}: {}", SOCKET_PATH, e.getMessage());
        }
    }
}
