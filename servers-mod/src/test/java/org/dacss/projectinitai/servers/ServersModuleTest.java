package org.dacss.projectinitai.servers;
/**/
import org.dacss.projectinitai.servers.utillities.*;
/**/
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * <h1>{@link ServersModuleTest}</h1>
 * Test class for servers-mod module.
 * <p>
 * Methods under test:
 * <ul>
 *     <li>{@link #testStartUnixSocketServer()}</li>
 *     <li>{@link #testPingUnixSocketServer()}</li>
 *     <li>{@link #testStopUnixSocketServer()}</li>
 *     <li>{@link #testRestartUnixSocketServer()}</li>
 *     <li>{@link #testStopHttpServer()}</li>
 * </ul>
 * </p>
 */
public class ServersModuleTest {

    private Path socketPath;
    private ServersHandler serversHandler;

    @BeforeMethod
    public void setUp() throws IOException {
        socketPath = Paths.get("/tmp/unix_socket");
        serversHandler = new ServersHandler();
        if (Files.exists(socketPath)) {
            Files.delete(socketPath);
        }
    }

    @AfterSuite
    public void tearDown() throws IOException {
        if (Files.exists(socketPath)) {
            Files.delete(socketPath);
        }
    }

    @Test
    public void testStartUnixSocketServer() {
        StartUnixSocketServerUtil.startServer();
        assertTrue(Files.exists(socketPath), "Unix socket should be created");
        System.out.println(STR."Test 'startServer()' passed: \{socketPath}");
    }

    @Test(dependsOnMethods = "testStartUnixSocketServer")
    public void testPingUnixSocketServer() {
        StartUnixSocketServerUtil.pingServer();
        System.out.println(STR."Test 'pingServer()' passed: \{socketPath}");
    }

    @Test(dependsOnMethods = "testPingUnixSocketServer")
    public void testStopUnixSocketServer() {
        StopUnixServerUtil.stopServer();
        assertFalse(Files.exists(socketPath), "Unix socket should be deleted");
        System.out.println(STR."Test 'stopServer()' passed: \{socketPath}");
    }

    @Test(dependsOnMethods = "testStopUnixSocketServer")
    public void testRestartUnixSocketServer() {
        RestartServersUtil.restartServer();
        assertTrue(Files.exists(socketPath), "Unix socket should be recreated");
        System.out.println(STR."Test 'restartServer()' passed: \{socketPath}");
    }

    @Test
    public void testStopHttpServer() {
        serversHandler.stopHttpServer();
        System.out.println("Test 'stopHttpServer()' passed");
    }

    @Test
    public void testPingServers() {
        PingServerUtil.pingServers();
        System.out.println("Test 'pingServers()' passed");
    }
}
