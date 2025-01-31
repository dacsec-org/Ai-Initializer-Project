package org.dacss.projectinitai.servers.utillities;
/**/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link StopUnixServerUtil}</h1>
 * Utility class for stopping the Unix Socket server.
 */
@Component
public final class StopUnixServerUtil {

    private static final Logger logger = LoggerFactory.getLogger(StopUnixServerUtil.class);
    private static final Path SOCKET_PATH = Paths.get("/tmp/unix_socket");
    //todo: fix the path, 'temp' is not a place for for socket files

    /**
     * <h1>{@link StopUnixServerUtil}</h1>
     * 0-arg constructor.
     */
    private StopUnixServerUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * <h1>{@link #stopServer()}</h1>
     * Stops the Unix Socket server.
     */
    public static void stopServer() {
        try {
            if (Files.exists(SOCKET_PATH)) {
                Files.delete(SOCKET_PATH);
                logger.info("Server stopped and socket file deleted: {}", SOCKET_PATH);
            } else {
                logger.warn("Socket file does not exist: {}", SOCKET_PATH);
            }
        } catch (IOException stopUnixSocketExc) {
            logger.error("Error stopping the server: {}", stopUnixSocketExc.getMessage());
        }
    }
}
