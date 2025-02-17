package org.dacss.projectinitai.servers;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>{@link WebSocketServer}</h1>
 * The {@code WebSocketServer} class is responsible for initializing and starting
 * a WebSocket server using the Netty framework. It listens for incoming WebSocket
 * connections on a specified port and handles the communication using a custom handler.
 *
 * <p>This implementation utilizes an EventLoopGroup for I/O threading, sets up
 * a pipeline of handlers for HTTP and WebSocket processing, and gracefully shuts
 * down resources when the server is stopped.</p>
 *
 * <p>WebSocket server supports features like:
 *   <ul>
 *     <li>HTTP request decoding and aggregation</li>
 *     <li>Chunked data transfer support</li>
 *     <li>WebSocket protocol handling</li>
 *     <li>Custom handling of WebSocket frames</li>
 *   </ul>
 * </p>
 *
 * @see io.netty.bootstrap.ServerBootstrap
 * @see io.netty.handler.codec.http.HttpServerCodec
 * @see io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler
 *
 */
public class WebSocketServer {

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    private final int port;

    /**
     * Constructs a {@code WebSocketServer} that will listen on the given port.
     *
     * @param port the port number on which the WebSocket server will listen
     */
    public WebSocketServer(int port) {
        if (port < 1 || port > 65535) {
            throw new IllegalArgumentException("Port number must be between 1 and 65535.");
        }
        this.port = port;
    }

    /**
     * <h3>{@link #start()}</h3>
     * Starts the WebSocket server by setting up the Netty server bootstrap configuration
     * and binding it to the specified port. This method blocks the current thread until
     * the server is stopped.
     *
     * <p>The server leverages the boss and worker event loop groups for handling connection
     * requests and data processing, respectively. It also employs a pipeline of handlers
     * to process HTTP requests, transfer data chunks, and manage WebSocket communication.</p>
     *
     * @throws InterruptedException if the current thread is interrupted while waiting
     *                              for the server to shut down
     */
    public void start() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException("Thread was interrupted before starting the WebSocket server.");
        }

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new HttpObjectAggregator(65536));
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                        }
                    });

            // Disable SO_REUSEADDR to prevent port sharing
            bootstrap.option(ChannelOption.SO_REUSEADDR, false);

            try {
                bootstrap.bind(port).sync(); // Attempt binding
                System.out.println("WebSocket Server started on port: " + port);
            } catch (Exception e) {
                if (e.getCause() instanceof java.net.BindException) {
                    System.err.println("Port " + port + " is already in use.");
                    throw new RuntimeException("Cannot start WebSocket server: Port conflict.", e);
                }
                throw e; // Rethrow other unexpected exceptions
            }
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
