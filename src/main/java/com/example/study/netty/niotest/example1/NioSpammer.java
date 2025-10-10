package com.example.study.netty.niotest.example1;

// NioSpammer.java
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class NioSpammer {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length < 2) {
            System.out.println("Usage: java NioSpammer <host> <port> [clients=200] [messages=5] [delayMs=200]");
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        System.out.println("port = " + port);
        int clients = args.length > 2 ? Integer.parseInt(args[2]) : 10000;
        int messages = args.length > 3 ? Integer.parseInt(args[3]) : 10;
        int delay = args.length > 4 ? Integer.parseInt(args[4]) : 200;

        long t0 = System.currentTimeMillis();

        List<SocketChannel> sockets = new ArrayList<>(clients);
        for (int i = 0; i < clients; i++) {
            SocketChannel ch = SocketChannel.open();
            ch.configureBlocking(true); // 클라쪽은 편의상 블로킹
            ch.connect(new InetSocketAddress(host, port));
            sockets.add(ch);
        }
        System.out.println("Connected " + clients + " clients.");

        ByteBuffer buf = ByteBuffer.allocate(1024);
        for (int m = 0; m < messages; m++) {
            for (int i = 0; i < sockets.size(); i++) {
                SocketChannel ch = sockets.get(i);
                String msg = "msg#" + m + " from client#" + i + "\n";
                ch.write(StandardCharsets.UTF_8.encode(msg));
                // 에코 수신(간단 확인)
                buf.clear();
                ch.read(buf);
            }
            if (delay > 0) Thread.sleep(delay);
        }

        for (SocketChannel ch : sockets) { try { ch.close(); } catch (IOException ignore) {} }

        long t1 = System.currentTimeMillis();
        long total = (long) clients * messages;
        double sec = Math.max(0.001, (t1 - t0) / 1000.0);
        System.out.printf("Sent %,d messages in %.3f s  (~%,.0f msg/s)%n", total, sec, total / sec);
    }
}