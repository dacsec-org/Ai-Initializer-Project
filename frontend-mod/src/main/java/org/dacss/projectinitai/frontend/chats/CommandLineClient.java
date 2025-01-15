//package org.dacss.projectinitai.frontend.chats;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CommandLineClient {
//
//    private final UnixSocketServer unixSocketServer;
//    private final UnixSocketClient unixSocketClient;
//
//    @Autowired
//    public CommandLineClient(UnixSocketServer unixSocketServer, UnixSocketClient unixSocketClient) {
//        this.unixSocketServer = unixSocketServer;
//        this.unixSocketClient = unixSocketClient;
//    }
//
//    public void startServer() {
//        unixSocketServer.startServer();
//    }
//
//    public void connectToServer() {
//        unixSocketClient.connectToServer();
//    }
//}
