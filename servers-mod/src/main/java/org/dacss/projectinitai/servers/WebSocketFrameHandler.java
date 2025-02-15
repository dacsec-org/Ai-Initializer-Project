package org.dacss.projectinitai.servers;

import io.netty.channel.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.dacss.projectinitai.servers.UnixSocketServer;
import reactor.core.publisher.Flux;

public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (frame instanceof TextWebSocketFrame) {
            String request = ((TextWebSocketFrame) frame).text();
            System.out.println("Received: " + request);

            // Communicate with Unix socket server
            UnixSocketServer.sendMessageToLLM(Flux.just(request))
                .subscribe(response -> {
                    ctx.channel().writeAndFlush(new TextWebSocketFrame(response.toString()));
                });
        } else {
            String message = "Unsupported frame type: " + frame.getClass().getName();
            throw new UnsupportedOperationException(message);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
