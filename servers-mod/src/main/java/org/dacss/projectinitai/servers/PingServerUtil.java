//package org.dacss.projectinitai.servers;
///**/
//
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
///**
// * <h1>{@link PingServerUtil}</h1>
// * <p>
// * Utility class for pinging the local servers to check if there up,
// * this also keeps the servers from going to sleep.
// * </p>
// */
//public class PingServerUtil {
//
//    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//    UnixSocketServer unixSocketServer;
//    HttpServerImpl httpServerImpl;
//
//    /**
//     * <h1>{@link #pingServers}</h1>
//     * Pings the servers to keep them from going to sleep.
//     *
//     * @see UnixSocketServer#pingServer()
//     * @see HttpServerImpl#pingServer()
//     */
//    public void pingServers() {
//        if (unixSocketServer == null) {
//            unixSocketServer = new UnixSocketServer();
//        }
//        if (httpServerImpl == null) {
//            httpServerImpl = new HttpServerImpl();
//        }
//
//        Runnable pingTask = () -> {
//            unixSocketServer.pingServer();
//            httpServerImpl.pingServer();
//        };
//
//        scheduler.scheduleAtFixedRate(pingTask, 0, 5, TimeUnit.SECONDS);
//    }
//}
