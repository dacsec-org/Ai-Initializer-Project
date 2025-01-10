package org.dacss.projectinitai.chats;

import org.dacss.projectinitai.servers.clients.UnixSocketClient;
import org.dacss.projectinitai.servers.UnixSocketServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandLineClient {

    private final UnixSocketServer unixSocketServer;
    private final UnixSocketClient unixSocketClient;

    @Autowired
    public CommandLineClient(UnixSocketServer unixSocketServer, UnixSocketClient unixSocketClient) {
        this.unixSocketServer = unixSocketServer;
        this.unixSocketClient = unixSocketClient;
    }

    public void startServer() {
        unixSocketServer.startServer();
    }

    public void connectToServer() {
        unixSocketClient.connectToServer();
    }
}
