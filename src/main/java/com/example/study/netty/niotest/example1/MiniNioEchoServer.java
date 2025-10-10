package com.example.study.netty.niotest.example1;

// MiniNioEchoServer.java
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class MiniNioEchoServer {
    public static void main(String[] args) throws IOException {
        // 0) 포트 결정: 인자가 없으면 기본 9090
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 9090;

        // 1) Selector 생성: 논블로킹 채널들의 준비 상태(accept/read/write)를 모아서 알려주는 이벤트 멀티플렉서
        Selector selector = Selector.open();

        // 2) 서버 소켓 채널 생성 및 논블로킹 전환
        ServerSocketChannel server = ServerSocketChannel.open();     // 채널 생성
        server.configureBlocking(false);                              // 논블로킹 모드(Selector에 등록하려면 필수)
        server.bind(new InetSocketAddress(port));                     // 리슨 소켓 바인딩(0.0.0.0:port)

        // 3) Selector에 서버 채널 등록: "새 연결 수락 가능(ACCEPT)" 이벤트만 감시
        //    반환되는 SelectionKey는 (server, selector) 등록을 대표하는 토큰
        server.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("[start] NIO echo server on " + port + " (single-thread)");

        // 4) 이벤트 루프 시작: 준비된 채널이 생길 때까지 select()로 대기 → 깨어나면 처리
        while (true) {
            System.out.println("checking request....");

            // select(): 최소 하나의 채널이 '준비(ready)' 상태가 될 때까지 블록(타임아웃 없이 대기)
            // 준비되면 selectedKeys()에 해당 키들이 담긴다.
            selector.select();

            // 선택된(준비된) 키들 순회
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove(); // 현재 키를 처리 목록에서 제거(중복 처리 방지)

                try {
                    // 5-A) 새 연결 수락 가능 상태: ServerSocketChannel + OP_ACCEPT
                    if (key.isAcceptable()) {
                        // key.channel()은 등록 당시의 채널(여기서는 ServerSocketChannel)
                        SocketChannel ch = ((ServerSocketChannel) key.channel()).accept(); // 즉시 수락(논블로킹: 없으면 null)
                        if (ch != null) {
                            ch.configureBlocking(false); // 클라이언트 채널도 논블로킹으로
                            // 연결별로 읽기 버퍼를 하나 붙여서(OP_READ 관심) 등록
                            // attachment(여기서는 ByteBuffer)는 다음 이벤트 때 key.attachment()로 다시 꺼내 활용
                            ch.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(8 * 1024));
                            System.out.println("[ACCEPT] " + ch.getRemoteAddress());
                        }

                        // 5-B) 읽기 가능 상태: SocketChannel + OP_READ
                    } else if (key.isReadable()) {
                        SocketChannel ch = (SocketChannel) key.channel();      // 준비된 클라이언트 채널
                        ByteBuffer buf = (ByteBuffer) key.attachment();        // 등록 시 붙여둔 버퍼

                        // 논블로킹 read:
                        //  - >0 : 읽은 바이트 수
                        //  -  0 : 지금은 읽을 데이터가 없음(준비 안 됨)
                        //  - -1 : 원격에서 스트림 종료(소켓 닫힘)
                        int n = ch.read(buf);
                        if (n < 0) {               // 연결 종료
                            ch.close();
                            key.cancel();           // Selector 감시 해제
                            continue;
                        }
                        if (n == 0) continue;      // 지금은 읽을 것 없음 → 다음 이벤트 대기

                        // 에코 처리(학습용 단순화)
                        buf.flip();                 // write용으로 모드 전환 (limit=현재 데이터 끝, position=0)
                        ch.write(buf);              // 보낼 수 있는 만큼 즉시 전송(부분쓰기 발생 가능)
                        buf.compact();              // 남은 데이터 앞당기고 position을 끝으로 이동(다음 read 준비)

                        // ※ 실무 팁:
                        // 부분쓰기로 buf에 데이터가 남았는지 확인해 남았다면
                        // key.interestOps(key.interestOps() | SelectionKey.OP_WRITE)로 OP_WRITE를 켜고
                        // isWritable() 이벤트에서 나머지를 이어 쓰는 방식 권장.
                    }

                } catch (IOException e) {
                    // 채널 I/O 예외 시 안전 종료 및 키 해제
                    try { key.channel().close(); } catch (IOException ignore) {}
                    key.cancel();
                }
            }
            // 여기서 selectedKeys().clear()를 따로 호출할 필요는 없음(위에서 it.remove()로 모두 제거)
        }
    }
}