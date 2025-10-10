package com.example.study.netty.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        // 1. EventLoopGroup 생성
        // Boss: 연결 수락
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // Worker: 데이터 처리
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 2. ServerBootstrap 설정
            ServerBootstrap b = new ServerBootstrap();
            // 3. Channel 타입 지정
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                    // 4. Handler 설정
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sh) throws Exception {
                        sh.pipeline().addLast(new DiscardServerHandler());
                    }
                })
                // 5. ServerChannel 옵션
                .option(ChannelOption.SO_BACKLOG, 128)
                // 6. Client Channel 옵션
                .childOption(ChannelOption.SO_KEEPALIVE, true);
            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new DiscardServer(port).run();
    }
}
