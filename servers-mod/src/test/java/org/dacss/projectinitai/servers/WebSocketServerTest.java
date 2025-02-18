//package org.dacss.projectinitai.servers;
//
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import static org.testng.Assert.*;
//
//public class WebSocketServerTest {
//
//    private WebSocketServer webSocketServer;
//    private final int TEST_PORT = 8080;
//
//    @BeforeMethod
//    public void setUp() {
//        // Initialize the WebSocketServer before each test
//        webSocketServer = new WebSocketServer(TEST_PORT);
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        // Clean up after tests
//        webSocketServer = null;
//    }
//
//    /**
//     * Test 1: Ensure the WebSocket server is constructed successfully.
//     */
//    @Test(priority = 1)
//    public void testConstructWebSocketServer() {
//        assertNotNull(webSocketServer, "The WebSocket server instance should not be null.");
//    }
//
//    /**
//     * Test 2: Verify the server starts without exceptions and uses the correct port.
//     */
//    @Test(priority = 2)
//    public void testStartServer() {
//        try {
//            Thread serverThread = new Thread(() -> {
//                try {
//                    webSocketServer.start();
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            });
//
//            serverThread.start();
//
//            Thread.sleep(500); // Allow time for the server to bind to the port
//
//            // Assert that the port is in use after the server starts
//            assertTrue(isPortInUse(TEST_PORT), "The server port should be in use after starting the server.");
//
//            // Stop the server thread to avoid interference with other tests
//            serverThread.interrupt();
//        } catch (Exception e) {
//            fail("Unexpected exception during testStartServer: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Test 3: Ensure the server handles invalid port numbers gracefully.
//     */
//    @Test(priority = 3)
//    public void testInvalidPortNumber() {
//        try {
//            new WebSocketServer(-1).start();
//            fail("Expected an exception when starting the server with an invalid port.");
//        } catch (IllegalArgumentException e) {
//            assertEquals(e.getMessage(), "Port number must be between 1 and 65535.");
//        } catch (InterruptedException e) {
//            fail("Unexpected InterruptedException: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Test 4: Verify that the server cannot start if the port is already in use.
//     */
//    @Test(priority = 4)
//    public void testPortAlreadyInUse() {
//        try {
//            // Bind the port to an external server socket to simulate a conflict
//            java.net.ServerSocket socket = new java.net.ServerSocket(TEST_PORT);
//
//            Thread.sleep(500); // Ensure the socket fully occupies the port
//
//            // Start the WebSocketServer, which should fail
//            Thread serverThread = new Thread(() -> {
//                try {
//                    webSocketServer.start();
//                } catch (Exception e) {
//                    // Expecting a RuntimeException due to BindException
//                }
//            });
//
//            serverThread.start();
//            Thread.sleep(500); // Wait for the server to attempt binding
//
//            // Verify the WebSocketServer could not bind to the port
//            assertFalse(isPortInUse(TEST_PORT), "The server should not be able to start if the port is already in use.");
//
//            // Cleanup
//            socket.close();
//            serverThread.interrupt();
//        } catch (Exception e) {
//            fail("Unexpected exception during testPortAlreadyInUse: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Test 5: Simulate an interrupted start and verify the server does not start.
//     */
//    @Test(priority = 5)
//    public void testInterruptedStart() {
//        try {
//            Thread serverThread = new Thread(() -> {
//                try {
//                    webSocketServer.start();
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            });
//
//            serverThread.start();
//            serverThread.interrupt(); // Interrupt the thread before it starts
//
//            Thread.sleep(500); // Allow time for the thread to process the interruption
//
//            // Verify the server did not start
//            assertFalse(isPortInUse(TEST_PORT), "The server should not start if the thread is interrupted.");
//        } catch (Exception e) {
//            fail("Unexpected exception during testInterruptedStart: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Helper method to check if a port is in use.
//     *
//     * @return True if the port is currently in use, false otherwise.
//     */
//    private boolean isPortInUse(int TEST_PORT) {
//        int retries = 3; // Retry 3 times with short delays
//        for (int i = 0; i < retries; i++) {
//            try (java.net.ServerSocket socket = new java.net.ServerSocket(8080)) {
//                return false; // If we can bind to the port, it's not in use
//            } catch (java.io.IOException e) {
//                // If bind fails, port is still in use. Add a retry delay.
//                try {
//                    Thread.sleep(200); // Wait before retrying
//                } catch (InterruptedException ex) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        }
//        return true; // If all retries fail, assume the port is in use
//    }
//}
