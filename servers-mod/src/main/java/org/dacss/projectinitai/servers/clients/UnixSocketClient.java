//package org.dacss.projectinitai.servers.clients;
//
//import com.vaadin.flow.component.notification.Notification;
//import org.springframework.stereotype.Component;
//
//import java.io.*;
//import java.net.*;
//import java.nio.file.*;
//
//@Component
//public class UnixSocketClient {
//
//    public void connectToServer() {
//        Path socketPath = Paths.get("/tmp/unix_socket");
//        try (Socket socket = new Socket()) {
//            socket.connect(UnixDomainSocketAddress.of(socketPath));
//            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            Notification.show("Connected to the server");
//            Notification.show("Server response: " + in.readLine());
//        } catch (IOException e) {
//            Notification.show("Error connecting to the server: " + e.getMessage());
//        }
//    }
//}
