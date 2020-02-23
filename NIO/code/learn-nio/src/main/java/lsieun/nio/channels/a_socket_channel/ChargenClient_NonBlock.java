package lsieun.nio.channels.a_socket_channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class ChargenClient_NonBlock {
    public static int DEFAULT_PORT = 19;

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 1919;
        try {
            SocketAddress address = new InetSocketAddress(ip, port);
            SocketChannel client = SocketChannel.open(address);
            client.configureBlocking(false); // 非阻塞

            ByteBuffer buffer = ByteBuffer.allocate(74);
            WritableByteChannel out = Channels.newChannel(System.out);
            while (true) {
                // Put whatever code here you want to run every pass through the loop
                // whether anything is read or not
                int n = client.read(buffer);
                if (n > 0) {
                    buffer.flip();
                    out.write(buffer);
                    buffer.clear();
                } else if (n == -1) {
                    // This shouldn't happen unless the server is misbehaving.
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
