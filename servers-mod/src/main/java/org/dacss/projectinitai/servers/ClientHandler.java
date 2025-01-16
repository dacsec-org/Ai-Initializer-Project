//package org.dacss.projectinitai.servers;
///**/
//
//import com.vaadin.flow.component.notification.Notification;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//
///**
// * <h1>{@link ClientHandler}</h1>
// */
//class ClientHandler implements Runnable {
//
//    private static final Logger log = LoggerFactory.getLogger(ClientHandler.class);
//    private Socket clientSocket;
//
//    /**
//     * {@link #ClientHandler(Socket)}
//     *
//     * @param socket
//     */
//    public ClientHandler(Socket socket) {
//        this.clientSocket = socket;
//    }
//
//
//    /**
//     * {@link #run()}
//     * <p>
//     *     This method is used to handle the client request.
//     * </p>
//     */
//    @Override
//    public void run() {
//        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                log.info("Echo: {}", inputLine);
//                out.println(STR."Echo: \{inputLine}");
//            }
//        } catch (IOException handleClientExc) {
//            log.error("Error handling client", handleClientExc);
//        }
//    }
//}
