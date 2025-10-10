package com.example.study.netty.niotest.example1;

// BlockingEchoServer.java
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingEchoServer {
    public static void main(String[] args) throws IOException {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 9091;

        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(true);
        server.bind(new InetSocketAddress(port));
        System.out.println("Blocking echo server on port " + port);

        ExecutorService pool = Executors.newCachedThreadPool();

        while (true) {
            SocketChannel client = server.accept(); // block
            pool.submit(() -> {
                ByteBuffer buf = ByteBuffer.allocate(8 * 1024);
                try (SocketChannel ch = client) {
                    while (true) {
                        buf.clear();
                        int n = ch.read(buf); // block
                        if (n < 0) break;
                        buf.flip();
                        ch.write(buf); // block
                    }
                } catch (IOException ignored) {
                    System.out.println("ignored = " + ignored);
                }
            });
        }
    }
}