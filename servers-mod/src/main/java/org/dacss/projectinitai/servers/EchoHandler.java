//package org.dacss.projectinitai.servers;
///**/
//import com.vaadin.flow.component.notification.Notification;
//import com.sun.net.httpserver.HttpHandler;
//import com.sun.net.httpserver.HttpExchange;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.nio.charset.StandardCharsets;
//
///**
// * <h1>{@link EchoHandler}</h1>
// */
//public class EchoHandler implements HttpHandler {
//
//    private static final Logger log = LoggerFactory.getLogger(EchoHandler.class);
//
//    /**
//     * {@link #handle(HttpExchange)}
//     * <p>
//     *     This method is used to handle the client request.
//     * </p>
//     */
//    @Override
//    public void handle(HttpExchange exchange) throws IOException {
//        if ("POST".equals(exchange.getRequestMethod())) {
//            InputStream is = exchange.getRequestBody();
//            String requestBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);
//            log.info("Echo: {}", requestBody);
//
//            String response = STR."Echo: \{requestBody}";
//            exchange.sendResponseHeaders(200, response.getBytes().length);
//            OutputStream os = exchange.getResponseBody();
//            os.write(response.getBytes());
//            os.close();
//        } else {
//            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
//        }
//    }
//}
